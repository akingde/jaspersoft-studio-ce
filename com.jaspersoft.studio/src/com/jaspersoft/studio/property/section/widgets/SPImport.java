/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.classname.dialog.ImportDialog;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPImport extends SPText<IPropertyDescriptor> {

	private Composite container;
	
	public SPImport(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}
	
	@Override
	protected int getStyle() {
		return SWT.BORDER;
	}

	protected void createComponent(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		container.setLayout(layout);
		super.createComponent(container);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.minimumWidth = 50;
		ftext.setLayoutData(textData);

		Button btn = section.getWidgetFactory().createButton(container, "...", SWT.PUSH);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportDialog dialog = new ImportDialog(Display.getDefault().getActiveShell(), ftext.getText());
				if (dialog.open() == Dialog.OK)
					handleTextChanged(section, pDescriptor.getId(), dialog.getImports());
			}
		});
	}
	
	@Override
	public Control getControl() {
		return container;
	}

}
