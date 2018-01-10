/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.hql;

import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLScanner;

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
