/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;

/**
 * Decimal format used to format and validate the number into a NumericText
 * 
 * @author Orlandin Marco
 *
 */
public class ValidatedDecimalFormat extends DecimalFormat{
	
	public static final DecimalFormatSymbols SYMBOLS;
	
	static {
		DecimalFormat format= (DecimalFormat)DecimalFormat.getInstance();
		SYMBOLS=format.getDecimalFormatSymbols();
	}
	
	/**
	 * Static variable with the decimal separator
	 */
	public static final char DECIMAL_SEPARATOR = getDecimalSeparator();
	
	/**
	 * The decimal separator string that can be used into a regular expression
	 * it is already escaped
	 */
	public static final String PATTERN_DECIMAL_SEPARATOR = getPatternDecimalSeparator();

	private static final long serialVersionUID = -8953035800738294624L;
	
	/**
	 * Compiled pattern to match to be valid
	 */
	private Pattern patternToMatch;
	
	/**
	 * Create a number format used to handle numbers with 0 or more decimals
	 * 
	 * @param minAcceptedDigits the minimum number of decimal digits displayed when formatting the value
	 * @param maxAcceptedDigits maximum number of decimal digits accepted. Set this to 0 mean no decimal digits
	 */
	public ValidatedDecimalFormat(int minAcceptedDigits, int maxAcceptedDigits){
		super();
		Assert.isTrue(minAcceptedDigits >= 0, "Digits number can't be negative");
		Assert.isTrue(maxAcceptedDigits >= minAcceptedDigits, "The max accepted digits must be less or equal of the number of digits");
		setParseBigDecimal(maxAcceptedDigits > 10);
		String pattern = null;
		//Create the pattern using the number of digits
		if (maxAcceptedDigits > 0){
			pattern = "#####0.";
			for(int i = 0; i < maxAcceptedDigits; i++){
				if (i < minAcceptedDigits) {
					pattern += "0";
				} else {
					pattern += "#";
				}
			}  
		} else {
			pattern = "######";
		}
		
		if (maxAcceptedDigits == 0){
			patternToMatch = Pattern.compile("[0-9]+");
		} else {
			patternToMatch = Pattern.compile("[0-9]+([" + DECIMAL_SEPARATOR + "]{0,1}[0-9]{0," + maxAcceptedDigits + "})?");
		}
		applyPattern(pattern);
	}
	
	/**
	 * Parse and validate the value. The validation is done checking the value against the pattern
	 */
	@Override
	public Number parse(String source) throws ParseException {
	    ParsePosition pp = new ParsePosition(0);
	    //char groupSeparator = getDecimalFormatSymbols().getGroupingSeparator();
	    //remove the grouping separator
	    String valueToParse = source;//source.replace(String.valueOf(groupSeparator), "");
	    //When the value start with a minus handle it like a positive changing the minus with 0 
	    //and changing sign at the end
	    boolean isNegative = false;
	    if (valueToParse.startsWith("-")){
	    	isNegative = true;
	    	valueToParse = "0" + valueToParse.substring(1);
	    }
	    Matcher matcher = patternToMatch.matcher(valueToParse);
			if (!matcher.matches()) {
				throw new ParseException(valueToParse, pp.getIndex());
			}
	    Number result = super.parse(valueToParse, pp);
	    if (pp.getIndex() == valueToParse.length())  {
	    	 return isNegative ? result.doubleValue() * -1 : result;
	     } else {
	    	 throw new ParseException(valueToParse, pp.getIndex());
	     }
	}
	
	/**
	 * Return the decimal separator of the current instance
	 */
	protected static char getDecimalSeparator(){
		return SYMBOLS.getDecimalSeparator();
	}
	
	/**
	 * Return the escaped version of the decimal separator
	 */
	protected static String getPatternDecimalSeparator(){
		if (".".equals(String.valueOf(DECIMAL_SEPARATOR))){
			return "\\.";
		} else return String.valueOf(DECIMAL_SEPARATOR);
	}
	
}
