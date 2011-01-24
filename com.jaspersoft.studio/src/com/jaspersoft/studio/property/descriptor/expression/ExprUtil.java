package com.jaspersoft.studio.property.descriptor.expression;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;

public class ExprUtil {
	public static JRExpression setValues(JRExpression e, Object value) {
		String text = "";
		JRDesignExpression expr = (JRDesignExpression) e;
		if (expr == null) {
			expr = new JRDesignExpression();
			expr.setValueClassName(String.class.getName());
		}
		if (value instanceof MExpression) {
			expr = (JRDesignExpression) ((MExpression) value).getValue();
			text = expr.getText();
			expr.setValueClassName(expr.getValueClassName());
		} else if (value instanceof String) {
			text = (String) value;
		}
		expr.setText(text);
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
