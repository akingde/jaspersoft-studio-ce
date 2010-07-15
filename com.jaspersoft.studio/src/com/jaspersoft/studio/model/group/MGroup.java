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
package com.jaspersoft.studio.model.group;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MGroup.
 * 
 * @author Chicu Veaceslav
 */
public class MGroup extends APropertyNode implements IPropertySource {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("group");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m group.
	 */
	public MGroup() {
		super();
	}

	/**
	 * Instantiates a new m group.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MGroup(ANode parent, JRDesignGroup jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return ((JRDesignGroup) getValue()).getName();
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
		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignGroup.PROPERTY_NAME, "Name");
		nameD.setDescription("Name of the group.");
		desc.add(nameD);

		JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(JRDesignGroup.PROPERTY_EXPRESSION,
				"Expression");
		expressionD
				.setDescription("Specifies the expression that will be evaluated at runtime to determine the group ruptures.");
		desc.add(expressionD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignGroup jrGroup = (JRDesignGroup) getValue();
		if (id.equals(JRDesignGroup.PROPERTY_NAME))
			return jrGroup.getName();
		if (id.equals(JRDesignGroup.PROPERTY_EXPRESSION))
			return jrGroup.getExpression();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignGroup jrGroup = (JRDesignGroup) getValue();
		if (id.equals(JRDesignGroup.PROPERTY_NAME))
			jrGroup.setName((String) value);
		// else if (id.equals(JRDesignGroup.PROPERTY_EXPRESSION))
		// jrGroup.setExpression((String) value);
	}

	/**
	 * Creates the jr group.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design group
	 */
	public static JRDesignGroup createJRGroup(JRDesignDataset jrDataset) {
		JRDesignGroup jrDesignGroup = new JRDesignGroup();
		jrDesignGroup.setName(ModelUtils.getDefaultName(jrDataset.getGroupsMap(), "Group"));
		return jrDesignGroup;
	}

}
