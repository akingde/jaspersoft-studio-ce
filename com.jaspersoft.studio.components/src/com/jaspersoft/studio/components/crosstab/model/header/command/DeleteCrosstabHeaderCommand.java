/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.header.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.header.MCrosstabHeaderCell;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteCrosstabHeaderCommand extends Command {

	private JRDesignCrosstab jrCrosstab;
	private JRDesignCellContents jrCell;

	/**
	 * Instantiates a new delete parameter command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteCrosstabHeaderCommand(MCrosstab destNode, MCrosstabHeaderCell srcNode) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		if (srcNode != null && srcNode.getValue() != null)
			this.jrCell = (JRDesignCellContents) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		jrCrosstab.setHeaderCell(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrCrosstab == null || jrCell == null)
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
		jrCrosstab.setHeaderCell(jrCell);
	}

}
