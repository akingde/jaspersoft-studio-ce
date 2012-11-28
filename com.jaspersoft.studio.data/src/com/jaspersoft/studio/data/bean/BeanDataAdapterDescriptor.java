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
package com.jaspersoft.studio.data.bean;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.bean.BeanDataAdapter;
import net.sf.jasperreports.data.bean.BeanDataAdapterImpl;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class BeanDataAdapterDescriptor extends DataAdapterDescriptor {
	private BeanDataAdapter beanDataAdapter;

	@Override
	public BeanDataAdapter getDataAdapter() {
		if (beanDataAdapter == null) {
			beanDataAdapter = new BeanDataAdapterImpl();
			beanDataAdapter
					.setFactoryClass("com.jaspersoft.studio.data.sample.SampleJRDataSourceFactory");
			beanDataAdapter.setMethodName("createBeanCollection");
		}
		return beanDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.beanDataAdapter = (BeanDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new BeanDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/beans.png");
		}
		return null;
	}
}
