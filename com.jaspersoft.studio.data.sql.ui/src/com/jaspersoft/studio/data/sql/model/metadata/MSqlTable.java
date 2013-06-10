package com.jaspersoft.studio.data.sql.model.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

public class MSqlTable extends AMSQLObject implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSqlTable(ANode parent, String value, String image) {
		super(parent, value, image);
	}

	public MSqlTable(ANode parent, String value, ResultSet rs) {
		super(parent, value, "icons/table.png");
		setType(rs);
		setRemarks(rs);
	}

	protected void setType(ResultSet rs) {
		try {
			type = rs.getString("TABLE_TYPE");
			tooltip += "\n" + type;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void setRemarks(ResultSet rs) {
		try {
			remarks = rs.getString("REMARKS");
			tooltip += "\n" + remarks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String type;

	public String getType() {
		return type;
	}

	private String remarks;

	public String getRemarks() {
		return remarks;
	}

}
