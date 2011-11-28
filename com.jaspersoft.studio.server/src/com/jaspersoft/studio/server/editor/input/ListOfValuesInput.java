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
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class ListOfValuesInput implements IDataInput {

	private Table table;

	public boolean isForType(Class<?> valueClass) {
		if (List.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final IParameter param,
			final Map<String, Object> params) {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;
		final ResourceDescriptor rd = rdprm.getResourceDescriptor();

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
				return false;
			}
		} else {
			items = rd2.getListOfValues();
		}

		if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES) {
			createList(parent, items, SWT.SINGLE);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO) {
			createList(parent, items, SWT.SINGLE | SWT.RADIO);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES) {
			createList(parent, items, SWT.MULTI);
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
			createList(parent, items, SWT.MULTI | SWT.CHECK);
		} else
			return false;

		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
						|| rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {

					List<Object> lst = new ArrayList<Object>();
					for (TableItem item : ti)
						lst.add(item.getData());

					params.put(param.getName(), lst);
				} else {
					params.put(param.getName(), ti[0].getData());
				}
			}
		});
		Object p = params.get(param.getName());
		if (p != null) {
			if (rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
					|| rd.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX) {
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

		return true;
	}

	private void createList(Composite parent, List<ListItem> list, int style) {
		table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		if (list.size() > 4)
			gd.heightHint = 100;
		table.setLayoutData(gd);
		for (ListItem item : list) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(item.getLabel());
			ti.setData(item.getValue());
		}
		table.select(0);
	}

}
