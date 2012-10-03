package com.jaspersoft.studio.data.hive.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;

/**
 * Line style for Hive-QL language.
 * 
 */
public class HiveQLLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new HiveQLScanner();
	}
}