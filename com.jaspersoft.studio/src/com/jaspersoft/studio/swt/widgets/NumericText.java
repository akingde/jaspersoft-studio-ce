/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.mihalis.opal.utils.StringUtil;

import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.ValidatedDecimalFormat;

/**
 * Extension of an swt widget to handle only numeric values. It will forbid invalid characters.
 * It will handle also min and max values. It is not possible to forbid the input of numbers
 * outside the max min bounds in some cases. For example if the minimum is 1.5 and the user want
 * to type a number it will start with 1, but at this point the platform would not allow him 
 * to input because it is lower of the minimum, but maybe the user want to add other digits that
 * in the end will keep the value inside the bounds. For this reason when the value is out of bounds
 * the control is colored in red and the user is allow to type any number. Until the value is not valid
 * it is not stored at model level, and it is reverted to a valid value when the focus is lost
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class NumericText extends Text {
	
	/**
	 * Enumeration used as a result of the validation, valid it means the inserted text is 
	 * a valid number, not valid means it can not be formatted as a number, out of bounds means
	 * it is a valid number but exceeds the min or max accepted values
	 */
	protected enum VALIDATION_RESULT {VALID, NOT_VALID, OUT_OF_BOUNDS};
	
	/**
	 * The listeners on this widget
	 */
	private final List<SelectionListener> selectionListeners = new ArrayList<SelectionListener>();

	/**
	 * The minimum value accepted
	 */
	private Double minimum = 0d;
	
	/**
	 * The maximum value accepted
	 */
	private Double maximum = Double.MAX_VALUE;
	
	/**
	 * Flag used to know if the null value is accepted or not
	 */
	private boolean isNullable = true;
	
	/**
	 * Flag to know if the shown value is inherited or not
	 */
	private boolean isInherited = false;
	
	/**
	 * Store the original color of the widget
	 */
	private Color currentColor = null;
	
	/**
	 * Formatter for the number
	 */
	private NumberFormat formatter;
	
	/**
	 * The current value of the number
	 */
	private Number storedValue;
	
	/**
	 * The increment step where the increment or decrements methods are called
	 */
	private int increamentStep = 1;
	
	/**
	 * The default value shown in the text area when the value is null
	 */
	private Number defaultValue = null;
	
	/**
	 * Flag used to know if the trailing zeroes after the decimal separator should be removed or not
	 */
	private boolean removeTrailZeroes = false;
	
	/**
	 * Flag used to avoid to fire the listener when the focus is lost but the value
	 * was not modified
	 */
	private boolean changedAfterFocus = false;
	
	/**
	 * The background color to use when there aren't validation errors
	 */
	private Color defaultBackgroundColor;
	
	/**
	 * The status of the value displayed
	 */
	protected VALIDATION_RESULT currentState = VALIDATION_RESULT.VALID;
	
	/**
	 * Verify listener used to check if the typed value is valid or not
	 */
	private VerifyListener inputVerifier = new VerifyListener() {
		@Override
		public void verifyText(final VerifyEvent e) {
			//Update the validation status
			currentState = verifyEntryAndStoreValue(e.text, e.keyCode);
			e.doit = currentState != VALIDATION_RESULT.NOT_VALID;
		}
	};
	
	/**
	 * Modify listeners called when the value in the text area change, fire all the attached
	 * selection listeners
	 */
	private ModifyListener inputNotifier = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			if (currentState == VALIDATION_RESULT.VALID){
				//Fire the listeners only if the value is valid
				fireListeners();
			}
			//open the flag to fire the listeners
			changedAfterFocus = true;
		}
	};
	
	private FocusListener focusNotifier = new FocusAdapter() {
		@Override
		public void focusLost(final FocusEvent e) {
			if (changedAfterFocus){
				//The listener are fired instead only if the value changed
				//after the focus gain
				if (currentState == VALIDATION_RESULT.VALID) {
					//Fire the listeners only if the value is valid
					fireListeners();
				}
				//Refresh the value
				setValue(storedValue, true);
			}
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			changedAfterFocus = false;
			//The set value on focus lost is done always to avoid
			//the mac text reset bug
			if (Util.isMac()){
				setValue(storedValue, true);
			}
		}
	};
	
	/**
	 * Create the textual control
	 * 
	 * @param parent the parent
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 * @param decimalDigitsShown the minimum number of decimal digits displayed when formatting the value, must be not negative
	 * @param decimalDigitsAccepted maximum number of decimal digits accepted. Set this to 0 mean no decimal digits, must be greater or equal of 
	 * decimalDigitsShown
	 */
	public NumericText(Composite parent, int style, int decimalDigitsShown, int decimalDigitsAccepted){
		super(parent, style);
		currentColor = getForeground();
		this.formatter = new ValidatedDecimalFormat(decimalDigitsShown, decimalDigitsAccepted);
		addListeners();
		defaultBackgroundColor = getBackground();
	}
	
	/**
	 * Create the textual control to handle integer numbers
	 * 
	 * @param parent the parent
	 * @param formatter the formatter for this control
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 */
	public NumericText(Composite parent, NumberFormat formatter, int style) {
		super(parent, style);
		currentColor = getForeground();
		addListeners();
		this.formatter = formatter;
		Assert.isTrue(formatter != null, "The formatter can't be null");
		defaultBackgroundColor = getBackground();
	}
	
	/**
	 * Add the listeners on the widget
	 */
	protected void addListeners(){
		addVerifyListener(inputVerifier);
		addModifyListener(inputNotifier);
		addFocusListener(focusNotifier);
		
	}
	
	/**
	 * Sets the minimum value that the receiver will allow. This new value will
	 * be ignored if it is greater than the receiver's current maximum value. If
	 * the new minimum is applied then the receiver's selection value will be
	 * adjusted if necessary to fall within its new range.
	 * 
	 * @param value the new minimum, which must be less than or equal to the
	 *            current maximum
	 * 
	 */
	public void setMinimum(Double min){
		if (min == null){
			//there is no maximum so the value is not updated
			this.minimum = null;
		} else if (maximum == null || min < maximum){
			this.minimum = min;
			if (storedValue != null && (this.minimum != null && storedValue.doubleValue() < this.minimum)){
				setValue(minimum);
			}
		}
	}
	
	/**
	 * Sets the maximum value that the receiver will allow. This new value will
	 * be ignored if it is less than the receiver's current minimum value. If
	 * the new maximum is applied then the receiver's selection value will be
	 * adjusted if necessary to fall within its new range.
	 * 
	 * @param value the new maximum, which must be greater than or equal to the
	 *            current minimum
	 * 
	 */
	public void setMaximum(Double max){
		if (max == null){
			//there is no maximum so the value is not updated
			this.maximum = null;
		} else if (minimum == null || max > minimum){
			this.maximum = max;
			if (storedValue != null && (this.maximum != null && storedValue.doubleValue() > this.maximum)){
				setValue(maximum);
			}
		}
	}

	/**
	 * Set if the value shown is inherited or not (an inherited value uses a different font color)
	 * 
	 * @param value true if the value is inherited, false otherwise
	 */
	public void setInherited(boolean value){
		if (isInherited != value){
			if (value){
				currentColor = getForeground();
				super.setForeground(ColorConstants.gray);
			} else {
				super.setForeground(currentColor);
			}
			this.isInherited = value;
		}
	}
	
	/**
	 * Set the foreground color of the area, but the change will be visible only
	 * when the inherited flag is false
	 */
	@Override
	public void setForeground(Color color) {
		currentColor = getForeground();
		if (!isInherited) super.setForeground(color);
	}
	
	/**
	 * Sets the receiver's selection, minimum value, maximum value all at once.
	 * <p>
	 * Note: This is similar to setting the values individually using the
	 * appropriate methods, but may be implemented in a more efficient fashion
	 * on some platforms.
	 * </p>
	 * 
	 * @param selection the new selection value
	 * @param minimum the new minimum value
	 * @param maximum the new maximum value
	 */
	public void setValues(Number selection, Number minimum, Number maximum) {
		this.setMinimum(minimum != null ? minimum.doubleValue() : null);
		this.setMaximum(maximum != null ? maximum.doubleValue() : null);
		setValue(selection);
	}
	
	/**
	 * Check if two number are the same, they are equals if they value is 
	 * the same or if the formatted value with the current formatter is
	 * the same
	 * 
	 * @param newValue the first value
	 * @param storedValue the second value
	 * @return  true if the values have the same textual representation, false otherwise
	 */
	protected boolean hasSameValue(Number newValue, Number storedValue){
		if (ModelUtils.safeEquals(newValue, storedValue)) return true;
		String newFormat = null;
		if (newValue != null) newFormat = formatNumber(newValue);
		String storedFormat = null;
		if (storedValue != null) storedFormat = formatNumber(storedValue);
		return ModelUtils.safeEquals(newFormat, storedFormat);
	}
	
	/**
	 * Method that convert the number value to a string. The perfect place to do this
	 * could be the formatter, but since the format method is final we do this workaround
	 * to allow custom implementation
	 * 
	 * @param value the number to format, must be not null
	 * @return the number as a string
	 */
	protected String formatNumber(Number value){
		String result;
		if (value instanceof Float){
			//When using a decimal format there is a conversion error done passing from float to double
			//explained here (http://programmingjungle.blogspot.it/2013/03/float-to-double-conversion-in-java.html)
			//Doing this will avoid the conversion error
			result = formatter.format(Double.parseDouble(value.toString()));
		} else {
			result = formatter.format(value);
		}
		if (removeTrailZeroes && result.indexOf(ValidatedDecimalFormat.DECIMAL_SEPARATOR) != -1){
			result = result.replaceAll("0*$", "").replaceAll(ValidatedDecimalFormat.PATTERN_DECIMAL_SEPARATOR + "$", "");
		}
		return result;
	}
	
	/**
	 * Sets the <em>selection</em>, which is the receiver's position, to the
	 * argument. If the argument is not within the range specified by minimum
	 * and maximum, it will be adjusted to fall within this range. The value is
	 * set only if the current value is different, also the value is formatted with the
	 * current formatter
	 * 
	 * @param value the new selection, can be null but only if the isNullable flag is
	 * set to true (it is by default)
	 * 
	 */
	public void setValue(Number selection) {
		if (selection != null){
			setInherited(false);
			if (!hasSameValue(selection, storedValue)){
				setValue(selection, true);
			}
		} else if (defaultValue != null){
			setInherited(true);
			if (!hasSameValue(defaultValue, storedValue)){
				setValue(null, true);
			}
		} else if (storedValue != null){
			setInherited(false);
			setValue(null, false);
		}
	}
	
	/**
	 * Sets the <em>selection</em>, which is the receiver's position, to the
	 * argument. If the argument is not within the range specified by minimum
	 * and maximum, it will be adjusted to fall within this range.
	 * 
	 * @param value the new selection, can be null but only if the isNullable flag is
	 * set to true (it is by default)
	 * @param formatText true if the text should be formatted before to be shown inside the
	 * text area, false to have it put directly
	 * 
	 */
	protected void setValue(Number selection, boolean formatText) {
		this.checkWidget();
		if (selection != null){	
			if ((minimum != null && selection.doubleValue() < minimum) || 
						(maximum != null && selection.doubleValue() > maximum)) {
				//out of bounds, update the validation status
				updateBackground(ColorConstants.red);
				currentState = VALIDATION_RESULT.OUT_OF_BOUNDS;
			} else {
				//valid value, update the validation status
				updateBackground(defaultBackgroundColor);
				currentState = VALIDATION_RESULT.VALID;
				storedValue = selection;
			}
			if (formatText) {
				setText(formatNumber(selection));
			} else {
				setText(selection.toString());
			}
		} else {
			if (isNullable){
				//valid value, update the validation status
				updateBackground(defaultBackgroundColor);
				currentState = VALIDATION_RESULT.VALID;
				storedValue = null;
				if (defaultValue != null){
					if (formatText) {
						setText(formatNumber(defaultValue));
					} else {
						setText(defaultValue.toString());
					}
				} else {
					setText("");
				}
			} else {
				throw new IllegalArgumentException("The widget can not accept null values when the isNullable property is false");
			}
		}
	}
	
	/**
	 * Handle the input of the user and output the final string 
	 * 
	 * @param entry the key pressed by the user
	 * @param keyCode the code of the key pressed by the user
	 * @param text the current text on the widget
	 * @param cursorSelection the current cursor selection on the widget
	 * @return the text that should be on the widget after the key is pressed
	 */
	protected String updateString(final String entry, int keyCode, String text, Point cursorSelection){
		String work = "";
		if (keyCode == SWT.DEL) {
			if (cursorSelection.x == cursorSelection.y && cursorSelection.x != text.length()){
				work = StringUtil.removeCharAt(getText(), getCaretPosition());
			} else {
				work = text.substring(0, cursorSelection.x) + text.substring(cursorSelection.y, text.length());
			}
		} else if (keyCode == SWT.BS) {
			if (cursorSelection.x == cursorSelection.y && cursorSelection.x != 0){
				work = StringUtil.removeCharAt(getText(), getCaretPosition() - 1);
			} else if (cursorSelection.x != cursorSelection.y) {
				work = text.substring(0, cursorSelection.x) + text.substring(cursorSelection.y, text.length());
			}
		} else if (keyCode == 0) {
			work = entry;
		} else {
			if (cursorSelection.x == cursorSelection.y){
				work = StringUtil.insertString(getText(), entry, getCaretPosition());
			} else if (cursorSelection.x != cursorSelection.y) {
				work = text.substring(0, cursorSelection.x) + entry + text.substring(cursorSelection.y, text.length());
			}
		}
		work = work.trim();
		return work;
	}
	
	/**
	 * Verify the entry and store the value in the field storedValue
	 * 
	 * @param entry entry to check
	 * @param keyCode code of the typed key
	 * @return <code>true</code> if the entry if correct, <code>false</code>
	 *         otherwise
	 */
	private VALIDATION_RESULT verifyEntryAndStoreValue(String entry, int keyCode) {
		Point cursorSelection = getSelection();
		String text = getText();
		String work = updateString(entry, keyCode, text, cursorSelection);
		if (work.isEmpty()){
			if (isNullable){
				storedValue = null;
			} else {
				return VALIDATION_RESULT.NOT_VALID;
			}
		} else {
			try {			
				Number newValue = formatter.parse(work);
				if ((minimum != null && newValue.doubleValue() < minimum) || 
							(maximum != null && newValue.doubleValue() > maximum)) {
					updateBackground(ColorConstants.red);
					return VALIDATION_RESULT.OUT_OF_BOUNDS;
				} else {
					storedValue = newValue;
				}
			} catch (ParseException nfe) {
				return VALIDATION_RESULT.NOT_VALID;
			}
		}
		updateBackground(defaultBackgroundColor);
		return VALIDATION_RESULT.VALID;
	}
	
	/**
	 * On macos the update of the color need some additional operation because of an SWT bug
	 * (https://bugs.eclipse.org/bugs/show_bug.cgi?id=346361). If the widget is focused it need
	 * to lose the focus to be updated correctly. For this reason the widget is forced to loose
	 * the focus and the it will regain it
	 * 
	 * @param color the color to set
	 */
	protected void updateBackground(Color color){
		if (Util.isMac() && isFocusControl() && !ModelUtils.safeEquals(color, super.getBackground())){
			removeFocusListener(focusNotifier);
			Point caretPosition = getSelection();
			boolean oldEnabled = getEnabled();
			setEnabled(false);//Force the focus lost
			super.setBackground(color);
			setEnabled(oldEnabled);
			setFocus();
			setSelection(caretPosition.x);
			addFocusListener(focusNotifier);
		} else {
			super.setBackground(color);
		}
	}

	/**
	 * Returns the numeric value stored inside the control, as an integer
	 * 
	 * @return the numeric value, could be null
	 */
	public Integer getValueAsInteger() {
		if (storedValue == null) return null;
		else return storedValue.intValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as a long
	 * 
	 * @return the numeric value, could be null
	 */
	public Long getValueAsLong(){
		if (storedValue == null) return null;
		else return storedValue.longValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as a double
	 * 
	 * @return the numeric value, could be null
	 */
	public Double getValueAsDouble(){
		if (storedValue == null) return null;
		else return storedValue.doubleValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as a float
	 * 
	 * @return the numeric value, could be null
	 */
	public Float getValueAsFloat(){
		if (storedValue == null) return null;
		else return storedValue.floatValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as a BigDecimal
	 * 
	 * @return the numeric value, could be null
	 */
	public BigDecimal getValueAsBigDecimal(){
		if (storedValue == null) return null;
		else return new BigDecimal(storedValue.toString());
	}
	
	/**
	 * Returns the numeric value stored inside the control
	 * 
	 * @return the numeric value, could be null
	 */
	public Number getValue(){
		return storedValue;
	}

	/**
	 * Add a selection listeners called when the value change
	 * 
	 * @param listener the listener, must be not null
	 */
	@Override
	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		selectionListeners.add(listener);
	}

	/**
	 * Set the flag to enable or disable the acceptance of empty null value
	 * 
	 * @param value true if the null value is accepted, false otherwise
	 */
	public void setNullable(boolean value){
		this.isNullable = value;
	}
	
	/**
	 *  Set the increment step for the increment operation
	 *  
	 * @param step a positive integer
	 */
	public void setIncrementStep(int step){
		Assert.isTrue(step >= 0, "The step can't be negative");
		this.increamentStep = step;
	}
	
	/**
	 * Increment the current value of the specified step. The value can't be 
	 * incremented above the maximum. Trigger the selection listeners after the operation
	 */
	public void increment(){
		if (storedValue == null){
			//if the minimum is < 0 start from zero as default
			double defaultMin = 0;
			if (defaultValue != null){
				defaultMin = defaultValue.intValue();
			}
			if (minimum != null && minimum > defaultMin) defaultMin = minimum;
			storedValue = new Double(defaultMin);
		}
		double newValue = storedValue.doubleValue() + increamentStep;
		if ((minimum != null && newValue >= minimum) && (maximum != null && newValue <= maximum)){
			setValue(newValue, true);
		}
		fireListeners();
	}
	
	/**
	 * Decrement the current value of the specified step. The value can't be 
	 * decremented below the minimum. Trigger the selection listeners after the operation
	 */
	public void decrement(){
		if (storedValue == null){
			//if the minimum is < 0 start from zero as default
			double defaultMin = 0;
			if (defaultValue != null){
				defaultMin = defaultValue.intValue();
			}
			if (minimum != null && minimum > defaultMin) defaultMin = minimum;
			storedValue = new Double(defaultMin);
			setValue(storedValue, true);
		} else {
			double newValue = storedValue.doubleValue() - increamentStep;	
			if ((minimum != null && newValue >= minimum) && (maximum != null && newValue <= maximum)){
				setValue(newValue, true);
			}
		}
		fireListeners();
	}
	
	/**
	 * Must be implemented empty to allow the extension of a standard swt widget
	 */
	@Override
	protected void checkSubclass() {
	}
	
	@Override
	public void cut() {
		super.cut();
		setValue(null);
		fireListeners();
	}
	
	@Override
	public void paste() {
		super.paste();
		String work = getText().trim();
		if (work.isEmpty()){
			setValue(null);
		} else {
			try {
				Number newValue = formatter.parse(work);
				setValue(newValue, true);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		fireListeners();
	}
	
	/**
	 * Set the text formatter for this widget. The text formatter is used both to format
	 * the text and to validate it. When the text change it is parsed by the formatter, if 
	 * it raise a ParseException then the new valued is considered invalid and is not allowed
	 * 
	 * @param formatter a not null formatter
	 */
	public void setFormat(NumberFormat formatter){
		Assert.isTrue(formatter != null, "The formatter can't be null");
		this.formatter = formatter;
		String work = getText().trim();
		if (work.isEmpty()){
			setValue(null);
		} else {
			try {
				Number newValue = formatter.parse(work);
				setValue(newValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Set the text in the text area, this will not trigger
	 * the selection listener or modify listener added to the custom widget
	 * 
	 * @param the string to set, must be not null
	 */
	@Override
	public void setText(String string) {
		removeVerifyListener(inputVerifier);
		removeModifyListener(inputNotifier);
		Point location = getSelection();
		super.setText(string);
		setSelection(location.x);
		addVerifyListener(inputVerifier);
		addModifyListener(inputNotifier);
	}

	/**
	 * Fire the selection listener added to this widget
	 */
	protected void fireListeners(){
		Event e = new Event();
		e.widget = this;
		e.time = (int)System.currentTimeMillis();
		SelectionEvent selectionEvent = new SelectionEvent(e);
		for (final SelectionListener s : selectionListeners) {
			s.widgetSelected(selectionEvent);
		}
	}
	
	/**
	 * Set the default value. The default value is shown when the current
	 * value is null, and it is shown into a different font. A default value
	 * is not returned by the method getValue
	 * 
	 * @param value the new default value, or null if there are no default values
	 */
	public void setDefaultValue(Number value){
		this.defaultValue = value;
	}
	
	/**
	 * Set the flag to know if the trailing zeroes after the decimal separator should be removed or not.
	 * By default they are not removed
	 * 
	 * @param value true to remove the zeroes, false otherwise
	 */
	public void setRemoveTrailZeroes(boolean value){
		this.removeTrailZeroes = value;
	}
	
	/**
	 * When the background is set the original color is stored to be restored when 
	 * switching between a valid and invalid state
	 */
	@Override
	public void setBackground(Color color) {
		if (currentState != VALIDATION_RESULT.OUT_OF_BOUNDS) {
			super.setBackground(color);
		}
		defaultBackgroundColor = color;
	}
}
