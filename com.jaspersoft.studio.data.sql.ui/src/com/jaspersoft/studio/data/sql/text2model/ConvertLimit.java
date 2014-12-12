/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
					lim += ", " + cols.getL2().getInteger();
			} else
				lim += "ANY";
			new AMKeyword(mroot, lim, null);
		}
	}
}
