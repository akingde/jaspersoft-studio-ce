/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWLanguageCombo;

/**
 * Custom {@link RWComboBoxPropertyDescriptor} to be used for the language of the report.
 * It provide a widget able to mark the beanshell language as deprecated
 * 
 * @author Orlandin Marco
 *
 */
public class RWLanguageComboBoxPropertyDescriptor extends RWComboBoxPropertyDescriptor {

	public RWLanguageComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull, boolean caseSensitive) {
		super(id, displayName, labelsArray, canBeNull, caseSensitive);
	}
	
	public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		combo = new SPRWLanguageCombo<RWComboBoxPropertyDescriptor>(parent, section, this);
		return combo;
	}
}
