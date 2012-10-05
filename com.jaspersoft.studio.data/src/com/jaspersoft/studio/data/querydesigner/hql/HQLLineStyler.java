package com.jaspersoft.studio.data.querydesigner.hql;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;

/**
 * Line style for HQL language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class HQLLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new HQLScanner();
	}
}