/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.action.resource;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.editor.ReportUnitEditor;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.SelectionHelper;

public class RunReportUnitAction extends Action {
	private static final String ID = "RUNREPORTUNIT";
	private TreeViewer treeViewer;

	public RunReportUnitAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText("Run Report Unit");
		setDescription("Run Report Unit");
		setToolTipText("Run the report unit");
		setImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/resources/eclipse/start_task.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/resources/eclipse/start_task.gif")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				INode node = ((MResource) obj).getReportUnit();
				if (node != null) {
					final String key = ServerManager.getKey((MReportUnit) node);
					if (key != null)
						Display.getDefault().asyncExec(new Runnable() {

							public void run() {
								SelectionHelper.openEditor(key,
										ReportUnitEditor.ID);

							}
						});
				}
				break;
			}
		}
	}
}
