package com.jaspersoft.studio.data.sql.ui;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

public class SQLQueryOutline {
	private TextViewer textViewer;

	public SQLQueryOutline(Injector injector) {
		injector.injectMembers(refreshJob);
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
