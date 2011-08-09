package com.jaspersoft.studio.utils.parameter;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRValueParameter;

public class ParameterUtil {

	public static void setParameters(JRDataset dataset, Map<String, Object> inmap) {
		for (JRParameter p : dataset.getParameters()) {
			if (!p.isSystemDefined() && p.isForPrompting() && p.getDefaultValueExpression() == null) {
				Object val;
				try {
					val = p.getValueClass().newInstance();
					inmap.put(p.getName(), val);
				} catch (InstantiationException e) {
					inmap.put(p.getName(), getDefaultInstance(p));
				} catch (IllegalAccessException e) {
					inmap.put(p.getName(), getDefaultInstance(p));
				}

			}
		}
	}

	private static Object getDefaultInstance(JRParameter p) {
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
