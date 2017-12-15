/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.view.control.VParameters;

import net.sf.jasperreports.eclipse.util.Misc;

public class URLInput extends ADataInput {
	private Text txt;

	public boolean isForType(Class<?> valueClass) {
		return URL.class.isAssignableFrom(valueClass) || URI.class.isAssignableFrom(valueClass);
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

			ModifyListener listener = new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					if (!isRefresh) {
						hideError(txt);
						if (param.getValueClass().equals(URL.class))
							try {
								updateModel(new URL(Misc.nvl(txt.getText())));
							} catch (MalformedURLException e1) {
								setError(txt, e1.getMessage());
							}
						else if (param.getValueClass().equals(URI.class))
							try {
								updateModel(new URI(Misc.nvl(txt.getText())));
							} catch (URISyntaxException e1) {
								setError(txt, e1.getMessage());
							}
					}
				}
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
			txt.setText(value == null ? "" : value.toString());
			isRefresh = false;
		}
		setDecoratorNullable(param);
	}
}
