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

import org.eclipse.swt.widgets.Composite;

/**
 * Sub-classes of this Composite can be used as main editing area 
 * of a dialog in an expression editor.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public abstract class ExpressionEditorComposite extends Composite implements IExpressionStatusNotifier{

	/**
	 * Creates the composite.
	 * 
	 * @param parent the parent of the newly created composite
	 * @param style style information of the newly created composite
	 */
	public ExpressionEditorComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Sets the expression context that is supposed to be used for
	 * operations on the jrexpression.
	 * 
	 * <p>
	 * This method should also be used when there is the need to
	 * update some context information that are useful to edit
	 * the expression (i.e: parameters, fields, variables, etc.).
	 * 
	 * @param exprContext the expression context
	 */
	public abstract void setExpressionContext(ExpressionContext exprContext);
	
	/**
	 * Returns the {@link JRExpression} currently being modified.
	 * 
	 * @return the edited expression
	 */
	public abstract JRExpression getExpression();
	
	/**
	 * Sets the expression to be edited.
	 * 
	 * @param expression the expression to be modified
	 */
	public abstract void setExpression(JRExpression expression);
	
}
