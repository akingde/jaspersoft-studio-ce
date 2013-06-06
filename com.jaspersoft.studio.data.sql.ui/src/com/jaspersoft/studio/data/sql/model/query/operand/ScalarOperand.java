package com.jaspersoft.studio.data.sql.model.query.operand;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.utils.Misc;

public class ScalarOperand<T> extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private T value;

	public ScalarOperand(MExpression mexpr, T value) {
		super(mexpr);
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toSQLString() {
		if (value != null) {
			if (value.getClass().isInstance(Number.class))
				return NumberFormat.getInstance().format((Number) value);
			if (value.getClass().isInstance(Date.class))
				return SimpleDateFormat.getInstance().format((Date) value);
			return "'" + value + "'";
		}
		return Misc.nvl(value, "");
	}
}
