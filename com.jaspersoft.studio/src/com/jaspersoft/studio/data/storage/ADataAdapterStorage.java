package com.jaspersoft.studio.data.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

public abstract class ADataAdapterStorage {
	private static final String PROP_DATAADAPTERS = "DATAADAPTERS";
	private static PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(
			JaspersoftStudioPlugin.getInstance());

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.removePropertyChangeListener(listener);
	}

	private List<DataAdapterDescriptor> daDescriptors = new ArrayList<DataAdapterDescriptor>();

	public List<DataAdapterDescriptor> getDataAdapterDescriptors() {
		return daDescriptors;
	}

	/**
	 * Add a DataAdapter to the list of DataAdapters in JaspersoftStudio.
	 * 
	 * @param DataAdapterService
	 */
	public void addDataAdapter(DataAdapterDescriptor adapter) {
		if (!daDescriptors.contains(adapter)) {
			daDescriptors.add(adapter);
			propChangeSupport.fireIndexedPropertyChange(PROP_DATAADAPTERS, daDescriptors.size() - 1, null, adapter);
		}
	}

	/**
	 * Remove the DataAdapter to the list of DataAdapters in JaspersoftStudio.
	 * 
	 * @param DataAdapterService
	 */
	public void removeDataAdapter(DataAdapterDescriptor adapter) {
		if (daDescriptors.contains(adapter)) {
			int ind = daDescriptors.indexOf(adapter);
			daDescriptors.remove(adapter);
			propChangeSupport.fireIndexedPropertyChange(PROP_DATAADAPTERS, ind, adapter, null);
		}
	}
}
