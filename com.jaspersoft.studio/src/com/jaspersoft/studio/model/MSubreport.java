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

import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;

/**
 * The Class MSubreport.
 */
public class MSubreport extends MGraphicElement {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("subreport");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m subreport.
	 */
	public MSubreport() {
		super();
	}

	/**
	 * Instantiates a new m subreport.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrSubreport
	 *          the jr subreport
	 * @param newIndex
	 *          the new index
	 */
	public MSubreport(ANode parent, JRDesignSubreport jrSubreport, int newIndex) {
		super(parent, newIndex);
		setValue(jrSubreport);
		if (jrSubreport != null)
			((JRDesignSubreport) jrSubreport).getEventSupport().addPropertyChangeListener(this);
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

		CheckBoxPropertyDescriptor runToBottomD = new CheckBoxPropertyDescriptor(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM,
				"Run To Bottom", NullEnum.NULL);
		runToBottomD
				.setDescription("Flag to specify if the subreport should consume all the available space on the current page. When this flag is set, the subreport would consume the entire vertical space available on the master page, and its column and page footers will be printed at the bottom of this space.");
		desc.add(runToBottomD);

		CheckBoxPropertyDescriptor useCacheD = new CheckBoxPropertyDescriptor(JRBaseSubreport.PROPERTY_USING_CACHE,
				"Using Cache", NullEnum.INHERITED);
		useCacheD
				.setDescription("If true, tells the report engine to cache the report definition objects that are loaded from the same location.");
		desc.add(useCacheD);

		runToBottomD.setCategory("Subreport Properties");

	}

	@Override
	public Object getPropertyValue(Object id) {
		JRBaseSubreport jrElement = (JRBaseSubreport) getValue();
		if (id.equals(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM))
			return jrElement.isRunToBottom();
		if (id.equals(JRBaseSubreport.PROPERTY_USING_CACHE))
			return jrElement.isOwnUsingCache();
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRBaseSubreport jrElement = (JRBaseSubreport) getValue();
		if (id.equals(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM))
			jrElement.setRunToBottom((Boolean) value);
		else if (id.equals(JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM))
			jrElement.setUsingCache((Boolean) value);
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
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return new JRDesignSubreport(jasperDesign);
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
