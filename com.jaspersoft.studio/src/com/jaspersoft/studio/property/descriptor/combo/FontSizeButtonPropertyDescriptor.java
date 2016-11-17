/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPFontSize;

/**
 * This class describe a toolbar with two buttons to increment and decrement the font size
 * 
 * @author Orlandin Marco
 * 
 */
public class FontSizeButtonPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	/**
	 * The edited node
	 */
	private APropertyNode elementFont;

	/**
	 * Crate a new descriptor for increment or decrement of the font size
	 * 
	 * @param id
	 *          id of this attribute
	 * @param fontModel
	 *          model that contain the font attribute to increment\decrement
	 */
	public FontSizeButtonPropertyDescriptor(Object id, APropertyNode fontModel) {
		super(id, "");
		elementFont = fontModel;
		//This filter avoid to show this element on the advanced properties views
		setFilterFlags(new String[]{IPropertySheetEntry.FILTER_ID_EXPERT});
	}

	/**
	 * Create the widget and return it
	 */
	@Override
	public ASPropertyWidget<FontSizeButtonPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPFontSize<FontSizeButtonPropertyDescriptor>(parent, section, this, elementFont);
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
}
