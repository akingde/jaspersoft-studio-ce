/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql;

import com.jaspersoft.studio.data.sql.model.ISubQuery;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.data.sql.model.query.IQueryString;
import com.jaspersoft.studio.data.sql.model.query.MUnion;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectSubQuery;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class QueryWriter {
	public static String writeQuery(ANode root) {
		final StringBuilder sb = new StringBuilder();
		new ModelVisitor<INode>(root) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof ISubQuery) {
					if (n instanceof MSelectSubQuery && !((MSelectSubQuery) n).isFirst())
						sb.append(",\n\t");
					sb.append("(");
				} else if (n instanceof IQueryString) {
					if (n instanceof MFromTable && !(n instanceof MFromTableJoin) && n.getValue() instanceof MQueryTable
							&& !((MFromTable) n).isFirst())
						sb.append(",\n\t");
					sb.append(((IQueryString) n).toSQLString());
				}
				return true;
			}

			@Override
			protected void postChildIteration(INode n) {
				boolean b = n instanceof MFromTable && n.getValue() instanceof MQueryTable;
				if (n instanceof ISubQuery || b) {
					if (b && n instanceof MFromTableJoin)
						return;
					sb.append(")");
					if (n instanceof AMQueryAliased<?>)
						sb.append(((AMQueryAliased<?>) n).addAlias());
				}
			}
		};
		return sb.toString().trim();
	}

	public static String writeSubQuery(ANode root) {
		final StringBuilder sb = new StringBuilder();
		new ModelVisitor<INode>(root) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MUnion || n instanceof MOrderBy)
					stop();
				if (n instanceof ISubQuery)
					sb.append("(");
				else if (n instanceof IQueryString)
					sb.append(((IQueryString) n).toSQLString());
				return true;
			}

			@Override
			protected void postChildIteration(INode n) {
				boolean b = n instanceof MFromTable && n.getValue() instanceof MQueryTable;
				if (n instanceof ISubQuery || b) {
					sb.append(")");
					if (b && n instanceof MFromTableJoin)
						return;
					if (n instanceof AMQueryAliased<?>)
						sb.append(((AMQueryAliased<?>) n).addAlias());
				}
			}
		};
		return sb.toString();
	}
}
