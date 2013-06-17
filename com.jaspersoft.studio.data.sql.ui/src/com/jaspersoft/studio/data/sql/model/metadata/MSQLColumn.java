/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.metadata;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

public class MSQLColumn extends AMSQLObject implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSQLColumn(ANode parent, String value, ResultSet rs) {
		super(parent, value, null);
		try {
			typeName = rs.getString("TYPE_NAME");
			columnSize = rs.getInt("COLUMN_SIZE");
			scale = rs.getInt("DECIMAL_DIGITS");
			precission = rs.getInt("NUM_PREC_RADIX");
			nullable = rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
			tooltip = formatedType();
			remarks = rs.getString("REMARKS");
			if (remarks != null)
				tooltip += "\n" + remarks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String remarks;
	private String typeName;
	private int columnSize;
	private int precission;
	private int scale;
	private boolean nullable;

	public String getRemarks() {
		return remarks;
	}

	public String getTypeName() {
		return formatedType();
	}

	private String formatedType() {
		String tname = "\n" + typeName;
		if (typeName.equalsIgnoreCase("VARCHAR") || typeName.equalsIgnoreCase("CHAR") || typeName.equalsIgnoreCase("CHARACTER") || typeName.equalsIgnoreCase("NATIONAL CHARACTER")
				|| typeName.equalsIgnoreCase("NCHAR") || typeName.equalsIgnoreCase("CHARACTER VARYING") || typeName.equalsIgnoreCase("NATIONAL CHARACTER VARYING") || typeName.equalsIgnoreCase("NVARCHAR")
				|| typeName.equalsIgnoreCase("BIT") || typeName.equalsIgnoreCase("BIT VARYING") || typeName.equalsIgnoreCase(" TEXT") || typeName.equalsIgnoreCase("STRING")
				|| typeName.equalsIgnoreCase("BINARY") || typeName.equalsIgnoreCase("VARBINARY") || typeName.equalsIgnoreCase("LONGVARBINARY"))
			tname += "(" + columnSize + ")";
		else if (typeName.equalsIgnoreCase("NUMERIC") || typeName.equalsIgnoreCase("DECIMAL"))
			tname += "(" + precission + ", " + scale + ")";
		if (!nullable)
			tname += " NOT NULL";
		return tname;
	}

}
