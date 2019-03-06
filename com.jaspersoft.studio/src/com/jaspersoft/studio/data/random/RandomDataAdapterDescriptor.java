/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.random;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.data.random.RandomDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;

public class RandomDataAdapterDescriptor extends DataAdapterDescriptor {

	/**
	 * Name of the random data adapter
	 */
	public static final String RANDOM_ADAPTER_NAME = Messages.RandomDataAdapterDescriptor_0;

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public RandomDataAdapterImpl getDataAdapter() {
		if (dataAdapter == null) {
			dataAdapter = new RandomDataAdapterImpl();
			dataAdapter.setName(RANDOM_ADAPTER_NAME);
			((RandomDataAdapterImpl) dataAdapter).setRecordNumber(10);
		}
		return (RandomDataAdapterImpl) dataAdapter;
	}

	@Override
	public Image getIcon(int size) {
		return JaspersoftStudioPlugin.getInstance().getImage("icons/battery-empty.png");
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new RandomDataAdapterEditor();
	}

}
