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
package com.jaspersoft.studio.data.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.AMQueryObject;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class Util {
	public static boolean columnExists(MColumn c, MDBObjects orderBy, List<MSqlTable> tables) {
		if (!tables.contains(c.getParent()))
			return true;
		for (INode n : orderBy.getChildren())
			if (n.getValue().equals(c))
				return true;
		return false;
	}

	public static List<AMSQLObject> getUsedColumns(ANode sel) {
		List<AMSQLObject> list = new ArrayList<AMSQLObject>();
		MDBObjects mgb = null;
		if (sel instanceof MDBObjects)
			mgb = (MDBObjects) sel;
		else if (sel instanceof AMQueryObject)
			mgb = (MDBObjects) sel.getParent();
		for (INode n : mgb.getChildren())
			if (n instanceof MSelectColumn)
				list.add((AMSQLObject) n.getValue());
		return list;
	}

	public static List<MSqlTable> getTables(ANode sel) {
		List<MSqlTable> list = new ArrayList<MSqlTable>();
		MRoot r = (MRoot) sel.getRoot();
		for (INode n : r.getChildren()) {
			if (n instanceof MFrom) {
				for (INode t : n.getChildren()) {
					list.add((MSqlTable) t.getValue());
					if (!t.getChildren().isEmpty()) {
						for (INode jt : t.getChildren())
							list.add((MSqlTable) jt.getValue());
					}
				}
				break;
			}
		}
		return list;
	}

	public static List<MFromTable> getFromTables(ANode sel) {
		List<MFromTable> list = new ArrayList<MFromTable>();
		MRoot r = (MRoot) sel.getRoot();
		for (INode n : r.getChildren()) {
			if (n instanceof MFrom) {
				for (INode t : n.getChildren()) {
					list.add((MFromTable) t);
					if (!t.getChildren().isEmpty()) {
						for (INode jt : t.getChildren())
							list.add((MFromTable) jt);
					}
				}
				break;
			}
		}
		return list;
	}

	public static MRoot getRoot(MColumn mcol, MExpression mexpr) {
		if (mcol != null)
			return (MRoot) mcol.getRoot();
		ModelVisitor<INode> mv = new ModelVisitor<INode>(mexpr.getRoot()) {
			@Override
			public boolean visit(INode n) {
				if (n instanceof AMQueryObject && n.getValue() instanceof ANode) {
					ANode v = (ANode) ((AMQueryObject<?>) n).getValue();
					setObject(v.getRoot());
					return false;
				}
				return true;
			}
		};
		return (MRoot) mv.getObject();
	}

	public static ANode getOldNode(ANode target, final ANode src) {
		ModelVisitor<ANode> mv = new ModelVisitor<ANode>(target.getRoot()) {
			@Override
			public boolean visit(INode n) {
				if (n instanceof MExpression && src instanceof MExpression)
					System.out.println(((MExpression) n).getId() + " --- " + ((MExpression) src).getId());
				if (src != n && src.equals(n)) {
					setObject((ANode) n);
					return false;
				}
				return true;
			}
		};
		return mv.getObject();
	}

	public static <T extends AMKeyword> T getKeyword(ANode msc, Class<T> clazz) {
		for (INode n : msc.getRoot().getChildren())
			if (clazz.isInstance(n))
				return (T) n;
		return null;
	}

	public static List<ANode> getAllNodes(Object data) {
		List<ANode> nodes = new ArrayList<ANode>();
		if (data.getClass().isArray()) {
			Object[] ar = (Object[]) data;
			for (Object obj : ar)
				if (obj instanceof ANode)
					nodes.add((ANode) obj);
		} else if (data instanceof ANode)
			nodes.add((ANode) data);
		return nodes;
	}

	public static void filterTables(List<ANode> node, Set<MSqlTable> tables, Set<MColumn> cols, Set<ANode> others) {
		for (ANode n : node) {
			if (n instanceof MSqlTable)
				tables.add((MSqlTable) n);
			else if (n instanceof MColumn)
				cols.add((MColumn) n);
			else
				others.add(n);
		}
	}

	public static void cleanTableVersions(final MFromTable newTbl, final MFromTable oldTbl) {
		new ModelVisitor<Object>(newTbl.getRoot()) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MSelectColumn && ((MSelectColumn) n).getMFromTable().equals(oldTbl)) {
					((MSelectColumn) n).setMFromTable(newTbl);
					return false;
				}
				if (n instanceof MGroupByColumn && ((MGroupByColumn) n).getMFromTable().equals(oldTbl)) {
					((MGroupByColumn) n).setMFromTable(newTbl);
					return false;
				}
				if (n instanceof MOrderByColumn && ((MOrderByColumn) n).getMFromTable().equals(oldTbl)) {
					((MOrderByColumn) n).setMFromTable(newTbl);
					return false;
				}
				if (n instanceof MExpression) {
					for (AOperand op : ((MExpression) n).getOperands()) {
						if (op instanceof FieldOperand && ((FieldOperand) op).getFromTable().equals(oldTbl)) {
							((FieldOperand) op).setFromTable(newTbl);
						}
					}
					return false;
				}
				return true;
			}

		};
	}
}
