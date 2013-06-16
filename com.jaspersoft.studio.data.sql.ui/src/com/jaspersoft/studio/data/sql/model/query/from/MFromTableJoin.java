package com.jaspersoft.studio.data.sql.model.query.from;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MFromTableJoin extends MFromTable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFromTableJoin(MFromTable parent, MSqlTable value) {
		this(parent, value, -1);
	}

	public MFromTableJoin(MFromTable parent, MSqlTable value, int index) {
		super(parent, value, index);
		tableJoin = new TableJoin(this, parent);
	}

	@Override
	public void setParent(ANode newparent, int newIndex) {
		if (newparent == null) {
			tableJoin.getFromTable().removeTableJoin(tableJoin);
			tableJoin = null;
		}
		super.setParent(newparent, newIndex);
	}

	@Override
	public MSqlTable getValue() {
		return (MSqlTable) super.getValue();
	}

	private String join = AMKeyword.INNER_JOIN;

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	@Override
	public String getToolTip() {
		return join + " " + super.getToolTip() + " ON ";
	}

	@Override
	public String getDisplayText() {
		return join + " " + super.getDisplayText() + " ON ";
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString dt = new StyledString(join + " ", FontUtils.KEYWORDS_STYLER);
		String tbltext = super.getDisplayText();
		int ind = (join + " " + tbltext).indexOf(" AS ");
		dt.append(tbltext);
		if (ind >= 0)
			dt.setStyle(ind, " AS ".length(), FontUtils.KEYWORDS_STYLER);
		dt.append(" ON ", FontUtils.KEYWORDS_STYLER);
		return dt;
	}

	private TableJoin tableJoin;

	public TableJoin getTableJoin() {
		return tableJoin;
	}
}
