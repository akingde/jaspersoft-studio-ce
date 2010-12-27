package com.jaspersoft.studio.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.MElementGroup;

public class OrphanElementGroupCommand extends Command {

	/** The index. */
	private int index;
	private JRDesignElementGroup jrElement;
	private JRDesignCellContents jrCell;

	/**
	 * Instantiates a new orphan element command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanElementGroupCommand(MCell parent, MElementGroup child) {
		super(Messages.common_orphan_child);
		this.jrElement = (JRDesignElementGroup) child.getValue();
		this.jrCell = (JRDesignCellContents) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		index = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElementGroup(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (index >= 0 && index <= jrCell.getChildren().size())
			jrCell.addElementGroup(index, jrElement);
		else
			jrCell.addElementGroup(jrElement);
	}

}
