/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.text2model;

import com.jaspersoft.studio.data.sql.Limit;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.impl.LimitImpl;
import com.jaspersoft.studio.data.sql.model.MSQLRoot;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;

public class ConvertLimit {
	public static void convertLimit(SQLQueryDesigner designer, Limit cols) {
		if (cols == null)
			return;
		String lim = "LIMIT ";
		if (cols instanceof LimitImpl) {
			MSQLRoot mroot = designer.getRoot();
			if (cols.getL1() > 0) {
				lim += cols.getL1();
				if (cols.getL2() != null)
					lim += ", " + cols.getL2();
			} else
				lim += "ANY";
			new AMKeyword(mroot, lim, null);
		}
	}
}
