/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.enums;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.JREnum;

public enum PlotOrientationEnum implements JREnum {
	/**
	 * Specifies that the element is opaque.
	 */
	HORIZONTAL((byte) 1, "Horizontal"),

	/**
	 * Specifies that the element is transparent.
	 */
	VERTICAL((byte) 2, "Vertical");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private PlotOrientationEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 *
	 */
	public Byte getValueByte() {
		return new Byte(value);
	}

	/**
	 *
	 */
	public final byte getValue() {
		return value;
	}

	/**
	 *
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 */
	public static PlotOrientationEnum getByName(String name) {
		if (HORIZONTAL.getName().equals(name))
			return HORIZONTAL;
		if (VERTICAL.getName().equals(name))
			return VERTICAL;
		return null;
	}

	/**
	 *
	 */
	public static PlotOrientationEnum getByValue(Byte value) {
		if (value == null)
			return null;
		if (value.intValue() == 1)
			return HORIZONTAL;
		if (value.intValue() == 2)
			return VERTICAL;
		return null;
	}

	/**
	 *
	 */
	public static PlotOrientationEnum getByValue(byte value) {
		return getByValue(new Byte(value));
	}

}
