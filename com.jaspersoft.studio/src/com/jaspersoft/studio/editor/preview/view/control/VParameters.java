/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.input.BooleanNumericInput;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ParameterJasper;
import com.jaspersoft.studio.editor.preview.input.PropertyChangeNotifier;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.execution.InputControlsPreferencePage;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class VParameters extends AVParameters {

	private PropertyChangeNotifier propertyChangeNotifier;
	
	public VParameters(Composite parent, JasperReportsConfiguration jContext, PropertyChangeNotifier propertyChangeNotifier) {
		super(parent, jContext);
		this.propertyChangeNotifier = propertyChangeNotifier;
	}
	
	protected void createInputControls(List<JRParameter> prompts, Map<String, Object> params) {
		this.params = params;
		this.prompts = prompts;
		Map<String, Boolean> dirtyMap = new HashMap<String, Boolean>();
		for (String key : incontrols.keySet()){
			IDataInput control =  incontrols.get(key);
			dirtyMap.put(key, control.isDirty());
			propertyChangeNotifier.removeDataInput(control);
		}
		incontrols.clear();
		for (Control c : composite.getChildren())
			c.dispose();
		
		boolean first = true;
		if (prompts != null)
			for (JRParameter p : prompts)
				if (isParameterToShow(p)) {
					try {
						boolean created = createInput(composite, (JRDesignParameter) p, this.params, first);
						if (first && created) {
							first = false;
							Boolean b = dirtyMap.get(p.getName());
							if (b != null)
								incontrols.get(p.getName()).setDirty(b);
						}
					} catch (Exception e) {
						if (!(e instanceof ClassNotFoundException))
							e.printStackTrace();
					}
				}
		if (defaultJob != null)
			defaultJob.cancel();
		if (defaultNonDirtyJob != null)
			defaultNonDirtyJob.cancel();
		if (showEmptyParametersWarning) {
			// setupDefaultValues();
			setDirty(false);
		} else
			;// setupDefaultValuesNonDirty();
		showEmptyParametersWarning = false;
		
		refreshControl();
	}

	protected boolean isSystem = false;

	public void setupDefaultValuesNonDirty() {
		defaultNonDirtyJob = new Job(Messages.VParameters_calculate_default_values) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(Messages.VParameters_resetparameters, IProgressMonitor.UNKNOWN);
				ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(jContext.getClassLoader());
					JRDesignDataset mDataset = (JRDesignDataset) jContext.getJasperDesign().getMainDataset();
					Set<String> keys = new HashSet<String>();
					for (String pname : new HashSet<String>(incontrols.keySet())) {
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						JRParameter p = mDataset.getParametersMap().get(pname);
						if (p == null || (!isSystem && p.isSystemDefined()) || (isSystem && !p.isSystemDefined()))
							continue;
						if (p.getName().equals(pname)) {
							if (params.get(pname) != null)
								continue;
							if (p.getDefaultValueExpression() != null)
								params.put(pname, ExpressionUtil.cachedExpressionEvaluation(p.getDefaultValueExpression(), jContext));
							else
								params.remove(pname);
							keys.add(pname);
						}
					}
					updateControlInput(keys);
				} finally {
					monitor.done();
					Thread.currentThread().setContextClassLoader(oldCL);
				}
				return Status.OK_STATUS;
			}
		};
		defaultNonDirtyJob.setPriority(Job.SHORT);
		defaultNonDirtyJob.schedule();
	}

	public void setupDefaultValues() {
		defaultJob = new Job(Messages.VParameters_calculate_default_values) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(Messages.VParameters_resetparameters, IProgressMonitor.UNKNOWN);
				ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(jContext.getClassLoader());
					JRDesignDataset mDataset = (JRDesignDataset) jContext.getJasperDesign().getMainDataset();
					Set<String> keys = new HashSet<String>();
					for (String pname : new HashSet<String>(incontrols.keySet())) {
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						JRParameter p = mDataset.getParametersMap().get(pname);
						if (p == null || (!isSystem && p.isSystemDefined()) || (isSystem && !p.isSystemDefined()))
							continue;
						if (p.getName().equals(pname)) {
							if (p.getDefaultValueExpression() != null)
								params.put(pname, ExpressionUtil.cachedExpressionEvaluation(p.getDefaultValueExpression(), jContext));
							else
								params.remove(pname);
							keys.add(pname);
						}
					}
					updateControlInput(keys);
				} finally {
					monitor.done();
					Thread.currentThread().setContextClassLoader(oldCL);
				}
				return Status.OK_STATUS;
			}
		};
		defaultJob.setPriority(Job.SHORT);
		defaultJob.schedule();
	}

	private void updateControlInput(final Set<String> keys) {
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				for (String pname : keys) {
					IDataInput di = incontrols.get(pname);
					if (di != null)
						di.updateInput();
				}
			}
		});
	}

	public void update() {
		for (IDataInput di : incontrols.values()) {
			di.updateInput();
		}
	}

	protected boolean isParameterToShow(JRParameter p) {
		return p.isForPrompting() && !p.isSystemDefined();
	}

	private Map<String, Object> params;

	private List<JRParameter> prompts;

	private Job defaultNonDirtyJob;

	private Job defaultJob;

	public boolean checkFieldsFilled() {
		String show = jContext.getProperty(InputControlsPreferencePage.JSS_IC_SHOW, InputControlsPreferencePage.ALWAYS);
		if (show.equals(InputControlsPreferencePage.NEVER))
			return true;
		boolean defaultExists = false;
		int count = 0;
		if (prompts != null)
			for (JRParameter p : prompts) {
				String pname = p.getName();
				if (p.isForPrompting() && !p.isSystemDefined() && incontrols.containsKey(pname)) {
					if (p.getDefaultValueExpression() != null)
						defaultExists = true;
					count++;
					if ((params.containsKey(pname) || incontrols.get(pname).isRemoved()) && incontrols.get(pname).isDirty())
						return true;
				}
			}
		if (count > 0) {
			if (defaultExists && show.equals(InputControlsPreferencePage.ALL_EMPTY))
				return true;
			return false;
		}
		return true;
	}

	private void createControl(Composite sectionClient, ParameterJasper pres, final IDataInput in, JRDesignParameter p,boolean first) {
		incontrols.put(p.getName(), in);
		createVerticalSeprator(first);
		createLabel(sectionClient, pres, in);
		in.createInput(sectionClient, pres, params);
		propertyChangeNotifier.addDataInput(in);
	}

	protected boolean createInput(Composite sectionClient, JRDesignParameter p, Map<String, Object> params, boolean first)
			throws ClassNotFoundException {
		ParameterJasper pres = new ParameterJasper(p, jContext);
		// Use a custom control for the report maxcount instead of the integer standard one
		if (p.getName().equals(JRParameter.REPORT_MAX_COUNT)) {
			createControl(sectionClient, pres, new BooleanNumericInput(), p, first);
			return true;
		}
		for (IDataInput in : ReportController.inputs) {
			if (in.isForType(pres.getValueClass())) {
				final IDataInput input = in.getInstance();
				createControl(sectionClient, pres, input, p, first);		
				return true;
			}
		}
		return false;
	}

}
