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
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ParameterJasper;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.UIUtils;

public class VParameters extends APreview {

	protected Composite composite;
	protected ScrolledComposite scompo;

	public VParameters(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected Control createControl(Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setMinWidth(100);
		scompo.setAlwaysShowScrollBars(false);

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

	public void createInputControls(List<JRParameter> prompts, Map<String, Object> params) {
		this.params = params;
		this.prompts = prompts;
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		for (Control c : composite.getChildren()) {
			c.dispose();
		}
		for (JRParameter p : prompts)
			if (p.isForPrompting() && !p.isSystemDefined()) {
				createInput(composite, (JRDesignParameter) p, params);
			}

		scompo.setMinSize(composite.getSize());
		composite.pack();
	}

	private Map<String, Object> params;
	private List<JRParameter> prompts;

	public boolean checkFieldsFilled() {
		int count = 0;
		for (JRParameter p : prompts)
			if (p.isForPrompting() && !p.isSystemDefined() && haveWidget4Type(p)) {
				count++;
				if (params.containsKey(p.getName())) {
					return true;
				}
			}
		if (count > 0)
			return false;
		return true;
	}

	protected boolean haveWidget4Type(JRParameter p) {
		for (IDataInput in : ReportControler.inputs) {
			if (in.isForType(p.getValueClass())) {
				return true;
			}
		}
		return false;
	}

	protected void createInput(Composite sectionClient, JRDesignParameter p, Map<String, Object> params) {
		ParameterJasper pres = new ParameterJasper(p);
		for (IDataInput in : ReportControler.inputs) {
			if (in.isForType(pres.getValueClass())) {
				in = in.getInstance();
				if (!in.isLabeled())
					UIUtils.createLabel(sectionClient, Messages.getString(pres.getLabel()));
				in.createInput(sectionClient, pres, params);
				break;
			}
		}
	}

}
