/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
