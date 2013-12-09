package com.jaspersoft.studio.data.cassandra.cql3;

import java.util.ResourceBundle;

import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/**
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3IconDescriptor extends NodeIconDescriptor {

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *            the name
	 */
	public CassandraCQL3IconDescriptor(String name) {
		super(name, Activator.getDefault());
	}

	/** The resource bundle icons. */
	private static ResourceBundle resourceBundleIcons;

	@Override
	public ResourceBundle getResourceBundleIcons() {
		return resourceBundleIcons;
	}

	@Override
	public void setResourceBundleIcons(ResourceBundle resourceBundleIcons) {
		CassandraCQL3IconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}
}
