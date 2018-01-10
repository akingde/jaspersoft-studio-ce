/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.cassandra.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLScanner;

/**
 * Line style for Hive-QL language.
 * 
 */
public class CassandraQLLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new CassandraQLScanner();
	}
}
