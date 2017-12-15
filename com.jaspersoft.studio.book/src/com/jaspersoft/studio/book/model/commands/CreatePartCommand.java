/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model.commands;

import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.book.model.MReportPartContainer;

public class CreatePartCommand extends Command {
	
	private JRDesignPart partToCreate = null;
	private MReportPartContainer container = null;
	private int index = -1;
	
	public CreatePartCommand(MReportPartContainer container, JRDesignPart partToCreate){
		this.container = container;
		this.partToCreate = partToCreate;
	}
	
	public CreatePartCommand(MReportPartContainer container, JRDesignPart partToCreate, int index){
		this.container = container;
		this.partToCreate = partToCreate;
		this.index = index;
	}
	
	@Override
	public boolean canExecute() {
		return partToCreate != null && container != null;
	}
	
	@Override
	public void execute() {
		JRDesignSection section = container.getValue();
		if(index==-1){
			section.addPart(partToCreate);
		}
		else {
			section.addPart(index,partToCreate);
		}
	}
	
	@Override
	public boolean canUndo() {
		return partToCreate != null && container != null;
	}
	
	@Override
	public void undo() {
		JRDesignSection containerJr = container.getValue();
		containerJr.removePart(partToCreate);
	}
	
}
