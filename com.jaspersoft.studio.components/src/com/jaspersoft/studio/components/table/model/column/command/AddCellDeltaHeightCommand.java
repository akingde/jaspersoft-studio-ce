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
package com.jaspersoft.studio.components.table.model.column.command;

import org.eclipse.gef.commands.Command;

import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;

/**
 * Command to add a delta (positive or negative) to the height of a cell. If the 
 * delta is negative and it's abs is greater then the height then it is changed to 
 * the height of the cell (always with a negative sign) to avoid to have a cell with
 * height < 0
 * 
 * @author Orlandin Marco
 *
 */
public class AddCellDeltaHeightCommand extends Command{
	
	/**
	 * The old height for the undo, it is easily computable, but 
	 * for simplicity we store it
	 */
	private int oldHeight;
	
	/**
	 * The delta to add to the height of the cell
	 */
	private int newHeightDelta;
	
	/**
	 * The cell to change
	 */
	private DesignCell cell;
	
	/**
	 * Create an instance of the class
	 * 
	 * @param cell a not null cell
	 * @param newHeight the new height of the cell
	 */
	public AddCellDeltaHeightCommand(Cell cell, int newHeight){
		this.cell = (DesignCell)cell;
		this.newHeightDelta = newHeight;
	}
	
	@Override
	public void execute() {
		oldHeight = cell.getHeight();
		if (newHeightDelta < 0 && cell.getHeight() < Math.abs(newHeightDelta)){
			newHeightDelta = -cell.getHeight();
		}
		cell.setHeight(cell.getHeight()+newHeightDelta);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}
	
	@Override
	public void undo() {
		cell.setHeight(oldHeight);
	}
}
