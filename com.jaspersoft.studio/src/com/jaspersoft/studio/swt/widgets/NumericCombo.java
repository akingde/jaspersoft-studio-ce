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
package com.jaspersoft.studio.swt.widgets;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.mihalis.opal.utils.StringUtil;

import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.ValidatedDecimalFormat;

/**
 * Extension of an swt widget to handle only numeric values
 * 
 * @author Orlandin Marco
 *
 */
public class NumericCombo extends Combo {
	
	/**
	 * The listeners on this widget
	 */
	private final List<SelectionListener> selectionListeners = new ArrayList<SelectionListener>();

	/**
	 * The minimum value accepted
	 */
	private double minimum = 0;
	
	/**
	 * The maximum value accepted
	 */
	private double maximum = Double.MAX_VALUE;
	
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
	 * Verify listener used to check if the typed value is valid or not
	 */
	private VerifyListener inputVerifier = new VerifyListener() {
		@Override
		public void verifyText(final VerifyEvent e) {
			e.doit = verifyEntryAndStoreValue(e.text, e.keyCode);
		}
	};
	
	/**
	 * Modify listeners called when the value in the text area change, fire all the attached
	 * selection listeners
	 */
	private ModifyListener inputNotifier = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			fireListeners();
		}
	};
	
	/**
	 * Selection listeners called when the value in the text area change, fire all the attached
	 * selection listeners
	 */
	private SelectionListener selectionNotifier = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			fireListeners();
		}
	};
	
	/**
	 * Create the combo control
	 * 
	 * @param parent the parent
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 * @param decimalDigits the number of decimal digits shown in the widget
	 */
	public NumericCombo(Composite parent, int style, int decimalDigits){
		super(parent, style);
		currentColor = getForeground();
		this.formatter = new ValidatedDecimalFormat(decimalDigits);
		addListeners();
	}
	
	/**
	 * Create the combo control to handle the numbers accepter by the passed NumberFormat
	 * 
	 * @param parent the parent
	 * @param formatter the formatter for this control
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 */
	public NumericCombo(Composite parent, NumberFormat formatter, int style) {
		super(parent, style);
		currentColor = getForeground();
		addListeners();
		this.formatter = formatter;
		Assert.isTrue(formatter != null, "The formatter can't be null");
	}
	
	/**
	 * Add the listeners on the widget
	 */
	protected void addListeners(){
		addVerifyListener(inputVerifier);
		addModifyListener(inputNotifier);
		super.addSelectionListener(selectionNotifier);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent e) {
				setValue(storedValue, true);
				fireListeners();
			}
		});
		
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
	public void setMinimum(double min){
		if (min < maximum){
			this.minimum = min;
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
	public void setMaximum(double max){
		if (max > minimum){
			this.maximum = max;
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
	public void setValues(Number selection, int minimum, int maximum) {
		this.setMinimum(minimum);
		this.setMaximum(maximum);
		setValue(selection);
	}
	
	/**
	 * Check if two number are the same, they are equals if they value is 
	 * the same or if the formatted value with the current formatter is
	 * the same
	 * 
	 * @param newValue the first value
	 * @param storedValue the second value
	 * @return  true if the values have the same textual rappresentation, false otherwise
	 */
	protected boolean hasSameValue(Number newValue, Number storedValue){
		if (ModelUtils.safeEquals(newValue, storedValue)) return true;
		String newFormat = null;
		if (newValue != null) newFormat = formatter.format(newValue);
		String storedFormat = null;
		if (storedValue != null) storedFormat = formatter.format(storedValue);
		return ModelUtils.safeEquals(newFormat, storedFormat);
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
		if (!hasSameValue(selection, storedValue)){
			setValue(selection, true);
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
			if (selection.doubleValue() < minimum) {
				selection = this.minimum;
			} else if (selection.doubleValue() > maximum) {
				selection = this.maximum;
			}
			storedValue = selection;
			if (formatText) {
				setText(formatter.format(selection));
			} else {
				setText(selection.toString());
			}
			setSelection(new Point(0, getText().length()));
			setFocus();
		} else {
			if (isNullable){
				storedValue = null;
				setText("");
				setSelection(new Point(0, getText().length()));
				setFocus();
			} else {
				throw new IllegalArgumentException("The widget can not accept null values when the isNullable property is false");
			}
		}
	}
	
	/**
	 * Verify the entry and store the value in the field storedValue
	 * 
	 * @param entry entry to check
	 * @param keyCode code of the typed key
	 * @return <code>true</code> if the entry if correct, <code>false</code>
	 *         otherwise
	 */
	private boolean verifyEntryAndStoreValue(final String entry, final int keyCode) {
		String work = "";
		if (keyCode == SWT.DEL) {
			work = StringUtil.removeCharAt(getText(), getCaretPosition());
		} else if (keyCode == SWT.BS) {
			Point cursorSelection = getSelection();
			if (cursorSelection.x == cursorSelection.y && cursorSelection.x != 0){
				work = StringUtil.removeCharAt(getText(), getCaretPosition() - 1);
			} else if (cursorSelection.x != cursorSelection.y) {
				String text = getText();
				work = text.substring(0, cursorSelection.x) + text.substring(cursorSelection.y, text.length());
			}
		} else if (keyCode == 0) {
			work = entry;
		} else {
			Point cursorSelection = getSelection();
			if (cursorSelection.x == cursorSelection.y){
				work = StringUtil.insertString(getText(), entry, cursorSelection.x);
			} else if (cursorSelection.x != cursorSelection.y) {
				String text = getText();
				work = text.substring(0, cursorSelection.x) + entry + text.substring(cursorSelection.y, text.length());
			}
		}
		work = work.trim();
		
		if (work.isEmpty()){
			if (isNullable){
				storedValue = null;
			} else {
				return false;
			}
		} else {
			try {			
				Number newValue = formatter.parse(work);
				if (newValue.doubleValue() < minimum || newValue.doubleValue() > maximum) return false;
				storedValue = newValue;
			} catch (ParseException nfe) {
				return false;
			}
		}
		return true;
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
			if (minimum > 0) defaultMin = minimum;
			storedValue = new Double(defaultMin);
		}
		double newValue = storedValue.doubleValue() + increamentStep;
		setValue(newValue, true);
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
			if (minimum > 0) defaultMin = minimum;
			storedValue = new Double(defaultMin);
			setValue(storedValue, true);
		} else {
			double newValue = storedValue.doubleValue() - increamentStep;	
			setValue(newValue, true);
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
		super.setText(string);
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
}
