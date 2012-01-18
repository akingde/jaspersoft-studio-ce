/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.editor.input.lov;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.ADataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.server.editor.input.IInput;
import com.jaspersoft.studio.server.editor.input.PResourceDescriptor;

public class ListOfValuesInput extends ADataInput {

	private IInput iinput;
	private PResourceDescriptor rdprm;

	public ResourceDescriptor getRd() {
		return rdprm.getResourceDescriptor();
	}

	public boolean isForType(Class<?> valueClass) {
		return List.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param,
			final Map<String, Object> params) {
		super.createInput(parent, param, params);
		rdprm = (PResourceDescriptor) param;

		if (getRd().getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES) {
			iinput = new ListInput(this, param, params);
			iinput.createControl(parent, SWT.SINGLE);
		} else if (getRd().getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO) {
			iinput = new TableInput(this, param, params);
			iinput.createControl(parent, SWT.SINGLE | SWT.RADIO);
		} else if (getRd().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES) {
			iinput = new TableInput(this, param, params);
			iinput.createControl(parent, SWT.MULTI);
		} else if (getRd().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
			iinput = new TableInput(this, param, params);
			iinput.createControl(parent, SWT.MULTI | SWT.CHECK);
		} else
			return;

		setMandatory(param, iinput.getControl());

	}

	public void updateInput() {
		iinput.updateInput();
	}

	public void fillTable() {
		iinput.fillControl();
	}

}
