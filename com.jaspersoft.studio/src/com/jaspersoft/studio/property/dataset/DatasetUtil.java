/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.JRDefaultDataAdapterStorage;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.property.metadata.PropertyMetadataRegistry;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.properties.PropertiesMetadataUtil;
import net.sf.jasperreports.properties.PropertyMetadata;

public class DatasetUtil {
	private static DataAdapter da;

	public static DataAdapter refreshDataAdapter(JasperDesign jDesign, JRDesignDataset dataset,
			JasperReportsConfiguration jConfig) {
		String d = dataset.getPropertiesMap().getProperty(DataQueryAdapters.DEFAULT_DATAADAPTER);
		if (d == null && dataset.isMainDataset())
			// if none available get the default for the main report
			d = jDesign.getProperty(DataQueryAdapters.DEFAULT_DATAADAPTER);
		ADataAdapterStorage[] dastorages = DataAdapterManager.getDataAdapter((IFile) jConfig.get(FileUtils.KEY_FILE),
				jConfig);
		if (d != null && dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				for (DataAdapterDescriptor dad : das.getDataAdapterDescriptors())
					if (dad.getName().equals(d)) {
						da = dad.getDataAdapter();
						refreshPropertiesMap(jConfig);
						return da;
					}
		}
		// Else check if there is the default data adapter available
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(jConfig);
		JRDesignDataset currentDataset = dataset;
		if (currentDataset != null) {
			DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
			if (defaultDA != null) {
				da = defaultDA.getDataAdapter();
				refreshPropertiesMap(jConfig);
				return da;
			}
		}
		da = null;
		refreshPropertiesMap(jConfig);
		return null;
	}

	private static Map<JasperReportsConfiguration, Map<String, PropertyMetadata>> pmap = new HashMap<JasperReportsConfiguration, Map<String, PropertyMetadata>>();

	public static Map<String, PropertyMetadata> getPmap(JasperReportsConfiguration jConfig) {
		return pmap.get(jConfig);
	}

	public static void refreshPropertiesMap(JasperReportsConfiguration jConfig) {
		Map<String, PropertyMetadata> m = getPmap(jConfig);
		if (m == null) {
			m = new HashMap<String, PropertyMetadata>();
			pmap.put(jConfig, m);
		} else
			m.clear();
		if (da != null) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(jConfig);
			List<PropertyMetadata> props;
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());
				props = pmu.getParameterProperties(da);
				for (PropertyMetadata pm : props)
					m.put(pm.getName(), pm);
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		}
		m.putAll(PropertyMetadataRegistry.getPropertiesMetadata());
	}

	public static void removeProperty(JRDesignDataset dataset, String p) {
		for (JRParameter prm : dataset.getParameters())
			if (!prm.isSystemDefined())
				prm.getPropertiesMap().removeProperty(p);
	}

	public static DataAdapter getDataAdapter(JasperReportsConfiguration jConfig, JRDesignDataset ds) {
		DataAdapter da = null;
		String dapath = ds.getPropertiesMap()
				.getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		if (!Misc.isNullOrEmpty(dapath)) {
			ADataAdapterStorage[] das = DataAdapterManager.getDataAdapter((IFile) jConfig.get(FileUtils.KEY_FILE), jConfig);
			for (ADataAdapterStorage s : das) {
				for (DataAdapterDescriptor dad : s.getDataAdapterDescriptors()) {
					if (s.getUrl(dad).equals(dapath)) {
						da = dad.getDataAdapter();
						break;
					}
				}
				if (da != null)
					break;
			}
		}
		if (da == null) {
			String name = ds.getPropertiesMap().getProperty(DataQueryAdapters.DEFAULT_DATAADAPTER);
			if (!Misc.isNullOrEmpty(name)) {
				ADataAdapterStorage storage = DataAdapterManager.getJRDefaultStorage(jConfig);
				for (DataAdapterDescriptor dad : storage.getDataAdapterDescriptors()) {
					if (dad.getDataAdapter().getName().equals(name)) {
						da = dad.getDataAdapter();
						break;
					}
				}
			}
		}
		return da;
	}
}
