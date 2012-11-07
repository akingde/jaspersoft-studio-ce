/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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
		inputs.addAll(ReportControler.inputs);
		inputs.add(new ListOfValuesInput());
		inputs.add(new QueryInput());
	}

	private InputControlsManager icm;

	public VInputControls(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
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
			for (ResourceDescriptor p : icm.getInputControls())
				if (p.isMandatory() && p.isVisible() && !p.isReadOnly()
						&& icm.getParameters().get(p.getName()) == null)
					return false;
		}
		return true;
	}

	protected boolean createInput(Composite sectionClient,
			ResourceDescriptor p, InputControlsManager icm, boolean first) {
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
