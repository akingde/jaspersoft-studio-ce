/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.action;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.JRStyle;

import org.eclipse.gef.commands.Command;

/**
 * The command to remove the style of a crosstab, support the undo
 * 
 * @author Orlandin Marco
 *
 */
public class RemoveStyleCommand extends Command{
	
	private JRDesignCellContents cell;
	
	private JRStyle oldStyle;
	
	private String oldStyleReference;
	
	public RemoveStyleCommand(JRDesignCellContents cell){
		this.cell = cell;
		oldStyle = null;
		oldStyleReference = null;
	}
	

	@Override
	public void execute() {
		oldStyle = cell.getStyle();
		oldStyleReference = cell.getStyleNameReference();
		cell.setStyle(null);
		cell.setStyleNameReference(null);
	}
	
	@Override
	public void undo() {
		cell.setStyle(oldStyle);
		cell.setStyleNameReference(oldStyleReference);
	}
	
	/**
	 * Undo is available if the cell is not null. A null value for the style is acceptable since a null 
	 * style means no style
	 */
	@Override
	public boolean canUndo() {
		return (cell != null);
	}

}
