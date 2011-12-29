package com.jaspersoft.studio.data;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.editor.preview.ABasicEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;

public class DataAdapterEditorPart extends ABasicEditor {
	private DataAdapterDescriptor descriptor;
	private Composite composite;

	public DataAdapterEditorPart() {
		super(true);
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		InputStream in;
		try {
			in = ((IFileEditorInput) getEditorInput()).getFile().getContents();
			descriptor = DataAdapterManager.readDataADapter(in);
		} catch (CoreException e) {
			UIUtils.showError(e);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		IResource resource = ((IFileEditorInput) getEditorInput()).getFile();
		// IDocumentProvider dp = getDocumentProvider();
		// IDocument doc = dp.getDocument(getEditorInput());
		// doc.set(DataAdapterManager.toDataAdapterFile(descriptor));

		try {
			resource.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			UIUtils.showError(e);
		}

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
		c.setLayout(new GridLayout());

		final DataAdapterEditor editor = descriptor.getEditor();
		composite = editor.getComposite(c, SWT.NONE, null);

		editor.setDataAdapter(descriptor);

		final Button btnTest = new Button(c, SWT.PUSH);
		btnTest.setText("Test Connection");
		btnTest.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

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
		composite.setFocus();
	}

}
