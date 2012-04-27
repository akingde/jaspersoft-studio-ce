/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.expression.dialog;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.wizard.WizardPage;
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
import com.jaspersoft.studio.utils.Misc;

public class JRExpressionPage extends WizardPage {
	private JRDesignExpression value;
	private StyledText queryText;
	private ClassType valueType;
	private ExpressionEditorComposite contributedComposite;
	private ExpressionContext exprContext;

	public JRDesignExpression getValue() {
		if(contributedComposite!=null){
			return (JRDesignExpression) contributedComposite.getExpression();
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
	}

	protected JRExpressionPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_expression_editor);
		setDescription(Messages.JRExpressionPage_description);
	}

	public void createControl(Composite parent) {
		// Seeks for a possible contributed composite
		ExpressionEditorSupport editorSupportForReportLanguage = ExpressionEditorSupportUtil.getEditorSupportForReportLanguage();
		if(editorSupportForReportLanguage!=null){
			contributedComposite = editorSupportForReportLanguage.createExpressionEditorComposite(parent);
			contributedComposite.setExpressionContext(getExpressionContext());
			contributedComposite.addExpressionStatusChangeListener(new IExpressionStatusChangeListener() {
				public void statusChanged(ExpressionStatus status) {
					if(status.equals(ExpressionStatus.ERROR)){
						setErrorMessage(status.getShortDescription());
					}
					else{
						setErrorMessage(null);
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
	
			valueType = new ClassType(composite, "Expression Class type (deprecated)");
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
		queryText.setText(Misc.nvl(value.getText(), ""));
		valueType.setClassType(value.getValueClassName());
	}

	
	
}
