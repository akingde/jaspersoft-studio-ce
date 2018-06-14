/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.properties.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MDummy;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.action.resource.RefreshResourcesAction;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class RepositoryComposite extends Composite {
	private boolean showCompatible;

	/**
	 * @param parent
	 * @param style
	 */
	public RepositoryComposite(Composite parent, int style, INode root, boolean showCompatible) {
		super(parent, style);
		this.showCompatible = showCompatible;
		this.root = root;
		createComposite();
	}

	protected void createComposite() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);

		treeViewer = new TreeViewer(this, SWT.SINGLE);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		treeViewer.setContentProvider(new ReportTreeContetProvider() {
			@Override
			public Object[] getChildren(Object parentElement) {
				if (showCompatible)
					return super.getChildren(parentElement);
				if (parentElement instanceof INode) {
					INode node = (INode) parentElement;
					List<INode> res = new ArrayList<>();
					for (INode n : node.getChildren()) {
						if (n instanceof MFolder || n instanceof MDummy
								|| (n instanceof AMResource && isResourceCompatible((AMResource) n)))
							res.add(n);
					}
					return res.toArray();
				}
				return EMPTY_ARRAY;
			}
		});
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());

		ColumnViewerToolTipSupport.enableFor(treeViewer);
		treeViewer.addSelectionChangedListener(event -> {
			TreeSelection ts = (TreeSelection) event.getSelection();
			Object obj = ts.getFirstElement();
			if (obj instanceof AMResource) {
				AMResource mres = (AMResource) obj;
				boolean resCompatible = isResourceCompatible(mres);
				setOkButtonEnabled(resCompatible);
				if (resCompatible)
					setResource((AMResource) obj);
			}
		});
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			private RefreshResourcesAction refreshAction;

			@Override
			public void doubleClick(DoubleClickEvent event) {
				TreeSelection ts = (TreeSelection) treeViewer.getSelection();
				Object el = ts.getFirstElement();
				if (el instanceof AMResource) {
					AMResource mres = (AMResource) el;
					boolean resCompatible = isResourceCompatible(mres);
					if (resCompatible) {
						okPressed();
						return;
					}
					if (mres instanceof MFolder) {
						if (treeViewer.getExpandedState(el))
							treeViewer.collapseToLevel(el, 1);
						else {
							if (refreshAction == null)
								refreshAction = new RefreshResourcesAction(treeViewer);
							if (refreshAction.isEnabled())
								refreshAction.run();
							treeViewer.expandToLevel(el, 1);
						}
					}
				}
			}
		});
		treeViewer.addTreeListener(new ITreeViewerListener() {

			private ServerProvider serverProvider;

			public void treeExpanded(TreeExpansionEvent event) {
				if (serverProvider == null)
					serverProvider = new ServerProvider();
				serverProvider.handleTreeEvent(event);
			}

			public void treeCollapsed(TreeExpansionEvent event) {
				// nothing to do
			}
		});
		UIUtils.getDisplay().asyncExec(this::createReadRepositoryJob);

	}

	protected void createReadRepositoryJob() {
		Job job = new Job("Looking into repository") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Looking into repository", IProgressMonitor.UNKNOWN);
				return doReadRepository(monitor);
			}

		};
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	protected IStatus doReadRepository(IProgressMonitor monitor) {
		try {
			MServerProfile msp = null;
			if (root instanceof MServerProfile)
				msp = (MServerProfile) root;
			else if (root instanceof AMResource)
				msp = (MServerProfile) ((AMResource) root).getRoot();
			if (ModelUtils.isEmpty(msp))
				WSClientHelper.connectGetData(msp, monitor);
			UIUtils.getDisplay().asyncExec(() -> treeViewer.setInput(root));
		} catch (Exception e) {
			UIUtils.showError(e);
			return Status.CANCEL_STATUS;
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	public boolean isResourceCompatible(AMResource r) {
		return true;
	}

	private INode root;
	protected AMResource resource;
	private TreeViewer treeViewer;

	public void setResource(AMResource res) {
		this.resource = res;
	}

	protected void okPressed() {
		// nothing to do
	}

	protected void setOkButtonEnabled(boolean resCompatible) {
		// nothing to do
	}

}
