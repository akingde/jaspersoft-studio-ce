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
package com.jaspersoft.studio.utils.parameter;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRValueParameter;

import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ParameterUtil {

	public static void setParameters(JasperReportsConfiguration jConfig, JRDataset dataset, Map<String, Object> inmap) {
		for (JRParameter p : dataset.getParameters()) {
			if (!p.isSystemDefined() && p.isForPrompting() && p.getDefaultValueExpression() != null) {
				Object val;
				try {
					val = p.getValueClass().newInstance();
					inmap.put(p.getName(), val);
				} catch (InstantiationException e) {
					inmap.put(p.getName(), getDefaultInstance(p, jConfig, dataset));
				} catch (IllegalAccessException e) {
					inmap.put(p.getName(), getDefaultInstance(p, jConfig, dataset));
				}
			}
		}
	}

	private static Object getDefaultInstance(JRParameter p, JasperReportsConfiguration jConfig, JRDataset dataset) {
		if (p.getDefaultValueExpression() != null) {
			return ExpressionUtil.eval(p.getDefaultValueExpression(), dataset, jConfig);
		}

		if (p.getValueClass().isAssignableFrom(Integer.class))
			return new Integer(0);
		if (p.getValueClass().isAssignableFrom(Byte.class))
			return new Byte("0");
		if (p.getValueClass().isAssignableFrom(Short.class))
			return new Short("0");
		if (p.getValueClass().isAssignableFrom(Float.class))
			return new Float(0);
		if (p.getValueClass().isAssignableFrom(Long.class))
			return new Long(0);
		if (p.getValueClass().isAssignableFrom(Double.class))
			return new Double(0);

		return "";
	}

	public static Map<String, JRValueParameter> convertMap(Map<String, ?> inmap) {
		Map<String, JRValueParameter> outmap = new HashMap<String, JRValueParameter>();
		for (String key : inmap.keySet())
			outmap.put(key, new SimpleValueParameter(inmap.get(key)));

		return outmap;
	}
}
