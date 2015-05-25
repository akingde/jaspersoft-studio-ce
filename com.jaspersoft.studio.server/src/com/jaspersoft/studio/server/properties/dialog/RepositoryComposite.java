/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.properties.dialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.action.resource.RefreshResourcesAction;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class RepositoryComposite extends Composite {

	/**
	 * @param parent
	 * @param style
	 */
	public RepositoryComposite(Composite parent, int style, INode root) {
		super(parent, style);
		this.root = root;
		createWidget();
	}

	protected void createWidget() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);

		treeViewer = new TreeViewer(this, SWT.SINGLE);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());

		ColumnViewerToolTipSupport.enableFor(treeViewer);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection ts = (TreeSelection) event.getSelection();
				Object obj = ts.getFirstElement();
				if (obj instanceof MResource) {
					MResource mres = (MResource) obj;
					boolean resCompatible = isResourceCompatible(mres);
					setOkButtonEnabled(resCompatible);
					if (resCompatible)
						setResource((MResource) obj);
				}
			}
		});
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			private RefreshResourcesAction refreshAction;

			@Override
			public void doubleClick(DoubleClickEvent event) {
				TreeSelection ts = (TreeSelection) treeViewer.getSelection();
				Object el = ts.getFirstElement();
				if (el instanceof MResource) {
					MResource mres = (MResource) el;
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
								refreshAction = new RefreshResourcesAction(
										treeViewer);
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

			}
		});
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				createReadRepositoryJob();
			}
		});

	}

	protected void createReadRepositoryJob() {
		Job job = new Job("Looking into repository") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Looking into repository",
						IProgressMonitor.UNKNOWN);
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
			else if (root instanceof MResource)
				msp = (MServerProfile) ((MResource) root).getRoot();
			if (ModelUtils.isEmpty(msp))
				WSClientHelper.connectGetData(msp, monitor);
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					treeViewer.setInput(root);
				}
			});
		} catch (Exception e) {
			UIUtils.showError(e);
			return Status.CANCEL_STATUS;
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	public boolean isResourceCompatible(MResource r) {
		return true;
	}

	private INode root;
	protected MResource resource;
	private TreeViewer treeViewer;

	public void setResource(MResource res) {
		this.resource = res;
	}

	protected void okPressed() {

	}

	protected void setOkButtonEnabled(boolean resCompatible) {

	}

}
