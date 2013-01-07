/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.text.MessageFormat;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ADatasetObjectDeleteCommand extends Command {
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

	protected boolean checkExpressions() {
		if (canceled != null)
			return false;

		JRExpressionCollector reportCollector = JRExpressionCollector.collector(jContext, jd);
		JRExpressionCollector datasetCollector = reportCollector.getCollector(jrDataset);
		List<JRExpression> datasetExpressions = datasetCollector.getExpressions();
		for (JRExpression expr : datasetExpressions) {
			String s = expr.getText();
			if (s != null && s.length() > 4 && s.contains(objectName)) {
				if (UIUtils.showConfirmation(Messages.ADatasetObjectDeleteCommand_confirmationtitle,
						MessageFormat.format(Messages.ADatasetObjectDeleteCommand_confirmationquestion, objectName)))
					break;
				else {
					canceled = Boolean.TRUE;
					return false;
				}
			}
		}
		canceled = Boolean.FALSE;
		return true;
	}
}
