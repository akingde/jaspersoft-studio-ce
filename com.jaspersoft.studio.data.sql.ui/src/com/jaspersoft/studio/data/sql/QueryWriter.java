package com.jaspersoft.studio.data.sql;

import com.jaspersoft.studio.data.sql.model.IQueryString;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class QueryWriter {
	public static String writeQuery(MRoot root) {
		final StringBuffer sb = new StringBuffer();
		new ModelVisitor<INode>(root) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof IQueryString)
					sb.append(((IQueryString) n).toSQLString());
				return true;
			}

		};
		return sb.toString();
	}
}
