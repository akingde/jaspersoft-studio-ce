package com.jaspersoft.studio.data.sql.model.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

public class MColumn extends AMSQLObject implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MColumn(ANode parent, String value, ResultSet rs) {
		super(parent, value, null);
		try {
			typeName = rs.getString("TYPE_NAME");
			tooltip = typeName;
			remarks = rs.getString("REMARKS");
			if (remarks != null)
				tooltip += "\n" + remarks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String remarks;
	private String typeName;

	public String getRemarks() {
		return remarks;
	}

	public String getTypeName() {
		return typeName;
	}
}
