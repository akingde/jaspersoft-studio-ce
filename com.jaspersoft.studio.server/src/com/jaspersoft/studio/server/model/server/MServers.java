/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model.server;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.ServerManager;

public class MServers extends ANode {

	private static final long serialVersionUID = 5651467748362082228L;
	
	/**
	 * Comparator to sort the data adapter by name
	 */
	private static final Comparator<INode> serverComparator = new Comparator<INode>() {
		
		@Override
		public int compare(INode o1, INode o2) {
			ServerProfile d1 = (ServerProfile)o1.getValue();
			ServerProfile d2 = (ServerProfile)o2.getValue();
			return d1.getName().toLowerCase().compareTo(d2.getName().toLowerCase());
		}
	};
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("SERVERS".equals(evt.getPropertyName())) //$NON-NLS-1$
			updateChildren();
		if (evt.getPropertyName().equals(ServerManager.SERVERPROFILE) || 
				evt.getPropertyName().equals(ServerProfile.PROPERTY_NAME)){
			Collections.sort(getChildren(), serverComparator);
		}
		super.propertyChange(evt);
	}

	private void updateChildren() {

	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("servers"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MServers(ANode parent) {
		super(parent, -1);
		updateChildren();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
