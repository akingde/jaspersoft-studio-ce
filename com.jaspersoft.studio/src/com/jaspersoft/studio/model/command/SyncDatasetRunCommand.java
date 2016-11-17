/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRException;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.utils.SyncDatasetRunParameters;

public class SyncDatasetRunCommand extends Command {
	
	protected MDataset dataset;
	
	protected String oldLang;
	
	protected String newLang;

	public SyncDatasetRunCommand(MDataset target, MQuery newValue, MQuery oldValue) {
		super();
		this.dataset = target;
		this.oldLang = oldValue != null && oldValue.getValue() != null ? oldValue.getValue().getLanguage() : null;
		this.newLang = newValue != null && newValue.getValue() != null ? newValue.getValue().getLanguage() : null;
	}

	public SyncDatasetRunCommand(MQuery target, String newValue, String oldValue) {
		super();
		dataset = target.getMdataset();
		this.oldLang = oldValue;
		this.newLang = newValue;
	}

	public SyncDatasetRunCommand(MDataset target, String newValue, String oldValue) {
		super();
		dataset = target;
		this.oldLang = oldValue;
		this.newLang = newValue;
	}
	
	@Override
	public void execute() {
		try {
			for (IQueryLanguageChanged exec : SyncDatasetRunParameters.changed)
				exec.syncDataset(dataset.getJasperDesign(), dataset.getValue(), oldLang, newLang);
			SyncDatasetRunParameters.syncDataset(dataset, oldLang, newLang, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		try {
			for (IQueryLanguageChanged exec : SyncDatasetRunParameters.changed)
				exec.syncDataset(dataset.getJasperDesign(), dataset.getValue(), newLang, oldLang);
			SyncDatasetRunParameters.syncDataset(dataset, newLang, oldLang, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
