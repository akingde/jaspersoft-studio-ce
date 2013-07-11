package com.jaspersoft.studio.data.sql.text2model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.OrOrderByColumn;
import com.jaspersoft.studio.data.sql.OrderByColumnFull;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.model.util.KeyValue;

public class ConvertOrderBy {
	public static void convertOrderBy(SQLQueryDesigner designer, OrOrderByColumn cols) {
		if (cols == null)
			return;
		if (cols instanceof OrderByColumnFull)
			doColumn(designer, Util.getKeyword(designer.getRoot(), MSelect.class), (OrderByColumnFull) cols);
		else if (cols instanceof OrOrderByColumn) {
			MSelect msel = Util.getKeyword(designer.getRoot(), MSelect.class);
			for (OrderByColumnFull fcol : cols.getEntries())
				doColumn(designer, msel, fcol);
		}
	}

	private static void doColumn(SQLQueryDesigner designer, MSelect msel, OrderByColumnFull tf) {
		EList<ColumnFull> eContents = tf.getColOrder().getEntries();
		String column = null;
		if (tf instanceof DbObjectNameImpl)
			column = ((DbObjectNameImpl) tf).getDbname();
		else
			column = getDbObjectName(eContents, 1);
		String table = getDbObjectName(eContents, 2);
		String schema = getDbObjectName(eContents, 3);
		// String catalog = getDbObjectName(eContents, 3);
		MOrderByColumn mocol = findColumn(msel, schema, table, column);
		if (mocol != null) {
			String direction = tf.getDirection();
			if (direction != null)
				mocol.setDesc(direction.trim().equalsIgnoreCase(AMKeyword.DESCENDING_KEYWORD.trim()));
		}
	}

	public static String getDbObjectName(EList<ColumnFull> eContents, int i) {
		int size = eContents.size();
		if (size >= i) {
			EObject eobj = eContents.get(size - i);
			if (eobj instanceof DbObjectName)
				return ((DbObjectName) eobj).getDbname();
		}
		return null;
	}

	private static MOrderByColumn findColumn(final MSelect msel, final String schema, final String table, final String column) {
		KeyValue<MSQLColumn, MFromTable> kv = Text2Model.findColumn(msel, schema, table, column);
		if (kv != null)
			return new MOrderByColumn(Util.getKeyword(msel.getParent(), MOrderBy.class), kv.key, kv.value);
		return null;
	}
}
