/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.enums;

import org.jfree.ui.VerticalAlignment;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.JREnum;

public enum JFreeChartVerticalAlignmentEnum implements JREnum {

	TOP((byte) 0, "Top"), CENTER((byte) 1, "Center"), BOTTOM((byte) 2, "Bottom");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private JFreeChartVerticalAlignmentEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public static JFreeChartVerticalAlignmentEnum getValue(VerticalAlignment ha) {
		if (ha != null) {
			if (ha.equals(VerticalAlignment.CENTER))
				return CENTER;
			if (ha.equals(VerticalAlignment.TOP))
				return TOP;
			if (ha.equals(VerticalAlignment.BOTTOM))
				return BOTTOM;
		}
		return TOP;
	}

	public VerticalAlignment getJFreeChartValue() {
		if (value == 0)
			return VerticalAlignment.TOP;
		if (value == 1)
			return VerticalAlignment.CENTER;
		if (value == 2)
			return VerticalAlignment.BOTTOM;
		return null;
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
	public static JFreeChartVerticalAlignmentEnum getByName(String name) {
		if (CENTER.getName().equals(name))
			return CENTER;
		if (TOP.getName().equals(name))
			return TOP;
		if (BOTTOM.getName().equals(name))
			return BOTTOM;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartVerticalAlignmentEnum getByValue(Byte value) {
		if (CENTER.getValueByte().equals(value))
			return CENTER;
		if (TOP.getValueByte().equals(value))
			return TOP;
		if (BOTTOM.getValueByte().equals(value))
			return BOTTOM;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartVerticalAlignmentEnum getByValue(byte value) {
		return getByValue(new Byte(value));
	}

}
