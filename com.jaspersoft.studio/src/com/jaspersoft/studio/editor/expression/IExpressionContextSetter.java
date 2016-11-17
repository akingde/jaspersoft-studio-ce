/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.expression;

/**
 * Interface shared among different kind of classes that need 
 * a setter method for an expression context information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface IExpressionContextSetter {
	
	/**
	 * Sets the expression context. 
	 * 
	 * <p>
	 * Internal state of the interested class should be updated inside this method.
	 * For example a complex widget that implements this interface should take care
	 * of propagating the update of the expression context to its internal widgets too.
	 * 
	 * @param expContext the new expression context
	 */
	void setExpressionContext(ExpressionContext expContext);
}
