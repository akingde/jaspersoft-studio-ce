package com.jaspersoft.cassandra.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.JRAbstractQueryExecuter;

import org.apache.log4j.Logger;

import com.jaspersoft.cassandra.CassandraDataSource;
import com.jaspersoft.cassandra.connection.CassandraConnection;

/**
 * Cassandra query executer for CQL queries. <br/>
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraQueryExecuter extends JRAbstractQueryExecuter {
	private Statement statement;

	protected static final String CLAUSE_ID_IN = "IN";

	private final static DateFormat DATE_FORMAT = DateFormat
			.getDateInstance(DateFormat.FULL);

	private final static Logger logger = Logger
			.getLogger(CassandraQueryExecuter.class);

	public CassandraQueryExecuter(JasperReportsContext jasperReportsContext,
			JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters) {
		super(jasperReportsContext, dataset, parameters);
		registerFunctions();
		parseQuery();
	}

	protected void registerFunctions() {
		registerClauseFunction(CLAUSE_ID_IN, CassandraSqlInClause.getInstance());
	}

	public boolean cancelQuery() throws JRException {
		try {
			statement.cancel();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new JRException(e.getMessage());
		}
	}

	public void close() {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement = null;
		}
	}

	/**
	 * Creates a new {@link CassandraDataSource} that will run the query and
	 * process the results
	 */
	@Override
	public JRDataSource createDatasource() throws JRException {
		CassandraConnection connection = (CassandraConnection) ((Map<?, ?>) getParameterValue(JRParameter.REPORT_PARAMETERS_MAP))
				.get(CassandraDataSource.CASSANDRA_CONNECTION);
		if (connection == null) {
			logger.error("No Cassandra connection");
			System.out.println("Testing as default connection");
			connection = (CassandraConnection) ((Map<?, ?>) getParameterValue(JRParameter.REPORT_PARAMETERS_MAP))
					.get(JRParameter.REPORT_CONNECTION);
			if (connection == null) {
				return null;
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement = null;
		}
		try {
			statement = connection.getSqlConnection().createStatement();
			return new CassandraDataSource(
					statement.executeQuery(getQueryString()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new JRException(e.getMessage());
		}
	}

	protected String getParameterReplacement(String parameterName) {
		JRValueParameter parameter = getValueParameter(parameterName);
		Class<?> parameterType = parameter.getValueClass();
		Object parameterValue = parameter.getValue();
		if (parameterValue == null) {
			return "null";
		}
		return getParameterReplacement(parameterType, parameterValue);
	}

	public static String getParameterReplacement(Class<?> parameterType,
			Object parameterValue) {
		if (Boolean.class.isAssignableFrom(parameterType)) {
			return String.valueOf(((Boolean) parameterValue).booleanValue());
		} else if (Byte.class.isAssignableFrom(parameterType)) {
			return "\"" + ((Byte) parameterValue).byteValue() + "\"";

		} else if (Double.class.isAssignableFrom(parameterType)) {
			return String.valueOf(parameterValue);
		} else if (Float.class.isAssignableFrom(parameterType)) {
			return String.valueOf(parameterValue);
		} else if (Integer.class.isAssignableFrom(parameterType)) {
			return String.valueOf(parameterValue);
		} else if (Long.class.isAssignableFrom(parameterType)) {
			return String.valueOf(parameterValue);
		} else if (Short.class.isAssignableFrom(parameterType)) {
			return String.valueOf(parameterValue);
		} else if (BigDecimal.class.isAssignableFrom(parameterType)) {
			return String.valueOf(parameterValue);
		} else if (String.class.isAssignableFrom(parameterType)) {
			return "\"" + String.valueOf(parameterValue) + "\"";
		} else if (Timestamp.class.isAssignableFrom(parameterType)) {
			return "\"" + DATE_FORMAT.format((Timestamp) parameterValue) + "\"";
		} else if (Time.class.isAssignableFrom(parameterType)) {
			return "\"" + DATE_FORMAT.format((Time) parameterValue) + "\"";
		} else if (java.util.Date.class.isAssignableFrom(parameterType)) {
			return "\"" + DATE_FORMAT.format((Date) parameterValue) + "\"";
		} else {
			return String.valueOf(parameterValue);
		}
	}
}