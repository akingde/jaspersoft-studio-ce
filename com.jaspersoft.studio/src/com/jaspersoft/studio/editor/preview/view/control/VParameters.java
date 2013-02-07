/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ParameterJasper;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VParameters extends AVParameters {

	public VParameters(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	public void createInputControls(List<JRParameter> prompts, Map<String, Object> params) {
		this.params = params;
		this.prompts = prompts;
		incontrols.clear();
		for (Control c : composite.getChildren())
			c.dispose();
		boolean first = true;
		if (prompts != null)
			for (JRParameter p : prompts)
				if (isParameterToShow(p)) {
					try {
						boolean created = createInput(composite, (JRDesignParameter) p, this.params, first);
						if (first && created)
							first = false;
					} catch (Exception e) {
						if (!(e instanceof ClassNotFoundException))
							e.printStackTrace();
					}
				}
		composite.pack();
		setScrollbarMinHeight();
		if (showEmptyParametersWarning) {
			setupDefaultValues();
			setDirty(false);
		}
		showEmptyParametersWarning = false;
	}

	public void setupDefaultValues() {
		JRDataset mDataset = jContext.getJasperDesign().getMainDataset();
		for (String pname : incontrols.keySet()) {
			for (JRParameter p : prompts) {
				if (p.isSystemDefined())
					continue;
				if (p.getName().equals(pname)) {
					if (p.getDefaultValueExpression() != null) {
						params.put(pname, ExpressionUtil.eval(p.getDefaultValueExpression(), mDataset, jContext));
						incontrols.get(pname).updateInput();
					} else {
						params.put(pname, null);
						incontrols.get(pname).updateInput();
					}
					break;
				}
			}
		}
	}

	protected boolean isParameterToShow(JRParameter p) {
		return p.isForPrompting() && !p.isSystemDefined();
	}

	private Map<String, Object> params;

	private List<JRParameter> prompts;

	public boolean checkFieldsFilled() {
		int count = 0;
		if (prompts != null)
			for (JRParameter p : prompts)
				if (p.isForPrompting() && !p.isSystemDefined() && haveWidget4Type(p)) {
					count++;
					String pname = p.getName();
					if (params.containsKey(pname) && incontrols.get(pname).isDirty())
						return true;
				}
		if (count > 0)
			return false;
		return true;
	}

	protected boolean haveWidget4Type(JRParameter p) {
		return incontrols.containsKey(p.getName());
	}

	protected boolean createInput(Composite sectionClient, JRDesignParameter p, Map<String, Object> params, boolean first)
			throws ClassNotFoundException {
		ParameterJasper pres = new ParameterJasper(p);
		for (IDataInput in : ReportControler.inputs) {
			if (in.isForType(pres.getValueClass())) {
				in = in.getInstance();
				incontrols.put(p.getName(), in);
				createVerticalSeprator(first);
				createLabel(sectionClient, pres, in);
				in.createInput(sectionClient, pres, params);
				return true;
			}
		}
		return false;
	}

}
