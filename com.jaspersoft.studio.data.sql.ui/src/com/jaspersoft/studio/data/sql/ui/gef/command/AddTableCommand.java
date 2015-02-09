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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.dnd.DND;

import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.metadata.keys.ForeignKey;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.prefs.SQLEditorPreferencesPage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.utils.Misc;

public class AddTableCommand extends Command {
	private Rectangle location;
	private MFrom parent;
	private Collection<MSqlTable> child;
	private List<MFromTable> fromTable;
	private Map<MFromTable, ANode> parentMap;
	private String joinOnDND = SQLEditorPreferencesPage.DROP;
	private int dndDetail = 0;

	public AddTableCommand(MFrom parent, Collection<MSqlTable> child,
			Rectangle location) {
		this.location = location;
		this.parent = parent;
		this.child = child;
	}

	public void setJoinOnDND(String joinOnDND) {
		this.joinOnDND = joinOnDND;
	}

	public void setDnDDetail(int dndDetail) {
		this.dndDetail = dndDetail;
	}

	@Override
	public void execute() {
		if (fromTable == null) {
			fromTable = new ArrayList<MFromTable>();
			final Map<ForeignKey, MFromTable> keys = new HashMap<ForeignKey, MFromTable>();
			for (MSqlTable mtlb : child) {
				MFromTable ft = new MFromTable(parent, mtlb);
				if (location != null && child.size() == 1) {
					ft.setNoEvents(true);
					ft.setPropertyValue(MFromTable.PROP_X, location.x);
					ft.setPropertyValue(MFromTable.PROP_Y, location.y);
					ft.setNoEvents(false);
				}
				fromTable.add(ft);
				for (INode n : mtlb.getChildren()) {
					List<ForeignKey> lfk = ((MSQLColumn) n).getForeignKeys();
					if (lfk != null)
						for (ForeignKey fk : lfk)
							if (fk.getTable().equals(mtlb))
								keys.put(fk, ft);
				}
			}
			new ModelVisitor<Object>(parent) {

				@Override
				public boolean visit(INode n) {
					if (!(n instanceof MFromTable && !fromTable.contains(n)))
						return false;
					MFromTable mft = (MFromTable) n;
					for (INode child : mft.getValue().getChildren()) {
						if (!(child instanceof MSQLColumn))
							continue;
						MSQLColumn col = (MSQLColumn) child;
						if (col.getForeignKeys() == null)
							continue;
						for (ForeignKey fk : col.getForeignKeys()) {
							if (fk.getDestColumns() == null)
								continue;
							for (MSQLColumn c : fk.getDestColumns()) {
								MSqlTable tbl = (MSqlTable) c.getParent();
								for (MFromTable ftbl : fromTable) {
									if (ftbl.getValue().equals(tbl))
										keys.put(fk, mft);
								}
							}
						}
					}
					return true;
				}
			};

			// look into all the tables to see, if there are fks to the new
			// tables
			boolean createFKs = false;
			if (!keys.isEmpty())
				if (joinOnDND.equals(SQLEditorPreferencesPage.DROP))
					createFKs = dndDetail == DND.DROP_MOVE;
				else if (joinOnDND.equals(SQLEditorPreferencesPage.ASK))
					createFKs = UIUtils.showConfirmation(
							Messages.AddTableCommand_0,
							Messages.AddTableCommand_1);
				else if (joinOnDND.equals(SQLEditorPreferencesPage.CTRL_DROP))
					createFKs = dndDetail == DND.DROP_COPY;

			if (createFKs)
				for (ForeignKey fk : keys.keySet()) {
					for (MSQLColumn c : fk.getDestColumns()) {
						MFromTable src = keys.get(fk);
						List<MFromTable> destinations = hasTable(c);
						if (Misc.isNullOrEmpty(destinations))
							break;
						if (destinations.contains(src))
							break;
						if (!(src instanceof MFromTableJoin)) {
							for (MFromTable dest : destinations) {
								MFromTable p = dest instanceof MFromTableJoin ? (MFromTable) dest
										.getParent() : dest;

								src.setParent(null, -1);
								fromTable.remove(src);

								MFromTableJoin join = new MFromTableJoin(p,
										src.getValue());
								MExpression mexpr = new MExpression(join, dest,
										-1);
								mexpr.getOperands().add(
										new FieldOperand(fk.getSrcColumns()[0],
												join, mexpr));
								mexpr.getOperands().add(
										new FieldOperand(c, dest, mexpr));

								src.copyProperties(join);
								fromTable.add(join);
								break;
							}
						}
						break;
					}
				}
		} else {
			for (MFromTable mft : fromTable)
				mft.setParent(parent, -1);
		}
		parent.firePropertyChange("wrongvalue", true, false);
	}

	private List<MFromTable> hasTable(MSQLColumn c) {
		List<MFromTable> tables = new ArrayList<MFromTable>();
		for (INode n : parent.getChildren()) {
			if (!(n instanceof MFromTable))
				continue;
			MFromTable ft = (MFromTable) n;
			if (ft.getValue().equals(c.getParent()))
				tables.add(ft);
		}
		return tables;
	}

	@Override
	public void undo() {
		if (parentMap == null)
			parentMap = new HashMap<MFromTable, ANode>();
		for (MFromTable p : parentMap.keySet()) {
			parentMap.put(p, p.getParent());
			p.setParent(null, -1);
		}
		parent.firePropertyChange("wrongvalue", true, false);
	}
}
