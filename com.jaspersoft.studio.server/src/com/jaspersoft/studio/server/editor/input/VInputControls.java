/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.view.control.AVParameters;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.editor.input.lov.ListOfValuesInput;
import com.jaspersoft.studio.server.editor.input.query.QueryInput;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VInputControls extends AVParameters {

	public List<IDataInput> inputs = new ArrayList<IDataInput>();

	private InputControlsManager icm;
	private ResourceDescriptor rdrepunit;
	private String type = ResourceDescriptor.TYPE_REPORTUNIT;
	private String uri;
	private Set<Control> toIgnore = new HashSet<Control>();

	public VInputControls(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
		inputs.add(new DateInput());
		inputs.add(new ListOfValuesInput());
		inputs.add(new QueryInput());
		inputs.addAll(ReportControler.inputs);
	}

	public InputControlsManager getIcm() {
		return icm;
	}

	public String getUri() {
		return uri;
	}

	public String getType() {
		return type;
	}

	public Set<Control> getToIgnore() {
		return toIgnore;
	}

	public void setReportUnit(InputControlsManager icm, ResourceDescriptor rdrepunit, IProgressMonitor monitor) {
		this.rdrepunit = rdrepunit;
		this.icm = icm;
		uri = rdrepunit.getUriString();
		type = rdrepunit.getWsType();
		initICOptions(icm, rdrepunit, monitor);
	}

	private boolean hasOptions = false;

	public void createInputControls(InputControlsManager icm, IProgressMonitor monitor) {
		this.icm = icm;
		for (IDataInput di : icm.getControls())
			di.dispose();
		icm.getControls().clear();

		for (Control c : composite.getChildren()) {
			if (toIgnore.contains(c))
				continue;
			c.dispose();
		}
		if (!hasOptions) {
			createControl(composite, this);
			hasOptions = true;
		}
		boolean first = true;
		for (ResourceDescriptor p : icm.getInputControls())
			if (p.isVisible()) {
				try {
					boolean created = createInput(composite, p, icm, first);
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
			// setupDefaultValues();
			setDirty(false);
		}
		showEmptyParametersWarning = false;
	}

	public void setupDefaultValues(final IProgressMonitor monitor) throws Exception {
		monitor.subTask(Messages.VParameters_resetparameters);
		rdrepunit = icm.getWsClient().initInputControls(uri, type, monitor);
		setReportUnit(icm, rdrepunit, monitor);
		icm.initInputControls(rdrepunit);
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				createInputControls(icm, monitor);
			}
		});
	}

	public boolean checkFieldsFilled() {
		if (icm.isAnyVisible()) {
			Boolean rAlwaysPrompt = Misc.nvl(rdrepunit.getResourcePropertyValueAsBoolean(ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS), false);

			boolean hasDirty = false;
			for (ResourceDescriptor p : icm.getInputControls()) {
				String pname = p.getName();
				if (p.isVisible() && !p.isReadOnly() && incontrols.containsKey(pname)) {
					if (incontrols.get(pname).isDirty())
						hasDirty = true;
					if (p.isMandatory() && icm.getParameters().containsKey(pname) && !hasDirty)
						return false;
				}
			}
			if (rAlwaysPrompt && !hasDirty)
				return false;
		}
		return true;
	}

	protected boolean createInput(Composite sectionClient, ResourceDescriptor p, InputControlsManager icm, boolean first) {
		PResourceDescriptor pres = new PResourceDescriptor(p, icm);
		Class<?> vclass = pres.getValueClass();
		if (vclass != null)
			for (IDataInput in : inputs) {
				if (in.isForType(vclass)) {
					in = in.getInstance();
					incontrols.put(p.getName(), in);
					createVerticalSeprator(first);
					createLabel(sectionClient, pres, in);
					in.createInput(sectionClient, pres, icm.getParameters());
					if (InputControlsManager.isICSingle(p) && p.getValue() != null)
						in.updateModel(p.getValue());
					in.addChangeListener(icm.getPropertyChangeListener());
					icm.getControls().add(in);
					return true;
				}
			}
		return false;
	}

	public void updateInputControls(final IProgressMonitor monitor) throws Exception {
		final String icuri = getICContainerUri(uri);

		monitor.subTask(Messages.VInputControls_0);

		icm.initInputControls(icm.getWsClient().initInputControls(icuri, type, monitor));
		icm.getDefaults();
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				createInputControls(icm, monitor);
			}
		});

	}

	public void createControl(Composite composite, VInputControls icForm) {
		for (IInputControls r : getExtensionManager())
			r.createControl(composite, icForm);
	}

	public String getICContainerUri(String uri) {
		for (IInputControls r : getExtensionManager())
			uri = r.getICContainerUri(uri);
		return uri;
	}

	public void initICOptions(InputControlsManager icm, ResourceDescriptor rdrepunit, IProgressMonitor monitor) {
		for (IInputControls r : getExtensionManager())
			r.initICOptions(icm, rdrepunit, monitor);
	}

	private List<IInputControls> controls;

	private List<IInputControls> getExtensionManager() {
		if (controls == null)
			controls = Activator.getExtManager().getInstance();
		return controls;
	}

}
