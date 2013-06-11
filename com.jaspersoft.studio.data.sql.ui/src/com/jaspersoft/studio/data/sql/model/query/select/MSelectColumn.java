package com.jaspersoft.studio.data.sql.model.query.select;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.model.ANode;

public class MSelectColumn extends AMQueryAliased<MColumn> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MFromTable mfTable;

	public MSelectColumn(ANode parent, MColumn value, MFromTable mfTable) {
		this(parent, value, mfTable, -1);
	}

	public MSelectColumn(ANode parent, MColumn value, MFromTable mfTable, int index) {
		super(parent, value, null, index);
		this.mfTable = mfTable;
	}

	public MFromTable getMFromTable() {
		return mfTable;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString ss = new StyledString();
		if (mfTable.getAlias() != null && !mfTable.getAlias().trim().isEmpty())
			ss.append(mfTable.getAlias());
		else
			ss.append(mfTable.getValue().toSQLString());
		ss.append("." + getValue().getDisplayText());
		addAlias(ss);
		return ss;
	}

	@Override
	public String getToolTip() {
		MColumn mc = getValue();
		String tooltip = mc.toSQLString();
		tooltip += addAlias();
		tooltip += "\n" + mc.getTypeName();
		if (getValue().getRemarks() != null)
			tooltip += "\n" + mc.getRemarks();
		return tooltip;
	}

	@Override
	public String toSQLString() {
		StringBuffer ss = new StringBuffer();
		if (mfTable.getAlias() != null && !mfTable.getAlias().trim().isEmpty())
			ss.append(mfTable.getAlias());
		else
			ss.append(mfTable.getValue().toSQLString());
		ss.append("." + getValue().getDisplayText());
		ss.append(addAlias());

		return isFirst() ? ss.toString() : ",\n\t" + ss.toString();
	}

}