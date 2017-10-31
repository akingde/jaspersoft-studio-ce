/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report.action;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.section.report.PageFormatDialog;

public class PageFormatAction extends ACachedSelectionAction {
	public static final String ID = "pageFormatAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *            the GraphicalViewer whose grid enablement and visibility
	 *            properties are to be toggled
	 */
	public PageFormatAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText(Messages.PageFormatAction_actionName);
		setToolTipText(Messages.PageFormatAction_actionTooltip);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/transparent_icon.png"));
		setEnabled(false);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		ReportEditor part = (ReportEditor) getWorkbenchPart();
		ANode n = (ANode) part.getModel().getChildren().get(0);
		PageFormatDialog dlg = new PageFormatDialog(Display.getCurrent().getActiveShell(), n);
		if (dlg.open() == Window.OK)
			execute(dlg.getCommand());
	}

	@Override
	protected boolean calculateEnabled() {
		if (getSelectedObjects().size() > 1)
			return false;

		List<Object> pageEditPart = editor.getSelectionCache().getSelectionPartForType(ReportPageEditPart.class);
		if (pageEditPart.size() > 0)
			return true;

		List<Object> bandEditParts = editor.getSelectionCache().getSelectionPartForType(BandEditPart.class);
		if (bandEditParts.size() > 0)
			return true;

		return false;
	}
}
