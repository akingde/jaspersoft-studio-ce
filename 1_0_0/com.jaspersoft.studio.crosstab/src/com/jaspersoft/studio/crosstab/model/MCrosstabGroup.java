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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public abstract class MCrosstabGroup extends APropertyNode implements IPropertySource {

	/**
	 * Instantiates a new m field.
	 */
	public MCrosstabGroup() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MCrosstabGroup(ANode parent, JRCrosstabGroup jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRCrosstabGroup) getValue()).getName();
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
		ComboBoxPropertyDescriptor totalPositionD = new ComboBoxPropertyDescriptor(
				JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION, Messages.common_total_position, EnumHelper.getEnumNames(
						CrosstabTotalPositionEnum.values(), NullEnum.NOTNULL));
		totalPositionD.setDescription(Messages.MCrosstabGroup_total_position_description);
		desc.add(totalPositionD);

		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(JRDesignCrosstabGroup.PROPERTY_NAME,
				Messages.common_name);
		nameD.setDescription(Messages.MCrosstabGroup_name_description);
		desc.add(nameD);

		JRPropertyDescriptor bucketD = new JRPropertyDescriptor(JRDesignCrosstabGroup.PROPERTY_BUCKET,
				Messages.common_bucket);
		bucketD.setDescription(Messages.MCrosstabGroup_bucket_description);
		desc.add(bucketD);

	}

	private MBucket mBucket;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabGroup jrField = (JRDesignCrosstabGroup) getValue();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION))
			return EnumHelper.getValue(jrField.getTotalPositionValue(), 0, false);
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_BUCKET)) {
			if (mBucket == null) {
				mBucket = new MBucket(jrField.getBucket());
				setChildListener(mBucket);
			}
			return mBucket;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabGroup jrField = (JRDesignCrosstabGroup) getValue();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_NAME))
			jrField.setName((String) value);
		else if (id.equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION)) {
			jrField.setTotalPosition((CrosstabTotalPositionEnum) EnumHelper.getSetValue(CrosstabTotalPositionEnum.values(),
					value, 0, false));
			MCrosstab cross = getMCrosstab();
			cross.getCrosstabManager().refresh();
			getPropertyChangeSupport().firePropertyChange(
					new PropertyChangeEvent(this, JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION, null, value));
		}
	}

	public MCrosstab getMCrosstab() {
		INode node = getParent();
		while (node != null) {
			if (node instanceof MCrosstab) {
				return (MCrosstab) node;
			}
			node = node.getParent();
		}
		return null;
	}

}
