package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public abstract class AMQueryAliased<T> extends AMQueryObject<T> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMQueryAliased(ANode parent, T value, String image, int index) {
		super(parent, value, image, index);
	}

	public AMQueryAliased(ANode parent, T value, String image) {
		super(parent, value, image);
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

	protected String addAlias() {
		if (alias != null && !alias.trim().isEmpty())
			return aliasKeyword + alias;
		return "";
	}

	protected void addAlias(StyledString dt) {
		if (alias != null && !alias.trim().isEmpty()) {
			dt.append(aliasKeyword, FontUtils.KEYWORDS_STYLER);
			dt.append(alias);
		}
	}

	@Override
	public String getDisplayText() {
		return super.getDisplayText() + addAlias();
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString dt = new StyledString(super.getDisplayText());
		addAlias(dt);
		return dt;
	}
}
