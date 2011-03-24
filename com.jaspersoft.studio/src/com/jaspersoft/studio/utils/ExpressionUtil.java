package com.jaspersoft.studio.utils;

import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRExpressionUtil;

import org.eclipse.jdt.core.IJavaProject;

public class ExpressionUtil {
	public static final String eval(JRExpression expr, JasperDesign jd) {
		if (jd == null)
			return JRExpressionUtil.getSimpleExpressionText(expr);

		return eval(expr, jd, jd.getMainDesignDataset(), null).toString();
	}

	public static final Object eval(JRExpression expr, JasperDesign jd, JRDataset jrd, IJavaProject javaProject) {
		ClassLoader classLoader = null;
		if (javaProject != null)
			classLoader = new JavaProjectClassLoader(javaProject);
		else if (jd != null)
			classLoader = jd.getClass().getClassLoader();
		else
			classLoader = Thread.currentThread().getContextClassLoader();
		ExpressionInterpreter ei = new ExpressionInterpreter(jd.getMainDesignDataset(), classLoader, jd, javaProject);
		return ei.interpretExpression(expr.getText());
	}
}
