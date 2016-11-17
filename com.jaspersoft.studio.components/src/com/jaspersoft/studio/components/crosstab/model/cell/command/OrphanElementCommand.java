/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.MGraphicElement;

public class OrphanElementCommand extends Command {

	/** The index. */
	private int index;
	private JRDesignElement jrElement;
	private JRDesignCellContents jrCell;
	private MCell parent;

	/**
	 * Instantiates a new orphan element command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanElementCommand(MCell parent, MGraphicElement child) {
		super(Messages.common_orphan_child);
		this.jrElement = (JRDesignElement) child.getValue();
		this.jrCell = (JRDesignCellContents) parent.getValue();
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElement(jrElement);
		LayoutManager.layoutContainer(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index >= 0 && index <= jrCell.getChildren().size())
			jrCell.addElement(index, jrElement);
		else
			jrCell.addElement(jrElement);
		LayoutManager.layoutContainer(parent);
	}

}
