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
package com.jaspersoft.studio.toolbars;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.swt.widgets.NumericCombo;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

/**
 * Controls to change the textual attributes of the selected elements
 * 
 * @author Orlandin Marco
 *
 */
public class TextualContributionItem extends CommonToolbarHandler {
	
	/**
	 * Listener used to check if the font contribution in the preferences are changed, 
	 * and trigger the update of the combo
	 */
	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(FontsPreferencePage.FPP_FONT_LIST)) {
				//If the property change in the preferences force the refresh of the fonts
				setAvailableFonts();
				setAllControlsData();
			}
		}
	}
	
	/**
	 * The node actually selected which attributes are shown in the controls
	 */
	private APropertyNode showedNode = null;

	/**
	 * Main container of the controls
	 */
	private Composite controlsArea;
	
	/**
	 * Combo with the font names
	 */
	private Combo fontName;
	
	//Controls for the font size combo
	
	/**
	 * Combo with the font sizes
	 */
	private NumericCombo fontSize;

	//Controls for the font size buttons
	
	/**
	 * Toolitem to increment the text size
	 */
	private ToolItem incrementButton;
	
	/**
	 * % factor for the increment\decrement
	 */
	public static Integer factor = 10;
	
	//Controls for the font style
	
	/**
	 * Toolitem to set the text to bold
	 */
	private ToolItem bold;
	
	/**
	 * Toolitem to set the text to italic
	 */
	private ToolItem italic;
	
	/**
	 * Flag to ignore the change listeners when the state is refreshing 
	 */
	private boolean refreshing = false;
	
	/**
	 * The last font list set on the combo. Set or read the font list directly from the 
	 * combo has a little overhead because the status of the widget is check (about 100ms in some cases)
	 * so it is better to compare it with this one first
	 */
	private String[] fontList = null;
	
	PreferenceListener preferenceListener = new PreferenceListener();
	
	//Used listener
	
	/**
	 * 
	 * Listener used to update the status of the Font name, size bold and italic
	 * when they change inside the the element selected (maybe because they are 
	 * changed from the properties view)
	 */
	private PropertyChangeListener nodeChangeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (fontSize == null || fontSize.isDisposed() || fontName.isDisposed())
				return;

			refreshing = true;
			List<Object> selection = getSelectionForType(MTextElement.class);
			if (selection.size() == 1) {
				APropertyNode node = (APropertyNode) selection.get(0);
				if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_FONT_NAME)) {
					setFontNameText(node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_NAME));
				} else if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_FONT_SIZE)) {
					Object actaulValue = node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_SIZE);
					Object ownValue = node.getPropertyValue(JRDesignStyle.PROPERTY_FONT_SIZE);
					setFontSizeComboText(actaulValue, ownValue);
				} else if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_ITALIC)) {
					italic.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_ITALIC));
				} else if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_BOLD)) {
					bold.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_BOLD));
				}
			} else {
				setFontSizeComboText(null, null);
				setFontNameText(null);
				italic.setSelection(false);
				bold.setSelection(false);
				if (showedNode != null) {
					showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
					showedNode = null;
				}
			}
			refreshing = false;
		}
	};
	
	/**
	 * Listener called when the on of the fontsize buttons is pressed
	 */
	private SelectionAdapter fontSizeButtonSelect = new SelectionAdapter() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				JSSCompoundCommand cc = null;
				if (e.widget == incrementButton){
					cc = createIncremenrtsCommand(true, selection);
				} else {
					cc = createIncremenrtsCommand(false, selection);
				} 				
				getCommandStack().execute(cc);
			}
		}

	};
	
	/**
	 * Listener called when the element selected in the font name combo changes
	 */
	private SelectionAdapter fontNameComboSelect = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				String value = fontName.getText();
				JSSCompoundCommand cc = new JSSCompoundCommand(null);
				for(Object textElement : selection){
					Command changeValueCmd = createCommand(textElement, value, JRDesignStyle.PROPERTY_FONT_NAME);
					if (changeValueCmd != null) {
						cc.add(changeValueCmd);
						cc.setReferenceNodeIfNull(textElement);
					}
				}
				getCommandStack().execute(cc);
			}
		} 
	};
	
	
	/**
	 * Change the font size of one or more elements
	 */
	private ModifyListener fontSizeComboModify = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				JSSCompoundCommand cc = new JSSCompoundCommand(null);
				Float value = fontSize.getValueAsFloat();
				for (Object obj : selection) {
					Command changeValueCmd = createCommand(obj, value, JRDesignStyle.PROPERTY_FONT_SIZE);
					if (changeValueCmd != null) {
						cc.add(changeValueCmd);
						cc.setReferenceNodeIfNull(obj);
					}
				}		
				CommandStack cs = getCommandStack();
				if (cs != null) getCommandStack().execute(cc);
			}
		}
	};
	
	/**
	 * Listener called when the bold or italic button is pressed
	 */
	private SelectionAdapter booleanButtonSelected = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
				List<Object> selection = getSelectionForType(MTextElement.class);
				if (selection.isEmpty())
					return;
				Object value =	((ToolItem)e.widget).getSelection();
				Object property = e.widget.getData(WIDGET_DATA_KEY);
				JSSCompoundCommand cc = new JSSCompoundCommand(null);
				for(Object textElement : selection){
					Command changeValueCmd = createCommand(textElement, value, property);
					if (changeValueCmd != null) {
						cc.add(changeValueCmd);
						cc.setReferenceNodeIfNull(textElement);
					}
				}
				getCommandStack().execute(cc);
			}
		};
	};
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			if (!refreshing){
					List<Object> selection = getSelectionForType(MTextElement.class);
					if (selection.isEmpty())
						return;
					
					JSSCompoundCommand changeSizeCommands = new JSSCompoundCommand(null);
					String property = "";
					Object data = e.widget.getData(WIDGET_DATA_KEY);
					if (data instanceof VerticalTextAlignEnum) property = JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT;
					else if (data instanceof HorizontalTextAlignEnum) property = JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT;
					else return;
					for (Object textElement : selection){	
						changeSizeCommands.setReferenceNodeIfNull(textElement);
						Command c = createCommand(textElement, data, property);
						if (c != null) {
							changeSizeCommands.add(c);
						}
					}
					CommandStack cs = getCommandStack();
					cs.execute(changeSizeCommands);
			}
		}
	};

	@Override
	protected Control createControl(Composite parent) {
		controlsArea = new Composite(parent, SWT.NONE);
		RowLayout layout = new RowLayout();
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		controlsArea.setLayout(layout);
		
		fontList = null;
		fontName = new Combo(controlsArea, SWT.DROP_DOWN);
		fontName.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_NAME);
		fontName.addSelectionListener(fontNameComboSelect);
		setAvailableFonts();
		
		fontSize = new NumericCombo(controlsArea, SWT.DROP_DOWN, 0, 6);
		fontSize.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_SIZE);
		fontSize.setItems(ModelUtils.FONT_SIZES);
		fontSize.addModifyListener(fontSizeComboModify);

		RowData data = new RowData();
		data.width = 80;
		fontSize.setLayoutData(data);
		
		ToolBar sizeButtons = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		incrementButton = createFontSizeButton(true, sizeButtons);
		createFontSizeButton(false, sizeButtons);
		
		//Italic and bold button

		ToolBar buttons = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		
		bold = new ToolItem(buttons, SWT.CHECK);
		bold.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-bold.gif"));		
		bold.setToolTipText("Bold");
		bold.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_BOLD);
		bold.addSelectionListener(booleanButtonSelected);
		bold.setWidth(25);
		
		italic = new ToolItem(buttons, SWT.CHECK);
		italic.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-italic.gif"));		
		italic.setToolTipText("Italic");
		italic.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_ITALIC);
		italic.addSelectionListener(booleanButtonSelected);
		italic.setWidth(25);
		
		//Buttons to set the text alignment
		
		buttons = new ToolBar(controlsArea, SWT.FLAT | SWT.WRAP);
		
		ToolItem alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/left_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.LEFT);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/center_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.CENTER);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/right_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.RIGHT);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/justified_align.gif"));
		alignButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.JUSTIFIED);
		alignButton.addSelectionListener(pushButtonPressed);
		
		new ToolItem(buttons, SWT.SEPARATOR);
		
		alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-top.png"));
		alignButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.TOP);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"));
		alignButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.MIDDLE);
		alignButton.addSelectionListener(pushButtonPressed);
		
		alignButton = new ToolItem(buttons, SWT.PUSH);
		alignButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment.png"));
		alignButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.BOTTOM);
		alignButton.addSelectionListener(pushButtonPressed);
		
		setAllControlsData();

		return controlsArea;
	}

	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		fontList = null;
		ToolItem tiFontName = new ToolItem(parent,SWT.SEPARATOR);
		fontName = new Combo(parent, SWT.DROP_DOWN);
		fontName.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_NAME);
		fontName.addSelectionListener(fontNameComboSelect);
		setAvailableFonts();
		fontName.pack();
		tiFontName.setWidth(fontName.getSize().x);
		tiFontName.setControl(fontName);
		getToolItems().add(tiFontName);
		
		ToolItem tiFontSizeCombo = new ToolItem(parent,SWT.SEPARATOR);
		fontSize = new NumericCombo(parent, SWT.DROP_DOWN, 0, 6);
		fontSize.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_SIZE);
		fontSize.setItems(ModelUtils.FONT_SIZES);
		fontSize.addModifyListener(fontSizeComboModify);
		fontSize.pack();
		tiFontSizeCombo.setWidth(fontSize.getSize().x);
		tiFontSizeCombo.setControl(fontSize);
		getToolItems().add(tiFontSizeCombo);
		
		incrementButton = createFontSizeButton(true, parent);
		ToolItem decrementButton = createFontSizeButton(false, parent);
		getToolItems().add(incrementButton);
		getToolItems().add(decrementButton);
		
		//Italic and bold button
		bold = new ToolItem(parent, SWT.CHECK);
		bold.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-bold.gif"));		
		bold.setToolTipText("Bold");
		bold.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_BOLD);
		bold.addSelectionListener(booleanButtonSelected);
		bold.setWidth(25);
		getToolItems().add(bold);
		
		italic = new ToolItem(parent, SWT.CHECK);
		italic.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/font-italic.gif"));		
		italic.setToolTipText("Italic");
		italic.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_ITALIC);
		italic.addSelectionListener(booleanButtonSelected);
		italic.setWidth(25);
		getToolItems().add(italic);
		
		//Buttons to set the text alignment
		ToolItem alignLeftButton = new ToolItem(parent, SWT.PUSH);
		alignLeftButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/left_align.gif"));
		alignLeftButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.LEFT);
		alignLeftButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignLeftButton);
		
		ToolItem alignCenterButton = new ToolItem(parent, SWT.PUSH);
		alignCenterButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/center_align.gif"));
		alignCenterButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.CENTER);
		alignCenterButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignCenterButton);
		
		ToolItem alignRightButton = new ToolItem(parent, SWT.PUSH);
		alignRightButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/right_align.gif"));
		alignRightButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.RIGHT);
		alignRightButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignRightButton);
		
		ToolItem alignJustifiedButton = new ToolItem(parent, SWT.PUSH);
		alignJustifiedButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/justified_align.gif"));
		alignJustifiedButton.setData(WIDGET_DATA_KEY, HorizontalTextAlignEnum.JUSTIFIED);
		alignJustifiedButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignJustifiedButton);
		
		new ToolItem(parent, SWT.SEPARATOR);
		
		ToolItem alignTopButton = new ToolItem(parent, SWT.PUSH);
		alignTopButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-top.png"));
		alignTopButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.TOP);
		alignTopButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignTopButton);
		
		ToolItem alignMiddleButton = new ToolItem(parent, SWT.PUSH);
		alignMiddleButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"));
		alignMiddleButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.MIDDLE);
		alignMiddleButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignMiddleButton);
		
		ToolItem alignBottomButton = new ToolItem(parent, SWT.PUSH);
		alignBottomButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment.png"));
		alignBottomButton.setData(WIDGET_DATA_KEY, VerticalTextAlignEnum.BOTTOM);
		alignBottomButton.addSelectionListener(pushButtonPressed);
		getToolItems().add(alignBottomButton);
		
		setAllControlsData();
		
		return true;
	}

	
	/**
	 * Create a single button into the toolbar
	 * 
	 * @param increment true if the button should be used for increment, false otherwise
	 */
	private ToolItem createFontSizeButton(final boolean increment, ToolBar buttons){
		Image imageValue;
		String message;
		if (increment){
			imageValue = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-up.png");
			message = MessageFormat.format(Messages.SPButon_Size_Increment, new Object[]{factor.toString()});
		} else {
			imageValue = JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/edit-size-down.png");
			message = MessageFormat.format(Messages.SPButon_Size_Decrement, new Object[]{factor.toString()});
		}
		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setImage(imageValue);
		button.setToolTipText(message);		
		button.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_SIZE);
		button.setWidth(25);
		button.addSelectionListener(fontSizeButtonSelect);
		return button;
	}
	


	/**
	 * Create a command to change the property of the element
	 * 
	 * @param model the element
	 * @param value the new value
	 * @param property the property
	 * @return the command to change the property
	 */
	protected Command createCommand(Object model, Object value, Object property) {
		if (!(model instanceof IPropertySource))
			return null;
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget((IPropertySource) model);
		cmd.setPropertyId(property);
		cmd.setPropertyValue(value);
		return cmd;
	}
	
	/**
	 * Check if the font in the combo should be updated or not
	 * 
	 * @param newFont the new fonts
	 * @return Return true if the new font are different from the fonts previously stored
	 * false otherwise
	 */
	private boolean needFontsUpdate(String[] newFont){
		return (fontList == null || !fontList.equals(newFont));
	}

	/**
	 * Set the available fonts inside the combo for the current report
	 */
	private void setAvailableFonts(){
		List<Object> selection = getSelectionForType(MTextElement.class);
		if (selection.size() > 0){
			APropertyNode node = (APropertyNode)selection.get(0);
			//The fonts are already cached here
			String[] fonts = node.getJasperConfiguration().getFontList();
			if (needFontsUpdate(fonts) &&  fontName != null && !fontName.isDisposed()) {
				fontName.setItems(fonts);
				fontList = fonts;
			}
		}
	}

	@Override
	public boolean isVisible() {
		JaspersoftStudioPlugin.getInstance().removePreferenceListener(preferenceListener);
		if (!super.isVisible()) {
			return false;
		}
		
		List<Object> selection = getSelectionForType(MTextElement.class);
		boolean selectionValid = selection.size() > 0;
		if (selectionValid){
			setAvailableFonts();
			setAllControlsData();
		} else if (showedNode != null) {
			showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = null;
		}
		if (selectionValid){
			JaspersoftStudioPlugin.getInstance().addPreferenceListener(preferenceListener);
		}
		return selectionValid;
	}
	
	/**
	 * Set a string inside the font name combo
	 * 
	 * @param value the string
	 */
	protected void setFontNameText(Object value) {
		fontName.setText(Misc.nvl(value, "").toString());
	}

	/**
	 * Set the font size on the combo. It set the value to know if the number is 
	 * inherited or not.
	 * 
	 * @param resolvedValue the value of the font size resolved considering also the hirarchy
	 * @param elementValue the font size value on the element itself
	 */
	protected void setFontSizeComboText(Object resolvedValue, Object elementValue) {
		if (fontSize == null || fontSize.isDisposed())
			return;
		if (resolvedValue != null) {
			int oldpos = fontSize.getCaretPosition();
			if (elementValue == null) {
				fontSize.setDefaultValue((Number)resolvedValue);
			}
			fontSize.setValue((Number)elementValue);
			if (fontSize.getText().length() >= oldpos){
				fontSize.setSelection(new Point(oldpos, oldpos));
			}
		} else if (elementValue != null){
			int oldpos = fontSize.getCaretPosition();
			fontSize.setValue((Number)elementValue);
			if (fontSize.getText().length() >= oldpos){
				fontSize.setSelection(new Point(oldpos, oldpos));
			}
		} else {
			fontSize.setValue(null);
		}
	}
	
	/**
	 * Create the command to change the font size
	 * 
	 * @param increment true if you want to increment the font, false otherwise
	 */
	protected JSSCompoundCommand createIncremenrtsCommand(boolean increment, List<Object> models){
		JSSCompoundCommand changeSizeCommands = new JSSCompoundCommand(null);
		for (Object model : models){
			Object fontSize  = ((APropertyNode)model).getPropertyActualValue(JRBaseFont.PROPERTY_FONT_SIZE);
			Float newValue = 2.0f;
			if (fontSize != null && fontSize.toString().length()>0){
				newValue = Float.valueOf(fontSize.toString());
				Integer plus = null;
				if (increment) plus = Math.round((new Float(newValue) / 100)*factor)+1;
				else plus =  Math.round((new Float(newValue) / 100)*-factor)-1;
				if ((newValue+plus)>99) newValue = 99.0f;
				else if ((newValue+plus)>0) newValue += plus;

				Command c = createCommand(model, newValue, JRBaseFont.PROPERTY_FONT_SIZE );
				changeSizeCommands.setReferenceNodeIfNull(model);
				if (c != null) {
					changeSizeCommands.add(c);
				}
			}
		}
		return changeSizeCommands;
	}

	/**
	 * Initialize all the controls that show the state of the object with the value of the
	 * selected element
	 */
	protected void setAllControlsData(){
		if (fontSize == null || fontSize.isDisposed() || fontName.isDisposed()) return;
		refreshing = true;
		List<Object> selection = getSelectionForType(MTextElement.class);
		if (selection.size() == 1){
			APropertyNode node = (APropertyNode)selection.get(0);
			Object actaulValue = node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_SIZE);
			Object ownValue = node.getPropertyValue(JRDesignStyle.PROPERTY_FONT_SIZE);
			setFontSizeComboText(actaulValue, ownValue);
			setFontNameText(node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_NAME));
			italic.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_ITALIC));
			bold.setSelection((Boolean) node.getPropertyActualValue(JRDesignStyle.PROPERTY_BOLD));
			
			if (showedNode != null) showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = node;
			showedNode.getPropertyChangeSupport().addPropertyChangeListener(nodeChangeListener);
			
		} else {
			setFontSizeComboText(null, null);
			setFontNameText(null);
			italic.setSelection(false);
			bold.setSelection(false);
			if (showedNode != null) {
				showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);	
				showedNode = null;	
			}
		}
		refreshing = false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (showedNode != null) {
			showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = null;
		}
		if (controlsArea != null) {
			controlsArea.dispose();
			controlsArea = null;
		}
		fontSize = null;
		bold = null;
		italic = null;
		factor = 10;
		refreshing = false;
	}
}
