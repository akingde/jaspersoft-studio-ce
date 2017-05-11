/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.debug;

import java.util.HashMap;
import java.util.Map;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactoryContext;

/**
 * @author Veaceslav Chicu (schicu@tibco.com)
 */
public class TraceGovernor extends JRDefaultScriptlet {
	public static final String PREFIX = "com.jaspersoft.studio.trace.";

	public static final String TYPE_FIELD = "field.";
	public static final String TYPE_VARIABLE = "variable.";
	public static final String TYPE_PARAMETER = "parameter.";

	public static final String AFTER_DETAIL_EVAL = "afterDetailEval.";
	public static final String BEFORE_DETAIL_EVAL = "beforeDetailEval.";
	public static final String AFTER_GROUP_INIT = "afterGroupInit.";
	public static final String BEFORE_GROUP_INIT = "beforeGroupInit.";
	public static final String AFTER_COLUMN_INIT = "afterColumnInit.";
	public static final String BEFORE_COLUMN_INIT = "beforeColumnInit.";
	public static final String AFTER_PAGE_INIT = "afterPageInit.";
	public static final String BEFORE_PAGE_INIT = "beforePageInit.";
	public static final String AFTER_REPORT_INIT = "afterReportInit.";
	public static final String BEFORE_REPORT_INIT = "beforeReportInit.";
	private ScriptletFactoryContext context;
	private JasperReportsConfiguration jrConfig;

	public TraceGovernor(ScriptletFactoryContext context) {
		this.context = context;
		if (context.getJasperReportsContext() instanceof JasperReportsConfiguration)
			this.jrConfig = (JasperReportsConfiguration) context.getJasperReportsContext();
	}

	private void show(String level, Map<String, Boolean> map) {
		if (jrConfig == null || !trace)
			return;

		String trace = "";
		JRDataset dataset = context.getDataset();
		for (JRField f : dataset.getFields()) {
			String name = f.getName();
			if (map.get(PREFIX + TYPE_FIELD + dataset.getName() + "." + level + name))
				try {
					trace += "Field [" + name + "] " + getFieldValue(name) + "\n";
				} catch (JRScriptletException e) {
					e.printStackTrace();
				}
		}
		for (JRVariable v : dataset.getVariables()) {
			String name = v.getName();
			if (map.get(PREFIX + TYPE_VARIABLE + dataset.getName() + "." + level + name))
				try {
					trace += "Variable [" + name + "] " + getVariableValue(name) + "\n";
				} catch (JRScriptletException e) {
					e.printStackTrace();
				}
		}
		for (JRParameter p : dataset.getParameters()) {
			String name = p.getName();
			if (map.get(PREFIX + TYPE_PARAMETER + dataset.getName() + "." + level + name))
				try {
					trace += "Parameter [" + name + "] " + getParameterValue(name) + "\n";
				} catch (JRScriptletException e) {
					e.printStackTrace();
				}
		}
		if (!Misc.isNullOrEmpty(trace))
			System.out.println("\n" + trace);
	}

	private boolean trace = false;
	private Map<String, Boolean> beforeRepInit = new HashMap<String, Boolean>();
	private Map<String, Boolean> afterRepInit = new HashMap<String, Boolean>();

	private Map<String, Boolean> beforePageInit = new HashMap<String, Boolean>();
	private Map<String, Boolean> afterPageInit = new HashMap<String, Boolean>();

	private Map<String, Boolean> beforeColInit = new HashMap<String, Boolean>();
	private Map<String, Boolean> afterColInit = new HashMap<String, Boolean>();

	private Map<String, Boolean> beforeGroupInit = new HashMap<String, Boolean>();
	private Map<String, Boolean> afterGroupInit = new HashMap<String, Boolean>();

	private Map<String, Boolean> beforeDetEval = new HashMap<String, Boolean>();
	private Map<String, Boolean> afterDetEval = new HashMap<String, Boolean>();

	private void setup(Map<String, Boolean> map, String type, String level, String name) {
		JRDataset dataset = context.getDataset();
		String key = PREFIX + type + dataset.getName() + "." + level + name;
		Boolean b = jrConfig.getPropertyBoolean(key, false);
		if (b)
			trace = true;
		map.put(key, b);
	}

	private void initMaps(String type, String name) {
		setup(beforeRepInit, type, BEFORE_REPORT_INIT, name);
		setup(afterRepInit, type, AFTER_REPORT_INIT, name);

		setup(beforePageInit, type, BEFORE_PAGE_INIT, name);
		setup(afterPageInit, type, AFTER_PAGE_INIT, name);

		setup(beforeColInit, type, BEFORE_COLUMN_INIT, name);
		setup(afterColInit, type, AFTER_COLUMN_INIT, name);

		if (context.getDataset().getGroups() != null)
			for (JRGroup gr : context.getDataset().getGroups()) {
				String n = gr.getName();
				setup(beforeGroupInit, type, BEFORE_GROUP_INIT + n + ".", name);
				setup(afterGroupInit, type, AFTER_GROUP_INIT + n + ".", name);
			}

		setup(beforeDetEval, type, BEFORE_DETAIL_EVAL, name);
		setup(afterDetEval, type, AFTER_DETAIL_EVAL, name);
	}

	private void resetMaps() {
		trace = false;
		beforeRepInit.clear();
		afterRepInit.clear();

		beforePageInit.clear();
		afterPageInit.clear();

		beforeColInit.clear();
		afterColInit.clear();

		beforeGroupInit.clear();
		afterGroupInit.clear();

		beforeDetEval.clear();
		afterDetEval.clear();
	}

	@Override
	public void beforeReportInit() throws JRScriptletException {
		resetMaps();
		JRField[] fields = context.getDataset().getFields();
		if(fields!=null) {
			for (JRField f : fields)
				initMaps(TYPE_FIELD, f.getName());
		}
		JRVariable[] variables = context.getDataset().getVariables();
		if(variables!=null){
			for (JRVariable v : variables)
				initMaps(TYPE_VARIABLE, v.getName());
		}
		JRParameter[] parameters = context.getDataset().getParameters();
		if(parameters!=null) {
			for (JRParameter p : parameters)
				initMaps(TYPE_PARAMETER, p.getName());
		}
		show(BEFORE_REPORT_INIT, beforeRepInit);
	}

	@Override
	public void afterReportInit() throws JRScriptletException {
		show(AFTER_REPORT_INIT, afterRepInit);
	}

	@Override
	public void beforePageInit() throws JRScriptletException {
		show(BEFORE_PAGE_INIT, beforePageInit);
	}

	@Override
	public void afterPageInit() throws JRScriptletException {
		show(AFTER_PAGE_INIT, afterPageInit);
	}

	@Override
	public void beforeColumnInit() throws JRScriptletException {
		show(BEFORE_COLUMN_INIT, beforeColInit);
	}

	@Override
	public void afterColumnInit() throws JRScriptletException {
		show(AFTER_COLUMN_INIT, afterColInit);
	}

	@Override
	public void beforeGroupInit(String groupName) throws JRScriptletException {
		show(BEFORE_GROUP_INIT + groupName + ".", beforeGroupInit);
	}

	@Override
	public void afterGroupInit(String groupName) throws JRScriptletException {
		show(AFTER_GROUP_INIT + groupName + ".", afterGroupInit);
	}

	@Override
	public void beforeDetailEval() throws JRScriptletException {
		show(BEFORE_DETAIL_EVAL, beforeDetEval);
	}

	@Override
	public void afterDetailEval() throws JRScriptletException {
		show(AFTER_DETAIL_EVAL, afterDetEval);
	}
}
