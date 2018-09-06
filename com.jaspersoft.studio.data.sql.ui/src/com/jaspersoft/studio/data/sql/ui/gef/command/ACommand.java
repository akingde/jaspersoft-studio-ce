/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.AMapElement;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.eclipse.util.KeyValue;

public abstract class ACommand extends Command {
	// nodes to remove in undo
	protected Set<ANode> undoRemove;
	// nodes to add back in undo
	protected Map<ANode, KeyValue<ANode, Integer>> undoAdd;
	protected Map<ANode, Map<String, Object>> undoProperties;
	protected List<Command> undoCmd;

	@Override
	public void execute() {
		if (undoRemove == null)
			undoRemove = new HashSet<>();
		if (undoAdd == null)
			undoAdd = new HashMap<>();
		if (undoProperties == null)
			undoProperties = new HashMap<>();
		if (undoCmd == null)
			undoCmd = new ArrayList<>();
	}

	protected void reparent(ANode n, ANode p) {
		ANode parent = n.getParent();
		if (parent != null)
			undoAdd.put(n, new KeyValue<ANode, Integer>(parent, parent.getChildren().indexOf(n)));
		if (p != null)
			undoRemove.add(n);
		n.setParent(p, -1);
	}

	private ANode root;

	private void findRoot() {
		root = null;
		for (ANode p : undoRemove) {
			if (root != null)
				return;
			root = (ANode) p.getRoot();
		}
		for (ANode p : undoAdd.keySet()) {
			if (root != null)
				return;
			root = (ANode) p.getRoot();
		}
		for (ANode p : undoProperties.keySet()) {
			if (root != null)
				return;
			root = (ANode) p.getRoot();
		}
	}

	@Override
	public void undo() {
		findRoot();
		for (Command c : undoCmd)
			c.undo();
		for (ANode p : undoRemove)
			p.setParent(null, -1);
		for (ANode p : undoAdd.keySet()) {
			KeyValue<ANode, Integer> parent = undoAdd.get(p);
			p.setParent(parent.key, parent.value);
		}
		for (ANode p : undoProperties.keySet()) {
			AMapElement ame = (AMapElement) p;
			ame.copyProperties(undoProperties.get(p));
		}

		if (!undoRemove.isEmpty() || !undoAdd.isEmpty() || !undoProperties.isEmpty())
			firePropertyChange();
	}

	protected void firePropertyChange() {
		if (root != null)
			root.getPropertyChangeSupport().firePropertyChange("wrongvalue", true, false);
	}
}
