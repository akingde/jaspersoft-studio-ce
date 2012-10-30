/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.HashMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.report.util.Unit;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * This class implement a Textfield where display a number with a measure unit. The 
 * number and the measure unit can be changed and the conversion are done automatically
 * @author Orlandin Marco
 *
 */
public class TextMisureUnit extends ASPropertyWidget {


	/**
	 * Hash map the bind a measure unit, by its key, to a series of method 
	 * to convert and handle that measure
	 */
	private static HashMap<String, MeasureUnit> unitsMap = null;

	/**
	 * The text field
	 */
	private Text insertField;

	/**
	 * The key of the default measure unit
	 */
	private String defaultValue;

	private class LostFocusListener implements FocusListener {

		@Override
		public void focusLost(FocusEvent e) {
			updateValue();
		}

		@Override
		public void focusGained(FocusEvent e) {}
	}

	/**
	 * Class that offer the method to convert and handle a measure unit 
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
		 * Holds the suggested precision when this measure is displayed. It's not the 
		 * real precision, but it's intended to be used when the measure is displayed
		 */
		private int precision;

		/** Creates a new instance of Unit */
		public MeasureUnit(String unitName) {
			this(unitName, unitName,2);
		}

		public MeasureUnit(String keyName, String unitName, int precision) {
			this.unitName = unitName;
			this.keyName = keyName;
			this.precision = precision;
		}
		
		/**
		 * Number of decimal digits to show when this measure is displayed
		 * @return
		 */
		public int getPrecision(){
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
		 * @param targetUnit The MeasureUnit of the target type
		 * @param value the value to convert
		 * @return the converted value
		 */
		public String doConversionFromThis(MeasureUnit targetUnit, String value){
			if (this.getKeyName().equals(targetUnit.getKeyName())) return value;
			return String.valueOf((new Unit(Double.parseDouble(value), keyName)).getValue(targetUnit.getKeyName()));
		}
	}

	public TextMisureUnit(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	/**
	 * Add the default measure type to the map
	 */
	private void CreateDefaultUnits() {
		unitsMap = new HashMap<String, MeasureUnit>();
		MeasureUnit[] units = new MeasureUnit[5];
		//Adding the measure unit for pixel
		units[0] = new MeasureUnit(Unit.PX, "pixels",0);
		unitsMap.put(Unit.PX, units[0]);
		//Adding the measure unit for inch
		units[1] = new MeasureUnit(Unit.INCH, "inches",2);
		unitsMap.put(Unit.INCH, units[1]);
		//Adding the meausre unit for centimeter
		units[2] = new MeasureUnit(Unit.CM, "cm",2);
		unitsMap.put(Unit.CM, units[2]);
		//Adding the measure unit for millimeters
		units[3] = new MeasureUnit(Unit.MM, "mm",2);
		unitsMap.put(Unit.MM, units[3]);
		//Adding the measure unit for meters
		units[4] = new MeasureUnit(Unit.METER, "m",2);
		unitsMap.put(Unit.METER, units[4]);
	}
	
	/**
	 * Return the measure unit typed in the textfield
	 * @param value content of the text field
	 * @return measure unit, it's the last alphabetical string in the textfield
	 */
	private String getMeasureUnit(String value) {
		String[] results = value.split("[^a-z]");
		//If the array is void then no measure unit are specified
		if (results.length == 0) return null;
		return results[results.length - 1];
	}

	/**
	 * Cut the decimal of a double a precise number of digits
	 * @param number number to cut
	 * @param numDigits number of decimal digits
	 * @return cut double, represented as string
	 */
	private static String truncateDouble(double number, int numDigits) {
		String arg = Double.toString(number);
		int idx = arg.indexOf('.');
		int offset = numDigits>0 ? 1 : 0;
		if (idx != -1) {
			if (arg.length() > idx + numDigits) {
				arg = arg.substring(0, idx + numDigits + offset);
			}
		}
		return arg;
	}

	/**
	 * Read the value in the textfield and update it in the model, but before the value
	 * is converted to pixel, and in the textbox is displayed as default type
	 */
	private void updateValue() {
		String text = insertField.getText();
		String key = getMeasureUnit(text);
		MeasureUnit defaultUnit = unitsMap.get(defaultValue);
		String value;
		MeasureUnit unit;
		if (key == null){
			unit = unitsMap.get(Unit.getKeyFromAlias(defaultValue));
			value = text;
		} else {
			unit = unitsMap.get(Unit.getKeyFromAlias(key));
			value = text.substring(0, text.indexOf(key));
		}
		if (unit != null) {
			String convertedValue = unit.doConversionFromThis(defaultUnit, value);
			insertField.setText(convertedValue.concat(defaultUnit.getKeyName()));
			try{
				Integer newValue = Integer.parseInt(getText());
				if (!section.changeProperty(pDescriptor.getId(), newValue)) {
					setData(section.getElement(), newValue);
				}
				insertField.setBackground(null);
			}catch(NumberFormatException ex){
				insertField.setBackground(ColorConstants.red);
			}
		}
	}

	/**
	 * Set the value into the textfield, it's converted from pixel to the default measure unit
	 * @param value the value to set, must be in pixel
	 */
	public void setText(String value) {
		MeasureUnit defaultMeasure = unitsMap.get(defaultValue);
		double dValue = (new Unit(Double.parseDouble(value), Unit.PX)).getValue(defaultMeasure.getKeyName());
		insertField.setBackground(null);
		insertField.setText(truncateDouble(dValue,defaultMeasure.getPrecision()).concat(defaultValue));
	}

	/**
	 * Return the value in the textfield, it's returned as pixel
	 * @return the value in the textfield as pixel
	 */
	public String getText() {
		String text = insertField.getText().trim().toLowerCase();
		String key = getMeasureUnit(text);
		MeasureUnit unit = unitsMap.get(Unit.getKeyFromAlias(key));
		if (unit != null) {
			String value = text.substring(0, text.indexOf(key));
			Double dValue = (new Unit(Double.parseDouble(value), unit.getKeyName())).getValue(Unit.PX);
			return String.valueOf(dValue.longValue());
		}
		return null;
	}

	/**
	 * Set the size of the textfield
	 * @param parent parent of the textfield
	 * @param chars number of chars to store (used to choose the size)
	 */
	protected void setWidth(Composite parent, int chars) {
		int w = UIUtils.getCharWidth(insertField) * chars;
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

	@Override
	protected void createComponent(Composite parent) {
		if (unitsMap == null) {
			CreateDefaultUnits();
		}
		insertField = new Text(parent, SWT.BORDER);
		insertField.addFocusListener(new LostFocusListener());
		insertField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR)
					updateValue();
			}
		});

		insertField.setToolTipText(pDescriptor.getDescription());
		setWidth(parent, 10);
	}

	/**
	 * Receive a number and set it in the textfiled
	 * @param f the number
	 */
	public void setDataNumber(Number f) {
		if (f != null) {
			int oldpos = insertField.getCaretPosition();
			setText(f.toString());
			if (insertField.getText().length() >= oldpos)
				insertField.setSelection(oldpos, oldpos);
		} else
			insertField.setText("");
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		defaultValue = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getString(DesignerPreferencePage.P_PAGE_DEFAULT_UNITS);
		Number n = (Number) value;
		setDataNumber(n);
	}

	@Override
	public Control getControl() {
		return insertField;
	}

}
