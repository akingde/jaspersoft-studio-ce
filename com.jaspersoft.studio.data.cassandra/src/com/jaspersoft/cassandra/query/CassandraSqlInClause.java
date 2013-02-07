package com.jaspersoft.cassandra.query;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraSqlInClause extends CassandraAbstractInClause {
	protected static final String OPERATOR_IN = "IN";

	private static CassandraSqlInClause instance = new CassandraSqlInClause();

	private CassandraSqlInClause() {
	}

	public static CassandraSqlInClause getInstance() {
		return instance;
	}

	@Override
	protected void appendInOperator(StringBuffer stringBuffer) {
		stringBuffer.append(OPERATOR_IN);
	}
}