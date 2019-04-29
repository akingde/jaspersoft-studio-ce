/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.noda;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

import net.sf.jasperreports.data.qe.QueryExecuterDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;

public class NoDataAdapterDescriptor extends DataAdapterDescriptor {

	/**
	 * Name of the empty data adapter
	 */
	public static final String NO_ADAPTER_NAME = "NO_DATA_ADAPTER";

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public QueryExecuterDataAdapterImpl getDataAdapter() {
		if (dataAdapter == null) {
			dataAdapter = new QueryExecuterDataAdapterImpl();
			dataAdapter.setName(NO_ADAPTER_NAME); 
		}
		return (QueryExecuterDataAdapterImpl) dataAdapter;
	}
	
	@Override
	public String getTitle() { 
		return "Don't use a Data Adapter";
	}

	@Override
	public Image getIcon(int size) {
		return JaspersoftStudioPlugin.getInstance().getImage("icons/battery-empty.png");
	}

	@Override
	public DataAdapterEditor getEditor() {
		return null;
	}

}
