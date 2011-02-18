package com.jaspersoft.studio.editor.preview.inputs;

import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class BooleanInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (Boolean.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params) {
		if (isForType(valueClass)) {
			final Button txt = new Button(parent, SWT.CHECK);
			txt.setToolTipText(param.getDescription());
			txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			txt.setBackground(parent.getBackground());
			txt.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					params.put(param.getName(), new Boolean(txt.getSelection()));
				}

				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
			if (params.get(param.getName()) != null)
				txt.setSelection((Boolean) params.get(param.getName()));
			return true;
		}
		return false;
	}
}
