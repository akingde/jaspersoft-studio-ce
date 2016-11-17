/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnBeforeAction;

/**
 * Create the toolbar button to add a column to the selected table
 * 
 * @author Orlandin Marco
 *
 */
public class CreateColumnBeforeContributionItem extends CreateColumnContributionItem{
	
	/**
	 * Action that will be executed to add the column
	 */
	@Override
	protected CreateColumnAction getAction(){ 
		return new CreateColumnBeforeAction(null);
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		if (getSelectionForType(MCell.class).size() == 1){
			setEnablement();
			return true;
		}
		return false;
	}
	
}
