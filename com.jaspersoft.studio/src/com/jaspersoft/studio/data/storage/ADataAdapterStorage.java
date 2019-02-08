/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.design.JRDesignDataset;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;

public abstract class ADataAdapterStorage {

	public static final String PROP_DATAADAPTERS = "DATAADAPTERS";

	private PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(JaspersoftStudioPlugin.getInstance());

	public abstract String getStorageName();

	protected Map<String, DataAdapterDescriptor> daDescriptors;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.removePropertyChangeListener(listener);
	}

	public Collection<DataAdapterDescriptor> getDataAdapterDescriptors(JRDesignDataset dataset) {
		if (daDescriptors == null) {
			daDescriptors = new LinkedHashMap<>();
			findAll();
		}
		return daDescriptors.values();
	}

	public Collection<DataAdapterDescriptor> getDataAdapterDescriptors() {
		return getDataAdapterDescriptors(null);
	}

	protected String generateDataAdapterName(DataAdapterDescriptor adapter) {
		String adapterName = adapter.getName();
		int counter = 1;
		while (daDescriptors.containsKey(adapterName)) {
			adapterName = adapter.getName() + "_" + counter;
			counter++;
		}
		return adapterName;
	}

	protected void forceAddDataAdapter(DataAdapterDescriptor adapter) {
		String adapterName = generateDataAdapterName(adapter);
		daDescriptors.put(adapterName, adapter);
		propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, null, adapter);
	}

	public boolean addDataAdapter(DataAdapterDescriptor adapter) {
		if (!daDescriptors.containsKey(adapter.getName())) {
			daDescriptors.put(adapter.getName(), adapter);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, null, adapter);
			return true;
		}
		return false;
	}

	public boolean removeDataAdapter(DataAdapterDescriptor da) {
		if (da == null)
			return false;
		if (daDescriptors.containsKey(da.getName())) {
			daDescriptors.remove(da.getName());
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, da, null);
			return true;
		}
		// FIXME: This code is only to recover broken workspace in the future can be
		// removed
		// Maybe it was a duplicated data adapter, search it manually
		String key = getUrl(da);
		if (key != null) {
			daDescriptors.remove(key);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, da, null);
			return true;
		}
		return false;
	}

	public boolean editDataAdapter(String oldName, DataAdapterDescriptor adapter) {
		if (daDescriptors.containsKey(oldName)
				&& (adapter.getName().equals(oldName) || !daDescriptors.containsKey(adapter.getName()))) {
			daDescriptors.remove(oldName);
			daDescriptors.put(adapter.getName(), adapter);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, null, adapter);
			return true;
		}
		// FIXME: This code is only to recover broken workspace in the future can be
		// removed
		// Maybe it was a duplicated data adapter, search it manually
		String key = getUrl(adapter);
		if (key != null) {
			// Since for the duplicated data adapter the key is not binded to the real name
			// then we can overwrite it directly
			daDescriptors.put(key, adapter);
			propChangeSupport.firePropertyChange(PROP_DATAADAPTERS, null, adapter);
			return true;
		}
		return false;
	}

	public String getUrl(DataAdapterDescriptor da) {
		for (Entry<String, DataAdapterDescriptor> desc : daDescriptors.entrySet()) {
			if (desc.getValue() == da)
				return desc.getKey();
		}
		return null;
	}

	/**
	 * Check the validity of the data adapter name. It is valid only if it is not
	 * null, not empty and not already existed.
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

	public String getLabel(DataAdapterDescriptor d) {
		String label = d.getTitle();
		DataAdapterFactory factory = DataAdapterManager
				.findFactoryByDataAdapterClass(d.getDataAdapter().getClass().getCanonicalName());
		if (this instanceof FileDataAdapterStorage)
			label += " - [" + getUrl(d) + "]";
		else if (factory != null)
			label += " - " + factory.getLabel();
		return label;
	}

	/**
	 * Return the map of all the current data adapter with their key
	 * 
	 * @return a not null map of data adapters
	 */
	public Map<String, DataAdapterDescriptor> getDescriptors() {
		if (daDescriptors == null) {
			getDataAdapterDescriptors();
		}
		return new HashMap<>(daDescriptors);
	}

	public abstract void findAll();
}
