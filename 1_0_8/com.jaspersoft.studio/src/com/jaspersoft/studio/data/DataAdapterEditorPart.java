/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.editor.preview.ABasicEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;

public class DataAdapterEditorPart extends ABasicEditor {
	private DataAdapterDescriptor descriptor;
	private ModelPropertyChangeListener modelListener = new ModelPropertyChangeListener();
	private NameComposite nameComposite;
	private DataAdapterEditor editor;

	public DataAdapterEditorPart() {
		super(true);
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		InputStream in = null;
		try {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			in = file.getContents(true);

			descriptor = FileDataAdapterStorage.readDataADapter(in);
		} catch (CoreException e) {
			UIUtils.showError(e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			IResource resource = ((IFileEditorInput) getEditorInput()).getFile();
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();

			String xml = DataAdapterManager.toDataAdapterFile(descriptor);

			file.setContents(new ByteArrayInputStream(xml.getBytes()), true, true, monitor);

			resource.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			UIUtils.showError(e);
		}
		isDirty = false;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.setOriginalFile(((FileEditorInput) getEditorInput()).getFile());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				IFileEditorInput modelFile = new FileEditorInput(file);
				setInputWithNotify(modelFile);
				setInput(modelFile);
				setPartName(file.getName());
				IProgressMonitor progressMonitor = getEditorSite().getActionBars().getStatusLineManager().getProgressMonitor();
				doSave(progressMonitor);
			}
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	private final class ModelPropertyChangeListener implements PropertyChangeListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			getSite().getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					isDirty = true;
					firePropertyChange(ISaveablePart.PROP_DIRTY);
				}
			});

		}
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.justify = false;
		rowLayout.pack = true;
		rowLayout.fill = true;
		c.setLayout(rowLayout);

		nameComposite = new NameComposite(c, SWT.NONE);

		editor = descriptor.getEditor();
		ADataAdapterComposite composite = editor.getComposite(c, SWT.NONE, null);

		nameComposite.addModifyListener(modelListener);
		composite.addModifyListener(modelListener);

		editor.setDataAdapter(descriptor);
		nameComposite.setDataAdapter(descriptor);

		final Button btnTest = new Button(c, SWT.PUSH);
		btnTest.setText("Test Connection");

		btnTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
				try {
					ClassLoader cl = new JavaProjectClassLoader(JavaCore.create(((IFileEditorInput) getEditorInput()).getFile()
							.getProject()));

					if (cl != null)
						Thread.currentThread().setContextClassLoader(cl);

					DataAdapterServiceUtil.getDataAdapterService(editor.getDataAdapter().getDataAdapter()).test();

					MessageBox mb = new MessageBox(btnTest.getShell(), SWT.ICON_INFORMATION | SWT.OK);
					mb.setText(Messages.DataAdapterWizard_testbutton);
					mb.setMessage(Messages.DataAdapterWizard_testsuccesful);
					mb.open();
				} catch (Exception e1) {
					UIUtils.showError(e1);
				} finally {
					Thread.currentThread().setContextClassLoader(oldCL);
				}
			}
		});
	}

	@Override
	public void setFocus() {
		nameComposite.setFocus();
	}

}
