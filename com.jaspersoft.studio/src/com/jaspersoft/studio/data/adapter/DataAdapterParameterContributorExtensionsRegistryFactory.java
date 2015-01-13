/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.adapter;

import net.sf.jasperreports.data.BuiltinDataFileServiceFactory;
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
	
	static
	{
		ListExtensionsRegistry registry = new ListExtensionsRegistry();
		registry.add(ParameterContributorFactory.class, DataAdapterParameterContributorFactory.getInstance());
		registry.add(DataFileServiceFactory.class, BuiltinDataFileServiceFactory.instance());
		extensionsRegistry = registry;
	}
	
	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) 
	{
		return extensionsRegistry;
	}
}