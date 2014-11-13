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
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

/**
 * This dialog is used to define a pair of parameter name and expression for 
 * the parameter of a dataset run. since the parameter must match an existing parameter
 * inside the original dataset then the selection of the name can be made only between
 * a fixed set of parameters
 * 
 * @author Orlandin Marco
 *
 */
public class ComboInputParameterDialog  extends TitleAreaDialog {

	/**
	 * Combo where the user can select a valid parameter name
	 */
	private Combo paramterName;
	
	/**
	 * Widget where the user can provide the parameter expression
	 */
	private WTextExpression parameterExpression;
	
	/**
	 * The handled parameter inside the dialog
	 */
	private JRDesignDatasetParameter resultParameter;
	
	/**
	 * Actual expression context
	 */
	private ExpressionContext expContext;
	
	/**
	 * The lis of name to show in the combo
	 */
	private List<String> availableNames;
	
	/**
	 * Widget called when a widget is modified to update
	 * the container
	 */
	private ModifyListener widgetModified = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			updateContainer();
		}
	};
	
	/**
	 * Create the dialog for an edit operation
	 * 
	 * @param parentShell the shell
	 * @param editedParameter the parameter to edit
	 * @param jconfig the current jasper reports configuration
	 * @param previousParameters the list of parameters already defined
	 */
	public ComboInputParameterDialog(Shell parentShell, List<String> availableNames, JRDatasetParameter editedParameter) {
		super(parentShell);
		this.resultParameter = (JRDesignDatasetParameter)editedParameter;
		this.availableNames = availableNames;
	}
	
	/**
	 * Create the dialog for the creation of a new parameter
	 * 
	 * @param parentShell the shell
	 * @param availableNames list of the parameter names to show inside the combo
	 */
	public ComboInputParameterDialog(Shell parentShell, List<String> availableNames) {
		this(parentShell, availableNames, new JRDesignDatasetParameter());
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (newShell != null){
			newShell.setText(Messages.InputParameterDialog_dialogText);
			UIUtils.resizeAndCenterShell(newShell, 450, 300);
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label parameterNameLabel = new Label(container, SWT.NONE);
		parameterNameLabel.setText(Messages.InputParameterDialog_nameLabel);

		paramterName = new Combo(container, SWT.READ_ONLY);
		paramterName.setItems(availableNames.toArray(new String[availableNames.size()]));
		paramterName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		paramterName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (availableNames.size() == 0 || paramterName.getSelectionIndex() == -1){
					getButton(IDialogConstants.OK_ID).setEnabled(false);
				} else {
					getButton(IDialogConstants.OK_ID).setEnabled(true);
				}
			}
		});

		Label parameterExpressionLabel = new Label(container, SWT.NONE);
		parameterExpressionLabel.setText(Messages.InputParameterDialog_expressionLabel);
		GridData parameterExpressionData = new GridData();
		parameterExpressionData.verticalAlignment = SWT.TOP;
		parameterExpressionLabel.setLayoutData(parameterExpressionData);
		
		parameterExpression = new WTextExpression(container, SWT.NONE);
		if (expContext != null) parameterExpression.setExpressionContext(expContext);
		GridData expressionData = new GridData(GridData.FILL_BOTH);
		expressionData.minimumHeight = 100;
		expressionData.minimumWidth = 250;
		parameterExpression.setLayoutData(expressionData);

		initializeFields();
		updateContainer();
		
		//ADD THE MODIFY LISTENER AT THE END TO AVOID THAT IT'S CALLED DURING THE INITIALIZATION
		
		paramterName.addModifyListener(widgetModified);
		parameterExpression.getTextControl().addModifyListener(widgetModified);
		return container;
	}
	
	/**
	 * Save the value from the widget inside the container
	 */
	private void updateContainer(){
		resultParameter.setName(paramterName.getText());
		JRExpression newExpression = new JRDesignExpression(parameterExpression.getText());
		resultParameter.setExpression(newExpression);
		validate();
	}
	
	/**
	 * Create all the controls and validate the content of the dialog 
	 * 
	 * @param parent the parent control
	 * @return return the container control
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle(Messages.InputParameterDialog_dialogTitle);
		validate();
		return contents;
	}
	
	/**
	 * Check if the content of the widget is valid and enable\disable the ok button.
	 * Essentially the only validity check is a name not empty for the parameter name
	 * and that the parameter name is not already used
	 */
	private void validate(){
		boolean isValid = !resultParameter.getName().trim().isEmpty();
		if (!isValid){
			setMessage(Messages.InputParameterDialog_errorName, IMessageProvider.ERROR);
		} else {
			setMessage(Messages.InputParameterDialog_dialogDescription, IMessageProvider.INFORMATION);
		}
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null) okButton.setEnabled(isValid);
	}
	
	/**
	 * Return the parameter currently stored inside the class
	 */
	public JRDatasetParameter getValue(){
		return resultParameter;
	}
	
	/**
	 * Set the expression context that will be used inside the expression editor if it is 
	 * opened 
	 * 
	 * @param expContext expression context
	 */
	public void setExpressionContext(ExpressionContext expContext){
		this.expContext = expContext;
		if (parameterExpression != null) parameterExpression.setExpressionContext(expContext);
	}
	
	/**
	 * Initialize the controls with the value of an edited JRParameter, if this is 
	 * defined
	 */
	private void initializeFields() {
		parameterExpression.setExpression((JRDesignExpression) resultParameter.getExpression());
		paramterName.setText(paramterName.getText());
		int selectionIndex = availableNames.indexOf(resultParameter.getName());
		if (selectionIndex == -1 && resultParameter.getName() != null) {
			availableNames.add(0,resultParameter.getName());
			paramterName.setItems(availableNames.toArray(new String[availableNames.size()]));
			paramterName.select(0);
		} else {
			paramterName.select(selectionIndex);
		}
	}
}

