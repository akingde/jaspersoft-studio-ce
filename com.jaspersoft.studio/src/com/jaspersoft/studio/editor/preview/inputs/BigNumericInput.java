package com.jaspersoft.studio.editor.preview.inputs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class BigNumericInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (Long.class.isAssignableFrom(valueClass))
			return true;
		if (BigInteger.class.isAssignableFrom(valueClass))
			return true;
		if (BigDecimal.class.isAssignableFrom(valueClass))
			return true;
		if (Float.class.isAssignableFrom(valueClass))
			return true;
		if (Double.class.isAssignableFrom(valueClass))
			return true;
		if (Number.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params) {
		if (Number.class.isAssignableFrom(valueClass)) {
			final Text num = new Text(parent, SWT.BORDER);

			num.setToolTipText(param.getDescription());
			Number value = (Number) params.get(param.getName());
			if (value != null)
				num.setText(NumberFormat.getInstance().format(value));
			num.addListener(SWT.Verify, new Listener() {

				public void handleEvent(Event e) {
					try {
						String number = e.text;
						String oldText = ((Text) e.widget).getText();
						if (e.start == 0)
							number = e.text + oldText;
						else
							number = oldText.substring(0, e.start) + e.text;
						if (oldText.length() - 1 > e.start + 1)
							number += oldText.substring(e.start + 1);

						if (number.equals("-"))
							number = "-0";//$NON-NLS-1$
						if (number.equals("."))
							number = "0.";//$NON-NLS-1$

						if (param.getValueClass().equals(Long.class)) {
							Long.parseLong(number);
						} else if (param.getValueClass().equals(BigInteger.class)) {
							new BigInteger(number); //$NON-NLS-1$
						} else if (param.getValueClass().equals(BigDecimal.class)) {
							e.doit = BigDecimalValidator.getInstance().isValid(number);
						} else if (param.getValueClass().equals(Float.class)) {
							e.doit = FloatValidator.getInstance().isValid(number);
						} else if (param.getValueClass().equals(Double.class)) {
							e.doit = DoubleValidator.getInstance().isValid(number);
						}
					} catch (NumberFormatException ne) {
						e.doit = false;
					}
				}
			});
			num.addModifyListener(new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					try {
						Number n = null;
						if (param.getValueClass().equals(Long.class)) {
							n = new Long(num.getText());
						} else if (param.getValueClass().equals(BigInteger.class)) {
							n = new BigInteger(num.getText()); //$NON-NLS-1$
						} else if (param.getValueClass().equals(BigDecimal.class)) {
							n = new BigDecimal(num.getText());
						} else if (param.getValueClass().equals(Float.class)) {
							n = new Float(num.getText());
						} else if (param.getValueClass().equals(Double.class)) {
							n = new Double(num.getText());
						}
						params.put(param.getName(), n);
					} catch (NumberFormatException ne) {

					}
				}
			});
			num.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			return true;
		}
		return false;
	}
}
