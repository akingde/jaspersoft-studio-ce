/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.crosstabs.JRCrosstabGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRConstants;

public abstract class MCrosstabGroup extends MDatasetGroupNode implements IPropertySource {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;

	private static NamedEnumPropertyDescriptor<CrosstabTotalPositionEnum> totalPositionD;
	
	private MBucket mBucket;
	
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
	 *            the parent
	 * @param jfRield
	 *            the jf rield
	 * @param newIndex
	 *            the new index
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

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		totalPositionD = new NamedEnumPropertyDescriptor<CrosstabTotalPositionEnum>(
				JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION, Messages.common_total_position,
				CrosstabTotalPositionEnum.NONE, NullEnum.NOTNULL);
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

		CheckBoxPropertyDescriptor merge = new CheckBoxPropertyDescriptor(
				JRDesignCrosstabGroup.PROPERTY_MERGE_HEADER_CELLS, Messages.MCrosstabGroup_0);
		merge.setDescription(Messages.MCrosstabGroup_1);
		desc.add(merge);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabGroup jrField = (JRDesignCrosstabGroup) getValue();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION))
			return totalPositionD.getIntValue(jrField.getTotalPositionValue());
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_BUCKET)) {
			if (mBucket == null) {
				mBucket = new MBucket(jrField.getBucket(), this);
				setChildListener(mBucket);
			}
			mBucket.setValue(jrField.getBucket());
			return mBucket;
		}
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_MERGE_HEADER_CELLS)){
			boolean result = jrField.getMergeHeaderCells() != null ? jrField.getMergeHeaderCells() : false;
			return new Boolean(result);
		}
		return null;
	}

	/**
	 * Called when the group name changes, it must search the cells using that
	 * group and update their reference as well
	 * 
	 * @param oldName
	 *            the old name of the group
	 * @param newName
	 *            the new name of the group
	 */
	protected abstract void updateGroups(String oldName, String newName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabGroup jrField = (JRDesignCrosstabGroup) getValue();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_NAME)) {
			String oldName = jrField.getName();
			jrField.setName((String) value);
			// Request the update of the name of the cells associated with this
			// group
			updateGroups(oldName, jrField.getName());
		} else if (id.equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION)) {
			jrField.setTotalPosition(totalPositionD.getEnumValue(value));
			MCrosstab cross = getMCrosstab();
			if (cross != null){
				cross.getCrosstabManager().refresh();
				getPropertyChangeSupport().firePropertyChange(new PropertyChangeEvent(this, JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION, null, value));
			}
		} else if (id.equals(JRDesignCrosstabGroup.PROPERTY_MERGE_HEADER_CELLS))
			jrField.setMergeHeaderCells(((Boolean) value).booleanValue());
	}
	
	@Override
	public HashMap<String, List<ANode>> getUsedStyles() {
		HashMap<String, List<ANode>> result = super.getUsedStyles();
		for (INode node : getChildren()) {
			if (node instanceof ANode) {
				mergeElementStyle(result, ((ANode) node).getUsedStyles());
			}
		}
		return result;
	}
}
