/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.eclipse.util.StringUtils;

/**
 * Node for the cell of the row group total cell of a crosstab. This subclass
 * only implement the methods to set and update the node name
 * 
 * @author Orlandin Marco
 *
 */
public class MRowGroupTotalCell extends MCell {

	private static final long serialVersionUID = 6508321371445769087L;

	public MRowGroupTotalCell(MRowGroup parent, JRCellContents jfRield, String groupName) {
		super(parent, jfRield, Messages.common_total + StringUtils.SPACE + groupName);
	}
	
	/**
	 * Update the name with the current group name.
	 */
	@Override
	public void updateName() {
		ANode parent = getParent();
		if (parent != null && parent.getValue() instanceof JRCrosstabRowGroup){
			JRCrosstabRowGroup rowGroup = (JRCrosstabRowGroup)parent.getValue();
			setName(Messages.common_total + StringUtils.SPACE + rowGroup.getName());
		}
		//Trigger the node refresh
		super.updateName();
	}
}
