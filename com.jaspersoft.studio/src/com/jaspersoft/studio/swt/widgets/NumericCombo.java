/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.mihalis.opal.utils.StringUtil;

import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.ValidatedDecimalFormat;

/**
 * Extension of an swt widget to handle only numeric values. It uses different widgets in 
 * windows and other OS. In windows it is used internally a CCombo, this because there is an 
 * abnormal behavior in windows: when the combo is opened a modify listener is triggered. This
 * happen only on windows, the CCcombo is not affected by this. In the other OS this bug is not
 * present on both Combo and CCombo, so a base combo is used since it has a better look & feel.
 * 
 * @author Orlandin Marco
 *
 */
public class NumericCombo extends Composite {
	
	/**
	 * The combo control used in MacOS/Linux
	 */
	private Combo controlCombo = null;
	
	/**
	 * The combo control used on windows
	 */
	private CCombo controlCCombo = null;
	
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
	 * Flag used to avoid to fire the listener when the focus is lost but the value
	 * was not modified
	 */
	private boolean changedAfterFocus = false;
	
	/**
	 * The default value shown in the text area when the value is null
	 */
	private Number defaultValue = null;
	
	/**
	 * The default size of a native combo on the current OS. It will be initialized
	 * the first time this class is used
	 */
	private static Point defaultSize = null;
	
