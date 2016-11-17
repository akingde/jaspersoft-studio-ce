/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.enums;

import org.jfree.ui.HorizontalAlignment;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.JREnum;

public enum JFreeChartHorizontalAlignmentEnum implements JREnum {

	CENTER((byte) 0, "Center"), LEFT((byte) 1, "Left"), RIGHT((byte) 2, "Right");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private JFreeChartHorizontalAlignmentEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public static JFreeChartHorizontalAlignmentEnum getValue(HorizontalAlignment ha) {
		if (ha != null) {
			if (ha.equals(HorizontalAlignment.CENTER))
				return CENTER;
			if (ha.equals(HorizontalAlignment.LEFT))
				return LEFT;
			if (ha.equals(HorizontalAlignment.RIGHT))
				return RIGHT;
		}
		return LEFT;
	}

	public HorizontalAlignment getJFreeChartValue() {
		if (value == 0)
			return HorizontalAlignment.CENTER;
		if (value == 1)
			return HorizontalAlignment.LEFT;
		if (value == 2)
			return HorizontalAlignment.RIGHT;
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
	public static JFreeChartHorizontalAlignmentEnum getByName(String name) {
		if (CENTER.getName().equals(name))
			return CENTER;
		if (LEFT.getName().equals(name))
			return LEFT;
		if (RIGHT.getName().equals(name))
			return RIGHT;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartHorizontalAlignmentEnum getByValue(Byte value) {
		if (CENTER.getValueByte().equals(value))
			return CENTER;
		if (LEFT.getValueByte().equals(value))
			return LEFT;
		if (RIGHT.getValueByte().equals(value))
			return RIGHT;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartHorizontalAlignmentEnum getByValue(byte value) {
		return getByValue(new Byte(value));
	}

}
