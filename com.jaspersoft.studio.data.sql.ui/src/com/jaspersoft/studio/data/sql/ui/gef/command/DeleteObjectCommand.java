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