	/**
	 * Custom layout used to place the elements inside the composite. This will remove 
	 * every unnecessary space to have the combo completely fit the container area
	 */
	private Layout mainLayout = new Layout() {
		
		@Override
		protected void layout(Composite parent, boolean flushCache) {
			Control[] children = parent.getChildren();
			Rectangle carea = parent.getClientArea();
			children[0].setBounds(0, 0, carea.width, carea.height);
		}
		
		@Override
		protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
			int width = wHint;
			int height = hHint;
			if (wHint == SWT.DEFAULT){
				width = defaultSize != null ? defaultSize.x : 50;
			}
			if (hHint == SWT.DEFAULT){
				height = defaultSize != null ? defaultSize.y : 23;
			}
			return new Point(width, height);
		}
	};

	
	/**
	 * Verify listener used to check if the typed value is valid or not
	 */
	private VerifyListener inputVerifier = new VerifyListener() {
		@Override
		public void verifyText(final VerifyEvent e) {
			if (controlCCombo != null) {
				e.doit = verifyEntryAndStoreValueCCombo(e.text, e.keyCode);
			} else if (controlCombo != null) {
				e.doit = verifyEntryAndStoreValueCombo(e.text, e.keyCode);
			}
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
			//open the flag to fire the listeners
			changedAfterFocus = true;
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
			//open the flag to fire the listeners
			changedAfterFocus = true;
		}
	};
	
	/**
	 * Create the combo control
	 * 
	 * @param parent the parent
	 * @param style the style bits, the supported ones are the same of a standard SWT text widget
	 * @param decimalDigitsShown the minimum number of decimal digits displayed when formatting the value, must be not negative
	 * @param decimalDigitsAccepted maximum number of decimal digits accepted. Set this to 0 mean no decimal digits, must be greater or equal of 
	 * decimalDigitsShown	 
	 */
	public NumericCombo(Composite parent, int style, int decimalDigitsShown, int decimalDigitsAccepted){
		super(parent, SWT.NONE);
		createControls();
		currentColor = getForeground();
		this.formatter = new ValidatedDecimalFormat(decimalDigitsShown, decimalDigitsAccepted);
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
		super(parent, SWT.NONE);
		createControls();
		currentColor = getForeground();
		addListeners();
		this.formatter = formatter;
		Assert.isTrue(formatter != null, "The formatter can't be null");
	}
	
	/**
	 * Initialize the default size if necessary then build the appropriated
	 * combo for the current OS, CCombo for windows, Combo for every others
	 */
	protected void createControls(){
		setLayout(mainLayout);
		if (defaultSize == null){
			Combo tempCombo = new Combo(this, SWT.DROP_DOWN);
			defaultSize = tempCombo.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			tempCombo.dispose();
		}
		if (Util.isWindows()){
			controlCCombo = new CCombo(this, SWT.BORDER);
		} else {
			controlCombo = new Combo(this, SWT.DROP_DOWN);
		}
		layout();
	}
	
	/**
	 * Add the listeners on the widget
	 */
	protected void addListeners(){
		addVerifyListener(inputVerifier);
		addModifyListener(inputNotifier);
		addComboSelectionListener(selectionNotifier);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent e) {
				//The set value on focus lost is done always to avoid
				//the mac text reset bug
				setValue(storedValue, true);
				if (changedAfterFocus){
					//The listener are fired instead only if the value changed
					//after the focus gain
					fireListeners();
				}
				changedAfterFocus = false;
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				changedAfterFocus = false;
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
				setComboForeground(ColorConstants.gray);
			} else {
				setComboForeground(currentColor);
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
		if (!isInherited) setComboForeground(color);
	}
	
	@Override
	public Color getForeground() {
		if (controlCombo != null){
			return controlCombo.getForeground();
		} else if (controlCCombo != null){
			controlCCombo.getForeground();
		}
		return null;
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
		} else {
			if (isNullable){
				storedValue = null;
				if (defaultValue != null){
					if (formatText) {
						setText(formatter.format(defaultValue));
					} else {
						setText(defaultValue.toString());
					}
				} else {
					setText("");
				}
				setSelection(new Point(0, getText().length()));
			} else {
				throw new IllegalArgumentException("The widget can not accept null values when the isNullable property is false");
			}
		}
	}
	
	/**
	 * Verify the entry and store the value in the field storedValue, this is used for the ccombo
	 * 
	 * @param entry entry to check
	 * @param keyCode code of the typed key
	 * @return <code>true</code> if the entry if correct, <code>false</code>
	 *         otherwise
	 */
	private boolean verifyEntryAndStoreValueCCombo(final String entry, final int keyCode) {
		String work = "";
		if (keyCode == SWT.DEL) {
			Point cursorSelection = controlCCombo.getSelection();
			String text = getText();
			if (cursorSelection.x == cursorSelection.y && cursorSelection.x != text.length()){
				work = StringUtil.removeCharAt(getText(), cursorSelection.x);
			} else {
				work = text.substring(0, cursorSelection.x) + text.substring(cursorSelection.y, text.length());
			}
		} else if (keyCode == SWT.BS) {
			Point cursorSelection = controlCCombo.getSelection();
			if (cursorSelection.x == cursorSelection.y && cursorSelection.x != 0){
				work = StringUtil.removeCharAt(getText(), cursorSelection.x - 1);
			} else if (cursorSelection.x != cursorSelection.y) {
				String text = getText();
				work = text.substring(0, cursorSelection.x) + text.substring(cursorSelection.y, text.length());
			}
		} else if (keyCode == 0) {
			work = entry;
		} else {
			Point cursorSelection = controlCCombo.getSelection();
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
	 * Verify the entry and store the value in the field storedValue, this is used for the standard combo
	 * 
	 * @param entry entry to check
	 * @param keyCode code of the typed key
	 * @return <code>true</code> if the entry if correct, <code>false</code>
	 *         otherwise
	 */
	private boolean verifyEntryAndStoreValueCombo(final String entry, final int keyCode) {
		String work = "";
		if (keyCode == SWT.DEL) {
			work = StringUtil.removeCharAt(getText(), getCaretPosition());
		} else if (keyCode == SWT.BS) {
			Point cursorSelection = controlCombo.getSelection();
			if (cursorSelection.x == cursorSelection.y && cursorSelection.x != 0){
				work = StringUtil.removeCharAt(getText(), getCaretPosition() - 1);
			} else if (cursorSelection.x != cursorSelection.y) {
				String text = getText();
				work = text.substring(0, cursorSelection.x) + text.substring(cursorSelection.y, text.length());
			}
		} else if (keyCode == 0) {
			work = entry;
		} else {
			Point cursorSelection = controlCombo.getSelection();
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
			if (minimum > defaultMin) defaultMin = minimum;
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
			if (defaultValue != null){
				defaultMin = defaultValue.intValue();
			}
			if (minimum > defaultMin) defaultMin = minimum;
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
	
	public void cut() {
		comboCut();
		setValue(null);
		fireListeners();
	}
	
	public void paste() {
		comboPaste();
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
	public void setText(String string) {
		removeVerifyListener(inputVerifier);
		removeModifyListener(inputNotifier);
		setComboText(string);
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
	
	public void select(int index) {
		removeComboSelectionListener(selectionNotifier);
		comboSelect(index);
		int count = getItemCount ();
		if (0 <= index && index < count) {
			if (index == getSelectionIndex()) return;
			setValue(Double.parseDouble(getItem(index)));
		}
		addComboSelectionListener(selectionNotifier);
	}
	
	//THE FOLLOWING METHODS RECRATE SOME API OF THE COMBO AND CALL THEM ON
	//THE APPROPRIATE COMBO, DEPENDING ON WHICH WAS INITIALIZED

	protected void comboSelect(int index){
		if (controlCombo != null){
			controlCombo.select(index);
		} else if (controlCCombo != null){
			controlCCombo.select(index);
		}
	}
	
	public int getItemCount(){
		if (controlCombo != null){
			return controlCombo.getItemCount();
		} else if (controlCCombo != null){
			return controlCCombo.getItemCount();
		}
		return 0;
	}
	
	public int getSelectionIndex(){
		if (controlCombo != null){
			return controlCombo.getSelectionIndex();
		} else if (controlCCombo != null){
			return controlCCombo.getSelectionIndex();
		}
		return 0;
	}
	
	public String getItem(int index){
		if (controlCombo != null){
			return controlCombo.getItem(index);
		} else if (controlCCombo != null){
			return controlCCombo.getItem(index);
		}
		return null;
	}
	
	public void setItems(String[] items) {
		removeVerifyListener(inputVerifier);
		removeModifyListener(inputNotifier);
		setComboItems(items);
		addVerifyListener(inputVerifier);
		addModifyListener(inputNotifier);
	}
	
	protected void setComboItems(String[] items){
		if (controlCombo != null){
			controlCombo.setItems(items);
		} else if (controlCCombo != null){
			controlCCombo.setItems(items);
		}
	}
	
	public int getCaretPosition(){
		if (controlCombo != null){
			return controlCombo.getCaretPosition();
		} else if (controlCCombo != null){
			return controlCCombo.getSelection().y;
		}
		return 0;
	}
	
	public String getText(){
		if (controlCombo != null){
			return controlCombo.getText();
		} else if (controlCCombo != null){
			return controlCCombo.getText();
		}
		return null;
	}
	
	@Override
	public void setMenu(Menu menu) {
		if (controlCombo != null){
			controlCombo.setMenu(menu);
		} else if (controlCCombo != null){
			controlCCombo.setMenu(menu);
		}
	}
	
	@Override
	public Menu getMenu() {
		if (controlCombo != null){
			controlCombo.getMenu();
		} else if (controlCCombo != null){
			controlCCombo.getMenu();
		}
		return null;
	}
	
	protected void addVerifyListener(VerifyListener listener){
		if (controlCombo != null){
			controlCombo.addVerifyListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.addVerifyListener(listener);
		}
	}
	
	protected void addModifyListener(ModifyListener listener){
		if (controlCombo != null){
			controlCombo.addModifyListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.addModifyListener(listener);
		}
	}
	
	protected void removeVerifyListener(VerifyListener listener){
		if (controlCombo != null){
			controlCombo.removeVerifyListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.removeVerifyListener(listener);
		}
	}
	
	protected void removeModifyListener(ModifyListener listener){
		if (controlCombo != null){
			controlCombo.removeModifyListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.removeModifyListener(listener);
		}
	}
	
	protected void addComboSelectionListener(SelectionListener listener){
		if (controlCombo != null){
			controlCombo.addSelectionListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.addSelectionListener(listener);
		}
	}
	
	protected void removeComboSelectionListener(SelectionListener listener){
		if (controlCombo != null){
			controlCombo.removeSelectionListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.removeSelectionListener(listener);
		}
	}
	
	@Override
	public void addFocusListener(FocusListener listener){
		if (controlCombo != null){
			controlCombo.addFocusListener(listener);
		} else if (controlCCombo != null){
			controlCCombo.addFocusListener(listener);
		}
	}
	
	protected void setComboText(String text){
		if (controlCombo != null){
			controlCombo.setText(text);
		} else if (controlCCombo != null){
			controlCCombo.setText(text);
		}
	}
	
	protected void comboCut(){
		if (controlCombo != null){
			controlCombo.cut();
		} else if (controlCCombo != null){
			controlCCombo.cut();
		}
	}
	
	protected void comboPaste(){
		if (controlCombo != null){
			controlCombo.paste();
		} else if (controlCCombo != null){
			controlCCombo.paste();
		}
	}
	
	public void setSelection(Point selection){
		if (controlCombo != null){
			controlCombo.setSelection(selection);
		} else if (controlCCombo != null){
			controlCCombo.setSelection(selection);
		}
	}
	
	@Override
	public void setBackground(Color color) {
		if (controlCombo != null){
			controlCombo.setBackground(color);
		} else if (controlCCombo != null){
			controlCCombo.setBackground(color);
		}
	}
	
	protected void setComboForeground(Color color){
		if (controlCombo != null){
			controlCombo.setForeground(color);
		} else if (controlCCombo != null){
			controlCCombo.setForeground(color);
		}
	}
}
