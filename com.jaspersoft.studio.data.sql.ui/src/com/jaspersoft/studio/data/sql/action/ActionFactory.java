package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.column.CreateColumn;
import com.jaspersoft.studio.data.sql.action.groupby.CreateGroupByColumn;
import com.jaspersoft.studio.data.sql.action.order.CreateOrderByColumn;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.action.table.DeleteTable;

public class ActionFactory {
	private List<AAction> actions = new ArrayList<AAction>();
	private MenuManager menuMgr;

	public ActionFactory(MenuManager menuMgr, IXtextDocument xtextDocument, TreeViewer treeViewer, SQLQueryDesigner designer) {
		this.menuMgr = menuMgr;
		actions.add(new CreateColumn(xtextDocument, designer));
		actions.add(null);
		actions.add(new CreateTable(xtextDocument, designer));
		actions.add(new DeleteTable(xtextDocument, designer));
		actions.add(null);
		actions.add(new CreateGroupByColumn(xtextDocument, designer));
		actions.add(null);
		actions.add(new CreateOrderByColumn(xtextDocument, designer));
	}

	public void fillMenu(Object[] selection) {
		for (AAction act : actions) {
			if (act == null)
				menuMgr.add(new org.eclipse.jface.action.Separator());
			else if (act.calculateEnabled(selection))
				menuMgr.add(act);
		}
	}
}
