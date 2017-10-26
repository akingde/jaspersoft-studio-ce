/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;

public enum AgregationFunctionEnum {
	UNIQUE((int) 0, "Unique"), YEAR((int) 1, "Year"), MONTH((int) 2, "Month"), WEEK(
			(int) 3, "Week"), DAY((int) 4, "Day"), DAY_OF_THE_WEEK((int)5, "Day of the Week"),
			HOUR((int)6, "Hour"), MINUTE((int)7, "Minute"), SECOND((int)8, "Second"),
			MILLISECOND((int)9, "Millisecond"), QUARTER((int)10, "Quarter"), SEMESTER((int)11, "Semi");

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private final transient int value;
	private final transient String name;

	private AgregationFunctionEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 *
	 */
	public int getValueInt() {
		return value;
	}

	/**
	 *
	 */
	public final int getValue() {
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
	public static AgregationFunctionEnum getByName(String name) {
		for (AgregationFunctionEnum v : values())
			if (v.getName().equals(name))
				return v;
		return null;
	}

	/**
	 *
	 */
	public static AgregationFunctionEnum getByValue(int value) {
		for (AgregationFunctionEnum v : values())
			if (v.getValue() == value)
				return v;
		return null;
	}

	/**
	 *
	 */
	public static AgregationFunctionEnum getByValue(Integer value) {
		return getByValue(value.intValue());
	}

	public static String[] getStringValues() {
		AgregationFunctionEnum[] vals = values();
		String[] svalues = new String[vals.length];
		for (int i = 0; i < svalues.length; i++)
			svalues[i] = vals[i].getName();
		return svalues;
	}
	
	public static String[] getValuesNames(AgregationFunctionEnum[] values) {
		String[] names = new String[values.length];
		for(int i = 0; i < values.length; i++) {
			names[i] = values[i].getName();
		}
		return names;
	}
	
	/**
	 * Return the aggregation function for a specific class
	 * 
	 * @param  className the class to check, typically the class of the dataset element
	 * @return a not null array for the passed class. the array is also not empty since 
	 * the UNIQUE class is always available
	 */
	public static AgregationFunctionEnum[] getStringValues(String className) {
		List<AgregationFunctionEnum> names = new ArrayList<AgregationFunctionEnum>();
		names.add(UNIQUE);
		if (className != null) {
			String lClassName = className.toLowerCase().trim();
			if (java.util.Date.class.getName().toLowerCase().equals(lClassName) || java.sql.Timestamp.class.getName().toLowerCase().equals(lClassName)) {
				names.add(YEAR);
				names.add(SEMESTER);
				names.add(QUARTER);
				names.add(MONTH);
				names.add(WEEK);
				names.add(DAY);
				names.add(DAY_OF_THE_WEEK);
				names.add(HOUR);
				names.add(MINUTE);
				names.add(SECOND);
				names.add(MILLISECOND);
			} else if (java.sql.Date.class.getName().toLowerCase().equals(lClassName)) {
				names.add(YEAR);
				names.add(SEMESTER);
				names.add(QUARTER);
				names.add(MONTH);
				names.add(WEEK);
				names.add(DAY);
				names.add(DAY_OF_THE_WEEK);
			} else if (java.sql.Time.class.getName().toLowerCase().equals(lClassName)) {
				names.add(HOUR);
				names.add(MINUTE);
				names.add(SECOND);
				names.add(MILLISECOND);
			}
		}
		
		return names.toArray(new AgregationFunctionEnum[names.size()]);
	}
	
	/**
	 * Return true if there is an aggregation function beside unique for a specific 
	 * type, otherwise it return false
	 * 
	 * @param className the class to check
	 * @return true if the class is a java date or an sql data,time or timestap, false
	 * otherwise
	 */
	public static boolean isEditable(String className) {
		if (className != null) {
			String lClassName = className.toLowerCase().trim();
			if (java.util.Date.class.getName().toLowerCase().equals(lClassName) || 
					java.sql.Date.class.getName().toLowerCase().equals(lClassName) ||
						java.sql.Timestamp.class.getName().toLowerCase().equals(lClassName) ||
							java.sql.Time.class.getName().toLowerCase().equals(lClassName)) {
				return true;
			}
		}
		return false;
	}
}
