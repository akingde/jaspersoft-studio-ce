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
package com.jaspersoft.studio.editor.preview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.editor.preview.inputs.DateInput;
import com.jaspersoft.studio.editor.preview.inputs.IDataInput;
import com.jaspersoft.studio.editor.preview.inputs.LocaleInput;
import com.jaspersoft.studio.editor.preview.inputs.NumericInput;
import com.jaspersoft.studio.editor.preview.inputs.TextInput;
import com.jaspersoft.studio.editor.preview.inputs.TimeZoneInput;
import com.jaspersoft.studio.messages.Messages;

public class ParametersDialog extends FormDialog {
	private static List<IDataInput> inputs = new ArrayList<IDataInput>();
	static {
		inputs.add(new TextInput());
		inputs.add(new LocaleInput());
		inputs.add(new TimeZoneInput());
		inputs.add(new NumericInput());
		inputs.add(new DateInput());
	}
	private List<JRDesignParameter> prompts;
	private Map<String, Object> params;

	public ParametersDialog(Shell shell, List<JRDesignParameter> prompts, Map<String, Object> params) {
		super(shell);
		this.prompts = prompts;
		this.params = params;
	}

	public Map<String, Object> getParameters() {
		return params;
	}

	public boolean canShowParameters() {
		for (JRDesignParameter p : prompts)
			for (IDataInput in : inputs)
				if (in.isForType(p.getValueClass())) {
					return true;
				}
		return false;
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		mform.getForm().setText(Messages.ParametersDialog_report_parameters);

		FormToolkit toolkit = mform.getToolkit();

		mform.getForm().getBody().setLayout(new GridLayout(2, false));

		for (JRDesignParameter p : prompts)
			for (IDataInput in : inputs) {
				if (in.isForType(p.getValueClass())) {
					toolkit.createLabel(mform.getForm().getBody(), p.getName() + ":", SWT.RIGHT);
					in.createInput(mform.getForm().getBody(), p, p.getValueClass(), params);
					break;
				}
			}
	}

}
