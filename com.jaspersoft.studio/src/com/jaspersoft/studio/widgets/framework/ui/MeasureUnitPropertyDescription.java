/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.HashMap;

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

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.section.report.util.Unit;
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
 * Widget used to insert values in different measure units
 * 
 * @author Orlandin Marco
 *
 */
public class MeasureUnitPropertyDescription extends AbstractExpressionPropertyDescription<Long> implements IDialogProvider {
	
	/**
	 * Hash map the bind a measure unit, by its key, to a series of method to convert and handle that measure
	 */
	private static HashMap<String, MeasureUnit> unitsMap = null;
	
	/**
	 * Ordered list of measure units supported
	 */
	private static MeasureUnit[] units;
	
	/**
	 * The color used as background when the control is not in error status. 
	 * This is initialized when the control is created
	 */
	protected Color defaultBackgroundColor = null;

	/**
	 * String added to the autocomplete
	 */
	private static String[] autocompleteValues;
	
	/**
	 * The key used to store inside the widget the measure units popup menu
	 */
	private static final String POPUP_KEY = "measureUnitMenu";
	
	/**
	 * Name of the properties used to store in the model the last selected measure unit for this
	 * property
	 */
	private static final String CURRENT_MEASURE_KEY = ".measureUnit";
	
	/**
	 * The key used to store inside the widget the focus listener
	 */
	protected static final String FOCUS_KEY = "focusListener";
	
	protected Number min;
	
	protected Number max;

	public MeasureUnitPropertyDescription() {
	}
	
	public MeasureUnitPropertyDescription(String name, String label, String description, boolean mandatory, Long defaultValue, long min, long max) {
		super(name, label, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
	}

	public MeasureUnitPropertyDescription(String name, String label, String description, boolean mandatory, long min, long max) {
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

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);


		if (unitsMap == null) {
			CreateDefaultUnits();
		}
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
		
		defaultBackgroundColor = simpleControl.getBackground();
		cmp.getSecondContainer().setData(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
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
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			Number n = v != null ? Long.parseLong(v) : null;
			setDataNumber(n, simpleControl, wip);
			//String errorMessage = getErrorMessages();
			//setErrorStatus(errorMessage, simpleControl);
			
			changeFallbackForeground(isFallback, simpleControl);
			cmp.switchToSecondContainer();
		}
	}
	
