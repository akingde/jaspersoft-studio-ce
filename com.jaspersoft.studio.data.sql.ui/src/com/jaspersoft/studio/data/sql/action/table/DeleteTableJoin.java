/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.action.table;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.MSQLRoot;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoinDetail;
import com.jaspersoft.studio.data.sql.ui.gef.command.DeleteObjectCommand;
import com.jaspersoft.studio.data.sql.ui.gef.command.DeleteTableJoinCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class DeleteTableJoin extends AAction {
	private SQLQueryDesigner designer;

	public DeleteTableJoin(SQLQueryDesigner designer, TreeViewer treeViewer) {
		super(Messages.DeleteTableJoin_0, treeViewer);
		this.designer = designer;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && ((selection[0] instanceof TableJoinDetail)
				|| (selection[0] instanceof ANode && isColumn((ANode) selection[0]))
				|| (selection[0] instanceof EditPart && isColumn((ANode) ((EditPart) selection[0]).getModel())));
	}

	protected boolean isColumn(ANode element) {
		return element instanceof MFromTableJoin;
	}

	@Override
	public void run() {
		if (UIUtils.showConfirmation(Messages.DeleteTableJoin_1, Messages.DeleteTableJoin_2)) {
			Command c = null;
			if (selection[0] instanceof TableJoinDetail) {
				TableJoinDetail tjd = (TableJoinDetail) selection[0];
				Integer cnt = new ModelVisitor<Integer>(tjd.getMFromTableJoin()) {
					private int count = 0;

					@Override
					public boolean visit(INode n) {
						if (count > 1)
							stop();
						if (n instanceof MExpression)
							setObject(++count);
						return true;
					}

				}.getObject();
				if (cnt != null && cnt == 1)
					c = new DeleteTableJoinCommand(selection);
				else {
					List<ANode> todel = new ArrayList<ANode>();
					todel.add(tjd.getExpr());
					c = new DeleteObjectCommand(todel);
				}
				selection[0] = tjd.getMFromTableJoin();
			} else
				c = new DeleteTableJoinCommand(selection);
			if (c != null) {
				MSQLRoot r = ((MFromTableJoin) selection[0]).getRoot();
				designer.getDiagram().getViewer().getEditDomain().getCommandStack().execute(c);
				r.getPropertyChangeSupport().firePropertyChange("wrong", true, false);
			}
		}
	}
}
