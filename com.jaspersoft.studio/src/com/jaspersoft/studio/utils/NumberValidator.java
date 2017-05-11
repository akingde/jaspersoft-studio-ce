/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.BigIntegerValidator;
import org.apache.commons.validator.routines.ByteValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.apache.commons.validator.routines.ShortValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.eclipse.util.Misc;

public class NumberValidator implements VerifyListener {
	private Number min;
	private Number max;
	private Class<?> type;

	public NumberValidator(Number min, Number max, Class<?> type) {
		this.min = min;
		this.max = max;
		this.type = type;
	}

	@Override
	public void verifyText(VerifyEvent e) {
		switch (e.keyCode) {
		case SWT.BS:
		case SWT.DEL:
		case SWT.HOME:
		case SWT.END:
		case SWT.ARROW:
			return;
		}
		if (Misc.isNullOrEmpty(e.text)) {
			e.doit = false;
			return;
		}
		String number = e.text;
		String oldText = ((Text) e.widget).getText();
		if (e.start != e.end)
			oldText = oldText.substring(0, e.start) + oldText.substring(e.end);
		number = oldText.substring(0, e.start) + e.text;
		if (oldText.length() - 1 > e.start)
			number += oldText.substring(e.start);

		if (number.equals("-")) //$NON-NLS-1$
			number = "-0";//$NON-NLS-1$
		if (number.equals(".")) //$NON-NLS-1$
			number = "0.";//$NON-NLS-1$

		if (number.isEmpty()) {
			e.doit = true;
			return;
		}
		if (type.equals(Long.class)) {
			e.doit = LongValidator.getInstance().isValid(number, Locale.US);
		} else if (type.equals(BigInteger.class)) {
			e.doit = BigIntegerValidator.getInstance().isValid(number, Locale.US);
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
		try {
			Number n = getNumber(number, type);
			if (n != null) {
				if (e.doit && min != null && min instanceof Comparable<?>)
					e.doit = ((Comparable) min).compareTo(n) <= 0;
				if (e.doit && max != null && max instanceof Comparable<?>)
					e.doit = ((Comparable) max).compareTo(n) >= 0;
			}
		} catch (NumberFormatException e1) {
			e.doit = false;
		}
	}

	protected Number getNumber(String number, Class<?> typ) throws NumberFormatException {
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
}
