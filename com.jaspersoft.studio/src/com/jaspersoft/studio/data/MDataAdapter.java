package com.jaspersoft.studio.data;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;

/**
 * This class wraps a DataAdapter to display a dataAdapter
 * inside the repository view.
 * The dataAdapters node in the view takes care to update its content
 * 
 * @author czhu, gtoffoli
 *
 */
public class MDataAdapter extends ANode {

	/** The icon descriptor. */
	private DataAdapter dataAdapter = null;
	
	public DataAdapter getDataAdapter() {
		return dataAdapter;
	}

	public MDataAdapter(DataAdapter dataAdapter)
	{
		this.dataAdapter = dataAdapter;
		//String description = DataAdapterManager.findFactoryByDataAdapterClass(this.dataAdapter.getClass().getName()).getDescription();
	}

	public String getDisplayText() {
		return dataAdapter.getTitle();
	}

	public ImageDescriptor getImagePath() {
		return dataAdapter.getIcon16();
	}
}
