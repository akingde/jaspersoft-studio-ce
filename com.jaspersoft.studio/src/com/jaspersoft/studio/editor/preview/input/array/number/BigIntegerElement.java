package com.jaspersoft.studio.editor.preview.input.array.number;

import java.math.BigInteger;

public class BigIntegerElement extends ANumberElement {

	@Override
	public Class<?> getSupportedType() {
		return BigInteger.class;
	}

	protected boolean isValid(String number) {
		new BigInteger(number);
		return true;
	}

	@Override
	protected Object convertString(String str) {
		return new BigInteger(str);
	}
}
