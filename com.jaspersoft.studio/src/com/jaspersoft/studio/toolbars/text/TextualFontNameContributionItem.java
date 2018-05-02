/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.property.ResetValueCommand;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboItemSeparator;
import com.jaspersoft.studio.property.combomenu.WritableComboTableViewer;
import com.jaspersoft.studio.property.section.widgets.SPFontNamePopUp;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.JSSTableCombo;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.fonts.FontUtil;

/**
 * Controls to change the font name of  the selected items. Since this is a complex toolitem (because
 * the created toolitem contains a composite with other controls) this need to
 * be put in a toolbar where at least a static simple item is present. In the context
 * of the textual toolbar this is done using the {@link DecrementFontSizeButtonContributionItem} and
 * the {@link IncrementFontSizeButtonContributionItem}
 * 
 * @author Orlandin Marco
 *
 */
public class TextualFontNameContributionItem extends CommonToolbarHandler {
	
	/**
	 * On MacOS seems the contextual menu is not opened on combo, this
	 * lister will force it to open when a right click is found
	 */
	protected static MouseAdapter macComboMenuOpener = new MouseAdapter() {
		
		@Override
		public void mouseUp(MouseEvent e) {	
			if (e.button == 3 && ((Control)e.widget).getMenu() != null){
				Menu menu = ((Control)e.widget).getMenu();
				if (!menu.isDisposed() && !menu.isVisible()){
	        		Point location = e.widget.getDisplay().getCursorLocation();
					menu.setLocation(location.x, location.y);
					menu.setVisible(true);
				}
			}
		}
	};
	
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
	private WritableComboTableViewer fontName;
	

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
			if (fontName.isDisposed())
				return;

