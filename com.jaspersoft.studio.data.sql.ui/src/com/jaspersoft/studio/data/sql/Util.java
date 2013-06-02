package com.jaspersoft.studio.data.sql;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.data.sql.model.MQueryObjects;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MFrom;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;

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
		else if (sel instanceof MQueryObjects)
			mgb = (MDBObjects) sel.getParent();
		for (INode n : mgb.getChildren())
			list.add((AMSQLObject) n.getValue());
		return list;
	}

	public static List<MSqlTable> getTables(ANode sel) {
		List<MSqlTable> list = new ArrayList<MSqlTable>();
		MRoot r = (MRoot) sel.getRoot();
		for (INode n : r.getChildren()) {
			if (n instanceof MFrom) {
				for (INode t : n.getChildren())
					list.add((MSqlTable) t.getValue());
				break;
			}
		}
		return list;
	}
}
