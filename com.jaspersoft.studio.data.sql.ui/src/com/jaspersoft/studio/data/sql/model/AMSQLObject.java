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

import com.jaspersoft.studio.data.sql.model.metadata.MSqlSchema;
import com.jaspersoft.studio.data.sql.model.query.IQueryString;
import com.jaspersoft.studio.data.sql.text2model.ConvertUtil;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.Misc;

public class AMSQLObject extends MDBObjects implements IQueryString {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMSQLObject(ANode parent, String value, String image) {
		super(parent, value, image);
	}

	@Override
	public String getToolTip() {
		String name = ConvertUtil.cleanDbNameFull(toSQLString());
		if (tooltip != null)
			name += tooltip;
		return name;
	}

	public String toSQLString() {
		String str = getValue();
		ANode p = getParent();
		String IQ = getRoot().getIdentifierQuote();
		while (p != null) {
			if (p instanceof AMSQLObject) {
				if (p instanceof MSqlSchema) {
					if ((((MSqlSchema) p).isCurrent()))
						return IQ + getValue() + IQ;
				}
				String s = ((AMSQLObject) p).toSQLString();
				if (Misc.isNullOrEmpty(s))
					return getValue();
				return s + "." + IQ + getValue() + IQ;
			}
			p = p.getParent();
		}
		if (this instanceof MSqlSchema)
			return IQ + getValue() + IQ;
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
