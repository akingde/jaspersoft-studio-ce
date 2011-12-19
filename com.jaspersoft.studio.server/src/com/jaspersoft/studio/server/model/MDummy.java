package com.jaspersoft.studio.server.model;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.Activator;

public class MDummy extends ANode {

	public MDummy(ANode parent) {
		super(parent, -1);
	}

	public ImageDescriptor getImagePath() {
		return Activator.getImageDescriptor("icons/hourglass.png");
	}

	public String getDisplayText() {
		return "Pending ...";
	}

}
