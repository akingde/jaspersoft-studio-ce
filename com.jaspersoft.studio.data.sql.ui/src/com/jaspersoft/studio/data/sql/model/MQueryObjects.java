package com.jaspersoft.studio.data.sql.model;

import java.util.UUID;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.sql.Activator;
import com.jaspersoft.studio.model.ANode;

public abstract class MQueryObjects extends ANode implements IQueryString {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	transient private ImageDescriptor icon;
	private String image;
	protected String tooltip;
	private String id;

	public MQueryObjects(ANode parent, AMSQLObject value, String image) {
		this(parent, value, image, -1);
	}

	public MQueryObjects(ANode parent, AMSQLObject value, String image, int index) {
		super(parent, index);
		setValue(value);
		this.image = image;
		id = UUID.randomUUID().toString();
		if (image != null)
			icon = Activator.getDefault().getImageDescriptor(image);
	}

	public String getId() {
		return id;
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
		if (icon == null && image != null)
			icon = Activator.getDefault().getImageDescriptor(image);
		return icon;
	}

	@Override
	public String getDisplayText() {
		return getValue().toSQLString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MQueryObjects && ((MQueryObjects) obj).getId().equals(getId());
	}

	public int hashCode() {
		return getId().hashCode();
	};

	@Override
	public String toSQLString() {
		return (isFirst() ? getDisplayText() : ",\n\t" + getDisplayText()) + " ";
	}
}
