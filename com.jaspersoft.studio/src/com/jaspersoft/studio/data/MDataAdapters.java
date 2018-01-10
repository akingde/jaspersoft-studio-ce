/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Comparator;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/**
 * Class that contains all the global data adapters. This
 * adapters are sorted by name
 *
 */
public class MDataAdapters extends ANode {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 * Comparator to sort the data adapter by name
	 */
	private static final Comparator<INode> adapterComparator = new Comparator<INode>() {
		
		@Override
		public int compare(INode o1, INode o2) {
			DataAdapterDescriptor d1 = (DataAdapterDescriptor)o1.getValue();
			DataAdapterDescriptor d2 = (DataAdapterDescriptor)o2.getValue();
			return d1.getName().toLowerCase().compareTo(d2.getName().toLowerCase());
		}
	};
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (ADataAdapterStorage.PROP_DATAADAPTERS.equals(evt.getPropertyName())) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				boolean exists = false;
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getNewValue()) {
						exists = true;
						break;
					}
				}
				if (!exists){
					new MDataAdapter(this, (DataAdapterDescriptor) evt.getNewValue());
					//Added a new adapter, need to sort the children
					Collections.sort(getChildren(), adapterComparator);
				}
			}
			if (evt.getOldValue() != null)
				for (INode n : getChildren()) {
					MDataAdapter m = (MDataAdapter) n;
					if (m.getValue() == evt.getOldValue()) {
						removeChild(m);
						break;
					}
				}
		}
		super.propertyChange(evt);
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
			iconDescriptor = new NodeIconDescriptor("dataAdapters"); //$NON-NLS-1$
		return iconDescriptor;
	}

	private transient ADataAdapterStorage storage;

	public MDataAdapters(ANode parent, ADataAdapterStorage storage) {
		super(parent, -1);
		this.storage = storage;
		storage.addPropertyChangeListener(this);
		storage.getDataAdapterDescriptors();
		for (DataAdapterDescriptor dad : storage.getDataAdapterDescriptors()){
			new MDataAdapter(this, dad);
		}
		//Sort all the added children
		Collections.sort(getChildren(), adapterComparator);
	}

	@Override
	public ADataAdapterStorage getValue() {
		return storage;
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
