/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.random;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.random.RandomDataAdapterImpl;
import net.sf.jasperreports.engine.JasperReportsContext;

public class RandomDataAdapterFactory implements DataAdapterFactory {

	public RandomDataAdapterDescriptor createDataAdapter() {
		return new RandomDataAdapterDescriptor();
	}

	public String getDataAdapterClassName() {
		return RandomDataAdapterImpl.class.getName();
	}

	public String getLabel() {
		return Messages.RandomDataAdapterFactory_0;
	}

	public String getDescription() {
		return Messages.RandomDataAdapterFactory_1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	public Image getIcon(int size) {
		if (size == 16) {
			return JaspersoftStudioPlugin.getInstance().getImage("icons/battery-empty.png"); //$NON-NLS-1$
		}
		return null;
	}

	public DataAdapterService createDataAdapterService(JasperReportsContext jasperReportsContext,
			DataAdapter dataAdapter) {
		return null;
	}

	@Override
	public IDataAdapterCreator iReportConverter() {
		return null;
	}

	@Override
	public boolean isDeprecated() {
		return false;
	}

}
