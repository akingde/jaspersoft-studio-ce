/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPReadCombo;

public class JSSComboPropertyDescriptor extends ComboBoxPropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	protected String[] labels;

	protected SPReadCombo combo;

	public JSSComboPropertyDescriptor(Object id, String displayName, String[] labels) {
		super(id, displayName, labels);
		this.labels = labels;
	}

	public String[] getLabels() {
		return labels;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = super.createPropertyEditor(parent);
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public SPReadCombo createWidget(Composite parent, AbstractSection section) {
		combo = new SPReadCombo(parent, section, this);
		return combo;
	}

	private IHelpRefBuilder refBuilder;

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}

	public void setItems(String[] items) {
		labels = items;
		if (combo != null && !combo.getControl().isDisposed() && !Arrays.equals(labels, combo.getItems())) {
			combo.setItems(labels);
		}
	}
}
