/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWFloatFontSizeCombo;

import net.sf.jasperreports.eclipse.ui.JSSTableCombo;

/**
 * Property descriptor used to input font size number using a {@link JSSTableCombo}
 * 
 * @author Orlandin Marco
 *
 */
public class RWFontSizePropertyDescriptor extends RWFloatComboBoxPropertyDescriptor {

	public RWFontSizePropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull) {
		super(id, displayName, labelsArray, canBeNull, true);
	}

	public RWFontSizePropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull,
			boolean caseSensitive) {
		super(id, displayName, labelsArray, canBeNull, caseSensitive);
	}

	@Override
	public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPRWFloatFontSizeCombo<RWComboBoxPropertyDescriptor>(parent, section, this);
	}
}
