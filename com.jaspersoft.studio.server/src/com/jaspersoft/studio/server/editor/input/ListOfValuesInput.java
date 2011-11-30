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
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.ADataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class ListOfValuesInput extends ADataInput {

	private Table table;
	private ResourceDescriptor rd;

	public boolean isForType(Class<?> valueClass) {
		return List.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param,
			final Map<String, Object> params) {
		super.createInput(parent, param, params);
		PResourceDescriptor rdprm = (PResourceDescriptor) param;
		rd = rdprm.getResourceDescriptor();

		if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES) {
			createList(parent, SWT.SINGLE);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO) {
			createList(parent, SWT.SINGLE | SWT.RADIO);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES) {
			createList(parent, SWT.MULTI);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
			createList(parent, SWT.MULTI | SWT.CHECK);
		} else
			return;

		setMandatory(param, table);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
						|| rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {

					List<Object> lst = new ArrayList<Object>();
					for (TableItem item : ti)
						lst.add(item.getData());
					updateModel(lst);
				} else {
					updateModel(ti[0].getData());
				}
			}
		};
		table.addSelectionListener(listener);
		updateInput();
		listener.widgetSelected(null);
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null) {
			if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
					|| rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
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
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		table.setLayoutData(gd);
		fillTable();
	}

	public void fillTable() {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;

		ResourceDescriptor rd2 = (ResourceDescriptor) rd.getChildren().get(0);
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

		for (ListItem item : items) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(item.getLabel());
			ti.setData(item.getValue());
		}
		if (items.size() > 4)
			((GridData) table.getLayoutData()).heightHint = 100;
	}

}
