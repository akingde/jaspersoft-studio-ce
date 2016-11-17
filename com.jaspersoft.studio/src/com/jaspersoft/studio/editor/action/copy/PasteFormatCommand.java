/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.List;

import net.sf.jasperreports.engine.base.JRBasePen;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MGraphicElementLinePen;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.SetValueCommand;

public class PasteFormatCommand extends Command {

	/**
	 * List of commands executed to copy the appearance of an element into 
	 * one or more elements
	 */
	protected JSSCompoundCommand executedCommands;
	
	/**
	 * Nodes where the appearance is pasted
	 */
	protected List<APropertyNode> editedNodes;

	public PasteFormatCommand(List<APropertyNode> editedNodes) {
		this.editedNodes = editedNodes;
		executedCommands = null;
	}

	@Override
	public boolean canExecute() {
		return !editedNodes.isEmpty() && CopyFormatAction.hasCopiedValues();
	}

	@Override
	public void execute() {
		//The list of nodes is not empty for the canExecuted
		executedCommands = new JSSCompoundCommand(editedNodes.get(0));
		for(APropertyNode node : editedNodes){
			generateCommandsForNode(node, executedCommands);
		}
		executedCommands.execute();
	}
	
	/**
	 * Generate the sets of SetValueCommand, one for every copied property, to 
	 * set the appearance of the target node
	 * 
	 * @param node the target node
	 * @param commands the container for the commands
	 */
	private void generateCommandsForNode(APropertyNode node, JSSCompoundCommand commands){
		for(String propString : CopyFormatAction.propertyNames){
			Object copiedValue = CopyFormatAction.getCopiedValue(propString);
			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setTarget(node);
			setCommand.setPropertyId(propString);
			setCommand.setPropertyValue(copiedValue);
			commands.add(setCommand);
			if (node instanceof MGraphicElementLinePen){
				MGraphicElementLinePen penNode = (MGraphicElementLinePen) node;
				MLinePen linePen = penNode.getLinePen();
				generateCommandsForLinePen(MGraphicElementLinePen.LINE_PEN, linePen, commands);
			}
			if (node instanceof MGraphicElementLineBox){
				MGraphicElementLineBox boxNode = (MGraphicElementLineBox) node;
				MLineBox lineBox = boxNode.getLineBox();
				generateCommandsForLinePen(MLineBox.LINE_PEN, (MLinePen)lineBox.getPropertyValue(MLineBox.LINE_PEN), commands);
				generateCommandsForLinePen(MLineBox.LINE_PEN_BOTTOM, (MLinePen)lineBox.getPropertyValue(MLineBox.LINE_PEN_BOTTOM), commands);
				generateCommandsForLinePen(MLineBox.LINE_PEN_LEFT, (MLinePen)lineBox.getPropertyValue(MLineBox.LINE_PEN_LEFT), commands);
				generateCommandsForLinePen(MLineBox.LINE_PEN_RIGHT, (MLinePen)lineBox.getPropertyValue(MLineBox.LINE_PEN_RIGHT), commands);
				generateCommandsForLinePen(MLineBox.LINE_PEN_TOP, (MLinePen)lineBox.getPropertyValue(MLineBox.LINE_PEN_TOP), commands);
			}
		}
	}
	
	/**
	 * Generate the commands to paste the attributed on a MLinePen
	 */
	private void generateCommandsForLinePen(String prefix, MLinePen node, JSSCompoundCommand commands){
		SetValueCommand setCommand = new SetValueCommand();
		setCommand.setTarget(node);
		setCommand.setPropertyId(JRBasePen.PROPERTY_LINE_COLOR);
		setCommand.setPropertyValue(CopyFormatAction.getCopiedValue(prefix+JRBasePen.PROPERTY_LINE_COLOR));
		commands.add(setCommand);
		setCommand = new SetValueCommand();
		setCommand.setTarget(node);
		setCommand.setPropertyId(JRBasePen.PROPERTY_LINE_WIDTH);
		setCommand.setPropertyValue(CopyFormatAction.getCopiedValue(prefix+JRBasePen.PROPERTY_LINE_WIDTH));
		commands.add(setCommand);
		setCommand = new SetValueCommand();
		setCommand.setTarget(node);
		setCommand.setPropertyId(JRBasePen.PROPERTY_LINE_STYLE);
		setCommand.setPropertyValue(CopyFormatAction.getCopiedValue(prefix+JRBasePen.PROPERTY_LINE_STYLE));
		commands.add(setCommand);
	}

	@Override
	public void redo() {
		executedCommands.redo();
	}

	@Override
	public boolean canUndo() {
		return executedCommands.canUndo();
	}

	@Override
	public void undo() {
		executedCommands.undo();
	}

}