	/**
	 * Receive a number and set it in the text widget
	 * 
	 * @param f the number
	 * @param insertField the text widget, must be not null
	 * @param wItemProp the {@link IWItemProperty} used to read the current measure unit from the model
	 */
	public void setDataNumber(Number f, Text insertField, IWItemProperty wItemProp) {
		if (f != null) {
			int oldpos = insertField.getCaretPosition();
			setPixels(f.toString(), insertField, wItemProp);
			if (insertField.getText().length() >= oldpos)
				insertField.setSelection(oldpos, oldpos);
		} else
			insertField.setText(""); //$NON-NLS-1$
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
	 * Add the default measure type to the map
	 */
	private void CreateDefaultUnits() {
		unitsMap = new HashMap<String, MeasureUnit>();
		units = new MeasureUnit[5];
		// Adding the measure unit for pixel
		units[0] = new MeasureUnit(Unit.PX, "px", 0); //$NON-NLS-1$
		unitsMap.put(Unit.PX, units[0]);
		// Adding the measure unit for inch
		units[1] = new MeasureUnit(Unit.INCH, "inch", 2); //$NON-NLS-1$
		unitsMap.put(Unit.INCH, units[1]);
		// Adding the meausre unit for centimeter
		units[2] = new MeasureUnit(Unit.CM, "cm", 2); //$NON-NLS-1$
		unitsMap.put(Unit.CM, units[2]);
		// Adding the measure unit for millimeters
		units[3] = new MeasureUnit(Unit.MM, "mm", 2); //$NON-NLS-1$
		unitsMap.put(Unit.MM, units[3]);
		// Adding the measure unit for meters
		units[4] = new MeasureUnit(Unit.METER, "m", 2); //$NON-NLS-1$
		unitsMap.put(Unit.METER, units[4]);

		autocompleteValues = new String[] { "centimeters", "millimeters", "inches", "meters", "pixels" };// Unit.getAliasList(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
	
	/**
	 * Return the measure unit typed in the textfield
	 * 
	 * @param value
	 *          content of the text field
	 * @return measure unit, it's the last alphabetical string in the textfield or null
	 * if there is no alphabetical value
	 */
	private String getMeasureUnit(String value) {
		String[] results = value.split("[^a-z]"); //$NON-NLS-1$
		// If the array is void then no measure unit are specified
		if (results.length == 0)
			return null;
		String measure = results[results.length - 1].trim();
		if (measure.isEmpty()){
			return null;
		}
		return measure;
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
		for (int i = 0; i < units.length; i++) {
			MeasureUnit key = units[i];
			MenuItem item = new MenuItem(popUpMenu, SWT.PUSH);
			item.setText(key.getUnitName());
			item.addSelectionListener(new MenuAction(key.getKeyName(), insertField, wItemProp));
		}
		return popUpMenu;
	}

	/**
	 * Return the value in the text widget, it's returned as pixel
	 * 
	 * @param insertField the text widget, must be not null
	 * @return the value in the textfield as pixel
	 */
	protected String getPixels(Text insertField) {
		String text = insertField.getText().trim().toLowerCase();
		String key = getMeasureUnit(text);
		MeasureUnit unit = unitsMap.get(Unit.getKeyFromAlias(key));
		if (unit != null) {
			String value = text.substring(0, text.indexOf(key));
			if (value.trim().isEmpty()) return null; 
			Unit uunit = new Unit(Double.parseDouble(value), unit.getKeyName(), jConfig);
			Double dValue = uunit.getValue(Unit.PX);
			return String.valueOf(dValue.longValue());
		}
		return null;
	}
	
	/**
	 * Set the value into the text widget, it's converted from pixel to the default measure unit
	 * 
	 * @param value the value to set, must be in pixel
	 * @param insertField the text widget, must be not null
	 * @param wItemProp the {@link IWItemProperty} used to read the current preferred measure unit from the widget
	 */
	protected void setPixels(String value, Text insertField, IWItemProperty wItemProp) {
		MeasureUnit defaultMeasure = getDefaultMeasure(insertField, wItemProp);
		Unit uunit = new Unit(Double.parseDouble(value), Unit.PX, jConfig);
		Double dValue = uunit.getValue(defaultMeasure.getKeyName());
		
		insertField.setBackground(null);
		if (Unit.PX.equals(defaultMeasure.getKeyName())){
			insertField.setText(String.valueOf(dValue.intValue()).concat(" ".concat(defaultMeasure.getUnitName()))); //$NON-NLS-1$
		} else {
			insertField.setText(String.valueOf(dValue).concat(" ".concat(defaultMeasure.getUnitName()))); //$NON-NLS-1$
		}
	}
	
	/**
	 * Return the default measure unit, that can be a local value if it's present or the global default value
	 * 
	 * @return
	 */
	protected MeasureUnit getDefaultMeasure(Text insertField, IWItemProperty wItemProp) {
		MeasureUnit mu = null;
		String localValue = getMeasureUnit(wItemProp);
		if (localValue != null && unitsMap.containsKey(localValue)) {
			mu = unitsMap.get(localValue);
		} else
			mu = unitsMap.get(Unit.PX);
		if (mu == null)
			mu = units[0];
		return mu;
	}

	@Override
	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (!wiProp.isExpressionMode() && txt instanceof Text){
			Text insertField = (Text)txt;
			String text = insertField.getText().trim().toLowerCase();
			if (!text.isEmpty()){
				String key = getMeasureUnit(text);
				String value;
				MeasureUnit unit;
				if (key == null) {
					//A unit is not specified, so use the element or default one
					unit = getDefaultMeasure(insertField, wiProp); 
					value = text;
				} else {
					unit = unitsMap.get(Unit.getKeyFromAlias(key));
					value = text.substring(0, text.indexOf(key));
				}
				if (unit != null) {
					try{ 
						setMeasureUnit(unit.getKeyName(), wiProp);
						//Convert the value into pixel, internally JR work always with pixels
						String convertedValue = unit.doConversionFromThis(unitsMap.get(Unit.PX), value);
						if (convertedValue != null){
							long pixelCount = Double.valueOf(convertedValue).longValue();
							wiProp.setValue(String.valueOf(pixelCount), null);
						} else {
							wiProp.setValue(null, null);
						}
						setErrorStatus(null, insertField);
					} catch (NumberFormatException ex) {
						//The value can not be converted into a number
						setErrorStatus(Messages.common_this_is_not_an_integer_number, insertField);
					} catch (PixelConversionException ex){
						//The value can be converted into a number but not into an integer
						setErrorStatus(ex.getMessage(), insertField);
					}
				} else {
					//Measure unit not found
					setErrorStatus(Messages.SPPixel_errorMeasureUnit, insertField);
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
	 * Update the current local measure unit for the element
	 * Set the measure value into the properties of the model
	 * 
	 * @param measureUnitKey the key of the measure unit to store
	 * @param wItemProperty the WItemProperty to write the property on the element
	 */
	private void setMeasureUnit(String measureUnitKey, IWItemProperty wItemProperty) {
		String propertyName = wItemProperty.getPropertyName();
		String measureUnitProperty = propertyName + CURRENT_MEASURE_KEY;
		wItemProperty.getPropertyEditor().createUpdateProperty(measureUnitProperty, measureUnitKey, null);
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
		return wItemProperty.getPropertyEditor().getPropertyValue(measureUnitProperty);
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

	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		Long min = null;
		Long max = null;
		Long def = null;
		Long fallBack = null;
		
		//Setup the minimum
		if (cpd.getMin() != null){
			min = new Long(cpd.getMin());
		} else {
			min = Long.MIN_VALUE;
		}
	 	
		//Setup the maximum
		if (cpd.getMax() != null){
			max = new Long(cpd.getMax());
		} else {
			max = Long.MAX_VALUE;
		}
		
		//Setup the default value
		if (cpd.getDefaultValue() != null && !cpd.getDefaultValue().isEmpty()){
			def = new Long(cpd.getDefaultValue());
		}
		
		//Setup the fallback value
		if (cpd.getFallbackValue() != null && !cpd.getFallbackValue().isEmpty()){
			fallBack = new Long(cpd.getFallbackValue());
		}
		MeasureUnitPropertyDescription intDesc = new MeasureUnitPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max);
		intDesc.setReadOnly(cpd.isReadOnly());
		intDesc.setFallbackValue(fallBack);
		intDesc.setjConfig(jConfig);
		return intDesc;
	}

	@Override
	public ItemPropertyDescription<Long> clone() {
		MeasureUnitPropertyDescription result = new MeasureUnitPropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.min = min;
		result.max = max;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	//ADDITIONAL CLASSES
	
	/**
	 * Class that offer the method to convert and handle a measure unit
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	public class MeasureUnit {
		/**
		 * Holds value of property unitName.
		 */
		private String unitName;

		/**
		 * The unity key
		 */
		private String keyName;

		/**
		 * Holds the suggested precision when this measure is displayed. It's not the real precision, but it's intended to
		 * be used when the measure is displayed
		 */
		private int precision;

		/** Creates a new instance of Unit */
		public MeasureUnit(String unitName) {
			this(unitName, unitName, 2);
		}

		public MeasureUnit(String keyName, String unitName, int precision) {
			this.unitName = unitName;
			this.keyName = keyName;
			this.precision = precision;
		}

		/**
		 * Number of decimal digits to show when this measure is displayed
		 * 
		 * @return
		 */
		public int getPrecision() {
			return precision;
		}

		/**
		 * Getter for property unitName.
		 * 
		 * @return Value of property unitName.
		 * 
		 */
		public String getUnitName() {
			return this.unitName;
		}

		/**
		 * Return the key of the stored type
		 * 
		 * @return key represented as string
		 */
		public String getKeyName() {
			return this.keyName;
		}

		/**
		 * Setter for property unitName.
		 * 
		 * @param unitName
		 *          New value of property unitName.
		 * 
		 */
		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

		/**
		 * Convert a value from this type to another type
		 * 
		 * @param targetUnit
		 *          The MeasureUnit of the target type
		 * @param value
		 *          the value to convert
		 * @return the converted value as a string, that is a textual representation
		 * of a double
		 */
		public String doConversionFromThis(MeasureUnit targetUnit, String value) throws PixelConversionException{
			if (value == null || value.isEmpty()) return null;
			/* Even if from a logical point of view is the target measure unit the same of the source we can simply return
			 the input technically it is better to do the conversion since the passed value as string can be too big to fit
			 an int once it is parsed. Instead the conversion check if the value fit an int and eventually throw an exception
			if (this.getKeyName().equals(targetUnit.getKeyName())){
				//no conversion requested
				return value;
			}*/
			return String.valueOf((new Unit(Double.parseDouble(value), keyName, jConfig)).getValue(targetUnit.getKeyName()));
		}
	}

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
			String measureUnit = getMeasureUnit(text);
			if (insertField.getCaretPosition() == text.length() && measureUnit != null)
				return measureUnit;
			else
				return ""; //$NON-NLS-1$
		}

		public void setControlContents(Control control, String text, int cursorPosition) {
			String textField = insertField.getText().trim().toLowerCase();
			String key = getMeasureUnit(textField);
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
			String measureUnitAlias = insertField.getSelectionText().trim().toLowerCase();
			String measureUnitName = Unit.getKeyFromAlias(measureUnitAlias);
			if (measureUnitName != null) {
				openPopupMenu(insertField, wItemProp);
			}
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
			String pixelValue = getPixels(insertField);
			setMeasureUnit(value, wItemProperty);
			setPixels(pixelValue, insertField, wItemProperty);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
}

