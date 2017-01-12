/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.section.report.util.Unit.PixelConversionException;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.dialog.ItemPropertyElementDialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Widget used to insert values in different measure units. The measure units list are built using the 
 * combo entry of the widget definitions. If they are not provided it will use a default set. This widget
 * doesn't do any conversion on the inserted value, simply append the numeric inserted value to the measure unit.
 * The measure unit is stored into the model with a separate properties.
 * 
 * @author Orlandin Marco
 *
 */
public class FixedMeasurePropertyDescription extends AbstractExpressionPropertyDescription<String> implements IDialogProvider {
	
	/**
	 * Hash map the bind a measure unit, by its key, to a series of method to convert and handle that measure
	 */
	protected Map<String, String> unitsMap = null;
	
	/**
	 * String added to the autocomplete
	 */
	protected String[] autocompleteValues;
	
	/**
	 * The color used as background when the control is not in error status. 
	 * This is initialized when the control is created
	 */
	protected Color defaultBackgroundColor = null;
	
	/**
	 * The key used to store inside the widget the measure units popup menu
	 */
	protected static final String POPUP_KEY = "measureUnitMenu";

	/**
	 * The key used to store inside the widget the focus listener
	 */
	protected static final String FOCUS_KEY = "focusListener";
	
	/**
	 * Formatter used to format the number 
	 */
	protected static final DecimalFormat format = new DecimalFormat("0.################");
	
	/**
	 * Name of the properties used to store in the model the last selected measure unit for this
	 * property
	 */
	protected static final String CURRENT_MEASURE_KEY = ".measureUnit";
	
	protected Number min;
	
	protected Number max;

	public FixedMeasurePropertyDescription() {
	}
	
