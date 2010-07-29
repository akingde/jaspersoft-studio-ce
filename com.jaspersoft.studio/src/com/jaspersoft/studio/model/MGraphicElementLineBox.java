/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;

/**
 * The Class MGeneric.
 */
public abstract class MGraphicElementLineBox extends MGraphicElement implements IGraphicElement {

	public static final String LINE_BOX = "LineBox";

	public MGraphicElementLineBox() {
		super();
	}

	public MGraphicElementLineBox(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MGraphicElementLineBox(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, jrLine, newIndex);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, "Line Box");
		lineBoxD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(lineBoxD);
		lineBoxD.setCategory("Graphic");
	}

	private MLineBox lineBox;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		// pen
		if (id.equals(LINE_BOX)) {
			JRBoxContainer jrGraphicElement = (JRBoxContainer) getValue();
			if (lineBox == null){
				lineBox = new MLineBox(jrGraphicElement.getLineBox());
				lineBox.getPropertyChangeSupport().addPropertyChangeListener(this);
			}
			return lineBox;
		}
		return super.getPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(LINE_BOX)) {
			;
		}
		super.setPropertyValue(id, value);
	}
}
