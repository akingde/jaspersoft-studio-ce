/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlSchema;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.IQueryString;
import com.jaspersoft.studio.data.sql.text2model.ConvertUtil;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

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
		if (str == null || str.isEmpty())
			return "";
		ANode p = getParent();
		MSQLRoot r = getRoot();
		if (r == null)
			return "(...)";
		else {
			String IQ = r.getIdentifierQuote();
			boolean onlyException = r.isQuoteExceptions();
			while (p != null) {
				if (p instanceof AMSQLObject) {
					if (p instanceof MSqlSchema && (((MSqlSchema) p).isCurrent()))
						return Misc.quote(getValue(), IQ, onlyException);
					String s = ((AMSQLObject) p).toSQLString();
					if (Misc.isNullOrEmpty(s))
						return Misc.quote(getValue(), IQ, onlyException);
					return s + "." + Misc.quote(getValue(), IQ, onlyException);
				}
				p = p.getParent();
			}
			if (this instanceof MSqlSchema)
				return Misc.quote(getValue(), IQ, onlyException);
			else if (this instanceof MSqlTable)
				return Misc.quote(getValue(), IQ, onlyException);
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
