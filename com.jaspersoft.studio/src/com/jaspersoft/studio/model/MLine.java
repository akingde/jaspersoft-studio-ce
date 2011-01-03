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

import net.sf.jasperreports.engine.base.JRBaseLine;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.LineDirectionEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MLine.
 */
public class MLine extends MGraphicElementLinePen {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("line"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m line.
	 */
	public MLine() {
		super();
	}

	/**
	 * Instantiates a new m line.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrLine
	 *          the jr line
	 * @param newImage
	 *          the new image
	 */
	public MLine(ANode parent, JRDesignLine jrLine, int newImage) {
		super(parent, newImage);
		setValue(jrLine);
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor directionD = new ComboBoxPropertyDescriptor(JRBaseLine.PROPERTY_DIRECTION, Messages.MLine_direction,
				EnumHelper.getEnumNames(LineDirectionEnum.values(), NullEnum.NULL));
		directionD
				.setDescription(Messages.MLine_direction_description);
		directionD.setCategory(Messages.MLine_line_category);
		desc.add(directionD);

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FILL, Messages.common_fill, EnumHelper
				.getEnumNames(FillEnum.values(), NullEnum.INHERITED));
		fillD.setDescription(Messages.MLine_fill_description);
		desc.add(fillD);

		defaultsMap.put(JRBaseLine.PROPERTY_DIRECTION, EnumHelper.getValue(LineDirectionEnum.TOP_DOWN, 1, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, null);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignLine jrElement = (JRDesignLine) getValue();
		if (id.equals(JRBaseLine.PROPERTY_DIRECTION))
			return EnumHelper.getValue(jrElement.getDirectionValue(), 1, true);
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return EnumHelper.getValue(jrElement.getOwnFillValue(), 1, true);
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignLine jrElement = (JRDesignLine) getValue();
		if (id.equals(JRBaseLine.PROPERTY_DIRECTION))
			jrElement.setDirection((LineDirectionEnum) EnumHelper.getSetValue(LineDirectionEnum.values(), value, 1, true));
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrElement.setFill((FillEnum) EnumHelper.getSetValue(FillEnum.values(), value, 1, true));
		else
			super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 30;
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
		JRDesignLine jrDesignLine = new JRDesignLine(jasperDesign);
		jrDesignLine.setWidth(getDefaultWidth());
		jrDesignLine.setHeight(getDefaultHeight());
		return jrDesignLine;
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
