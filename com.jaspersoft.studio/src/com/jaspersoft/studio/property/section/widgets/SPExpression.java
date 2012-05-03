/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.dataset.ExpressionWidget;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPExpression {
	private ExpressionWidget expr;

	public SPExpression(Composite parent, AbstractSection section, String property) {
		createComponent(parent, section, property);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property) {
		expr = new ExpressionWidget(parent, null) {
			@Override
			protected void setOnParent(JRDesignExpression exp) {
				section.changeProperty(property, exp != null ? exp.clone() : null);
			}
		};
	}

	public void setData(JRDesignExpression b) {
		expr.setExpression(b);
	}

	public void setEnabled(boolean enabled) {
		expr.setEnabled(enabled);
	}
	
	public boolean isEnabled(){
		return expr.isEnabled();
	}
	
	public void setExpressionContext(ExpressionContext exprContext){
		expr.setExpressionContext(exprContext);
	}
}
