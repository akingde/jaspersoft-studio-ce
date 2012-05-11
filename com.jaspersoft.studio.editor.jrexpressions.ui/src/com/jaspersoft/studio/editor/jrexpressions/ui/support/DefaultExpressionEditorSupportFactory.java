package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import net.sf.jasperreports.engine.JRExpression;

import com.jaspersoft.studio.editor.expression.ExpressionEditorSupport;
import com.jaspersoft.studio.editor.expression.IExpressionEditorSupportFactory;

/**
 * This is the default support factory for the {@link JRExpression} editor,
 * provided by Jaspersoft Studio.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DefaultExpressionEditorSupportFactory implements
		IExpressionEditorSupportFactory {

	public DefaultExpressionEditorSupportFactory() {
	}

	public ExpressionEditorSupport getExpressionEditorSupport(String language) {
		// FIXME Implement check based on the specified language!
		return new DefaultJavaExpressionEditorSupport();
	}

}
