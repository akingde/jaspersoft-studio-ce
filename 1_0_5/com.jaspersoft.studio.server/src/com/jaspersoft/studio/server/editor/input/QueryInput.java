package com.jaspersoft.studio.server.editor.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.InputControlQueryDataRow;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.ADataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;

public class QueryInput extends ADataInput {

	private Table table;
	private PResourceDescriptor rdprm;

	public boolean isForType(Class<?> valueClass) {
		return ResourceDescriptor.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param,
			final Map<String, Object> params) {
		super.createInput(parent, param, params);
		rdprm = (PResourceDescriptor) param;

		if (getRD().getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY) {
			createList(parent, SWT.SINGLE);
		} else if (getRD().getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY_RADIO) {
			createList(parent, SWT.SINGLE | SWT.RADIO);
		} else if (getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY) {
			createList(parent, SWT.MULTI);
		} else if (getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
			createList(parent, SWT.MULTI | SWT.CHECK);
		} else
			return;

		setMandatory(param, table);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
						|| getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
					List<Object> lst = new ArrayList<Object>();
					for (TableItem item : ti)
						lst.add(item.getData());
					updateModel(lst);
				} else
					updateModel(ti.length > 0 ? ti[0].getData() : null);
			}
		};
		table.addSelectionListener(listener);

		updateInput();

		listener.widgetSelected(null);
	}

	protected ResourceDescriptor getRD() {
		return rdprm.getResourceDescriptor();
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null) {
			if (getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
					|| getRD().getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
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

	private void createList(Composite parent, int style) {
		table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		gd.minimumHeight = 100;
		table.setLayoutData(gd);

		fillTable();
	}

	public void fillTable() {
		table.removeAll();

		List<InputControlQueryDataRow> qvalues = getRD().getQueryData();
		String[] qcolumns = getRD().getQueryVisibleColumns();

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

		table.getParent().layout();
	}
}
