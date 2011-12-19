package com.jaspersoft.studio.server.editor.input.lov;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.server.editor.input.IInput;
import com.jaspersoft.studio.server.editor.input.PResourceDescriptor;
import com.jaspersoft.studio.utils.UIUtils;

public class ListInput implements IInput {

	private ListOfValuesInput dataInput;
	private Map<String, Object> params;
	private IParameter param;
	private Combo combo;
	private List<ListItem> items;

	public ListInput(ListOfValuesInput dataInput, IParameter param,
			Map<String, Object> params) {
		this.dataInput = dataInput;
		this.param = param;
		this.params = params;
	}

	public void createControl(Composite parent, int style) {
		combo = new Combo(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		gd.minimumHeight = 100;
		combo.setLayoutData(gd);

		fillControl();

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int ti = combo.getSelectionIndex();
				if (ti >= 0 && ti < items.size())
					dataInput.updateModel(items.get(ti).getValue());
			}
		};
		combo.addSelectionListener(listener);
		updateInput();
		listener.widgetSelected(null);
	}

	public void fillControl() {
		PResourceDescriptor rdprm = (PResourceDescriptor) param;

		ResourceDescriptor rd2 = (ResourceDescriptor) dataInput.getRd()
				.getChildren().get(0);
		items = null;

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
		combo.removeAll();
		if (items != null) {
			String[] citems = new String[items.size()];
			for (int i = 0; i < items.size(); i++) {
				citems[i] = items.get(i).getLabel();
			}
			combo.setItems(citems);
		}
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null) {
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getValue().equals(value)) {
					combo.select(i);
					break;
				}
			}
		}
	}

	public Control getControl() {
		return combo;
	}
}
