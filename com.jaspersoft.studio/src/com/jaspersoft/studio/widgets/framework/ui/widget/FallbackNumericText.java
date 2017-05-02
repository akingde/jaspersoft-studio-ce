/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.widget;

import java.text.NumberFormat;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.NumericText;

/**
 * This class extend the numeric text to be used inside the dynamic framework
 * widgets. The difference is this one will completely replace the value inside
 * the widget when the current display value is a fallback value. If the 
 * current value is a fallback one must be set from outside.
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class FallbackNumericText extends NumericText {

	/**
	 * Flag to know if the current value is a fallback value
	 */
	private boolean isFallback = false;
	
	/**
	 * Create the textual control to handle integer numbers
	 * 
	 * @param parent the parent
	 * @param formatter the formatter for this control
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 */
	public FallbackNumericText(Composite parent, NumberFormat formatter, int style) {
		super(parent, formatter, style);
	}

	/**
	 * Create the textual control
	 * 
	 * @param parent the parent
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 * @param decimalDigitsShown the minimum number of decimal digits displayed when formatting the value, must be not negative
	 * @param decimalDigitsAccepted maximum number of decimal digits accepted. Set this to 0 mean no decimal digits, must be greater or equal of 
	 * decimalDigitsShown
	 */
	public FallbackNumericText(Composite parent, int style, int decimalDigitsShown, int decimalDigitsAccepted){
		super(parent, style, decimalDigitsShown, decimalDigitsAccepted);
	}
	
	/**
	 * Set if the current value is a fallback value
	 * 
	 * @param value true if it is a fallback value, false otherwise
	 */
	public void setFallback(boolean value){
		isFallback = value;
	}
	
	/**
	 * Override the original update string to replace all the content of the widget with the input
	 * when the shown value is a fallback
	 */
	@Override
	protected String updateString(final String entry, int keyCode, String text, Point cursorSelection){
		if (isFallback){
			setText(entry.trim());
			return entry.trim();
		} else {
			return super.updateString(entry, keyCode, text, cursorSelection);
		}
	}
	
	/**
	 * Always update the value inside the widget with the model one, to update the widget
	 * even if when there is a fallback value
	 */
	@Override
	protected boolean hasSameValue(Number newValue, Number storedValue) {
		return false;
	}
	
	/**
	 * Set any text inside the widget but set its status to invalid
	 */
	public void setUnparsedValue(String text){
		currentState = VALIDATION_RESULT.NOT_VALID;
		updateBackground(ColorConstants.red);
		setText(text);
	}
}
