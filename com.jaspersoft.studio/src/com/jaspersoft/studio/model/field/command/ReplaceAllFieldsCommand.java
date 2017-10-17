/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.util.BundleCommonUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.field.MFields;

/**
 * This command takes care of removing the all the current fields from a {@link JRDesignDataset} instance
 * and replace them with the newly specified ones. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ReplaceAllFieldsCommand extends Command {
	
	private List<JRDesignField> oldJRFields;
	private List<JRDesignField> newOrderedJRFields;
	private JRDesignDataset jrDataset;

	/**
	 * Creates the command.
	 * 
	 * @param children the list of new fields
	 * @param parent the model object parent
	 */
	public ReplaceAllFieldsCommand(List<JRDesignField> children, MFields parent) {
		super(Messages.ReplaceAllFieldsCommand_Label);
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.newOrderedJRFields = children;
		this.oldJRFields = new ArrayList<JRDesignField>(jrDataset.getFieldsList().size());
	}

	@Override
	public void execute() {
		try {
			JRField[] originalFields = jrDataset.getFields();
			for(int i=0;i<originalFields.length;i++) {
				jrDataset.removeField(originalFields[i]);
				oldJRFields.add((JRDesignField) originalFields[i]);
			}
			for(int j=0;j<newOrderedJRFields.size();j++) {
				jrDataset.addField(newOrderedJRFields.get(j));
			}
		} catch (JRException e) {
			BundleCommonUtils.logError(
					JaspersoftStudioPlugin.PLUGIN_ID, Messages.ReplaceAllFieldsCommand_ExecuteError, e);
		}
	}	

	@Override
	public void undo() {
		try {
			JRField[] fields = jrDataset.getFields();
			for(JRField f : fields) {
				jrDataset.removeField(f);
			}
			for(JRField f : oldJRFields) {
				jrDataset.addField(f);
			}
		} catch (JRException e) {
			BundleCommonUtils.logError(
					JaspersoftStudioPlugin.PLUGIN_ID, Messages.ReplaceAllFieldsCommand_UndoError, e);
		}
	}

}
