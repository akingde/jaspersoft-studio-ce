/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.report.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Decimal format used to format numbers that could be written
 * with different measure unit. With unit pixels the decimal are
 * not allowed, with the other supported units are allowed max 4
 * decimals 
 * 
 * @author Orlandin Marco
 *
 */
public class ValidatedMeasureUnitFormat extends DecimalFormat{

	private static final long serialVersionUID = -8953035800738294624L;
	
	/**
	 * Compiled pattern to match to be valid
	 */
	private Pattern patternToMatch;
	
	/**
	 * The current measure unit
	 */
	private String measureUnit;
	
	/**
	 * Create a number format used to handle numbers with 0 or more decimals
	 * 
	 * @param minAcceptedDigits the minimum number of decimal digits displayed when formatting the value
	 * @param maxAcceptedDigits maximum number of decimal digits accepted. Set this to 0 mean no decimal digits
	 */
	public ValidatedMeasureUnitFormat(String measureUnit){
		super();
		this.measureUnit = measureUnit;
		String pattern = "#####0.####";
		updateStringPattern();
		applyPattern(pattern);
	}
	
	/**
	 * Update the string pattern to match the current measure unit
	 */
	protected void updateStringPattern(){
		DecimalFormat format= (DecimalFormat)DecimalFormat.getInstance();
		DecimalFormatSymbols symbols=format.getDecimalFormatSymbols();
		char decimalSeparator=symbols.getDecimalSeparator();
		if (ModelUtils.safeEquals(measureUnit, Unit.PX)){
			patternToMatch = Pattern.compile("[0-9]+");
		} else {
			patternToMatch = Pattern.compile("[0-9]+([" + decimalSeparator + "]{0,1}[0-9]{0,4})?");
		}
	}
	
	/**
	 * Parse and validate the value. The validation is done checking the value against the pattern
	 */
	public Number parse(String source) throws ParseException {
    ParsePosition pp = new ParsePosition(0);
    //char groupSeparator = getDecimalFormatSymbols().getGroupingSeparator();
    //remove the grouping separator
    String valueToParse = source;//source.replace(String.valueOf(groupSeparator), "");
    Matcher matcher = patternToMatch.matcher(valueToParse);
		if (!matcher.matches()) {
			throw new ParseException(valueToParse, pp.getIndex());
		}
    Number result = super.parse(valueToParse, pp);
    if (pp.getIndex() == valueToParse.length())  {
    	 return result;
     } else {
    	 throw new ParseException(valueToParse, pp.getIndex());
     }
	}
	
	/**
	 * Set the measure unit used in this validator
	 * 
	 * @param the new measure unit, must be one defined
	 * in the Unit class
	 */
	public void setMeasureUnit(String unit){
		if (!ModelUtils.safeEquals(unit, this.measureUnit)){
			this.measureUnit = unit;
			updateStringPattern();
		}
	}
	
}
