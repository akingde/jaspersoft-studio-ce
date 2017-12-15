/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.editor.preview.ABasicEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.dialog.RunWithProgressBar;
import com.jaspersoft.studio.utils.jobs.CheckedRunnableWithProgress;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.eclipse.builder.Markers;
import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.ParameterContributorContext;

public class DataAdapterEditorPart extends ABasicEditor {

	private final class ModelPropertyChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			getSite().getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					isDirty = true;
					firePropertyChange(ISaveablePart.PROP_DIRTY);
				}
			});

		}
	}

	public static final String ID = "com.jaspersoft.studio.data.DataAdapterEditorPart"; //$NON-NLS-1$
	private DataAdapterDescriptor descriptor;
	private ModelPropertyChangeListener modelListener = new ModelPropertyChangeListener();
	private NameComposite nameComposite;
	private DataAdapterEditor editor;
	private ADataAdapterComposite dacomposite;

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

			descriptor = FileDataAdapterStorage.readDataADapter(in, file, jrContext);
			if (descriptor == null)
				throw new PartInitException("Can't find DataAdapter mapping."); //$NON-NLS-1$
		} catch (CoreException e) {
			UIUtils.showError(e);
		} finally {
			FileUtils.closeStream(in);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			IResource resource = ((IFileEditorInput) getEditorInput()).getFile();
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			descriptor = dacomposite.getDataAdapter();
			dacomposite.performAdditionalUpdates();

			String xml = DataAdapterManager.toDataAdapterFile(descriptor, jrContext);

			file.setContents(new ByteArrayInputStream(xml.getBytes()), true, true, monitor);
			Markers.deleteMarkers(resource);
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

	@Override
	public void createPartControl(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		nameComposite = new NameComposite(c, SWT.NONE, jrContext);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		nameComposite.setLayoutData(gd);
		if (descriptor != null) {
			editor = descriptor.getEditor();
			dacomposite = editor.getComposite(c, SWT.NONE, null, jrContext);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(c, editor.getHelpContextId());
			nameComposite.addModifyListener(modelListener);
			dacomposite.addModifyListener(modelListener);

			gd = new GridData(GridData.FILL_BOTH);
			gd.horizontalSpan = 2;
			dacomposite.setLayoutData(gd);

			editor.setDataAdapter(descriptor);
			nameComposite.setDataAdapter(descriptor);

			final Button btnTest = new Button(c, SWT.PUSH);
			btnTest.setText(Messages.DataAdapterEditorPart_testButton);

			Composite cmp = new Composite(c, SWT.NONE);
			cmp.setLayout(new GridLayout(2, false));
			cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			final RunWithProgressBar runner = new RunWithProgressBar(cmp);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.heightHint = 20;
			runner.getProgressBar().setLayoutData(gd);

			final DataAdapterService das = DataAdapterServiceUtil
					.getInstance(new ParameterContributorContext(jrContext, null, null))
					.getService(editor.getDataAdapter().getDataAdapter());
			btnTest.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						runner.run(false, true, new CheckedRunnableWithProgress() {

							@Override
							protected void runOperations(IProgressMonitor monitor) {
								UIUtils.getDisplay().syncExec(new Runnable() {
									public void run() {
										btnTest.setEnabled(false);
									}
								});

								monitor.beginTask(Messages.DataAdapterEditorPart_0, IProgressMonitor.UNKNOWN);
								ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
								try {
									IProject project = ((IFileEditorInput) getEditorInput()).getFile().getProject();
									if (project.hasNature(JavaCore.NATURE_ID)) {
										ClassLoader cl = JavaProjectClassLoader.instance(JavaCore.create(project));
										if (cl != null)
											Thread.currentThread().setContextClassLoader(cl);
									}
									das.test();
									UIUtils.getDisplay().syncExec(new Runnable() {
										public void run() {
											MessageBox mb = new MessageBox(btnTest.getShell(), SWT.ICON_INFORMATION | SWT.OK);
											mb.setText(Messages.DataAdapterWizard_testbutton);
											mb.setMessage(Messages.DataAdapterWizard_testsuccesful);
											mb.open();
										}
									});
								} catch (Exception e1) {
									UIUtils.showError(e1);
								} finally {
									Thread.currentThread().setContextClassLoader(oldCL);
									UIUtils.getDisplay().syncExec(new Runnable() {
										public void run() {
											btnTest.setEnabled(true);
										}
									});
								}
							}
						});
					} catch (InvocationTargetException e1) {
						UIUtils.showError(e1.getTargetException());
					} catch (InterruptedException e1) {
						UIUtils.showError(e1);
					}
				}
			});
		}

	}

	@Override
	public void setFocus() {
		nameComposite.setFocus();
	}

}
