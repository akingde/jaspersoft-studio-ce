/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.view.control.AVParameters;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.server.editor.input.lov.ListOfValuesInput;
import com.jaspersoft.studio.server.editor.input.query.QueryInput;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VInputControls extends AVParameters {

	public static List<IDataInput> inputs = new ArrayList<IDataInput>();
	static {
		inputs.add(new DateInput());
		inputs.add(new ListOfValuesInput());
		inputs.add(new QueryInput());
		inputs.addAll(ReportControler.inputs);
	}

	private InputControlsManager icm;
	private ResourceDescriptor rdrepunit;

	public VInputControls(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	public void setReportUnit(ResourceDescriptor rdrepunit) {
		this.rdrepunit = rdrepunit;
	}

	public void createInputControls(InputControlsManager icm) {
		this.icm = icm;
		for (IDataInput di : icm.getControls())
			di.dispose();
		icm.getControls().clear();
		for (Control c : composite.getChildren())
			c.dispose();
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
			setupDefaultValues();
			setDirty(false);
		}
		showEmptyParametersWarning = false;
	}

	public void setupDefaultValues() {
		// we should set default values
	}

	public boolean checkFieldsFilled() {
		if (icm.isAnyVisible()) {
			boolean rAlwaysPrompt = rdrepunit.getResourcePropertyValueAsBoolean(ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS);

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
					in.addChangeListener(icm.getPropertyChangeListener());
					icm.getControls().add(in);
					return true;
				}
			}
		return false;
	}
}
