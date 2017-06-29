/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.empty;

import net.sf.jasperreports.data.empty.EmptyDataAdapter;
import net.sf.jasperreports.data.empty.EmptyDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class EmptyDataAdapterDescriptor extends DataAdapterDescriptor {

	/**
	 * Name of the empty data adapter
	 */
	public static final String EMPTY_ADAPTER_NAME = "One Empty Record";

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public EmptyDataAdapter getDataAdapter() {
		if (dataAdapter == null) {
			dataAdapter = new EmptyDataAdapterImpl();
			dataAdapter.setName(EMPTY_ADAPTER_NAME);
			((EmptyDataAdapter) dataAdapter).setRecordCount(1);
		}
		return (EmptyDataAdapter) dataAdapter;
	}

	@Override
	public Image getIcon(int size) {
		return JaspersoftStudioPlugin.getInstance().getImage("icons/battery-empty.png");
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new EmptyDataAdapterEditor();
	}

}
