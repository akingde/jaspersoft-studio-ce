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

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.FillEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

/*
 * The Class MRectangle.
 */
public class MRectangle extends MGraphicElementLinePen {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("rectangle"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m rectangle.
	 */
	public MRectangle() {
		super();
	}

	/**
	 * Instantiates a new m rectangle.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrRectangle
	 *          the jr rectangle
	 * @param newImage
	 *          the new image
	 */
	public MRectangle(ANode parent, JRDesignRectangle jrRectangle, int newImage) {
		super(parent, newImage);
		setValue(jrRectangle);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FILL, Messages.common_fill,
				EnumHelper.getEnumNames(FillEnum.values(), NullEnum.INHERITED));
		fillD.setDescription(Messages.MRectangle_fill_description);
		desc.add(fillD);

		IntegerPropertyDescriptor rD = new IntegerPropertyDescriptor(JRBaseStyle.PROPERTY_RADIUS, Messages.common_radius);
		rD.setCategory(Messages.MRectangle_rectangle_properties_category);
		rD.setDescription(Messages.MRectangle_radius_description);
		desc.add(rD);

		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, null);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignRectangle jrElement = (JRDesignRectangle) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_RADIUS))
			return jrElement.getOwnRadius();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return EnumHelper.getValue(jrElement.getFillValue(), 1, true);
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignRectangle jrElement = (JRDesignRectangle) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrElement.setFill((FillEnum) EnumHelper.getSetValue(FillEnum.values(), value, 1, true));
		else if (id.equals(JRBaseStyle.PROPERTY_RADIUS)) {
			jrElement.setRadius(((Integer) value).intValue());
		} else
			super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignRectangle jrDesignRectangle = new JRDesignRectangle(jasperDesign);
		jrDesignRectangle.setWidth(getDefaultWidth());
		jrDesignRectangle.setHeight(getDefaultHeight());
		return jrDesignRectangle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