			refreshing = true;
			List<Object> selection = getSelectionForType(MTextElement.class);
			if (selection.size() == 1) {
				APropertyNode node = (APropertyNode) selection.get(0);
				Object actaulNameValue = node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_NAME);
				Object ownNameValue = node.getPropertyValue(JRDesignStyle.PROPERTY_FONT_NAME);
				setFontNameText(actaulNameValue, ownNameValue);
			} else {
				setFontNameText(null, null);
				if (showedNode != null) {
					showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
					showedNode = null;
				}
			}
			refreshing = false;
		}
	};
	
	
	/**
	 * Listener called when the element selected in the font name combo changes
	 */
	private ComboItemAction fontNameComboModify = new ComboItemAction() {
		
		@Override
		public void exec() {
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
		fontName = new WritableComboTableViewer(controlsArea, JSSTableCombo.STRIGHT_CORNER);
		fontName.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_NAME);
		fontName.addSelectionListener(fontNameComboModify);
		setAvailableFonts();
		
		setAllControlsData();

		return controlsArea;
	}

	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		fontList = null;
		ToolItem tiFontName = new ToolItem(parent,SWT.SEPARATOR);
		fontName = new WritableComboTableViewer(parent, JSSTableCombo.STRIGHT_CORNER);
		fontName.setData(WIDGET_DATA_KEY, JRDesignStyle.PROPERTY_FONT_NAME);
		fontName.addSelectionListener(fontNameComboModify);
		setAvailableFonts();
		fontName.getControl().pack();
		tiFontName.setWidth(200);
	
		tiFontName.setControl(fontName.getControl());
		getToolItems().add(tiFontName);
		
		setAllControlsData();
		
		return true;
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
		refreshing = true;
		List<Object> selection = getSelectionForType(MTextElement.class);
		if (selection.size() > 0){
			APropertyNode node = (APropertyNode)selection.get(0);
			//The fonts are already cached here
			JasperReportsConfiguration jConfig = node.getJasperConfiguration();
			String[] fonts = jConfig.getFontList();
			if (needFontsUpdate(fonts) &&  fontName != null && !fontName.isDisposed()) {
				fontName.setItems(stringToItems(ModelUtils.getFontNames(jConfig), jConfig));
				
				fontList = fonts;
			}
		}
		refreshing = false;
	}
	
	/**
	 * Convert a list of array of string into a List of ComboItem, ready to be inserted into a combo popup
	 * 
	 * @param fontsList
	 *          List of array of fonts, between every array will be inserted a separator
	 * @return List of combo item
	 */
	private List<ComboItem> stringToItems(List<String[]> fontsList, JasperReportsConfiguration jConfig) {
		int i = 0;
		List<ComboItem> itemsList = new ArrayList<ComboItem>();
		FontUtil util = FontUtil.getInstance(jConfig);
		for (int index = 0; index < fontsList.size(); index++) {
			String[] fonts = fontsList.get(index);
			for (String element : fonts) {
				Image resolvedImage = ResourceManager.getImage(element);
				if (resolvedImage == null){
					resolvedImage = new Image(null, ImageUtils.convertToSWT(SPFontNamePopUp.createFontImage(element, util)));
					ResourceManager.addImage(element, resolvedImage);
				}
				itemsList.add(new ComboItem(element, true, resolvedImage, i, element, element));
				i++;
			}
			if (index + 1 != fontsList.size() && fonts.length > 0) {
				itemsList.add(new ComboItemSeparator(i));
				i++;
			}
		}
		return itemsList;
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
	 * @param resolvedValue the font name resolved trough the JR hierarchy
	 * @param elementValue the value of the element itself
	 */
	protected void setFontNameText(Object resolvedValue, Object elementValue) {
		//Point selection = fontName.getSelection();
		fontName.setText(Misc.nvl(resolvedValue, "").toString());
		if (elementValue == null){
			fontName.setInherithed(true);
		} else {
			fontName.setInherithed(false);
		}
		//fontName.setSelection(selection);
	}
	

	/**
	 * Initialize all the controls that show the state of the object with the value of the
	 * selected element
	 */
	protected void setAllControlsData(){
		if (fontName == null || fontName.isDisposed()) return;
		refreshing = true;
		List<Object> selection = getSelectionForType(MTextElement.class);
		if (selection.size() == 1){
			APropertyNode node = (APropertyNode)selection.get(0);
					
			Object actaulNameValue = node.getPropertyActualValue(JRDesignStyle.PROPERTY_FONT_NAME);
			Object ownNameValue = node.getPropertyValue(JRDesignStyle.PROPERTY_FONT_NAME);
			setFontNameText(actaulNameValue, ownNameValue);
			createContextualMenu(node, fontName.getControl(), JRDesignStyle.PROPERTY_FONT_NAME);
		
			if (showedNode != null) showedNode.getPropertyChangeSupport().removePropertyChangeListener(nodeChangeListener);
			showedNode = node;
			showedNode.getPropertyChangeSupport().addPropertyChangeListener(nodeChangeListener);
			
		} else {
			setFontNameText(null, null);
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
		refreshing = false;
	}
	
	/**
	 * Create a contextual menu for the passed control. This contextual menu
	 * will contains the action to reset the value of a property if the property
	 * has default value inside the node. Also it will contain the action to set the
	 * value to null if the operation is allowed.
	 * 
	 * Since on mac the combo item doens't have a contextual menu it add a special listneer
	 * for them as workaround to the problem
	 * 
	 * @param node node where the the command will be executed and from where the default map is extracted
	 * @param control control where the contextual menu will be set
	 * @param propertyID id of the property to set
	 */
	protected void createContextualMenu(final APropertyNode node, final Control control, final String propertyID){
		if (node != null && control != null && !control.isDisposed()){
		
			//MacOS fix, the combo on MacOS doesn't have a contextual menu, so we need to handle this listener manually
			boolean handleComboListener = Util.isMac() && control.getClass() == Combo.class;
			if (handleComboListener){
				control.removeMouseListener(macComboMenuOpener);
			}
			
			boolean entryCreated = false;
			Map<String, DefaultValue> defaultMap = node.getDefaultsPropertiesMap();
			if (defaultMap != null){
				DefaultValue defaultEntry = defaultMap.get(propertyID);
				if (defaultEntry != null && (defaultEntry.isNullable() || defaultEntry.hasDefault())){
					Menu controlMenu = new Menu(control);
					
					//Create the reset entry if necessary
					if (defaultEntry.hasDefault()){
						MenuItem resetItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						resetItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								ResetValueCommand cmd = new ResetValueCommand();
								cmd.setPropertyId(propertyID);
								cmd.setTarget(node);
								CommandStack cs = getCommandStack();
								cs.execute(cmd);
								control.setFocus();
							}
						});
				    resetItem.setText(Messages.ASPropertyWidget_0);
					}
					
					//Create the null entry if necessary
					if (defaultEntry.isNullable()){
						MenuItem nullItem = new MenuItem(controlMenu, SWT.NONE);
						entryCreated = true;
						nullItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								SetValueCommand cmd = new SetValueCommand();
								cmd.setPropertyId(propertyID);
								cmd.setTarget(node);
								cmd.setPropertyValue(null);
								CommandStack cs = getCommandStack();
								cs.execute(cmd);
								control.setFocus();
							}
						});
				    nullItem.setText(Messages.ASPropertyWidget_1);
					}
					
					//if the control already have a menu dispose it first, since it is a swt widget
					//it is not disposed automatically by the garbage collector
					if (control.getMenu() != null){
						control.getMenu().dispose();
					}
					
					//set the new menu
					control.setMenu(controlMenu);
					if (handleComboListener){
						control.addMouseListener(macComboMenuOpener);
					}
				}
			}
			if (!entryCreated) {
				//if no entry was created remove the contextual menu, but first dispose
				//the old one
				if (control.getMenu() != null){
					control.getMenu().dispose();
				}
				control.setMenu(null);
			}
		}
		
	}
}
