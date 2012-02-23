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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.ImageExporter;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.export.ResourceBundleExporter;
import com.jaspersoft.studio.server.export.StyleTemplateExporter;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.UIUtils;

public class OpenInEditorAction extends Action {
	private static final String ID = "OPENINEDITOR";
	private TreeViewer treeViewer;

	public OpenInEditorAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText("Open In Editor");
		setDescription("Open resource in the editor");
		setToolTipText("Open resource in the editor");
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && isDataResource();
	}

	private boolean isDataResource() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			if (!isFileResource(p[i].getLastSegment()))
				return false;
		}
		return true;
	}

	private boolean isFileResource(Object obj) {
		return (obj != null && (obj instanceof MRImage || obj instanceof MJrxml || obj instanceof MResourceBundle));
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
						.getDefault().getActiveShell());
				try {
					pm.run(true, true, new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							try {
								dorun(obj);
							} catch (Throwable e) {
								throw new InvocationTargetException(e);
							} finally {
								monitor.done();
							}
						}

					});
				} catch (InvocationTargetException e) {
					UIUtils.showError(e.getCause());
				} catch (InterruptedException e) {
					UIUtils.showError(e);
				}
				break;
			}
		}
	}

	protected void dorun(final Object obj) throws Exception,
			FileNotFoundException, IOException {
		if (isFileResource(obj)) {
			MResource res = (MResource) obj;
			ResourceDescriptor rd = res.getValue();

			String fkeyname = ServerManager.getKey(res);
			if (fkeyname == null)
				return;
			String type = rd.getWsType();
			File f = null;
			if (type.equals(ResourceDescriptor.TYPE_JRXML))
				f = new JrxmlExporter().exportFile(res, rd, fkeyname);
			else if (type.equals(ResourceDescriptor.TYPE_IMAGE))
				f = new ImageExporter().exportFile(res, rd, fkeyname);
			else if (type.equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE))
				f = new ResourceBundleExporter().exportFile(res, rd, fkeyname);
			else if (type.equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE))
				f = new StyleTemplateExporter().exportFile(res, rd, fkeyname);
			if (f != null)
				openEditor(f);
		}
	}

	private void openEditor(final File f) {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				SelectionHelper.openEditor(f);
			}
		});
	}

}
