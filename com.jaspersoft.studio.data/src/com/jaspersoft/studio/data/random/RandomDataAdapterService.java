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

import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.ParameterContributorContext;

/**
 * The service uses the data adapter to populate the DataSource
 * 
 * @author Veaceslav Chicu
 *
 */
public class RandomDataAdapterService extends AbstractDataAdapterService {

	private RandomDataAdapter dataAdapter;

	public RandomDataAdapterService(ParameterContributorContext context, RandomDataAdapter dataAdapter) {
		super(context, dataAdapter);
		this.dataAdapter = dataAdapter;
	}

	@Override
	public void contributeParameters(Map<String, Object> parameters) throws JRException {
		parameters.put(JRParameter.REPORT_DATA_SOURCE, new RandomDataSource(dataAdapter.getRecordNumber()));
	}

}