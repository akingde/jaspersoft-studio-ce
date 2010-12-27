package com.jaspersoft.studio.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.MElementGroup;

public class ReorderElementGroupCommand extends Command {

	private int oldIndex, newIndex;

	private JRDesignElementGroup jrElement;
	private JRDesignCellContents jrCell;

	/**
	 * Instantiates a new reorder element command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderElementGroupCommand(MElementGroup child, MCell parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = newIndex;
		this.jrElement = (JRDesignElementGroup) child.getValue();
		this.jrCell = (JRDesignCellContents) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElementGroup(jrElement);

		if (newIndex >= 0 && newIndex < jrCell.getChildren().size())
			jrCell.addElementGroup(newIndex, jrElement);
		else
			jrCell.addElementGroup(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrCell.removeElementGroup(jrElement);

		if (oldIndex >= 0 && oldIndex < jrCell.getChildren().size())
			jrCell.addElementGroup(oldIndex, jrElement);
		else
			jrCell.addElementGroup(jrElement);
	}
}
