package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.model.ANode;

public class MGroupByColumn extends AMQueryObject<MColumn> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MFromTable mfTable;

	public MGroupByColumn(ANode parent, MColumn value, MFromTable mfTable) {
		this(parent, value, mfTable, -1);
	}

	public MGroupByColumn(ANode parent, MColumn value, MFromTable mfTable, int index) {
		super(parent, value, null, index);
		this.mfTable = mfTable;
	}

	public MFromTable getMFromTable() {
		return mfTable;
	}

	@Override
	public String getDisplayText() {
		return getValue().toSQLString();
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString ss = new StyledString();
		if (mfTable.getAlias() != null && !mfTable.getAlias().trim().isEmpty())
			ss.append(mfTable.getAlias());
		else
			ss.append(mfTable.getValue().toSQLString());
		ss.append("." + getValue().getDisplayText());
		return ss;
	}

	@Override
	public String toSQLString() {
		StringBuffer ss = new StringBuffer();
		if (mfTable.getAlias() != null && !mfTable.getAlias().trim().isEmpty())
			ss.append(mfTable.getAlias());
		else
			ss.append(mfTable.getValue().toSQLString());
		ss.append("." + getValue().getDisplayText());
		return isFirst() ? ss.toString() : ",\n\t" + ss.toString();
	}

}