/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;
import com.jaspersoft.studio.utils.parameter.SimpleValueParameter;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuter;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JRQueryExecuter;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRQueryExecuterUtils;

public class JDBCFieldsProvider implements IFieldsProvider {

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		con.contributeParameters(parameters);

		ParameterUtil.setParameters(jConfig, jDataset, parameters);
		parameters.put(JRJdbcQueryExecuterFactory.PROPERTY_JDBC_FETCH_SIZE, 0);
		parameters.put(JRParameter.REPORT_MAX_COUNT, 1);
		List<JRDesignField> columns = null;
		Connection c = null;
		try {
			c = (Connection) parameters.get(JRParameter.REPORT_CONNECTION);

			// JasperReports query executer instances require
			// REPORT_PARAMETERS_MAP parameter to be defined and not null
			Map<String, JRValueParameter> tmpMap = ParameterUtil.convertMap(parameters, jDataset);
			tmpMap.put(JRParameter.REPORT_PARAMETERS_MAP,
					new SimpleValueParameter(new HashMap<String, JRValueParameter>()));

			QueryExecuterFactory queryExecuterFactory = JRQueryExecuterUtils.getInstance(jConfig)
					.getExecuterFactory(jDataset.getQuery().getLanguage());
			JRQueryExecuter qe = queryExecuterFactory.createQueryExecuter(jConfig, jDataset, tmpMap);
			qe.createDatasource();
			if (qe instanceof JRJdbcQueryExecuter) {
				ResultSet rs = ((JRJdbcQueryExecuter) qe).getResultSet();
				if (rs != null) {
					ResultSetMetaData metaData = rs.getMetaData();
					int cc = metaData.getColumnCount();
					Set<String> colset = new HashSet<String>();
					columns = new ArrayList<JRDesignField>(cc);
					for (int i = 1; i <= cc; i++) {
						JRDesignField field = new JRDesignField();
						String name = metaData.getColumnLabel(i);
						// System.out.println("name: " +
						// metaData.getColumnName(i) +
						// " Label: " + name);

						field.getPropertiesMap().setProperty(DataQueryAdapters.FIELD_LABEL, name);
						if (colset.contains(name))
							name = JRResultSetDataSource.INDEXED_COLUMN_PREFIX + i;
						colset.add(name);

						field.setName(StringUtils.xmlEncode(name, null));

						field.setValueClassName(getJdbcTypeClass(metaData, i));
						// FIXME - Temporary commented for performance issues
						// with Simba JDBC driver for Impala
						// try {
						// String catalog = metaData.getCatalogName(i);
						// String schema = metaData.getSchemaName(i);
						// String table = metaData.getTableName(i);
						// if (!(Misc.isNullOrEmpty(catalog)
						// && Misc.isNullOrEmpty(schema) && Misc
						// .isNullOrEmpty(table))) {
						// ResultSet rsmc = c.getMetaData().getColumns(
						// catalog, schema, table, name);
						// while (rsmc.next()) {
						// field.setDescription(StringUtils.xmlEncode(
						// rsmc.getString("REMARKS"), null));
						// break;
						// }
						// }
						// } catch (SQLException se) {
						// se.printStackTrace();
						// }

						String tbl = metaData.getTableName(i);
						if (!Misc.isNullOrEmpty(tbl))
							field.getPropertiesMap().setProperty(DataQueryAdapters.FIELD_PATH, tbl);
						columns.add(field);
					}
				}
			}
		} catch (SQLException e) {
			throw new JRException(e);
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					throw new JRException(e);
				}
		}
		return columns;
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
				case Types.BOOLEAN:
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
					return "java.lang.Float";
				case Types.FLOAT:
				case Types.DOUBLE:
					return "java.lang.Double";
				case Types.BINARY:
				case Types.VARBINARY:
				case Types.LONGVARBINARY:
					return "byte[]";
				case Types.DATE:
					return "java.sql.Date";
				case Types.TIME:
					return "java.sql.Time";
				case Types.TIMESTAMP:
					return "java.sql.Timestamp";
				case Types.CLOB:
					return "java.sql.Clob";
				case Types.BLOB:
					return "java.sql.Blob";
				case Types.ARRAY:
					return "java.sql.Array";
				case Types.STRUCT:
					return "java.sql.Struct";
				case Types.REF:
					return "java.sql.Ref";
				case Types.DATALINK:
					return "java.net.URL";
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

	private static Map<String, String> types = new HashMap<>();
	static {
		types.put("CHAR", "java.lang.String");
		types.put("VARCHAR", "java.lang.String");
		types.put("LONGVARCHAR", "java.lang.String");
		types.put("NUMERIC", "java.math.BigDecimal");
		types.put("DECIMAL", "java.math.BigDecimal");
		types.put("NUMERIC", "java.math.BigDecimal");
		types.put("BIT", "java.lang.Boolean");
		types.put("TINYINT", "java.lang.Byte");
		types.put("SMALLINT", "java.lang.Short");
		types.put("INTEGER", "java.lang.Integer");
		types.put("BIGINT", "java.lang.Long");
		types.put("REAL", "java.lang.Float");
		types.put("FLOAT", "java.lang.Double");
		types.put("DOUBLE", "java.lang.Double");
		types.put("BINARY", "java.lang.Byte[]");
		types.put("VARBINARY", "java.lang.Byte[]");
		types.put("LONGVARBINARY", "java.lang.Byte[]");
		types.put("DATE", "java.sql.Date");
		types.put("TIME", "java.sql.Time");
		types.put("TIMESTAMP", "java.sql.Timestamp");
		types.put("CLOB", "java.sql.Clob");
		types.put("BLOB", "java.sql.Blob");
		types.put("ARRAY", "java.sql.Array");
		types.put("DISTINCT", "Mapping of underlying type");
		types.put("STRUCT", "java.sql.Struct");
		types.put("REF", "java.sql.Ref");
		types.put("JAVA_OBJECT", "Underlying Java class");
	}

	public static String getJavaType4SQL(String type) {
		if (type == null)
			return Object.class.getCanonicalName();
		return Misc.nvl(types.get(type.toUpperCase()), "java.lang.String");
	}
}
