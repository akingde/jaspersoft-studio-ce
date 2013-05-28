package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.action.table.DeleteTable;

public class ActionFactory {
	private List<AAction> actions = new ArrayList<AAction>();
	private MenuManager menuMgr;

	public ActionFactory(MenuManager menuMgr, IXtextDocument xtextDocument) {
		this.menuMgr = menuMgr;
		actions.add(new CreateTable(xtextDocument));
		actions.add(new DeleteTable(xtextDocument));
	}

	public void fillMenu(Object[] selection) {
		for (AAction act : actions)
			if (act.calculateEnabled(selection))
				menuMgr.add(act);
	}
}
