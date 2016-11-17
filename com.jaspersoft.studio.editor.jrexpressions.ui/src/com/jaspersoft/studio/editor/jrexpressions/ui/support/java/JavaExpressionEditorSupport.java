/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.ui.support.java;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorComposite;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupport;
import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsActivator;
import com.jaspersoft.studio.editor.jrexpressions.ui.support.StyledTextXtextAdapter2;

/**
 * Expression editor support class provided by Jaspersoft Studio for the Java language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JavaExpressionEditorSupport extends ExpressionEditorSupport {
	
	@Override
	public void configureExpressionWidget(StyledText widget, ExpressionContext exprContext) {
		StyledTextXtextAdapter2 xtextAdapter=new StyledTextXtextAdapter2(
				JRExpressionsActivator.getInstance().getInjector(JRExpressionsActivator.COM_JASPERSOFT_STUDIO_EDITOR_JREXPRESSIONS_JAVAJREXPRESSION));
		xtextAdapter.adapt(widget);
		xtextAdapter.configureExpressionContext(exprContext);
	}

	@Override
	public ExpressionEditorComposite createExpressionEditorComposite(
			Composite parent) {
		ExpressionEditorComposite content = new JavaExpressionEditorComposite(parent,SWT.NONE);
		content.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		return content;
	}

}
