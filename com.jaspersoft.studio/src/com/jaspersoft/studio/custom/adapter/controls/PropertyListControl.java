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

public class PropertyListControl extends Composite {

	private String elementsType;
	
	private Text properties;
	
	private List<Object> values;
	
	private List<ModifyListener> modifyListeners = new ArrayList<ModifyListener>();
	
	
	public PropertyListControl(Composite parent, String elementsType) {
		super(parent, SWT.NONE);
		this.elementsType = elementsType;
		this.setLayout(new GridLayout(2,false));
		properties = new Text(this, SWT.BORDER);
		properties.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		properties.setEditable(false);
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
		properties.setText(collection);
	}
	
	public String[] getSelection(){
		String[] result = new String[values.size()];
		int index = 0;
		for(Object value : values){
			result[index] = value.toString();
			index++;
		}
		return result;
	}
	
	public void addModifyListener(ModifyListener listener){
		modifyListeners.add(listener);
	}
	
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
