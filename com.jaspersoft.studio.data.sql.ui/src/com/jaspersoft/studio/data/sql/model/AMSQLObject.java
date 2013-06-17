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
package com.jaspersoft.studio.data.sql.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.IQueryString;
import com.jaspersoft.studio.model.ANode;

public class AMSQLObject extends MDBObjects implements IQueryString {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMSQLObject(ANode parent, String value, String image) {
		super(parent, value, image);
	}

	@Override
	public String getToolTip() {
		String name = toSQLString();
		if (tooltip != null)
			name += "\n" + tooltip;
		return name;
	}

	public String toSQLString() {
		String str = getValue();
		ANode p = getParent();
		while (p != null) {
			if (p instanceof AMSQLObject)
				return ((AMSQLObject) p).toSQLString() + "." + getValue();
			p = p.getParent();
		}
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof AMSQLObject && ((AMSQLObject) obj).toSQLString().equals(toSQLString());
	}

	public int hashCode() {
		return toSQLString().hashCode();
	};
}
