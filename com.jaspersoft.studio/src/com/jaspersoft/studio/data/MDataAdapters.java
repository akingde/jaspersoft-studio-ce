package com.jaspersoft.studio.data;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

public class MDataAdapters extends ANode{
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if("DATAADAPTERS".equals(evt.getPropertyName())) //$NON-NLS-1$
		{
			updateChildren();
		}
		
		super.propertyChange(evt);
	}

	private void updateChildren() {
		this.removeChildren();
		List<DataAdapter> dataAdapters = DataAdapterManager.getDataAdapters();
		for (DataAdapter dataAdapter : dataAdapters) {
			MDataAdapter mDataAdapter = new MDataAdapter(dataAdapter);
			this.addChild(mDataAdapter);
		}
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

	public MDataAdapters(ANode parent) {
		super(parent, -1);
		DataAdapterManager.getPropertyChangeSupport().addPropertyChangeListener(this);
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
