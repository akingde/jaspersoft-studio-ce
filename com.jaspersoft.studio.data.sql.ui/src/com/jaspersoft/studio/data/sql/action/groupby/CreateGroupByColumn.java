package com.jaspersoft.studio.data.sql.action.groupby;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.dialogs.UsedColumnsDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.MGroupByColumn;
import com.jaspersoft.studio.model.ANode;

public class CreateGroupByColumn extends AAction {

	public CreateGroupByColumn(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Add Group By Column", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		return element instanceof MGroupBy || element instanceof MGroupByColumn;
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
		MGroupBy orderBy = null;
		if (sel instanceof MGroupBy)
			orderBy = (MGroupBy) sel;
		else if (sel instanceof MGroupByColumn)
			orderBy = (MGroupBy) ((MGroupByColumn) sel).getParent();
		List<MSqlTable> tables = Util.getTables(orderBy);
		for (MColumn t : nodes) {
			if (Util.columnExists(t, orderBy, tables))
				continue;
			if (sel instanceof MGroupBy)
				sel = run(t, (MGroupBy) sel, 0);
			else if (sel instanceof MGroupByColumn) {
				sel = run(t, (MGroupByColumn) sel);
			}
		}
		selectInTree(sel);
	}

	protected MGroupByColumn run(MColumn node, MGroupByColumn mtable) {
		MGroupBy mfrom = (MGroupBy) mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MGroupByColumn run(MColumn node, MGroupBy select, int index) {
		return new MGroupByColumn(select, node, index);
	}

}