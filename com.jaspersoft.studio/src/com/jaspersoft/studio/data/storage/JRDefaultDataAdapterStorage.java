/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Data adapter storage to provide the data adapter stored in the property
 * net.sf.jasperreports.data.adapter of a report. It will return nothing if the
 * property is not defined or doesn't point a valid file.
 * 
 * @author Orlandin Marco
 *
 */
public class JRDefaultDataAdapterStorage extends ADataAdapterStorage {

	/**
	 * The jasperreports configuration for which this storage is instantiated
	 */
	private JasperReportsConfiguration jConfig;

	/**
	 * The design of the report for which this storage is instantiated
	 */
	private JasperDesign design;

	/**
	 * The file of the report for which this storage is instantiated
	 */
	private IFile report;

	/**
	 * Map of the resolvers for each location, each location has its own resolver
	 * that handle also caching and reloading
	 */
	private Map<String, DataAdapterLocationResolver> resolvers = new HashMap<>();

	/**
	 * Create the storage for a specific report
	 */
	public JRDefaultDataAdapterStorage(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
		this.design = jConfig.getJasperDesign();
		this.report = (IFile) jConfig.get(FileUtils.KEY_FILE);
		daDescriptors = new HashMap<String, DataAdapterDescriptor>();
	}

	@Override
	public void findAll() {
	}

	@Override
	public String getStorageName() {
		return Messages.JRDefaultDataAdapterStorage_0;
	}

	/**
	 * Get if available the default adapter for the main dataset
	 * 
	 * @return a data adapter descriptor if the property for the default adapter is
	 *         available on the dataset and can be resolved, otherwise null
	 */
	@Override
	public Collection<DataAdapterDescriptor> getDataAdapterDescriptors() {
		if (design != null) {
			return getDataAdapterDescriptors(design.getMainDesignDataset());
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Get if available the default adapter for the specified dataset
	 * 
	 * @param dataset
	 *            the dataset where the property is searched
	 * @return a data adapter descriptor if the property for the default adapter is
	 *         available on the dataset and can be resolved, otherwise null
	 */
	@Override
	public Collection<DataAdapterDescriptor> getDataAdapterDescriptors(JRDesignDataset dataset) {
		daDescriptors.clear();
		String location = dataset.getPropertiesMap()
				.getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		DataAdapterDescriptor currentDefault = getDefaultJRDataAdapter(location);
		if (currentDefault != null) {
			daDescriptors.put(location, currentDefault);
		}
		return daDescriptors.values();
	}

	/**
	 * Get a dataadapter list for a location if it can be resolved
	 * 
	 * @param location
	 *            the location of the adapter, can be an url
	 * @return the data adapter if the location was resolved otherwise null
	 */
	public DataAdapterDescriptor getDefaultJRDataAdapter(String location) {
		if (location != null) {
			DataAdapterLocationResolver resolver = getResolver(location);
			return resolver.getDataAdapter();
		} else {
			return null;
		}
	}

	/**
	 * Get a dataadapter for a location if it can be resolved
	 * 
	 * @param dataset
	 *            the location is read from here, if null is search on the main
	 *            dataset
	 * @return the data adapter if the location was resolved otherwise null
	 */
	public DataAdapterDescriptor getDefaultJRDataAdapter(JRDesignDataset dataset) {
		String location = null;
		if (dataset != null) {
			location = dataset.getPropertiesMap()
					.getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		} else {
			location = design.getMainDataset().getPropertiesMap()
					.getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
		}
		return getDefaultJRDataAdapter(location);
	}

	/**
	 * Get a dataadapter list for a location if it can be resolved
	 * 
	 * @param location
	 *            the location of the adapter, can be an url
	 * @return the data adapter if the location was resolved otherwise null
	 */
	protected DataAdapterLocationResolver getResolver(String location) {
		DataAdapterLocationResolver resolver = resolvers.get(location);
		if (resolver == null) {
			resolver = new DataAdapterLocationResolver(location, jConfig, report);
			resolvers.put(location, resolver);
		}
		return resolver;
	}

	/**
	 * Get the label for the default data adapter
	 * 
	 * @param d
	 *            the data dapter
	 * @return the label for the data adapter
	 */
	public String getLabel(DataAdapterDescriptor d) {
		String label = d.getTitle();
		return label + Messages.JRDefaultDataAdapterStorage_1;
	}

	/**
	 * Return URL for the passed data adapter if it is one adapter provided by this
	 * class, otherwise null
	 */
	@Override
	public String getUrl(DataAdapterDescriptor da) {
		for (DataAdapterLocationResolver resolver : resolvers.values()) {
			if (resolver.getDataAdapter() == da) {
				return resolver.getAdapterURL();
			}
		}
		return null;
	}
}
