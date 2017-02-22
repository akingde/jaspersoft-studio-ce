/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPFontNamePopUp;

/**
 * Property descriptor for a combo popup that represent a font
 * @author Orlandin Marco
 *
 */
public class FontNamePropertyDescriptor extends RWCComboPropertyDescriptor {
	
	public FontNamePropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull,
			boolean caseSensitive) {
		super(id, displayName, labelsArray, canBeNull, caseSensitive);
	}

	public FontNamePropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull) {
		super(id, displayName, labelsArray, canBeNull);
	}

	@Override
	public ASPropertyWidget<RWCComboPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPFontNamePopUp<RWCComboPropertyDescriptor>(parent, section, this);
	}
		
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new FontNameCellEditor(parent, labels);
		if (getValidator() != null)
			cellEditor.setValidator(getValidator());
		HelpSystem.bindToHelp(this, cellEditor.getControl());
		return cellEditor;
	}
}
