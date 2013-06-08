package com.jaspersoft.studio.data.sql.ui;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;
import org.eclipse.xtext.ui.editor.model.XtextDocumentUtil;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.IOutlineTreeProvider;
import org.eclipse.xtext.ui.util.DisplayRunHelper;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Injector;
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
import com.jaspersoft.studio.data.sql.model.query.MFrom;
import com.jaspersoft.studio.data.sql.model.query.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.MSelect;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.dnd.NodeTreeDropAdapter;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;

public class SQLQueryOutline {
	private SQLQueryDesigner designer;
	private TextViewer textViewer;

	public SQLQueryOutline(Injector injector, SQLQueryDesigner designer) {
		injector.injectMembers(refreshJob);
		this.designer = designer;
	}

	public void setSourceViewer(TextViewer textViewer) {
		this.textViewer = textViewer;
		xtextDocument = XtextDocumentUtil.get(textViewer);
		Assert.isNotNull(xtextDocument);
	}

	public Control createOutline(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.SINGLE) {

			@Override
			public void refresh(boolean updateLabels) {
				super.refresh(updateLabels);
				if (root != null) {
					boolean update = false;
					for (INode c : root.getChildren())
						if (!c.getChildren().isEmpty()) {
							update = true;
							break;
						}
					if (update)
						designer.refreshQuery();
				}
			}
		};
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setUseHashlookup(true);
		ColumnViewerToolTipSupport.enableFor(treeViewer);

		List<IOutlineNode> initiallyExpandedNodes = xtextDocument.readOnly(new IUnitOfWork<List<IOutlineNode>, XtextResource>() {
			public List<IOutlineNode> exec(XtextResource resource) throws Exception {
				return getInitiallyExpandedNodes();
			}
		});
		refreshViewer(initiallyExpandedNodes.get(0), initiallyExpandedNodes, Collections.<IOutlineNode> emptySet());

