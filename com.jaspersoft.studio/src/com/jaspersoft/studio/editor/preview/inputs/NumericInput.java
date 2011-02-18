package com.jaspersoft.studio.editor.preview.inputs;

import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;

public class NumericInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (Integer.class.isAssignableFrom(valueClass))
			return true;
		if (Short.class.isAssignableFrom(valueClass))
			return true;
		if (Byte.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params) {
		if (Number.class.isAssignableFrom(valueClass)) {
			int min = 0;
			int max = 0;
			int digits = 0;
			int increment = 1;
			int pageIncrement = 10;
			if (valueClass.equals(Integer.class)) {
				min = Integer.MIN_VALUE;
				max = Integer.MAX_VALUE;
			} else if (valueClass.equals(Short.class)) {
				min = (int) Short.MIN_VALUE;
				max = (int) Short.MAX_VALUE;
			} else if (valueClass.equals(Byte.class)) {
				min = (int) Byte.MIN_VALUE;
				max = (int) Byte.MAX_VALUE;
			}

			final Spinner num = new Spinner(parent, SWT.BORDER);
			num.setToolTipText(param.getDescription());
			Number value = (Number) params.get(param.getName());
			int val = 0;
			if (value != null)
				if (digits == 0)
					val = value.intValue();
				else
					val = (int) (value.doubleValue() * Math.pow(10000, 1));
			num.setValues(val, min, max, digits, increment, pageIncrement);
			num.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Number n = null;
					if (param.getValueClass().equals(Integer.class)) {
						n = new Integer(num.getSelection());
					} else if (param.getValueClass().equals(Byte.class)) {
						n = new Byte((byte) num.getSelection());
					} else if (param.getValueClass().equals(Short.class)) {
						n = new Short((short) num.getSelection());
					}
					params.put(param.getName(), n);
				}
			});
			num.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			return true;
		}
		return false;
	}
}
