/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.parameter.command;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.util.BundleCommonUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.parameter.MParameters;

/**
 * This command takes care of removing the all the current parameters from a {@link JRDesignDataset} instance
 * and replace them with the newly specified ones. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ReplaceAllParametersCommand extends Command {
	
	private List<JRDesignParameter> oldJRParameters;
	private List<JRDesignParameter> newOrderedJRParameters;
	private JRDesignDataset jrDataset;

	/**
	 * Creates the command.
	 * 
	 * @param children the list of new parameters
	 * @param mParameters the model object parent
	 */
	public ReplaceAllParametersCommand(List<JRDesignParameter> children, MParameters<?> mParameters) {
		super(Messages.ReplaceAllParametersCommand_Label);
		this.jrDataset = (JRDesignDataset) mParameters.getValue();
		this.newOrderedJRParameters = children;
		this.oldJRParameters = new ArrayList<JRDesignParameter>(jrDataset.getParametersList().size());
	}

	@Override
	public void execute() {
		try {
			JRParameter[] originalParameters = jrDataset.getParameters();
			for(int i=0;i<originalParameters.length;i++) {
				jrDataset.removeParameter(originalParameters[i]);
				oldJRParameters.add((JRDesignParameter) originalParameters[i]);
			}
			for(int j=0;j<newOrderedJRParameters.size();j++) {
				jrDataset.addParameter(newOrderedJRParameters.get(j));
			}
		} catch (JRException e) {
			BundleCommonUtils.logError(
					JaspersoftStudioPlugin.PLUGIN_ID, Messages.ReplaceAllParametersCommand_ExecuteError, e);
		}
	}	

	@Override
	public void undo() {
		try {
			JRParameter[] fields = jrDataset.getParameters();
			for(JRParameter f : fields) {
				jrDataset.removeParameter(f);
			}
			for(JRParameter f : oldJRParameters) {
				jrDataset.addParameter(f);
			}
		} catch (JRException e) {
			BundleCommonUtils.logError(
					JaspersoftStudioPlugin.PLUGIN_ID, Messages.ReplaceAllParametersCommand_UndoError, e);
		}
	}

}
