/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.adapter;

import net.sf.jasperreports.data.DataFileServiceFactory;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.ParameterContributorFactory;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;
import net.sf.jasperreports.extensions.ListExtensionsRegistry;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: DataAdapterParameterContributorExtensionsRegistryFactory.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class DataAdapterParameterContributorExtensionsRegistryFactory implements ExtensionsRegistryFactory {
	private static final ExtensionsRegistry extensionsRegistry;

	static {
		ListExtensionsRegistry registry = new ListExtensionsRegistry();
		registry.add(ParameterContributorFactory.class, DataAdapterParameterContributorFactory.getInstance());
		registry.add(DataFileServiceFactory.class, JSSBuiltinDataFileServiceFactory.instance());
		extensionsRegistry = registry;
	}

	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) {
		return extensionsRegistry;
	}
}
