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
