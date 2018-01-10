/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.wizards;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.DatasetRunWidgetRadio;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class WizardConnectionPage extends JSSHelpWizardPage implements IExpressionContextSetter{

	private DatasetRunWidgetRadio dsRun;
	
	private ExpressionContext expContext;
	
	private JRDesignDatasetRun resultValues;
	
	public WizardConnectionPage() {
		super("connectionpage"); //$NON-NLS-1$
		setTitle(Messages.common_connection);
		setDescription(Messages.WizardConnectionPage_description);
		
		resultValues = new JRDesignDatasetRun();
		
		// By default we set the dataset run to use the report connection...
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("$P{REPORT_CONNECTION}");
		resultValues.setConnectionExpression(exp);
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_DATASET_CONNECTION;
	}


	public void createControl(Composite parent) {
		dsRun = new DatasetRunWidgetRadio(parent);
		setControl(dsRun.getControl());
		dsRun.setData(resultValues);
		if(expContext!=null){
			dsRun.setExpressionContext(expContext);
		}
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext=expContext;
		if(dsRun!=null){
			dsRun.setExpressionContext(expContext);
		}
	}
	
	public JRExpression getConnectionExpression(){
		return resultValues.getConnectionExpression();
	}
	
	public JRExpression getDatasourceExpression(){
		return resultValues.getDataSourceExpression();
	}
	
}
