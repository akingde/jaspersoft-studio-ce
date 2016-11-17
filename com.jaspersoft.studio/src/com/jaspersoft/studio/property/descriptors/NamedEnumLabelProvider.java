/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
