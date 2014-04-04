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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.input.BooleanNumericInput;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ParameterJasper;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
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
		} else
			setupDefaultValuesNonDirty();
		showEmptyParametersWarning = false;
	}

	protected boolean isSystem = false;

	public void setupDefaultValuesNonDirty() {
		Job job = new Job("Calculating Default Values") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				JRDesignDataset mDataset = (JRDesignDataset) jContext.getJasperDesign().getMainDataset();
				Set<String> keys = new HashSet<String>();
				for (String pname : incontrols.keySet()) {
					JRParameter p = mDataset.getParametersMap().get(pname);
					if (p == null || (!isSystem && p.isSystemDefined()) || (isSystem && !p.isSystemDefined()))
						continue;
					if (p.getName().equals(pname)) {
						if (params.get(pname) != null)
							continue;
						if (p.getDefaultValueExpression() != null) {
							params.put(pname, getInter(mDataset).interpretExpression(p.getDefaultValueExpression().getText()));
						} else
							params.put(pname, null);
						keys.add(pname);
					}

				}
				updateControlInput(keys);
				return Status.OK_STATUS;
			}

			private ExpressionInterpreter eint;

			private ExpressionInterpreter getInter(JRDesignDataset mDataset) {
				if (eint == null)
					eint = ExpressionUtil.getInterpreter(mDataset, jContext, jContext.getJasperDesign());
				return eint;
			}

		};
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	public void setupDefaultValues() {
		Job job = new Job("Calculating Default Values") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				JRDesignDataset mDataset = (JRDesignDataset) jContext.getJasperDesign().getMainDataset();
				Set<String> keys = new HashSet<String>();
				for (String pname : incontrols.keySet()) {
					JRParameter p = mDataset.getParametersMap().get(pname);
					if (p == null || (!isSystem && p.isSystemDefined()) || (isSystem && !p.isSystemDefined()))
						continue;
					if (p.getName().equals(pname)) {
						if (p.getDefaultValueExpression() != null) {
							params.put(pname, getInter(mDataset).interpretExpression(p.getDefaultValueExpression().getText()));
						} else
							params.put(pname, null);
						keys.add(pname);
					}

				}
				updateControlInput(keys);
				return Status.OK_STATUS;
			}

			private ExpressionInterpreter eint;

			private ExpressionInterpreter getInter(JRDesignDataset mDataset) {
				if (eint == null)
					eint = ExpressionUtil.getInterpreter(mDataset, jContext, jContext.getJasperDesign());
				return eint;
			}

		};
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	private void updateControlInput(final Set<String> keys) {
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				for (String pname : keys)
					incontrols.get(pname).updateInput();
			}
		});
	}

	protected boolean isParameterToShow(JRParameter p) {
		return p.isForPrompting() && !p.isSystemDefined();
	}

	private Map<String, Object> params;

	private List<JRParameter> prompts;

	public boolean checkFieldsFilled() {
		int count = 0;
		if (prompts != null)
			for (JRParameter p : prompts) {
				String pname = p.getName();
				if (p.isForPrompting() && !p.isSystemDefined() && incontrols.containsKey(pname)) {
					count++;
					if (params.containsKey(pname) && incontrols.get(pname).isDirty())
						return true;
				}
			}
		if (count > 0)
			return false;
		return true;
	}

	private void createControl(Composite sectionClient, ParameterJasper pres, IDataInput in, JRDesignParameter p,
			boolean first) {
		incontrols.put(p.getName(), in);
		createVerticalSeprator(first);
		createLabel(sectionClient, pres, in);
		in.createInput(sectionClient, pres, params);
	}

	protected boolean createInput(Composite sectionClient, JRDesignParameter p, Map<String, Object> params, boolean first)
			throws ClassNotFoundException {
		ParameterJasper pres = new ParameterJasper(p);
		// Use a custom control for the report maxcount instead of the integer standard one
		if (p.getName().equals(JRParameter.REPORT_MAX_COUNT)) {
			createControl(sectionClient, pres, new BooleanNumericInput(), p, first);
			return true;
		}
		for (IDataInput in : ReportControler.inputs) {
			if (in.isForType(pres.getValueClass())) {
				createControl(sectionClient, pres, in.getInstance(), p, first);
				return true;
			}
		}
		return false;
	}

}
