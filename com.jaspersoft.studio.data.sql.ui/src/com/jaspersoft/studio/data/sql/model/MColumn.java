package com.jaspersoft.studio.data.sql.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

public class MColumn extends MDBObjects implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MColumn(ANode parent, String value, ResultSet rs) {
		super(parent, value, null);
		try {
			tooltip = rs.getString("TYPE_NAME");
			String comment = rs.getString("REMARKS");
			if (comment != null)
				tooltip += "\n" + comment;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
