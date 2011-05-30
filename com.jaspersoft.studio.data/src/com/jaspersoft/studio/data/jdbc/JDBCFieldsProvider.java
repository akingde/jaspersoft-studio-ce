package com.jaspersoft.studio.data.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuter;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;

public class JDBCFieldsProvider implements IFieldsProvider {

	public boolean supportsGetFieldsOperation() {
		return true;
	}

	public List<JRDesignField> getFields(DataAdapterDescriptor con,
			JRDataset reportDataset, Map<String, Object> parameters)
			throws JRException, UnsupportedOperationException {
		// this will create jdbc connection
		parameters.putAll(con.getDataAdapterService().getParameters());
		parameters.put(JRJdbcQueryExecuterFactory.PROPERTY_JDBC_FETCH_SIZE, 0);
		try {
			JRJdbcQueryExecuter qe = new JRJdbcQueryExecuter(reportDataset,
					parameters);
			qe.createDatasource();
			ResultSet rs = qe.getResultSet();
			ResultSetMetaData metaData = rs.getMetaData();
			int cc = metaData.getColumnCount();
			List<JRDesignField> columns = new ArrayList<JRDesignField>(cc);
			for (int i = 0; i < cc; i++) {
				JRDesignField field = new JRDesignField();
				field.setName(metaData.getColumnName(i));
				field.setValueClassName(getJdbcTypeClass(metaData, i));
				field.setDescription(null);
				columns.add(field);
			}
			return columns;
		} catch (SQLException e) {
			throw new JRException(e);
		}
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
