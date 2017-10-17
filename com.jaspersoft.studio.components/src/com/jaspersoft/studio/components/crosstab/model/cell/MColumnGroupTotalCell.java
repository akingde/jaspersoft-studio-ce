/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.eclipse.util.StringUtils;

/**
 * Node for the cell of the column group total cell of a crosstab. This subclass
 * only implement the methods to set and update the node name
 * 
 * @author Orlandin Marco
 *
 */
public class MColumnGroupTotalCell extends MCell {

	private static final long serialVersionUID = -282946431078592480L;

	public MColumnGroupTotalCell(MColumnGroup parent, JRCellContents jfRield, String groupName) {
		super(parent, jfRield, Messages.common_total + StringUtils.SPACE + groupName);
	}
	
	/**
	 * Update the name with the current group name.
	 */
	@Override
	public void updateName() {
		ANode parent = getParent();
		if (parent != null && parent.getValue() instanceof JRCrosstabColumnGroup){
			JRCrosstabColumnGroup columnGroup = (JRCrosstabColumnGroup)parent.getValue();
			setName(Messages.common_total + StringUtils.SPACE + columnGroup.getName());
		}
		//Trigger the node refresh
		super.updateName();
	}
}
