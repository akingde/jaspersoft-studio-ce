/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.ui.gef.command.SetSilentValuesCommand;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class LayoutAction extends Action {
	private SQLQueryDesigner designer;

	public LayoutAction(SQLQueryDesigner designer) {
		super(Messages.LayoutAction_0);
		this.designer = designer;
	}

	@Override
	public boolean isEnabled() {
		boolean b = super.isEnabled();
		if (b) {
			Boolean exists = new ModelVisitor<Boolean>(designer.getRoot()) {

				@Override
				public boolean visit(INode n) {
					if (n instanceof MFromTable) {
						setObject(Boolean.TRUE);
						stop();
					}
					return true;
				}

			}.getObject();
			return exists != null && exists;
		}
		return b;
	}

	@Override
	public void run() {
		final SetSilentValuesCommand c = new SetSilentValuesCommand(true);
		new ModelVisitor<Object>(designer.getRoot()) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MFromTable) {
					c.add((MFromTable) n, MFromTable.PROP_X, null);
					c.add((MFromTable) n, MFromTable.PROP_Y, null);
				}
				return true;
			}

		};
		if (!c.isEmpty())
			designer.getDiagram().getViewer().getEditDomain().getCommandStack()
					.execute(c);
	}

}
