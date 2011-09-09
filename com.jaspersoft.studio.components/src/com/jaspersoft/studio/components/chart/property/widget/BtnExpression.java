package com.jaspersoft.studio.components.chart.property.widget;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.ExpressionWidget;
import com.jaspersoft.studio.property.section.AbstractSection;

public class BtnExpression {
	private ExpressionWidget expr;

	public BtnExpression(Composite parent, AbstractSection section,
			String property) {
		createComponent(parent, section, property);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property) {
		expr = new ExpressionWidget(parent, null) {
			@Override
			protected void setOnParent(JRDesignExpression exp) {
				section.changeProperty(property, exp != null ? exp.clone()
						: null);
			}
		};
	}

	public void setData(JRDesignExpression b) {
		expr.setExpression(b);
	}
}
