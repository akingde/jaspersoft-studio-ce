package com.jaspersoft.studio.data.sql.model.query;

import java.util.UUID;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.sql.Activator;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public abstract class AMQueryObject<T> extends ANode implements IQueryString {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	transient private ImageDescriptor icon;
	private String image;
	protected String tooltip;
	private String id;

	public AMQueryObject(ANode parent, T value, String image) {
		this(parent, value, image, -1);
	}

	public AMQueryObject(ANode parent, T value, String image, int index) {
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

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		return (T) super.getValue();
	}

	@Override
	public String getToolTip() {
		if (getValue() instanceof INode) {
			String name = ((INode) getValue()).getToolTip();
			if (tooltip != null)
				name += "\n" + tooltip;
			return name;
		} else if (getValue() instanceof String)
			return (String) getValue();
		return null;
	}

	@Override
	public ImageDescriptor getImagePath() {
		if (icon == null && image != null)
			icon = Activator.getDefault().getImageDescriptor(image);
		return icon;
	}

	@Override
	public String getDisplayText() {
		if (getValue() instanceof IQueryString)
			return ((IQueryString) getValue()).toSQLString();
		else if (getValue() instanceof String)
			return (String) getValue();
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return obj.getClass().equals(getClass()) && ((AMQueryObject<?>) obj).getId().equals(getId());
	}

	public int hashCode() {
		return getId().hashCode();
	};

	@Override
	public String toSQLString() {
		return (isFirst() ? getDisplayText() : ",\n\t" + getDisplayText()) + " ";
	}
}
