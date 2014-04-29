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
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWFloatCombo;

/**
 * Property descriptor used to input float number
 * 
 * @author Orlandin Marco
 *
 */
public class RWFloatComboBoxPropertyDescriptor extends RWComboBoxPropertyDescriptor implements IPropertyDescriptorWidget,
		IHelp {

	public RWFloatComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull) {
		super(id, displayName, labelsArray, canBeNull, true);
	}

	public RWFloatComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull,
			boolean caseSensitive) {
		super(id, displayName, labelsArray, canBeNull, caseSensitive);
	}


	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPRWFloatCombo(parent, section, this);
	}

}
