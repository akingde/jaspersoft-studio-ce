/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.beans.PropertyChangeEvent;
import java.util.List;

import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.AMultiEditor;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Special command used when an element that could  have an external 
 * editor opened it deleted. Before to delete the element ask to all
 * its children to close their editor if they are opened. This is useful
 * when it is deleted an element that has a children with the editor 
 * opened, to avoid to have an editor on an not existing element. To 
 * keep good the performance it checks first if there are subeeditors opened,
 * if there arne't it is like a standard delete command.
 * 
 * @author Orlandin Marco
 *
 */
public class CloseSubeditorsCommand extends Command{
	
	/**
	 * The command to delete the parent element
	 */
	private Command deleteCommand;
	
	/**
	 * The parent element
	 */
	private INode element;
	
	/**
	 * Create the command to close the editor (even for its children) of an element that will be deleted
	 * 
	 * @param deleteCommand the command to delete the element
	 * @param element the element that will be deleted, must be not null
	 */
	public CloseSubeditorsCommand(Command deleteCommand, INode element){
		this.deleteCommand = deleteCommand;
		this.element = element;
	}
	
	/**
	 * Create the command to close the editor (even for its children) of an element
	 * 
	 * @param parent the element that will be deleted, must be not null
	 */
	public CloseSubeditorsCommand(INode parent){
		//Create the dummy command
		this.deleteCommand = new Command() {
		};
		this.element = parent;
	}
	
	/**
	 * It send the event to close the editor recursively to all the children
	 * of the element and then execute the delete command
	 */
	@Override
	public void execute() {
		//Send the event only if there are subeditors opened
		if (areSubeditorOpened(element)) sendDeleteEvent(element.getChildren());
		deleteCommand.execute();
	}
	
	/**
	 * Recursively send the close editor event to the passed children and to every
	 * child of them
	 * 
	 * @param children the children
	 */
	private void sendDeleteEvent(List<INode> children){
		if (children == null) return;
		for (INode child : children){
			sendDeleteEvent(child.getChildren());
			if (child.getValue() instanceof JRChangeEventsSupport){
				JRChangeEventsSupport eventElement = (JRChangeEventsSupport)child.getValue();
				PropertyChangeEvent event = new PropertyChangeEvent(child.getValue(), ReportContainer.CLOSE_EDITOR_PROPERTY, child.getValue(), null);
				eventElement.getEventSupport().firePropertyChange(event);
			}
		}
	}
	
	/**
	 * Check if there are other subeditors opened. This is done to avoid 
	 * the send the close editor event if there aren't editors opened, 
	 * and do useless operation. If for some reason it is not possible to
	 * check it always return true
	 * 
	 * @param node a not null node of the model
	 * @return true if there are subeditors opened, false otherwise
	 */
	private boolean areSubeditorOpened(INode node){
		JasperReportsConfiguration jConfig = ((ANode)node).getJasperConfiguration();
		if (jConfig != null) {
			Object rawEditor = jConfig.get(AMultiEditor.THEEDITOR);
			if (rawEditor != null && rawEditor instanceof JrxmlEditor){
				JrxmlEditor editor = (JrxmlEditor)rawEditor;
				return editor.getReportContainer().hasSubeditorOpened();
			}
		}
		return true;
	}
	
	/**
	 * The undo and execute depends only on the delete command
	 */
	@Override
	public boolean canExecute() {
		return deleteCommand != null && deleteCommand.canExecute();
	}
	
	/**
	 * The undo and execute depends only on the delete command
	 */
	@Override
	public boolean canUndo() {
		return deleteCommand != null && deleteCommand.canUndo();
	}
	
	/**
	 * Simply undo the delete command, the closed editors are not reopened
	 */
	@Override
	public void undo() {
		deleteCommand.undo();
	}
	
}
