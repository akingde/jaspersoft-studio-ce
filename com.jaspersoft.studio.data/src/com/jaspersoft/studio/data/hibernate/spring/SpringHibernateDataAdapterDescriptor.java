/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.hibernate.spring;

import net.sf.jasperreports.data.hibernate.spring.SpringHibernateDataAdapter;
import net.sf.jasperreports.data.hibernate.spring.SpringHibernateDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuter;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class SpringHibernateDataAdapterDescriptor extends DataAdapterDescriptor {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public SpringHibernateDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new SpringHibernateDataAdapterImpl();
		return (SpringHibernateDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new SpringHibernateDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/hibernate.png");
		}
		return null;
	}

	@Override
	public String[] getLanguages() {
		return new String[] { JRHibernateQueryExecuter.CANONICAL_LANGUAGE };
	}
}
