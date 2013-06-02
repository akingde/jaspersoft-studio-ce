package com.jaspersoft.studio.data.sql.model;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.sql.Activator;
import com.jaspersoft.studio.model.ANode;

public class MDBObjects extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	transient private ImageDescriptor icon;
	protected String tooltip;

	public MDBObjects(ANode parent, String value, String image) {
		super(parent, -1);
		setValue(value);
		if (image != null)
			icon = Activator.getDefault().getImageDescriptor(image);
	}

	@Override
	public String getValue() {
		return (String) super.getValue();
	}

	@Override
	public String getToolTip() {
		String name = getValue();
		if (tooltip != null)
			name += "\n" + tooltip;
		return name;
	}

	@Override
	public ImageDescriptor getImagePath() {
		return icon;
	}

	@Override
	public String getDisplayText() {
		return getValue();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MDBObjects && ((MDBObjects) obj).getValue().equals(getValue());
	}

	public int hashCode() {
		return getValue().hashCode();
	};
}
