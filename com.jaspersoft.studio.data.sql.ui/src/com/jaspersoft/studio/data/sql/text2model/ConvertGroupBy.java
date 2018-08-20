/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.text2model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.jaspersoft.studio.data.sql.GroupByColumnFull;
import com.jaspersoft.studio.data.sql.OrGroupByColumn;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupByExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.eclipse.util.KeyValue;
import net.sf.jasperreports.eclipse.util.Misc;

public class ConvertGroupBy {
	public static void convertGroupBy(SQLQueryDesigner designer, ANode qroot, OrGroupByColumn cols) {
		if (cols == null)
			return;
		if (cols instanceof GroupByColumnFull)
			doColumn(designer, Util.getKeyword(qroot, MSelect.class), (GroupByColumnFull) cols);
		else if (cols instanceof OrGroupByColumn) {
			MSelect msel = Util.getKeyword(qroot, MSelect.class);
			for (GroupByColumnFull fcol : cols.getEntries())
				doColumn(designer, msel, fcol);
		}
	}

	private static void doColumn(SQLQueryDesigner designer, MSelect msel, GroupByColumnFull tf) {
		try {
			MGroupBy parent = Util.getKeyword(msel.getParent(), MGroupBy.class);
			if (tf.getGbFunction() != null) {
				String f = ConvertSelectColumns.getFunctionString(designer, msel.getRoot(), parent, tf.getGbFunction(),
						msel);
				new MGroupByExpression(parent, f);
			} else if (tf.getColGrBy() != null) {
				EList<EObject> eContents = tf.eContents();
				String column = null;
				if (tf instanceof DbObjectNameImpl)
					column = ((DbObjectNameImpl) tf).getDbname();
				else
					column = ConvertUtil.getDbObjectName(eContents, 1);
				String table = ConvertUtil.getDbObjectName(eContents, 2);
				String schema = ConvertUtil.getDbObjectName(eContents, 3);
				KeyValue<MSQLColumn, MFromTable> kv = ConvertUtil.findColumn(msel, schema, table, column, designer);
				if (kv != null)
					new MGroupByColumn(parent, kv.key, kv.value);
				else {
					for (INode sn : msel.getChildren()) {
						if (sn instanceof AMQueryAliased) {
							String alias = ((AMQueryAliased<?>) sn).getAlias();
							if (!Misc.isNullOrEmpty(alias) && alias.equalsIgnoreCase(column)) {
								if (sn instanceof MSelectColumn)
									new MGroupByColumn(parent, ((MSelectColumn) sn).getValue(),
											((MSelectColumn) sn).getMFromTable());
								else if (sn instanceof MSelectExpression)
									new MGroupByExpression(parent, (MSelectExpression) sn);
							}
						}
					}
				}
			} else
				new MGroupByExpression(parent, Long.toString(tf.getGrByInt()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
