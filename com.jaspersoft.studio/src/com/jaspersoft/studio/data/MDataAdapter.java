/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;

/*
 * This class wraps a DataAdapter to display a dataAdapter inside the repository view. The dataAdapters node in the view
 * takes care to update its content
 * 
 * @author czhu, gtoffoli
 */
public class MDataAdapter extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public DataAdapterDescriptor getValue() {
		return (DataAdapterDescriptor) super.getValue();
	}

	public MDataAdapter(MDataAdapters parent, DataAdapterDescriptor dataAdapter) {
		super(parent, dataAdapter, -1);
	}

	public String getDisplayText() {
		return getValue().getTitle();
	}

	public ImageDescriptor getImagePath() {
		return getValue().getIcon16();
	}
}
