package com.jaspersoft.studio.data.cassandra.cql3.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.SimpleSQLQueryDesigner;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

/**
 * Simple query designer for CQL3 that provides syntax highlighting.
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3QLQueryDesigner extends SimpleSQLQueryDesigner {

	@Override
	protected SQLLineStyler getSQLBasedLineStyler() {
		return new CassandraCQL3QLLineStyler();
	}

	@Override
	public String getContextHelpId() {
		return ContextHelpIDs.PREFIX.concat("query_CQL3"); //$NON-NLS-1$
	}
}
