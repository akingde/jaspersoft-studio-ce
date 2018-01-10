/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.tabstops;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.tabstops.dialog.TabStopsEditor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPButton;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.util.JRCloneUtils;

public class TabStopsPropertyDescriptor extends NTextPropertyDescriptor {

	public TabStopsPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new TabStopsCellEditor(parent);
		return editor;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new TabStopsLabelProvider();
	}

	@Override
	public ASPropertyWidget<? extends IPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPButton<IPropertyDescriptor>(parent, section, this, Messages.MParagraph_tabStopsTitle) {
			private List<TabStop> value;

			@Override
			protected void handleButtonPressed() {
				TabStopsEditor wizard = new TabStopsEditor();
				wizard.setValue(JRCloneUtils.cloneList(value));
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK)
					section.changeProperty(pDescriptor.getId(), wizard.getValue());
			}

			@Override
			public void setData(APropertyNode pnode, Object value) {
				this.value = (List<TabStop>) value;
				String size = Misc.isNullOrEmpty(this.value) ? " (Empty)" : " (" + this.value.size() + ")";
				button.setText(Messages.MParagraph_tabStopsTitle + size);
				button.getParent().layout();
			}
		};
	}
}
