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
package com.jaspersoft.studio.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.jrQuery.JRQueryPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.resource.NResourcePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The Class MDataset.
 * 
 * @author Chicu Veaceslav
 */
public class MDataset extends APropertyNode implements ICopyable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String PROPERTY_MAP = "PROPERTY_MAP"; //$NON-NLS-1$
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("dataset"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m dataset.
	 */
	public MDataset() {
		super();
	}

	private MReport mreport;

	public MDataset(MReport mreport, JRDesignDataset jrDataset) {
		super();
		this.mreport = mreport;
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
		INode root = getRoot();
		if (root != null && root instanceof MReport)
			mreport = (MReport) root;
	}

	public MReport getMreport() {
		return mreport;
	}

	@Override
	public JRDesignDataset getValue() {
		return (JRDesignDataset) super.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRDesignDataset) getValue()).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
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
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JSSTextPropertyDescriptor nameD = new JSSTextPropertyDescriptor(JRDesignDataset.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MDataset_name_description);
		desc.add(nameD);

		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP,
				Messages.common_properties);
		propertiesD.setDescription(Messages.MDataset_properties_description);
		desc.add(propertiesD);

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(JRDesignDataset.PROPERTY_SCRIPTLET_CLASS,
				Messages.common_class);
		classD.setDescription(Messages.MDataset_class_description);
		desc.add(classD);

		NResourcePropertyDescriptor resBundleD = new NResourcePropertyDescriptor(JRDesignDataset.PROPERTY_RESOURCE_BUNDLE,
				Messages.MDataset_resource_bundle);
		resBundleD.setDescription(Messages.MDataset_resource_bundle_description);
		desc.add(resBundleD);

		JRQueryPropertyDescriptor queryD = new JRQueryPropertyDescriptor(JRDesignDataset.PROPERTY_QUERY,
				Messages.common_query, NullEnum.NULL);
		queryD.setDescription(Messages.MDataset_query_description);
		desc.add(queryD);

		whenResMissTypeD = new JSSEnumPropertyDescriptor(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE,
				Messages.MDataset_when_resource_missing_type, WhenResourceMissingTypeEnum.class, NullEnum.NOTNULL);
		whenResMissTypeD.setDescription(Messages.MDataset_when_resource_missing_type_description);
		desc.add(whenResMissTypeD);

		JRExpressionPropertyDescriptor filterExpression = new JRExpressionPropertyDescriptor(
				JRDesignDataset.PROPERTY_FILTER_EXPRESSION, Messages.MDataset_filter_expression);
		filterExpression.setDescription(Messages.MDataset_filter_expression_description);
		desc.add(filterExpression);

		defaultsMap.put(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE,
				EnumHelper.getValue(WhenResourceMissingTypeEnum.NULL, 1, false));
		defaultsMap.put(JRDesignDataset.PROPERTY_FILTER_EXPRESSION, null);
	}

	private MQuery mQuery;
	private static JSSEnumPropertyDescriptor whenResMissTypeD;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignDataset jrDataset = (JRDesignDataset) getValue();
		if (id.equals(JRDesignDataset.PROPERTY_NAME))
			return jrDataset.getName();

		if (id.equals(JRDesignDataset.PROPERTY_QUERY)) {
			if (mQuery == null) {
				JRQuery jdq = jrDataset.getQuery();
				mQuery = new MQuery(jdq, this);
				mQuery.setJasperConfiguration(getJasperConfiguration());
				setChildListener(mQuery);
			}
			return mQuery;
		}
		if (id.equals(JRDesignDataset.PROPERTY_FILTER_EXPRESSION)) {
			return ExprUtil.getExpression(jrDataset.getFilterExpression());
		}
		if (id.equals(JRDesignDataset.PROPERTY_SCRIPTLET_CLASS))
			return jrDataset.getScriptletClass();
		if (id.equals(PROPERTY_MAP)) {
			// to avoid duplication I remove it first
			JRPropertiesMap pmap = jrDataset.getPropertiesMap();
			return pmap;
		}
		if (id.equals(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE))
			return whenResMissTypeD.getEnumValue(jrDataset.getWhenResourceMissingTypeValue());
		if (id.equals(JRDesignDataset.PROPERTY_RESOURCE_BUNDLE))
			return jrDataset.getResourceBundle();

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignDataset jrDataset = (JRDesignDataset) getValue();
		if (id.equals(JRDesignDataset.PROPERTY_NAME))
			jrDataset.setName((String) value);
		else if (id.equals(JRDesignDataset.PROPERTY_RESOURCE_BUNDLE))
			jrDataset.setResourceBundle((String) value);
		else if (id.equals(JRDesignDataset.PROPERTY_SCRIPTLET_CLASS))
			jrDataset.setScriptletClass((String) value);
		else if (id.equals(JRDesignDataset.PROPERTY_FILTER_EXPRESSION))
			jrDataset.setFilterExpression(ExprUtil.setValues(jrDataset.getFilterExpression(), value));
		else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrDataset.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrDataset.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrDataset.setProperty(names[i], v.getProperty(names[i]));
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, false, true);
		} else if (id.equals(JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE))
			jrDataset.setWhenResourceMissingType((WhenResourceMissingTypeEnum) whenResMissTypeD.getEnumValue(value));
		else if (id.equals(JRDesignDataset.PROPERTY_QUERY)) {
			if (value instanceof MQuery) {
				unsetChildListener(mQuery);
				mQuery = (MQuery) value;
				setChildListener(mQuery);
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
		jrDesignDataset.setName(ModelUtils.getDefaultName(jrDesign.getDatasetMap(), "Dataset")); //$NON-NLS-1$
		JRDesignQuery jrDesignQuery = new JRDesignQuery();
		jrDesignQuery.setLanguage("sql"); //$NON-NLS-1$
		jrDesignQuery.setText("");
		jrDesignDataset.setQuery(jrDesignQuery);
		return jrDesignDataset;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MReport)
			return true;
		return false;
	}

}
