package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

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

public class ParameterWizardPage extends WizardPage {

	private ParameterDefinition modifiedElement;
	
	private JasperDesign jd;
	
	private Text name;
	
	private Combo typeCombo;
	
	private WTextExpression expression;
	
	private Button isPromptButton;
	
	public ParameterDefinition regenerateParameter(){
		String expressionText = ""; //$NON-NLS-1$
		JRExpression exp = expression.getExpression();
		if (exp != null) expressionText = exp.getText();
		String type = ParameterDefinition.getParameterTypes().get(typeCombo.getSelectionIndex());
		return new ParameterDefinition(name.getText(), type, expressionText, isPromptButton.getSelection());
	}
	
	public ParameterWizardPage(ParameterDefinition modifiedElement, JasperDesign jd) {
		super("parameterPage"); //$NON-NLS-1$
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
	public ParameterDefinition getDefinition(){
		return modifiedElement;
	}
	
	public ExpressionContext getExpressionContext() {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(jd.getMainDesignDataset(), jConfig);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(container, SWT.NONE).setText(Messages.ParametersPage_nameLabel);
		name = new Text(container, SWT.BORDER);
		name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		new Label(container, SWT.NONE).setText(Messages.ParametersPage_typeLabel);
		List<String> comboEntry = new ArrayList<String>();
		for(String type : ParameterDefinition.getParameterTypes()){
			comboEntry.add(MessagesByKeys.getString(type));
		}
		
		isPromptButton = new Button(container, SWT.CHECK);
		isPromptButton.setText(Messages.ParameterWizardPage_promptLabel);
		GridData promptData = new GridData(GridData.FILL_HORIZONTAL);
		promptData.horizontalSpan = 2;
		isPromptButton.setLayoutData(promptData);
		
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
		
		//Creating the combo 
		typeCombo = new Combo(container, SWT.READ_ONLY);
		typeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeCombo.setData(ParameterDefinition.getParameterTypes());
		typeCombo.setItems(comboEntry.toArray(new String[comboEntry.size()]));
		typeCombo.select(0);
		
		
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
		
		name.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
		validate();
		setControl(container);
	}
	
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
