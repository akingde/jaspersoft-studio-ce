/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

/**
 * Dialog to provide the configuration of a generic parameter
 * 
 * @author Orlandin Marco
 *
 */
public class InputParameterDialog extends TitleAreaDialog {

	/**
	 * Control where the user can insert a valid parameter name
	 */
	protected Control parameterName;
	
	/**
	 * Widget where the user can provide the parameter expression
	 */
	private WTextExpression parameterExpression;
	
	/**
	 * The handled parameter inside the dialog
	 */
	protected GenericJSSParameter resultParameter;
	
	/**
	 * Actual expression context
	 */
	private ExpressionContext expContext;
	
	/**
	 * List of the JRSubreportParameter already defined
	 */
	private List<GenericJSSParameter> previousParameters;
	
	/**
	 * The original name of the parameter if this is used for an edit operation or null
	 * if this is a new parameter operation
	 */
	private String originalName = null;
	
	/**
	 * Widget called when a widget is modified to update
	 * the container
	 */
	protected ModifyListener widgetModified = new ModifyListener() {
		
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
	public InputParameterDialog(Shell parentShell, GenericJSSParameter editedParameter, List<GenericJSSParameter> previousParameters) {
		super(parentShell);
		this.resultParameter = editedParameter;
		this.previousParameters = previousParameters;
		originalName = resultParameter.getName();
	}
	
	/**
	 * Create the dialog for a new operation
	 * 
	 * @param parentShell the shell
	 * @param jconfig the current jasper reports configuration
	 * @param previousParameters the list of parameters already defined
	 */
	public InputParameterDialog(Shell parentShell, List<GenericJSSParameter> previousParameters) {
		this(parentShell, new GenericJSSParameter(), previousParameters);
		originalName = null;
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
		
		parameterName = getParameterNameControl(container);
		parameterName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label parameterExpressionLabel = new Label(container, SWT.NONE);
		parameterExpressionLabel.setText(Messages.InputParameterDialog_expressionLabel);
		GridData parameterExpressionData = new GridData();
		parameterExpressionData.verticalAlignment = SWT.TOP;
		parameterExpressionLabel.setLayoutData(parameterExpressionData);
		
		parameterExpression = new WTextExpression(container, SWT.NONE);
		GridData expressionData = new GridData(GridData.FILL_BOTH);
		expressionData.minimumHeight = 100;
		expressionData.minimumWidth = 250;
		if (expContext != null) parameterExpression.setExpressionContext(expContext);
		parameterExpression.setLayoutData(expressionData);

		String name = resultParameter.getName();
		setNameOnControl(name);
		
		JRExpression expressionValue = resultParameter.getExpression();
		String expression = expressionValue != null ? expressionValue.getText() : null;
		parameterExpression.getTextControl().setText(expression != null ? expression : ""); //$NON-NLS-1$
		updateContainer();
		
		//ADD THE MODIFY LISTENER AT THE END TO AVOID THAT IT'S CALLED DURING THE INITIALIZATION
		
		configureNameControl();
		parameterExpression.getTextControl().addModifyListener(widgetModified);
		return container;
	}
	
	/**
	 * Save the value from the widget inside the container
	 */
	private void updateContainer(){
		resultParameter.setName(getNameFromControl());
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
		if (isValid){
			for(GenericJSSParameter parameter : previousParameters){
				if (parameter.getName().equals(resultParameter.getName().trim())){
					if (originalName != null && parameter.getName().equals(originalName)){
						isValid = true;
						break;
					} else {
						isValid = false;
						setMessage(Messages.InputParameterDialog_errorDuplicate, IMessageProvider.ERROR);
						break;
					}
				}
			}
		} else {
			setMessage(Messages.InputParameterDialog_errorName, IMessageProvider.ERROR);
		}
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null) okButton.setEnabled(isValid);
		if (isValid){
			setMessage(Messages.InputParameterDialog_dialogDescription, IMessageProvider.INFORMATION);
		}
	}
	
	/**
	 * Set the expression context that will be used inside the expression editor if it is 
	 * opened 
	 * 
	 * @param expContext expression context
	 */
	public void setExpressionContext(ExpressionContext expContext){
		this.expContext = expContext;
	}
	
	/**
	 * Return the parameter currently stored inside the class
	 */
	public GenericJSSParameter getValue(){
		return resultParameter;
	}
	
	protected String getNameFromControl(){
		return ((Text)parameterName).getText();
	}
	
	protected void setNameOnControl(String name){
		((Text)parameterName).setText(name != null ? name : "");
	}
	
	protected Control getParameterNameControl(Composite parent){
		return new Text(parent, SWT.BORDER);
	}
	
	protected void configureNameControl(){
		((Text)parameterName).addModifyListener(widgetModified);
	}
}
