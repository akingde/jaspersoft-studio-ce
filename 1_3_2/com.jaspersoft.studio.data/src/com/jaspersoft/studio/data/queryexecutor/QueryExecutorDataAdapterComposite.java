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
package com.jaspersoft.studio.data.queryexecutor;

import net.sf.jasperreports.data.DataAdapter;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

public class QueryExecutorDataAdapterComposite extends ADataAdapterComposite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public QueryExecutorDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {

	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new QueryExecutorDataAdapterDescriptor();
		return dataAdapterDesc;
	}

}
