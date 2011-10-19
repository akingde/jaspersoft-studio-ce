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

package com.jaspersoft.studio.utils;

import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRExpressionUtil;

import org.eclipse.jdt.core.IJavaProject;

public class ExpressionUtil {
	public static final String eval(JRExpression expr, JasperDesign jd) {
		if (expr == null)
			return null;
		if (jd == null)
			return JRExpressionUtil.getSimpleExpressionText(expr);

		Object eval = eval(expr, jd, jd.getMainDesignDataset(), null);
		if (eval != null)
			return eval.toString();
		return null;
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
