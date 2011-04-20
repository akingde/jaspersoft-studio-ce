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
package com.jaspersoft.studio.property.descriptor.expression;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;

public class ExprUtil {
	public static JRExpression setValues(JRExpression e, Object value) {
		if (value == null)
			return null;
		String text = "";
		JRDesignExpression expr = (JRDesignExpression) e;
		expr = createExpression(expr);
		if (value instanceof JRDesignExpression) {
			text = ((JRDesignExpression) value).getText();
			expr.setValueClassName(((JRDesignExpression) value).getValueClassName());
		} else if (value instanceof MExpression) {
			expr = (JRDesignExpression) ((MExpression) value).getValue();
			if (expr == null)
				return null;
			text = expr.getText();
			expr.setValueClassName(expr.getValueClassName());
		} else if (value instanceof String) {
			text = (String) value;
		}
		expr.setText(text);
		return expr;
	}

	private static JRDesignExpression createExpression(JRDesignExpression expr) {
		if (expr == null) {
			expr = new JRDesignExpression();
			expr.setValueClassName(String.class.getName());
		}
		return expr;
	}

	public static MExpression getExpression(ANode n, MExpression m, JRExpression jrExpression) {
		if (m == null) {
			m = new MExpression(null);
			n.setChildListener(m);
		}
		m.setValue(jrExpression);
		return m;
	}
}
