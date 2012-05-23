package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorComposite;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupport;
import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsActivator;

/**
 * Default implementation for the expression editor support class provided by Jaspersoft Studio.
 * 
 * NOTE: This support class should be used only when the report language is Java.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class DefaultJavaExpressionEditorSupport extends ExpressionEditorSupport {
	
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
		ExpressionEditorComposite content = new DefaultExpressionEditorComposite(parent,SWT.NONE);
		content.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		return content;
	}

}
