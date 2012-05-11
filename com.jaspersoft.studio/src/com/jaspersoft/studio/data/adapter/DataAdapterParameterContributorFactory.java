/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.ParameterContributor;
import net.sf.jasperreports.engine.ParameterContributorContext;
import net.sf.jasperreports.engine.ParameterContributorFactory;
import net.sf.jasperreports.repo.RepositoryUtil;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: DataAdapterParameterContributorFactory.java 4734 2011-10-21 12:13:21Z teodord $
 */
public final class DataAdapterParameterContributorFactory implements ParameterContributorFactory {

	public static final String PARAMETER_DATA_ADAPTER = "PARAMETER_DATA_ADAPTER";

	private DataAdapterParameterContributorFactory(JasperReportsContext jasperReportsContext) {
		this.jasperReportsContext = jasperReportsContext;
	}

	/**
	 * 
	 */
	public static DataAdapterParameterContributorFactory getInstance(JasperReportsContext jasperReportsContext) {
		return new DataAdapterParameterContributorFactory(jasperReportsContext);
	}

	private JasperReportsContext jasperReportsContext;

	/**
	 *
	 */
	public List<ParameterContributor> getContributors(ParameterContributorContext context) throws JRException {
		List<ParameterContributor> contributors = new ArrayList<ParameterContributor>();

		DataAdapter dataAdapter = null;
		Object param = context.getParameterValues().get(PARAMETER_DATA_ADAPTER);
		if (param != null && param instanceof DataAdapter)
			dataAdapter = (DataAdapter) param;
		if (dataAdapter == null) {
			String dataAdapterUri = JRPropertiesUtil.getInstance(jasperReportsContext).getProperty(context.getDataset(),
					"net.sf.jasperreports.data.adapter");
			if (dataAdapterUri != null)
				dataAdapter = RepositoryUtil.getInstance(jasperReportsContext).getResourceFromLocation(dataAdapterUri,
						DataAdapter.class);
		}
		if (dataAdapter != null) {
			ParameterContributor dataAdapterService = DataAdapterServiceUtil.getInstance(jasperReportsContext).getService(
					dataAdapter);
			return Collections.singletonList(dataAdapterService);
		}
		return contributors;
	}
}