	public FixedMeasurePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue, long min, long max, Map<String, String> unitsMap) {
		super(name, label, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
		this.unitsMap = unitsMap;
		autocompleteValues = buildAtuocompleteValues();
	}

	public FixedMeasurePropertyDescription(String name, String label, String description, boolean mandatory, long min, long max, Map<String, String> unitsMap) {
		super(name, label, description, mandatory);
		this.min = min;
		this.max = max;
		this.unitsMap = unitsMap;
		autocompleteValues = buildAtuocompleteValues();
	}
	
	public Number getMin() {
		return min;
	}

	public Number getMax() {
		return max;
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);

		final Text simpleControl = new Text(cmp.getSecondContainer(), SWT.BORDER); //$NON-NLS-1$
		// Flag used to overcome the problem of focus events in Mac OS X
		// - JSS Bugzilla 42999
		// - Eclipse Bug 383750
		// It makes sense only on E4 platform and Mac OS X operating systems.
		FocusListener focusListener = new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (UIUtil.isMacAndEclipse4()) {
					if (((Text) e.getSource()).isDisposed())
						return;
					wiProp.updateWidget();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				handleEdit(simpleControl, wiProp);
			}
		};
		simpleControl.addFocusListener(focusListener);
		//Store inside the control the focus listener, so it can be removed and added another time in some case
		simpleControl.setData(FOCUS_KEY, focusListener);
		
		
		simpleControl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR)
					handleEdit(simpleControl, wiProp);
			}
		});
		simpleControl.addMouseListener(new MouseClickListener(simpleControl, wiProp));
		
		//add the autocomplete only if there are more then one entry
		if (autocompleteValues.length > 1){
			new AutoCompleteField(simpleControl, new AutoCompleteMeasure(simpleControl), autocompleteValues);
		}

		cmp.getSecondContainer().setData(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		defaultBackgroundColor = simpleControl.getBackground();
		simpleControl.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
			
		});
		setupContextMenu(simpleControl, wiProp);
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Set the value inside the correct control, if the editor is in 
	 * expression mode or not
	 */
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			boolean isFallback = false;
			Text simpleControl = (Text)cmp.getSecondContainer().getData();
			if (!hasError(simpleControl)){
				//avoid to update the content if it is in error status to allow the user
				//to fix it
				String v = wip.getStaticValue();
				if (v == null && wip.getFallbackValue() != null){
					v = wip.getFallbackValue().toString();
					isFallback = true;
				}
				setDataNumber(v, simpleControl, wip);
				
				changeFallbackForeground(isFallback, simpleControl);
				cmp.switchToSecondContainer();
			}
		}
	}
	
	/**
	 * Build the array of string representing the autocomplete entries. 
	 * They are based on the unique measure values
	 * 
	 * @return a not null array of string
	 */
	private String[] buildAtuocompleteValues(){
		String[] result = new String[unitsMap.keySet().size()];
		int index = 0;
		for(String measure : unitsMap.keySet()){
			result[index] = measure;
			index++;
		}
		return result;
	}
	
	/**
	 * Receive a number and set it in the text widget
	 * 
	 * @param f the number
	 * @param insertField the text widget, must be not null
	 * @param wiProp the WItemProperty used to read the measure unit from the model
	 */
	public void setDataNumber(String value, Text insertField, IWItemProperty wiProp) {
		if (value != null) {
			Point oldpos = insertField.getSelection();
			String measureUnitText = getMeasureUnit(wiProp);
			Double number = null;
			String roundedNumber = null;
			boolean error = false;
			String measureUnit = unitsMap.get(measureUnitText);
			if (measureUnitText == null || measureUnit == null){
				setErrorStatus("Measure Unit not recognized", insertField);
				error = true;
			} else {
				String numberText = getNumericValue(value);
				try{
					 number = Double.valueOf(numberText);
					 roundedNumber = getRoundedValue(measureUnit, number);
				} catch (Exception ex){
					setErrorStatus("The number is not valid", insertField);
					error = true;
				}
			}
			if (error){
				insertField.setText(value);
			} else {
				setErrorStatus(null, insertField);
				insertField.setText(roundedNumber + measureUnitText);
			}
			
			if (insertField.getText().length() >= oldpos.y){
				insertField.setSelection(oldpos);
			}
		} else {
			insertField.setText(""); //$NON-NLS-1$
		}
	}

	@Override
	public String getToolTip() {
		String tt = super.getToolTip();
		if (getMin() != null)
			tt += "\nmin: " + getMin();
		if (getMax() != null)
			tt += "\nmax: " + getMax();
		return tt;
	}
	
	/**
	 * Return the measure unit typed in the textfield
	 * 
	 * @param value content of the text field
	 * @return measure unit, it's the last alphabetical string in the textfield or null
	 * if there is no alphabetical value
	 */
	private String getMeasureUnitFromText(String value) {
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
	 * @param wItemProperty the WItemProperty to write the property on the element
	 */
	private void setMeasureUnit(String measureUnitKey, IWItemProperty wItemProperty) {
		String propertyName = wItemProperty.getPropertyName();
		String measureUnitProperty = propertyName + CURRENT_MEASURE_KEY;
		wItemProperty.getPropertyEditor().createUpdateProperty(measureUnitProperty, measureUnitKey != null ? measureUnitKey.toLowerCase() :  null, null);
	}
	
	/**
	 * Return the current measure unit reading it from the element model
	 * 
	 * @param wItemProperty the WItemProperty to read the property from the element
	 * @return  the key of the measure, can be null
	 */
	private String getMeasureUnit(IWItemProperty wItemProperty) {
		String propertyName = wItemProperty.getPropertyName();
		String measureUnitProperty = propertyName + CURRENT_MEASURE_KEY;
		String result = wItemProperty.getPropertyEditor().getPropertyValue(measureUnitProperty);
		return result != null ? result.toLowerCase() : null;
	}
	
	/**
	 * Return the value in the text widget, it's returned as pixel
	 * 
	 * @param insertField the text widget, must be not null
	 * @return the value in the textfield as pixel
	 */
	protected String getNumericValue(String textualValue) {
		String text = textualValue.trim().toLowerCase();
		String key = getMeasureUnitFromText(text);
		if (key != null) {
			String value = text.substring(0, text.indexOf(key));
			if (value != null) return value;
		}
		return text;
	}
	
	/**
	 * Round the value inserted by the user using the static formatter. This
	 * will remove decimal digits when using pixels or remove unnecessary zeores
	 * from the decimal digits in the other cases
	 *  
	 * @param measureUnitKey the key of the measure unit
	 * @param value the value to format
	 * @return the formatted value
	 */
	protected String getRoundedValue(String measureUnitKey, Double value){
		if (value == null) return null;
		if (measureUnitKey.trim().toLowerCase().equals("px")){
			return String.valueOf(value.intValue());
		} else {
			return format.format(value); 
		}
	}
	
	/**
	 * This is the values that will be written in the model and it is generated by rounding the numeric
	 * value removing unecessary zeroes or decimal digit when using an integer unit (px). then at the
	 * number is appended the measure unit key.
	 * 
	 * @param measureUnitKey the key of the measure unit that should be written in the model
	 * @param measureUnitName the label of the measure, typed by the user
	 * @param value the numeric value
	 * @return the value that will be written on the model
	 */
	protected String getWrittenValue(String measureUnitKey, String measureUnitName, Double value){
		if (value == null) return null;
		String roundedValue = getRoundedValue(measureUnitKey, value);
		return roundedValue + measureUnitKey;
	}
	
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
		if (!popUpMenu.isDisposed()) {
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

	/**
	 * Create the popup menu
	 * 
	 * @param insertField the Text widget where the menu is set, must be not null
	 * @param wItemProp the {@link IWItemProperty} used to apply the action when an entry of the menu is selected, must be not null
	 */
	protected Menu createPopupMenu(Text insertField, IWItemProperty wItemProp) {
		final Menu popUpMenu = new Menu(insertField);
		insertField.setData(POPUP_KEY, popUpMenu);
		insertField.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				popUpMenu.dispose();
			}
		});
		// Add the new elements
		for (int i = 0; i < autocompleteValues.length; i++) {
			MenuItem item = new MenuItem(popUpMenu, SWT.PUSH);
			item.setText(autocompleteValues[i]);
			item.addSelectionListener(new MenuAction(autocompleteValues[i], insertField, wItemProp));
		}
		return popUpMenu;
	}
	
	@Override
	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (!wiProp.isExpressionMode() && txt instanceof Text){
			Text insertField = (Text)txt;
			String text = insertField.getText().trim().toLowerCase();
			if (!text.isEmpty()){
				String measureUnitName = getMeasureUnitFromText(text);
				String measureUnitKey = unitsMap.get(measureUnitName);
				if (measureUnitKey != null) {
					try{ 
						setMeasureUnit(measureUnitName, wiProp);
						Double value = Double.valueOf(getNumericValue(text));
						String writtenValue = getWrittenValue(measureUnitKey, measureUnitName, value);
						wiProp.setValue(writtenValue, null);
						setErrorStatus(null, insertField);
					} catch (NumberFormatException ex) {
						//The value can not be converted into a number
						setErrorStatus("The number is not valid", insertField);
					} catch (PixelConversionException ex){
						//The value can be converted into a number but not into an integer
						setErrorStatus(ex.getMessage(), insertField);
					}
				} else {
					//Measure unit not found
					setErrorStatus("Measure Unit not recognized", insertField);
				}	
			} else {
				wiProp.setValue(null, null);
			}
		} else super.handleEdit(txt, wiProp);
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
	 * Check if the control is in an error status. The background is used to do this
	 * check
	 * 
	 * @param insertField the text control
	 * @return true if it is in error status, false otherwise
	 */
	protected boolean hasError(Text insertField){
		return ColorConstants.red.equals(insertField.getBackground());
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
	 * This property description provide a custom dialog to allow to use also the property to handle the measure unit
	 */
	@Override
	public ItemPropertyElementDialog getDialog(final WItemProperty wItemProp) {
		ItemPropertyElementDialog result = new ItemPropertyElementDialog(UIUtils.getShell(), this, wItemProp){
			
			@Override
			public boolean close() {
				if (getReturnCode() == Dialog.OK){
					//when the dialog is closed force to lose focus to trigger the focus listener
					//otherwise the text is destroyed before the focus lost
					Control focusedControl = getShell().getDisplay().getFocusControl();
					if (focusedControl != null){
						//disabling a control force the focus lost on SWT
						focusedControl.setEnabled(false);
					}
					
					//Write the current measure unit in the model
					String propertyName = wItemProp.getPropertyName();
					String measureUnit = customPropertiesMap.get(propertyName + CURRENT_MEASURE_KEY);
					if (measureUnit != null){
						setMeasureUnit(measureUnit, wItemProp);
					}
				}
				return super.close();
			}
			
			@Override
			protected Control createDialogArea(Composite parent) {
				//On create write the measure unit property in the additional properties map
				String currentMeasureUnit = getMeasureUnit(wItemProp);
				String propertyName = wItemProp.getPropertyName();
				customPropertiesMap.put(propertyName + CURRENT_MEASURE_KEY, currentMeasureUnit);
				return super.createDialogArea(parent);
			}
		};
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
	
	protected Map<String, String> createMesureUnitsMap(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd){
		//setup the measures
		HashMap<String, String> i18nOpts = new HashMap<String, String>();
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			for (int i = 0; i < opts.length; i++) {
				i18nOpts.put(cd.getLocalizedString(opts[i][1]).toLowerCase().trim(), opts[i][0].trim());
			}
			for(String key : new ArrayList<String>(i18nOpts.values())){
				i18nOpts.put(key, key);
			}
		} else {
			//use default values
			i18nOpts.put("pixel", "px");
			i18nOpts.put("pixels", "px");
			i18nOpts.put("px", "px");
			i18nOpts.put("inches", "inch");
			i18nOpts.put("inch", "inch");
			i18nOpts.put("em", "em");
			i18nOpts.put("centimeter", "cm");
			i18nOpts.put("centimeters", "cm");
			i18nOpts.put("cm", "cm");
			i18nOpts.put("millimeter", "mm");
			i18nOpts.put("millimeters", "mm");
			i18nOpts.put("mm", "mm");
			i18nOpts.put("meter", "m");
			i18nOpts.put("meters", "m");
			i18nOpts.put("m", "m");
		}
		return i18nOpts;
	}

	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		Long min = createMin(cpd);
		Long max = createMax(cpd);
		Map<String, String> i18nOpts = createMesureUnitsMap(cd, cpd);
		FixedMeasurePropertyDescription intDesc = new FixedMeasurePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), 
																									cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), min, max, i18nOpts);
		intDesc.setReadOnly(cpd.isReadOnly());
		intDesc.setFallbackValue(cpd.getFallbackValue());
		intDesc.setjConfig(jConfig);
		return intDesc;
	}

	@Override
	public ItemPropertyDescription<String> clone() {
		FixedMeasurePropertyDescription result = new FixedMeasurePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.min = min;
		result.max = max;
		result.unitsMap = unitsMap;
		result.autocompleteValues = autocompleteValues;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	//ADDITIONAL CLASSES
	
	/**
	 * Read the measure unit and help to autocomplete
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class AutoCompleteMeasure extends TextContentAdapter {
		
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
	 * Listener that handle the double click on the Text, made the contextual menu appears
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class MouseClickListener implements MouseListener {
		
		/**
		 * The text widget
		 */
		private Text insertField;
		
		/**
		 * The {@link IWItemProperty} used to execute the change of value
		 */
		private IWItemProperty wItemProp;
		
		public MouseClickListener(Text insertField, IWItemProperty wItemProp) {
			this.insertField = insertField;
			this.wItemProp = wItemProp;
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			openPopupMenu(insertField, wItemProp);
		}

		@Override
		public void mouseDown(MouseEvent e) {
		}

		@Override
		public void mouseUp(MouseEvent e) {
		}
	}
	
	/**
	 * Action to execute when a new measure unit is selected from a popup combo
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class MenuAction implements SelectionListener {

		/**
		 * Key of the unit represented by this listener
		 */
		private String value;
		
		/**
		 * The text widget
		 */
		private Text insertField;
		
		/**
		 * The {@link IWItemProperty} used to execute the change of value
		 */
		private IWItemProperty wItemProperty;

		public MenuAction(String value, Text insertField, IWItemProperty wItemProperty) {
			this.value = value;
			this.insertField = insertField;
			this.wItemProperty = wItemProperty;
		}

		/**
		 * When a new measure unit is selected a new local is set and the conversion is done
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			String textValue = insertField.getText().trim();
			Point oldpos = insertField.getSelection();
			String numberText = getNumericValue(textValue);
			insertField.setText(numberText + value);
			if (insertField.getText().length() >= oldpos.y){
				insertField.setSelection(oldpos);
			}
			try{
				Double.valueOf(numberText);
				handleEdit(insertField, wItemProperty);
			} catch (Exception ex){
				setErrorStatus("The number is not valid", insertField);
			}
		
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
}

