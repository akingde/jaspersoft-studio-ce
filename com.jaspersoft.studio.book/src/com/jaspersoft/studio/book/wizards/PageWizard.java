/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.wizards;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRSubreportParameter;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterPage;
import com.jaspersoft.studio.wizards.JSSWizard;

public class PageWizard extends JSSWizard {
	
	private WizardConnectionPage step0;
	private ParameterPage step1;
	
	public PageWizard() {
		super();
		setWindowTitle(Messages.PageWizard_newPageWizardTitle);
	}

	@Override
	public void addPages() {
		ExpressionContext ec =  ExpressionEditorSupportUtil.getReportExpressionContext();
		step0 = new WizardConnectionPage();
		step0.setExpressionContext(ec);
		step1 = new ParameterPage();
		step1.setExpressionContext(ec);
		addPage(step0);
		addPage(step1);
	}
	
	public JRExpression getConnectionExpression(){
		return step0.getConnectionExpression();
	}
	
	public JRExpression getDatasourceExpression(){
		return step0.getDatasourceExpression();
	}
	
	public JRSubreportParameter[] getParameters() {
		return GenericJSSParameter.convertToSubreport(step1.getValue());
	}
	
}
