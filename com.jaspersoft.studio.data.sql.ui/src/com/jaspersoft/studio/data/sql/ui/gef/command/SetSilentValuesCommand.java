/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.AMapElement;

public class SetSilentValuesCommand extends Command {
	private Map<AMapElement, Map<String, Object>> newVals;
	private Map<AMapElement, Map<String, Object>> oldVals;
	private boolean fireEvent = false;

	public SetSilentValuesCommand(boolean fireEvent) {
		this.fireEvent = fireEvent;
	}

	public void add(AMapElement m, String id, Object val) {
		if (newVals == null)
			newVals = new HashMap<>();
		Map<String, Object> vals = newVals.get(m);
		if (vals == null) {
			vals = new HashMap<>();
			newVals.put(m, vals);
		}
		vals.put(id, val);
	}

	public boolean isEmpty() {
		return newVals == null || newVals.isEmpty();
	}

	@Override
	public void execute() {
		if (newVals == null)
			return;
		if (oldVals == null)
			oldVals = new HashMap<>();
		AMapElement last = null;
		for (AMapElement m : newVals.keySet()) {
			Map<String, Object> newvalues = newVals.get(m);
			Map<String, Object> oldvalues = oldVals.get(m);
			if (oldvalues == null) {
				oldvalues = new HashMap<>();
				oldVals.put(m, oldvalues);
			}
			for (String key : newvalues.keySet()) {
				oldvalues.put(key, m.getPropertyValue(key));
				m.setNoEvents(true);
				m.setPropertyValue(key, newvalues.get(key));
				m.setNoEvents(false);
			}
			last = m;
		}
		if (fireEvent && last != null)
			last.firePropertyChange("", true, false);
	}

	@Override
	public void undo() {
		AMapElement last = null;
		for (AMapElement m : oldVals.keySet()) {
			Map<String, Object> newvalues = newVals.get(m);
			Map<String, Object> oldvalues = oldVals.get(m);
			for (String key : oldvalues.keySet()) {
				m.setNoEvents(true);
				m.setPropertyValue(key, newvalues.get(key));
				m.setNoEvents(false);
			}
			last = m;
		}
		if (fireEvent && last != null)
			last.firePropertyChange("", true, false);
	}
}
