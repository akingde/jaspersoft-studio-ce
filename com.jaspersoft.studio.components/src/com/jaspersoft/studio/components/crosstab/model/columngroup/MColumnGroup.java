/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.crosstab.model.columngroup;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabColumnPositionEnum;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.crosstab.CrosstabComponentFactory;
import com.jaspersoft.studio.components.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.MCrosstabGroup;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;

public class MColumnGroup extends MCrosstabGroup implements ICopyable {
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
			iconDescriptor = new CrosstabNodeIconDescriptor("columngroup"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MColumnGroup() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *            the parent
	 * @param jfRield
	 *            the jf rield
	 * @param newIndex
	 *            the new index
	 */
	public MColumnGroup(ANode parent, JRCrosstabColumnGroup jfRield,
			int newIndex) {
		super(parent, jfRield, newIndex);
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
	private static JSSEnumPropertyDescriptor columnPositionD;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		columnPositionD = new JSSEnumPropertyDescriptor(
				JRDesignCrosstabColumnGroup.PROPERTY_POSITION,
				Messages.MColumnGroup_column_position,
				CrosstabColumnPositionEnum.class, NullEnum.NOTNULL);
		columnPositionD
				.setDescription(Messages.MColumnGroup_column_position_description);
		desc.add(columnPositionD);

		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(
				JRDesignCrosstabColumnGroup.PROPERTY_HEIGHT,
				Messages.common_height);
		heightD.setDescription(Messages.MColumnGroup_height_description);
		desc.add(heightD);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabColumnGroup jrField = (JRDesignCrosstabColumnGroup) getValue();
		if (id.equals(JRDesignCrosstabColumnGroup.PROPERTY_POSITION))
			return columnPositionD.getEnumValue(jrField.getPositionValue());
		if (id.equals(JRDesignCrosstabColumnGroup.PROPERTY_HEIGHT))
			return jrField.getHeight();
		return super.getPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabColumnGroup jrField = (JRDesignCrosstabColumnGroup) getValue();
		if (id.equals(JRDesignCrosstabColumnGroup.PROPERTY_POSITION))
			jrField.setPosition((CrosstabColumnPositionEnum) columnPositionD
					.getEnumValue(value));
		else if (id.equals(JRDesignCrosstabColumnGroup.PROPERTY_HEIGHT)) {
			jrField.setHeight((Integer) value);
			MCrosstab cross = getMCrosstab();
			cross.getCrosstabManager().refresh();
			getPropertyChangeSupport().firePropertyChange(
					new PropertyChangeEvent(this,
							JRDesignCrosstabColumnGroup.PROPERTY_HEIGHT, null,
							value));
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(
				JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION)) {
			this.removeChildren();
			CrosstabComponentFactory.createColumnGroupCells(this,
					(JRCrosstabColumnGroup) getValue());
			MCrosstab mCrosstab = getMCrosstab();
			CrosstabComponentFactory.deleteCellNodes(mCrosstab);
			CrosstabComponentFactory.createCellNodes(
					(JRDesignCrosstab) mCrosstab.getValue(), mCrosstab);
		}
		super.propertyChange(evt);
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MColumnGroups)
			return true;
		return false;
	}

}
