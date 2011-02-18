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
