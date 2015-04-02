/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.JREnum;

import org.eclipse.ui.views.properties.ComboBoxLabelProvider;

import com.jaspersoft.studio.utils.EnumHelper;

/**
 * Specialized label provider for {@link NamedEnumPropertyDescriptor} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class NamedEnumLabelProvider extends ComboBoxLabelProvider {

	public NamedEnumLabelProvider(String[] values) {
		super(values);
	}
	
	@Override
	public String getText(Object element) {
		if(element instanceof JREnum) {
			return EnumHelper.getEnumTranslation((JREnum) element);
		}
		return super.getText(element);
	}
	
}