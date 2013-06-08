package com.jaspersoft.studio.data.sql.action.order;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.UsedColumnsDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.MOrderByColumn;
import com.jaspersoft.studio.model.ANode;

public class CreateOrderByColumn extends AAction {

	public CreateOrderByColumn(SQLQueryDesigner designer) {
		super("&Add Order By Column", designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		return element instanceof MOrderBy || element instanceof MOrderByColumn;
	}

	@Override
	public void run() {
		UsedColumnsDialog dialog = new UsedColumnsDialog(Display.getDefault().getActiveShell());
		dialog.setRoot(designer.getDbMetadata().getRoot());
		dialog.setSelection((ANode) selection[0]);
		if (dialog.open() == Window.OK)
			run(dialog.getColumns());
	}

	public void run(Collection<MColumn> nodes) {
		Object sel = selection[0];
		MOrderBy orderBy = null;
		if (sel instanceof MOrderBy)
			orderBy = (MOrderBy) sel;
		else if (sel instanceof MOrderByColumn)
			orderBy = (MOrderBy) ((MOrderByColumn) sel).getParent();
		List<MSqlTable> tables = Util.getTables(orderBy);
		for (MColumn t : nodes) {
			if (Util.columnExists(t, orderBy, tables))
				continue;
			if (sel instanceof MOrderBy)
				sel = run(t, (MOrderBy) sel, 0);
			else if (sel instanceof MOrderByColumn) {
				sel = run(t, (MOrderByColumn) sel);
			}
		}
		selectInTree(sel);
	}

	protected MOrderByColumn run(MColumn node, MOrderByColumn mtable) {
		MOrderBy mfrom = (MOrderBy) mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MOrderByColumn run(MColumn node, MOrderBy select, int index) {
		return new MOrderByColumn(select, node, index);
	}

}