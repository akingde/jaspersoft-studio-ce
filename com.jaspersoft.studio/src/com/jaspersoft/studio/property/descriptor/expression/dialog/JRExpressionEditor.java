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

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;

public class JRExpressionEditor extends Wizard {
	private JRDesignExpression mExpression;
	private ExpressionContext exprContext;
	private JRExpressionPage page0;

	public JRDesignExpression getValue() {
		if (page0 != null)
			return page0.getValue();
		return mExpression;
	}

	public void setValue(JRDesignExpression value) {
		if (page0 != null)
			page0.setValue(value);
		this.mExpression = value;
	}
	
	public void setExpressionContext(ExpressionContext exprContext){
		this.exprContext=exprContext;
		if(page0!=null){
			page0.setExpressionContext(this.exprContext);
		}
	}

	public JRExpressionEditor() {
		super();
		setWindowTitle(Messages.common_expression_editor);
	}

	@Override
	public void addPages() {
		page0 = new JRExpressionPage("jrquery.editor"); //$NON-NLS-1$
		page0.setValue(mExpression);
		if(exprContext!=null){
			page0.setExpressionContext(exprContext);
		}
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		if(exprContext!=null){
			// Add the imports needed for the functions library
			ExpressionEditorSupportUtil.addFunctionsLibraryImports(
					exprContext.getJasperReportsConfiguration().getJasperDesign());
		}
		return true;
	}

}
