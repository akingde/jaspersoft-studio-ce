/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;

/*
 * The Class MGeneric.
 */
public abstract class MGraphicElementLineBox extends MGraphicElement implements IGraphicElement, ILineBox {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String LINE_BOX = "LineBox"; //$NON-NLS-1$

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
	public HashMap<String,Object> getStylesDescriptors() {
		HashMap<String, Object> result = super.getStylesDescriptors();
		if (getValue() == null)
			return result;
		MLineBox element = (MLineBox) getPropertyValue(LINE_BOX);
		result.putAll(element.getStylesDescriptors());
		return result;
	}
	

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, Messages.common_line_box);
		lineBoxD.setDescription(Messages.MGraphicElementLineBox_line_box_description);
		desc.add(lineBoxD);
		lineBoxD.setCategory(Messages.common_graphic);
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
			if (lineBox == null) {
				lineBox = new MLineBox(jrGraphicElement.getLineBox());
				setChildListener(lineBox);
			}
			return lineBox;
		}
		return super.getPropertyValue(id);
	}

	public JRBoxContainer getBoxContainer() {
		return (JRBoxContainer) getValue();
	}
}
