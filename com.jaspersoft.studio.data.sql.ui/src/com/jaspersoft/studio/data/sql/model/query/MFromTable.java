package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.MQueryObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MFromTable extends MQueryObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFromTable(ANode parent, MSqlTable value) {
		super(parent, value, null);
	}

	public MFromTable(ANode parent, MSqlTable value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public MSqlTable getValue() {
		return (MSqlTable) super.getValue();
	}

	private String alias;
	private String aliasKeyword = AMKeyword.ALIAS_KEYWORD;

	public void setAliasKeyword(String aliasKeyword) {
		this.aliasKeyword = aliasKeyword;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAliasKeyword() {
		return aliasKeyword;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public String getToolTip() {
		MSqlTable mc = getValue();
		String tooltip = mc.toSQLString();
		if (alias != null && !alias.trim().isEmpty())
			tooltip += aliasKeyword + alias;
		if (getValue().getRemarks() != null)
			tooltip += "\n" + mc.getRemarks();
		return tooltip;
	}

	@Override
	public String getDisplayText() {
		String dt = super.getDisplayText();
		if (alias != null && !alias.trim().isEmpty())
			dt += aliasKeyword + alias;
		return dt;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString dt = new StyledString(super.getDisplayText());
		if (alias != null && !alias.trim().isEmpty()) {
			dt.append(aliasKeyword, FontUtils.KEYWORDS_STYLER);
			dt.append(alias);
		}
		return dt;
	}

}
