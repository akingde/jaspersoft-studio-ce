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
package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;

public abstract class AAction extends Action {
	protected Object[] selection;
	protected SQLQueryDesigner designer;

	public AAction(String text, SQLQueryDesigner designer) {
		super(text);
		this.designer = designer;
	}

	public boolean calculateEnabled(ISelection iselection) {
		List<Object> lst = new ArrayList<Object>();
		if (iselection instanceof TreeSelection) {
			for (TreePath tp : ((TreeSelection) iselection).getPaths())
				lst.add(tp.getLastSegment());
		}
		return calculateEnabled(lst.toArray());
	}

	public boolean calculateEnabled(Object[] selection) {
		this.selection = selection;
		return true;
	}

	protected void selectInTree(Object sel) {
		TreeViewer tv = designer.getOutline().getTreeViewer();
		tv.refresh(true);
		tv.setSelection(new TreeSelection(new TreePath(new Object[] { sel })));
		tv.reveal(sel);
	}
}
