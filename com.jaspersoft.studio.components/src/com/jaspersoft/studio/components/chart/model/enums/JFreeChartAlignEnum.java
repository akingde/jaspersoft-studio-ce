/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.enums;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.EnumUtil;
import net.sf.jasperreports.engine.type.JREnum;

import org.jfree.ui.Align;

public enum JFreeChartAlignEnum implements JREnum {

	CENTER((byte) Align.CENTER, "Center"), TOP((byte) Align.TOP, "Top"), BOTTOM((byte) Align.BOTTOM, "Bottom"), LEFT((byte) Align.LEFT, "Left"), RIGHT((byte) Align.RIGHT, "Right"), TOP_LEFT(
			(byte) Align.TOP_LEFT, "Top Left"), TOP_RIGHT((byte) Align.TOP_RIGHT, "Top Right"), BOTTOM_LEFT((byte) Align.BOTTOM_LEFT, "Bottom Left"), BOTTOM_RIGHT((byte) Align.BOTTOM_RIGHT, "Bottom Right"), FIT_HORIZONTAL(
			(byte) Align.FIT_HORIZONTAL, "Fit Horizontal"), FIT_VERTICAL((byte) Align.FIT_VERTICAL, "Fit Vertical"), FIT((byte) Align.FIT, "Fit"), NORTH((byte) Align.NORTH, "North"), SOUTH(
			(byte) Align.SOUTH, "South"), WEST((byte) Align.WEST, "West"), EAST((byte) Align.EAST, "East"), NORTH_WEST((byte) Align.NORTH_WEST, "North West"), NORTH_EAST((byte) Align.NORTH_EAST,
			"North East"), SOUTH_WEST((byte) Align.SOUTH_WEST, "South West"), SOUTH_EAST((byte) Align.SOUTH_EAST, "South East");

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient byte value;
	private final transient String name;

	private JFreeChartAlignEnum(byte value, String name) {
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
	public static JFreeChartAlignEnum getByName(String name) {
		return (JFreeChartAlignEnum) EnumUtil.getByName(values(), name);
	}

	/**
	 *
	 */
	public static JFreeChartAlignEnum getByValue(Byte value) {
		return (JFreeChartAlignEnum) EnumUtil.getByValue(values(), value);
	}

	/**
	 *
	 */
	public static JFreeChartAlignEnum getByValue(byte value) {
		return getByValue(new Byte(value));
	}

}
