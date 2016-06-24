/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.eclipse.util.StringUtils;

/**
 * Node for the cell of the column group header cell of a crosstab. This subclass
 * only implement the methods to set and update the node name
 * 
 * @author Orlandin Marco
 *
 */
public class MColumnGroupHeaderCell extends MCell {

	private static final long serialVersionUID = -282946431078592480L;

	public MColumnGroupHeaderCell(MColumnGroup parent, JRCellContents jfRield, String groupName) {
		super(parent, jfRield, Messages.CrosstabComponentFactory_header + StringUtils.SPACE + groupName);
	}

	/**
	 * Update the name with the current group name.
	 */
	@Override
	public void updateName() {
		ANode parent = getParent();
		if (parent != null && parent.getValue() instanceof JRCrosstabColumnGroup){
			JRCrosstabColumnGroup columnGroup = (JRCrosstabColumnGroup)parent.getValue();
			setName(Messages.CrosstabComponentFactory_header + StringUtils.SPACE + columnGroup.getName());
		}
		//Trigger the node refresh
		super.updateName();
	}
}
