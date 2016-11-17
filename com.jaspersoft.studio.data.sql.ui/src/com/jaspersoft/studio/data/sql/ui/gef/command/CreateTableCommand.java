/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class CreateTableCommand extends ACommand {
	private MSqlTable node;
	private MFrom mfrom;
	private int index;
	private MFromTable mft;

	public CreateTableCommand(MSqlTable node, MFrom mfrom, int index) {
		this.node = node;
		this.mfrom = mfrom;
		this.index = index;
	}

	@Override
	public void execute() {
		super.execute();
		mft = new MFromTable(mfrom, node, index);
		undoRemove.add(mft);
	}

	public MFromTable getResultTable() {
		return mft;
	}

	@Override
	protected void firePropertyChange() {
	}
}
