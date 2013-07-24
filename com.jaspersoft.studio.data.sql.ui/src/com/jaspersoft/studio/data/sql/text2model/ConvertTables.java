package com.jaspersoft.studio.data.sql.text2model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.jaspersoft.studio.data.sql.FromTable;
import com.jaspersoft.studio.data.sql.OrTable;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.TableFull;
import com.jaspersoft.studio.data.sql.TableOrAlias;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.impl.DbObjectNameImpl;
import com.jaspersoft.studio.data.sql.impl.OrTableImpl;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.utils.Misc;

public class ConvertTables {
	public static void convertTables(SQLQueryDesigner designer, OrTable tbls) {
		if (tbls == null)
			return;
		if (tbls instanceof FromTable)
			doTables(designer, Util.getKeyword(designer.getRoot(), MFrom.class), (FromTable) tbls);
		else if (tbls instanceof OrTableImpl) {
			MFrom mfrom = Util.getKeyword(designer.getRoot(), MFrom.class);
			for (FromTable ftbl : tbls.getEntries())
				doTables(designer, mfrom, ftbl);
		}
	}

	private static void doTables(SQLQueryDesigner designer, MFrom mfrom, FromTable ftbl) {
		TableOrAlias t = ftbl.getTable();
		if (t.getTfull() != null) {
			MRoot dbroot = designer.getDbMetadata().getRoot();

			MSqlTable msqlt = getTable(dbroot, t.getTfull());
			MFromTable mft = new MFromTable(mfrom, msqlt);
			if (t.getTblAlias() != null)
				mft.setAlias(t.getTblAlias().getDbname());
			mft.setAliasKeyword(Misc.nvl(t.getAlias(), " "));
			if (ftbl.getOnTable() != null) {
				TableOrAlias onTbl = ftbl.getOnTable();
				MSqlTable msqljt = getTable(dbroot, ftbl.getOnTable().getTfull());
				MFromTableJoin mftj = new MFromTableJoin(mft, msqljt);
				if (onTbl.getTblAlias() != null)
					mftj.setAlias(onTbl.getTblAlias().getDbname());
				mftj.setJoin(ftbl.getJoin().getLiteral());
				mftj.setAliasKeyword(Misc.nvl(onTbl.getAlias(), " "));

				new ConvertExpression().convertExpression(designer, mftj, ftbl.getJoinExpr());
			}
		}
	}

	private static MSqlTable getTable(MRoot dbroot, TableFull tf) {
		EList<EObject> eContents = tf.eContents();
		String table = Text2Model.getDbObjectName(eContents, 1);
		String schema = Text2Model.getDbObjectName(eContents, 2);
		if (tf instanceof DbObjectNameImpl)
			table = ((DbObjectNameImpl) tf).getDbname();
		// String catalog = getDbObjectName(eContents, 3);
		MSqlTable msqlt = Text2Model.findTable(dbroot, schema, table);
		if (msqlt == null)
			msqlt = Text2Model.getTableUnknown(dbroot, schema, table);
		return msqlt;
	}

}
