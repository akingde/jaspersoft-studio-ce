package com.jaspersoft.studio.server.editor.input;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.InputControlQueryDataRow;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class QueryInput implements IDataInput {

	public boolean isForType(Class<?> valueClass) {
		if (ResourceDescriptor.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, IParameter param,
			Map<String, Object> params) {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;
		ResourceDescriptor rd = rdprm.getResourceDescriptor();

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
		}

		return true;
	}

	private void createList(Composite parent, int style,
			List<InputControlQueryDataRow> list, String[] columns) {
		Table table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		if (list.size() > 4)
			gd.heightHint = 100;
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
			}
		}

		for (TableColumn tc : table.getColumns())
			tc.pack();
	}
}
