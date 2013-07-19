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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
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
import com.jaspersoft.studio.server.export.XmlExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MRStyleTemplate;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.server.model.MXmlFile;
import com.jaspersoft.studio.utils.SelectionHelper;

public class OpenInEditorAction extends Action {
	private static final String ID = "OPENINEDITOR"; //$NON-NLS-1$
	private TreeViewer treeViewer;

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

	private boolean isFileResource(Object obj) {
		return (obj != null && (obj instanceof MJrxml || obj instanceof MXmlFile || obj instanceof MRImage || obj instanceof MResourceBundle || obj instanceof MRStyleTemplate));
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				ProgressMonitorDialog pm = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
				try {
					pm.run(true, true, new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							try {
								monitor.beginTask("Open File In Editor", IProgressMonitor.UNKNOWN);
								dorun(obj, monitor);
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

	protected void dorun(final Object obj, IProgressMonitor monitor) throws Exception, FileNotFoundException, IOException {
		if (isFileResource(obj)) {
			MResource res = (MResource) obj;
			ResourceDescriptor rd = res.getValue();

			String fkeyname = ServerManager.getKey(res);
			if (fkeyname == null)
				return;
			String type = rd.getWsType();
			IFile f = null;
			if (type.equals(ResourceDescriptor.TYPE_JRXML)) {
				IFile file = new JrxmlExporter().exportToIFile(res, rd, fkeyname, monitor);
				if (file != null)
					openEditor(file);
				return;
			} else if (type.equals(ResourceDescriptor.TYPE_IMAGE))
				f = new ImageExporter().exportToIFile(res, rd, fkeyname, monitor);
			else if (type.equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE))
				f = new ResourceBundleExporter().exportToIFile(res, rd, fkeyname, monitor);
			else if (type.equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE))
				f = new StyleTemplateExporter().exportToIFile(res, rd, fkeyname, monitor);
			else if (type.equals(ResourceDescriptor.TYPE_XML_FILE))
				f = new XmlExporter().exportToIFile(res, rd, fkeyname, monitor);
			if (f != null)
				openEditor(f);
		}
	}

	private void openEditor(final IFile f) {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				SelectionHelper.openEditor(f);
			}
		});
	}

	private void openEditor(final File f) {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				SelectionHelper.openEditor(f);
			}
		});
	}

}
