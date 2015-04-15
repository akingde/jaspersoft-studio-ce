/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
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
package com.jaspersoft.studio.book.wizards;

import net.sf.jasperreports.engine.JRExpression;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.wizards.JSSWizard;

public class PageWizard extends JSSWizard {
	
	private WizardConnectionPage step0;
	
	public PageWizard() {
		super();
		setWindowTitle(Messages.PageWizard_newPageWizardTitle);
	}

	@Override
	public void addPages() {

		step0 = new WizardConnectionPage();
		addPage(step0);

		ExpressionContext ec =  ExpressionEditorSupportUtil.getReportExpressionContext();
		step0.setExpressionContext(ec);	
	}
	
	public JRExpression getConnectionExpression(){
		return step0.getConnectionExpression();
	}
	
	public JRExpression getDatasourceExpression(){
		return step0.getDatasourceExpression();
	}
}
