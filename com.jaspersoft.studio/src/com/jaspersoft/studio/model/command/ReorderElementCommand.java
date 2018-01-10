/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
/*
 * The Class ReorderElementCommand.
 */
public class ReorderElementCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;
	
	/** The jr element. */
	private JRDesignElement jrElement;
	
	/** The jr group. */
	private JRElementGroup jrGroup;
	
	/**
	 * The parent of the moved element
	 */
	private ANode parent;
	
	/**
	 * The command used to relayout the children when they are moved
	 */
	private Command layoutCommand = null;

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
	public ReorderElementCommand(MGraphicElement child, ANode parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = Math.max(0, newIndex);
		this.jrElement = (JRDesignElement) child.getValue();
		this.jrGroup = jrElement.getElementGroup();
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrGroup.getChildren().indexOf(jrElement);

		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElement(jrElement);
			if (newIndex < 0 || newIndex > jrGroup.getChildren().size())
				((JRDesignElementGroup) jrGroup).addElement(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElement(newIndex, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElement(jrElement);
			if (newIndex < 0 || newIndex > jrGroup.getChildren().size())
				((JRDesignFrame) jrGroup).addElement(jrElement);
			else
				((JRDesignFrame) jrGroup).addElement(newIndex, jrElement);
		}
		layoutCommand = LayoutManager.createRelayoutCommand(parent);
		if (layoutCommand != null){
			layoutCommand.execute();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElement(jrElement);
			if (oldIndex < 0 || oldIndex > jrGroup.getChildren().size())
				((JRDesignElementGroup) jrGroup).addElement(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElement(oldIndex, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElement(jrElement);
			if (oldIndex < 0 || oldIndex > jrGroup.getChildren().size())
				((JRDesignFrame) jrGroup).addElement(jrElement);
			else
				((JRDesignFrame) jrGroup).addElement(oldIndex, jrElement);
		}
		if (layoutCommand != null){
			layoutCommand.undo();
		}
	}

}
