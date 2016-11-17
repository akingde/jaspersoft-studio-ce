/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.MElementGroup;

public class DeleteElementGroupCommand extends Command {

	private JRDesignCellContents jrCell;
	private JRDesignElementGroup jrElement;

	private int elementPosition = 0;

	/**
	 * Instantiates a new delete element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteElementGroupCommand(MCell destNode, MElementGroup srcNode) {
		super();
		this.jrElement = (JRDesignElementGroup) srcNode.getValue();
		this.jrCell = (JRDesignCellContents) destNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElementGroup(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrCell == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (elementPosition >= 0 && elementPosition <= jrCell.getChildren().size())
			jrCell.addElementGroup(elementPosition, jrElement);
		else
			jrCell.addElementGroup(jrElement);
	}
}
