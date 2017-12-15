/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.empty;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.empty.EmptyDataAdapterImpl;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;
import com.jaspersoft.studio.messages.Messages;

public class EmptyDataAdapterFactory implements DataAdapterFactory {

	public EmptyDataAdapterDescriptor createDataAdapter() {
		return new EmptyDataAdapterDescriptor();
	}

	public String getDataAdapterClassName() {
		return EmptyDataAdapterImpl.class.getName();
	}

	public String getLabel() {
		return Messages.EmptyDataAdapterFactory_label;
	}

	public String getDescription() {
		return Messages.EmptyDataAdapterFactory_description;
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

	public DataAdapterService createDataAdapterService(JasperReportsContext jasperReportsContext, DataAdapter dataAdapter) {
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
