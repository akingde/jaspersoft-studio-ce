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

import net.sf.jasperreports.engine.base.JRBaseBreak;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BreakTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MBreak.
 */
public class MBreak extends MGraphicElement {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("break"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m break.
	 */
	public MBreak() {
		super();
	}

	/**
	 * Instantiates a new m break.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrBreak
	 *          the jr break
	 * @param newIndex
	 *          the new index
	 */
	public MBreak(ANode parent, JRDesignBreak jrBreak, int newIndex) {
		super(parent, newIndex);
		setValue(jrBreak);
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

		ComboBoxPropertyDescriptor hAlignD = new ComboBoxPropertyDescriptor(JRBaseBreak.PROPERTY_TYPE, Messages.MBreak_type, EnumHelper
				.getEnumNames(BreakTypeEnum.values(), NullEnum.NULL));
		hAlignD.setDescription(Messages.MBreak_type_description);
		desc.add(hAlignD);
		hAlignD.setCategory(Messages.MBreak_break_category);

		defaultsMap.put(JRBaseBreak.PROPERTY_TYPE, EnumHelper.getValue(BreakTypeEnum.PAGE, 1, true));
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignBreak jrElement = (JRDesignBreak) getValue();
		if (id.equals(JRBaseBreak.PROPERTY_TYPE))
			return EnumHelper.getValue(jrElement.getTypeValue(), 1, true);
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignBreak jrElement = (JRDesignBreak) getValue();

		if (id.equals(JRBaseBreak.PROPERTY_TYPE))
			jrElement.setType((BreakTypeEnum) EnumHelper.getSetValue(BreakTypeEnum.values(), value, 1, true));
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
		return 3;
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
		return new JRDesignBreak();
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
