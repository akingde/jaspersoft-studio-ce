package com.jaspersoft.studio.data.sql.text2model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.ColumnOrAlias;
import com.jaspersoft.studio.data.sql.OpFunction;
import com.jaspersoft.studio.data.sql.OpFunctionArg;
import com.jaspersoft.studio.data.sql.Operand;
import com.jaspersoft.studio.data.sql.Operands;
import com.jaspersoft.studio.data.sql.OrColumn;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.ScalarOperand;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl;
import com.jaspersoft.studio.data.sql.impl.OperandImpl;
import com.jaspersoft.studio.data.sql.impl.OperandsImpl;
import com.jaspersoft.studio.data.sql.impl.OrColumnImpl;
import com.jaspersoft.studio.data.sql.impl.SelectImpl;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectSubQuery;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.KeyValue;
import com.jaspersoft.studio.utils.Misc;

public class ConvertSelectColumns {
	public static void convertSelectColumns(SQLQueryDesigner designer, ANode qroot, OrColumn cols) {
		if (cols == null)
			return;
		if (cols instanceof ColumnOrAlias)
			doColumns(designer, Util.getKeyword(qroot, MSelect.class), (ColumnOrAlias) cols);
		else if (cols instanceof OrColumnImpl) {
			MSelect msel = Util.getKeyword(qroot, MSelect.class);
			for (ColumnOrAlias fcol : cols.getEntries())
				doColumns(designer, msel, fcol);
		}
	}

	private static void doColumns(SQLQueryDesigner designer, MSelect msel, ColumnOrAlias fcol) {
		if (fcol.getAllCols() != null)
			new MSelectExpression(msel, "*");
		else if (fcol.getCe() != null) {
			if (fcol.getCe() instanceof OperandImpl)
				setupAlias(getMSelectColumn(designer, (OperandImpl) fcol.getCe(), msel), fcol);
			else if (fcol.getCe() instanceof OperandsImpl) {
				AMQueryAliased<?> mscol = null;
				for (Operands op : fcol.getCe().getEntries()) {
					mscol = getMSelectColumn(designer, (OperandImpl) op, msel);
					mscol.setValue(operand2String((Operand) op));
				}
				setupAlias(mscol, fcol);
			}
		}
	}

	protected static String operand2String(Operand oper) {
		// if (oper.getSubq() != null) {
		// MSelectSubQuery qroot = new MSelectSubQuery(msel);
		// Util.createSelect(qroot);
		// Text2Model.convertSelect(designer, qroot, (SelectImpl)
		// oper.getSubq().getSel());
		// } else
		if (oper.getColumn() != null)
			return getColumn(oper.getColumn().getCfull());
		if (oper.getFunc() != null)
			return getFunctionString(oper.getFunc(), oper);
		if (oper.getParam() != null)
			return oper.getParam().toString();
		if (oper.getScalar() != null)
			return oper.getScalar().toString();
		return "";
	}

	private static AMQueryAliased<?> getMSelectColumn(SQLQueryDesigner designer, OperandImpl op, MSelect msel) {
		AMQueryAliased<?> mscol = null;
		if (op.getSubq() != null) {
			mscol = new MSelectSubQuery(msel);
			Util.createSelect(mscol);
			Text2Model.convertSelect(designer, mscol, (SelectImpl) op.getSubq().getSel());
		} else if (op.getColumn() != null)
			mscol = getColumn(msel, op.getColumn().getCfull());
		else if (op.getFunc() != null)
			mscol = getColumnUnknown(msel, getFunctionString(op.getFunc(), op));
		else if (op.getParam() != null)
			mscol = getColumnUnknown(msel, op.getParam().toString());
		else if (op.getScalar() != null)
			mscol = getColumnUnknown(msel, getScalarString(op.getScalar()));
		else if (op.getXop() != null)
			mscol = getMSelectColumn(designer, (OperandImpl) op.getXop(), msel);
		return mscol;
	}

	private static String getFunctionString(OpFunction f, Operand oper) {
		String sargs = " ";
		OpFunctionArg args = f.getArgs();
		if (args != null) {
			String sep = "";
			for (Operands eobj : args.getEntries()) {
				sargs += sep;
				if (eobj instanceof Operand)
					sargs += operand2String((Operand) eobj);
				sep = ",";
			}
		}
		return f.getFname() + "(" + sargs + ")";
	}

	private static String getScalarString(ScalarOperand sc) {
		if (sc.getSodate() != null)
			return sc.getSodate().toString();
		if (sc.getSodbl() != null)
			return sc.getSodbl().toString();
		if (sc.getSodt() != null)
			return sc.getSodt().toString();
		if (sc.getSoint() != null)
			return sc.getSoint().toString();
		if (sc.getSostr() != null)
			return sc.getSostr();
		if (sc.getSotime() != null)
			return sc.getSotime().toString();
		return "";
	}

	private static void setupAlias(AMQueryAliased<?> mscol, ColumnOrAlias fcol) {
		if (mscol == null)
			return;
		mscol.setAliasKeyword(Misc.nvl(fcol.getAlias(), " "));
		if (fcol.getColAlias() != null)
			mscol.setAlias(fcol.getColAlias().getDbname());
	}

	private static String getColumn(ColumnFull tf) {
		EList<EObject> eContents = tf.eContents();
		String column = null;
		if (tf instanceof DbObjectNameImpl)
			column = ((DbObjectNameImpl) tf).getDbname();
		else
			column = Text2Model.getDbObjectName(eContents, 1);
		String table = Text2Model.getDbObjectName(eContents, 2);
		String schema = Text2Model.getDbObjectName(eContents, 3);
		// String catalog = getDbObjectName(eContents, 3);
		if (table != null)
			column = table + "." + column;
		if (schema != null)
			column = schema + "." + column;
		return column;
	}

	private static AMQueryAliased<?> getColumn(MSelect msel, ColumnFull tf) {
		EList<EObject> eContents = tf.eContents();
		String column = null;
		if (tf instanceof DbObjectNameImpl)
			column = ((DbObjectNameImpl) tf).getDbname();
		else
			column = Text2Model.getDbObjectName(eContents, 1);
		String table = Text2Model.getDbObjectName(eContents, 2);
		String schema = Text2Model.getDbObjectName(eContents, 3);
		// String catalog = getDbObjectName(eContents, 3);
		MSelectColumn msqlt = findColumn(msel, schema, table, column);
		if (msqlt == null)
			return getColumnUnknown(msel, column);
		return msqlt;
	}

	private static MSelectExpression getColumnUnknown(MSelect msel, String column) {
		return new MSelectExpression(msel, column);
	}

	private static MSelectColumn findColumn(final MSelect msel, final String schema, final String table, final String column) {
		KeyValue<MSQLColumn, MFromTable> kv = Text2Model.findColumn(msel, schema, table, column);
		if (kv != null)
			return new MSelectColumn(msel, kv.key, kv.value);
		return null;
	}

}
