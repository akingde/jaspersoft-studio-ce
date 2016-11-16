/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model.commands;

import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;

public class RemoveChildrenCommand extends Command {

	private MReportPart partToRemove = null;
	
	private MReportPartContainer container = null;
	
	private int oldIndex = -1;
	
	public RemoveChildrenCommand(MReportPartContainer container, MReportPart partToRemove){
		this.container = container;
		this.partToRemove = partToRemove;
	}
	
	@Override
	public boolean canExecute() {
		return partToRemove != null && container != null;
	}
	
	@Override
	public void execute() {
		System.out.println("Executing remove part " + this + " " + partToRemove.getValue().getUUID());
		oldIndex = container.getValue().getPartsList().indexOf(partToRemove.getValue());
		container.getValue().removePart(partToRemove.getValue());
	}
	
	@Override
	public boolean canUndo() {
		return oldIndex > -1;
	} 
	
	@Override
	public void undo() {
		JRDesignSection section = container.getValue();
		if (section.getPartsList().size()<=oldIndex){
			section.addPart(partToRemove.getValue());
		} else {
			section.addPart(oldIndex, partToRemove.getValue());
		}
	}
}
