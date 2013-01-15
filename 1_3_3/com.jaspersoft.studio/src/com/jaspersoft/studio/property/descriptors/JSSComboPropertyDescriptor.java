/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPReadCombo;

public class JSSComboPropertyDescriptor extends ComboBoxPropertyDescriptor implements IPropertyDescriptorWidget {
	private String[] labels;

	public JSSComboPropertyDescriptor(Object id, String displayName, String[] labels) {
		super(id, displayName, labels);
		this.labels = labels;
	}

	public String[] getLabels() {
		return labels;
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPReadCombo(parent, section, this);
	}

}
