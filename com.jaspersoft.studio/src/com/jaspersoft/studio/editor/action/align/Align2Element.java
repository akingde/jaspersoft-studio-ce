/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.align;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.util.Pair;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.messages.Messages;

/**
 * This class copy an alignment action to add the primary element checking, to take it as reference to move all the
 * other selected elements
 * 
 * @author Orlandin Marco
 * 
 */
public class Align2Element extends SelectionAction implements IGlobalAction {

	/**
	 * Indicates that the bottom edges should be aligned.
	 */
	public static final String ID_ALIGN_BOTTOM = GEFActionConstants.ALIGN_BOTTOM;

	/**
	 * Indicates that the horizontal centers should be aligned.
	 */
	public static final String ID_ALIGN_CENTER = GEFActionConstants.ALIGN_CENTER;

	/**
	 * Indicates that the left edges should be aligned.
	 */
	public static final String ID_ALIGN_LEFT = GEFActionConstants.ALIGN_LEFT;

	/**
	 * Indicates that the vertical midpoints should be aligned.
	 */
	public static final String ID_ALIGN_MIDDLE = GEFActionConstants.ALIGN_MIDDLE;

	/**
	 * Indicates that the right edges should be aligned.
	 */
	public static final String ID_ALIGN_RIGHT = GEFActionConstants.ALIGN_RIGHT;

	/**
	 * Indicates that the top edges should be aligned.
	 */
	public static final String ID_ALIGN_TOP = GEFActionConstants.ALIGN_TOP;

	/**
	 * The type of alignment
	 */
	private int alignment;

	/**
	 * The elements that will be aligned
	 */
	private List<?> operationSet;

	/**
	 * Constructs an AlignmentAction with the given part and alignment ID. The alignment ID must by one of:
	 * <UL>
	 * <LI>GEFActionConstants.ALIGN_LEFT
	 * <LI>GEFActionConstants.ALIGN_RIGHT
	 * <LI>GEFActionConstants.ALIGN_CENTER
	 * <LI>GEFActionConstants.ALIGN_TOP
	 * <LI>GEFActionConstants.ALIGN_BOTTOM
	 * <LI>GEFActionConstants.ALIGN_MIDDLE
	 * </UL>
	 * 
	 * @param part
	 *          the workbench part used to obtain context
	 * @param align
	 *          the aligment ID.
	 */
	public Align2Element(IWorkbenchPart part, int align) {
		super(part);
		alignment = align;
		initUI();
	}

	/**
	 * Returns the alignment rectangle to which all selected parts should be aligned. The rectangle coordinate are the
	 * same of the primary element
	 * 
	 * @param request
	 *          the alignment Request
	 * @param primary
	 *          the object used as primary during the alignment
	 * @return the alignment rectangle
	 */
	protected Rectangle calculateAlignmentRectangle(List<?> editparts, Object primary) {
		if (editparts == null || editparts.isEmpty())
			return null;
		GraphicalEditPart part = (GraphicalEditPart) primary;
		Rectangle rect = new PrecisionRectangle(part.getFigure().getBounds());
		part.getFigure().translateToAbsolute(rect);
		return rect;
	}

