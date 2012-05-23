package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import com.google.inject.Injector;
import com.jaspersoft.studio.editor.expression.ExpressionContext;

import de.itemis.xtext.utils.jface.viewers.StyledTextXtextAdapter;

/**
 * This is a custom adapter that extends the functionalities of 
 * the default {@link StyledTextXtextAdapter}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class StyledTextXtextAdapter2 extends StyledTextXtextAdapter {

	/**
	 * Creates the adapter.
	 * 
	 * @param injector the injector
	 */
	public StyledTextXtextAdapter2(Injector injector) {
		super(injector);
	}

	/**
	 * Sets the information related to the expression context as
	 * viewer attribute.
	 * 
	 * <p>
	 * NOTE: this gives the ability to re-use this information when needed in
	 * specific context.<br>
	 * EXAMPLE OF USE: inside the proposal provider class when giving 
	 * code completion for parameter, field or variable tokens.
	 * 
	 * @param exprContext the expression context
	 */
	public void configureExpressionContext(ExpressionContext exprContext){
		getXtextSourceviewer().setData(ExpressionContext.ATTRIBUTE_EXPRESSION_CONTEXT, exprContext);
	}
}
