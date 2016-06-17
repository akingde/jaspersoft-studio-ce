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
package com.jaspersoft.studio.data.sql.model.query.from;

import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;

public class TableJoinDetail {
	private MFromTable srcTbl;
	private MFromTable destTbl;
	private MExpression expr;

	public TableJoinDetail(MFromTable srcTbl, MFromTable destTbl, MExpression expr) {
		this.srcTbl = srcTbl;
		this.destTbl = destTbl;
		this.expr = expr;
	}

	public MExpression getExpr() {
		return expr;
	}

	public MFromTable getSrcTable() {
		return srcTbl;
	}

	public MFromTable getDestTable() {
		return destTbl;
	}

	public FieldOperand getSrcColumn() {
		if (expr == null) {

		} else if (!expr.getOperands().isEmpty())
			for (AOperand op : expr.getOperands())
				if (op instanceof FieldOperand && ((FieldOperand) op).getFromTable() == srcTbl)
					return (FieldOperand) op;
		return null;
	}

	public FieldOperand getTgtColumn() {
		if (expr == null) {
		} else if (!expr.getOperands().isEmpty())
			for (AOperand op : expr.getOperands())
				if (op instanceof FieldOperand && ((FieldOperand) op).getFromTable() == destTbl)
					return (FieldOperand) op;
		return null;
	}

	public MFromTableJoin getMFromTableJoin() {
		if (destTbl instanceof MFromTableJoin)
			return (MFromTableJoin) destTbl;
		if (srcTbl instanceof MFromTableJoin)
			return (MFromTableJoin) srcTbl;
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return srcTbl.toSQLString() + " , " + destTbl.toSQLString();
	}
}
