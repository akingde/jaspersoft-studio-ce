/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.MGraphicElement;

public class DeleteElementCommand extends Command {

	private JRDesignCellContents jrCell;
	private JRDesignElement jrElement;

	private int elementPosition = 0;
	
	private MCell parent;

	/**
	 * Instantiates a new delete element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteElementCommand(MCell destNode, MGraphicElement srcNode) {
		super();
		this.jrElement = (JRDesignElement) srcNode.getValue();
		this.jrCell = (JRDesignCellContents) destNode.getValue();
		this.parent = destNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrCell != null && jrCell.getChildren() != null) {
			elementPosition = jrCell.getChildren().indexOf(jrElement);
			jrCell.removeElement(jrElement);
			LayoutManager.layoutContainer(parent);
		}
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
		if (jrCell != null && jrCell.getChildren() != null) {
			if (elementPosition >= 0 && elementPosition <= jrCell.getChildren().size())
				jrCell.addElement(elementPosition, jrElement);
			else
				jrCell.addElement(jrElement);
			
			LayoutManager.layoutContainer(parent);
		}
	}
}
