/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
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
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.color.Tag;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;

/**
 * This is a color selection widget that offer two method of inputs to provide
 * the colors patter. One based on a selection of a color and a variation from 
 * a closed set. And one providing a number of fixed colors. The fixed number 
 * of fixed colors start from zero and can be incremented with the method 
 * add buttons. This method create a button that can be used to choose a 
 * single color.
 * The input method can be changed using an appropriate button crated ad the right
 * of the controls or called from outside the method switchInputMethod
 * 
 * @author Orlandin Marco
 *
 */
public class ColorSelectionWidget {
	
	/**
	 * Class that to initially define a button 
	 * that represent a color
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ButtonDescriptor{
		
		/**
		 * The unique button id
		 */
		private String id;
		
		/**
		 * The text placed before the button
		 */
		private String text;
		
		/**
		 * The initial button color
		 */
		private RGB color;
		
		public ButtonDescriptor(String id, String text, RGB color){
			this.id = id;
			this.text = text;
			this.color = color;
		}
	}
	
	/**
	 * 
	 * Enumeration to switch between the input method, with MANUAL
	 * only the buttons (where every button represent a color) are shown.
	 * With schema are shown instead the combo schema\variations
	 *
	 */
	public enum ColorInput{MANUAL, SCHEMA}	
	
	/**
	 * Provider to generate an image from an RGB color, used to create the image
	 * for the single color toolbutton
	 */
	private final static ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);
	
	/**
	 * Layout used to switch between the two types of control to define the cell 
	 * colors, color schema and manually color
	 */
	private StackLayout layout;
	
	/**
	 * Button used to switch between the two types of control to define the cell 
	 * colors
	 */
	private Button changeControl;
	
	/**
	 * Composite where are placed the toolbutton to define manually the colors
	 */
	private Composite manualCompoiste;
	
	/**
	 * Composite where are placed the combo to define the colors using the schema\variations method
	 */
	private Composite schemaCompoiste;
	
	/**
	 * Combo for the color schema
	 */
	private TableCombo colorScheme;
	
	/**
	 * Variations scheme for the color
	 */
	private Combo variations;
	
	/**
	 * List of the available schemes for the variations
	 */
	private List<Tag> variants;
	
	/**
	 * Map of all the created buttons for the color, where the key is the unique
	 * key of the button and the item is the button itself
	 */
	private HashMap<String, ToolItem> buttonsMap;
	
	/**
	 * Selection adapter used when the color of a button change, the method 
	 * widget selected will be called
	 */
	private SelectionAdapter selectionChangeButton;
	
	/**
	 * Selection adapter used when the value of the combo schema or variation change, the method 
	 * widget selected will be called
	 */
	private SelectionAdapter selectionChangeCombo;
	
	/**
	 * Composite where all the control will be placed
	 */
	private Composite parent;
	
	/**
	 * List of the toolitem that will be created and inserted into the buttonsMap
	 */
	private List<ButtonDescriptor> buttonToAdd;
	
	/**
	 * Constructor, not that to create the widget you need also to call addButton for every
	 * button you want to create, and when you have finished you must call createControl;
	 * 
	 * @param parent composite where the widget will be placed 
	 * @param selectionChangeButton Selection adapter used when the color of a button change, the method 
	 * widget selected will be called
	 * @param selectionChangeCombo Selection adapter used when the value of the combo schema or variation change, the method 
	 * widget selected will be called
	 */
	public ColorSelectionWidget(Composite parent, SelectionAdapter selectionChangeButton,  SelectionAdapter selectionChangeCombo){
		this.parent = parent;
		this.selectionChangeButton = selectionChangeButton;
		this.selectionChangeCombo = selectionChangeCombo;
		this.buttonToAdd = new ArrayList<ButtonDescriptor>();
		this.buttonsMap = new HashMap<String, ToolItem>();
	}
	
	/**
	 * Request the creation of a new button. Note that the creation of all buttons will happen
	 * only after createControl is called. The order of the buttons is the same they added with this
	 * method
	 * 
	 * @param id The unique id of the button
	 * @param text The text to place before the button
	 * @param initialColor The initial color of the button
	 */
	public void addButton(String id, String text, RGB initialColor){
		buttonToAdd.add(new ButtonDescriptor(id, text, initialColor));
	}
	
	/**
	 * Create all the control of the widget, must be called onece
	 */
	public void createControl(){
		//Composite where the two input methods for the color are placed as stack
		Composite colorComposite = new Composite(parent, SWT.NONE);
		colorComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		layout = new StackLayout();
		colorComposite.setLayout(layout);
		
		//Crate the controls for the input method based on schema variations
		schemaCompoiste = new Composite(colorComposite, SWT.NONE);
		schemaCompoiste.setLayout(new GridLayout(2,false));
		Label firstLabel = new Label(schemaCompoiste,SWT.NONE);
		firstLabel.setText(Messages.TableWizardLayoutPage_color_schema_label);
		
		colorScheme = new TableCombo(schemaCompoiste, SWT.BORDER);
		List<String> colors = ColorSchemaGenerator.getColors();
		for(String color : colors){
			TableItem item = new TableItem(colorScheme.getTable(), SWT.READ_ONLY);
			item.setImage(ColorSchemaGenerator.getImagePreview(color));
			item.setText(color);
		}
		colorScheme.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		colorScheme.select(0);
		colorScheme.setEditable(false);
		
		Label secondLabel = new Label(schemaCompoiste,SWT.NONE);
		secondLabel.setText(Messages.TableWizardLayoutPage_variations_label);
		
		variations = new Combo(schemaCompoiste,SWT.READ_ONLY);
		variations.setItems(getVariantsName());
		variations.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		variations.select(0);
		
		//Create the controls for the input method based on the manual selection of every color
		manualCompoiste = new Composite(colorComposite, SWT.NONE);
		manualCompoiste.setLayout(new GridLayout(4,false));
		
		//Used the color from a default style to initialize the toolitems colors values
		for(ButtonDescriptor desc : buttonToAdd){
			ToolItem item = createSingleColors(desc.text, manualCompoiste, desc.color);
			buttonsMap.put(desc.id, item);
		}


		layout.topControl = schemaCompoiste;
		//Create the button to switch between the two input method
		changeControl = new Button(parent, SWT.NONE);
		changeControl.setText(">>"); //$NON-NLS-1$
		changeControl.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		changeControl.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (layout.topControl == schemaCompoiste) switchInputMethod(ColorInput.MANUAL);
				else switchInputMethod(ColorInput.SCHEMA);
			}
		});
		
		variations.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Event selEvent = new Event();
				selEvent.widget = e.widget;
				selectionChangeCombo.widgetSelected(new SelectionEvent(selEvent));
			}
		});
		colorScheme.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectionChangeCombo.widgetSelected(e);
			}
		});
	}
	
	/**
	 * Return the color of a precise button
	 * 
	 * @param buttonId the button unique id
	 * @return the color of the button, or null if the id is not valid
	 */
	public RGB getButtonData(String buttonId){
		ToolItem button = buttonsMap.get(buttonId);
		if (button != null){
			return (RGB)button.getData();
		}
		return null;
	}
	
	/**
	 * Set the color of a precise button
	 * 
	 * @param buttonId the button unique id 
	 * @param newColor a not null RGB color
	 */
	public void setButtonData(String buttonId, RGB newColor){
		ToolItem button = buttonsMap.get(buttonId);
		if (button != null){
			button.setImage(colorLabelProvider.getImage(newColor));
			button.setData(newColor);
		}
	}
	
	/**
	 * Return the value selected in the variant combo
	 * 
	 * @return a not null ColorSchemaGenerator.SCHEMAS
	 */
	public ColorSchemaGenerator.SCHEMAS getVariantSelectedKey(){
		return (ColorSchemaGenerator.SCHEMAS)variants.get(variations.getSelectionIndex()).getValue();
	}
	
	/**
	 * Return the color selected in the color combo
	 * 
	 * @return a not null string that represent a color
	 */
	public String getSchemaSelected(){
		return colorScheme.getItem(colorScheme.getSelectionIndex());
	}
	
	/**
	 * Create a toolitem to represent a color. In the passed composite will be 
	 * created two element, a label with a text and the toolitem
	 * 
	 * @param text the text that will be used into the label
	 * @param parent the composite where the controls will be placed
	 * @param color the color used to initialize the control
	 * @return the created toolitem
	 */
	private ToolItem createSingleColors(String text, Composite parent, RGB color){		
		new Label(parent, SWT.NONE).setText(text);
		final ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(parent.getBackground());

		final ToolItem foreButton = new ToolItem(toolBar, SWT.PUSH);
		setButtonColor(color, foreButton);
		foreButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(toolBar.getShell());
				cd.setText(Messages.TableWizardLayoutPage_colorSelectionDialog);
				RGB newColor = cd.open();
				if (newColor != null) {
					setButtonColor(newColor,foreButton);
					selectionChangeButton.widgetSelected(e);
				}
			}
		});
		toolBar.pack();
		return foreButton;
	}
	
	/**
	 * Switch the input mode to the specified one 
	 * 
	 * @param mode a ColorInput that specify the input mode between manual or schema\variation
	 */
	public void switchInputMethod(ColorInput mode){
		if (mode.equals(ColorInput.MANUAL)){
			layout.topControl = manualCompoiste;
			changeControl.setText("<<"); //$NON-NLS-1$
			changeControl.setToolTipText(Messages.TableWizardLayoutPage_changeButtonTooltip1);
		} else {
			layout.topControl = schemaCompoiste;
			changeControl.setText(">>"); //$NON-NLS-1$
			changeControl.setToolTipText(Messages.TableWizardLayoutPage_changeButtonTooltip2);
		}
		schemaCompoiste.getParent().layout();
	}
		
	/**
	 * Set a color on a toolbutton
	 * @param newColor color to show
	 * @param button button where the color will be shown
	 */
	private void setButtonColor(RGB newColor, ToolItem button){
		button.setImage(colorLabelProvider.getImage(newColor));
		button.setData(newColor);
	}
	
	/**
	 * Return an array of string that represents the human name of the 
	 * color variations
	 * 
	 * @return array of the color variations name
	 */
	private String[] getVariantsName(){
		variants = ColorSchemaGenerator.getVariants();
		String[] variantsName = new String[variants.size()];
		for(int i=0; i<variants.size(); i++)
			variantsName[i] = variants.get(i).getName();
		return variantsName;
	}
}
