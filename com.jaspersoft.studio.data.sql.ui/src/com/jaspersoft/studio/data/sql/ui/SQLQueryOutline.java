package com.jaspersoft.studio.data.sql.ui;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.TextViewer;
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
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineNodeContentProvider;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineNodeLabelProvider;
import org.eclipse.xtext.ui.util.DisplayRunHelper;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.action.table.CreateTable;
import com.jaspersoft.studio.data.sql.model.MColumn;
import com.jaspersoft.studio.data.sql.model.MSqlTable;
import com.jaspersoft.studio.data.sql.model.MView;
import com.jaspersoft.studio.data.ui.outline.JSSEObjectNode;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.dnd.NodeTreeDropAdapter;
import com.jaspersoft.studio.model.ANode;

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
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.SINGLE);
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setContentProvider(contentProvider);
		contentProvider.setFilterAndSorter(filterAndSorter);
		treeViewer.setUseHashlookup(true);

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
		final ActionFactory afactory = new ActionFactory(menuMgr, xtextDocument, treeViewer, designer);
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
				if (data.getClass().isArray()) {
					Object[] ar = (Object[]) data;
					for (Object obj : ar)
						if (obj instanceof ANode)
							doDrop((ANode) obj);
				} else if (data instanceof ANode)
					doDrop((ANode) data);
				return false;
			}

			private void doDrop(ANode node) {
				Object target = getCurrentTarget();
				System.out.println(node + " ------ " + target + " : " + ((JSSEObjectNode) target).getEObject());
				EObject eobj = ((JSSEObjectNode) target).getEObject();
				if (node instanceof MSqlTable || node instanceof MView) {
					new CreateTable(xtextDocument, designer).run((MSqlTable) node, eobj);
				} else if (node instanceof MColumn) {

				}
			}
		};
		treeViewer.addDropSupport(ops, transfers, dropAdapter);

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

	protected void refreshViewer(final IOutlineNode rootNode, final Collection<IOutlineNode> nodesToBeExpanded, final Collection<IOutlineNode> selectedNodes) {
		DisplayRunHelper.runAsyncInDisplayThread(new Runnable() {
			public void run() {
				try {
					if (!treeViewer.getTree().isDisposed()) {
						treeViewer.setInput(rootNode);
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
	@Inject
	private OutlineNodeLabelProvider labelProvider;

	@Inject
	private OutlineNodeContentProvider contentProvider;
	@Inject
	private IOutlineTreeProvider treeProvider;

	@Inject
	private OutlineFilterAndSorter filterAndSorter;
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

	public void scheduleRefresh() {
		refreshJob.cancel();
		refreshJob.schedule();
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
