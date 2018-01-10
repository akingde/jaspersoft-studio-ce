/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.expression;

import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;


/**
 * This support class provides the facilities to configure the editor 
 * for {@link JRExpression} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public abstract class ExpressionEditorSupport {

	/**
	 * Configures the expression widget that is used to represent the {@link JRExpression}.
	 * 
	 * <p>
	 * The {@link StyledText} is preferred to a common text widget, because if possible 
	 * additional facilities, like syntax highlighting, are provided.
	 * 
	 * @param widget the widget to configure 
	 * @param exprContext the expression context
	 */
	public abstract void configureExpressionWidget(StyledText widget, ExpressionContext exprContext);

	/**
	 * Creates the composite that contains the editing area for the expression.
	 * 
	 * <p>
	 * This composite can be used in the popup dialog opened when clicking on the button
	 * of the expression widget.
	 * 
	 * @param parent the parent composite
	 * @return the expression editor composite
	 */
	public abstract ExpressionEditorComposite createExpressionEditorComposite(Composite parent);

}
