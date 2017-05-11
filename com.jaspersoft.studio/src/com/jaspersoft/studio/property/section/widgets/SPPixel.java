/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.HashMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptors.AbstractJSSCellEditorValidator;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.property.section.report.util.Unit;
import com.jaspersoft.studio.property.section.report.util.Unit.PixelConversionException;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * This class implement a Textfield where display a number with a measure unit. The number and the measure unit can be
 * changed and the conversion are done automatically
 * 
 * @author Orlandin Marco
 * 
 */
public class SPPixel extends ASPropertyWidget<PixelPropertyDescriptor> {
	
	/**
	 * Style bit to have the measure unit not persistent and doesn't allow null
	 */
	public static final int NONE = 0;
	
	/**
	 * Style bit to have the measure unit persistent. if the the local measure unit will 
	 * be store into the jrxml and saved, otherwise the visualization will be
	 * reseted to the default measure unit everytime the element properties are shown.
	 */
	public static final int PERSISTENT = 2;
	
	/**
	 * Hash map the bind a measure unit, by its key, to a series of method to convert and handle that measure
	 */
	private static HashMap<String, MeasureUnit> unitsMap = null;

	/**
	 * Ordered list of measure units supported
	 */
	private static MeasureUnit[] units;

	/**
	 * String added to the autocomplete
	 */
	private static String[] autocompleteValues;

	/**
	 * The text field
	 */
	private Text insertField;

	/**
	 * The key of the default measure unit
	 */
	private String defaultValue;

	/**
	 * Popup menu shown to change the measure unit
	 */
	private Menu popUpMenu;

	/**
	 * Key of the local measure unit
	 */
	private String localValue;

	/**
	 * The style of the control
	 */
	private int style;
	
	/**
	 * The current JasperReportsConfiguration
	 */
	private JasperReportsConfiguration jConfig;
	

	/**
	 * Used to store the last text set into the Textfield, needed to prevent that the lost focus event do multiple update
	 */
	private String lastSetValue;
	
	// Flag used to overcome the problem of focus events in Mac OS X
	// 	- JSS Bugzilla 42999
	// 	- Eclipse Bug 383750
	// It makes sense only on E4 platform and Mac OS X operating systems.
	// DO NOT USE THIS FLAG FOR OTHER PURPOSES.
	private boolean editHappened = false;

	@Override
	protected void handleFocusLost() {
		super.handleFocusLost();
		if(UIUtil.isMacAndEclipse4() && !editHappened){
			insertField.setText(Misc.nvl(lastSetValue));
		}
		// Focus lost, do the change only if the text is changed
		if (lastSetValue == null || !lastSetValue.equals(insertField.getText()))
			updateValue();
		if(UIUtil.isMacAndEclipse4()) {
			editHappened=false;
		}
	}

	/**
	 * Listener that handle the double click on the Text, made the contextual menu appears
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class MouseClickListener implements MouseListener {

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			String measureUnitAlias = insertField.getSelectionText().trim().toLowerCase();
			String measureUnitName = Unit.getKeyFromAlias(measureUnitAlias);
			if (measureUnitName != null) {
				openPopupMenu();
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

		public MenuAction(String value) {
			this.value = value;
		}

		/**
		 * When a new measure unit is selected a new local is set and the conversion is done
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			String pixelValue = getText();
			setLocalValue(value);
			setText(pixelValue);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}

	}

	/**
	 * Read the measure unit and hel to autocomplete
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class AutoCompleteMeasure extends TextContentAdapter {
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
	 * Create the widget with the persistent flag enabled and that allow null values
	 */
	public SPPixel(Composite parent, AbstractSection section, PixelPropertyDescriptor pDescriptor) {
		this(parent, section, pDescriptor, PERSISTENT);
	}

