package com.jaspersoft.studio.data.sql.model;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.sql.Activator;
import com.jaspersoft.studio.model.ANode;

public abstract class MQueryObjects extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	transient private ImageDescriptor icon;
	transient protected String tooltip;

	public MQueryObjects(ANode parent, AMSQLObject value, String image) {
		this(parent, value, image, -1);
	}

	public MQueryObjects(ANode parent, AMSQLObject value, String image, int index) {
		super(parent, index);
		setValue(value);
		if (image != null)
			icon = Activator.getDefault().getImageDescriptor(image);
	}

	@Override
	public AMSQLObject getValue() {
		return (AMSQLObject) super.getValue();
	}

	@Override
	public String getToolTip() {
		String name = getValue().getToolTip();
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
		return getValue().getDisplayText();
	}

	public abstract String toSQLString();

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MQueryObjects && ((MQueryObjects) obj).toSQLString().equals(toSQLString());
	}

	public int hashCode() {
		return toSQLString().hashCode();
	};
}
