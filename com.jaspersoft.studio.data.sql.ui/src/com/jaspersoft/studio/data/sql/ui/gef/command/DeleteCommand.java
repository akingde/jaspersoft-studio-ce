/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.ISubQuery;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.prefs.SQLEditorPreferencesPage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class DeleteCommand extends Command {
	private MFromTable node;
	private ANode parent;
	private Map<ANode, ANode> mapDel;
	private Map<ANode, Integer> mapDelIndex;

	private Map<ANode, ANode> mapAdd;
	private Map<ANode, Integer> mapAddIndex;

	private String dropsubquery = SQLEditorPreferencesPage.ASK;

	public DeleteCommand(MFromTable node) {
		this.node = node;
		this.parent = node.getParent();
	}

	/**
	 * @param dropsubquery
	 *            the dropsubquery to set
	 */
	public void setDropSubquery(String dropsubquery) {
		this.dropsubquery = dropsubquery;
	}

	@Override
	public void execute() {
		if (mapDel == null) {
			mapDel = new HashMap<ANode, ANode>();
			mapDelIndex = new HashMap<ANode, Integer>();

			mapAdd = new HashMap<ANode, ANode>();
			mapAddIndex = new HashMap<ANode, Integer>();

			mapDel.put(node, parent);
			int indx = node.getParent().getChildren().indexOf(node);
			mapDelIndex.put(node, indx);
			if (!(node instanceof MFromTableJoin)
					&& !node.getChildren().isEmpty()) {
				int i = 0;
				for (INode n : node.getChildren()) {
					if (n instanceof MFromTable) {
						MFromTable mft = new MFromTable(null,
								((MFromTable) n).getValue());
						mft.setAlias(((MFromTable) n).getAlias());
						mft.setAliasKeyword(((MFromTable) n).getAliasKeyword());

						node.copyProperties((MFromTable) n);

						mapAdd.put(mft, node.getParent());
						mapAddIndex.put(mft, indx + i);
					}
					i++;
				}
			}
			doDeleteMore(parent, node);
		}
		for (ANode mft : mapDel.keySet())
			mft.setParent(null, -1);
		for (ANode mft : mapAdd.keySet())
			mft.setParent(mapAdd.get(mft), mapAddIndex.get(mft));
		parent.getRoot().getPropertyChangeSupport()
				.firePropertyChange("wrongvalue", true, false); //$NON-NLS-1$
	}

	public void undo() {
		for (ANode mft : mapAdd.keySet())
			mft.setParent(null, -1);
		for (ANode key : mapDel.keySet())
			key.setParent(mapDel.get(key), mapDelIndex.get(key));
		parent.getRoot().getPropertyChangeSupport()
				.firePropertyChange("wrongvalue", true, false); //$NON-NLS-1$
	}

	protected void doDeleteMore(ANode parent, MFromTable todel) {
		if (parent instanceof MFrom
				&& parent.getParent() != null
				&& (parent.getParent() instanceof ISubQuery || parent
						.getParent().getValue() instanceof MQueryTable)
				&& parent.getChildren().size() <= 1
				&& parent.getChildren().get(0) == todel) {
			dropColumns(parent.getParent(), todel);
			if (dropsubquery.equals(SQLEditorPreferencesPage.DROP)
					|| (dropsubquery.equals(SQLEditorPreferencesPage.ASK) && UIUtils
							.showConfirmation(Messages.DeleteCommand_2,
									Messages.DeleteCommand_3))) {
				ANode msq = parent.getParent();
				mapDel.put(msq, msq.getParent());
				mapDelIndex
						.put(msq, msq.getParent().getChildren().indexOf(msq));
			}
		}
		if (parent.getRoot() != null)
			dropColumns((ANode) parent.getRoot(), todel);
	}

	private void dropColumns(ANode parent, MFromTable todel) {
		for (INode n : parent.getChildren()) {
			List<ANode> toRemove = new ArrayList<ANode>();
			if (n instanceof MSelect) {
				for (INode gb : n.getChildren()) {
					if (gb instanceof MSelectColumn) {
						MSelectColumn gbc = (MSelectColumn) gb;
						if (gbc.getMFromTable() != null
								&& gbc.getMFromTable().equals(todel))
							toRemove.add(gbc);
					}
				}
				((MSelect) n).removeChildren(toRemove);
			} else if (n instanceof MGroupBy) {
				for (INode gb : n.getChildren()) {
					MGroupByColumn gbc = (MGroupByColumn) gb;
					if (gbc.getMFromTable() != null
							&& gbc.getMFromTable().equals(todel))
						toRemove.add(gbc);
				}

				((MGroupBy) n).removeChildren(toRemove);
			} else if (n instanceof MOrderBy) {
				for (INode gb : n.getChildren()) {
					if (gb instanceof MOrderByColumn) {
						MOrderByColumn gbc = (MOrderByColumn) gb;
						if (gbc.getMFromTable() != null
								&& gbc.getMFromTable().equals(todel))
							toRemove.add(gbc);
					}
				}
				((MOrderBy) n).removeChildren(toRemove);
			}
			for (ANode rem : toRemove) {
				ANode p = rem.getParent();
				if (p == null)
					continue;
				mapDel.put(rem, p);
				mapDelIndex.put(rem, p.getChildren().indexOf(rem));
			}
		}
	}
}