	/**
	 * Create the widget with the specified style
	 *  
	 * @param style must be one between SPPixel.NONE or SPPixel.PERSISTENT
	 *          
	 */
	public SPPixel(Composite parent, AbstractSection section, PixelPropertyDescriptor pDescriptor, int style) {
		super(parent, section, pDescriptor);
		this.style = style;
		localValue = getLocalValue();
		jConfig = section.getElement().getJasperConfiguration();
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
	 * Cut the decimal of a double a precise number of digits
	 * 
	 * @param number
	 *          number to cut
	 * @param numDigits
	 *          number of decimal digits
	 * @return cut double, represented as string
	 */
	private static String truncateDouble(double number, int numDigits) {
		String arg = Double.toString(number);
		int idx = arg.indexOf('.');
		int offset = numDigits > 0 ? 1 : 0;
		if (idx != -1) {
			if (arg.length() > idx + numDigits) {
				arg = arg.substring(0, idx + numDigits + offset);
			}
		}
		return arg;
	}

	protected Command getChangePropertyCommand(Object property, Object newValue, APropertyNode n) {
		Object oldValue = n.getPropertyValue(property);
		if (((oldValue == null && newValue != null) || (oldValue != null && newValue == null) || (newValue != null && !newValue
				.equals(oldValue)))) {
			SetValueCommand setCommand = new SetValueCommand(n.getDisplayText());
			setCommand.setTarget(n);
			setCommand.setPropertyId(property);
			setCommand.setPropertyValue(newValue);
			return setCommand;
		}
		return null;
	}

	/**
	 * Calculate the percentage of a value
	 */
	private Long getNewValue(Double percentage, APropertyNode pnode, String property) {
		String oldValue = pnode.getPropertyActualValue(property).toString();
		Integer oldNumericValue = Integer.parseInt(oldValue);
		Double newValueLong = (oldNumericValue.doubleValue() * percentage) / 100d;
		Long newValue = Math.round(newValueLong);
		return newValue;
	}

	/**
	 * This method do a percentage resize of one or more elements. If the resize is done on the height or width, and are
	 * selected more than one element, then the coordinate X and Y of the element are translated to try to keep the same
	 * aspect ratio between them
	 */
	private void percentageResize() {
		String text = insertField.getText().trim().toLowerCase();
		int percPosition = text.indexOf("%"); //$NON-NLS-1$
		if (percPosition > 0) {
			try {
				Double value = Double.parseDouble(text.substring(0, percPosition));
				CommandStack cs = section.getEditDomain().getCommandStack();
				JSSCompoundCommand cc = new JSSCompoundCommand("Set " + pDescriptor.getId(), null); //$NON-NLS-1$
				for (APropertyNode pnode : section.getElements()) {
					try {
						cc.setReferenceNodeIfNull(pnode);
						Long newValue = getNewValue(value, pnode, pDescriptor.getId().toString());
						Command c = getChangePropertyCommand(pDescriptor.getId(), newValue.intValue(), pnode);
						if (c != null)
							cc.add(c);
						if (pDescriptor.getId().equals(JRDesignElement.PROPERTY_HEIGHT) && section.getElements().size() > 1) {
							newValue = getNewValue(value, pnode, JRDesignElement.PROPERTY_Y);
							c = getChangePropertyCommand(JRDesignElement.PROPERTY_Y, newValue.intValue(), pnode);
							if (c != null)
								cc.add(c);
						}
						if (pDescriptor.getId().equals(JRDesignElement.PROPERTY_WIDTH) && section.getElements().size() > 1) {
							newValue = getNewValue(value, pnode, JRDesignElement.PROPERTY_X);
							c = getChangePropertyCommand(JRDesignElement.PROPERTY_X, newValue.intValue(), pnode);
							if (c != null)
								cc.add(c);
						}
					} catch (NumberFormatException ex) {
					}
				}
				cs.execute(cc);
				APropertyNode firstNode = section.getElements().get(0);
				setData(firstNode, firstNode.getPropertyActualValue(pDescriptor.getId()));
			} catch (NumberFormatException ex) {
			}
		}
	}

	/**
	 * Change the property by setting null as value
	 */
	protected void setNullValue(){
		section.changeProperty(pDescriptor.getId(), null);
		insertField.setBackground(null);
		insertField.setToolTipText(pDescriptor.getDescription());
	}
	
	/**
	 * Validate the content of the text area and return the first error message
	 * 
	 * @return the error message or null if there aren't errors
	 */
	private String getErrorMessages(){
		String text = insertField.getText().trim().toLowerCase();
		String key = getMeasureUnit(text);
		String value;
		MeasureUnit unit;
		if (key == null) {
			//A unit is not specified, so use the element or default one
			unit = getDefaultMeasure(); 
			value = text;
		} else {
			unit = unitsMap.get(Unit.getKeyFromAlias(key));
			value = text.substring(0, text.indexOf(key));
		}
		if (unit != null) {
			try {
				//Convert the value into pixel, internally JR work always with pixels
				String convertedValue = unit.doConversionFromThis(unitsMap.get(Unit.PX), value);

				//check if the resize must be done
				AbstractJSSCellEditorValidator validator = pDescriptor.getValidator();
				if (validator != null){
					//A validator is provided, so validate the property
					Integer newValue = getIntegerValue(convertedValue);
					String errorMessage = null;
					for (APropertyNode pnode : section.getElements()) {
						validator.setTargetNode(pnode);
						errorMessage = validator.isValid(newValue);
						if (errorMessage != null){
							//there is an error message , break the cycle
							return errorMessage;
						}
					}
				} 
			} catch (NumberFormatException ex) {
				//The value can not be converted into a number
				return Messages.common_this_is_not_an_integer_number;
			} catch (PixelConversionException ex){
				//The value can be converted into a number but not into an integer
				return ex.getMessage();
			}
		} else {
			//Measure unit not found
			return Messages.SPPixel_errorMeasureUnit;
		}
		return null;
	}
	
	/**
	 * Read the value in the textfield and update it in the model, but before the value is converted to pixel, and in the
	 * textbox is displayed as default type. If a validator is provided then the value is first validated
	 */
	private void absoluteResize() {
		String text = insertField.getText().trim().toLowerCase();
		String key = getMeasureUnit(text);
		String value;
		MeasureUnit unit;
		if (key == null) {
			//A unit is not specified, so use the element or default one
			unit = getDefaultMeasure(); 
			value = text;
		} else {
			unit = unitsMap.get(Unit.getKeyFromAlias(key));
			value = text.substring(0, text.indexOf(key));
		}
		if (unit != null) {
			setLocalValue(unit.getKeyName());
			try {
				JSSCompoundCommand commands = new JSSCompoundCommand(section.getElement());
				//Convert the value into pixel, internally JR work always with pixels
				String convertedValue = unit.doConversionFromThis(unitsMap.get(Unit.PX), value);
				//Generate the command to update the measure unit in the preferences
				for (APropertyNode pnode : section.getElements()) {
					if (pnode.getValue() != null && pnode.getValue() instanceof JRPropertiesHolder) {
						JRPropertiesMap pmap = (JRPropertiesMap) pnode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
						if (pmap != null && setLocalValue(pmap, unit.getUnitName())) {
							SetValueCommand cmd = new SetValueCommand();
							cmd.setTarget(pnode);
							cmd.setPropertyId(MGraphicElement.PROPERTY_MAP);
							cmd.setPropertyValue(pmap);
							commands.add(cmd);
						}
					}
				}
				
				//check if the resize must be done
				AbstractJSSCellEditorValidator validator = pDescriptor.getValidator();
				if (validator != null){
					//A validator is provided, so validate the property
					Integer newValue = getIntegerValue(convertedValue);
					String errorMessage = null;
					for (APropertyNode pnode : section.getElements()) {
						validator.setTargetNode(pnode);
						errorMessage = validator.isValid(newValue);
						if (errorMessage != null){
							//there is an error message , break the cycle
							break;
						}
						Command changeCommand = section.getChangePropertyCommand(pDescriptor.getId(), newValue, pnode);
						if (changeCommand != null) {
							commands.add(changeCommand);
						}
					}
					//Restore the main element inside the validator
					validator.setTargetNode(section.getElement());
					if (errorMessage != null){
						//The values are not valid, show the error message
						setErrorStatus(errorMessage);
					} else {
						//The values are valid, apply them
						section.runCommand(commands);
						//Operation completed, clear the error status and update the text area
						clearErrorStatus();
					}
				} else {
					//No validator provided
					Integer newValue = getIntegerValue(convertedValue);
					//Execute the command
					section.changeProperty(pDescriptor.getId(), newValue, commands.getCommands());
					//Operation completed, clear the error status and update the text area
					clearErrorStatus();
				}
			} catch (NumberFormatException ex) {
				//The value can not be converted into a number
				setErrorStatus(Messages.common_this_is_not_an_integer_number);
			} catch (PixelConversionException ex){
				//The value can be converted into a number but not into an integer
				setErrorStatus(ex.getMessage());
			}
		} else {
			//Measure unit not found
			setErrorStatus(Messages.SPPixel_errorMeasureUnit);
		}
	}
	
	/**
	 * Update the text area content with the current value from the element, this is done
	 * since the set of a value with a measure different from pixels can bring some rounding
	 * on the final value, so the real value is back converted to the desired measure unit and
	 * show in the text area.
	 * It also clear the error status by removing the red background and restoring the tooltip
	 */
	protected void clearErrorStatus(){
		Object currentValue = section.getElement().getPropertyActualValue(pDescriptor.getId());
		setData(section.getElement(), currentValue);
		insertField.setBackground(null);
		insertField.setToolTipText(pDescriptor.getDescription());
	}
	
	/**
	 * Set the insert field into an error status, so with a red background
	 * and a tooltip that describe the error
	 * 
	 * @param message the error message, it will be used as tooltip, can be null
	 * for no error message (with null erase the old errors from the tooltip and 
	 * restore the default ones)
	 */
	protected void setErrorStatus(String message){
		if (message != null){
			insertField.setBackground(ColorConstants.red);
			insertField.setToolTipText(message);
		} else {
			insertField.setBackground(null);
			insertField.setToolTipText(pDescriptor.getDescription());
		}
	}
	
	/**
	 * Return the textual double value as an integer. If the text area
	 * is empty return null, if the value is not a valid integer it raise a
	 * NumberFormatException. If the string can be convertend into a double then
	 * it is rounded to get an integer
	 * 
	 * @param valueText the value as a string, must be the representation of a double
	 * @return the value as integer or null
	 */
	private Integer getIntegerValue(String valueText){
		if (valueText != null && !valueText.trim().isEmpty()) {
			return (int)Math.round(Double.parseDouble(valueText));
		} 
		return null;
	}
	
	/**
	 * Update the value on the elements with the inserted one
	 */
	private void updateValue() {
		if (insertField.getText().contains("%"))
			percentageResize();
		else {
			absoluteResize();
		}
	}

	/**
	 * Return the default measure unit, that can be a local value if it's present or the global default value
	 * 
	 * @return
	 */
	protected MeasureUnit getDefaultMeasure() {
		MeasureUnit mu = null;
		if (localValue != null && unitsMap.containsKey(localValue)) {
			mu = unitsMap.get(localValue);
		} else
			mu = unitsMap.get(Unit.getKeyFromAlias(defaultValue));
		if (mu == null)
			mu = units[0];
		return mu;
	}

	/**
	 * Set the value into the textfield, it's converted from pixel to the default measure unit
	 * 
	 * @param value
	 *          the value to set, must be in pixel
	 */
	public void setText(String value) {
		MeasureUnit defaultMeasure = getDefaultMeasure();
		setUUnit(value, Unit.PX);
		double dValue = uunit.getValue(defaultMeasure.getKeyName());
		insertField.setBackground(null);
		insertField.setText(truncateDouble(dValue, defaultMeasure.getPrecision()).concat(
				" ".concat(defaultMeasure.getUnitName()))); //$NON-NLS-1$
		lastSetValue = insertField.getText();
	}

	private Unit uunit;

	/**
	 * Return the value in the textfield, it's returned as pixel
	 * 
	 * @return the value in the textfield as pixel
	 */
	public String getText() {
		String text = insertField.getText().trim().toLowerCase();
		String key = getMeasureUnit(text);
		MeasureUnit unit = unitsMap.get(Unit.getKeyFromAlias(key));
		if (unit != null) {
			String value = text.substring(0, text.indexOf(key));
			if (value.trim().isEmpty()) return null; 
			setUUnit(value, unit.getKeyName());
			Double dValue = uunit.getValue(Unit.PX);
			return String.valueOf(dValue.longValue());
		}
		return null;
	}

	private void setUUnit(String value, String u) {
		if (uunit == null)
			uunit = new Unit(Double.parseDouble(value), u, jConfig);
		else
			uunit.setValue(Double.parseDouble(value), u);
	}

	/**
	 * Set the size of the textfield
	 * 
	 * @param parent
	 *          parent of the textfield
	 * @param chars
	 *          number of chars to store (used to choose the size)
	 */
	protected void setWidth(Composite parent, int chars) {
		int w = getCharWidth(insertField) * chars;
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = w;
			insertField.setLayoutData(rd);

		} else if (parent.getLayout() instanceof GridLayout) {
			GridData rd = new GridData();
			rd.widthHint = w;
			insertField.setLayoutData(rd);
		}
	}

