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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.editor.preview.input.ParameterJasper;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VParameters extends APreview {

	protected Composite composite;
	protected ScrolledComposite scompo;

	public VParameters(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected Control createControl(Composite parent) {
		scompo = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);
		scompo.setMinWidth(100);
		scompo.setMinHeight(100);
		scompo.setAlwaysShowScrollBars(false);

		composite = new Composite(scompo, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 2;
		composite.setLayout(layout);
		composite.setBackground(parent.getBackground());
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
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

		for (Control c : composite.getChildren())
			c.dispose();
		if (prompts != null)
			for (JRParameter p : prompts)
				if (p.isForPrompting() && !p.isSystemDefined()) {
					createInput(composite, (JRDesignParameter) p, this.params);
				}

		scompo.setMinSize(composite.getSize());
		composite.pack();
	}

	private Map<String, Object> params;
	private List<JRParameter> prompts;

	public boolean checkFieldsFilled() {
		int count = 0;
		if (prompts != null)
			for (JRParameter p : prompts)
				if (p.isForPrompting() && !p.isSystemDefined() && haveWidget4Type(p)) {
					count++;
					if (params.containsKey(p.getName()))
						return true;
				}
		if (count > 0)
			return false;
		return true;
	}

	protected boolean haveWidget4Type(JRParameter p) {
		for (IDataInput in : ReportControler.inputs) {
			if (in.isForType(p.getValueClass()))
				return true;
		}
		return false;
	}

	protected void createInput(Composite sectionClient, JRDesignParameter p, Map<String, Object> params) {
		ParameterJasper pres = new ParameterJasper(p);
		for (IDataInput in : ReportControler.inputs) {
			if (in.isForType(pres.getValueClass())) {
				in = in.getInstance();
				if (!in.isLabeled()) {
					Label lbl = new Label(sectionClient, SWT.WRAP);
					lbl.setText(Messages.getString(pres.getLabel()));
					lbl.setToolTipText(createToolTip(pres));
					GridData gd = new GridData(GridData.FILL_HORIZONTAL);
					gd.horizontalIndent = 8;
					lbl.setLayoutData(gd);
					UIUtils.setBold(lbl);
				}
				in.createInput(sectionClient, pres, params);

				Label lblsep = new Label(sectionClient, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_NONE);
				lblsep.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				break;
			}
		}
	}

	public static String createToolTip(IParameter param) {
		String desc = Misc.nvl(param.getDescription());
		if (param.getValueClass() != null)
			desc += "\nThe class type is:" + param.getValueClass().getCanonicalName();
		return desc;
	}

}
