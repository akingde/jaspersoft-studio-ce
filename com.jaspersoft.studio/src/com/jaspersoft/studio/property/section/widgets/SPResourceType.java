/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;



import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPResourceType<T extends IPropertyDescriptor> extends SPText<T> {

	private Button btn;

	public SPResourceType(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public void setReadOnly(boolean readonly) {
		super.setReadOnly(readonly);
		btn.setEnabled(!readonly);
	}
	
	protected SelectionAdapter buttonPressed(){
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(ftext.getShell(), false,
						ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				dialog.setTitle(Messages.ResourceCellEditor_open_resource);
				dialog.setInitialPattern("*.properties"); //$NON-NLS-1$
				if (dialog.open() == Window.OK) {
					IFile file = (IFile) dialog.getFirstResult();
					if (file != null)
						handleTextChanged(section, pDescriptor.getId(), convertFile2Value(file));
				}
			}
		};
	}
	
	@Override
	protected int getStyle() {
		return SWT.BORDER;
	}

	protected void createComponent(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		container.setLayout(layout);
		super.createComponent(container);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.minimumWidth = 50;
		ftext.setLayoutData(textData);
		btn = section.getWidgetFactory().createButton(container, "...", SWT.PUSH);
		btn.addSelectionListener(buttonPressed());
	}

	protected String convertFile2Value(IFile f) {
		return f.getProjectRelativePath().toOSString();
	}
	
	@Override
	public Control getControl() {
		return btn.getParent();
	}

	protected void handleTextChanged(final AbstractSection section, final Object property, String text) {
		if (text != null && text.trim().isEmpty())
			text = null;
		section.changeProperty(property, text);
	}

}
