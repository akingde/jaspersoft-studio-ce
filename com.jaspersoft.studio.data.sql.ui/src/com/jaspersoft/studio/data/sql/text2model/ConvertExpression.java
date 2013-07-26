package com.jaspersoft.studio.data.sql.text2model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.FullExpression;
import com.jaspersoft.studio.data.sql.JRParameter;
import com.jaspersoft.studio.data.sql.Operand;
import com.jaspersoft.studio.data.sql.Operands;
import com.jaspersoft.studio.data.sql.OrExpr;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.XExpr;
import com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl;
import com.jaspersoft.studio.data.sql.impl.OperandImpl;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionGroup;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionX;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ParameterNotPOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ParameterPOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.widgets.Factory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.KeyValue;
import com.jaspersoft.studio.utils.Misc;

public class ConvertExpression {
	public static void convertExpression(SQLQueryDesigner designer, ANode qroot, ANode parent, OrExpr cols) {
		if (cols == null)
			return;
		if (cols instanceof FullExpression)
			doExpression(designer, Util.getKeyword(qroot, MSelect.class), parent, (FullExpression) cols);
		else if (cols instanceof OrExpr) {
			MSelect msel = Util.getKeyword(qroot, MSelect.class);
			for (FullExpression fcol : cols.getEntries())
				doExpression(designer, msel, parent, fcol);
		}
	}

	private static void doExpression(SQLQueryDesigner designer, MSelect msel, ANode parent, FullExpression tf) {
		String prevCond = tf.getC();
		if (tf.getEfrag() != null)
			tf = tf.getEfrag();
		if (tf.getExp() != null) {
			tf = tf.getExp();
			MExpression me = new MExpression(parent, null, -1);
			me.getOperands().add(getOperand(designer, msel, tf.getOp1(), me));
			if (tf.getComp() != null) {
				me.setOperator(Operator.getOperator(tf.getComp().getOperator()));
				me.getOperands().add(getOperand(designer, msel, tf.getComp().getOp2(), me));
			} else if (tf.getIsnull() != null)
				me.setOperator(Operator.getOperator(tf.getIsnull()));
			else if (tf.getBetween() != null) {
				me.setOperator(Operator.getOperator(tf.getBetween().getOpBetween()));
				me.getOperands().add(getOperand(designer, msel, tf.getBetween().getOp2(), me));
				me.getOperands().add(getOperand(designer, msel, tf.getBetween().getOp3(), me));
			} else if (tf.getLike() != null) {
				me.setOperator(Operator.getOperator(tf.getLike().getOpLike()));
				me.getOperands().add(new ScalarOperand<String>(me, tf.getLike().getOp2()));
			} else if (tf.getIn() != null) {
				me.setOperator(Operator.getOperator(tf.getIn().getOp().replace("(", "").trim()));
				if (tf.getIn().getSubquery() != null) {

				} else if (tf.getIn().getOpList() != null) {
					for (EObject eobj : tf.getIn().getOpList().eContents()) {
						if (eobj instanceof Operand)
							me.getOperands().add(getOperand(designer, msel, (Operand) eobj, me));
					}
				}
			}
			setPrevOperator(me, prevCond);
		} else if (tf.getExpgroup() != null) {
			MExpressionGroup meg = new MExpressionGroup(parent);
			convertExpression(designer, msel.getParent(), meg, tf.getExpgroup().getExpr());
			// setPrevOperator(meg, prevCond);
		} else if (tf.getXexp() != null) {
			MExpressionX me = new MExpressionX(parent, null, -1);
			XExpr xexpr = tf.getXexp();
			FieldOperand fc = null;
			if (xexpr.getCol() != null)
				fc = doColumn(designer, msel, xexpr.getCol().getCfull(), me);
			me.getOperands().add(Misc.nvl(fc, new FieldOperand(null, null, me)));
			me.setFunction(xexpr.getXf().getLiteral());
			if (xexpr.getPrm() != null)
				if (xexpr.getPrm() instanceof JRParameter)
					addParam(me, (JRParameter) xexpr.getPrm());
				else
					for (EObject fcol : xexpr.getPrm().eContents()) {
						if (fcol instanceof JRParameter)
							addParam(me, (JRParameter) fcol);
					}
			setPrevOperator(me, prevCond);
		}
	}

