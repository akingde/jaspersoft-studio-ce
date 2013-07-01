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

import com.jaspersoft.studio.data.querydesigner.sql.text.SQLScanner;
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
		if (alias != null && alias.trim().isEmpty())
			alias = null;
		this.alias = alias;
	}

	public String getAliasKeyword() {
		return aliasKeyword;
	}

	public String getAlias() {
		return alias;
	}

	public String addAlias() {
		if (alias != null && !alias.trim().isEmpty()) {
			return aliasKeyword + prepareAlias();
		}
		return "";
	}

	protected String prepareAlias() {
		String al = alias.trim();
		if (al.contains(" ") || SQLScanner.SQL_KEYWORDS.contains(alias))
			al = "'" + al + "'";
		return al;
	}

	public void addAlias(StyledString dt) {
		if (alias != null && !alias.trim().isEmpty()) {
			dt.append(aliasKeyword, FontUtils.KEYWORDS_STYLER);
			dt.append(prepareAlias());
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
