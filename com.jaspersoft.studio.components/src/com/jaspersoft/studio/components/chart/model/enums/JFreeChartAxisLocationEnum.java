/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.enums;

import org.jfree.chart.axis.AxisLocation;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.JREnum;

public enum JFreeChartAxisLocationEnum implements JREnum {

	TOP_OR_LEFT((byte) 0, "Top Or Left"), TOP_OR_RIGHT((byte) 1, "Top Or Right"), BOTTOM_OR_LEFT((byte) 2,
			"Bottom Or Left"), BOTTOM_OR_RIGHT((byte) 3, "Bottom Or Right");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private JFreeChartAxisLocationEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public static JFreeChartAxisLocationEnum getValue(AxisLocation ha) {
		if (ha != null) {
			if (ha.equals(AxisLocation.TOP_OR_LEFT))
				return TOP_OR_LEFT;
			if (ha.equals(AxisLocation.TOP_OR_RIGHT))
				return TOP_OR_RIGHT;
			if (ha.equals(AxisLocation.BOTTOM_OR_LEFT))
				return BOTTOM_OR_LEFT;
			if (ha.equals(AxisLocation.BOTTOM_OR_RIGHT))
				return BOTTOM_OR_RIGHT;
		}
		return BOTTOM_OR_RIGHT;
	}

	public AxisLocation getJFreeChartValue() {
		if (value == 0)
			return AxisLocation.TOP_OR_LEFT;
		if (value == 1)
			return AxisLocation.TOP_OR_RIGHT;
		if (value == 2)
			return AxisLocation.BOTTOM_OR_LEFT;
		if (value == 3)
			return AxisLocation.BOTTOM_OR_RIGHT;
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
	public static JFreeChartAxisLocationEnum getByName(String name) {
		if (TOP_OR_LEFT.getName().equals(name))
			return TOP_OR_LEFT;
		if (TOP_OR_RIGHT.getName().equals(name))
			return TOP_OR_RIGHT;
		if (BOTTOM_OR_LEFT.getName().equals(name))
			return BOTTOM_OR_LEFT;
		if (BOTTOM_OR_RIGHT.getName().equals(name))
			return BOTTOM_OR_RIGHT;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartAxisLocationEnum getByValue(Byte value) {
		if (TOP_OR_LEFT.getValueByte().equals(value))
			return TOP_OR_LEFT;
		if (TOP_OR_RIGHT.getValueByte().equals(value))
			return TOP_OR_RIGHT;
		if (BOTTOM_OR_LEFT.getValueByte().equals(value))
			return BOTTOM_OR_LEFT;
		if (BOTTOM_OR_RIGHT.getValueByte().equals(value))
			return BOTTOM_OR_RIGHT;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartAxisLocationEnum getByValue(byte value) {
		return getByValue(new Byte(value));
	}

}
