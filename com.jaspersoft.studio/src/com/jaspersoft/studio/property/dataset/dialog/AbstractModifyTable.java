/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;

/**
 * Superclass of some widgets based on the tables on the dataset dialog.
 * Simply implements some common methods to handle the modify event
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractModifyTable {

	/**
	 * The handled table
	 */
	protected Table wtable;
	
	/**
	 * The list of modify listener
	 */
	private List<ModifyListener> modifyListeners = new ArrayList<ModifyListener>();

	/**
	 * Add a new modify listener
	 * 
	 * @param listener the listener, must be not null & not added before
	 */
	public void addModifyListener(ModifyListener listener){
		if (listener != null && !modifyListeners.contains(listener)) modifyListeners.add(listener);
	}
	
	/**
	 * Fire a modify event for all the registered listeners
	 */
	protected void fireModifyListeners(){
		Event event = new Event(); 
		event.widget = wtable;
		event.data = this;
		ModifyEvent mEvent = null; 
		if (wtable != null){
			mEvent = new ModifyEvent(event);
		}
		for(ModifyListener listener : modifyListeners){
			listener.modifyText(mEvent);
		}
	}
}
