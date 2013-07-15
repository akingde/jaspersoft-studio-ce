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
package com.jaspersoft.studio.properties.internal;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IFormColors;

import com.jaspersoft.studio.properties.view.ISection;
import com.jaspersoft.studio.properties.view.TabContents;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetWidgetFactory;


/**
 * Display a search bar into the top of a property sheet page. This bar can be used 
 * to search and highlight a specific property
 * 
 * @author Orlandin Marco
 *
 */
public class TabbedPropertySearch extends Composite {
	
	/**
	 * Combo where the name of the property can be typed or selected from the available
	 */
	private Combo textArea;

	/**
	 * Name of the property actually selected in the combo
	 */
	private String comboSelectedText="";
	
	/**
	 * The factory of the property sheet
	 */
	private TabbedPropertySheetWidgetFactory factory;
	
	/**
	 * The last element selected by the user
	 */
	private Object lastSelectedElement = null;
	
	/**
	 * Keep the properties list of the last selected element, until the selection dosen't change with an element
	 * with a different type. Maybe could be improved by storing all the created properties when they are builded
	 */
	private ComboDataContainer cachedProperties = null;
	
	/**
	 * 
	 * Class that contains all the properties selectable 
	 * for a specific type of item
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ComboDataContainer{
		
		/**
		 * List of the properties name
		 */
		String[] labels;
		
		/**
		 * List of the property ids associated 1:1 with the labels, must have the same size of labels
		 */
		Object[] ids;
		
		/**
		 * Create an instance of the class
		 * 
		 * @param labels list of the properties name available
		 * @param ids list of ids associated with the properties name
		 */
		public ComboDataContainer(String[] labels, Object[] ids){
			this.labels = labels;
			this.ids = ids;
		}
		
