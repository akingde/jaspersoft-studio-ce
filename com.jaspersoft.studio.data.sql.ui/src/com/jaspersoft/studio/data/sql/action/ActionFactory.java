package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.expression.ChangeOperator;
import com.jaspersoft.studio.data.sql.action.expression.CreateExpression;
import com.jaspersoft.studio.data.sql.action.expression.CreateExpressionGroup;
import com.jaspersoft.studio.data.sql.action.expression.EditExpression;
import com.jaspersoft.studio.data.sql.action.groupby.CreateGroupByColumn;
import com.jaspersoft.studio.data.sql.action.order.CreateOrderByColumn;
import com.jaspersoft.studio.data.sql.action.order.OrderByDesc;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.select.CreateSelectExpression;
import com.jaspersoft.studio.data.sql.action.select.EditColumn;
import com.jaspersoft.studio.data.sql.action.select.SelectDistinct;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.action.table.DeleteTableJoin;
import com.jaspersoft.studio.data.sql.action.table.EditTable;
import com.jaspersoft.studio.data.sql.action.table.EditTableJoin;
import com.jaspersoft.studio.data.sql.action.table.JoinTable;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;

public class ActionFactory {
	private List<AAction> actions = new ArrayList<AAction>();
	private MenuManager menuMgr;

	public ActionFactory(MenuManager menuMgr, TreeViewer treeViewer, SQLQueryDesigner designer) {
		this.menuMgr = menuMgr;
		actions.add(new SelectDistinct(designer));
		actions.add(null);
		actions.add(new CreateColumn(designer));
		actions.add(new CreateSelectExpression(designer));
		actions.add(new EditColumn(designer));
		actions.add(new DeleteAction<MSelectColumn>(designer, "Column", MSelectColumn.class));
		actions.add(null);
		actions.add(new JoinTable(designer));
		actions.add(new EditTableJoin(designer));
		actions.add(new DeleteTableJoin(designer));
		actions.add(null);
		actions.add(new CreateTable(designer));
		actions.add(new EditTable(designer));
		actions.add(new DeleteAction<MFromTable>(designer, "Table", MFromTable.class));
		actions.add(null);
		actions.add(new CreateGroupByColumn(designer));
		actions.add(new DeleteAction<MGroupByColumn>(designer, "Column", MGroupByColumn.class));
		actions.add(null);
		actions.add(new CreateOrderByColumn(designer));
		actions.add(new OrderByDesc(designer));
		actions.add(new DeleteAction<MOrderByColumn>(designer, "Column", MOrderByColumn.class));

		actions.add(null);
		actions.add(new CreateExpressionGroup(designer));
		actions.add(new CreateExpression(designer));
		actions.add(new ChangeOperator(designer));
		actions.add(new EditExpression(designer));
		actions.add(new DeleteAction<MExpression>(designer, "Expression", MExpression.class));
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
