/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.table;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class CreateSubQueryTable extends AAction {

	public CreateSubQueryTable(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.CreateSubQueryTable_0, treeViewer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection == null
				|| (selection != null && selection.length == 1 && isInFrom(selection[0]));
	}

	public static boolean isInFrom(Object element) {
		if (element instanceof MFromTable
				&& ((MFromTable) element).getValue() instanceof MQueryTable)
			return false;
		return element instanceof MFrom
				|| (element instanceof MFromTable && ((MFromTable) element)
						.getParent() instanceof MFrom);
	}

	@Override
	public void run() {
		Object sel = selection[0];
		MFrom mfrom = null;
		int index = 0;
		if (sel instanceof MFrom)
			mfrom = (MFrom) sel;
		else if (sel instanceof ANode
				&& ((ANode) sel).getParent() instanceof MFrom) {
			mfrom = (MFrom) ((ANode) sel).getParent();
			index = mfrom.getChildren().indexOf((MFromTable) sel) + 1;
		}

		MQueryTable mtable = new MQueryTable(null);
		MFromTable msq = new MFromTable(mfrom, mtable, index);
		setAlias(msq, mfrom);
		mtable.setSubquery(Util.createSelect(msq));

		selectInTree(msq);
		treeViewer.expandToLevel(msq, 1);
	}

	private void setAlias(MFromTable msq, MFrom mfrom) {
		String alias = "sq";//$NON-NLS-1$
		final Set<String> aliases = new HashSet<String>();
		new ModelVisitor<String>(mfrom.getRoot()) {
			@Override
			public boolean visit(INode n) {
				if (n instanceof MFromTable)
					aliases.add(((MFromTable) n).getAlias());
				return true;
			}
		};
		String tmp = alias;
		int i = 1;
		while (aliases.contains(tmp))
			tmp = alias + "_" + i++;
		msq.setAlias(tmp);
	}
}
