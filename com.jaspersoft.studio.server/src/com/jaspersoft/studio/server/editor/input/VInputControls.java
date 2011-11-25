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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class VInputControls extends APreview {

	protected Composite composite;
	protected ScrolledComposite scompo;
	private InputControlsManager icm;

	public VInputControls(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected Control createControl(Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setMinWidth(100);

		composite = new Composite(scompo, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setBackground(parent.getBackground());

		scompo.setContent(composite);
		return scompo;
	}

	@Override
	public void setEnabled(boolean enabled) {
		scompo.setEnabled(enabled);
	}

	public void createInputControls(InputControlsManager icm) {
		this.icm = icm;
		for (Control c : composite.getChildren())
			c.dispose();

		for (ResourceDescriptor p : icm.getInputControls())
			if (p.isVisible()) {
				createInput(composite, p, icm);
			}

		scompo.setMinSize(composite.getSize());
		composite.pack();
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

	protected void createInput(Composite sectionClient, ResourceDescriptor p,
			InputControlsManager icm) {
		PResourceDescriptor pres = new PResourceDescriptor(p, icm.getWsClient());
		Class<?> vclass = pres.getValueClass();
		if (vclass != null)
			for (IDataInput in : ReportControler.inputs) {
				if (in.isForType(vclass)) {
					Label lbl = new Label(sectionClient, SWT.RIGHT);
					lbl.setText(Messages.getString(p.getName()) + ":"); //$NON-NLS-1$
					lbl.setBackground(sectionClient.getBackground());
					lbl.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_END));

					in.createInput(sectionClient, pres, icm.getParameters());
					break;
				}
			}
	}

}