		configureModelListener();
		refreshJob.setOutlinePage(this);
		configureTextInputListener();
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		afactory = new ActionFactory(menuMgr, xtextDocument, treeViewer, designer);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				TreeSelection s = (TreeSelection) treeViewer.getSelection();
				afactory.fillMenu(s != null ? s.toArray() : null);
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
				List<ANode> nodes = new ArrayList<ANode>();
				if (data.getClass().isArray()) {
					Object[] ar = (Object[]) data;
					for (Object obj : ar)
						if (obj instanceof ANode)
							nodes.add((ANode) obj);
				} else if (data instanceof ANode)
					nodes.add((ANode) data);
				return doDrop(nodes);
			}

			private boolean doDrop(List<ANode> node) {
				Object target = getCurrentTarget();
				if (target instanceof ANode && ((ANode) target).getParent() == null)
					return false;

				Set<MSqlTable> tablesset = new LinkedHashSet<MSqlTable>();
				Set<MColumn> colsset = new LinkedHashSet<MColumn>();
				Set<ANode> others = new LinkedHashSet<ANode>();
				for (ANode n : node) {
					if (n instanceof MSqlTable)
						tablesset.add((MSqlTable) n);
					else if (n instanceof MColumn)
						colsset.add((MColumn) n);
					else
						others.add(n);
				}
				doDropTable(target, tablesset);
				doDropColumn(target, colsset);
				if (!others.isEmpty()) {
					for (ANode n : others) {
						ANode oldNode = Util.getOldNode((ANode) target, n);
						if (target instanceof MExpressionGroup || target instanceof MWhere || target instanceof MHaving || target instanceof MFromTableJoin) {
							if (n instanceof MExpression || n instanceof MExpressionGroup) {
								oldNode.setParent(null, -1);
								oldNode.setParent((ANode) target, -1);
								treeViewer.refresh(true);
								treeViewer.reveal(oldNode);
								continue;
							}
						} else if (target instanceof MExpression) {
							if (n instanceof MExpression || n instanceof MExpressionGroup) {
								oldNode.setParent(null, -1);
								MExpression mexpr = (MExpression) target;
								ANode p = mexpr.getParent();
								oldNode.setParent(p, p.getChildren().indexOf(mexpr));

								treeViewer.refresh(true);
								treeViewer.reveal(oldNode);
								continue;
							}
						} else
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
						treeViewer.refresh(true);
						treeViewer.reveal(n);
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

		return treeViewer.getControl();
	}

	protected List<IOutlineNode> getInitiallyExpandedNodes() {
		IOutlineNode rootNode = treeProvider.createRoot(xtextDocument);
		List<IOutlineNode> result = newArrayList(rootNode);
		addChildren(Collections.singletonList(rootNode), result, getDefaultExpansionLevel());
		return result;
	}

	protected int getDefaultExpansionLevel() {
		return 1;
	}

	protected void addChildren(List<IOutlineNode> nodes, List<IOutlineNode> allChildren, int depth) {
		for (IOutlineNode node : nodes) {
			List<IOutlineNode> children = node.getChildren();
			if (depth > 1) {
				allChildren.addAll(children);
				addChildren(children, allChildren, depth - 1);
			}
		}
	}

	private IXtextModelListener modelListener;

	protected void configureModelListener() {
		modelListener = new IXtextModelListener() {
			public void modelChanged(XtextResource resource) {
				try {
					scheduleRefresh();
				} catch (Throwable t) {
					UIUtils.showError(t);
				}
			}

		};
		xtextDocument.addModelListener(modelListener);
	}

	private MRoot root;

	protected void refreshViewer(final IOutlineNode rootNode, final Collection<IOutlineNode> nodesToBeExpanded, final Collection<IOutlineNode> selectedNodes) {
		DisplayRunHelper.runAsyncInDisplayThread(new Runnable() {

			public void run() {
				try {
					if (!treeViewer.getTree().isDisposed()) {
						if (root != null)
							root.removeChildren();
						else
							root = new MRoot(null, designer.getjDataset());
						new MSelect(root);
						new MFrom(root);
						new MWhere(root);
						new MGroupBy(root);
						new MHaving(root);
						new MOrderBy(root);
						treeViewer.setInput(root);
						// treeViewer.setInput(rootNode);
						treeViewer.expandToLevel(1);
						treeViewer.setExpandedElements(Iterables.toArray(nodesToBeExpanded, IOutlineNode.class));
						treeViewer.setSelection(new StructuredSelection(Iterables.toArray(selectedNodes, IOutlineNode.class)));

					}
				} catch (Throwable t) {
					UIUtils.showError(t);
				}
			}
		});
	}

	private IXtextDocument xtextDocument;

	private ReportTreeContetProvider contentProvider = new ReportTreeContetProvider();
	@Inject
	private IOutlineTreeProvider treeProvider;

	private TreeViewer treeViewer;
	private ITextInputListener textInputListener;

	protected void configureTextInputListener() {
		textInputListener = new ITextInputListener() {
			public void inputDocumentChanged(IDocument oldInput, IDocument newInput) {
				try {
					if (xtextDocument != null && modelListener != null)
						xtextDocument.removeModelListener(modelListener);
					xtextDocument = XtextDocumentUtil.get(newInput);
					if (xtextDocument != null && modelListener != null) {
						xtextDocument.addModelListener(modelListener);
						scheduleRefresh();
					}
				} catch (Throwable t) {
					UIUtils.showError(t);
				}
			}

			public void inputDocumentAboutToBeChanged(IDocument oldInput, IDocument newInput) {
			}
		};
		textViewer.addTextInputListener(textInputListener);
	}

	private OutlineRefreshJob refreshJob = new OutlineRefreshJob();
	private ActionFactory afactory;

	public void scheduleRefresh() {
		if (root != null)
			root.setValue(designer.getjDataset());
		// refreshJob.cancel();
		// refreshJob.schedule();
	}

	public IXtextDocument getXtextDocument() {
		return xtextDocument;
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public IOutlineTreeProvider getTreeProvider() {
		return treeProvider;
	}

	public void dispose() {
		textViewer.removeTextInputListener(textInputListener);
		if (modelListener != null) {
			xtextDocument.removeModelListener(modelListener);
			modelListener = null;
		}
		contentProvider.dispose();
	}
}
