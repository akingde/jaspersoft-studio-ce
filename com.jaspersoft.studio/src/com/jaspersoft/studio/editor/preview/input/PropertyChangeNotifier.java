/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Shared property change notifier to listen when a parameter change value when inserting in the
 * preview parameters tab. When it changes value it will notify it to all the other parameters
 * currently used.
 * 
 */
public class PropertyChangeNotifier {
	
	/**
	 * List of all the parameter registered
	 */
	private List<IDataInput> registeredInput = new ArrayList<IDataInput>();
	
	/**
	 * Flag used to avoid multiple refresh
	 */
	private boolean refreshing = false;
	
	/**
	 * The listener add to every registered item to know when its value changes
	 */
	private PropertyChangeListener inputListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (!refreshing){
				refreshing = true;
				//notify the change to every other registered item except the one who triggred the event
				for(IDataInput input : registeredInput){
					if (!evt.getPropertyName().equals(input.getParameter().getName())){
						input.parameterChanged(evt);
					}
				}
				refreshing = false;
			}
		}
	};
	
	/**
	 * Add an {@link IDataInput} to the list of handled elements, must be not null
	 */
	public void addDataInput(IDataInput input){
		input.addChangeListener(inputListener);
		registeredInput.add(input);
	}
	
	/**
	 * Remove a previously added {@link IDataInput}
	 */
	public void removeDataInput(IDataInput input){
		input.removeChangeListener(inputListener);
		registeredInput.add(input);
	}
	
}
