/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.editor.preview.inputs.BigNumericInput;
import com.jaspersoft.studio.editor.preview.inputs.BooleanInput;
import com.jaspersoft.studio.editor.preview.inputs.DateInput;
import com.jaspersoft.studio.editor.preview.inputs.IDataInput;
import com.jaspersoft.studio.editor.preview.inputs.ImageInput;
import com.jaspersoft.studio.editor.preview.inputs.LocaleInput;
import com.jaspersoft.studio.editor.preview.inputs.NumericInput;
import com.jaspersoft.studio.editor.preview.inputs.TextInput;
import com.jaspersoft.studio.editor.preview.inputs.TimeZoneInput;
import com.jaspersoft.studio.messages.Messages;

public class ParametersDialog extends FormDialog {
	private static List<IDataInput> inputs = new ArrayList<IDataInput>();
	static {
		inputs.add(new BooleanInput());
		inputs.add(new TextInput());
		inputs.add(new LocaleInput());
		inputs.add(new TimeZoneInput());
		inputs.add(new NumericInput());
		inputs.add(new BigNumericInput());
		inputs.add(new DateInput());
		inputs.add(new ImageInput());
	}
	private List<JRParameter> prompts;
	private Map<String, Object> params;
	private JasperDesign jDesign;

	public ParametersDialog(Shell shell, JasperDesign jDesign, Map<String, Object> params) {
		super(shell);
		this.jDesign = jDesign;
		this.prompts = jDesign.getParametersList();
		this.params = params;
		shell.setText("Parameters");
	}

	public Map<String, Object> getParameters() {
		return params;
	}

	public boolean canShowParameters() {
		for (JRParameter p : prompts) {
			if (p.isForPrompting() && !p.isSystemDefined())
				for (IDataInput in : inputs)
					try {
						if (in.isForType(p.getValueClass())) {
							return true;
						}
					} catch (JRRuntimeException e) {
						e.printStackTrace();
					}
		}
		return false;
	}

	@Override
	protected void createFormContent(final IManagedForm mform) {
		getShell().setText(Messages.ParametersDialog_report_parameters);
		mform.getForm().setText(Messages.ParametersDialog_report_parameters);
		FormToolkit toolkit = mform.getToolkit();

		Composite body = mform.getForm().getBody();
		body.setLayout(new GridLayout());

		CTabFolder tabFolder = new CTabFolder(body, SWT.BOTTOM);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 700;
		gd.heightHint = 500;
		tabFolder.setLayoutData(gd);
		tabFolder.setBackground(body.getBackground());

		createUserParam(toolkit, tabFolder);

		createBParam(toolkit, tabFolder);

		new SortFieldSection(prompts, params, jDesign).createSortField(toolkit, tabFolder);
	}

	private void createBParam(FormToolkit toolkit, CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ParametersDialog_built_in_parameters);

		ScrolledComposite scompo = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.H_SCROLL);
		scompo.setExpandHorizontal(true);
		scompo.setExpandVertical(true);

		Composite sectionClient = toolkit.createComposite(scompo);
		sectionClient.setLayout(new GridLayout(2, false));

		for (JRParameter p : prompts)
			if (!p.isForPrompting() || p.isSystemDefined())
				createInput(toolkit, sectionClient, (JRDesignParameter) p);
		sectionClient.pack();
		scompo.setMinSize(sectionClient.getSize());
		scompo.setContent(sectionClient);
		bptab.setControl(scompo);
	}

	private void createUserParam(FormToolkit toolkit, CTabFolder tabFolder) {
		if (canShowParameters()) {
			CTabItem ptab = new CTabItem(tabFolder, SWT.NONE);
			ptab.setText(Messages.ParametersDialog_user_parameters);

			ScrolledComposite scompo = new ScrolledComposite(tabFolder, SWT.V_SCROLL | SWT.H_SCROLL);
			scompo.setExpandHorizontal(true);
			scompo.setExpandVertical(true);

			Composite sectionClient = toolkit.createComposite(scompo);
			sectionClient.setLayout(new GridLayout(2, false));

			for (JRParameter p : prompts)
				if (p.isForPrompting() && !p.isSystemDefined())
					createInput(toolkit, sectionClient, (JRDesignParameter) p);
			sectionClient.pack();
			scompo.setMinSize(sectionClient.getSize());
			scompo.setContent(sectionClient);
			ptab.setControl(scompo);
		}
	}

	private void createInput(FormToolkit toolkit, Composite sectionClient, JRDesignParameter p) {
		for (IDataInput in : inputs) {
			if (in.isForType(p.getValueClass())) {
				Label lbl = toolkit.createLabel(sectionClient, Messages.getString(p.getName()) + ":", SWT.RIGHT); //$NON-NLS-1$
				// lbl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				in.createInput(sectionClient, p, p.getValueClass(), params);
				break;
			}
		}
	}

}
