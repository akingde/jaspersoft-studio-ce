package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class DeleteCommand extends Command {
	private MFromTable node;
	private ANode parent;
	private Map<ANode, ANode> map;
	private Map<ANode, Integer> mapIndex;

	public DeleteCommand(MFromTable node) {
		this.node = node;
		this.parent = node.getParent();
	}

	@Override
	public void execute() {
		if (map == null) {
			map = new HashMap<ANode, ANode>();
			mapIndex = new HashMap<ANode, Integer>();
			map.put(node, parent);
			mapIndex.put(node, node.getParent().getChildren().indexOf(node));
			doDeleteMore(parent, node);
		}
		for (ANode mft : map.keySet())
			mft.setParent(null, -1);
	}

	public void undo() {
		for (ANode key : map.keySet())
			key.setParent(map.get(key), mapIndex.get(key));
	}

	protected void doDeleteMore(ANode parent, MFromTable todel) {
		if (parent.getRoot() != null)
			for (INode n : parent.getRoot().getChildren()) {
				List<ANode> toRemove = new ArrayList<ANode>();
				if (n instanceof MSelect) {
					for (INode gb : n.getChildren()) {
						MSelectColumn gbc = (MSelectColumn) gb;
						if (gbc.getMFromTable() != null && gbc.getMFromTable().equals(todel))
							toRemove.add(gbc);
					}
					((MSelect) n).removeChildren(toRemove);
				} else if (n instanceof MGroupBy) {
					for (INode gb : n.getChildren()) {
						MGroupByColumn gbc = (MGroupByColumn) gb;
						if (gbc.getMFromTable() != null && gbc.getMFromTable().equals(todel))
							toRemove.add(gbc);
					}

					((MGroupBy) n).removeChildren(toRemove);
				} else if (n instanceof MOrderBy) {
					for (INode gb : n.getChildren()) {
						if (gb instanceof MOrderByColumn) {
							MOrderByColumn gbc = (MOrderByColumn) gb;
							if (gbc.getMFromTable() != null && gbc.getMFromTable().equals(todel))
								toRemove.add(gbc);
						}
					}
					((MOrderBy) n).removeChildren(toRemove);
				}
				for (ANode rem : toRemove) {
					map.put(rem, rem.getParent());
					mapIndex.put(rem, rem.getParent().getChildren().indexOf(rem));
				}
			}
	}
}