	protected static void addParam(MExpressionX me, JRParameter fcol) {
		ParameterPOperand pop = new ParameterPOperand(me);
		pop.setJrParameter(fcol.getJrprm());
		me.getOperands().add(pop);
	}

	private static void setPrevOperator(AMExpression<?> me, String prevCond) {
		if (prevCond != null)
			me.setPrevCond(prevCond.trim().toUpperCase());
	}

	private static AOperand getOperand(SQLQueryDesigner designer, MSelect msel, Operands ops, AMExpression<?> me) {
		if (ops == null)
			return null;
		if (ops instanceof OperandImpl)
			return getOperand(designer, msel, (Operand) ops, me);
		return getOperand(designer, msel, ops.getEntries().get(0), me);
	}

	private static AOperand getOperand(SQLQueryDesigner designer, MSelect msel, Operand op, AMExpression<?> me) {
		AOperand aop = null;
		if (op.getColumn() != null) {
			aop = doColumn(designer, msel, op.getColumn().getCfull(), me);
		} else if (op.getScalar() != null) {
			aop = getScalarOperand(me, op.getScalar());
		} else if (op.getXop() != null) {
			Operand xop = op.getXop();
			if (xop.getScalar() != null) {
				aop = getScalarOperand(me, op.getXop().getScalar());
			} else if (xop.getParam() != null) {
				ParameterPOperand pop = new ParameterPOperand(me);
				pop.setJrParameter(xop.getParam().getPrm());
				return pop;
			} else if (xop.getEparam() != null) {
				ParameterNotPOperand prm = new ParameterNotPOperand(me);
				prm.setJrParameter(xop.getEparam().getPrm());
				return prm;
			}
		} else if (op.getSubq() != null) {

		}
		return Misc.nvl(aop, Factory.getDefaultOperand(me));
	}

	protected static AOperand getScalarOperand(AMExpression<?> me, com.jaspersoft.studio.data.sql.ScalarOperand sop) {
		if (sop.getSostr() != null) {
			String str = sop.getSostr();
			str = str.substring(1, str.length() - 1);
			return new ScalarOperand<String>(me, str);
		}
		if (sop.getSodate() != null)
			return new ScalarOperand<Date>(me, sop.getSodate());
		if (sop.getSodbl() != null)
			return new ScalarOperand<BigDecimal>(me, sop.getSodbl());
		if (sop.getSodt() != null)
			return new ScalarOperand<Timestamp>(me, new Timestamp(sop.getSodt().getTime()));
		if (sop.getSoint() != null)
			return new ScalarOperand<Integer>(me, sop.getSoint());
		if (sop.getSotime() != null)
			return new ScalarOperand<Time>(me, new Time(sop.getSotime().getTime()));
		return null;
	}

	private static FieldOperand doColumn(SQLQueryDesigner designer, MSelect msel, ColumnFull tf, AMExpression<?> mexpr) {
		EList<EObject> eContents = tf.eContents();
		String column = null;
		if (tf instanceof DbObjectNameImpl)
			column = ((DbObjectNameImpl) tf).getDbname();
		else
			column = Text2Model.getDbObjectName(eContents, 1);
		String table = Text2Model.getDbObjectName(eContents, 2);
		String schema = Text2Model.getDbObjectName(eContents, 3);
		// String catalog = getDbObjectName(eContents, 3);
		KeyValue<MSQLColumn, MFromTable> kv = Text2Model.findColumn(msel, schema, table, column);
		if (kv != null)
			return new FieldOperand(kv.key, kv.value, mexpr);
		return null;
	}

}
