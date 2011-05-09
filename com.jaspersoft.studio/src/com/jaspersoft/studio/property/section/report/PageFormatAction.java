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

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.model.ANode;

public class PageFormatAction extends Action {
	public static final String ID = "pageFormatAction"; //$NON-NLS-1$
	private GraphicalViewer diagramViewer;

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public PageFormatAction(GraphicalViewer diagramViewer) {
		super("Page Format ...");
		this.diagramViewer = diagramViewer;
		setToolTipText("Page format");
		setId(ID);
		setActionDefinitionId(ID);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		StructuredSelection ss = (StructuredSelection) diagramViewer.getSelection();
		ANode n = (ANode) ((EditPart) ss.getFirstElement()).getModel();
		IFile file = ((IFileEditorInput) ((DefaultEditDomain) diagramViewer.getEditDomain()).getEditorPart()
				.getEditorInput()).getFile();
		PageFormatDialog dlg = new PageFormatDialog(Display.getCurrent().getActiveShell(), n, file);
		if (dlg.open() == Window.OK) {
			diagramViewer.getEditDomain().getCommandStack().execute(dlg.getCommand());
		}
	}
}
