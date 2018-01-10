/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.marker.dialog.MarkerEditor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

import net.sf.jasperreports.components.items.StandardItemData;

public class SPMarker extends ASPropertyWidget<MarkerDescriptor> {

	private Button btn;

	public SPMarker(Composite parent, AbstractSection section, MarkerDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	protected void createComponent(Composite parent) {
		btn = new Button(parent, SWT.PUSH);
		btn.setText(Messages.SPMarker_MarkersListBtn);
		btn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				MarkerEditor wizard = new MarkerEditor();
				wizard.setValue(dto);
				WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK)
					section.changeProperty(StandardItemData.PROPERTY_ITEMS, wizard.getValue());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private MarkersDTO dto;

	@Override
	public void setData(APropertyNode pnode, Object value) {
		if (value instanceof MarkersDTO)
			dto = (MarkersDTO) value;
	}

	@Override
	public Control getControl() {
		return btn;
	}

}
