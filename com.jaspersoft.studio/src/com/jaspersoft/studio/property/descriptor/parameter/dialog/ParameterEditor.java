/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;

/**
 * A wizard where the user can edit, add, sort or delete a list of generic parameters
 * 
 * @author Orlandin Marco
 *
 */
public class ParameterEditor extends Wizard implements IExpressionContextSetter{
	
	/**
	 * The edited parameter
	 */
	protected List<GenericJSSParameter> values;
	
	/**
	 * The only page show in the wizard with the controls to handle the parameters
	 */
	protected ParameterPage page0;
	
	/**
	 * The current expression context, used if the user want to provide an expression
	 * for the parameter trough the expression editor
	 */
	private ExpressionContext exprContext;

	/* A possible custom title used in the inner wizard page */ 
	private String customTitle;
	
	/* A possible custom message used in the inner wizard page */
	private String customMessage;

	/* A possible custom window title used in wizard */
	private String customWindowTitle;

	public ParameterEditor() {
		super();
		setNeedsProgressMonitor(false);
	}
	
	public ParameterEditor(String windowTitle, String title, String message) {
		super();
		setNeedsProgressMonitor(false);
		this.customWindowTitle = windowTitle;
		this.customTitle = title;
		this.customMessage = message;
	}
	
	/**
	 * Return the current list of parameter 
	 * 
	 * @return a not null list of generic parameters
	 */
	public List<GenericJSSParameter> getValue() {
		if (page0 != null)
			return page0.getValue();
		return values;
	}

	/**
	 * Set the current list of parameters
	 * 
	 * @param values a not null list of generic parameters
	 */
	public void setValue(List<GenericJSSParameter> values) {
		if (page0 != null)
			page0.setValue(values);
		this.values = values;
	}

	@Override
	public void addPages() {
		page0 = getEditingPage();
		page0.setValue(values);
		if(exprContext!=null){
			page0.setExpressionContext(exprContext);
		}
		addPage(page0);
	}

	/**
	 * Set the expression context for the current page. The current expression context, 
	 * used if the user want to provide an expression for the parameter trough the 
	 * expression editor
	 * 
	 * @param exprContext a not null expression context
	 */
	public void setExpressionContext(ExpressionContext exprContext){
		this.exprContext=exprContext;
		if(page0!=null){
			page0.setExpressionContext(this.exprContext);
		}
	}
	
	/**
	 * Return the title for the current windows, can be overridden to provide
	 * a custom title since this is a generic page
	 * 
	 * @return a not null window title
	 */
	public String getWindowTitle(){
		if(customWindowTitle!=null) {
			return customTitle;
		}
		else {
			return Messages.common_properties;
		}
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
	
	/**
	 * Return the page used to edit the parameters, can be overridden to 
	 * provide a customized page since the default one is generic one
	 * 
	 * @return a not null ParameterPage
	 */
	protected ParameterPage getEditingPage(){
		ParameterPage parameterPage = new ParameterPage();
		if(customTitle!=null){
			parameterPage.setTitle(customTitle);
		}
		if(customMessage!=null){
			parameterPage.setDescription(customMessage);
		}
		return parameterPage; 
	}
	
}
