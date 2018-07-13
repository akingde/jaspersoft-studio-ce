/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.combomenu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.help.HelpSystem;

import net.sf.jasperreports.eclipse.ui.JSSTableCombo;
import net.sf.jasperreports.eclipse.ui.ValueChangedEvent;
import net.sf.jasperreports.eclipse.ui.ValueChangedListener;

/**
 * Class that manage the {@link JSSTableCombo}, where a combo is created trough a canvas and a table for the menu. the {@link JSSTableCombo}
 * want to imitate a combobox where a series of element are listed, but the selection of one item is done using a Table into a separeted shell, 
 * opened by a left click on the combobox
 * 
 * @author Orlandin Marco
 * 
 */
public class WritableComboTableViewer implements IMenuProvider {

	private static final String ITEM_DATA = "itemData";
	
	/**
	 * Style bit: Create handle control and drop-down widget with default behaviours, i.e. showing text, showing image,
	 * using menu as drop-down widget.
	 */
	public static final int NORMAL = ComboButton.NORMAL;

	/**
	 * Style bit: Don't show text.
	 */
	public static final int NO_TEXT = ComboButton.NO_TEXT;

	/**
	 * Style bit: Don't show image.
	 */
	public static final int NO_IMAGE = ComboButton.NO_IMAGE;

	/**
	 * List of registered actions that will be executed when an element is selected
	 */
	private List<ComboItemAction> listeners;

	/**
	 * Button that made the popup menu appears
	 */
	private JSSTableCombo dropDownHandle;

	/**
	 * List of the items inside the popup menu
	 */
	private List<ComboItem> elementList = new ArrayList<ComboItem>();

	/**
	 * Last element selected in the menu
	 */
	private SelectedComboItem selectedItem = null;
	
	/**
	 * Disable the emphasis effect on the last selected item when set to true
	 */
	private boolean disableSelectedItemEmphasis = false;
	
	private String longestName = null;
	
	/**
	 * Constructs a new instance of this class given its parent and a style value describing its behavior and appearance.
	 * 
	 * @param parent
	 *          a composite control which will be the parent of the new instance (cannot be null)
	 * @param style
	 *          the style of control to construct
	 * 
	 * @see #NORNAL
	 * @see #NO_TEXT
	 * @see #NO_IMAGE
	 * @see #FILTERED
	 */
	public WritableComboTableViewer(Composite parent, int style) {
		dropDownHandle = new JSSTableCombo(parent, style){
			@Override
			protected void setTableData(Table table) {
				refreshTableItems(table);
			}
			
			@Override
			protected int getTopItem(Table table) {
				return computeTopItem(table);
			}
			
			@Override
			protected String getLongestText() {
				return computeLongestName();
			}
		};
		// tell the TableCombo that I want 2 blank columns auto sized.
		dropDownHandle.defineColumns(2);
		// set which column will be used for the selected item.
		dropDownHandle.setDisplayColumnIndex(0);
		dropDownHandle.setShowTableHeader(false);
		listeners = new ArrayList<ComboItemAction>();
		
	
		dropDownHandle.addModifyListener(new ValueChangedListener() {
			
			@Override
			public void valueChanged(ValueChangedEvent e) {
				if (e.isTyped()) {
					selectedItem = new SelectedComboItem(e.getText(), true, e.getText(), e.getText());
				} else {
					ComboItem selectedItemData = (ComboItem)e.getSelectedItem().getData(ITEM_DATA);
					selectedItem = new SelectedComboItem(e.getText(), false, selectedItemData.getItem(), selectedItemData.getValue());
				}
				for (ComboItemAction listener : listeners) {
					listener.exec();
				}
			}
		});
	}
	
	private void refreshTableItems(Table table){
		table.removeAll();
		for(ComboItem item : elementList){
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setData(ITEM_DATA, item);
			if (item instanceof ComboItemSeparator){
				tableItem.setText("______________");		
			} else {
				tableItem.setText(0, item.getText());
				tableItem.setImage(1, item.getImage());
			}

		}
	}
	
