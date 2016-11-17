/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.expression;

import net.sf.jasperreports.engine.JRExpression;

/**
 * This interface is supposed to be implemented in order to provide 
 * facilities for the editor of {@link JRExpression} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface IExpressionEditorSupportFactory {
	
	/**
	 * Returns a support class for the specified language (i.e: Java, Groovy, etc.). 
	 * 
	 * @param language the language for which is needed support
	 * @return the editor support class
	 */
	ExpressionEditorSupport getExpressionEditorSupport(String language);
}
