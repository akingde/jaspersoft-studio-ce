package com.jaspersoft.studio.data.sql.model.metadata.keys;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.utils.Misc;

public class ForeignKey implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String fkName;
	private MSQLColumn[] srcColumns;
	private MSQLColumn[] destColumns;

	public ForeignKey(String fkName) {
		super();
		this.fkName = fkName;
	}

	public void setColumns(MSQLColumn[] srcColumns, MSQLColumn[] destColumns) {
		this.srcColumns = srcColumns;
		this.destColumns = destColumns;
	}

	public String getFkName() {
		return fkName;
	}

	private String sql;

	public String toSqlString() {
		if (sql == null) {
			StringBuffer sb = new StringBuffer("CONSTRAINT ");
			sb.append(Misc.nvl(fkName));
			sb.append(" FOREIGN KEY (");
			String sep = "";
			if (srcColumns != null)
				for (MSQLColumn c : srcColumns) {
					sb.append(sep);
					sep = ", ";
					sb.append(c.getValue());
				}
			sb.append(") REFERENCES ");
			if (destColumns != null && destColumns.length > 0) {
				MSqlTable destTable = (MSqlTable) destColumns[0].getParent();
				sb.append(destTable.toSQLString()).append(" (");
				sep = "";
				for (MSQLColumn c : destColumns) {
					sb.append(sep);
					sep = ", ";
					sb.append(c.getValue());
				}
			}
			sb.append(")");
			sql = sb.toString();
		}
		return sql;
	}
}
