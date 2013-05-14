package com.jaspersoft.studio.data.querydesigner.sql.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MView extends MDBObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MView(ANode parent, String value, ResultSet rs) {
		super(parent, "View", "icons/table.png");
		try {
			tooltip = rs.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}