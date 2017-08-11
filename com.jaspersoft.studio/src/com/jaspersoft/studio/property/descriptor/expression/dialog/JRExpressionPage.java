/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression.dialog;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorComposite;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupport;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.expression.ExpressionStatus;
import com.jaspersoft.studio.editor.expression.IExpressionStatusChangeListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.ClassType;
import com.jaspersoft.studio.utils.RecentExpressions;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class JRExpressionPage extends JSSHelpWizardPage {

	private JRDesignExpression value;
	private StyledText queryText;
	private ClassType valueType;
	private ExpressionEditorComposite contributedComposite;
	private ExpressionContext exprContext;

	public JRDesignExpression getValue() {
		if(contributedComposite!=null){
			JRDesignExpression expression = (JRDesignExpression) contributedComposite.getExpression();
			if(expression!=null && !Misc.nvl(expression.getText()).equals("")){ //$NON-NLS-1$
				RecentExpressions.addNewExpression(expression.getText());
			}
			return expression;
		}
		else{
			return value;			
		}
	}

	public void setValue(JRDesignExpression value) {
		if (value != null)
			this.value = (JRDesignExpression) value.clone();
		else
			this.value = new JRDesignExpression();
	}
	
	public void setExpressionContext(ExpressionContext exprContext){
		this.exprContext=exprContext;
		// Update the current expression context reference
		ExpressionEditorSupportUtil.setCurrentExpressionContext(this.exprContext);
	}

	protected JRExpressionPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_expression_editor);
		setDescription(Messages.JRExpressionPage_description);
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_EXPRESSION_EDITOR;
	}

	public void createControl(Composite parent) {
		// Seeks for a possible contributed composite
		ExpressionEditorSupport editorSupportForReportLanguage = ExpressionEditorSupportUtil.getEditorSupport(exprContext);
		if(editorSupportForReportLanguage!=null){
			contributedComposite = editorSupportForReportLanguage.createExpressionEditorComposite(parent);
			contributedComposite.setExpressionContext(getExpressionContext());
			contributedComposite.addExpressionStatusChangeListener(new IExpressionStatusChangeListener() {
				public void statusChanged(ExpressionStatus status) {
					setErrorMessage(null);
					switch (status) {
						case INFO:
							setMessage(Messages.JRExpressionPage_description);
							break;
						case ERROR: 
							setMessage(status.getShortDescription(),DialogPage.ERROR);
							break;
						case WARNING: 
							setMessage(status.getShortDescription(),DialogPage.WARNING);
							break;
					}
				}
			});
			contributedComposite.setExpression(this.value);
			setControl(contributedComposite);
		}
		// Otherwise fallback to a generic composite
		else{
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout(2, false));
			setControl(composite);
	
			Label lbl1 = new Label(composite, SWT.NONE);
			lbl1.setText("Value Class Name"); //$NON-NLS-1$
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			lbl1.setLayoutData(gd);
	
			valueType = new ClassType(composite, Messages.JRExpressionPage_1);
			valueType.addListener(new ModifyListener() {
	
				public void modifyText(ModifyEvent e) {
					value.setValueClassName(valueType.getClassType());
				}
			});
	
			Label lbl2 = new Label(composite, SWT.NONE);
			lbl2.setText(Messages.common_expression + ":"); //$NON-NLS-1$
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			lbl2.setLayoutData(gd);
	
			queryText = new StyledText(composite, SWT.BORDER);
			gd = new GridData(GridData.FILL_BOTH);
			gd.horizontalSpan = 2;
			queryText.setLayoutData(gd);
	
			setWidgets();
			queryText.setFocus();
			queryText.addModifyListener(new ModifyListener() {
	
				public void modifyText(ModifyEvent e) {
					value.setText(queryText.getText());
				}
			});
		}
	}

	private ExpressionContext getExpressionContext() {
		if(exprContext==null){
			// Try to get the global expression context that use the main dataset
			exprContext=ExpressionEditorSupportUtil.getReportExpressionContext();
		}
		return exprContext;
	}
	
	private void setWidgets() {
		queryText.setText(Misc.nvl(value.getText(), "")); //$NON-NLS-1$
		valueType.setClassType(value.getValueClassName());
	}

	
	
}
