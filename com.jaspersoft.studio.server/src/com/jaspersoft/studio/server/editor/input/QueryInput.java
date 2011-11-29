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
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class QueryInput implements IDataInput {

	private Table table;

	public boolean isForType(Class<?> valueClass) {
		if (ResourceDescriptor.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final IParameter param,
			final Map<String, Object> params) {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;
		final ResourceDescriptor rd = rdprm.getResourceDescriptor();

		ResourceDescriptor rd2 = (ResourceDescriptor) rd.getChildren().get(0);
		List<InputControlQueryDataRow> qvalues = null;
		String[] qcolumns = null;
		if (rd2.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE)) {
			ResourceDescriptor tmpRd = new ResourceDescriptor();
			tmpRd.setUriString(rd2.getReferenceUri());
			try {
				tmpRd = rdprm.getWsClient().get(tmpRd, null);
				qvalues = tmpRd.getQueryData();
				qcolumns = tmpRd.getQueryVisibleColumns();
			} catch (Exception ex) {
				UIUtils.showError(ex);
				return false;
			}
		} else {
			qvalues = rd.getQueryData();
			qcolumns = rd.getQueryVisibleColumns();
		}
		if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY) {
			createList(parent, SWT.SINGLE, qvalues, qcolumns);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY_RADIO) {
			createList(parent, SWT.SINGLE | SWT.RADIO, qvalues, qcolumns);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY) {
			createList(parent, SWT.MULTI, qvalues, qcolumns);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
			createList(parent, SWT.MULTI | SWT.CHECK, qvalues, qcolumns);
		} else
			return false;

		ADataInput.setMandatory(param, table);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
						|| rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
					List<Object> lst = new ArrayList<Object>();
					for (TableItem item : ti)
						lst.add(item.getData());
					params.put(param.getName(), lst);
				} else {
					params.put(param.getName(), ti[0].getData());
				}
			}
		};
		table.addSelectionListener(listener);

		Object p = params.get(param.getName());
		if (p != null) {
			if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
					|| rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX) {
				if (p instanceof List) {
					List<TableItem> titems = new ArrayList<TableItem>();
					List<Object> lst = (List<Object>) p;
					for (TableItem ti : table.getItems()) {
						if (lst.contains(ti.getData()))
							titems.add(ti);
					}
					table.setSelection(titems.toArray(new TableItem[titems
							.size()]));
				}

			} else {
				for (TableItem ti : table.getItems()) {
					if (ti.getData().equals(p))
						table.setSelection(ti);
				}
			}
		}

		listener.widgetSelected(null);

		return true;
	}

	private void createList(Composite parent, int style,
			List<InputControlQueryDataRow> list, String[] columns) {
		table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		if (list.size() > 4)
			gd.heightHint = 100;
		gd.horizontalIndent = 8;
		table.setLayoutData(gd);

		for (String c : columns) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(c);
		}

		for (InputControlQueryDataRow item : list) {
			TableItem ti = new TableItem(table, SWT.NONE);
			List<String> cvals = item.getColumnValues();
			for (int i = 0; i < cvals.size(); i++) {
				ti.setText(i, cvals.get(i));
				ti.setData(item.getValue());
			}
		}

		for (TableColumn tc : table.getColumns())
			tc.pack();
		table.select(0);
	}

	public boolean isLabeled() {
		return false;
	}
}
