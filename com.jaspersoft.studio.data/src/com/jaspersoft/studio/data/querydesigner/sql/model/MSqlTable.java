package com.jaspersoft.studio.data.querydesigner.sql.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MSqlTable extends MDBObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSqlTable(ANode parent, String value, ResultSet rs) {
		super(parent, value, "icons/table.png");
		try {
			tooltip = rs.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
