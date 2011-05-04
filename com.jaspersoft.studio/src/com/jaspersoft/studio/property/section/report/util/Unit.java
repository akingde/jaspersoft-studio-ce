package com.jaspersoft.studio.property.section.report.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Unit {
	private int dpi = 72;

	public static final String MM = "mm";
	public static final String CM = "cm";
	public static final String INCH = "inch";
	public static final String PX = "pixel";

	private static final Map<String, Float> units = new LinkedHashMap<String, Float>();
	static {
		units.put(PX, new Float(1));
		units.put(MM, new Float(0.0245));
		units.put(CM, new Float(0.245));
		units.put(INCH, new Float(1));
	}

	// value in pixel
	private int value;
	private String unit;

	public Unit(float value, String unit) {
		super();
		setValue(value, unit);
	}

	private static String[] unitsArrays;

	public void setUnit(String unit) {
		if (units.get(unit) != null)
			this.unit = unit;
	}

	public void setValue(float value, String unit) {
		if (unit.equals(PX))
			this.value = (int) value;
		else {
			Float c = units.get(unit);
			if (c != null) {
				this.value = Math.round(value * c.floatValue() * dpi);
				this.unit = unit;
			}
		}

	}

	public float getValue() {
		if (unit.equals(PX))
			return value;
		Float c = units.get(unit);
		if (c != null) {
			return value / (c.floatValue() * dpi);
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

	public static String[] getUnits() {
		if (unitsArrays == null)
			unitsArrays = units.keySet().toArray(new String[units.keySet().size()]);
		return unitsArrays;
	}
}
