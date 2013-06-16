package com.jaspersoft.studio.data.sql.ui.gef.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.action.table.DeleteTableJoin;
import com.jaspersoft.studio.data.sql.action.table.JoinTable;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;

public class JoinCommand extends Command {
	private SQLQueryDesigner designer;
	private MColumn src, dest;
	private MFromTable srcTbl, destTbl;

	public JoinCommand(MColumn src, MFromTable srcTbl, MColumn dest, MFromTable destTbl, SQLQueryDesigner designer) {
		this.designer = designer;
		this.src = src;
		this.srcTbl = srcTbl;
		this.dest = dest;
		this.destTbl = destTbl;
	}

	public JoinCommand(MFromTable srcTbl, MFromTable destTbl, SQLQueryDesigner designer) {
		this.designer = designer;
		this.srcTbl = srcTbl;
		this.destTbl = destTbl;
	}

	@Override
	public void execute() {
		ActionFactory afactory = designer.getOutline().getAfactory();
		if (srcTbl instanceof MFromTableJoin) {
			DeleteTableJoin dtj = afactory.getAction(DeleteTableJoin.class);
			dtj.calculateEnabled(new Object[] { srcTbl });
			srcTbl = dtj.runSilent();
		}
		if (destTbl instanceof MFromTableJoin) {
			DeleteTableJoin dtj = afactory.getAction(DeleteTableJoin.class);
			dtj.calculateEnabled(new Object[] { destTbl });
			destTbl = dtj.runSilent();
		}

		JoinTable jt = afactory.getAction(JoinTable.class);
		if (!srcTbl.getChildren().contains(destTbl))
			jt.doRun(dest, destTbl, src, srcTbl);
		else
			jt.doRun(src, srcTbl, dest, destTbl);

	}
}
