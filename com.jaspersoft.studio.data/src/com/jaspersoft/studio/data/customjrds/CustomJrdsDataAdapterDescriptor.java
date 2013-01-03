/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.customjrds;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.ds.DataSourceDataAdapter;
import net.sf.jasperreports.data.ds.DataSourceDataAdapterImpl;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class CustomJrdsDataAdapterDescriptor extends DataAdapterDescriptor {

	private DataSourceDataAdapter dsDataAdapter = new DataSourceDataAdapterImpl();

	@Override
	public DataAdapter getDataAdapter() {
		if (dsDataAdapter == null) {
			dsDataAdapter = new DataSourceDataAdapterImpl();
			dsDataAdapter
					.setFactoryClass("com.jaspersoft.studio.data.sample.SampleJRDataSourceFactory");
		}
		return dsDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		dsDataAdapter = (DataSourceDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new CustomJrdsDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/bean-green.png");
		}
		return null;
	}
}
