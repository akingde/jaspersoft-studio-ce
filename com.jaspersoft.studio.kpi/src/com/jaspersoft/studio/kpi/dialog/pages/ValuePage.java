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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

/**
 * Configuration page used to define the value and the target value of a 
 * KPI
 * 
 * @author Orlandin Marco
 *
 */
public class ValuePage extends AbstractKPIConfigurationPage {

	/**
	 * Name of the value variable in the KPI
	 */
	public static final String VALUE_VARIABLE_NAME = "value"; //$NON-NLS-1$
	
	/**
	 * Name of the target value variable in the KPI
	 */
	public static final String TARGET_VARIABLE_NAME = "target"; //$NON-NLS-1$
	
	/**
	 * Name of the parameter that contains the format of the value as default expression
	 */
	public static final String VALUE_FORMATTED_PARAMETER = "valuePattern"; //$NON-NLS-1$
	
	/**
	 * Name of the parameter that contains the format of the target as default expression
	 */
	public static final String TARGET_FORMATTED_PARAMETER = "targetPattern"; //$NON-NLS-1$
	
	/**
	 * Update the value of both the expression and initial value expression
	 * of a variable with a specific name. If the variable is not found it is 
	 * created. The expression is cloned for both the values
	 * 
	 * @param expression the expression, can be null to set the expressions to null
	 * @param variableName the variable name
	 */
	private void updateVariableExpression(JRExpression expression, String variableName){
		JRDesignVariable variable = getVariable(variableName);
		JRExpression valueExpression = null;
		JRExpression initialValueExpression = null;
		if (expression != null){
			valueExpression = (JRExpression)expression.clone();
			initialValueExpression = (JRExpression)expression.clone();
		}
		variable.setExpression(valueExpression);
		variable.setInitialValueExpression(initialValueExpression);
	}
	
	/**
	 * Set the pattern as default value expression of a parameter. If the parameter
	 * doesn't exist it is created. The pattern is saved as a string expression
	 * 
	 * @param pattern the pattern
	 * @param patternParameter the parameter name
	 */
	private void setPattern(String pattern, String patternParameter){
		JRDesignParameter element = getParameter(patternParameter);
		JRDesignExpression expression = new JRDesignExpression("\"" + pattern + "\""); //$NON-NLS-1$ //$NON-NLS-2$
		element.setDefaultValueExpression(expression);
	}
	
	/**
	 * Return the pattern value stored inside a parameter. If the parameter is not found then it is created
	 * 
	 * @param patternParameter the parameter name
	 * @return a not null string defining the pattern. If not pattern is define in the parameter then it return the empty string
	 */
	private String getPattern(String patternParameter){
		JRDesignParameter element = getParameter(patternParameter);
		String pattern = ""; //$NON-NLS-1$
		if (element.getDefaultValueExpression() != null && element.getDefaultValueExpression().getText()!=null){
			pattern = element.getDefaultValueExpression().getText();
			if (pattern.startsWith("\"") && pattern.endsWith("\"")){ //$NON-NLS-1$ //$NON-NLS-2$
				pattern = pattern.substring(1, pattern.length()-1);
			}
		}
		return pattern;
	}
	
	/**
	 * Create the controls to edit an expression, calculation time and a pattern 
	 * 
	 * @param parent the parent of the control
	 * @param groupName the text on the SWT group where the controls are created
	 * @param variableName the name of the variable where the expression is saved both as initial value expression and value expression
	 * @param patternField the name of the parameter where the pattern is stored 
	 */
	private void createExpressionGroup(Composite parent, String groupName, final String variableName, final String patternField){
		Group container = new Group(parent, SWT.NONE);
		container.setText(groupName);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		//Controls to edit the expression
		new Label(container,SWT.NONE).setText(Messages.common_expression);
		final WTextExpression expr = new WTextExpression(container, SWT.NONE, 3);
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRExpression exp = getVariable(variableName).getExpression();
		expr.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr.setExpressionContext(getExpressionContext(jd.getMainDesignDataset()));
		expr.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr.getExpression();
				updateVariableExpression(exp, variableName);
			}
		});
		
		//Controls for the calculation time
		new Label(container,SWT.NONE).setText(Messages.MVariable_calculation);
		CalculationEnum[] calculations = CalculationEnum.values();
		Combo calculationsCombo = new Combo(container, SWT.READ_ONLY);
		calculationsCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//Create the entries for the calculation time
		List<String> items = new ArrayList<String>();
		int selectionIndex = 0;
		CalculationEnum variableCalculation = getVariable(variableName).getCalculationValue();
		for(CalculationEnum calcEnum : calculations){
			if (calcEnum.equals(variableCalculation)){
				selectionIndex = items.size();
			}
			items.add(MessagesByKeys.getString(calcEnum.getName()));
		}
		calculationsCombo.setData(calculations);
		calculationsCombo.setItems(items.toArray(new String[items.size()]));
		calculationsCombo.select(selectionIndex);
		calculationsCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Combo combo = (Combo)e.widget;
				int index = combo.getSelectionIndex();
				CalculationEnum[] calculations = (CalculationEnum[])combo.getData();
				//Update the calculation on the variable
				getVariable(variableName).setCalculation(calculations[index]);
			}
		});
		
		//Create the controls for the buttern
		new Label(container,SWT.NONE).setText(Messages.common_pattern);
		Composite patternContainer = new Composite(container, SWT.NONE);
		patternContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout patternLayout = new GridLayout(2,false);
		patternLayout.marginWidth = 0;
		patternLayout.marginHeight = 0;
		patternContainer.setLayout(patternLayout);

		final Text pattern = new Text(patternContainer, SWT.BORDER);
		pattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pattern.setText(getPattern(patternField));
		pattern.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				setPattern(((Text)e.widget).getText(), patternField);
			}
		});
		
		//Button to open the pattern dialog
		Button btn = new Button(patternContainer, SWT.PUSH);
		btn.setText("..."); //$NON-NLS-1$
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setValue(pattern.getText());
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					pattern.setText(wizard.getValue());
				}
			}
		});
	}
	
	/**
	 * Create the page calling the super constructor
	 * 
	 * @param jd a not null JasperDesign
	 */
	public ValuePage(JasperDesign jd) {
		super(jd);
		Assert.isNotNull(jd);
	}
	
	@Override
	public String getTitle() {
		return Messages.ValuePage_pageTitle;
	}
	
	@Override
	public String getName() {
		return Messages.ValuePage_pageLabel;
	}
	
	@Override
	protected Composite createComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout mainLayout = new GridLayout(1,false);
		mainLayout.verticalSpacing = 10;
		container.setLayout(mainLayout);
		//Create the controls for the value and the target
		createExpressionGroup(container, Messages.ValuePage_valueLabel, VALUE_VARIABLE_NAME, VALUE_FORMATTED_PARAMETER);
		createExpressionGroup(container, Messages.ValuePage_targetLabel, TARGET_VARIABLE_NAME, TARGET_FORMATTED_PARAMETER);
		return container;
	}
}
