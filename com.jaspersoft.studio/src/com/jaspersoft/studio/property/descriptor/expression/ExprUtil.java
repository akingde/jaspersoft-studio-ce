/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.expression;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import com.jaspersoft.studio.utils.Misc;

public class ExprUtil {
	public static <T extends JRExpression> T setValues(T e, Object value) {
		return setValues(e, value, null);
	}

	@SuppressWarnings("unchecked")
	public static <T extends JRExpression> T setValues(T e, Object value, String valueClassName) {
		if (value == null)
			return null;
		String text = "";
		JRDesignExpression expr = (JRDesignExpression) e;
		expr = createExpression(expr);
		if (value instanceof JRDesignExpression) {
			return checkEmpty((T) value);
		} else if (value instanceof String) {
			text = (String) value;
		}
		expr.setText(text);
		if (valueClassName != null)
			expr.setValueClassName(valueClassName);
		return checkEmpty((T) expr);
	}

	private static JRDesignExpression createExpression(JRDesignExpression expr) {
		if (expr == null) {
			expr = new JRDesignExpression();
		}
		return expr;
	}

	public static String getExpressionText(JRExpression jrExpression) {
		if (jrExpression != null)
			return Misc.nvl(jrExpression.getText(), "");
		return "";
	}

	private static <T extends JRExpression> T checkEmpty(T e) {
		if (e.getText().trim().isEmpty())
			return null;
		return e;
	}

	public static JRExpression getExpression(JRExpression jrExpression) {
		return jrExpression;
	}
	
	
	/**
	 * Create a JRDesignExpression by specifying the expression text.
	 * 
	 * @param text - The text of the expression, or null for empty expressions
	 * 
	 * @return a new JRDesignExpression
	 */
	public static JRDesignExpression createExpression(String text)
	{
		return createExpression(text);
	}
	
	/**
	 * Create a JRDesignExpression by specifying the expression text.
	 * An optional value class name can be used as expression class.
	 * Value class name should be specified only if strictly required, since it is
	 * deprecated by JasperReports.
	 * 
	 * @param text - The text of the expression, or null for empty expressions
	 * @param valueClassName - A string representing the class returned by the expression
	 *
	 * @return a new JRDesignExpression
	 */
	@SuppressWarnings("deprecation")
	public static JRDesignExpression createExpression(String text, String valueClassName)
	{
		JRDesignExpression exp = new JRDesignExpression();
		
		if (text != null)
		{
			exp.setText(text);
		}
		
		if (valueClassName != null)
		{
			exp.setValueClassName(valueClassName);
		}
		
		return exp;
		
	}
	
	/**
	 * Create a JRDesignExpression by specifying the expression text.
	 * An optional value class can be used as expression class.
	 * Value class should be specified only if strictly required, since it is
	 * deprecated by JasperReports.
	 * 
	 * @param text - An expression, or null if the expression is empty.
	 * @param valueClass - A value class, or null for default
	 * 
	 * @return a new JRDesignExpression
	 */
	public static JRDesignExpression createExpression(String text, Class valueClass)
	{
		String valueClassName = null;
		if (valueClass != null)
		{
			valueClassName = valueClass.getName();
			if (valueClass.isArray()) valueClassName = null;
			else if (valueClass.isArray()) valueClassName = null;
			else if (valueClass.isPrimitive()) valueClassName = null;
		}
		return createExpression(text, valueClassName);
	}
}
