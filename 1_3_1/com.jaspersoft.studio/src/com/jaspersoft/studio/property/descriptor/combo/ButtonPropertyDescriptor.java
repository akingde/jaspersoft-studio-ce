package com.jaspersoft.studio.property.descriptor.combo;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPButon;

public class ButtonPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget {

	private boolean increment;
	
	private APropertyNode elementFont;

	/**
	 * Crate a new descriptor for increment or decrement of the font size
	 * @param id id of this attribute
	 * @param fontModel model that contain the font attribute to increment\decrement
	 * @param increment True if it is a decrement descriptor, false otherwise
	 */
	public ButtonPropertyDescriptor(Object id, APropertyNode fontModel, boolean increment) {
		super(id, "");
		elementFont = fontModel;
		this.increment = increment;
	}
	
	@Override
	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		if (increment) return new SPButon(parent, section, this, increment, elementFont);
		else return new SPButon(parent, section, this, increment, elementFont);
	}

}
