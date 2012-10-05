/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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
