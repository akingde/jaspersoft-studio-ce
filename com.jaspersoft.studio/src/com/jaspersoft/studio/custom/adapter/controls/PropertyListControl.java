/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter.controls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

/**
 * Control to edit the values of a list, it show a not editable text area
 * with the content of a list and a button to open a dialog to edit, add o remove
 * values from the list. Support the modify lister to notify when the values inside the
 * list changes and the method setSelection and getSelection to set or get the values 
 * inside the list
 * 
 * @author Orlandin Marco
 *
 */
public class PropertyListControl extends Composite {

	/**
	 * Type of the elements inside the list, the valid types are int, float, string, boolean
	 */
	private String elementsType;
	
	/**
	 * A preview of the content of the list
	 */
	private Text listContent;
	
	/**
	 * The values inside the list
	 */
	private List<Object> values = new ArrayList<Object>();
	
	/**
	 * The list of the modify listener added to the control
	 */
	private List<ModifyListener> modifyListeners = new ArrayList<ModifyListener>();
	
	/**
	 * Create the control
	 * 
	 * @param parent parent of the control
	 * @param elementsType Type of the elements inside the list, the valid types are int, float, string, boolean
	 */
	public PropertyListControl(Composite parent, String elementsType) {
		super(parent, SWT.NONE);
		this.elementsType = elementsType;
		this.setLayout(new GridLayout(2,false));
		listContent = new Text(this, SWT.BORDER);
		listContent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		listContent.setEditable(false);
		Button browse = new Button(this, SWT.PUSH);
		browse.setText("...");
		browse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CollectionInputDialog dialog = new CollectionInputDialog(getShell(), values, PropertyListControl.this.elementsType);
				if (dialog.open() == Dialog.OK){
					values = dialog.getValues();
					callSelectionListeners();
				}
			}
		});
	}
	
	/**
	 * Set the element inside the list
	 * 
	 * @param values not null list of elements inside the list, the should be 
	 * converted to string
	 */
	public void setSelection(List<Object> values){
		this.values = values;
		String collection = "[";
		int size = values.size();
		int index = 0;
		for(Object value : values){
			collection += value.toString();
			index ++;
			if (index < size) collection += " , ";
		}
		collection += "]";
		listContent.setText(collection);
	}
	
	/**
	 * Return the values inside the list as array of string
	 * 
	 * @return a not null array of string
	 */
	public String[] getSelection(){
		String[] result = new String[values.size()];
		int index = 0;
		for(Object value : values){
			result[index] = value.toString();
			index++;
		}
		return result;
	}
	
	/**
	 * Add a modify listener that is called when a value of the list
	 * is changed
	 * 
	 * @param listener a not null modify listener
	 */
	public void addModifyListener(ModifyListener listener){
		modifyListeners.add(listener);
	}
	
	/**
	 * Call all the listener added 
	 */
	private void callSelectionListeners(){
		Event event = new Event();
		event.doit = true;
		event.widget = this;
		event.data = values;
		for(ModifyListener listener : modifyListeners){
			listener.modifyText(new ModifyEvent(event));
		}
	}
}
