package com.jaspersoft.studio.data.sql.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

public class MView extends MDBObjects implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MView(ANode parent, String value, ResultSet rs) {
		super(parent, "View", "icons/table.png");
		try {
			tooltip = rs.getString("REMARKS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}