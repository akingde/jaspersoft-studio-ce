/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql;

import com.jaspersoft.studio.data.sql.model.query.IQueryString;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class QueryWriter {
	public static String writeQuery(MRoot root) {
		final StringBuffer sb = new StringBuffer();
		new ModelVisitor<INode>(root) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof IQueryString)
					sb.append(((IQueryString) n).toSQLString());
				return true;
			}

		};
		return sb.toString();
	}
}
