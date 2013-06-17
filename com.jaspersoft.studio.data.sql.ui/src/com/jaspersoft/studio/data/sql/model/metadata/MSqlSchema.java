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
package com.jaspersoft.studio.data.sql.model.metadata;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MSqlSchema extends AMSQLObject {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String tableCatalog;

	public MSqlSchema(ANode parent, String value, String tableCatalog) {
		super(parent, value, "icons/database.png");
		this.tableCatalog = tableCatalog;
	}

	public String getTableCatalog() {
		return tableCatalog;
	}

	private boolean isCurrent;

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	@Override
	public String getToolTip() {
		String tt = super.getToolTip();
		if (isCurrent)
			tt += " (CURRENT)";
		return tt;
	}

	@Override
	public String getDisplayText() {
		String dt = super.getDisplayText();
		if (isCurrent)
			dt += " (CURRENT)";
		return dt;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString dt = new StyledString(super.getDisplayText());
		if (isCurrent)
			dt.append(" (CURRENT)", FontUtils.FIELD_STYLER);
		return dt;
	}

}
