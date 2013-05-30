package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.MQueryObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.model.ANode;

public class MSelect extends MQueryObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelect(ANode parent, MColumn value, String image) {
		super(parent, value, "");
	}

}