	/**
	 * Open the popoup menu inside the menumanger and place it under the combobox
	 * 
	 * @param menuManager
	 */
	protected void openPopupMenu() {
		if (popUpMenu == null)
			createPopupMenu();
		if (!popUpMenu.isDisposed()) {
			if (popUpMenu.isVisible()) {
				popUpMenu.setVisible(false);
			} else {
				locatePopupMenu();
				popUpMenu.setVisible(true);
			}
		}
	}

	/**
	 * Set the menu in the right location
	 * 
	 * @param menu
	 */
	protected void locatePopupMenu() {
		Rectangle r;
		if (getControl() instanceof Composite) {
			r = ((Composite) getControl()).getClientArea();
		} else {
			r = getControl().getBounds();
			r.x = r.y = 0;
		}
		Point loc = getControl().toDisplay(r.x, r.y);
		loc.y += r.height;
		popUpMenu.setLocation(loc);
	}

	/**
	 * Create the popup menu
	 */
	protected void createPopupMenu() {
		popUpMenu = new Menu(insertField);
		// Add the new elements
		for (int i = 0; i < units.length; i++) {
			MeasureUnit key = units[i];
			MenuItem item = new MenuItem(popUpMenu, SWT.PUSH);
			item.setText(key.getUnitName());
			item.addSelectionListener(new MenuAction(key.getKeyName()));
		}
	}

	
	/**
	 * Read from the jrelement the local value of the measure unit
	 * 
	 * @return the key of a local measure unit
	 */
	private String getLocalValue() {
		Object node = section.getElement().getValue();
		if ((node instanceof JRPropertiesHolder) && isLocalPersistent()) {
			return PHolderUtil.getUnit((JRPropertiesHolder) node, pDescriptor.getId().toString(), null);
		} else {
			return null;
		}
	}

