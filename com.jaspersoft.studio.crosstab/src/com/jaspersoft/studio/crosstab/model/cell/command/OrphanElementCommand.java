package com.jaspersoft.studio.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.Messages;

public class OrphanElementCommand extends Command {

	/** The index. */
	private int index;
	private JRDesignElement jrElement;
	private JRDesignCellContents jrCell;

	/**
	 * Instantiates a new orphan element command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanElementCommand(MCell parent, MGraphicElement child) {
		super(Messages.OrphanElementCommand_orphan_child);
		this.jrElement = (JRDesignElement) child.getValue();
		this.jrCell = (JRDesignCellContents) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		index = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElement(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (index >= 0 && index <= jrCell.getChildren().size())
			jrCell.addElement(index, jrElement);
		else
			jrCell.addElement(jrElement);
	}

}
