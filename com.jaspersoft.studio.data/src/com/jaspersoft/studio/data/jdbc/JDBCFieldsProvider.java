/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuter;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;

import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;
import com.jaspersoft.studio.utils.parameter.SimpleValueParameter;

public class JDBCFieldsProvider implements IFieldsProvider {

	public boolean supportsGetFieldsOperation() {
		return true;
	}

	public List<JRDesignField> getFields(DataAdapterService con,
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		con.contributeParameters(parameters);

		ParameterUtil.setParameters(reportDataset, parameters);
		parameters.put(JRJdbcQueryExecuterFactory.PROPERTY_JDBC_FETCH_SIZE, 0);
		parameters.put(JRParameter.REPORT_MAX_COUNT, 0);

		try {
			Connection c = (Connection) parameters
					.get(JRParameter.REPORT_CONNECTION);
			
			// JasperReports query executer instances require REPORT_PARAMETERS_MAP parameter to be defined and not null
			Map<String,JRValueParameter> tmpMap=ParameterUtil.convertMap(parameters);
			tmpMap.put(JRParameter.REPORT_PARAMETERS_MAP, new SimpleValueParameter(new HashMap<>() ));
			
			JRJdbcQueryExecuter qe = new JRJdbcQueryExecuter(reportDataset,
					tmpMap);
			qe.createDatasource();
			ResultSet rs = qe.getResultSet();
			if (rs != null) {
				ResultSetMetaData metaData = rs.getMetaData();
				int cc = metaData.getColumnCount();
				List<JRDesignField> columns = new ArrayList<JRDesignField>(cc);
				for (int i = 1; i <= cc; i++) {
					JRDesignField field = new JRDesignField();
					String name = metaData.getColumnName(i);
					field.setName(name);

					field.setValueClassName(getJdbcTypeClass(metaData, i));
					try {
						String catalog = metaData.getCatalogName(i);
						String schema = metaData.getSchemaName(i);
						String table = metaData.getTableName(i);
						ResultSet rsmc = c.getMetaData().getColumns(catalog,
								schema, table, name);
						while (rsmc.next()) {
							field.setDescription(rsmc.getString("REMARKS"));
							break;
						}
					} catch (SQLException se) {
						se.printStackTrace();
					}
					columns.add(field);
				}
				return columns;
			}
		} catch (SQLException e) {
			throw new JRException(e);
		}
		return null;
	}

	public static String getJdbcTypeClass(java.sql.ResultSetMetaData rsmd, int t) {
		try {
			return getJRFieldType(rsmd.getColumnClassName(t));
		} catch (SQLException ex) {
			// if getColumnClassName is not supported...
			try {
				int type = rsmd.getColumnType(t);
				switch (type) {
				case Types.CHAR:
				case Types.VARCHAR:
				case Types.LONGVARCHAR:
					return "java.lang.String";
				case Types.NUMERIC:
				case Types.DECIMAL:
					return "java.math.BigDecimal";
				case Types.BIT:
					return "java.lang.Boolean";
				case Types.TINYINT:
					return "java.lang.Byte";
				case Types.SMALLINT:
					return "java.lang.Short";
				case Types.INTEGER:
					return "java.lang.Integer";
				case Types.BIGINT:
					return "java.lang.Long";
				case Types.REAL:
					return "java.lang.Real";
				case Types.FLOAT:
				case Types.DOUBLE:
					return "java.lang.Double";
				case Types.BINARY:
				case Types.VARBINARY:
				case Types.LONGVARBINARY:
					return "java.lang.Byte[]";
				case Types.DATE:
					return "java.sql.Date";
				case Types.TIME:
					return "java.sql.Time";
				case Types.TIMESTAMP:
					return "java.sql.Timestamp";
				}
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
		}
		return Object.class.getName();
	}

	public static String getJRFieldType(String type) {
		if (type == null)
			return Object.class.getName();
		if (type.equals(boolean.class.getName()))
			return Boolean.class.getName();
		if (type.equals(byte.class.getName()))
			return Byte.class.getName();
		if (type.equals(int.class.getName()))
			return Integer.class.getName();
		if (type.equals(long.class.getName()))
			return Long.class.getName();
		if (type.equals(double.class.getName()))
			return Double.class.getName();
		if (type.equals(float.class.getName()))
			return Float.class.getName();
		if (type.equals(short.class.getName()))
			return Short.class.getName();
		if (type.startsWith("["))
			return Object.class.getName();
		return type;
	}
}
