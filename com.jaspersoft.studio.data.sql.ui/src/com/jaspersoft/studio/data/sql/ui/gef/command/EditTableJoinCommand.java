/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class EditTableJoinCommand extends ACommand {
	private MFromTable tbl;
	private String oldAlias;
	private String alias;
	private String oldKey;
	private String key;

	public EditTableJoinCommand(MFromTable tbl, String alias, String key) {
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
