/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.section.report.util.Unit.PixelConversionException;
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
public class FixedMeasurePropertyDescription extends AbstractMeasurePropertyDescription<String> {
	
	/**
	 * Hash map the bind a measure unit, the key is a name of the measure unit, the value is the measure unit 
	 * Representation. An example could be a measure with key pixels and value px
	 */
	protected Map<String, String> nameKeyUnitsMap = null;
	
	/**
	 * Used for performance improvements, it the opposite map of nameKeyUnitsMap and store the units by their
	 * key insited of their names
	 */
	protected Map<String, List<String>> keyNameUnitsMap = null;
	
	/**
	 * String added to the autocomplete
	 */
	protected String[] autocompleteValues;
	
	/**
	 * Formatter used to format the number 
	 */
	protected static final DecimalFormat format = new DecimalFormat("0.################");
	
	/**
	 * Flag used to avoid to commit a value on the focus lost when the currently shown value is a fallback
	 */
	private boolean fallbackModified = true;

	public FixedMeasurePropertyDescription() {
	}
	
	public FixedMeasurePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue, long min, long max, Map<String, String> unitsMap) {
		super(name, label, description, mandatory, defaultValue, min, max);
		this.nameKeyUnitsMap = unitsMap;
		keyNameUnitsMap = getInversedUnitsMap();
		autocompleteValues = buildAutoocompleteValues();
	}

	public FixedMeasurePropertyDescription(String name, String label, String description, boolean mandatory, long min, long max, Map<String, String> unitsMap) {
		super(name, label, description, mandatory, min, max);
		this.nameKeyUnitsMap = unitsMap;
		keyNameUnitsMap = getInversedUnitsMap();
		autocompleteValues = buildAutoocompleteValues();
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//create the expression  control
		lazyCreateExpressionControl(wiProp, cmp);
		
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
		
		simpleControl.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				//regardless of there is or not a fallback the edit of the text
				//open the guard 
				fallbackModified = true;
			}
		});
		
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
		cmp.setSimpleControlToHighlight(simpleControl);
		
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		String tt = getToolTip();
		simpleControl.setToolTipText(tt);
		
		
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
			lazyCreateExpressionControl(wip, cmp);
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
				//close the guard of the fallback if we have set a fallback value, this
				//will avoid to not commit the value actually in the simple control until it will
				//be modified, because the currently displayed value is a fallback and should not
				//be committed for a focus lost. Also this flag must be closed after the setDataNumber call
				//otherwise the modifyLister is triggered and the flag reopened
				fallbackModified = !isFallback;
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
	private String[] buildAutoocompleteValues(){
		String[] result = new String[nameKeyUnitsMap.keySet().size()];
		int index = 0;
		for(String measure : nameKeyUnitsMap.keySet()){
			result[index] = measure;
			index++;
		}
		return result;
	}
	
	/**
	 * Search a measure name in the units map by its key (since 
	 * in the map the name is used as key this is like searching in 
	 * the map by value)
	 * 
	 * @param key the key of a measure
	 * @return a measure name whose key is the passed parameter, or null if it can't be found
	 */
	protected String searchMeasureNameByKey(String key){
		List<String> namesForKey = keyNameUnitsMap.get(key);
		if (namesForKey != null && !namesForKey.isEmpty()){
			return namesForKey.get(0);
		}
		return null;
	}
	
	/**
	 * Resolve the measure unit from the input text, if it can be resolved from the text
	 * use the last measure in the model
	 * 
	 * @param text the text in the combo 
	 * @param wiProp the {@link IWItemProperty} to access to the model
	 * @return a valid measure name for the measure, or null if it can't be resolved
	 */
	protected String resolveMeasureUnitText(String text, IWItemProperty wiProp){
		String measureUnitName = getMeasureUnitFromText(text);
		if (measureUnitName != null && nameKeyUnitsMap.containsKey(measureUnitName)){
			return measureUnitName;
		} else {
			MeasureDefinition lastModelMeasure = getMeasureUnit(wiProp);
			if (lastModelMeasure != null && lastModelMeasure.getName() != null){
				String measureName = lastModelMeasure.getName();
				if (nameKeyUnitsMap.containsKey(measureName)){
					//found a valid measure name inside the element
					return measureName;
				}
			}
			if (lastModelMeasure != null &&lastModelMeasure.getKey() != null) {
				//the measure name can't be resolved, try to resolve the measure key
				if (nameKeyUnitsMap.containsKey(lastModelMeasure.getKey())){
					//if the key is also a valid name use that as value
					return lastModelMeasure.getKey();
				} else {
					//fallback, search a valid name from the key
					return searchMeasureNameByKey(lastModelMeasure.getKey());
				}
			}
		}
		//At this point if it was not possible to get a measure unit check if there is only 
		//one defined and in this case use that, otherwise return null
		if (keyNameUnitsMap.size() == 1){
			Entry<String, List<String>> entry = keyNameUnitsMap.entrySet().iterator().next();
			return entry.getValue().get(0);
		} else {
		//Unable to find a measure on the textual value, try to find it on the fallback value
			if(wiProp.getFallbackValue() != null){
				measureUnitName = getMeasureUnitFromText(wiProp.getFallbackValue().toString());
				if (measureUnitName != null && nameKeyUnitsMap.containsKey(measureUnitName)){
					return measureUnitName;
				} 
			}
			return null;
		}
	}
	
	
	/**
	 * Resolve a measure definition comparing it with the current measure map, this is
	 * used to check that a loaded definition is correct with the current available values.
	 * First this check if the value is available on the model, if it is not is try
	 * to parse the text and find a measure from it. In the end if the measure can be determinate
	 * in an implicit way then it uses that as unit.
	 * 
	 * @param text the text containing both the number and the measure unit
	 * @param wItemProp the {@link IWItemProperty} of the current property, to read the measure unit from the model
	 * @return a definition that can match the current allowed value or null if the passed definition
	 * is not valid
	 */
	protected MeasureDefinition resolveMeasureUnit(String text, IWItemProperty wItemProp){
		MeasureDefinition loadedDefinition = getMeasureUnit(wItemProp);
		if (loadedDefinition != null && !nameKeyUnitsMap.containsKey(loadedDefinition.getName())){
			//try to resolve the measure by its key
			if (nameKeyUnitsMap.containsKey(loadedDefinition.getKey())){
				//the key is present but not the name (maybe because of localization), use the key as name
				loadedDefinition.setName(loadedDefinition.getKey());
			} else {
				//the passed definition doesn't match anything in the map, try to resolve it by name
				String resolvedByKey = searchMeasureNameByKey(loadedDefinition.getKey());
				if (resolvedByKey != null){
					loadedDefinition.setName(resolvedByKey);
				} else {
					//unable to resolve the measure
					return null;
				}
			}
		} else {
			//At this point if it was not possible to get a measure unit check if there is only 
			//one defined and in this case use that, otherwise return null
			if (keyNameUnitsMap.size() == 1){
				Entry<String, List<String>> entry = keyNameUnitsMap.entrySet().iterator().next();
				MeasureDefinition newDefinition = new MeasureDefinition(entry.getKey(), entry.getValue().get(0));
				return newDefinition;
			}
			//Unable to find a measure on the model, search it on the text of the property
			String measureUnitName = getMeasureUnitFromText(text);
			if (measureUnitName != null && nameKeyUnitsMap.containsKey(measureUnitName)){
				MeasureDefinition newDefinition = new MeasureDefinition(nameKeyUnitsMap.get(measureUnitName), measureUnitName);
				return newDefinition;
			} 
			//Unable to find a measure on the textual value, try to find it on the fallback value
			if(wItemProp.getFallbackValue() != null){
				measureUnitName = getMeasureUnitFromText(wItemProp.getFallbackValue().toString());
				if (measureUnitName != null && nameKeyUnitsMap.containsKey(measureUnitName)){
					MeasureDefinition newDefinition = new MeasureDefinition(nameKeyUnitsMap.get(measureUnitName), measureUnitName);
					return newDefinition;
				} 	
			}
		}
		return loadedDefinition;
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
			MeasureDefinition measureUnit = resolveMeasureUnit(value, wiProp); 
			Double number = null;
			String roundedNumber = null;
			boolean error = false;
			if (measureUnit == null){
				setErrorStatus("Measure Unit not recognized", insertField);
				error = true;
			} else {
				String numberText = getNumericValue(value);
				try{
					 number = Double.valueOf(numberText);
					 roundedNumber = getRoundedValue(measureUnit.getKey(), number);
				} catch (Exception ex){
					setErrorStatus("The number is not valid", insertField);
					error = true;
				}
			}
			if (error){
				insertField.setText(value);
			} else {
				setErrorStatus(null, insertField);
				insertField.setText(roundedNumber + " " + measureUnit.getName());
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
	 * value removing unnecessary zeroes or decimal digit when using an integer unit (px). then at the
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
	 * Create the popup menu
	 * 
	 * @param insertField the Text widget where the menu is set, must be not null
	 * @param wItemProp the {@link IWItemProperty} used to apply the action when an entry of the menu is selected, must be not null
	 */
	@Override
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
			if (!text.isEmpty() && fallbackModified){
				//the text is not empty and it is different from the fallback
				String measureUnitName = resolveMeasureUnitText(text, wiProp);
				String measureUnitKey = nameKeyUnitsMap.get(measureUnitName);
				if (measureUnitKey != null) {
					try{ 
						setMeasureUnit(measureUnitKey, measureUnitName, wiProp);
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
				//remove the measure unit property
				removeMeasureUnit(wiProp);
			}
		} else super.handleEdit(txt, wiProp);
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
						MeasureDefinition currentDef = decode(measureUnit);
						setMeasureUnit(currentDef.getKey(), currentDef.getName(), wItemProp);
					}
				}
				return super.close();
			}
			
			@Override
			protected Control createDialogArea(Composite parent) {
				//On create write the measure unit property in the additional properties map
				MeasureDefinition currentMeasureDef = getMeasureUnit(wItemProp);
				String propertyName = wItemProp.getPropertyName();
				customPropertiesMap.put(propertyName + CURRENT_MEASURE_KEY, encode(currentMeasureDef));
				return super.createDialogArea(parent);
			}
		};
		return result;
	}
	
	/**
	 * Starting from the units map it create the reversed map where the key is the unit value and
	 * the value is a list of all the available names for that unit
	 * 
	 * @return a not null {@link Map}
	 */
	protected Map<String, List<String>> getInversedUnitsMap(){
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		for(Entry<String, String> unit : nameKeyUnitsMap.entrySet()){
			String key = unit.getValue().toLowerCase();
			List<String> list = result.get(key);
			if (list == null){
				list = new ArrayList<String>();
				list.add(unit.getKey());
				result.put(key, list);
			} else {
				list.add(unit.getKey());
			}
		}
		return result;
	}

	protected Map<String, String> createMesureUnitsMap(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd){
		//setup the measures
		HashMap<String, String> i18nOpts = new HashMap<String, String>();
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			for (int i = 0; i < opts.length; i++) {
				i18nOpts.put(cd.getLocalizedString(opts[i][1]).toLowerCase().trim(), opts[i][0].trim());
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
	
	/**
	 * Add to the entry of the contexutal menu a listener to remove the error status, this 
	 * will force the widget to be updated when calling the updateWidget
	 */
	@Override
	protected void setupContextMenu(final Control c, final IWItemProperty wiProp) {
		super.setupContextMenu(c, wiProp);
		Menu controlMenu = c.getMenu();
		if (controlMenu != null && !controlMenu.isDisposed()){
			for (MenuItem mi : controlMenu.getItems()) {
				mi.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						setErrorStatus(null, (Text)c);
						wiProp.updateWidget();
					}
				});
			}		
		}
	}
	
	/**
	 * Set the insert field into an error status, so with a red background
	 * and a tooltip that describe the error. The differences between this and
	 * the superclass one is that this append an informative message to the tooltip
	 * when there aren't errors.
	 * 
	 * @param message the error message, it will be used as tooltip, can be null
	 * for no error message (with null erase the old errors from the tooltip and 
	 * restore the default ones)
	 */
	protected void setErrorStatus(String message, Text insertField){
		if (message != null){
			super.setErrorStatus(message, insertField);
		} else {
			updateBackground(defaultBackgroundColor, insertField);
			String tt = getToolTip();
			if (autocompleteValues.length > 1){
				tt += "\nDouble click to switch between different dimensions";
			}
			insertField.setToolTipText(tt);			
		}
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
		result.nameKeyUnitsMap = nameKeyUnitsMap;
		result.autocompleteValues = autocompleteValues;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	//ADDITIONAL CLASSES
	
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