	/**
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		operationSet = null;
		Command cmd = createAlignmentCommand();
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	/**
	 * Create the alignment command for the selected elements
	 * 
	 * @return the alignment command
	 */
	private Command createAlignmentCommand() {
		AlignmentRequest request = new AlignmentRequest(RequestConstants.REQ_ALIGN);
		// Validation of selected elements and choose of the primary
		Pair<List<?>, Object> editPartsAndPrimary = getOperationSet(request);
		List<?> editparts = editPartsAndPrimary.first();
		Object primary = editPartsAndPrimary.second();
		request.setAlignmentRectangle(calculateAlignmentRectangle(editparts, primary));
		request.setAlignment(alignment);

		if (editparts.size() < 2)
			return null;

		JSSCompoundCommand command = new JSSCompoundCommand(null);
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			command.setReferenceNodeIfNull(editpart.getModel());
			command.add(editpart.getCommand(request));
		}
		return command;
	}

	/**
	 * @see org.eclipse.gef.Disposable#dispose()
	 */
	public void dispose() {
		operationSet = Collections.EMPTY_LIST;
		super.dispose();
	}

	/**
	 * Return the primary object of the selections, or the last object if none of them is the primary
	 * 
	 * @param editparts
	 *          List of selected objects
	 * @return The primary object or a substitute if itsn't present
	 */
	protected Object getPrimary(List<?> editparts) {
		// editparts must be already checked to be sure that the list is not void
		Iterator<?> it = editparts.iterator();
		boolean primaryFound = false;
		EditPart actualPart = null;
		while (it.hasNext() && !primaryFound) {
			actualPart = (EditPart) it.next();
			if (actualPart.getSelected() == EditPart.SELECTED_PRIMARY) {
				primaryFound = true;
			}
		}
		return actualPart;
	}

	/**
	 * Returns the list of editparts which will participate in alignment and the primary object used to calculate the
	 * position
	 * 
	 * @param request
	 *          the alignment request
	 * @return A pair of element: a list of the editparts that take part in the selection and an edit part to take a
	 *         reference to set the position of all the others
	 */
	protected Pair<List<?>, Object> getOperationSet(Request request) {
		if (operationSet != null) {
			Object primary = getPrimary(operationSet);
			return new Pair<List<?>, Object>(operationSet, primary);
		}
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart))
			return new Pair<List<?>, Object>(Collections.EMPTY_LIST, null);
		Object primary = getPrimary(editparts);// editparts.get(editparts.size() - 1);
		editparts = ToolUtilitiesCompatibility.getSelectionWithoutDependants(editparts);
		ToolUtilities.filterEditPartsUnderstanding(editparts, request);
		if (editparts.size() < 2 || !editparts.contains(primary))
			return new Pair<List<?>, Object>(Collections.EMPTY_LIST, null);
		EditPart parent = ((EditPart) editparts.get(0)).getParent();
		for (int i = 1; i < editparts.size(); i++) {
			EditPart part = (EditPart) editparts.get(i);
			if (part.getParent() != parent)
				return new Pair<List<?>, Object>(Collections.EMPTY_LIST, null);
		}
		return new Pair<List<?>, Object>(editparts, primary);
	}

	/**
	 * Initializes the actions UI presentation.
	 */
	protected void initUI() {
		switch (alignment) {
		case PositionConstants.LEFT:
			setId(ID_ALIGN_LEFT);
			setText(Messages.Align2Element_Align_To_Left);
			setToolTipText(Messages.Align2Element_Align_To_Left_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/align-left.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/disabled/align-left.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.RIGHT:
			setId(ID_ALIGN_RIGHT);
			setText(Messages.Align2Element_Align_To_Right);
			setToolTipText(Messages.Align2Element_Align_To_Right_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/align-right.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/disabled/align-right.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.TOP:
			setId(ID_ALIGN_TOP);
			setText(Messages.Align2Element_Align_To_Top);
			setToolTipText(Messages.Align2Element_Align_To_Top_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/align-top.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/disabled/align-top.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.BOTTOM:
			setId(ID_ALIGN_BOTTOM);
			setText(Messages.Align2Element_Align_To_Bottom);
			setToolTipText(Messages.Align2Element_Align_To_Bottom_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/align-bottom.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/disabled/align-bottom.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.CENTER:
			setId(ID_ALIGN_CENTER);
			setText(Messages.Align2Element_Align_To_Center);
			setToolTipText(Messages.Align2Element_Align_To_Center_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/align-center.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/disabled/align-center.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.MIDDLE:
			setId(ID_ALIGN_MIDDLE);
			setText(Messages.Align2Element_Align_To_Middle);
			setToolTipText(Messages.Align2Element_Align_To_Middle_tooltip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/align-middle.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/disabled/align-middle.gif")); //$NON-NLS-1$ 
			break;
		}
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		operationSet = null;
		execute(createAlignmentCommand());
	}

}
