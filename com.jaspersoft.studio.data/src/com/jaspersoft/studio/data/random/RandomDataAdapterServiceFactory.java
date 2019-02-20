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
package com.jaspersoft.studio.data.random;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterContributorFactory;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.ParameterContributorContext;

/**
 * Build the service for the DummyDataAdapter type
 * 
 * @author Veaceslav Chicu
 *
 */
public class RandomDataAdapterServiceFactory implements DataAdapterContributorFactory {

	private static final RandomDataAdapterServiceFactory INSTANCE = new RandomDataAdapterServiceFactory();

	public static RandomDataAdapterServiceFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public DataAdapterService getDataAdapterService(ParameterContributorContext context, DataAdapter dataAdapter) {
		DataAdapterService dataAdapterService = null;
		if (dataAdapter instanceof RandomDataAdapter)
			dataAdapterService = new RandomDataAdapterService(context, (RandomDataAdapter) dataAdapter);
		return dataAdapterService;

	}

}
