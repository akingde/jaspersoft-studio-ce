/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.rowgroup;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.MCrosstabGroup;
import com.jaspersoft.studio.components.crosstab.model.cell.MGroupCell;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRCrosstabOrigin;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabRowPositionEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.util.Pair;

public class MRowGroup extends MCrosstabGroup implements ICopyable, IDragable {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;
	
	private static NamedEnumPropertyDescriptor<CrosstabRowPositionEnum> columnPositionD;
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("rowgroup"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MRowGroup() {
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
	public MRowGroup(ANode parent, JRCrosstabRowGroup jfRield, int newIndex) {
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
		super.createPropertyDescriptors(desc);

		columnPositionD = new NamedEnumPropertyDescriptor<CrosstabRowPositionEnum>(
				JRDesignCrosstabRowGroup.PROPERTY_POSITION,
				Messages.MRowGroup_row_position,
				CrosstabRowPositionEnum.BOTTOM, NullEnum.NOTNULL);
		columnPositionD
				.setDescription(Messages.MRowGroup_row_position_description);
		desc.add(columnPositionD);

		PixelPropertyDescriptor widthD = new PixelPropertyDescriptor(
				JRDesignCrosstabRowGroup.PROPERTY_WIDTH, Messages.common_width);
		widthD.setDescription(Messages.MRowGroup_width_description);
		desc.add(widthD);

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
		JRDesignCrosstabRowGroup jrField = (JRDesignCrosstabRowGroup) getValue();
		if (id.equals(JRDesignCrosstabRowGroup.PROPERTY_POSITION))
			return columnPositionD.getIntValue(jrField.getPositionValue());
		if (id.equals(JRDesignCrosstabRowGroup.PROPERTY_WIDTH))
			return jrField.getWidth();
		return super.getPropertyValue(id);
	}

	/**
	 * Called when the name of a group change Search in the crosstab the group
	 * cells that are using a reference to the group and update also their
	 * references. It update the group map in the JRCrosstabElement to keep it
	 * in sync with the current group name. Finally since the cell uses the
	 * group name for the model display name it run a fake event to update the
	 * cells in the editor
	 */
	@Override
	protected void updateGroups(String oldName, String newName) {
		MCrosstab crosstab = getMCrosstab();
		List<MGroupCell> cellsToRefresh = new ArrayList<MGroupCell>();
		for (INode child : crosstab.getChildren()) {
			if (child instanceof MGroupCell) {
				MGroupCell cell = (MGroupCell) child;
				String rowGroup = cell.getCell().getRowTotalGroup();
				if (rowGroup != null && rowGroup.equals(oldName)) {
					cell.getCell().setRowTotalGroup(newName);
					cellsToRefresh.add(cell);
				}
			}
		}
		
		//Update the cell origins
		List<JRDesignCellContents> contents = ModelUtils.getAllCells(crosstab.getValue());
	    for (JRDesignCellContents content : contents)
	    {
	    	if (content != null){
		    	JRCrosstabOrigin origin = content.getOrigin();
		    	if (ModelUtils.safeEquals(origin.getRowGroupName(), oldName)){
		    		JRCrosstabOrigin newOrigin = new JRCrosstabOrigin(crosstab.getValue(), origin.getType(), newName, origin.getColumnGroupName());
		    		content.setOrigin(newOrigin);
		    	}
	    	}
	    }
		
	    //Update the cells map
	    Map<Pair<String, String>, JRCrosstabCell> cellsMap = crosstab.getValue().getCellsMap();
	    //need to create a new array list to avoid the concurrent modification exception
	    for(Pair<String, String> key : new ArrayList<Pair<String,String>>(cellsMap.keySet())){
	    	//The pair are row name and column name
	    	if (ModelUtils.safeEquals(oldName, key.first())){
	    		JRCrosstabCell value = cellsMap.remove(key);
	    		cellsMap.put(new Pair<String, String>(newName, key.second()), value);
	    	}
	    }
		
		//Update the indexes map
		JRDesignCrosstab jrCrosstab = (JRDesignCrosstab) crosstab.getValue();
		Map<String, Integer> groupMap = jrCrosstab.getRowGroupIndicesMap();
		if (groupMap.containsKey(oldName)) {
			Integer value = groupMap.remove(oldName);
			groupMap.put(newName, value);
		}
		// The refresh must be done after the update of the map
		for (MGroupCell cell : cellsToRefresh) {
			JSSCompoundCommand.forceRefreshVisuals(cell);
		}
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
		JRDesignCrosstabRowGroup jrField = (JRDesignCrosstabRowGroup) getValue();
		if (id.equals(JRDesignCrosstabRowGroup.PROPERTY_POSITION))
			jrField.setPosition(columnPositionD.getEnumValue(value));
		else if (id.equals(JRDesignCrosstabRowGroup.PROPERTY_WIDTH)) {
			jrField.setWidth((Integer) value);
			MCrosstab cross = getMCrosstab();
			cross.getCrosstabManager().refresh();
			getPropertyChangeSupport().firePropertyChange(
					new PropertyChangeEvent(this,
							JRDesignCrosstabRowGroup.PROPERTY_WIDTH, null,
							value));
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION)) {
			MCrosstab crosstabModel = getMCrosstab();
			if (crosstabModel != null){
				JRDesignCrosstab jrCrosstab = crosstabModel.getValue();
				jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, getValue());
			}
		}
		super.propertyChange(evt);
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MRowGroups)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}
	
	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
