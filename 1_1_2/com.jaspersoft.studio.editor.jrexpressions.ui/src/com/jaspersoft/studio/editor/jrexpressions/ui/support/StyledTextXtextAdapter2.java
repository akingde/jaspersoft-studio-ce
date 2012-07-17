package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import java.lang.reflect.Method;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.TextViewer;

import com.google.inject.Injector;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsActivator;
import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsUIPlugin;

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
	
	/**
	 * Tries to tell to the Xtext viewer whether the registered
	 * auto edit strategies should be ignored.
	 * 
	 * @param ignore <code>true</code> if the strategies should be ignored.
	 */
	public void ignoreAutoEditStrategies(boolean ignore){
		try {
			// org.eclipse.jface.text.TextViewer#ignoreAutoEditStrategies(boolean) is protected by definition.
			// XtextSourceViewer does not extend its visibility so we have to bypass it
			// invoking the method via Reflection API.
			// N.B: This way of using reflection is a "violation" of OOP basis but
			// it is also a trick that works fine.
			Method declaredMethod = TextViewer.class.getDeclaredMethod("ignoreAutoEditStrategies",boolean.class);
			declaredMethod.setAccessible(true);
			declaredMethod.invoke(getXtextSourceviewer(), ignore);
		} catch (Exception e) {
			JRExpressionsActivator.getInstance().getLog().log(
					new Status(IStatus.ERROR, JRExpressionsUIPlugin.PLUGIN_ID, 
							"Unable to enable/disable auto edit strategies for Xtext source viewer.", e));
		}
	}
}
