/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.report.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class Unit {
	private int dpi = 72;

	public static final String MM = "mm";
	public static final String CM = "cm";
	public static final String METER = "m";
	public static final String INCH = "inch";
	public static final String PX = "pixel";

	private static final Map<String, BigDecimal> units = new LinkedHashMap<String, BigDecimal>();
	static {
		units.put(PX, new BigDecimal(1));
		units.put(MM, new BigDecimal(25.4));
		units.put(CM, new BigDecimal(2.54));
		units.put(METER, new BigDecimal(0.0254));
		units.put(INCH, new BigDecimal(1));
	}
	
	/**
	 * Map of the alias of a unit, because there can be more ways to require a unit
	 */
	private static final Map<String,String> alias = new LinkedHashMap<String, String>();
	static {
		alias.put("pixel", PX);
		alias.put("pixels", PX);
		alias.put("px",PX);
		alias.put(PX,PX);
		
		alias.put("cm",CM);
		alias.put("centimeter",CM);
		alias.put("centimeters",CM);
		alias.put(CM,CM);

		alias.put("mm",MM);
		alias.put("millimeter",MM);
		alias.put("millimeters",MM);
		alias.put(MM,MM);

		alias.put("inches", INCH);
		alias.put("inch", INCH);
		alias.put("''", INCH);
		alias.put(INCH,INCH);
		
		alias.put("meter", METER);
		alias.put("meters", METER);
		alias.put("m", METER);
		alias.put(METER, METER);
	}

	// value in pixel
	private int value = 0;
	private String unit = PX;

	public Unit(double value, String unit) {
		super();
		setValue(value, unit);
	}

	private static String[] unitsArrays;

	public boolean setUnit(String unit) {
		if (this.unit.equals(unit))
			return false;
		if (units.get(unit) != null) {
			this.unit = unit;
			return true;
		}
		return false;
	}

	private int toValue(double value, BigDecimal c) {
		return new BigDecimal(value * dpi).divide(c, 0, RoundingMode.FLOOR).intValue();
	}

	private double fromValue(BigDecimal c) {
		return c.multiply(new BigDecimal(value)).divide(new BigDecimal(dpi), 4, RoundingMode.HALF_UP).doubleValue();
	}

	public void setValue(double value, String unit) {
		if (unit.equals(PX))
			this.value = (int) value;
		else {
			BigDecimal c = units.get(unit);
			if (c != null) {
				this.value = toValue(value, c);
				this.unit = unit;
			}
		}
	}

	public double getValue(String unit) {
		if (unit.equals(PX))
			return value;
		BigDecimal c = units.get(unit);
		if (c != null) {
			return fromValue(c);
		}
		return 0.0f;
	}

	public int getPxValue() {
		return value;
	}

	public String getUnit() {
		return unit;
	}

	public void setDPI(int dpi) {
		this.dpi = dpi;
	}

	public static int getUnitIndex(String key) {
		int ind = 0;
		for (int i = 0; i < unitsArrays.length; i++)
			if (unitsArrays[i].equals(key))
				return i;
		return ind;
	}

	public static String[] getUnits() {
		if (unitsArrays == null)
			unitsArrays = units.keySet().toArray(new String[units.keySet().size()]);
		return unitsArrays;
	}
	
	public static String getKeyFromAlias(String aliasValue){
		return alias.get(aliasValue);
	}

	public static String[][] getUnits2() {
		String[][] res = new String[getUnits().length][2];
		for (int i = 0; i < res.length; i++) {
			res[i][0] = unitsArrays[i];
			res[i][1] = unitsArrays[i];
		}
		return res;
	}
}
