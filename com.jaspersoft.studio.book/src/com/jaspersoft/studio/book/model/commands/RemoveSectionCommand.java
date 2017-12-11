/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model.commands;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.book.model.MBookReport;
import com.jaspersoft.studio.book.model.MGroupReportPartContainer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public class RemoveSectionCommand extends Command {
	
	private MGroupReportPartContainer sectionToRemove = null;
	
	private MBookReport parent;
	
	private int oldIndex = -1;
	
	public RemoveSectionCommand(MGroupReportPartContainer sectionToRemove){
		this.sectionToRemove = sectionToRemove;
		this.parent = (MBookReport)sectionToRemove.getParent();
	}
	
	@Override
	public boolean canExecute() {
		return sectionToRemove != null && !sectionToRemove.isDetail();
	}
	
	@Override
	public void execute() {
		oldIndex = parent.getValue().getGroupsList().indexOf(sectionToRemove.getJrgroup());
		parent.getValue().removeGroup(sectionToRemove.getJrgroup());
	}
	
	@Override
	public boolean canUndo() {
		return oldIndex > -1;
	} 
	
	@Override
	public void undo() {
		JRDesignDataset mainDataset = (JRDesignDataset) parent.getValue().getMainDataset();
		try {
			mainDataset.addGroup(oldIndex, sectionToRemove.getJrgroup());
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
