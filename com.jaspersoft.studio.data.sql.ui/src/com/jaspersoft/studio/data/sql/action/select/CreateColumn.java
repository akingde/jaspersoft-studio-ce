package com.jaspersoft.studio.data.sql.action.select;

import java.util.Collection;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.dialogs.UsedColumnsDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.MFrom;
import com.jaspersoft.studio.data.sql.model.query.MSelect;
import com.jaspersoft.studio.data.sql.model.query.MSelectColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;

public class CreateColumn extends AAction {

	private CreateTable ct;

	public CreateColumn(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Add Column", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isInSelect(selection[0]);
	}

	public static boolean isInSelect(Object element) {
		return element instanceof MSelect || element instanceof MSelectColumn;
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
		for (MColumn t : nodes) {
			if (sel instanceof MSelect)
				sel = run(t, (MSelect) sel, 0);
			else if (sel instanceof MSelectColumn) {
				sel = run(t, (MSelectColumn) sel);
			}
			MSelectColumn msc = (MSelectColumn) sel;
			MSqlTable mstable = (MSqlTable) msc.getValue().getParent();
			if (!Util.getTables(msc).contains(mstable)) {
				if (ct == null)
					ct = new CreateTable(xtextDocument, designer);
				MRoot r = (MRoot) msc.getRoot();
				for (INode n : r.getChildren()) {
					if (n instanceof MFrom) {
						ct.run(mstable, (MFrom) n, -1);
						break;
					}
				}
			}
		}
		selectInTree(sel);
	}

	protected MSelectColumn run(MColumn node, MSelectColumn mtable) {
		MSelect mfrom = (MSelect) mtable.getParent();
		return run(node, mfrom, mfrom.getChildren().indexOf(mtable) + 1);
	}

	public MSelectColumn run(MColumn node, MSelect select, int index) {
		return new MSelectColumn(select, node, index);
	}

}