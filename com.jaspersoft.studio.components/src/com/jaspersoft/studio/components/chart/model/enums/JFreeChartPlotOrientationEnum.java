/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.enums;

import org.jfree.chart.plot.PlotOrientation;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.JREnum;

public enum JFreeChartPlotOrientationEnum implements JREnum {

	HORIZONTAL((byte) 0, "Horizontal"), VERTICAL((byte) 1, "Vertical");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private JFreeChartPlotOrientationEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public static JFreeChartPlotOrientationEnum getValue(PlotOrientation ha) {
		if (ha != null) {
			if (ha.equals(PlotOrientation.HORIZONTAL))
				return HORIZONTAL;
			if (ha.equals(PlotOrientation.VERTICAL))
				return VERTICAL;
		}
		return VERTICAL;
	}

	public PlotOrientation getJFreeChartValue() {
		if (value == 0)
			return PlotOrientation.HORIZONTAL;
		if (value == 1)
			return PlotOrientation.VERTICAL;
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
	public static JFreeChartPlotOrientationEnum getByName(String name) {
		if (HORIZONTAL.getName().equals(name))
			return HORIZONTAL;
		if (VERTICAL.getName().equals(name))
			return VERTICAL;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartPlotOrientationEnum getByValue(Byte value) {
		if (HORIZONTAL.getValueByte().equals(value))
			return HORIZONTAL;
		if (VERTICAL.getValueByte().equals(value))
			return VERTICAL;
		return null;
	}

	/**
	 *
	 */
	public static JFreeChartPlotOrientationEnum getByValue(byte value) {
		return getByValue(new Byte(value));
	}

}
