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
package com.jaspersoft.studio.server.editor.input.query;

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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.InputControlQueryDataRow;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.server.editor.input.IInput;

public class TableInput implements IInput {
	protected Table table;
	private QueryInput dataInput;
	private Map<String, Object> params;
	private IParameter param;

	public TableInput(QueryInput dataInput, IParameter param,
			Map<String, Object> params) {
		this.dataInput = dataInput;
		this.param = param;
		this.params = params;
	}

	public void createControl(Composite parent, int style) {
		createTable(parent, style);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		gd.minimumHeight = 100;
		table.setLayoutData(gd);
		fillControl();

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (dataInput.getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
						|| dataInput.getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
					List<Object> lst = new ArrayList<Object>();
					for (TableItem item : ti)
						lst.add(item.getData());
					dataInput.updateModel(lst);
				} else
					dataInput.updateModel(ti.length > 0 ? ti[0].getData()
							: null);
			}
		};
		table.addSelectionListener(listener);
		updateInput();
		listener.widgetSelected(null);
	}

	protected void createTable(Composite parent, int style) {
		table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(false);
	}

	public void fillControl() {
		table.select(-1);
		table.removeAll();

		List<InputControlQueryDataRow> qvalues = dataInput.getRD()
				.getQueryData();
		String[] qcolumns = dataInput.getRD().getQueryVisibleColumns();

		for (TableColumn tc : table.getColumns())
			tc.dispose();

		for (String c : qcolumns) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(c);
		}

		for (InputControlQueryDataRow item : qvalues) {
			TableItem ti = new TableItem(table, SWT.NONE);
			List<String> cvals = item.getColumnValues();
			for (int i = 0; i < cvals.size(); i++) {
				ti.setText(i, cvals.get(i));
				ti.setData(item.getValue());
			}
		}

		for (TableColumn tc : table.getColumns())
			tc.pack();

		if (qvalues.size() > 4)
			((GridData) table.getLayoutData()).heightHint = 100;

		getControl().getParent().layout();
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null) {
			if (dataInput.getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
					|| dataInput.getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
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
					if (ti.getData().equals(value))
						table.setSelection(ti);
				}
		}
	}

	public Control getControl() {
		return table;
	}

}
