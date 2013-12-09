package com.jaspersoft.studio.data.cassandra.cql3.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLScanner;

/**
 * Line style for CQL3 language.
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3QLLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new CassandraCQL3QLScanner();
	}
}
