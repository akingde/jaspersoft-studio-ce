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
package com.jaspersoft.studio.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.jrQuery.JRQueryPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.resource.NResourcePropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MDataset.
 * 
 * @author Chicu Veaceslav
 */
public class MDataset extends APropertyNode implements IPropertySource, ICopyable {

	private static final String PROPERTY_MAP = "PROPERTY_MAP";
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("dataset");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m dataset.
	 */
	public MDataset() {
		super();
	}

	public MDataset(JRDesignDataset jrDataset) {
		super();
		setValue(jrDataset);
	}

	/**
	 * Instantiates a new m dataset.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrDataset
	 *          the jr dataset
	 * @param newIndex
	 *          the new index
	 */
	public MDataset(ANode parent, JRDesignDataset jrDataset, int newIndex) {
		super(parent, newIndex);
		setValue(jrDataset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return ((JRDesignDataset) getValue()).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
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
		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignDataset.PROPERTY_NAME, "Name");
		nameD.setDescription("The name of the parameter that will be passed when iterating the subdataset.");
		desc.add(nameD);

		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP, "Properties");
		propertiesD.setDescription("Dataset properties");
		desc.add(propertiesD);

		ClassTypePropertyDescriptor classD = new ClassTypePropertyDescriptor(JRDesignDataset.PROPERTY_SCRIPTLET_CLASS,
				"Class");
		classD
				.setDescription("Indicates which class implements the scriptlets functionality for this report. The specified class must be a subclass of JRAbstractScriptlet class. If omitted, an instance of JRDefaultScriptlet will be created.");
		desc.add(classD);

		NResourcePropertyDescriptor resBundleD = new NResourcePropertyDescriptor(JRDesignDataset.PROPERTY_RESOURCE_BUNDLE,
				"Resource Bundle");
		resBundleD.setDescription("The base name of the dataset associated resource bundle.");
		desc.add(resBundleD);

		JRQueryPropertyDescriptor queryD = new JRQueryPropertyDescriptor(JRDesignDataset.PROPERTY_QUERY, "Query",
				NullEnum.NULL);
		queryD.setDescription("Contains the query that will be used to retrieve the data needed to fill the report.");
		desc.add(queryD);

		ComboBoxPropertyDescriptor whenResMissTypeD = new ComboBoxPropertyDescriptor(
				JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE, "When Resource Missing Type", EnumHelper.getEnumNames(
						WhenResourceMissingTypeEnum.values(), NullEnum.NOTNULL));
		whenResMissTypeD
				.setDescription("Allows customizing the way the engine deals with missing resources in the resource bundle.");
		desc.add(whenResMissTypeD);

		JRExpressionPropertyDescriptor filterExpression = new JRExpressionPropertyDescriptor(
				JRDesignDataset.PROPERTY_FILTER_EXPRESSION, "Filter Expression");
		filterExpression.setDescription("Boolean expression used to filter the rows of a data source. ");
		desc.add(filterExpression);

		defaultsMap.put(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE,
				EnumHelper.getValue(WhenResourceMissingTypeEnum.NULL, 1, false));
		defaultsMap.put(JRDesignDataset.PROPERTY_FILTER_EXPRESSION, null);
	}

	private MQuery mQuery;
	private MExpression mExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignDataset jrDataset = (JRDesignDataset) getValue();
		if (id.equals(JRDesignDataset.PROPERTY_NAME))
			return jrDataset.getName();

		if (id.equals(JRDesignDataset.PROPERTY_QUERY)) {
			if (mQuery == null)
				mQuery = new MQuery(jrDataset.getQuery());
			return mQuery;
		}
		if (id.equals(JRDesignDataset.PROPERTY_FILTER_EXPRESSION)) {
			if (mExpression == null)
				mExpression = new MExpression(jrDataset.getFilterExpression());
			return mExpression;
		}
		if (id.equals(JRDesignDataset.PROPERTY_SCRIPTLET_CLASS))
			return jrDataset.getScriptletClass();
		if (id.equals(PROPERTY_MAP))
			return jrDataset.getPropertiesMap();
		if (id.equals(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE))
			return EnumHelper.getValue(jrDataset.getWhenResourceMissingTypeValue(), 1, false);
		if (id.equals(JRDesignDataset.PROPERTY_RESOURCE_BUNDLE))
			return jrDataset.getResourceBundle();

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignDataset jrDataset = (JRDesignDataset) getValue();
		if (id.equals(JRDesignDataset.PROPERTY_NAME))
			jrDataset.setName((String) value);
		else if (id.equals(JRDesignDataset.PROPERTY_RESOURCE_BUNDLE))
			jrDataset.setResourceBundle((String) value);
		else if (id.equals(JRDesignDataset.PROPERTY_SCRIPTLET_CLASS))
			jrDataset.setScriptletClass((String) value);
		else if (id.equals(JRDesignDataset.PROPERTY_FILTER_EXPRESSION)) {
			if (value == null) {
				mExpression = null;
				jrDataset.setFilterExpression(null);
			}
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrDataset.setFilterExpression(expression);
			}
		} else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrDataset.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrDataset.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrDataset.setProperty(names[i], v.getProperty(names[i]));
		} else if (id.equals(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE))
			jrDataset.setWhenResourceMissingType((WhenResourceMissingTypeEnum) EnumHelper.getSetValue(
					WhenResourceMissingTypeEnum.values(), value, 1, false));
		else if (id.equals(JRDesignDataset.PROPERTY_QUERY)) {
			if (value instanceof MQuery) {
				mQuery = (MQuery) value;
				JRDesignQuery jrQuery = (JRDesignQuery) mQuery.getValue();
				jrDataset.setQuery(jrQuery);

			}
		}
	}

	@Override
	public String toString() {
		return getDisplayText();
	}

	/**
	 * Creates the jr dataset.
	 * 
	 * @param jrDesign
	 *          the jr design
	 * @return the jR design dataset
	 */
	public static JRDesignDataset createJRDataset(JasperDesign jrDesign) {
		JRDesignDataset jrDesignDataset = new JRDesignDataset(false);
		jrDesignDataset.setName(ModelUtils.getDefaultName(jrDesign.getDatasetMap(), "Dataset"));
		return jrDesignDataset;
	}

	@Override
	public boolean isCopyable2(Object parent) {
		if (parent instanceof MReport)
			return true;
		return false;
	}

}
