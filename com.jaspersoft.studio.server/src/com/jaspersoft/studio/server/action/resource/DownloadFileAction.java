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
package com.jaspersoft.studio.server.action.resource;

import java.io.File;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.type.ImageTypeEnum;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.SaveAsDialog;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.utils.SelectionHelper;

public class DownloadFileAction extends Action {
	private static final String ID = "DOWNLOADJSRESOURCE";
	private TreeViewer treeViewer;

	public DownloadFileAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText("Do&wnload To File");
		setDescription("Download File");
		setToolTipText("Download File associated with this resource");
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/drive-download.png")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/drive-download.png")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && isRunnable();
	}

	private boolean isRunnable() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			if (!isInReportUnit(p[i].getLastSegment()))
				return false;
		}
		return true;
	}

	private boolean isInReportUnit(Object obj) {
		return (obj != null && obj instanceof AFileResource);
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof AFileResource) {
				try {
					final AFileResource res = (AFileResource) obj;
					ResourceDescriptor rd = WSClientHelper.getResource(res, res.getValue());
					SaveAsDialog saveAsDialog = new SaveAsDialog(Display.getDefault().getActiveShell());
					saveAsDialog.setOriginalName(rd.getName() + "." + res.getDefaultFileExtension());
					if (saveAsDialog.open() == Dialog.OK) {
						final IPath path = saveAsDialog.getResult();
						if (path != null) {
							WorkspaceJob job = new WorkspaceJob("Saving file") {

								@Override
								public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
									monitor.beginTask("Saving file", IProgressMonitor.UNKNOWN);
									IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
									try {
										File f = file.getLocation().toFile();
										WSClientHelper.getResource(res, res.getValue(), f);
										file.refreshLocal(1, monitor);
										if (res.getValue().getWsType().equals(ResourceDescriptor.TYPE_IMAGE)) {
											ImageTypeEnum itype = JRTypeSniffer.getImageTypeValue(FileUtils.getBytes(f));

											String fname = file.getFullPath().toOSString();
											if (file.getFileExtension() != null && !file.getFileExtension().isEmpty())
												fname = fname.substring(0, fname.length() - file.getFileExtension().length() - 1);
											if (itype == ImageTypeEnum.GIF)
												file = move(file, new Path(fname + ".gif"), monitor);
											else if (itype == ImageTypeEnum.JPEG)
												file = move(file, new Path(fname + ".jpeg"), monitor);
											else if (itype == ImageTypeEnum.PNG)
												file = move(file, new Path(fname + ".png"), monitor);
											else if (itype == ImageTypeEnum.TIFF)
												file = move(file, new Path(fname + ".tiff"), monitor);
										}

										openInEditor(file);
									} catch (Exception e) {
										UIUtils.showError(e);
									}
									return Status.OK_STATUS;
								}

								protected void openInEditor(final IFile file) {
									Display.getDefault().asyncExec(new Runnable() {

										public void run() {
											SelectionHelper.openEditor(file);
										}
									});
								}
							};
							job.schedule();
						}
					}
				} catch (Exception e) {
					UIUtils.showError(e);
				}
				break;
			}
		}
	}

	private IFile move(IFile file, Path path, IProgressMonitor monitor) throws CoreException {
		IFile f = file.getProject().getFile(path.removeFirstSegments(1));
		if (f != null && f.exists())
			f.delete(true, monitor);

		file.move(path, true, monitor);
		f.refreshLocal(1, monitor);
		return f;
	}
}