	private String computeLongestName() {
		if (longestName == null) {
			longestName = "";
			for(ComboItem item : elementList){
				if (!(item instanceof ComboItemSeparator)){
					String currentText = item.getText();
					if (longestName.length() < currentText.length()) {
						longestName = currentText;
					}
				} 
			}
		}
		return longestName;
	}
	
	/**
	 * Return the index of the {@link TableItem} with the same text of the text area. if there is no element 
	 * selected that completely match the text it return the one that start with the same text. If no element
	 * start with the text of the text area then it return -1
	 */
	private int computeTopItem(Table table){
		int firstFullMatch = -1;
		int firstPartialMatch = -1;
		int index = 0;
		String text = dropDownHandle.getText().trim().toLowerCase();
		for(TableItem item : table.getItems()){
			String itemText = item.getText().trim().toLowerCase();
			if (firstFullMatch == -1 && itemText.equals(text)){
				firstFullMatch = index; 
			}  
			if (firstPartialMatch == -1 && itemText.startsWith(text)){
				firstPartialMatch = index;
			}
			if (firstFullMatch != -1 && firstPartialMatch != -1) break;
			index ++;
		}
		if (firstFullMatch != -1) return firstFullMatch;
		else return firstPartialMatch;
	}
	
	/**
	 * Disable the emphasis effect on the last selected item
	 * 
	 * @param value true if the emphasis on the last selected element should be disabled, otherwise false. By
	 * default it is false
	 */
	public void disableSelectedEmphasis(boolean value){
		disableSelectedItemEmphasis = value;
	}
	
	/**
	 * Add a new action to execute when an element form the popup menu is selected
	 * 
	 * @param listener
	 *          the action to execute when the element is selected
	 */
	public void addSelectionListener(ComboItemAction listener) {
		listeners.add(listener);
	}

	/**
	 * Set the tooltip text of the combo box
	 * 
	 * @param text
	 *          the text
	 */
	public void setToolTipText(String text) {
		dropDownHandle.setToolTipText(text);
	}

	/**
	 * Used to check if the popup menu has element inside it
	 * 
	 * @return true if there are entry, otherwise false
	 */
	protected boolean hasNoElement() {
		return elementList.isEmpty();
	}

	/**
	 * Return the number of entry in the popup menu
	 * 
	 * @return number of entry
	 */
	protected int getItemCount() {
		return elementList.size();
	}

	public int getWidth() {
		return dropDownHandle.getSize().x;
	}

	/**
	 * Return the index of the selected item in the combo
	 * 
	 * @return
	 */
	public int getSelectionIndex() {
		if (hasNoElement())
			return 0;
		return indexForElement(selectedItem);
	}

	/**
	 * Return the value of the selected item
	 * 
	 * @return the value type depends on how the ComboItem that define this voice is defined
	 */
	public Object getSelectionValue() {
		if (hasNoElement())
			return null;
		return getSelectedItem().getValue();
	}

	/**
	 * Return the item at a specific position
	 * 
	 * @param position
	 * @return
	 */
	public ComboItem getItemAtPosition(int position) {
		return elementList.get(position);
	}

	/**
	 * Return the last item selected
	 * 
	 * @return
	 */
	public ComboItem getSelectedItem() {
		return selectedItem;
	}

	/**
	 * Return the index in the list of a specific item
	 * 
	 * @param element
	 *          the item
	 * @return the index of the item
	 */
	protected int indexForElement(ComboItem element) {
		return elementList.indexOf(element);
	}

	/**
	 * Close the popupmenu when the input change
	 * 
	 * @param input
	 * @param oldInput
	 */
	protected void inputChanged(Object input, Object oldInput) {
		//dropDownHandle.setshow
	}

	/**
	 * Return the control of the combobox
	 * 
	 * @return A reference to the combobox control
	 */
	public Composite getControl() {
		return dropDownHandle;
	}

