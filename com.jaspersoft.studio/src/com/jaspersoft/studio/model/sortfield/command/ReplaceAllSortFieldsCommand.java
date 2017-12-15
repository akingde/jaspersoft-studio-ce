/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.sortfield.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.sortfield.MSortFields;

import net.sf.jasperreports.eclipse.util.BundleCommonUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;

/**
 * This command takes care of removing the all the current sort fields from a 
 * {@link JRDesignDataset} instance and replace them with the newly specified ones. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ReplaceAllSortFieldsCommand extends Command {
	
	private List<JRDesignSortField> oldSortFields;
	private List<JRDesignSortField> newOrderedSortFields;
	private JRDesignDataset jrDataset;

	/**
	 * Creates the command.
	 * 
	 * @param children the list of new fields
	 * @param mSortFields the model object parent
	 */
	public ReplaceAllSortFieldsCommand(List<JRDesignSortField> children, MSortFields mSortFields) {
		super(Messages.ReplaceAllSortFieldsCommand_Label);
		this.jrDataset = (JRDesignDataset) mSortFields.getValue();
		this.newOrderedSortFields = children;
		this.oldSortFields = new ArrayList<JRDesignSortField>(jrDataset.getSortFieldsList().size());
	}

	@Override
	public void execute() {
		try {
			JRSortField[] originalSortFields = jrDataset.getSortFields();
			for(int i=0;i<originalSortFields.length;i++) {
				jrDataset.removeSortField(originalSortFields[i]);
				oldSortFields.add((JRDesignSortField) originalSortFields[i]);
			}
			for(int j=0;j<newOrderedSortFields.size();j++) {
				jrDataset.addSortField(newOrderedSortFields.get(j));
			}
		} catch (JRException e) {
			BundleCommonUtils.logError(
					JaspersoftStudioPlugin.PLUGIN_ID, Messages.ReplaceAllSortFieldsCommand_ExecuteError, e);
		}
	}	

	@Override
	public void undo() {
		try {
			JRSortField[] sortFields = jrDataset.getSortFields();
			for(JRSortField f : sortFields) {
				jrDataset.removeSortField(f);
			}
			for(JRSortField f : oldSortFields) {
				jrDataset.addSortField(f);
			}
		} catch (JRException e) {
			BundleCommonUtils.logError(
					JaspersoftStudioPlugin.PLUGIN_ID, Messages.ReplaceAllSortFieldsCommand_UndoError, e);
		}
	}

}
