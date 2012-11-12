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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.server.editor.input.IInput;
import com.jaspersoft.studio.server.editor.input.PResourceDescriptor;
import com.jaspersoft.studio.utils.UIUtils;

public class TableInput implements IInput {
	private Table table;
	private ListOfValuesInput dataInput;
	private Map<String, Object> params;
	private IParameter param;

	public TableInput(ListOfValuesInput dataInput, IParameter param,
			Map<String, Object> params) {
		this.dataInput = dataInput;
		this.param = param;
		this.params = params;
	}

	public void createControl(Composite parent, int style) {
		table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		gd.minimumHeight = 100;
		table.setLayoutData(gd);
		fillControl();

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (dataInput.getRd().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
						|| dataInput.getRd().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
					List<Object> lst = new ArrayList<Object>();
					for (TableItem item : ti)
						lst.add(item.getData());
					dataInput.updateModel(lst);
				} else if (ti.length > 0) {
					dataInput.updateModel(ti[0].getData());
				}
			}
		};
		table.addSelectionListener(listener);
		updateInput();
		listener.widgetSelected(null);
	}

	public void fillControl() {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;

		ResourceDescriptor rd2 = (ResourceDescriptor) dataInput.getRd()
				.getChildren().get(0);
		List<ListItem> items = null;

		if (rd2.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE)) {
			ResourceDescriptor tmpRd = new ResourceDescriptor();
			tmpRd.setUriString(rd2.getReferenceUri());
			try {
				tmpRd = rdprm.getWsClient().get(tmpRd, null);
				items = tmpRd.getListOfValues();
			} catch (Exception ex) {
				UIUtils.showError(ex);
				return;
			}
		} else {
			items = rd2.getListOfValues();
		}

		table.removeAll();

		for (ListItem item : items) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(item.getLabel());
			ti.setData(item.getValue());
		}
		if (items.size() > 4)
			((GridData) table.getLayoutData()).heightHint = 100;

		table.getParent().layout();
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null) {
			if (dataInput.getRd().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
					|| dataInput.getRd().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
				if (value instanceof List) {
					List<TableItem> titems = new ArrayList<TableItem>();
					List<Object> lst = (List<Object>) value;
					for (TableItem ti : table.getItems())
						if (lst.contains(ti.getData()))
							titems.add(ti);
					table.setSelection(titems.toArray(new TableItem[titems
							.size()]));
				}
			} else
				for (TableItem ti : table.getItems()) {
					if (ti.getData().equals(value)) {
						table.setSelection(ti);
						break;
					}
				}
		}
	}

	public Control getControl() {
		return table;
	}

}
