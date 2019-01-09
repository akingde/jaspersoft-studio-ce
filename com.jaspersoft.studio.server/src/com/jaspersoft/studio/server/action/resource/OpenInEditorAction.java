/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.book.BookUtils;
import com.jaspersoft.studio.book.editors.JRBookEditor;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.JRSEditorContributor;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.export.ImageExporter;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class OpenInEditorAction extends Action {
	private static final String ID = "OPENINEDITOR"; //$NON-NLS-1$
	protected TreeViewer treeViewer;
	private boolean openInEditor = true;

	public OpenInEditorAction(TreeViewer treeViewer, boolean openInEditor) {
		this(treeViewer);
		this.openInEditor = openInEditor;

	}

	public OpenInEditorAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.OpenInEditorAction_title);
		setDescription(Messages.OpenInEditorAction_desc);
		setToolTipText(Messages.OpenInEditorAction_desc);
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

	protected boolean isFileResource(Object obj) {
		return (obj != null && (obj instanceof AFileResource && !(obj instanceof MReportUnit)));
	}

	protected boolean preDownload(AFileResource fres) {
		return true;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (isFileResource(obj)) {
				if (preDownload((AFileResource) obj)) {
					WorkspaceJob job = new WorkspaceJob(Messages.OpenInEditorAction_0) {
						public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
							try {
								monitor.beginTask(Messages.OpenInEditorAction_0, IProgressMonitor.UNKNOWN);
								dorun(obj, monitor);
							} catch (Throwable e) {
								UIUtils.showError(e);
							} finally {
								monitor.done();
							}
							return Status.OK_STATUS;
						}
					};
					job.setUser(true);
					job.schedule();
				}
				break;
			}
		}
	}

	protected IPath path;

	protected void dorun(final Object obj, IProgressMonitor monitor)
			throws Exception, FileNotFoundException, IOException {
		if (isFileResource(obj)) {
			AFileResource res = (AFileResource) obj;
			ResourceDescriptor rd = WSClientHelper.getResource(new NullProgressMonitor(), res, res.getValue());
			ANode parent = res.getParent();
			int index = parent.getChildren().indexOf(res);
			parent.removeChild(res);
			res = (AFileResource) ResourceFactory.getResource(parent, rd, index);
			WSClientHelper.fireResourceChanged(res);

			String fkeyname = ServerManager.getKey(res);
			if (fkeyname == null)
				return;
			String type = rd.getWsType();
			IFile f = null;
			if (type.equals(ResourceDescriptor.TYPE_JRXML)) {
				IFile file = new JrxmlExporter(path).exportToIFile(res, rd, fkeyname, monitor);
				if (file != null) {
					JasperReportsConfiguration jrconf = JasperReportsConfiguration.getDefaultJRConfig(file);
					try {
						jrconf.getPrefStore().setValue(JRSEditorContributor.KEY_PUBLISH2JSS_SILENT, true);
						openEditor(file, res);
					} finally {
						jrconf.dispose();
					}
				}
				if (res.getParent() instanceof MReportUnit) {
					MReportUnit runit = (MReportUnit) res.getParent();
					for (INode n : runit.getChildren()) {
						if (n == res)
							continue;
						if (n instanceof AFileResource) {
							AFileResource mfile = (AFileResource) n;
							fkeyname = ServerManager.getKey(mfile);
							rd = WSClientHelper.getResource(new NullProgressMonitor(), mfile, mfile.getValue());
							f = new AExporter(path).exportToIFile(mfile, rd, fkeyname, monitor);
							if (f != null)
								PublishUtil.savePath(f, mfile);
							if (rd.getReferenceUri() != null
									|| !rd.getUriString().startsWith(runit.getValue().getUriString()))
								createLink(f.getLocation(), rd.getName(), file, monitor);
						}
					}
				}
				return;
			} else if (type.equals(ResourceDescriptor.TYPE_IMAGE))
				f = new ImageExporter(path).exportToIFile(res, rd, fkeyname, monitor);
			else
				f = new AExporter(path).exportToIFile(res, rd, fkeyname, monitor);

			if (f != null) {
				PublishUtil.savePath(f, res);
				openEditor(f, res);
			}
			path = null;
		}
	}

	private void createLink(IPath path, String name, IFile file, IProgressMonitor monitor) throws CoreException {
		IProject project = file.getProject();
		IFile newFile = project.getFile(file.getParent().getProjectRelativePath() + "/" + name);
		newFile.createLink(path, IResource.REPLACE, monitor);
	}

	private void openEditor(final IFile f, final AMResource res) {
		// FIXME - temporary fix to handle the case of opening a book from JRS
		BookUtils.checkFileResourceForDefaultEditor(f);
		if (!openInEditor)
			return;
		UIUtils.getDisplay().asyncExec(new Runnable() {

			public void run() {
				if (res instanceof MJrxml)
					if (BookUtils.isValidJRBook(f))
						SelectionHelper.openEditorType(f, JRBookEditor.BOOK_EDITOR_ID);
					else
						SelectionHelper.openEditorType(f, JrxmlEditor.JRXML_EDITOR_ID);
				else
					SelectionHelper.openEditor(f);
			}
		});
	}

}
