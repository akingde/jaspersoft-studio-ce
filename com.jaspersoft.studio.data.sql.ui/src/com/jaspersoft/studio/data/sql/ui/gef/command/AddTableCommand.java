/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.dnd.DND;

import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.metadata.keys.ForeignKey;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.prefs.SQLEditorPreferencesPage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class AddTableCommand extends ACommand {
	private Rectangle location;
	private MFrom mFrom;
	private Collection<MSqlTable> child;
	private List<MFromTable> fromTable;
	private String joinOnDND = SQLEditorPreferencesPage.DROP;
	private int dndDetail = 0;

	public AddTableCommand(MFrom parent, Collection<MSqlTable> child,
			Rectangle location) {
		this.location = location;
		this.mFrom = parent;
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
		super.execute();

		if (fromTable == null) {
			fromTable = new ArrayList<MFromTable>();
			final Map<ForeignKey, MFromTable> keys = new HashMap<ForeignKey, MFromTable>();
			for (MSqlTable mtlb : child) {
				// create from table object for our table
				MFromTable ft = new MFromTable(mFrom, mtlb);
				undoRemove.add(ft);
				if (location != null && child.size() == 1) {
					ft.setNoEvents(true);
					ft.setPropertyValue(MFromTable.PROP_X, location.x);
					ft.setPropertyValue(MFromTable.PROP_Y, location.y);
					ft.setNoEvents(false);
				}
				fromTable.add(ft);
				// check if this new table contains foreign keys
				for (INode n : mtlb.getChildren()) {
					if (n instanceof MSQLColumn) {
						List<ForeignKey> lfk = ((MSQLColumn) n)
								.getForeignKeys();
						if (lfk != null)
							for (ForeignKey fk : lfk)
								if (fk.getTable().equals(mtlb))
									keys.put(fk, ft);
					}
				}
			}
			// let's look if we have fk to our table from existing tables
			new ModelVisitor<Object>(mFrom) {

				@Override
				public boolean visit(INode n) {
					if (!(n instanceof MFromTable))
						return false;
					if (fromTable.contains(n))
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
							if (keys.containsKey(fk))
								continue;
							for (MSQLColumn c : fk.getDestColumns()) {
								MSqlTable tbl = (MSqlTable) c.getParent();
								for (MFromTable ftbl : fromTable)
									if (ftbl.getValue().equals(tbl))
										keys.put(fk, mft);
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

			if (createFKs) {
				for (ForeignKey fk : new HashSet<ForeignKey>(keys.keySet())) {
					MFromTable src = keys.get(fk);
					MSQLColumn[] cols = null;
					if (fk.getTable().equals(src.getValue()))
						cols = fk.getDestColumns();
					if (cols != null)
						for (MSQLColumn c : cols) {
							Set<MFromTable> destinations = hasTable(c);
							if (Misc.isNullOrEmpty(destinations))
								break;
							if (destinations.contains(src))
								break;
							MFromTable nsrc = src;
							if (nsrc instanceof MFromTableJoin)
								nsrc = (MFromTable) src.getParent();
							for (MFromTable dest : destinations) {
								MFromTable mTbl = dest instanceof MFromTableJoin ? (MFromTable) dest
										.getParent() : dest;

								// MFromTableJoin join = convertToSubTable(
								// nsrc != mTbl ? nsrc : src, mTbl);
								MFromTableJoin join = null;
								if (dest instanceof MFromTableJoin)
									join = (MFromTableJoin) dest;
								else {
									MFromTable srcTbl = nsrc != mTbl ? nsrc
											: src;
									join = convertToSubTable(dest, srcTbl);
									for (ForeignKey fk1 : new HashSet<ForeignKey>(
											keys.keySet())) {
										if (keys.get(fk1) == dest)
											keys.put(fk1, join);
									}
									dest = join;
								}
								joinExpression(join, dest, c, src,
										fk.getSrcColumns()[0]);
								break;
							}
							break;
						}
				}
			}
		} else
			for (MFromTable mft : fromTable)
				reparent(mft, mFrom);
		mFrom.firePropertyChange("wrongvalue", true, false);
	}

	private Set<MFromTable> hasTable(MSQLColumn c) {
		Set<MFromTable> tables = new HashSet<MFromTable>();
		for (INode n : mFrom.getChildren()) {
			if (!(n instanceof MFromTable))
				continue;
			MFromTable ft = (MFromTable) n;
			if (ft.getValue().equals(c.getParent()))
				tables.add(ft);
			if (Misc.isNullOrEmpty(ft.getChildren()))
				continue;
			for (INode cn : ft.getChildren()) {
				if (!(cn instanceof MFromTable))
					continue;
				ft = (MFromTable) cn;
				if (ft.getValue().equals(c.getParent()))
					tables.add(ft);
			}
		}
		return tables;
	}

	private void joinExpression(MFromTableJoin join, MFromTable mtbl,
			MSQLColumn mCol, MFromTable sTbl, MSQLColumn sCol) {
		if (!mCol.getParent().equals(mtbl.getValue()))
			System.out.println("problem!");
		if (!sCol.getParent().equals(sTbl.getValue()))
			System.out.println("problem!");

		MExpression mexpr = new MExpression(join, mtbl, -1);
		List<AOperand> operands = mexpr.getOperands();
		operands.add(new FieldOperand(sCol, sTbl, mexpr));
		operands.add(new FieldOperand(mCol, mtbl, mexpr));
		undoRemove.add(mexpr);
	}

	private MFromTableJoin convertToSubTable(MFromTable nsrc, MFromTable parent) {
		reparent(nsrc, null);

		fromTable.remove(nsrc);

		if (parent instanceof MFromTableJoin)
			parent = (MFromTable) parent.getParent();

		MFromTableJoin join = new MFromTableJoin(parent, nsrc.getValue());
		undoRemove.add(join);

		undoProperties.put(nsrc, nsrc.copyPropertiesUndo(join));
		if (!Misc.isNullOrEmpty(nsrc.getChildren()))
			for (INode n : new ArrayList<INode>(nsrc.getChildren()))
				reparent((ANode) n, parent);
		fromTable.add(join);
		return join;
	}

	@Override
	protected void firePropertyChange() {
		mFrom.firePropertyChange("wrongvalue", true, false);
	}

}
