/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import com.jaspersoft.studio.components.table.model.column.MColumn;

/**
 * Command to check if the position of a column is correct, if it is then 
 * the command dosen't do nothing, otherwise move the column on the correct position.
 * This command do something only on the undo, and it is used to assure that after an
 * undo of a grouping operation all the columns are returned into their original position.
 * The right position of the column is the position it has when this command is created
 * 
 * @author Orlandin Marco
 *
 */
public class CheckColumnsOrder extends MoveColumnCommand implements Comparable<CheckColumnsOrder>{
	
	/**
	 * Create the command, and calculate as correct position of the column
	 * as the position it has when the command is created
	 * 
	 * @param column a not null column, to check and move eventually on the undo
	 */
	public CheckColumnsOrder(MColumn column){
		super(column, null, false);
	}
	
	@Override
	public void execute() {
	}
	
	/**
	 * Return the position where the column should be
	 * 
	 * @return position of the column
	 */
	public int getOldPosition(){
		return oldIndex;
	}
	
	/**
	 * Check the position of the column and if it is not in 
	 * the right position the it is moved
	 */
	@Override
	public void undo() {
		int actualIndex = -1;
		if (psrcColGroup == null)
			actualIndex = jrTable.getColumns().indexOf(srcColumn);
		else  {
			actualIndex = psrcColGroup.getColumns().indexOf(srcColumn);
		}
		if (actualIndex != oldIndex || actualIndex == -1){
			delColumn(psrcColGroup, srcColumn);
			addColumn(psrcColGroup, oldIndex, srcColumn);
			tbManager.refresh();
		}
	}

	@Override
	public int compareTo(CheckColumnsOrder o) {
		return oldIndex - o.getOldPosition();
	}
}
