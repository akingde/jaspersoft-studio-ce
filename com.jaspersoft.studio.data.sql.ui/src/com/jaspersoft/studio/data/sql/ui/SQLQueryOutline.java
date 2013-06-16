package com.jaspersoft.studio.data.sql.ui;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.PluginTransfer;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
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
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.MExpressionGroup;
import com.jaspersoft.studio.data.sql.model.query.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.MGroupByColumn;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.orderby.AMOrderByMember;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByColumn;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderByExpression;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.dnd.NodeTreeDropAdapter;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;

public class SQLQueryOutline {
	private SQLQueryDesigner designer;

	public SQLQueryOutline(SQLQueryDesigner designer) {
		this.designer = designer;
	}

	private boolean isRefresh = false;

	public Control createOutline(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI) {

			@Override
			public void refresh(boolean updateLabels) {
				isRefresh = true;
				super.refresh(updateLabels);
				designer.refreshQuery();
				isRefresh = false;
			}
		};
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setUseHashlookup(true);
		ColumnViewerToolTipSupport.enableFor(treeViewer);

		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		afactory = new ActionFactory(designer);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				TreeSelection s = (TreeSelection) treeViewer.getSelection();
				afactory.fillMenu(s != null ? s.toArray() : null, mgr);
			}

		});
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);

		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { NodeTransfer.getInstance(), PluginTransfer.getInstance() };
		treeViewer.addDragSupport(ops, transfers, new NodeDragListener(treeViewer));

		transfers = new Transfer[] { NodeTransfer.getInstance(), PluginTransfer.getInstance() };
		NodeTreeDropAdapter dropAdapter = new NodeTreeDropAdapter(treeViewer) {

			@Override
			public boolean performDrop(Object data) {
				return doDrop(Util.getAllNodes(data));
			}

			private boolean doDrop(List<ANode> node) {
				Object target = getCurrentTarget();
				if (target instanceof ANode && ((ANode) target).getParent() == null)
					return false;

				Set<MSqlTable> tablesset = new LinkedHashSet<MSqlTable>();
				Set<MColumn> colsset = new LinkedHashSet<MColumn>();
				Set<ANode> others = new LinkedHashSet<ANode>();
				Util.filterTables(node, tablesset, colsset, others);

				doDropTable(target, tablesset);
				doDropColumn(target, colsset);
				if (!others.isEmpty()) {
					for (ANode n : others) {
						ANode oldNode = Util.getOldNode((ANode) target, n);
						if ((target instanceof MExpressionGroup || target instanceof MWhere || target instanceof MHaving || target instanceof MFromTableJoin)
								&& (n instanceof MExpression || n instanceof MExpressionGroup)) {
							oldNode.setParent(null, -1);
							oldNode.setParent((ANode) target, -1);
							refreshAndReveal(oldNode);
							continue;
						}
						if (target instanceof MExpression && (n instanceof MExpression || n instanceof MExpressionGroup)) {
							oldNode.setParent(null, -1);
							MExpression mexpr = (MExpression) target;
							ANode p = mexpr.getParent();
							oldNode.setParent(p, p.getChildren().indexOf(mexpr));

							refreshAndReveal(oldNode);
							continue;
						}
						if (n instanceof MSelectColumn || n instanceof MSelectExpression) {
							int ind = -1;
							// can drop also in a child of one of this
							if (target instanceof MWhere) {

							}
							if (target instanceof MGroupByColumn) {
								ANode p = ((MGroupByColumn) target).getParent();
								ind = p.getChildren().indexOf(target);
								target = p;
							}
							if (target instanceof MGroupBy) {
								if (n instanceof MSelectColumn)
									refreshAndReveal(new MGroupByColumn((MGroupBy) target, (MSelectColumn) n, ind));
								continue;
							}
							if (target instanceof MHaving) {

							}
							if (target instanceof AMOrderByMember) {
								ANode p = ((AMOrderByMember<?>) target).getParent();
								ind = p.getChildren().indexOf(target);
								target = p;
							}
							if (target instanceof MOrderBy) {
								if (n instanceof MSelectExpression)
									refreshAndReveal(new MOrderByExpression((MOrderBy) target, (MSelectExpression) n, ind));
								else
									refreshAndReveal(new MOrderByColumn((MOrderBy) target, (MSelectColumn) n, ind));
								continue;
							}
						}
						reorder(target, n);
					}
				}
				return false;
			}

			private void reorder(Object target, ANode n) {
				ANode parent = null;
				if (target instanceof AMKeyword)
					parent = (ANode) target;
				else if (target.getClass().isAssignableFrom(n.getClass()))
					parent = ((ANode) target).getParent();
				if (n.getParent().equals(parent)) {
					int ind = parent.getChildren().indexOf(n);
					if (ind >= 0 && ind < parent.getChildren().size()) {
						n = (ANode) parent.getChildren().get(ind);
						int pos = 0;
						if (target != parent)
							pos = parent.getChildren().indexOf(target);
						parent.removeChild(n);
						parent.addChild(n, pos);
						refreshAndReveal(n);
					}
				}
			}

			protected void doDropTable(Object target, Set<MSqlTable> tablesset) {
				if (!tablesset.isEmpty()) {
					CreateTable ct = afactory.getAction(CreateTable.class);
					if (ct.calculateEnabled(new Object[] { target }))
						ct.run(tablesset);
				}
			}

			protected void doDropColumn(Object target, Set<MColumn> colsset) {
				if (!colsset.isEmpty()) {
					CreateColumn ct = afactory.getAction(CreateColumn.class);
					if (ct.calculateEnabled(new Object[] { target }))
						ct.run(colsset);
					CreateGroupByColumn cg = afactory.getAction(CreateGroupByColumn.class);
					if (cg.calculateEnabled(new Object[] { target }))
						cg.run(colsset);
					CreateOrderByColumn co = afactory.getAction(CreateOrderByColumn.class);
					if (co.calculateEnabled(new Object[] { target }))
						co.run(colsset);
					CreateExpression ce = afactory.getAction(CreateExpression.class);
					if (ce.calculateEnabled(new Object[] { target }))
						ce.run(colsset);
				}
			}

		};
		treeViewer.addDropSupport(ops, new Transfer[] { NodeTransfer.getInstance() }, dropAdapter);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				runAction(event, afactory.getAction(SelectDistinct.class));
				runAction(event, afactory.getAction(OrderByDesc.class));
				runAction(event, afactory.getAction(ChangeOperator.class));
				runAction(event, afactory.getAction(EditColumn.class));
				runAction(event, afactory.getAction(EditTable.class));
				runAction(event, afactory.getAction(EditExpression.class));
			}

			private void runAction(DoubleClickEvent event, AAction sd) {
				if (sd.calculateEnabled(event.getSelection()))
					sd.run();
			}
		});
		refreshViewer();
		return treeViewer.getControl();
	}

	protected void refreshViewer() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				treeViewer.setInput(designer.getRoot());
				treeViewer.expandToLevel(1);
			}
		});
	}

	private TreeViewer treeViewer;
	private ActionFactory afactory;

	public ActionFactory getAfactory() {
		return afactory;
	}

	public void scheduleRefresh() {
		if (isRefresh)
			return;
		if (designer.getRoot() != null)
			designer.getRoot().setValue(designer.getjDataset());
		treeViewer.refresh(true);
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public void dispose() {

	}

	protected void refreshAndReveal(ANode toselect) {
		treeViewer.refresh(true);
		treeViewer.reveal(toselect);
	}
}
