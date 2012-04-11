/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.report;

import java.util.List;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.model.ANode;

public class PageFormatAction extends SelectionAction {
	public static final String ID = "pageFormatAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
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
		setText("Page Format ...");
		setToolTipText("Page format");
		setId(ID);
		setEnabled(false);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		ReportEditor part = (ReportEditor) getWorkbenchPart();
		ANode n = (ANode) part.getModel().getChildren().get(0);
		PageFormatDialog dlg = new PageFormatDialog(Display.getCurrent().getActiveShell(), n);
		if (dlg.open() == Window.OK) {
			part.getEditDomain().getCommandStack().execute(dlg.getCommand());
		}
	}

	@Override
	protected boolean calculateEnabled() {
		List<Object> selection = getSelectedObjects();
		if (!selection.isEmpty() && selection.size() == 1) {
			Object obj = selection.get(0);
			if (obj instanceof ReportPageEditPart || obj instanceof BandEditPart)
				return true;
		}

		return false;
	}
}
