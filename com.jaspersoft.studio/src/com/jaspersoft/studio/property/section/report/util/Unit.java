package com.jaspersoft.studio.property.section.report.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class Unit {
	private int dpi = 72;

	public static final String MM = "mm";
	public static final String CM = "cm";
	public static final String INCH = "inch";
	public static final String PX = "pixel";

	private static final Map<String, BigDecimal> units = new LinkedHashMap<String, BigDecimal>();
	static {
		units.put(PX, new BigDecimal(1));
		units.put(MM, new BigDecimal(25.4));
		units.put(CM, new BigDecimal(2.54));
		units.put(INCH, new BigDecimal(1));
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

	public static String[][] getUnits2() {
		String[][] res = new String[getUnits().length][2];
		for (int i = 0; i < res.length; i++) {
			res[i][0] = unitsArrays[i];
			res[i][1] = unitsArrays[i];
		}
		return res;
	}
}
