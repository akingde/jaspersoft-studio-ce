/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.align;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.editor.gef.commands.AlignCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;

public class Align2BorderAction extends ACachedSelectionAction implements IGlobalAction {

	/**
	 * Indicates that the bottom edges should be aligned.
	 */
	public static final String ID_ALIGN_BOTTOM = "band_" + GEFActionConstants.ALIGN_BOTTOM; //$NON-NLS-1$

	/**
	 * Indicates that the horizontal centers should be aligned.
	 */
	public static final String ID_ALIGN_CENTER = "band_" + GEFActionConstants.ALIGN_CENTER; //$NON-NLS-1$

	/**
	 * Indicates that the left edges should be aligned.
	 */
	public static final String ID_ALIGN_LEFT = "band_" + GEFActionConstants.ALIGN_LEFT; //$NON-NLS-1$

	/**
	 * Indicates that the vertical midpoints should be aligned.
	 */
	public static final String ID_ALIGN_MIDDLE = "band_" + GEFActionConstants.ALIGN_MIDDLE; //$NON-NLS-1$

	/**
	 * Indicates that the right edges should be aligned.
	 */
	public static final String ID_ALIGN_RIGHT = "band_" + GEFActionConstants.ALIGN_RIGHT; //$NON-NLS-1$

	/**
	 * Indicates that the top edges should be aligned.
	 */
	public static final String ID_ALIGN_TOP = "band_" + GEFActionConstants.ALIGN_TOP; //$NON-NLS-1$
	private int alignment;

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
	public Align2BorderAction(IWorkbenchPart part, int align) {
		super(part);
		alignment = align;
		initUI();
	}

	/**
	 * Returns the alignment rectangle to which all selected parts should be aligned.
	 * 
	 * @param request
	 *          the alignment Request
	 * @return the alignment rectangle
	 */
	protected Rectangle calculateAlignmentRectangle(Request request) {
		List<?> editparts = getOperationSet(request);
		if (editparts == null || editparts.isEmpty())
			return null;
		EditPart part = (EditPart) editparts.get(editparts.size() - 1);
		if (part instanceof GraphicalEditPart) {
			Rectangle rect = new PrecisionRectangle(((GraphicalEditPart) part).getFigure().getBounds());
			((GraphicalEditPart) part).getFigure().translateToAbsolute(rect);
			return rect;
		} else if (part.getModel() instanceof MGraphicElement) {
			MGraphicElement m = (MGraphicElement) part.getModel();
			return m.getBounds();
		}
		return null;
	}

	/**
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		operationSet = null;
		return super.calculateEnabled();
	}

	protected Command createCommand(List<?> selectedObjects) {
		AlignmentRequest request = new AlignmentRequest(RequestConstants.REQ_ALIGN);
		request.setAlignmentRectangle(calculateAlignmentRectangle(request));
		request.setAlignment(alignment);
		List<?> editparts = getOperationSet(request);
		if (editparts.isEmpty())
			return null;

		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement)
				command.add(new AlignCommand(alignment, editpart));
			// command.add(editpart.getCommand(request));
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
	 * Returns the list of editparts which will participate in alignment.
	 * 
	 * @param request
	 *          the alignment request
	 * @return the list of parts which will be aligned
	 */
	protected List<?> getOperationSet(Request request) {
		if (operationSet != null)
			return operationSet;
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		if (editparts.isEmpty() || !(editparts.get(0) instanceof EditPart))
			return Collections.EMPTY_LIST;
		Object primary = editparts.get(editparts.size() - 1);
		editparts = ToolUtilitiesCompatibility.getSelectionWithoutDependants(editparts);
		ToolUtilities.filterEditPartsUnderstanding(editparts, request);
		if (!editparts.contains(primary))
			return Collections.EMPTY_LIST;
		EditPart parent = ((EditPart) editparts.get(0)).getParent();
		for (int i = 1; i < editparts.size(); i++) {
			EditPart part = (EditPart) editparts.get(i);
			if (part.getParent() != parent)
				return Collections.EMPTY_LIST;
		}
		return editparts;
	}

	/**
	 * Initializes the actions UI presentation.
	 */
	protected void initUI() {
		switch (alignment) {
		case PositionConstants.LEFT:
			setId(ID_ALIGN_LEFT);
			setText(Messages.Align2BorderAction_align_to_left);
			setToolTipText(Messages.Align2BorderAction_align_to_left_tool_tip);
			setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/align-band-left.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/disabled/align-band-left.gif")); //$NON-NLS-1$ 
			break;
		case PositionConstants.RIGHT:
			setId(ID_ALIGN_RIGHT);
			setText(Messages.Align2BorderAction_align_to_right);
			setToolTipText(Messages.Align2BorderAction_align_to_right_tool_tip);
			setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/align-band-right.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/disabled/align-band-right.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.TOP:
			setId(ID_ALIGN_TOP);
			setText(Messages.Align2BorderAction_align_to_top);
			setToolTipText(Messages.Align2BorderAction_align_to_top_tool_tip);
			setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/align-band-top.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/disabled/align-band-top.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.BOTTOM:
			setId(ID_ALIGN_BOTTOM);
			setText(Messages.Align2BorderAction_align_to_bottom);
			setToolTipText(Messages.Align2BorderAction_align_to_bottom_tool_tip);
			setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/align-band-bottom.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/disabled/align-band-bottom.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.CENTER:
			setId(ID_ALIGN_CENTER);
			setText(Messages.Align2BorderAction_align_to_center);
			setToolTipText(Messages.Align2BorderAction_align_to_center_tool_tip);
			setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/align-band-center.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/disabled/align-band-center.gif")); //$NON-NLS-1$ 
			break;

		case PositionConstants.MIDDLE:
			setId(ID_ALIGN_MIDDLE);
			setText(Messages.Align2BorderAction_align_to_middle);
			setToolTipText(Messages.Align2BorderAction_align_to_middle_tool_tip);
			setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/align-band-middle.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(
					"icons/resources/eclipse/disabled/align-band-middle.gif")); //$NON-NLS-1$ 
			break;
		}
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		operationSet = null;
		execute(createCommand(getSelectedObjects()));
	}

}