	/**
	 * Update the current local measure unit for the element
	 * Set the measure value into the jr element if the node is available and
	 * if the style flag to make the measure persistent is enabled
	 * 
	 * @param newLocal the new measure unit
	 * @return true if the properties map of the element has been changed, flase otherwise
	 */
	private boolean setLocalValue(String newLocal) {
		Object node = section.getElement().getValue();
		if ((node instanceof JRPropertiesHolder)) {
			JRPropertiesMap nodeMap = ((JRPropertiesHolder) node).getPropertiesMap();
			return  setLocalValue(nodeMap, newLocal); 
		}
		return false;
	}
	
	/**
	 * Update the current local measure unit for the element
	 * Set the measure value passed map  if the style flag to make the measure persistent is enabled
	 * 
	 * @param newLocal the new measure unit
	 * @return true if the properties map of the element has been changed, false otherwise
	 */
	private boolean setLocalValue(JRPropertiesMap nodeMap, String newLocal) {
		localValue = newLocal;
		// let's look at our units
		if (isLocalPersistent()){
			String dunit = MReport.getMeasureUnit(jConfig, jConfig.getJasperDesign());
			PHolderUtil.setProperty(false, nodeMap, pDescriptor.getId().toString(), newLocal, dunit);
		}
		return false;
	}

