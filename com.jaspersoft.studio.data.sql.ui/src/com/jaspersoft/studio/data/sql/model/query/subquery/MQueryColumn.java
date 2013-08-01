package com.jaspersoft.studio.data.sql.model.query.subquery;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.model.ANode;

public class MQueryColumn extends MSQLColumn {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private AMQueryAliased<?> col;

	public MQueryColumn(ANode parent, AMQueryAliased<?> value) {
		super(parent, value.getDisplayText(), null);
		this.col = value;
	}
}
