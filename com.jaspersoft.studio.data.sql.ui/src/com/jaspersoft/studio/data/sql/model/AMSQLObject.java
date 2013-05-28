package com.jaspersoft.studio.data.sql.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class AMSQLObject extends MDBObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMSQLObject(ANode parent, String value, String image) {
		super(parent, value, image);
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

}
