package com.jaspersoft.studio.data.sql.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class AMSQLObject extends MDBObjects implements IQueryString {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMSQLObject(ANode parent, String value, String image) {
		super(parent, value, image);
	}

	@Override
	public String getToolTip() {
		String name = toSQLString();
		if (tooltip != null)
			name += "\n" + tooltip;
		return name;
	}

	public String toSQLString() {
		String str = getValue();
		ANode p = getParent();
		while (p != null) {
			if (p instanceof AMSQLObject)
				return ((AMSQLObject) p).toSQLString() + "." + getValue();
			p = p.getParent();
		}
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof AMSQLObject && ((AMSQLObject) obj).toSQLString().equals(toSQLString());
	}

	public int hashCode() {
		return toSQLString().hashCode();
	};
}
