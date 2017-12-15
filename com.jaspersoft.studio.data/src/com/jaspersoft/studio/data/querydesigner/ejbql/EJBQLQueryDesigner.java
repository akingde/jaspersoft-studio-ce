/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.ejbql;

import com.jaspersoft.studio.data.querydesigner.sql.SimpleSQLQueryDesigner;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;

/**
 * Simple query designer for EJB-QL that provides syntax highlighting.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class EJBQLQueryDesigner extends SimpleSQLQueryDesigner {

	@Override
	protected SQLLineStyler getSQLBasedLineStyler() {
		return new EJBQLLineStyler();
	}
	
}

