package com.jaspersoft.studio.data.querydesigner.ejbql;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;

/**
 * Line style for EJB-QL language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class EJBQLLineStyler extends SQLLineStyler {
	@Override
	protected SQLScanner getSQLBasedScanner() {
		return new EJBQLScanner();
	}
}