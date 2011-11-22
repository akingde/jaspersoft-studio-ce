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
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

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
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.FileUtils;
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
		return (obj != null && (obj instanceof MRImage || obj instanceof MJrxml));
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
			String filename = fileurimap.get(fkeyname);

			File f = null;
			if (filename != null)
				f = new File(filename);
			else {
				f = File.createTempFile("jrsres", ".jrxml");
				f.deleteOnExit();
				filename = f.getAbsolutePath();
			}

			WSClientHelper.getResource(res, rd, f);

			if (rd.getWsType().equals(ResourceDescriptor.TYPE_IMAGE)) {
				filename = f.getAbsolutePath();
				int dotPos = filename.lastIndexOf(".");
				String strFilename = filename.substring(0, dotPos);
				switch (JRTypeSniffer.getImageType(FileUtils.getBytes(f))) {
				case JRRenderable.IMAGE_TYPE_GIF:
					f = FileUtils.fileRenamed(f, strFilename, ".gif", false);
					break;
				case JRRenderable.IMAGE_TYPE_JPEG:
					f = FileUtils.fileRenamed(f, strFilename, ".jpeg", false);
					break;
				case JRRenderable.IMAGE_TYPE_PNG:
					f = FileUtils.fileRenamed(f, strFilename, ".png", false);
					break;
				case JRRenderable.IMAGE_TYPE_TIFF:
					f = FileUtils.fileRenamed(f, strFilename, ".tiff", false);
					break;
				}
				filename = f.getAbsolutePath();
			}
			final String fname = filename;
			fileurimap.put(fkeyname, fname);
			Display.getDefault().asyncExec(new Runnable() {

				public void run() {
					SelectionHelper.openEditor(new File(fname));
				}
			});
		}
	}

	private static Map<String, String> fileurimap = new HashMap<String, String>();
}
