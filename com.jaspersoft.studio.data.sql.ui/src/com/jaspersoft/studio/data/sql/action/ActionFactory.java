package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.expression.ChangeOperator;
import com.jaspersoft.studio.data.sql.action.expression.CreateExpression;
import com.jaspersoft.studio.data.sql.action.expression.EditExpression;
import com.jaspersoft.studio.data.sql.action.groupby.CreateGroupByColumn;
import com.jaspersoft.studio.data.sql.action.order.CreateOrderByColumn;
import com.jaspersoft.studio.data.sql.action.order.OrderByDesc;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.select.EditColumn;
import com.jaspersoft.studio.data.sql.action.select.SelectDistinct;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.action.table.EditTable;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.MOrderByColumn;

public class ActionFactory {
	private List<AAction> actions = new ArrayList<AAction>();
	private MenuManager menuMgr;

	public ActionFactory(MenuManager menuMgr, IXtextDocument xtextDocument, TreeViewer treeViewer, SQLQueryDesigner designer) {
		this.menuMgr = menuMgr;
		actions.add(new SelectDistinct(xtextDocument, designer));
		actions.add(null);
		actions.add(new CreateColumn(xtextDocument, designer));
		actions.add(new EditColumn(xtextDocument, designer));
		actions.add(new DeleteAction<MColumn>(xtextDocument, designer, "Column", MColumn.class));
		actions.add(null);
		actions.add(new CreateTable(xtextDocument, designer));
		actions.add(new EditTable(xtextDocument, designer));
		actions.add(new DeleteAction<MFromTable>(xtextDocument, designer, "Table", MFromTable.class));
		actions.add(null);
		actions.add(new CreateGroupByColumn(xtextDocument, designer));
		actions.add(new DeleteAction<MGroupByColumn>(xtextDocument, designer, "Column", MGroupByColumn.class));
		actions.add(null);
		actions.add(new CreateOrderByColumn(xtextDocument, designer));
		actions.add(new OrderByDesc(xtextDocument, designer));
		actions.add(new DeleteAction<MOrderByColumn>(xtextDocument, designer, "Column", MOrderByColumn.class));

		actions.add(null);
		actions.add(new CreateExpression(xtextDocument, designer));
		actions.add(new ChangeOperator(xtextDocument, designer));
		actions.add(new EditExpression(xtextDocument, designer));
		actions.add(new DeleteAction<MExpression>(xtextDocument, designer, "Expression", MExpression.class));
	}

	@SuppressWarnings("unchecked")
	public <T extends AAction> T getAction(Class<T> aclass) {
		for (AAction act : actions) {
			if (act != null && aclass.isAssignableFrom(act.getClass()))
				return (T) act;
		}
		return null;
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
