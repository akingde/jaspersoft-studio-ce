package com.jaspersoft.studio.editor.preview.inputs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;

public class NumericInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (Number.class.isAssignableFrom(valueClass))
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
			} else if (valueClass.equals(Long.class)) {
				min = (int) Long.MIN_VALUE;
				max = (int) Long.MAX_VALUE;
			} else if (valueClass.equals(Short.class)) {
				min = (int) Short.MIN_VALUE;
				max = (int) Short.MAX_VALUE;
			} else if (valueClass.equals(BigInteger.class)) {
				min = (int) Long.MIN_VALUE;
				max = (int) Long.MAX_VALUE;
			} else if (valueClass.equals(Byte.class)) {
				min = (int) Byte.MIN_VALUE;
				max = (int) Byte.MAX_VALUE;
			} else if (valueClass.equals(BigDecimal.class) || valueClass.equals(Float.class)
					|| valueClass.equals(Double.class)) {
				min = (int) Long.MIN_VALUE;
				max = (int) Long.MAX_VALUE;
				digits = 4;
				increment = 1;
				pageIncrement = 1;
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
					} else if (param.getValueClass().equals(Long.class)) {
						n = new Long(num.getSelection());
					} else if (param.getValueClass().equals(Short.class)) {
						n = new Short((short) num.getSelection());
					} else if (param.getValueClass().equals(BigInteger.class)) {
						n = new BigInteger(new String("" + num.getSelection())); //$NON-NLS-1$
					} else if (param.getValueClass().equals(Byte.class)) {
						n = new Byte((byte) num.getSelection());
					} else if (param.getValueClass().equals(BigDecimal.class)) {
						n = new BigDecimal(num.getSelection() / Math.pow(10000, num.getDigits()));
					} else if (param.getValueClass().equals(Float.class)) {
						n = new Float(num.getSelection() / Math.pow(10000, num.getDigits()));
					} else if (param.getValueClass().equals(Double.class)) {
						n = new Double(num.getSelection() / Math.pow(10000, num.getDigits()));
					}
					params.put(param.getName(), n);
				}
			});
			return true;
		}
		return false;
	}
}
