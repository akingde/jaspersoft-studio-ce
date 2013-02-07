package com.jaspersoft.cassandra.query;

import java.util.Collection;

import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.query.JRClauseFunction;
import net.sf.jasperreports.engine.query.JRClauseTokens;
import net.sf.jasperreports.engine.query.JRQueryClauseContext;

/**
 * 
 * @author Eric Diaz
 * 
 */
public abstract class CassandraAbstractInClause implements JRClauseFunction {
	protected static final int POSITION_DB_COLUMN = 1;

	protected static final int POSITION_PARAMETER = 2;

	protected static final String CLAUSE_TRUISM = "0 = 0";

	protected CassandraAbstractInClause() {
	}

	public void apply(JRClauseTokens clauseTokens, JRQueryClauseContext queryContext) {
		String column = clauseTokens.getToken(POSITION_DB_COLUMN);
		String parameterName = clauseTokens.getToken(POSITION_PARAMETER);

		if (column == null) {
			throw new JRRuntimeException("CQL IN clause missing DB column token");
		}

		if (parameterName == null) {
			throw new JRRuntimeException("CQL IN clause missing parameter token");
		}

		StringBuffer stringBuffer = queryContext.queryBuffer();
		Object parameterValue = queryContext.getValueParameter(parameterName).getValue();
		if (parameterValue == null) {
			handleNoValues(queryContext);
		} else {
			stringBuffer.append(column);
			stringBuffer.append(' ');
			appendInOperator(stringBuffer);
			stringBuffer.append(' ');
			stringBuffer.append('(');
			appendValues(parameterValue, parameterName, stringBuffer);
			stringBuffer.append(')');
		}
	}

	protected void handleNoValues(JRQueryClauseContext queryContext) {
		queryContext.queryBuffer().append(CLAUSE_TRUISM);
	}

	protected void appendValues(Object parameterValue, String parameterName, StringBuffer buffer) {
		if (parameterValue.getClass().isArray()) {
			for (Object value : (Object[]) parameterValue) {
				buffer.append(CassandraQueryExecuter.getParameterReplacement(value.getClass(), value));
				buffer.append(",");
			}
		} else if (parameterValue instanceof Collection<?>) {
			for (Object value : (Collection<?>) parameterValue) {
				buffer.append(CassandraQueryExecuter.getParameterReplacement(value.getClass(), value));
				buffer.append(",");
			}
		} else {
			throw new JRRuntimeException("Invalid type " + parameterValue.getClass().getName() + " for parameter "
					+ parameterName + " used in an IN clause; the value must be an array or a collection.");
		}
		if (buffer.charAt(buffer.length() - 1) == ',') {
			buffer.deleteCharAt(buffer.length() - 1);
		}
	}

	protected abstract void appendInOperator(StringBuffer stringBuffer);
}