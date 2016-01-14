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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

/**
 * This class emulate a spinner that accept the value null, the 
 * numeric part is handled by a custom swt text widget, written to
 * handle the numbers. The increment and decrement button are instead
 * painted on a composite created for it
 * 
 * @author Orlandin Marco
 *
 */
public class NullableSpinner extends Composite {

	/**
	 * The text where the value can be typed
	 */
	private NumericText text;
	
	/**
	 * Compiosite where the button are painted
	 */
	private Composite buttonContainer;

	/**
	 * Create an instance of the class
	 * 
	 * @param parent the parent composite
	 * @param style the stylebit used for the text control
	 * @param digits the number of digits handled by the control. Must be not negative.
	 */
	public NullableSpinner(Composite parent, int style, int digits) {
		super(parent, style);

		final GridLayout gd = new GridLayout(2, false);
		gd.horizontalSpacing = gd.verticalSpacing = 0;
		gd.marginWidth = gd.marginHeight = 0;
		setLayout(gd);

		createContent(style, digits);
		addTextListeners();

		//Add a listener to paint an inside border on the text
		text.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(ColorConstants.lightGray);
				Rectangle rect = text.getBounds();
        e.gc.setLineStyle(SWT.LINE_SOLID);
        e.gc.drawLine(0, 0, rect.width, 0);
        e.gc.drawLine(0, -1, 0, rect.height + 1);
        e.gc.drawLine(0, rect.height-1, rect.width, rect.height-1);
			}
		}); 
	}
	
	/**
	 * Create a spinner that can handle integer number
	 * 
	 * @param parent the parent composite
	 * @param style the stylebit used for the text control
	 */
	public NullableSpinner(Composite parent, int style) {
		this(parent, style, 0);
	}

	/**
	 * Create the content of the widget
	 * 
	 * @param style style of the widget
	 * @param digits the number of decimal digits handled by the text control
	 */
	private void createContent(int style, int digits) {
		createText(style ^ SWT.BORDER, digits);
		
		GridLayout gd = new GridLayout(1, false);
		gd.horizontalSpacing = gd.verticalSpacing = 0;
		gd.marginWidth = gd.marginHeight = 0;
		
		buttonContainer = new Composite(this, SWT.NONE);
		buttonContainer.setLayout(gd);
		GridData buttonsData = new GridData(SWT.FILL, SWT.FILL, false, true);
		buttonsData.widthHint = 15;
		buttonsData.minimumWidth =15;
		buttonsData.heightHint = 18;
		buttonContainer.setLayoutData(buttonsData);
		//Listener to paint the spinner buttons
		buttonContainer.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				Color currentBackgroun = gc.getBackground();
				Color currentForeground = gc.getForeground();
				Rectangle bounds = buttonContainer.getBounds();
				
				//draw the middle line border
				
				gc.setBackground(ColorConstants.white);
				gc.setForeground(ColorConstants.lightGray);
				gc.fillRectangle(0, 0, bounds.width, bounds.height);
				int middleButtonHeight = bounds.height / 2;
				int middleButtonWidth = bounds.width / 2;
				gc.drawLine(0, middleButtonHeight, bounds.width + 2, middleButtonHeight);
				
				//draw the arrows
				
				gc.setBackground(ColorConstants.black);
				int arrowHeight = 3;
				int arrowWidth = 3;
				int heightSpan = (middleButtonHeight - arrowHeight) / 2;
				int[] upArrow = {middleButtonWidth, heightSpan, middleButtonWidth - arrowWidth, middleButtonHeight - heightSpan, middleButtonWidth + arrowWidth, middleButtonHeight - heightSpan};
				int[] downArrow = {middleButtonWidth, bounds.height - heightSpan, middleButtonWidth - arrowWidth, middleButtonHeight + heightSpan, middleButtonWidth + arrowWidth, middleButtonHeight + heightSpan};
				
				gc.fillPolygon(upArrow);
				gc.fillPolygon(downArrow);
				
				//draw the border
				
        gc.setLineStyle(SWT.LINE_SOLID);
        
        //Left line
        gc.drawLine(0, -1 , 0, bounds.height + 10);
        
        //Top line
        gc.drawLine(-1, 0, bounds.width, 0);
        
        //right line
        gc.drawLine(bounds.width-1, 0, bounds.width-1, bounds.height);
        
        //bottom line
        gc.drawLine(-1, bounds.height-1, bounds.width, bounds.height-1);
				
				gc.setBackground(currentBackgroun);
				gc.setForeground(currentForeground);
			}
		});
		
		//add the increment/decrement listener only if it is not read only
		boolean readOnly = (style & SWT.READ_ONLY) == SWT.READ_ONLY;
		if (!readOnly){
			buttonContainer.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					Rectangle bounds = buttonContainer.getBounds();
					if (e.y < bounds.height / 2){
						increaseValue();
					} else {
						decreaseValue();
					}
				}
			});
		}
	}

	/**
	 * Create the text zone
	 * 
	 * @param style style of the widget
	 * @param digits the number of decimal digits handled by the text control
	 */
	private void createText(int style, int digits) {
		this.text = new NumericText(this, style, digits);
		final GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		gd.minimumWidth = 30;
		gd.widthHint = 30;
		gd.heightHint = 18;
		this.text.setLayoutData(gd);
	}
	

	/**
	 * Add the text listeners
	 */
	private void addTextListeners() {

		text.addKeyListener(new KeyAdapter() {

			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt.events.KeyEvent)
			 */
			@Override
			public void keyReleased(final KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP) {
					text.increment();
				}
				if (e.keyCode == SWT.ARROW_DOWN) {
					text.decrement();
				}
			}

		});
	}


	/**
	 * Increase the value stored in this snippet
	 * 
	 */
	public void increaseValue() {
		text.increment();
	}

	/**
	 * Decrease the value stored in this snippet
	 * 
	 */
	public void decreaseValue() {
		text.decrement();
	}

	/**
	 * Copies the selected text.
	 * <p>
	 * The current selection is copied to the clipboard.
	 * </p>
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void copy() {
		this.checkWidget();
		this.text.copy();
	}

	/**
	 * Cuts the selected text.
	 * <p>
	 * The current selection is first copied to the clipboard and then deleted
	 * from the widget.
	 * </p>
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void cut() {
		this.checkWidget();
		this.text.cut();
	}

	/**
	 * Returns the numeric value stored inside the control
	 * 
	 * @return the numeric value, could be null
	 */
	public Number getValue() {
		this.checkWidget();
		return text.getValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as an integer
	 * 
	 * @return the numeric value, could be null
	 */
	public Integer getValueAsInteger() {
		this.checkWidget();
		Number value = getValue();
		if (value == null) return null;
		else return value.intValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as an double
	 * 
	 * @return the numeric value, could be null
	 */
	public Double getValueAsDouble(){
		this.checkWidget();
		Number value = getValue();
		if (value == null) return null;
		else return value.doubleValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as a long
	 * 
	 * @return the numeric value, could be null
	 */
	public Long getValueAsLong(){
		this.checkWidget();
		Number value = getValue();
		if (value == null) return null;
		else return value.longValue();
	}
	
	/**
	 * Returns the numeric value stored inside the control, as an float
	 * 
	 * @return the numeric value, could be null
	 */
	public Float getValueAsFloat(){
		this.checkWidget();
		Number value = getValue();
		if (value == null) return null;
		else return value.floatValue();
	}
	
	/**
	 * Returns a string containing a copy of the contents of the receiver's text
	 * field, or an empty string if there are no contents.
	 * 
	 */
	public String getText() {
		this.checkWidget();
		return this.text.getText();
	}

	/**
	 * Returns the maximum number of characters that the receiver's text field
	 * is capable of holding. If this has not been changed by
	 * <code>setTextLimit()</code>, it will be the constant
	 * <code>Spinner.LIMIT</code>.
	 * 
	 * @return the text limit
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see #LIMIT
	 */
	public int getTextLimit() {
		this.checkWidget();
		return this.text.getTextLimit();
	}

	/**
	 * Pastes text from clipboard.
	 * <p>
	 * The selected text is deleted from the widget and new text inserted from
	 * the clipboard.
	 * </p>
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void paste() {
		this.checkWidget();
		this.text.paste();
	}

	/**
	 * Sets the amount that the receiver's value will be modified by when the
	 * up/down arrows are pressed to the argument, which must be at least one.
	 * 
	 * @param value the new increment (must be greater than zero)
	 * 
	 */
	public void setIncrement(int value) {
		this.checkWidget();
		text.setIncrementStep(value);
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
	public void setMaximum(double value) {
		this.checkWidget();
		text.setMaximum(value);
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
	public void setMinimum(double value) {
		this.checkWidget();
		text.setMinimum(value);
	}

	/**
	 * Sets the <em>selection</em>, which is the receiver's position, to the
	 * argument. If the argument is not within the range specified by minimum
	 * and maximum, it will be adjusted to fall within this range.
	 * 
	 * @param value the new selection, can be null but only if the isNullable flag is
	 * set to true (it is by default)
	 * 
	 */
	public void setValue(Number selection) {
		this.checkWidget();
		text.setValue(selection);
	}
	
	/**
	 * Set the formatter for the numeric text
	 * 
	 * @param formatter a not null number formatter
	 */
	public void setFormat(NumberFormat formatter){
		text.setFormat(formatter);
	}
	
	/**
	 * Sets the maximum number of characters that the receiver's text field is
	 * capable of holding to be the argument.
	 * <p>
	 * To reset this value to the default, use
	 * <code>setTextLimit(Spinner.LIMIT)</code>. Specifying a limit value larger
	 * than <code>Spinner.LIMIT</code> sets the receiver's limit to
	 * <code>Spinner.LIMIT</code>.
	 * </p>
	 * 
	 * @param limit new text limit
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_CANNOT_BE_ZERO - if the limit is zero</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see #LIMIT
	 */
	public void setTextLimit(int limit) {
		this.checkWidget();
		this.text.setTextLimit(limit);
	}

	/**
	 * Sets the receiver's selection, minimum value, maximum value,
	 * increment and formatter all at once.
	 * <p>
	 * Note: This is similar to setting the values individually using the
	 * appropriate methods, but may be implemented in a more efficient fashion
	 * on some platforms.
	 * </p>
	 * 
	 * @param selection the new selection value
	 * @param minimum the new minimum value
	 * @param maximum the new maximum value
	 * @param increment the new increment value
	 * @param formatter the formatter for the decimal number
	 */
	public void setValues(Number selection, int minimum, int maximum, int increment, NumberFormat formatter) {
		setMinimum(minimum);
		setMaximum(maximum);
		setIncrement(increment);
		setFormat(formatter);
		setValue(selection);
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
	public void setValues(Number selection, final int minimum, final int maximum) {
		setMinimum(minimum);
		setMaximum(maximum);
		setValue(selection);
	}

	/**
	 * Sets the receiver's drag detect state. If the argument is
	 * <code>true</code>, the receiver will detect drag gestures, otherwise
	 * these gestures will be ignored.
	 * 
	 * @param dragDetect the new drag detect state
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public boolean setFocus() {
		this.checkWidget();
		return text.setFocus();
	}

	/**
	 * Forces the receiver to have the <em>keyboard focus</em>, causing all
	 * keyboard events to be delivered to it.
	 * 
	 * @return <code>true</code> if the control got focus, and
	 *         <code>false</code> if it was unable to.
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see #setFocus
	 */
	@Override
	public boolean forceFocus() {
		this.checkWidget();
		return text.forceFocus();
	}

	/**
	 * Sets the receiver's background color to the color specified by the
	 * argument, or to the default system color for the control if the argument
	 * is null.
	 * <p>
	 * Note: This operation is a hint and may be overridden by the platform. For
	 * example, on Windows the background of a Button cannot be changed.
	 * </p>
	 * 
	 * @param color the new color (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setBackground(Color color) {
		super.setBackground(color);
		text.setBackground(color);
	}

	/**
	 * Sets the receiver's background image to the image specified by the
	 * argument, or to the default system color for the control if the argument
	 * is null. The background image is tiled to fill the available space.
	 * <p>
	 * Note: This operation is a hint and may be overridden by the platform. For
	 * example, on Windows the background of a Button cannot be changed.
	 * </p>
	 * 
	 * @param image the new image (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument is not a
	 *                bitmap</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setBackgroundImage(Image image) {
		super.setBackgroundImage(image);
		text.setBackgroundImage(image);
	}

	/**
	 * Sets the receiver's cursor to the cursor specified by the argument, or to
	 * the default cursor for that kind of control if the argument is null.
	 * <p>
	 * When the mouse pointer passes over a control its appearance is changed to
	 * match the control's cursor.
	 * </p>
	 * 
	 * @param cursor the new cursor (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setCursor(final Cursor cursor) {
		super.setCursor(cursor);
		text.setCursor(cursor);
		buttonContainer.setCursor(cursor);
	}

	/**
	 * Enables the receiver if the argument is <code>true</code>, and disables
	 * it otherwise. A disabled control is typically not selectable from the
	 * user interface and draws with an inactive or "grayed" look.
	 * 
	 * @param enabled the new enabled state
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		text.setEnabled(enabled);
		buttonContainer.setEnabled(enabled);
	}

	/**
	 * Sets the font that the receiver will use to paint textual information to
	 * the font specified by the argument, or to the default font for that kind
	 * of control if the argument is null.
	 * 
	 * @param font the new font (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		this.text.setFont(font);
	}

	/**
	 * Sets the receiver's foreground color to the color specified by the
	 * argument, or to the default system color for the control if the argument
	 * is null.
	 * <p>
	 * Note: This operation is a hint and may be overridden by the platform.
	 * </p>
	 * 
	 * @param color the new color (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setForeground(final Color color) {
		super.setForeground(color);
		text.setForeground(color);
	}

	/**
	 * Sets the receiver's pop up menu to the argument. All controls may
	 * optionally have a pop up menu that is displayed when the user requests
	 * one for the control. The sequence of key strokes, button presses and/or
	 * button releases that are used to request a pop up menu is platform
	 * specific.
	 * <p>
	 * Note: Disposing of a control that has a pop up menu will dispose of the
	 * menu. To avoid this behavior, set the menu to null before the control is
	 * disposed.
	 * </p>
	 * 
	 * @param menu the new pop up menu
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_MENU_NOT_POP_UP - the menu is not a pop up menu</li>
	 *                <li>ERROR_INVALID_PARENT - if the menu is not in the same
	 *                widget tree</li>
	 *                <li>ERROR_INVALID_ARGUMENT - if the menu has been disposed
	 *                </li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setMenu(final Menu menu) {
		super.setMenu(menu);
		text.setMenu(menu);
		buttonContainer.setMenu(menu);
	}

	/**
	 * Sets the receiver's tool tip text to the argument, which may be null
	 * indicating that the default tool tip for the control will be shown. For a
	 * control that has a default tool tip, such as the Tree control on Windows,
	 * setting the tool tip text to an empty string replaces the default,
	 * causing no tool tip text to be shown.
	 * <p>
	 * The mnemonic indicator (character '&amp;') is not displayed in a tool
	 * tip. To display a single '&amp;' in the tool tip, the character '&amp;'
	 * can be escaped by doubling it in the string.
	 * </p>
	 * 
	 * @param string the new tool tip text (or null)
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	@Override
	public void setToolTipText(final String tooltipText) {
		super.setToolTipText(tooltipText);
		text.setToolTipText(tooltipText);
		buttonContainer.setToolTipText(tooltipText);
	}

	/**
	 * Set if the value shown is inherited or not (an inherited value uses a different font color)
	 * 
	 * @param value true if the value is inherited, false otherwise
	 */
	public void setInherited(boolean value){
		text.setInherited(value);
	}
	
	/**
	 * Set the flag to enable or disable the acceptance of empty null value
	 * 
	 * @param value true if the null value is accepted, false otherwise
	 */
	public void setNullable(boolean value){
		text.setNullable(value);
	}
	
	/**
	 * Add a selection listener to the text control. The selection listeners are
	 * called each time the numeric value changes, but not when the value is set programmatically
	 * 
	 * @param listener a not null listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		text.addSelectionListener(listener);
	}
	
	/**
	 * Return the textual control inside the spinner
	 * 
	 * @return a not null text control
	 */
	public NumericText getTextControl(){
		return text;
	}
	
	/**
	 * Return the caret position in the text area
	 * 
	 * @return the position of the caret
	 */
	public int getCaretPosition(){
		return text.getCaretPosition();
	}
	
	/**
	 * Set the text selection area
	 * 
	 * @param start start of the selection 
	 * @param end end of the selection
	 */
	public void setSelection(int start, int end){
		text.setSelection(start, end);
	}
}
