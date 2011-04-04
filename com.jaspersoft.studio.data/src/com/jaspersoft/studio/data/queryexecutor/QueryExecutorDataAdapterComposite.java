/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.queryexecutor;

import org.eclipse.swt.widgets.Composite;

public class QueryExecutorDataAdapterComposite extends Composite {
	
	private QueryExecutorDataAdapter queryExecutorDataAdapter = null;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public QueryExecutorDataAdapterComposite(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Set the MyDataAdapter to edit.
	 * The UI will be updated with the content of this adapter
	 * @param dataAdapter
	 */
	public void setDataAdapter(QueryExecutorDataAdapter queryExecutorDataAdapter) {
		this.queryExecutorDataAdapter = queryExecutorDataAdapter;
	}

	public QueryExecutorDataAdapter getDataAdapter() {
		return queryExecutorDataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}
}
