package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.MQueryObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MSelectColumn extends MQueryObjects {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelectColumn(ANode parent, MColumn value) {
		super(parent, value, null);
	}

	public MSelectColumn(ANode parent, MColumn value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public MColumn getValue() {
		return (MColumn) super.getValue();
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
		MColumn mc = getValue();
		String tooltip = mc.toSQLString();
		if (alias != null && !alias.trim().isEmpty())
			tooltip += aliasKeyword + alias;
		tooltip += "\n" + mc.getTypeName();
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

	@Override
	public String toSQLString() {
		return getDisplayText();
	}
}