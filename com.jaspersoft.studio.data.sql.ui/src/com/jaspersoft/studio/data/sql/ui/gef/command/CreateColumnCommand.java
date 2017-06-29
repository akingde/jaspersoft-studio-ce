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

import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;

public class CreateColumnCommand extends ACommand {
	private MSQLColumn node;
	private MSelect select;
	private int index;
	private MSelectColumn mft;
	private MFromTable mfTable;

	public CreateColumnCommand(MSQLColumn node, MSelect select, int index,
			MFromTable mfTable) {
		this.node = node;
		this.select = select;
		this.index = index;
		this.mfTable = mfTable;
	}

	@Override
	public void execute() {
		super.execute();
		mft = new MSelectColumn(select, node, mfTable, index);
		undoRemove.add(mft);
	}

	public MSQLColumn getColumn() {
		return node;
	}

	public MSelectColumn getResultColumn() {
		return mft;
	}

	@Override
	protected void firePropertyChange() {
	}
}
