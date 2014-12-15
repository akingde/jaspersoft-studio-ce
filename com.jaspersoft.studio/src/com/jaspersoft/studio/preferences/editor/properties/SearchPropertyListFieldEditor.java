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
package com.jaspersoft.studio.preferences.editor.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Class used to list the properties in the preferences. It allow
 * to use a filter to shown only a subset of all the properties
 * 
 * @author Orlandin Marco
 *
 */
public class SearchPropertyListFieldEditor extends PropertyListFieldEditor {

	
	/**
	 * The current filter, an empty filter means any property
	 */
	private String searchString = "";
	
	/**
	 * Hint string shown when the filter text area is leaved empty
	 */
	private static final String defaultString = "type filter text";
	
	/**
	 * Content provider for the table. It expect as input a list or a collection
	 * of Pair and return a subset of the collection of the pairs with the key
	 * that match the current filter
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class SearchContentProvider implements IStructuredContentProvider {
	
		@Override
		public void dispose() {
	
		}
	
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	
		}
		
		/**
		 * Get a list of elements that must be Pair and build a new list 
		 * with only the pair with a key that match the filter. Then return
		 * the new list as an array
		 * 
		 * @param allElements the element to check
		 * @return a not null array of the elements that match the filter
		 */
		private Object[] getSubset(Collection<?> allElements){
			List<Object> result = new ArrayList<Object>();
			for(Object obj : allElements){
				Pair pair = (Pair)obj;
				if (searchString.isEmpty() || pair.getKey().contains(searchString)){
					result.add(obj);
				}
			}
			return result.toArray();
		}
	
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement != null && inputElement instanceof List)
				return getSubset((List<?>) inputElement);
			//If it's not a list check if it is a least a collection
			else if (inputElement != null && inputElement instanceof Collection){
				return getSubset((Collection<?>) inputElement);
			}
			return new Object[0];
		}

	}
	

	public SearchPropertyListFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	/**
	 * Other than the label this method create the control
	 * to input a filter
	 */
	@Override
	public Label getLabelControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3,false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label labelControl = super.getLabelControl(container);
		final Text searchText = new Text(container, SWT.BORDER);
		searchText.setForeground(ColorConstants.gray);
		searchText.setText(defaultString);
		//Add the modify listener to update the filter when something valid
		//is typed
		searchText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				String text = searchText.getText().trim();
				String oldText = new String(searchString);
				if (text.equals(defaultString) || text.isEmpty()){
					searchString = "";
				} else {
					searchString = text;
				}
				//Update only if it is necessary
				if (!oldText.equals(searchText)) viewer.refresh();
			}
		});
		
		//Add the listener to show or remove the hint text
		searchText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (searchText.getText().trim().isEmpty()){
					searchText.setText(defaultString);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (searchText.getText().trim().equals(defaultString)){
					searchText.setText("");
				}
			}
		});
		GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).grab(true, false).hint(150, 16).applyTo(searchText);
		return labelControl;
	}

	/**
	 * When the table is created create also the viewer for it
	 */
	@Override
	public Table getTableControl(Composite parent) {
		Table table = super.getTableControl(parent);
		viewer.setContentProvider(new SearchContentProvider());
		return table;
	}
}
