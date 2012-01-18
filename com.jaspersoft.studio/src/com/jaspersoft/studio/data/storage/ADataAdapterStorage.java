/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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
	private static PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(
			JaspersoftStudioPlugin.getInstance());

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

	public DataAdapterDescriptor findDataAdapter(String url) {
		for (DataAdapterDescriptor dataAdapter : daDescriptors.values()) {
			if (dataAdapter.getName().equals(url))
				return dataAdapter;
		}
		return null;
	}
}
