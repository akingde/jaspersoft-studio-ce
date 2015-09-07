package com.jaspersoft.studio.property.itemproperty.desc;

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
	public Control createControl(IWItemProperty wiProp, Composite parent) {
		Text ctrl = (Text) super.createControl(wiProp, parent);
		ctrl.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				if (Misc.isNullOrEmpty(e.text))
					return;
				Number n = null;
				Class<?> type = getType();
				if (type.equals(Long.class)) {
					Long.parseLong(e.text);
				} else if (type.equals(BigInteger.class)) {
					new BigInteger(e.text);
				} else if (type.equals(Float.class)) {
					e.doit = FloatValidator.getInstance().isValid(e.text, Locale.US);
				} else if (type.equals(Double.class)) {
					e.doit = DoubleValidator.getInstance().isValid(e.text, Locale.US);
				} else if (type.equals(Integer.class)) {
					e.doit = IntegerValidator.getInstance().isValid(e.text, Locale.US);
				} else if (type.equals(Short.class)) {
					e.doit = ShortValidator.getInstance().isValid(e.text, Locale.US);
				} else if (type.equals(Byte.class)) {
					e.doit = ByteValidator.getInstance().isValid(e.text, Locale.US);
				} else {
					e.doit = BigDecimalValidator.getInstance().isValid(e.text, Locale.US);
				}
				if (e.doit && min != null && min instanceof Comparable<?>)
					e.doit = ((Comparable) min).compareTo(n) >= 0;
				if (e.doit && max != null && max instanceof Comparable<?>)
					e.doit = ((Comparable) max).compareTo(n) <= 0;
			}
		});
		return ctrl;
	}
}
