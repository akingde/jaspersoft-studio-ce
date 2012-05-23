package com.jaspersoft.studio.server.publish;

import net.sf.jasperreports.engine.design.JRDesignExpression;

public class PublishOptions {
	private boolean isOverwrite = true;
	private JRDesignExpression jExpression;
	private String expression;

	public boolean isOverwrite() {
		return isOverwrite;
	}

	public void setOverwrite(boolean isOverwrite) {
		this.isOverwrite = isOverwrite;
	}

	public JRDesignExpression getjExpression() {
		return jExpression;
	}

	public void setjExpression(JRDesignExpression jExpression) {
		this.jExpression = jExpression;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}
