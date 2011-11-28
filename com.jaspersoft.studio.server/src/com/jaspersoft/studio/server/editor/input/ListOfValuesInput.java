package com.jaspersoft.studio.server.editor.input;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
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

	public boolean isForType(Class<?> valueClass) {
		if (List.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, IParameter param,
			Map<String, Object> params) {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;
		ResourceDescriptor rd = rdprm.getResourceDescriptor();

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

		return true;
	}

	private void createList(Composite parent, List<ListItem> list, int style) {
		Table table = new Table(parent, style | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		if (list.size() > 4)
			gd.heightHint = 100;
		table.setLayoutData(gd);
		for (ListItem item : list) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(item.getLabel());
		}
	}

}
