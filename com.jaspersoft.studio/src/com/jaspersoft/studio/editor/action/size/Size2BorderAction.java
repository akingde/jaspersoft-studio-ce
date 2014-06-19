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
package com.jaspersoft.studio.editor.action.size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.editor.gef.commands.ResizeCommand;
import com.jaspersoft.studio.editor.report.CachedSelectionProvider;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;

public class Size2BorderAction extends ACachedSelectionAction implements IGlobalAction {

	/**
	 * Indicates that the bottom edges should be aligned.
	 */
	public static final String ID_SIZE_WIDTH = "band_WIDTH"; //$NON-NLS-1$

	/**
	 * Indicates that the horizontal centers should be aligned.
	 */
	public static final String ID_SIZE_HEIGHT = "band_HEIGHT"; //$NON-NLS-1$

	/**
	 * Indicates that the left edges should be aligned.
	 */
	public static final String ID_SIZE_BOTH = "band_BOTH"; //$NON-NLS-1$

	public final static int WIDTH = 0;
	public final static int HEIGHT = 1;
	public final static int BOTH = 2;

	private int alignment;
	
	/**
	 * The elements that will be aligned
	 */
	private static HashMap<CachedSelectionProvider, List<?>> cachedOperationSet = new HashMap<CachedSelectionProvider, List<?>>();

	public Size2BorderAction(IWorkbenchPart part, int alignment) {
		super(part);
		this.alignment = alignment;
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
		GraphicalEditPart part = (GraphicalEditPart) editparts.get(editparts.size() - 1);
		Rectangle rect = new PrecisionRectangle(part.getFigure().getBounds());
		part.getFigure().translateToAbsolute(rect);
		return rect;
	}

	@Override
	protected Command createCommand() {
		AlignmentRequest request = new AlignmentRequest(RequestConstants.REQ_ALIGN);
		request.setAlignmentRectangle(calculateAlignmentRectangle(request));
		request.setAlignment(alignment);
		List<?> editparts = getOperationSet(request);
		if (editparts.isEmpty())
			return null;

		JSSCompoundCommand command = new JSSCompoundCommand(null);
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement){
				command.add(new ResizeCommand(alignment, editpart));
				command.setReferenceNodeIfNull(editpart.getModel());
			}
		}
		return command;
	}

	/**
	 * @see org.eclipse.gef.Disposable#dispose()
	 */
	public void dispose() {
		cachedOperationSet.remove(editor);
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
		List<?> operationSet = 	cachedOperationSet.get(editor);
		if (operationSet != null)
			return operationSet;
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart))
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
		cachedOperationSet.put(editor, editparts);
		return editparts;
	}

	/**
	 * Initializes the actions UI presentation.
	 */
	protected void initUI() {
		switch (alignment) {
		case WIDTH:
			setId(ID_SIZE_WIDTH);
			setText(Messages.Size2BorderAction_fit_width);
			setToolTipText(Messages.Size2BorderAction_fit_width_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseapps/size_to_control_width.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseapps/size_to_control_width.gif")); //$NON-NLS-1$ 
			break;

		case HEIGHT:
			setId(ID_SIZE_HEIGHT);
			setText(Messages.Size2BorderAction_fit_height);
			setToolTipText(Messages.Size2BorderAction_fit_height_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseapps/size_to_control_height.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseapps/size_to_control_height.gif")); //$NON-NLS-1$ 
			break;

		case BOTH:
			setId(ID_SIZE_BOTH);
			setText(Messages.Size2BorderAction_fit_both);
			setToolTipText(Messages.Size2BorderAction_fit_both_tool_tip);
			setImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseapps/size_to_control.gif")); //$NON-NLS-1$
			setDisabledImageDescriptor(
					JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseapps/size_to_control.gif")); //$NON-NLS-1$ 
			break;
		}
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		execute(createCommand());
	}
	
	@Override
	protected void handleSelectionChanged() {
		cachedOperationSet.remove(editor);
		super.handleSelectionChanged();
	}

}
