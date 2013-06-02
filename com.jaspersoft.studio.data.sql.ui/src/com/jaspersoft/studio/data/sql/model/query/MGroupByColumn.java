package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.MQueryObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.model.ANode;

public class MGroupByColumn extends MQueryObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MGroupByColumn(ANode parent, MColumn value) {
		super(parent, value, null);
	}

	public MGroupByColumn(ANode parent, MColumn value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public String toSQLString() {
		return getValue().getDisplayText();
	}

}