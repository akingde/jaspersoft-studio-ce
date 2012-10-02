package com.jaspersoft.studio.data.hive.querydesigner;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;

/**
 * Line style for HQL language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class HiveQLLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new HiveQLScanner();
	}
}