		/**
		 * Return the number of properties
		 * 
		 * @return number of properties
		 */
		public int getSize(){
			//Its assumed that ids and labels have the same size
			return ids.length;
		}
	}
	
	/**
	 * Set the text inside the combo without raise any listener. It also place 
	 * the cursor to a similar position respect where it was with the previous text
	 * 
	 * @param text text to set inside the combo
	 */
	private void setTextWithoutListener(String text){
		Point oldSelection = textArea.getSelection();
		textArea.setText(text);
		if (text.length()>=oldSelection.y) textArea.setSelection(oldSelection);
		else textArea.setSelection(new Point(text.length(),text.length()));
	}
	
	/**
	 * When the text inside the combobox change this method is called to update the list of 
	 * element shown inside the combo list, in this way only the properties that contains the 
	 * typed text are shown
	 * 
	 * @param e event that references the combo
	 */
	private void updateComboString(EventObject e){
		comboSelectedText = ((Combo)e.getSource()).getText();
		ComboDataContainer comboValue = getFilteredProperites(comboSelectedText.toLowerCase().trim());
		String[] elements = comboValue.labels;
		//the list of elements is update only if the new subset has a different size from the old one
		if (textArea.getItemCount() != elements.length) {
			textArea.setItems(elements);
			textArea.setVisibleItemCount(textArea.getItemCount());
			textArea.setData(comboValue.ids);
			//The set items delete the text inside the combo so it is necessary to insert it programmatically
			setTextWithoutListener(comboSelectedText);
		}
	}

	/**
	 * Constructor for TabbedPropertySearch.
	 * 
	 * @param parent the parent composite.
	 * @param factory the widget factory for the tabbed property sheet
	 */
	public TabbedPropertySearch(Composite parent,
			TabbedPropertySheetWidgetFactory factory) {
		super(parent, SWT.NO_FOCUS);
		this.factory = factory;

		this.addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent e) {
				drawTitleBackground(e);
			}
		});

		factory.getColors().initializeSectionToolBarColors();
		setBackground(factory.getColors().getBackground());
		setForeground(factory.getColors().getForeground());

		FormLayout layout = new FormLayout();
		layout.marginWidth = 1;
		layout.marginHeight = 0;
		setLayout(layout);

		textArea = new Combo(this, SWT.NONE);
		textArea.setForeground(factory.getColors().getColor(IFormColors.TITLE));
		
		//Selection listener, to show the selected properties
		textArea.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboSelectedText = ((Combo)e.getSource()).getText();
				checkSelection();
			}
		});
		
		//Focus listener, to populate the combo when it is selected
		textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				updateComboString(e);
			}
		});
		
		//Key listener, to populate the combo when the text inside it change
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					checkSelection();
					return;
				}
				updateComboString(e);
				if (textArea.getItemCount()>0 && !textArea.getListVisible()){
					//if (!(textArea.getItemCount() == 1 && textArea.getItem(0).equals(comboSelectedText)))
					{
						textArea.setListVisible(true);
						setTextWithoutListener(comboSelectedText);
					}
				}
			}
		});
		
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		textArea.setLayoutData(data);
	}
	
	/**
	 * Method called when there is a selection event. If the text inside the combo
	 * match one of its elements then the property selected will be highlighted
	 */
	private void checkSelection(){
		int selectionIndex = getIndex(comboSelectedText.toLowerCase());
		if (selectionIndex != -1){
			//Get the id of the selected properties
			Object id = ((Object[])textArea.getData())[selectionIndex];
			selectElement(id);
			//In the dropwon list there will be only the selected properties
			String[] names = {comboSelectedText};
			Object[] values = {id};
			textArea.setItems(names);
			textArea.setData(values);
			//need re-set the text since the setitems erase it
			textArea.setText(comboSelectedText);
			textArea.setSelection(new Point(comboSelectedText.length(),comboSelectedText.length()));
		}
	}
	
	/**
	 * Get a string and return the index of the element in the combo that has the same value
	 * of the string 
	 * 
	 * @param searchedString element to search
	 * @return index of the searched element in the combo or -1 if it is not found
	 */
	private int getIndex(String searchedString){
		for(int i=0;i<textArea.getItems().length;i++){
			if (textArea.getItems()[i].toLowerCase().equals(searchedString)) return i;
		}
		return -1;
	}
	
	/**
	 * Select the properties in the property sheet page with a specific id
	 * 
	 * @param id the id of the property
	 */
	private void selectElement(Object id){
		List<TabContents> lst = factory.getAvailableTabContents();
		for(TabContents actualContents : lst){
			for(ISection section : actualContents.getSections()){
				if (section instanceof IWidgetsProviderSection){
					IWidgetsProviderSection actualSection = ((IWidgetsProviderSection)section);
					//search the section that contains the property
					if (actualSection.getHandledProperties().contains(id)){
						//Select the section, it will also create it
						factory.setSelection(actualContents);
						//Expand the properties expandable composite, if it is inside one of it
						actualSection.expandForProperty(id);
						//Get the widget from the section and highlight it for 2000ms
						IHighlightPropertyWidget widget = actualSection.getWidgetForProperty(id);
						widget.highLightWidget(2000);
					}
				}
			}
		}
	}
	
	/**
	 * Get the element actually selected, i need to search in all section until i not found the one
	 * where the element is not null because the element is set only in the already created sectuions, 
	 * so at first i don't know which section already store the element
	 * 
	 * @return
	 */
	private Object getSelectedElement(){
		List<TabContents> lst = factory.getAvailableTabContents();
		for(TabContents actualContents : lst){
			for(ISection section : actualContents.getSections()){
				if (section instanceof IWidgetsProviderSection){
					Object selectedElement = ((IWidgetsProviderSection)section).getSelectedElement();
					if (selectedElement != null) return selectedElement;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get a subset of the properties for the selected element. The subset will have only
	 * the properties that contains the searchedString
	 * 
	 * @param searchKey the searched String
	 * @return a not null subset of properties
	 */
	private ComboDataContainer getFilteredProperites(String searchKey){
		//Check if i have the properties for the element in the cache
		if (lastSelectedElement == null || cachedProperties == null){
			//I haven't build a cache yet, need to create it
			cachedProperties = createElements();
			lastSelectedElement = getSelectedElement();
		} else {
			//I have build a cache
			Object actualSelectedElement = getSelectedElement();
			if (actualSelectedElement != null && !actualSelectedElement.getClass().equals(lastSelectedElement.getClass())){
				//The cache was build for an element with different type\properties, i need to rebuild it
				cachedProperties = createElements();
				lastSelectedElement = getSelectedElement();
			}
		}
		//Select from all the properties only the one that contains the searched string
		List<String> result = new ArrayList<String>();
		List<Object> ids = new ArrayList<Object>();
		for(int i=0; i<cachedProperties.getSize();i++){
			String name = cachedProperties.labels[i];
			if (name.toLowerCase().contains(searchKey)){
				ids.add(cachedProperties.ids[i]);
				result.add(name);
			}
		}
		return new ComboDataContainer(result.toArray(new String[result.size()]),
					ids.toArray(new Object[ids.size()]));
	}
	
	/**
	 * Create a combo container containing all the selectable properties for 
	 * the actually selected element
	 * 
	 */
	private ComboDataContainer createElements(){
		List<String> result = new ArrayList<String>();
		List<Object> ids = new ArrayList<Object>();
		List<TabContents> lst = factory.getAvailableTabContents();
		for(TabContents actualContents : lst){
			for(ISection section : actualContents.getSections()){
				if (section instanceof IWidgetsProviderSection){
					IWidgetsProviderSection attributesSection = (IWidgetsProviderSection)section;
					List<Object> providedProperties = attributesSection.getHandledProperties();
					for(Object property : providedProperties){
						WidgetDescriptor desc = attributesSection.getPropertyInfo(property);
						ids.add(property);
						result.add(desc.getName());
					}
				}
			}
		}
		return new ComboDataContainer(result.toArray(new String[result.size()]),
				  						ids.toArray(new Object[ids.size()]));
	}
	
	/*
	private ComboDataContainer createVisibleElements(String searchKey){
		List<String> result = new ArrayList<String>();
		List<Object> ids = new ArrayList<Object>();
		List<TabContents> lst = factory.getAvailableTabContents();
		for(TabContents actualContents : lst){
			for(ISection section : actualContents.getSections()){
				if (section instanceof IWidgetsProviderSection){
					IWidgetsProviderSection attributesSection = (IWidgetsProviderSection)section;
					List<Object> providedProperties = attributesSection.getHandledProperties();
					for(Object property : providedProperties){
						String name = attributesSection.getPropertyName(property);
						if (name.toLowerCase().contains(searchKey)){
							ids.add(property);
							result.add(name);
						}
					}
				}
			}
		}
		return new ComboDataContainer(result.toArray(new String[result.size()]),
				  						ids.toArray(new Object[ids.size()]));
	}
	
	private void checkWidgets(Map widgets, String key, List<String> result){
		for(Object id : widgets.keySet()){
			IPropertyWidget widget = (IPropertyWidget)widgets.get(id);
			if (widget.getId().contains(key) || widget.getName().toLowerCase().contains(key)) result.add(widget.getName());
		}
	}*/

	/**
	 * @param e
	 */
	protected void drawTitleBackground(PaintEvent e) {
		Rectangle bounds = getClientArea();
		Color bg = factory.getColors().getColor(IFormColors.H_GRADIENT_END);
		Color gbg = factory.getColors().getColor(IFormColors.H_GRADIENT_START);
		GC gc = e.gc;
		gc.setForeground(bg);
		gc.setBackground(gbg);
		gc.fillGradientRectangle(bounds.x, bounds.y, bounds.width,
				bounds.height, true);
		// background bottom separator
		gc.setForeground(factory.getColors().getColor(
				IFormColors.H_BOTTOM_KEYLINE1));
		gc.drawLine(bounds.x, bounds.height - 2, bounds.x + bounds.width - 1,
				bounds.height - 2);
		gc.setForeground(factory.getColors().getColor(
				IFormColors.H_BOTTOM_KEYLINE2));
		gc.drawLine(bounds.x, bounds.height - 1, bounds.x + bounds.width - 1,
				bounds.height - 1);
	}

}
