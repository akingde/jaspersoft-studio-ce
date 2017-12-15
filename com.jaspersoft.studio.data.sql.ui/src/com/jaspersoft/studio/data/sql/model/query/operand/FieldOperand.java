/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import com.jaspersoft.studio.data.sql.model.MSQLRoot;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

public class FieldOperand extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MSQLColumn column;
	private MFromTable fromTable;

	public FieldOperand(MSQLColumn value, MFromTable fromTable, AMExpression<?> mexpr) {
		super(mexpr);
		setValue(value, fromTable);
	}

	public MFromTable getFromTable() {
		return fromTable;
	}

	public void setFromTable(MFromTable fromTable) {
		this.fromTable = fromTable;
	}

	public MSQLColumn getMColumn() {
		return column;
	}

	public void setColumn(MSQLColumn column) {
		this.column = column;
	}

	public void setValue(MSQLColumn value, MFromTable fromTable) {
		this.column = value;
		this.fromTable = fromTable;
	}

	@Override
	public String toSQLString() {
		MSQLRoot mroot = expression.getRoot();
		String IQ = mroot == null ? "": mroot.getIdentifierQuote();
		boolean onlyException = mroot == null ? false : mroot.isQuoteExceptions();
		if (column == null)
			return "___";
		StringBuffer ss = new StringBuffer();
		if (fromTable.getAlias() != null && !fromTable.getAlias().trim().isEmpty())
			ss.append(fromTable.getAlias());
		else
			ss.append(fromTable.getValue().toSQLString());
		ss.append("." + Misc.quote(column.getDisplayText(), IQ, onlyException));
		return ss.toString();
	}

	@Override
	public String toXString() {
		return toSQLString();
	}

}
