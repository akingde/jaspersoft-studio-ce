/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class EnumInput extends ADataInput {
	private Combo cmb;

	@Override
	public boolean isForType(Class<?> valueClass) {
		return valueClass.isEnum();
	}

	@Override
	public void createInput(Composite parent, final IParameter prm, Map<String, Object> params) {
		super.createInput(parent, prm, params);
		if (isForType(prm.getValueClass())) {
			cmb = new Combo(parent, SWT.READ_ONLY);
			cmb.setText(prm.getLabel());
			cmb.setToolTipText(prm.getDescription());
			cmb.addFocusListener(focusListener);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			cmb.setLayoutData(gd);

			List<String> values = new ArrayList<String>();
			values.add("");
			for (Object obj : prm.getValueClass().getEnumConstants())
				values.add(obj.toString());

			cmb.setItems(values.toArray(new String[values.size()]));

			SelectionAdapter listener = new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateModel(cmb.getText().isEmpty() ? null : Enum.valueOf((Class<Enum>) prm.getValueClass(), cmb.getText()));
					setDecoratorNullable(param);
				}
			};
			cmb.addSelectionListener(listener);
			setNullable(prm, cmb);
			updateInput();
		}
	}

	@Override
	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof Enum)
			value = ((Enum<?>) value).name();
		if (value != null && value instanceof String) {
			int i = 0;
			for (String s : cmb.getItems()) {
				if (s.equals(value))
					cmb.select(i);
				++i;
			}
		}
		setDecoratorNullable(param);
	}

}
