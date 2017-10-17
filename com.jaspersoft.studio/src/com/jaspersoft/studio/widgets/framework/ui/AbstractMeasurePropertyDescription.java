/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.io.IOException;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;

import net.sf.jasperreports.engine.JRRuntimeException;

/**
 * Generic class to handle a measure unit widget. This define the common utility method for the dynamic 
 * widgets that can handle a measure unit
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractMeasurePropertyDescription<T> extends AbstractExpressionPropertyDescription<T> implements IDialogProvider {

	/**
	 * The key used to store inside the widget the measure units popup menu
	 */
	protected static final String POPUP_KEY = "measureUnitMenu";
	
	/**
	 * Name of the properties used to store in the model the last selected measure unit for this
	 * property
	 */
	public static final String CURRENT_MEASURE_KEY = "_measureUnit";
	
	/**
	 * The key used to store inside the widget the focus listener
	 */
	protected static final String FOCUS_KEY = "focusListener";
	
	/**
	 * The color used as background when the control is not in error status. 
	 * This is initialized when the control is created
	 */
	protected Color defaultBackgroundColor = null;
	
	protected Number min;
	
	protected Number max;
	
	public AbstractMeasurePropertyDescription(){
	}
	
	public AbstractMeasurePropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, long min, long max){
		super(name, label, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
	}

	public AbstractMeasurePropertyDescription(String name, String label, String description, boolean mandatory, long min, long max){
		super(name, label, description, mandatory);
		this.min = min;
		this.max = max;
	}
	
	public Number getMin() {
		return min;
	}

	public Number getMax() {
		return max;
	}
	
	/**
	 * Return the measure unit typed in the textfield
	 * 
	 * @param value content of the text field
	 * @return measure unit, it's the last alphabetical string in the textfield or null
	 * if there is no alphabetical value
	 */
	protected String getMeasureUnitFromText(String value) {
		String[] results = value.split("[^a-z]"); //$NON-NLS-1$
		// If the array is void then no measure unit are specified
		if (results.length == 0)
			return null;
		String measure = results[results.length - 1].trim();
		if (measure.isEmpty()){
			return null;
		}
		return measure.toLowerCase();
	}
	
	/**
	 * Update the current local measure unit for the element
	 * Set the measure value into the properties of the model
	 * 
	 * @param measureUnitKey the key of the measure unit to store
	 * @param measureUnitName the textual name of the measure unit
	 * @param wItemProperty the WItemProperty to write the property on the element
	 */
	protected void setMeasureUnit(String measureUnitKey, String measureUnitName, IWItemProperty wItemProperty) {
		String propertyName = wItemProperty.getPropertyName();
		String measureUnitProperty = propertyName + CURRENT_MEASURE_KEY;
		MeasureDefinition definition = new MeasureDefinition(measureUnitKey, measureUnitName);
		String encodedDefinition = encode(definition);
		wItemProperty.getPropertyEditor().createUpdateProperty(measureUnitProperty, encodedDefinition != null ? encodedDefinition.toLowerCase() :  null, null);
	}
	
	/**
	 * Remove the current local measure unit for the element
	 * 
	 * @param wItemProperty the WItemProperty to write the property on the element
	 */
	protected void removeMeasureUnit(IWItemProperty wItemProperty){
		String propertyName = wItemProperty.getPropertyName();
		String measureUnitProperty = propertyName + CURRENT_MEASURE_KEY;
		wItemProperty.getPropertyEditor().removeProperty(measureUnitProperty);
	}
	
	/**
	 * Return the current measure unit reading it from the element model
	 * 
	 * @param wItemProperty the WItemProperty to read the property from the element
	 * @return The {@link MeasureDefinition} that define the measure pair of key and textual name
	 */
	protected MeasureDefinition getMeasureUnit(IWItemProperty wItemProperty) {
		String propertyName = wItemProperty.getPropertyName();
		String measureUnitProperty = propertyName + CURRENT_MEASURE_KEY;
		String result = wItemProperty.getPropertyEditor().getPropertyValue(measureUnitProperty);
		MeasureDefinition value = decode(result);
		return value;
	}
	
	/**
	 * Build a {@link MeasureDefinition} from its JSON string.
	 * 
	 * @param encodedMeasure the JSON string of the class
	 * @return a {@link MeasureDefinition} or null if the JSON is invalid
	 */
	protected MeasureDefinition decode(String encodedMeasure)
	{
		MeasureDefinition result = null;
		if (encodedMeasure != null)
		{
			try 
			{
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				result = mapper.readValue(encodedMeasure, MeasureDefinition.class);  
			}
			catch (JsonMappingException e)
			{
				throw new JRRuntimeException(e);
			} 
			catch (JsonParseException e)
			{
				throw new JRRuntimeException(e);
			} 
			catch (IOException e)
			{
				throw new JRRuntimeException(e);
			} 
		}
		return result;
	}

	/**
	 * Create a JSON string from a {@link MeasureDefinition}.
	 * 
	 * @return its JSON string or null if the parameter is null
	 */
	protected String encode(MeasureDefinition value)
	{
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			result = mapper.writeValueAsString(value);
		}
		catch (JsonProcessingException e) 
		{
			throw new JRRuntimeException(e);
		}
		return result;
	}

	protected Long createMin(WidgetPropertyDescriptor cpd){
		Long min = null;
		//Setup the minimum
		if (cpd.getMin() != null){
			min = new Long(cpd.getMin());
		} else {
			min = Long.MIN_VALUE;
		}
	 	return min;
	}
	
	protected Long createMax(WidgetPropertyDescriptor cpd){
		Long max = null;
		//Setup the maximum
		if (cpd.getMax() != null){
			max = new Long(cpd.getMax());
		} else {
			max = Long.MAX_VALUE;
		}
		return max;
	}
	
	/**
	 * On macos the update of the color need some additional operation because of an SWT bug
	 * (https://bugs.eclipse.org/bugs/show_bug.cgi?id=346361). If the widget is focused it need
	 * to lose the focus to be updated correctly. For this reason the widget is forced to loose
	 * the focus and the it will regain it
	 * 
	 * @param color the color to set
	 */
	protected void updateBackground(Color color, Text control){
		if (Util.isMac() && control.isFocusControl() && !ModelUtils.safeEquals(color, control.getBackground())){
			FocusListener focusListner = (FocusListener)control.getData(FOCUS_KEY);
			control.removeFocusListener(focusListner);
			Point caretPosition = control.getSelection();
			boolean oldEnabled = control.getEnabled();
			control.setEnabled(false);//Force the focus lost
			control.setBackground(color);
			control.setEnabled(oldEnabled);
			control.setFocus();
			control.setSelection(caretPosition.x);
			control.addFocusListener(focusListner);
		} else {
			control.setBackground(color);
		}
	}
	
	/**
	 * Set the insert field into an error status, so with a red background
	 * and a tooltip that describe the error
	 * 
	 * @param message the error message, it will be used as tooltip, can be null
	 * for no error message (with null erase the old errors from the tooltip and 
	 * restore the default ones)
	 */
	protected void setErrorStatus(String message, Text insertField){
		if (message != null){
			updateBackground(ColorConstants.red, insertField);
			insertField.setToolTipText(message);
		} else {
			updateBackground(defaultBackgroundColor, insertField);
			insertField.setToolTipText(getToolTip());
		}
	}
	
	/**
	 * Create the popup menu
	 * 
	 * @param insertField the Text widget where the menu is set, must be not null
	 * @param wItemProp the {@link IWItemProperty} used to apply the action when an entry of the menu is selected, must be not null
	 */
	protected abstract Menu createPopupMenu(Text insertField, IWItemProperty wItemProp);
	
	/**
	 * Open the popoup menu inside the menumanger and place it under the text widget
	 * 
	 * @param insertField the text widget where the menu is set
	 * @param wItemProp the {@link IWItemProperty} used to propagate the property change when an 
	 * entry of the popup menu is selected
	 */
	protected void openPopupMenu(Text insertField, IWItemProperty wItemProp) {
		Menu popUpMenu = (Menu)insertField.getData(POPUP_KEY);
		if (popUpMenu == null){
			popUpMenu = createPopupMenu(insertField, wItemProp);
		}
		if (!popUpMenu.isDisposed() && popUpMenu.getItemCount() > 1 && !insertField.getText().trim().isEmpty()) {
			if (popUpMenu.isVisible()) {
				popUpMenu.setVisible(false);
			} else {
				locatePopupMenu(insertField, popUpMenu);
				popUpMenu.setVisible(true);
			}
		}
	}

	/**
	 * Set the menu in the right location, under the text widget
	 * 
	 * @param insertField the text widget, must be not null
	 * @param popUpMenu the menu, must be not null
	 */
	protected void locatePopupMenu(Text insertField, Menu popUpMenu) {
		Rectangle r = insertField.getBounds();
		r.x = r.y = 0;
		Point loc = insertField.toDisplay(r.x, r.y);
		loc.y += r.height;
		popUpMenu.setLocation(loc);
	}
	
	//ADDITIONAL CLASSES
	
	/**
	 * Read the measure unit and help to autocomplete
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	protected class AutoCompleteMeasure extends TextContentAdapter {
		
		private Text insertField;
		
		public AutoCompleteMeasure(Text insertField){
			this.insertField = insertField;
		}
		
		public String getControlContents(Control control) {
			String text = insertField.getText().trim().toLowerCase();
			String measureUnit = getMeasureUnitFromText(text);
			if (insertField.getCaretPosition() == text.length() && measureUnit != null)
				return measureUnit;
			else
				return ""; //$NON-NLS-1$
		}

		public void setControlContents(Control control, String text, int cursorPosition) {
			String textField = insertField.getText().trim().toLowerCase();
			String key = getMeasureUnitFromText(textField);
			String value = textField.substring(0, textField.indexOf(key));
			((Text) control).setText(value.concat(text));
			((Text) control).setSelection(cursorPosition, cursorPosition);
		}
	}
	
	/**
	 * Class used to serialize and deserialize a measure definition
	 *
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class MeasureDefinition{
		
		private String key;

		private String name;
		
		public MeasureDefinition(){
			this.key = "";
			this.name = "";
		}
		
		@JsonIgnore
		public MeasureDefinition(String measureKey, String measureName){
			Assert.isNotNull(measureName);
			Assert.isNotNull(measureKey);
			this.key = measureKey;
			this.name = measureName.trim().toLowerCase();
		}
		
		public String getKey(){
			return key;
		}
		
		public String getName(){
			return name;
		}

		public void setKey(String value){
			this.key = value;
		}
		
		public void setName(String value){
			this.name = value;
		}
	}
}
