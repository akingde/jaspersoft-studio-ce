/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Command used to execute a series of SetValueCommand and then a layout 
 * command to refresh the disposition of the children inside  a container.
 * The layout command is optional and in case it is executed as last command both on
 * undo or executed
 */
public class SetLayoutProperty extends Command {

	/**
	 * the layout command
	 */
	private LayoutCommand layoutCommand = null;
	
	/**
	 * The list of {@link SetValueCommand} to be executed
	 */
	private List<SetValueCommand> setCommands = new ArrayList<SetValueCommand>();
	
	/**
	 * Add a new {@link SetValueCommand} to be executed 
	 * 
	 * @param command a not null {@link SetValueCommand}
	 */
	public void addSetValueCommand(SetValueCommand command){
		setCommands.add(command);
	}
	
	/**
	 * Add a {@link LayoutCommand} to be executed
	 * 
	 * @param command a {@link LayoutCommand}, if null it is not
	 * executed
	 */
	public void setLayoutCommand(LayoutCommand command){
		this.layoutCommand = command;
	}
	
	/**
	 * Execute all the set value command and then the layout
	 * command, if present
	 */
	@Override
	public void execute() {
		for(SetValueCommand command : setCommands){
			command.execute();
		}
		if (layoutCommand != null){
			layoutCommand.execute();
		}
	}
	
	/**
	 * Undo all the set value command and then the layout
	 * command, if present
	 */
	@Override
	public void undo() {
		for(SetValueCommand command : setCommands){
			command.undo();
		}
		if (layoutCommand != null){
			layoutCommand.undo();
		}
	}
	
	/**
	 * Redo all the set value command and then the layout
	 * command, if present
	 */
	@Override
	public void redo() {
		for(SetValueCommand command : setCommands){
			command.redo();
		}
		if (layoutCommand != null){
			layoutCommand.redo();
		}
	}
	
	/**
	 * Check if there is at least one set value command
	 * 
	 * @return true if a set value command is defined, false otherwise
	 */
	public boolean isEmpty(){
		return setCommands.isEmpty();
	}
}
