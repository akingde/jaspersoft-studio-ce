/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model.commands;

import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;

public class CreatePartAfterCommand extends Command {
	
	private JRDesignPart partToCreate = null;
	
	private MReportPartContainer container = null;
	
	private MReportPart afterPart = null;
	
	/**
	 * 
	 * @param container
	 * @param partToCreate
	 * @param afterPart If afterpart is null it is added to the 0 position
	 */
	public CreatePartAfterCommand(MReportPartContainer container, JRDesignPart partToCreate, MReportPart afterPart){
		this.container = container;
		this.afterPart = afterPart;
		this.partToCreate = partToCreate;
	}
	
	@Override
	public boolean canExecute() {
		return partToCreate != null && container != null;
	}
	
	@Override
	public void execute() {
		
		if (afterPart == null){
			container.getValue().addPart(0, partToCreate);
		} else {
			JRDesignPart afterJrPart = afterPart.getValue();
			JRDesignSection containerJr = container.getValue();
			int index = containerJr.getPartsList().indexOf(afterJrPart);
			if (index == (containerJr.getPartsList().size()-1) ){
				containerJr.addPart(partToCreate);
			} else {
				containerJr.addPart(index+1, partToCreate);
			}
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
