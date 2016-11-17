/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.List;

import com.jaspersoft.studio.model.ANode;

public class DeleteObjectCommand extends ACommand {
	private List<ANode> lst;

	public DeleteObjectCommand(List<ANode> lst) {
		this.lst = lst;
	}

	@Override
	public void execute() {
		super.execute();
		for (ANode ftbl : lst)
			reparent(ftbl, null);
	}

}
