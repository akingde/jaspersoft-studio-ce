/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.text.MessageFormat;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.action.CommandMessage;
import com.jaspersoft.studio.editor.action.MessageProviderCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ADatasetObjectDeleteCommand extends Command implements MessageProviderCommand{
	/** The jr dataset. */
	protected JRDesignDataset jrDataset;

	/** The element position. */
	protected int elementPosition = 0;
	protected JasperDesign jd;
	protected JasperReportsConfiguration jContext;

	protected Boolean canceled;
	protected String objectName;

	public ADatasetObjectDeleteCommand() {

	}

	public ADatasetObjectDeleteCommand(Boolean canceled) {
		this.canceled = canceled;
	}
	
	/**
	 * Check if the deleted field is used somewhere, in this case return a warning message
	 * otherwise null.
	 */
	@Override
	public CommandMessage getMessage() {
		JRExpressionCollector reportCollector = JRExpressionCollector.collector(jContext, jd);
		JRExpressionCollector datasetCollector = reportCollector.getCollector(jrDataset);
		List<JRExpression> datasetExpressions = datasetCollector.getExpressions();
		for (JRExpression expr : datasetExpressions) {
			String s = expr.getText();
			if (s != null && s.length() > 4 && s.contains(objectName) && !isOwnExpression(expr)) {
				return
						new CommandMessage(
								CommandMessage.Status.WARNING,
								MessageFormat.format(Messages.ADatasetObjectDeleteCommand_confirmationquestion, objectName));
			}
		}
		return null;
	}

	/**
	 * Check if the passed expression belong to the current element. This is 
	 * avoid to show the waring message when the dataset element is only 
	 * autoreferencing itself
	 * 
	 * @param expr the expression 
	 * @return true if the expression belong to the deleted element, false otherwise. By default
	 * this return always false
	 */
	protected boolean isOwnExpression(JRExpression expr){
		return false;
	}
}
