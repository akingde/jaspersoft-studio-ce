/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.hadoop.hive.connection.HiveConnection;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveFieldsProvider implements IFieldsProvider {
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

	public List<JRDesignField> getFields(DataAdapterService dataAdapterService,
			JasperReportsConfiguration jasperReportsConfiguration,
			JRDataset dataset) throws JRException,
			UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(JRParameter.REPORT_MAX_COUNT, 0);
		dataAdapterService.contributeParameters(parameters);
		ParameterUtil.setParameters(jasperReportsConfiguration, dataset,
				parameters);

		JRField[] fields = com.jaspersoft.hadoop.hive.HiveFieldsProvider
				.getInstance().getFields(
						jasperReportsConfiguration,
						(HiveConnection) parameters
								.get(JRParameter.REPORT_CONNECTION), dataset,
						parameters);
		List<JRDesignField> designFields = new ArrayList<JRDesignField>();
		for (int index = 0; index < fields.length; index++) {
			designFields.add((JRDesignField) fields[index]);
		}
		return designFields;
	}
}
