/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.queryexecutor;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.qe.QueryExecuterDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class QueryExecutorDataAdapterDescriptor extends DataAdapterDescriptor {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public DataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new QueryExecuterDataAdapterImpl();
		return dataAdapter;
	}

	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(JaspersoftStudioPlugin.PLUGIN_ID,
				"icons/QueryExecutorDataAdapterIcon-16.gif");
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new QueryExecutorDataAdapterEditor();
	}

	@Override
	public Image getIcon(int size) {
		if (size == 16)
			return JaspersoftStudioPlugin.getInstance().getImage("icons/QueryExecutorDataAdapterIcon-16.gif");
		return null;
	}

	@Override
	public boolean doSupportTest() {
		return false;
	}
}
