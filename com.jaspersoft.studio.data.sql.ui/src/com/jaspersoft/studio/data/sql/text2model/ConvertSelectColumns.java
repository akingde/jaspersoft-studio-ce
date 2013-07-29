package com.jaspersoft.studio.data.sql.text2model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.ColumnOrAlias;
import com.jaspersoft.studio.data.sql.OrColumn;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl;
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
		if (fcol.getAllCols() != null) {
			new MSelectExpression(msel, "*");
		} else if (fcol.getSq() != null) {
			MSelectSubQuery qroot = new MSelectSubQuery(msel);
			Util.createSelect(qroot);
			Text2Model.convertSelect(designer, qroot, (SelectImpl) fcol.getSq().getSel());
		} else {
			AMQueryAliased<?> mscol = getColumn(msel, fcol.getCfull());
			mscol.setAliasKeyword(Misc.nvl(fcol.getAlias(), " "));
			if (fcol.getColAlias() != null)
				mscol.setAlias(fcol.getColAlias().getDbname());
		}
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
