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

public class MovePartCommand extends Command {
	
	private JRDesignPart partToMove = null;
	
	private MReportPartContainer container = null;
	
	private MReportPart afterPart = null;
	
	/**
	 * 
	 * @param container
	 * @param partToCreate
	 * @param afterPart If afterpart is null it is added to the 0 position
	 */
	public MovePartCommand(MReportPartContainer container, JRDesignPart partToMove, MReportPart afterPart){
		this.container = container;
		this.afterPart = afterPart;
		this.partToMove = partToMove;
	}
	
	@Override
	public boolean canExecute() {
		return partToMove != null && container != null;
	}
	
	@Override
	public void execute() {
		
		System.out.println("Executing move part " + partToMove + " " + this);
		
		if (afterPart == null){
			return;
		} else {
			JRDesignPart afterJrPart = afterPart.getValue();
			JRDesignSection containerJr = container.getValue();
			int index = containerJr.getPartsList().indexOf(afterJrPart);
			
			
			containerJr.removePart(partToMove);
			
			
			if (index == (containerJr.getPartsList().size()-1) ){
				containerJr.addPart(partToMove);
			} else {
				containerJr.addPart(index+1, partToMove);
			}
		}
	}
	
	@Override
	public boolean canUndo() {
		return partToMove != null && container != null;
	}
	
	@Override
	public void undo() {
		JRDesignSection containerJr = container.getValue();
		containerJr.removePart(partToMove);
	}
	
}
