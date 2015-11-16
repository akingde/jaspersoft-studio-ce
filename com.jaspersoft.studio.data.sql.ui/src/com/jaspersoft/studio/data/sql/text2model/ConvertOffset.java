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

import com.jaspersoft.studio.data.sql.Offset;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.impl.OffsetImpl;
import com.jaspersoft.studio.data.sql.model.MSQLRoot;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;

public class ConvertOffset {
	public static void convertOffset(SQLQueryDesigner designer, Offset cols) {
		if (cols == null)
			return;
		if (cols instanceof OffsetImpl) {
			String lim = "OFFSET " + cols.getOffset();
			MSQLRoot mroot = designer.getRoot();
			new AMKeyword(mroot, lim, null);
		}
	}

}
