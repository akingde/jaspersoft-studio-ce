/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.imageprovider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPText;

public class SPImageProvider extends SPText<IPropertyDescriptor> {

	private Button btn;

	public SPImageProvider(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public void setReadOnly(boolean readonly) {
		super.setReadOnly(readonly);
		btn.setEnabled(!readonly);
	}

	protected void createComponent(Composite parent) {
		super.createComponent(parent);

		btn = section.getWidgetFactory().createButton(parent, "...", SWT.PUSH);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(ftext.getShell(), false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				dialog.setTitle(Messages.ResourceCellEditor_open_resource);
				dialog.setInitialPattern("*.*"); //$NON-NLS-1$
				if (dialog.open() == Window.OK) {
					IFile file = (IFile) dialog.getFirstResult();
					if (file != null)
						handleTextChanged(section, pDescriptor.getId(), file.getRawLocation().toOSString());
				}
			}
		});
	}

}
