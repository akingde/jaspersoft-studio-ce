/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
		
		DecimalFormat format= (DecimalFormat)DecimalFormat.getInstance();
		DecimalFormatSymbols symbols=format.getDecimalFormatSymbols();
		char decimalSeparator=symbols.getDecimalSeparator();
		
		if (maxAcceptedDigits == 0){
			patternToMatch = Pattern.compile("[0-9]+");
		} else {
			patternToMatch = Pattern.compile("[0-9]+([" + decimalSeparator + "]{0,1}[0-9]{0," + maxAcceptedDigits + "})?");
		}
		applyPattern(pattern);
	}
	
	/**
	 * Parse and validate the value. The validation is done checking the value against the pattern
	 */
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
	
}
