package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class FieldOperand extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MColumn column;
	private MFromTable fromTable;

	public FieldOperand(MColumn value, MFromTable fromTable, MExpression mexpr) {
		super(mexpr);
		setValue(value, fromTable);
	}

	public MFromTable getFromTable() {
		return fromTable;
	}

	public void setFromTable(MFromTable fromTable) {
		this.fromTable = fromTable;
	}

	public MColumn getMColumn() {
		return column;
	}

	public void setValue(MColumn value, MFromTable fromTable) {
		this.column = value;
		this.fromTable = fromTable;
	}

	@Override
	public String toSQLString() {
		if (column == null)
			return "";
		StringBuffer ss = new StringBuffer();
		if (fromTable.getAlias() != null && !fromTable.getAlias().trim().isEmpty())
			ss.append(fromTable.getAlias());
		else
			ss.append(fromTable.getValue().toSQLString());
		ss.append("." + column.getDisplayText());
		return ss.toString();
	}

}
