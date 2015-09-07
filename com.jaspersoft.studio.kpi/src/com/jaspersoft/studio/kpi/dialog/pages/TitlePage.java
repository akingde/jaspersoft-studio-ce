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
package com.jaspersoft.studio.kpi.dialog.pages;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.messages.Messages;

/**
 * Page where the KPI name can be configured
 * 
 * @author Orlandin Marco
 *
 */
public class TitlePage extends AbstractKPIConfigurationPage {

	/**
	 * Name of the parameter where the title is stored as default value expression. The
	 * parameter is searched in the main dataset
	 */
	public static final String TITLE_PARAMETER = "title"; //$NON-NLS-1$

	/**
	 * Return the title stored into an expression or an empty string if the expression is not valid
	 * 
	 * @param exp an expression, can be null
	 * @return a not null string representing the content of the expression 
	 */
	private String getTextFromExpression(JRExpression exp){
		if (exp == null) return ""; //$NON-NLS-1$
		String text = exp.getText();
		if (text == null) return ""; //$NON-NLS-1$
		if (text.startsWith("\"")) text = text.substring(1); //$NON-NLS-1$
		if (text.endsWith("\"")) text= text.substring(0, text.length()-1); //$NON-NLS-1$
		return text;
	}
	
	/**
	 * Store the title in the parameter. If the parameter is not found
	 * then it is created.  The title is stored as default value expression
	 * and so it is converted into a string expression
	 * 
	 * @param value the title
	 */
	private void setParameterExpression(String value){
		String expressionText = "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
		JRExpression newExpression = new JRDesignExpression(expressionText);
		JRParameter parameter = jd.getParametersMap().get(TITLE_PARAMETER);
		if (parameter != null){
			((JRDesignParameter)parameter).setDefaultValueExpression(newExpression);
		} else {
			JRDesignParameter newParameter = new JRDesignParameter();
			newParameter.setDefaultValueExpression(newExpression);
			newParameter.setName(TITLE_PARAMETER);
			try {
				jd.addParameter(newParameter);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Create the page calling the super constructor
	 * 
	 * @param jd a not null JasperDesign
	 */
	public TitlePage(JasperDesign jd) {
		super(jd);
		Assert.isNotNull(jd);
	}
	
	@Override
	protected Composite createComposite(Composite parent){
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		new Label(comp, SWT.NONE).setText(Messages.TitlePage_titleLabel);
		Text titleText = new Text(comp, SWT.BORDER);
		titleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRParameter parameter = jd.getParametersMap().get(TITLE_PARAMETER);
		if (parameter != null){
			titleText.setText(getTextFromExpression(parameter.getDefaultValueExpression()));
		}
		titleText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				setParameterExpression(((Text)e.widget).getText());
				
			}
		});
		return comp;
	}
	
	@Override
	public String getName() {
		return Messages.TitlePage_pageName;
	}
	
	@Override
	public String getTitle() {
		return Messages.TitlePage_pageTitle;
	}
}
