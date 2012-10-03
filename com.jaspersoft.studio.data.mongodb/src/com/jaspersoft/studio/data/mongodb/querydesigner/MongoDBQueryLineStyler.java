package com.jaspersoft.studio.data.mongodb.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;

/**
 * Line style for Mongo Query Language.
 * 
 * FIXME - This class is a placeholder. Query language for MongoDB is based on JSON
 * so we need to decide how to implement query designer for it and its syntax highlighting support.
 */
public class MongoDBQueryLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new MongoDBQueryScanner();
	}
}