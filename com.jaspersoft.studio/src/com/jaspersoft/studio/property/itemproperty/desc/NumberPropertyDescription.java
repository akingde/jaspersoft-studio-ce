package com.jaspersoft.studio.property.itemproperty.desc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.ByteValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.ShortValidator;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.Misc;

public class NumberPropertyDescription<T extends Number> extends ItemPropertyDescription<T> {
	private Number min;
	private Number max;

	public NumberPropertyDescription(String name, String description, boolean mandatory, Number min, Number max) {
		super(name, description, mandatory);
		this.min = min;
		this.max = max;
	}

	public NumberPropertyDescription(String name, String description, boolean mandatory, T defaultValue, Number min,
			Number max) {
		super(name, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
	}

	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,
			Number min, Number max) {
		super(name, label, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
	}

	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, Number min,
			Number max) {
		super(name, label, description, mandatory);
		this.min = min;
		this.max = max;
	}

	@Override
	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return BigDecimal.class;
	}

	public Number getMin() {
		return min;
	}

	public Number getMax() {
		return max;
	}

	@Override
	public Control createControl(IWItemProperty wiProp, Composite parent) {
		Text ctrl = (Text) super.createControl(wiProp, parent);
		ctrl.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				if (Misc.isNullOrEmpty(e.text))
					return;
				String number = e.text;
				String oldText = ((Text) e.widget).getText();
				if (e.start != e.end)
					oldText = oldText.substring(0, e.start) + oldText.substring(e.end);
				number = oldText.substring(0, e.start) + e.text;
				if (oldText.length() - 1 > e.start + 1)
					number += oldText.substring(e.start + 1);

				if (number.equals("-")) //$NON-NLS-1$
					number = "-0";//$NON-NLS-1$
				if (number.equals(".")) //$NON-NLS-1$
					number = "0.";//$NON-NLS-1$

				if (number.isEmpty()) {
					e.doit = true;
					return;
				}
				Class<?> type = getType();
				if (type.equals(Long.class)) {
					Long.parseLong(number);
				} else if (type.equals(BigInteger.class)) {
					new BigInteger(number);
				} else if (type.equals(Float.class)) {
					e.doit = FloatValidator.getInstance().isValid(number, Locale.US);
				} else if (type.equals(Double.class)) {
					e.doit = DoubleValidator.getInstance().isValid(number, Locale.US);
				} else if (type.equals(Integer.class)) {
					e.doit = IntegerValidator.getInstance().isValid(number, Locale.US);
				} else if (type.equals(Short.class)) {
					e.doit = ShortValidator.getInstance().isValid(number, Locale.US);
				} else if (type.equals(Byte.class)) {
					e.doit = ByteValidator.getInstance().isValid(number, Locale.US);
				} else {
					e.doit = BigDecimalValidator.getInstance().isValid(number, Locale.US);
				}
				Number n = getNumber(number);
				if (n != null) {
					if (e.doit && min != null && min instanceof Comparable<?>)
						e.doit = ((Comparable) min).compareTo(n) <= 0;
					if (e.doit && max != null && max instanceof Comparable<?>)
						e.doit = ((Comparable) max).compareTo(n) >= 0;
				}
			}
		});
		return ctrl;
	}

	protected Number getNumber(String number) throws NumberFormatException {
		Class<?> typ = getType();
		if (typ.equals(Long.class))
			return new Long(number);
		else if (typ.equals(BigInteger.class))
			return new BigInteger(number);
		else if (typ.equals(Float.class))
			return new Float(number);
		else if (typ.equals(Double.class))
			return new Double(number);
		else if (typ.equals(Integer.class))
			return new Integer(number);
		else if (typ.equals(Short.class))
			return new Short(number);
		else if (typ.equals(Byte.class))
			return new Byte(number);
		else if (typ.equals(BigDecimal.class))
			return new BigDecimal(number);

		return null;
	}

	@Override
	public String getToolTip() {
		String tt = super.getToolTip();
		if (getMin() != null)
			tt += "\nmin: " + getMin();
		if (getMax() != null)
			tt += "\nmax: " + getMax();
		return tt;
	}
}
