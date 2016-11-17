/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.editor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentSectionDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

public class ItemPropertiesEditor extends EditorPart {
	private ComponentSectionDescriptor model = new ComponentSectionDescriptor();

	@Override
	public void doSave(IProgressMonitor monitor) {
		ObjectMapper mapper = new ObjectMapper();
		try {

			IEditorInput input = getEditorInput();
			if (input instanceof FileEditorInput) {
				String value = mapper.writeValueAsString(model);
				((FileEditorInput) input).getFile().setContents(
						new ByteArrayInputStream(
								value.getBytes(FileUtils.UTF8_ENCODING)),
						IFile.KEEP_HISTORY | IFile.FORCE, monitor);
			}
			setDirty(false);
		} catch (JsonGenerationException e) {
			UIUtils.showError(e);
		} catch (JsonMappingException e) {
			UIUtils.showError(e);
		} catch (IOException e) {
			UIUtils.showError(e);
		} catch (CoreException e) {
			UIUtils.showError(e);
		} catch (Exception e)
		{
			UIUtils.showError(e);
		}
	}

	@Override
	public void doSaveAs() {
		doSave(new NullProgressMonitor());
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		InputStream content = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			if (input instanceof FileEditorInput) {
				content = ((FileEditorInput) input).getFile().getContents();
				model = mapper.readValue(content,
						ComponentSectionDescriptor.class);
			} else
				model = new ComponentSectionDescriptor();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e)
		{
			UIUtils.showError(e);
		} finally {
			FileUtils.closeStream(content);
		}
	}

	private boolean dirty = false;

	@Override
	public boolean isDirty() {
		return dirty;
	}

	protected void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void createPartControl(final Composite parent) {
		final Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		final Composite c = new Composite(cmp, SWT.BORDER);
		c.setLayout(new GridLayout());
		c.setLayoutData(new GridData(GridData.FILL_BOTH));
		// for (ItemPropertyDescription<?> ipd : model.getProperties()) {
		// new Label(c, SWT.NONE).setText(ipd.getLabel());
		// }

		Button b = new Button(cmp, SWT.PUSH);
		b.setText("Add New Property");
		b.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END));
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PropertyDescriptorDialog d = new PropertyDescriptorDialog(UIUtils.getShell());
				d.setDescriptor(new TextPropertyDescription<String>("property", "Property Label", "Property description", false));
				if (d.open() == Dialog.OK) {
					new Label(c, SWT.NONE)
							.setText(d.getDescriptor().getLabel());
					// model.getProperties().add(d.getDescriptor());
					setDirty(true);
					c.layout(true);
				}
			}
		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

}