	@Override
	protected void createComponent(Composite parent) {
		if (unitsMap == null) {
			CreateDefaultUnits();
		}
		int style = SWT.NONE;
		if (pDescriptor instanceof PixelPropertyDescriptor && ((PixelPropertyDescriptor) pDescriptor).isReadOnly())
			style = style | SWT.READ_ONLY;
		insertField = section.getWidgetFactory().createText(parent, "", style); //$NON-NLS-1$
		if(UIUtil.isMacAndEclipse4()) {
			insertField.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					editHappened=true;
				}
			});
		}
		insertField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR)
					updateValue();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		insertField.addMouseListener(new MouseClickListener());
		insertField.setToolTipText(pDescriptor.getDescription());
		new AutoCompleteField(insertField, new AutoCompleteMeasure(), autocompleteValues);
		setWidth(parent, 10);
	}

	/**
	 * Receive a number and set it in the textfield
	 * 
	 * @param f
	 *          the number
	 */
	public void setDataNumber(Number f) {
		if (f != null) {
			int oldpos = insertField.getCaretPosition();
			setText(f.toString());
			if (insertField.getText().length() >= oldpos)
				insertField.setSelection(oldpos, oldpos);
		} else
			insertField.setText(""); //$NON-NLS-1$
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		createContextualMenu(pnode);
		insertField.setEnabled(pnode.isEditable());
		defaultValue = MReport.getMeasureUnit(jConfig, jConfig.getJasperDesign());
		if (pnode.getValue() instanceof JRPropertiesHolder){
			localValue = PHolderUtil.getUnit((JRPropertiesHolder) pnode.getValue(), pDescriptor.getId().toString(),defaultValue);
		}
		Number n = (Number) value;
		setDataNumber(n);
		String errorMessage = getErrorMessages();
		setErrorStatus(errorMessage);
	}
	
	/**
	 * Change the text color if the attribute is overridden or not
	 */
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		setData(pnode, resolvedValue);
		if (insertField != null && !insertField.isDisposed()){
			if (elementValue != null){
				insertField.setForeground(ColorConstants.black);
			} else {
				insertField.setForeground(ColorConstants.gray);
			}
		}
	}
	
	/**
	 * Check if the measure unit should be saved into the jr element
	 * 
	 * @return true if the unit should be saved, false otherwise
	 */
	public boolean isLocalPersistent(){
		return ((style & PERSISTENT) == PERSISTENT);
	}

	@Override
	public Control getControl() {
		return insertField;
	}
}
