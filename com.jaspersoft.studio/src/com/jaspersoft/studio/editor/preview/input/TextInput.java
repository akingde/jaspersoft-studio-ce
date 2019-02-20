/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.view.control.VParameters;

public class TextInput extends ADataInput {
	private Text txt;

	public boolean isForType(Class<?> valueClass) {
		return String.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (isForType(param.getValueClass())) {
			txt = new Text(parent, SWT.BORDER);
			txt.setToolTipText(VParameters.createToolTip(param));
			txt.addFocusListener(focusListener);
			txt.addTraverseListener(keyListener);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			txt.setLayoutData(gd);
			setMandatory(param, txt);

			ModifyListener listener = e -> {
				if (!isRefresh)
					updateModel(txt.getText());
			};
			txt.addModifyListener(listener);
			updateInput();
			setNullable(param, txt);
		}
	}

	private boolean isRefresh = false;

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof String)
			txt.setText((String) value);
		else {
			isRefresh = true;
			txt.setText("");
			isRefresh = false;
		}
		setDecoratorNullable(param);
	}
}
