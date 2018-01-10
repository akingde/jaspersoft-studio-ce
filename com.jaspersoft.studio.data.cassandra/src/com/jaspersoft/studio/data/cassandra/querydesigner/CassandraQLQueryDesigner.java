/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.cassandra.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.SimpleSQLQueryDesigner;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

/**
 * Simple query designer for Hive-QL that provides syntax highlighting.
 * 
 */
public class CassandraQLQueryDesigner extends SimpleSQLQueryDesigner {

	@Override
	protected SQLLineStyler getSQLBasedLineStyler() {
		return new CassandraQLLineStyler();
	}

	@Override
	public String getContextHelpId() {
		return ContextHelpIDs.PREFIX.concat("query_CQL");
	}
}
