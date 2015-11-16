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
package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Wizard page to define or edit a new parameter
 * 
 * @author Orlandin Marco
 *
 */
public class ParameterWizardPage extends WizardPage {

	/**
	 * The modified element if the wizard is used to edit 
	 * an element or null if it is used to crate a new one
	 */
	private ParameterDefinition modifiedElement;
	
	/**
	 * The jasperdesign of the handled KPI 
	 */
	private JasperDesign jd;
	
	/**
	 * Field for the parameter name
	 */
	private Text name;
	
	/**
	 * Filed for the parameter type
	 */
	private Combo typeCombo;
	
	/**
	 * Widget for the parameter default value expression
	 */
	private WTextExpression expression;
	
	/**
	 * The checkbox to choose if the parameter is for prompt or not
	 */
	private Button isPromptButton;
	
	/**
	 * Create the wizard page to edit an element or create a new one
	 * 
	 * @param modifiedElement the element to modify or null if it is a creation
	 * @param jd the jasper design of the modified KPI, must be not null
	 */
	public ParameterWizardPage(ParameterDefinition modifiedElement, JasperDesign jd) {
		super("parameterPage"); //$NON-NLS-1$
		Assert.isNotNull(jd);
		this.modifiedElement = modifiedElement;
		this.jd = jd;
		if (modifiedElement != null){
			setTitle(Messages.ParameterWizardPage_pageTitleEdit);
			setMessage(Messages.ParameterWizardPage_pageDescriptionEdit);
		} else {
			setTitle(Messages.ParameterWizardPage_pageTitleNew);
			setMessage(Messages.ParameterWizardPage_pageDescriptionNew);
		}
	}
	
	/**
	 * Return the parameter definition initialized with the values defined
	 * inside the page controls
	 * 
	 * @return a not null parameter definition
	 */
	public ParameterDefinition regenerateParameter(){
		String expressionText = ""; //$NON-NLS-1$
		JRExpression exp = expression.getExpression();
		if (exp != null) expressionText = exp.getText();
		String type = ParameterDefinition.getParameterTypes().get(typeCombo.getSelectionIndex());
		return new ParameterDefinition(name.getText(), type, expressionText, isPromptButton.getSelection());
	}
	
	/**
	 * Return an expression context for the main dateset defined inside the JasperDesign
	 * 
	 * @return  a not null expression context
	 */
	protected ExpressionContext getExpressionContext() {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(jd.getMainDesignDataset(), jConfig);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		//creating the name field
		new Label(container, SWT.NONE).setText(Messages.ParametersPage_nameLabel);
		name = new Text(container, SWT.BORDER);
		name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		//creating the elements for the type combo
		new Label(container, SWT.NONE).setText(Messages.ParametersPage_typeLabel);
		List<String> comboEntry = new ArrayList<String>();
		for(String type : ParameterDefinition.getParameterTypes()){
			comboEntry.add(MessagesByKeys.getString(type));
		}
		
		//Creating the type combo 
		typeCombo = new Combo(container, SWT.READ_ONLY);
		typeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeCombo.setData(ParameterDefinition.getParameterTypes());
		typeCombo.setItems(comboEntry.toArray(new String[comboEntry.size()]));
		typeCombo.select(0);

		//Creating the expression area
		Label expLabel = new Label(container, SWT.NONE);
		expLabel.setText(Messages.ParametersPage_valueLabel);
		GridData labelData = new GridData();
		labelData.verticalAlignment = SWT.TOP;
		expLabel.setLayoutData(labelData);
		expression = new WTextExpression(container, SWT.NONE, 3);
		GridData expData = new GridData(GridData.FILL_HORIZONTAL);
		expData.heightHint = 80;
		expression.setLayoutData(expData);
		expression.setExpressionContext(getExpressionContext());

		//Creating the is for prompt checkbox
		isPromptButton = new Button(container, SWT.CHECK);
		isPromptButton.setText(Messages.ParameterWizardPage_promptLabel);
		GridData promptData = new GridData(GridData.FILL_HORIZONTAL);
		promptData.horizontalSpan = 2;
		isPromptButton.setLayoutData(promptData);
		
		//If it is a modifying operation initialize the widgets
		if (modifiedElement != null){
			name.setText(modifiedElement.getName());
			expression.setExpression(new JRDesignExpression(modifiedElement.getExpression()));
			isPromptButton.setSelection(modifiedElement.isForPrompt());
			String name = modifiedElement.getName();
			int nameIndex = ParameterDefinition.getParameterTypes().indexOf(name);
			if (nameIndex != -1){
				typeCombo.select(nameIndex);
			} else {
				typeCombo.select(0);
			}
		}
		
		//Add a modify listener to the name to validate it when it is changed
		name.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
		
		validate();
		setControl(container);
	}
	
	/**
	 * Check if the name of the parameter is valid, it must be unique and not
	 * empty. If it not valid the page finish is disabled and an appropriate error is shown
	 */
	private void validate(){
		if (name.getText().isEmpty()){
			setPageComplete(false);
			setErrorMessage(Messages.ParameterWizardPage_errorEmpty);
		} else if (jd.getParametersMap().containsKey(name.getText())){
			setPageComplete(false);
			setErrorMessage(Messages.ParameterWizardPage_errorDuplicate);
		} else {
			setPageComplete(true);
			setErrorMessage(null);
		}
	}
}
