package com.jaspersoft.studio.data.sql.model.metadata;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.utils.Misc;

public class PrimaryKey implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String pkName;
	private MSQLColumn[] columns;

	public PrimaryKey(String pkName) {
		super();
		this.pkName = pkName;
	}

	public void setColumns(MSQLColumn[] columns) {
		this.columns = columns;
	}

	private String sql;

	public String toSqlString() {
		if (sql == null) {
			StringBuffer sb = new StringBuffer("PRIMARY KEY ");
			sb.append(Misc.nvl(pkName));
			sb.append(" (");
			String sep = "";
			for (MSQLColumn c : columns) {
				sb.append(sep);
				sep = ", ";
				sb.append(c.getValue());
			}
			sb.append(")");
			sql = sb.toString();
		}
		return sql;
	}
}
