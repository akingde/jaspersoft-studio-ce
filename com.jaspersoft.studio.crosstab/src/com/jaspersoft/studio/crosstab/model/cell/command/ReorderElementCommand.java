package com.jaspersoft.studio.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.Messages;

public class ReorderElementCommand extends Command {

	private int oldIndex, newIndex;

	private JRDesignElement jrElement;
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
	public ReorderElementCommand(MGraphicElement child, MCell parent, int newIndex) {
		super(Messages.ReorderElementCommand_reorder_elements);
		this.newIndex = newIndex;
		this.jrElement = (JRDesignElement) child.getValue();
		this.jrCell = (JRDesignCellContents) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElement(jrElement);

		if (newIndex >= 0 && newIndex < jrCell.getChildren().size())
			jrCell.addElement(newIndex, jrElement);
		else
			jrCell.addElement(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrCell.removeElement(jrElement);

		if (oldIndex >= 0 && oldIndex < jrCell.getChildren().size())
			jrCell.addElement(oldIndex, jrElement);
		else
			jrCell.addElement(jrElement);
	}
}