	/**
	 * Set the elements inside the menu manager
	 * 
	 * @param newItems
	 *          a list of element
	 */
	public void setItems(List<ComboItem> newItems) {
		elementList = newItems;
		if (dropDownHandle.getTable() != null) refreshTableItems(dropDownHandle.getTable());
	}

	/**
	 * Set the contextual help for this control. The help will be set in the button and also in the contextual menu
	 * 
	 * @param href
	 *          uri to open when the help is requested
	 */
	public void setHelp(String href) {
		HelpSystem.setHelp(dropDownHandle, href);
		HelpProvider provider = new HelpProvider(dropDownHandle.getMenu());
		provider.setHelp(href);
	}



	/**
	 * Set the menu in the right location, under the combobox
	 * 
	 * @param menu
	 */
	protected void locatePopupMenu(Menu menu) {
		Rectangle r;
		if (getControl() instanceof Composite) {
			r = ((Composite) getControl()).getClientArea();
		} else {
			r = getControl().getBounds();
			r.x = r.y = 0;
		}
		Point loc = getControl().toDisplay(r.x, r.y);
		loc.y += r.height;
		menu.setLocation(loc);
	}

	/**
	 * Set the actual item selected in the menu (the selected item has a bold like text)
	 * 
	 * @param menuManager
	 */
	protected void setSelectionToMenu(Menu menu) {
		if (!disableSelectedItemEmphasis){
			int index = getSelectionIndex();
			if (menu != null && !menu.isDisposed()) {
				if (index < 0 || index >= menu.getItemCount()) {
					menu.setDefaultItem(null);
				} else {
					menu.setDefaultItem(menu.getItem(index));
				}
			}
		}
	}

	/**
	 * Set as selected the element with a specific index
	 * 
	 * @param index
	 *          the index of the element to select
	 */
	public void select(int index) {
		if (index >= 0 && index < elementList.size()) {
			ComboItem itemEntry = elementList.get(index);
			selectedItem = new SelectedComboItem(itemEntry.getText(), false, itemEntry.getItem(), itemEntry.getValue());
			dropDownHandle.setText(selectedItem.getText());
			//dropDownHandle.setImage(selectedItem.getImage());
		}
	}
	
	/**
	 * Show in the button the image and the text of a specific item
	 * 
	 * @param item item from where the image and the text to show in the button are taken.
	 * Can not be null and it can be an element external to the items list.
	 */
	public void select(ComboItem item) {
		dropDownHandle.setText(item.getText());
		//dropDownHandle.setImage(item.getImage());
	}

	/**
	 * Set only the text of the selected item
	 * 
	 * @param text
	 */
	public void setText(String text) {
		dropDownHandle.setText(text);
	}

	/**
	 * Dispose the element
	 * 
	 * @param event
	 */
	protected void handleDispose(DisposeEvent event) {
		dropDownHandle.dispose();
	}

	/**
	 * Set the combo enabled or disabled
	 * 
	 * @param enabled
	 *          true if enabled, otherwise false
	 */
	public void setEnabled(boolean enabled) {
		dropDownHandle.setEnabled(enabled);
	}

	/**
	 * Check if the combobox is enabled
	 * 
	 * @return true if it is enabled, otherwise false
	 */
	public boolean isEnabled() {
		return dropDownHandle.isEnabled();
	}

	@Override
	public Menu getMenu() {
		return dropDownHandle.getMenu();
	}
	
	public void setData(Object data){
		dropDownHandle.setData(data);
	}
	
	public void setData(String key, Object data){
		dropDownHandle.setData(key, data);
	}
	
	public boolean isDisposed(){
		return dropDownHandle.isDisposed();
	}
	
	public String getText(){
		return dropDownHandle.getText();
	}
	
	public void setForeground(Color color){
		dropDownHandle.setForeground(color);
	}
	
	public void setBackground(Color color){
		dropDownHandle.setBackground(color);
	}
	
	public void setInherithed(boolean isInherited){
		dropDownHandle.setInherithed(isInherited);
	}
}
