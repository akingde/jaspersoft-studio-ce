/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.checkbox;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.jface.BooleanCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SP3Boolean;
import com.jaspersoft.studio.property.section.widgets.SPBoolean;

/*
 * @author Chicu Veaceslav
 */
public class CheckBoxPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget {
	private NullEnum canBeNull;

	public CheckBoxPropertyDescriptor(Object id, String displayName, NullEnum canBeNull) {
		super(id, displayName);
		setValidator(new BooleanCellEditorValidator(canBeNull));
		this.canBeNull = canBeNull;
	}

	public CheckBoxPropertyDescriptor(Object id, String displayName) {
		this(id, displayName, NullEnum.NOTNULL);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new BCheckBoxCellEditor(parent, canBeNull);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		return editor;

	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new CheckBoxLabelProvider(canBeNull);
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		if (canBeNull == NullEnum.NOTNULL)
			return new SPBoolean(parent, section, this);
		else
			return new SP3Boolean(parent, section, this);
	}
}
