/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;

public class Unit {
	
	/**
	 * Exception raised when the construction value is converted into pixels,
	 * if the number of pixels is too big\small to fit an integer.
	 * Since all the pixels value are stored into an integer the integer
	 * max and min work as upper and lower bound
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public class PixelConversionException extends RuntimeException{
		
		private static final long serialVersionUID = -9221259595310027388L;

		/**
		 * It is the nearest value to the requested one, typically this will
		 * be the upper or lower bound of an integer number
		 */
		private int nearestValue;
		
		/**
		 * Create the exception
		 * 
		 * @param message message of the exception
		 * @param nearestValue It is the nearest value to the requested one, typically this will
		 * be the upper or lower bound of an integer number
		 */
		public PixelConversionException(String message, int nearestValue){
			super(message);
			this.nearestValue = nearestValue;
		}
		
		/**
		 * Return  the nearest value to the requested one, typically this will
		 * be the upper or lower bound of an integer number
		 * 
		 * @return a valid integer
		 */
		public int getNearestValue(){
			return nearestValue;
		}
	}
	
	/**
	 * Upper bound for the pixels
	 */
	protected static final BigDecimal MAXIMUM_PIXELS = new BigDecimal(Integer.MAX_VALUE);
	
	/**
	 * Lower bound for the pixels
	 */
	protected static final BigDecimal MINIMUM_PIXELS = new BigDecimal(Integer.MIN_VALUE);

	//DEFAULT MEASURE UNIT
	
	public static final String MM = "mm"; //$NON-NLS-1$
	
	public static final String CM = "cm"; //$NON-NLS-1$
	
	public static final String METER = "m"; //$NON-NLS-1$
	
	public static final String INCH = "inch"; //$NON-NLS-1$
	
	public static final String PX = "pixel"; //$NON-NLS-1$

	private static final Map<String, BigDecimal> units = new LinkedHashMap<String, BigDecimal>();
	static {
		units.put(PX, new BigDecimal(1));
		units.put(MM, new BigDecimal("25.4")); //$NON-NLS-1$
		units.put(CM, new BigDecimal("2.54")); //$NON-NLS-1$
		units.put(METER, new BigDecimal("0.0254")); //$NON-NLS-1$
		units.put(INCH, new BigDecimal(1));
	}

	/**
	 * Map of the alias of a unit, because there can be more ways to require a unit
	 */
	private static final Map<String, String> alias = new LinkedHashMap<String, String>();
	static {
		alias.put("pixel", PX); //$NON-NLS-1$
		alias.put("pixels", PX); //$NON-NLS-1$
		alias.put("px", PX); //$NON-NLS-1$
		alias.put(PX, PX);

		alias.put("cm", CM); //$NON-NLS-1$
		alias.put("centimeter", CM); //$NON-NLS-1$
		alias.put("centimeters", CM); //$NON-NLS-1$
		alias.put(CM, CM);

		alias.put("mm", MM); //$NON-NLS-1$
		alias.put("millimeter", MM); //$NON-NLS-1$
		alias.put("millimeters", MM); //$NON-NLS-1$
		alias.put(MM, MM);

		alias.put("inches", INCH); //$NON-NLS-1$
		alias.put("inch", INCH); //$NON-NLS-1$
		alias.put("''", INCH); //$NON-NLS-1$
		alias.put("\"", INCH); //$NON-NLS-1$
		alias.put(INCH, INCH);

		alias.put("meter", METER); //$NON-NLS-1$
		alias.put("meters", METER); //$NON-NLS-1$
		alias.put("m", METER); //$NON-NLS-1$
		alias.put(METER, METER);
	}
	
	/**
	 * The DPI value
	 */
	private int dpi = 72;
	
	/**
	 * The value in pixel
	 */
	private int value = 0;
	
	/**
	 * The original measure unit of the value
	 */
	private String unit = PX;
	
	/**
	 * An array of all the available measure units keys
	 */
	private static String[] unitsArrays;
	
	public Unit(double value, String unit, JasperReportsConfiguration jConfig) throws PixelConversionException{
		super();
		setValue(value, unit);
		dpi = Misc.nvl(jConfig.getPropertyInteger("net.sf.jasperreports.image.dpi"), dpi); //$NON-NLS-1$
	}

	public boolean setUnit(String unit) {
		if (this.unit.equals(unit))
			return false;
		if (units.get(unit) != null) {
			this.unit = unit;
			return true;
		}
		return false;
	}

	/**
	 * Convert a value into an integer pixels
	 * 
	 * @param value the value to convert
	 * @param c the conversion scale 
	 * @return the passed value converted int pixels using the conversion scale
	 * @throws PixelConversionException raised when the number of pixels doesn't fit an integer
	 */
	private int toValue(double value, BigDecimal c) throws PixelConversionException {
		//convert the value into pixels
		BigDecimal pixel = new BigDecimal(value * dpi).divide(c, 0, RoundingMode.FLOOR);
		//Check if the pixels amount fit an integer
		if (pixel.compareTo(MAXIMUM_PIXELS) == 1) {
			throw getErrorException(true, pixel.toPlainString()); 
		} else if (pixel.compareTo(MINIMUM_PIXELS) == -1) {
			throw getErrorException(false, pixel.toPlainString()); 
		} 
		//At this point it can be converted into a valid int, so return it
		return pixel.intValue();
	}
	
	protected PixelConversionException getErrorException(boolean isMaximum, String inputValue){
		if (isMaximum) {
			String message = MessageFormat.format(Messages.Unit_errorTooBig, new Object[]{inputValue, MAXIMUM_PIXELS.toPlainString()});
			return new PixelConversionException(message, Integer.MAX_VALUE);
		} else {
			String message = MessageFormat.format(Messages.Unit_errorTooSmall, new Object[]{inputValue, MINIMUM_PIXELS.toPlainString()});
			return new PixelConversionException(message, Integer.MIN_VALUE);
		} 
	}

	public double pixel2unit(int val) {
		if (unit.equals(PX))
			return val;
		BigDecimal c = units.get(unit);
		if (c != null)
			return c.multiply(new BigDecimal(val)).divide(new BigDecimal(dpi), 4, RoundingMode.CEILING).doubleValue();
		return val;
	}

	private double fromValue(BigDecimal c) {
		double uval = c.multiply(new BigDecimal(value)).divide(new BigDecimal(dpi), 4, RoundingMode.CEILING).doubleValue();
		// System.out.println("FROM -> Value: " + value + " C: " + c + " REZULT:" + uval + "[" + unit + "]");
		return uval;
	}

	public void setValue(double value, String unit) throws PixelConversionException{
		if (unit.equals(PX)){
			//The unit is already in pixel, check only if the pixels amount fit an integer
			if (value > (double)Integer.MAX_VALUE){
				throw getErrorException(true, String.format("%.0f", value)); 
			} else if (value < (double)Integer.MIN_VALUE){
				throw getErrorException(false, String.format("%.0f", value)); 
			}
			//At this point it can be converted into a valid int, so convert it
			this.value = (int) value;
		} else {
			//Convert the unit into pixels
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

	/**
	 * Given an alias return its value
	 * 
	 * @param aliasValue
	 *          the alias
	 * @return the key value, or null if the alias is not recognized
	 */
	public static String getKeyFromAlias(String aliasValue) {
		return alias.get(aliasValue);
	}

	/**
	 * Add a new alias to the map
	 * 
	 * @param aliasName
	 *          the alias name
	 * @param key
	 *          the key corresponding to the alias
	 */
	public static void addAlias(String aliasName, String key) {
		alias.put(aliasName, key);
	}

	/**
	 * Return a list of all the alias
	 * 
	 * @return list of the alias names, useful for the autocomplete
	 */
	public static String[] getAliasList() {
		ArrayList<String> result = new ArrayList<String>();
		for (String key : alias.keySet())
			result.add(key);
		return result.toArray(new String[result.size()]);
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
