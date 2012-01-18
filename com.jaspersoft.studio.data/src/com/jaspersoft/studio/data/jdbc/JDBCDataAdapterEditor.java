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
package com.jaspersoft.studio.data.jdbc;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
/*
 * @author gtoffoli
 *
 */
public class JDBCDataAdapterEditor implements DataAdapterEditor {

	protected JDBCDataAdapterComposite composite = null;
	
	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterEditor#getComposite(org.eclipse.swt.widgets.Composite, int)
	 */
	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage) {
		
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
	public DataAdapterDescriptor getDataAdapter() {

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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterEditor#setDataAdapter(com.jaspersoft.studio.data.DataAdapter)
	 */
	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		
		if (composite != null && dataAdapter instanceof JDBCDataAdapterDescriptor)
		{
			composite.setDataAdapter((JDBCDataAdapterDescriptor)dataAdapter);
		}
	}
}
