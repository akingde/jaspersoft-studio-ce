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
package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.model.ANode;

public class MGroupByColumn extends AMQueryObject<MSQLColumn> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MFromTable mfTable;
	private MSelectColumn msColumn;

	public MGroupByColumn(ANode parent, MSelectColumn msColumn) {
		this(parent, msColumn.getValue(), msColumn.getMFromTable(), -1);
	}

	public MGroupByColumn(ANode parent, MSelectColumn msColumn, int index) {
		this(parent, msColumn.getValue(), msColumn.getMFromTable(), index);
		this.msColumn = msColumn;
	}

	public MGroupByColumn(ANode parent, MSQLColumn value, MFromTable mfTable) {
		this(parent, value, mfTable, -1);
	}

	public MGroupByColumn(ANode parent, MSQLColumn value, MFromTable mfTable, int index) {
		super(parent, value, null, index);
		this.mfTable = mfTable;
	}

	public MSelectColumn getMSelectColumn() {
		return msColumn;
	}

	public void setMSelectColumn(MSelectColumn msColumn) {
		this.msColumn = msColumn;
	}

	public MFromTable getMFromTable() {
		return mfTable;
	}

	public void setMFromTable(MFromTable mfTable) {
		this.mfTable = mfTable;
	}

	@Override
	public String getDisplayText() {
		return getValue().toSQLString();
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString ss = new StyledString();
		if (msColumn == null || msColumn.getAlias() == null) {
			if (mfTable.getAlias() != null)
				ss.append(mfTable.getAlias());
			else
				ss.append(mfTable.getValue().toSQLString());
			ss.append("." + getValue().getDisplayText());
		} else
			ss.append(msColumn.getAlias());
		return ss;
	}

	@Override
	public String toSQLString() {
		StringBuffer ss = new StringBuffer();
		if (msColumn == null || msColumn.getAlias() == null) {
			if (mfTable.getAlias() != null)
				ss.append(mfTable.getAlias());
			else
				ss.append(mfTable.getValue().toSQLString());
			ss.append("." + getValue().getDisplayText());
		} else
			ss.append(msColumn.getAlias());
		return isFirst() ? ss.toString() : ",\n\t" + ss.toString();
	}

}
