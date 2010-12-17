package com.jaspersoft.studio.table.model.cell.command;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.table.model.column.MCell;

public class DeleteElementGroupCommand extends Command {

	private DesignCell jrCell;
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
		this.jrCell = destNode.getCell();
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