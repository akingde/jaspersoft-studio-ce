package com.jaspersoft.studio.data.sql;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.AMQueryObject;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
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
}
