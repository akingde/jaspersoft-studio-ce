/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class EditTableCommand extends ACommand {
	private MFromTable tbl;
	private String oldAlias;
	private String alias;
	private String oldKey;
	private String key;

	public EditTableCommand(MFromTable tbl, String alias, String key) {
		this.tbl = tbl;
		this.alias = alias;
		this.key = key;
	}

	@Override
	public void execute() {
		super.execute();
		oldAlias = tbl.getAlias();
		oldKey = tbl.getAliasKeyword();

		tbl.setAlias(alias);
		tbl.setAliasKeyword(key);
	}

	@Override
	public void undo() {
		super.undo();

		tbl.setAlias(oldAlias);
		tbl.setAliasKeyword(oldKey);
	}
}
