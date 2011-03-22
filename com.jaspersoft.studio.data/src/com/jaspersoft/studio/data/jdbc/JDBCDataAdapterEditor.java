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
package com.jaspersoft.studio.data.jdbc;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

/**
 * @author gtoffoli
 *
 */
public class JDBCDataAdapterEditor implements DataAdapterEditor {

	JDBCDataAdapterComposite composite = null;
	
	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterEditor#getComposite(org.eclipse.swt.widgets.Composite, int)
	 */
	public Composite getComposite(Composite parent, int style) {
		// TODO Auto-generated method stub
		if (composite == null || composite.getParent() != parent)
		{
			if (composite != null) composite.dispose();
			composite = new JDBCDataAdapterComposite(parent, style);
		}
		return composite;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterEditor#getDataAdapter()
	 */
	public DataAdapter getDataAdapter() {

		if (composite != null)
		{
			return composite.getDataAdapter();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterEditor#getHelpContextId()
	 */
	public String getHelpContextId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterEditor#setDataAdapter(com.jaspersoft.studio.data.DataAdapter)
	 */
	public void setDataAdapter(DataAdapter dataAdapter) {

			if (composite != null && dataAdapter instanceof JDBCDataAdapter)
			{
				composite.setDataAdapter((JDBCDataAdapter)dataAdapter);
			}
	}

}
