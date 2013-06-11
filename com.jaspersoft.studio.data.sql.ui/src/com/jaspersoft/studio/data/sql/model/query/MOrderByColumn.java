package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MOrderByColumn extends AMQueryObject<MColumn> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MFromTable mfTable;

	public MOrderByColumn(ANode parent, MColumn value, MFromTable mfTable) {
		this(parent, value, mfTable, -1);
	}

	public MOrderByColumn(ANode parent, MColumn value, MFromTable mfTable, int index) {
		super(parent, value, null, index);
		this.mfTable = mfTable;
	}

	public MFromTable getMFromTable() {
		return mfTable;
	}

	@Override
	public String getDisplayText() {
		String dt = getValue().toSQLString();
		dt += addDirection();
		return dt;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString ss = new StyledString();
		if (mfTable.getAlias() != null && !mfTable.getAlias().trim().isEmpty())
			ss.append(mfTable.getAlias());
		else
			ss.append(mfTable.getValue().toSQLString());
		ss.append("." + getValue().getDisplayText());
		ss.append(addDirection(), FontUtils.KEYWORDS_STYLER);
		return ss;
	}

	public String addDirection() {
		return isDesc ? AMKeyword.DESCENDING_KEYWORD : AMKeyword.ASCENDING_KEYWORD;
	}

	@Override
	public String toSQLString() {
		StringBuffer ss = new StringBuffer();
		if (mfTable.getAlias() != null && !mfTable.getAlias().trim().isEmpty())
			ss.append(mfTable.getAlias());
		else
			ss.append(mfTable.getValue().toSQLString());
		ss.append("." + getValue().getDisplayText());
		ss.append(addDirection());
		return isFirst() ? ss.toString() : ",\n\t" + ss.toString();
	}

	private boolean isDesc = true;

	public boolean isDesc() {
		return isDesc;
	}

	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
}