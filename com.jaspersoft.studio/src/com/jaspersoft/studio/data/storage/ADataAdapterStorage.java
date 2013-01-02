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
package com.jaspersoft.studio.data.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

public abstract class ADataAdapterStorage {
	private static final String PROP_DATAADAPTERS = "DATAADAPTERS";
	private PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(JaspersoftStudioPlugin.getInstance());

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.removePropertyChangeListener(listener);
	}

	protected Map<String, DataAdapterDescriptor> daDescriptors;

	public Collection<DataAdapterDescriptor> getDataAdapterDescriptors() {
		if (daDescriptors == null) {
			daDescriptors = new LinkedHashMap<String, DataAdapterDescriptor>();
			findAll();
		}
		return daDescriptors.values();
	}

	public void addDataAdapter(String url, DataAdapterDescriptor adapter) {
		if (daDescriptors.containsKey(url)) {
			daDescriptors.remove(url);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, daDescriptors.get(url), null);
		}
		daDescriptors.put(url, adapter);
		propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, null, adapter);
	}

	public void removeDataAdapter(DataAdapterDescriptor da) {
		String key = getUrl(da);
		if (key != null) {
			delete(key);
			daDescriptors.remove(key);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, da, null);
		}
	}

	public void removeDataAdapter(String key) {
		if (key != null) {
			delete(key);
			DataAdapterDescriptor da = daDescriptors.get(key);
			daDescriptors.remove(key);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, da, null);
		}
	}

	public String getUrl(DataAdapterDescriptor da) {
		for (String key : daDescriptors.keySet()) {
			if (daDescriptors.get(key) == da)
				return key;
		}
		return null;
	}

	public abstract void findAll();

	public abstract void save(String url, DataAdapterDescriptor adapter);

	public abstract void delete(String url);

	/**
	 * Check the validity of the data adapter name. It is valid only if it is not null, not empty and not already existed.
	 * 
	 * @param dataAdapterName
	 * @return bool
	 */
	public boolean isDataAdapterNameValid(String dataAdapterName) {
		if (dataAdapterName == null || "".equals(dataAdapterName.trim())) //$NON-NLS-1$
			return false;

		for (DataAdapterDescriptor dataAdapter : daDescriptors.values()) {
			if (dataAdapter.getName().equals(dataAdapterName))
				return false;
		}
		return true;
	}

	public DataAdapterDescriptor findDataAdapter(String name) {
		if (daDescriptors != null)
			for (DataAdapterDescriptor dataAdapter : daDescriptors.values()) {
				if (dataAdapter.getName().equals(name))
					return dataAdapter;
			}
		return null;
	}

}
