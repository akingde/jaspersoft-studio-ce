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
		setRemarks(rs);
	}

	protected void setRemarks(ResultSet rs) {
		try {
			remarks = rs.getString("REMARKS");
			tooltip = remarks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String remarks;

	public String getRemarks() {
		return remarks;
	}

}
