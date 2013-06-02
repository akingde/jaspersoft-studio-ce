package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;

public class MExpression extends ANode {

	public MExpression(ANode parent, Object value, int newIndex) {
		super(parent, value, newIndex);
	}

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		if (getValue() != null)
			return getValue().toString();
		return "NOTHING";
	}

}