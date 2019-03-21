/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.command;

import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.model.INode;

public class JoinTableCommand extends ACommand {
	private MSQLColumn src;
	private MFromTable srcTbl;
	private MSQLColumn dest;
	private MFromTable destTbl;
	private MFromTable fromTbl;
	private MExpression mexpr;
	private String join;

	public JoinTableCommand(MSQLColumn src, MFromTable srcTbl, MSQLColumn dest, MFromTable destTbl, MFromTable fromTbl,
			String join) {
		this.src = src;
		this.srcTbl = srcTbl;
		this.dest = dest;
		this.destTbl = destTbl;
		this.fromTbl = fromTbl;
		this.join = join;
	}

	@Override
	public void execute() {
		super.execute();
		if (src == null)
			src = getColumn(srcTbl.getValue());
		if (dest == null)
			dest = getColumn(destTbl.getValue());
		srcTbl.setParent(null, -1);

		boolean onlyExpression = false;
		for (INode n : fromTbl.getChildren()) {
			if (n == destTbl) {
				onlyExpression = true;
				break;
			}
		}
		MFromTableJoin mtbljoin = null;
		if (!onlyExpression) {
			mtbljoin = new MFromTableJoin(fromTbl, srcTbl.getValue());
			mtbljoin.setNoEvents(true);
			mtbljoin.setPropertyValue(MFromTable.PROP_X, srcTbl.getPropertyActualValue(MFromTable.PROP_X));
			mtbljoin.setPropertyValue(MFromTable.PROP_Y, srcTbl.getPropertyActualValue(MFromTable.PROP_Y));
			mtbljoin.setNoEvents(false);
			mtbljoin.setAlias(srcTbl.getAlias());
			mtbljoin.setAliasKeyword(srcTbl.getAliasKeyword());

			fromTbl.removeTableJoin(mtbljoin.getTableJoin());
			mtbljoin.setJoin(join);
			mtbljoin.setTableJoin(new TableJoin(mtbljoin, (MFromTable) destTbl));
			Util.copySubQuery(srcTbl, mtbljoin);
		} else
			mtbljoin = (MFromTableJoin) destTbl;

		mexpr = new MExpression(mtbljoin, src, -1);
		mexpr.getOperands().add(new FieldOperand(src, mtbljoin, mexpr));
		mexpr.getOperands().add(new FieldOperand(dest, destTbl, mexpr));

		Util.cleanTableVersions(mtbljoin, srcTbl);
	}

	public MExpression getMexpr() {
		return mexpr;
	}

	private MSQLColumn getColumn(MSqlTable tbl) {
		if (!tbl.getChildren().isEmpty()) {
			for (INode n : tbl.getChildren())
				if (((MSQLColumn) n).getPrimaryKey() != null)
					return (MSQLColumn) n;
			return (MSQLColumn) tbl.getChildren().get(0);
		}
		return null;
	}

	@Override
	protected void firePropertyChange() {
		// do nothing here
	}

}
