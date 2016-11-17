/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import java.util.List;

import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnBeginAction;
import com.jaspersoft.studio.model.MPage;

/**
 * Create the toolbar button to add a column to the selected table. The action is
 * visible only on the main editor and only when a table, a section or a column is selected
 * 
 * @author Orlandin Marco
 *
 */
public class CreateColumnBeginContributionItem extends CreateColumnContributionItem{

	/**
	 * Action that will be executed to add the column
	 */
	@Override
	protected CreateColumnAction getAction(){ 
		return new CreateColumnBeginAction(null);
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MTable.class);
		if (selection.size() == 1){
			MTable table = (MTable)selection.get(0);
			if (table.getParent() instanceof MPage){
				setEnablement();
				return true;
			} else return false;
		}
		selection = getSelectionForType(MColumn.class);
		if (selection.size() == 1){
			//don't need to check if it is in the subeditor since this nodes are selectable
			//only in the table subeditor 
			setEnablement();
			return true;
		}
		selection = getSelectionForType(AMCollection.class);
		if (selection.size() == 1){
			//don't need to check if it is in the subeditor since this nodes are selectable
			//only in the table subeditor 
			setEnablement();
			return true;
		}
		return false;
	}
	
};
