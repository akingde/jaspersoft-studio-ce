package com.jaspersoft.studio.table.model.cell.command;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.table.model.column.MCell;
import com.jaspersoft.studio.utils.SelectionHelper;

public class CreateElementCommand extends Command {
	private MGraphicElement srcNode;
	private JRDesignElement jrElement;
	private StandardBaseColumn jrColumn;

	private DesignCell jrCell;

	private Rectangle location;

	private int index = -1;

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateElementCommand(MCell destNode, MGraphicElement srcNode, int index) {
		super();
		this.jrElement = (JRDesignElement) srcNode.getValue();
		this.jrCell = destNode.getCell();
		this.index = index;
		this.srcNode = srcNode;
		this.jrColumn = (StandardBaseColumn) destNode.getValue();
	}

	public CreateElementCommand(MCell destNode, MGraphicElement srcNode, Rectangle position) {
		super();
		this.jrElement = (JRDesignElement) srcNode.getValue();
		this.jrCell = destNode.getCell();
		this.location = position;
		this.srcNode = srcNode;
		this.jrColumn = (StandardBaseColumn) destNode.getValue();
	}

	/**
	 * Creates the object.
	 */
	protected void createObject() {
		if (jrElement == null) {
			jrElement = srcNode.createJRElement(srcNode.getJasperDesign());

			if (jrElement != null) {
				if (location == null)
					location = new Rectangle(0, 0, Math.min(srcNode.getDefaultWidth(), jrColumn.getWidth() - location.x),
							Math.min(srcNode.getDefaultHeight(), jrCell.getHeight() - location.y));
				jrElement.setX(location.x);
				jrElement.setY(location.y);
				jrElement.setWidth(location.width);
				jrElement.setHeight(location.height);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrElement != null) {
			if (index >= 0 && index <= jrCell.getChildren().size())
				jrCell.addElement(index, jrElement);
			else
				jrCell.addElement(jrElement);
		}
		if (firstTime) {
			SelectionHelper.setSelection(jrElement, false);
			firstTime = false;
		}
	}

	private boolean firstTime = true;

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
		jrCell.removeElement(jrElement);
	}

}
