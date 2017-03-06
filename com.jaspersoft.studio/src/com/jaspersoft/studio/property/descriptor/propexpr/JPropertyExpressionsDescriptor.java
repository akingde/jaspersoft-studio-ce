/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPPropertyExpressionButton;

public class JPropertyExpressionsDescriptor extends NTextPropertyDescriptor {

	public JPropertyExpressionsDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		JPropertyExpressionsCellEditor editor = new JPropertyExpressionsCellEditor(parent);
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet())
			return super.getLabelProvider();
		return new JPropertyExpressionsLabelProvider();
	}

	@Override
	public SPPropertyExpressionButton createWidget(Composite parent, AbstractSection section) {
		return new SPPropertyExpressionButton(parent, section, this, getDisplayName());
	}
}
