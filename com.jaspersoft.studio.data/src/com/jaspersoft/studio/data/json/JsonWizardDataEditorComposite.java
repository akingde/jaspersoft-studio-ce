/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.json;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.progress.WorkbenchJob;

import com.jaspersoft.studio.data.ATreeWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.querydesigner.json.JsonDataManager;
import com.jaspersoft.studio.data.querydesigner.json.JsonLineStyler;
import com.jaspersoft.studio.data.querydesigner.json.JsonTreeContentProvider;
import com.jaspersoft.studio.data.querydesigner.json.JsonTreeCustomStatus;
import com.jaspersoft.studio.model.datasource.json.JsonSupportNode;

import net.sf.jasperreports.data.json.JsonDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Editor composite for the Json query language. This is supposed to used by
 * {@link JsonDataAdapterDescriptor}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JsonWizardDataEditorComposite extends ATreeWizardDataEditorComposite {

	private static final int JOB_DELAY = 300;
	private JsonDataManager jsonDataManager;
	private DecorateTreeViewerJob decorateJob;
	private NodeBoldStyledLabelProvider<JsonSupportNode> treeLabelProvider;
	private JsonTreeContentProvider treeContentProvider;
	private JsonLineStyler lineStyler;

	public JsonWizardDataEditorComposite(Composite parent, WizardPage page,
			DataAdapterDescriptor dataAdapterDescriptor) {
		super(parent, page, dataAdapterDescriptor);
	}

	@Override
	protected void init() {
		super.init();
		this.jsonDataManager = new JsonDataManager(getQueryLanguage());
		this.lineStyler = new JsonLineStyler();
		this.decorateJob = new DecorateTreeViewerJob();
		this.treeLabelProvider = new NodeBoldStyledLabelProvider<>();
		this.treeContentProvider = new JsonTreeContentProvider();
	}

	@Override
	protected IBaseLabelProvider getTreeLabelProvider() {
		return this.treeLabelProvider;
	}

	@Override
	protected IContentProvider getTreeContentProvider() {
		return this.treeContentProvider;
	}

	@Override
	protected void createTreeViewer(Composite parent) {
		super.createTreeViewer(parent);
		addDoubleClickSupport();
	}

	@Override
	protected void createQueryTextArea(Composite parent) {
		super.createQueryTextArea(parent);
		queryTextArea.addLineStyleListener(lineStyler);
	}

	@Override
	protected void refreshTreeViewerContent(final DataAdapterDescriptor da) {
		if (da != null && da.getDataAdapter() instanceof JsonDataAdapter) {
			treeViewer.setInput(JsonTreeCustomStatus.LOADING_JSON);
			UIUtils.getDisplay().asyncExec(() -> {
				try {
					jsonDataManager.loadJsonDataFile(((JsonDataAdapter) da.getDataAdapter()).getDataFile(),
							getJasperReportsConfiguration(), getDataset());
					treeViewer.setInput(jsonDataManager.getJsonSupportModel());
					treeViewer.expandToLevel(2);
					decorateTreeUsingQueryText();
				} catch (Exception e) {
					getStatusBar().showError(e);
					treeViewer.getTree().removeAll();
					treeViewer.setInput(JsonTreeCustomStatus.ERROR_LOADING_JSON);
				}
			});
		} else {
			treeViewer.getTree().removeAll();
			treeViewer.setInput(JsonTreeCustomStatus.FILE_NOT_FOUND);
		}
	}

	/*
	 * Adds support for generating the query expression, using the current selected
	 * node as input.
	 */
	private void addDoubleClickSupport() {
		treeViewer.addDoubleClickListener(event -> {
			TreeSelection s = (TreeSelection) treeViewer.getSelection();
			if (s.getFirstElement() instanceof JsonSupportNode) {
				JsonSupportNode jsonNode = (JsonSupportNode) s.getFirstElement();
				String queryExpression = jsonDataManager.getQueryExpression(null, jsonNode);
				queryTextArea.setText(queryExpression);
			}
		});
	}

	@Override
	protected void decorateTreeUsingQueryText() {
		if (jsonDataManager.getJsonSupportModel() != null) {
			decorateJob.cancel();
			decorateJob.schedule(JOB_DELAY);
		}
	}

	/*
	 * Job that is responsible to update the treeviewer presentation depending on
	 * the nodes selected by the Json query.
	 */
	private final class DecorateTreeViewerJob extends WorkbenchJob {

		public DecorateTreeViewerJob() {
			super(Messages.JsonWizardDataEditorComposite_Job);
			setSystem(true);
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			if (!isDisposed()) {
				monitor.beginTask(Messages.JsonWizardDataEditorComposite_Task, IProgressMonitor.UNKNOWN);
				String query = queryTextArea.getText();
				treeLabelProvider.setSelectedNodes(jsonDataManager.getSelectableNodes(query));
				treeViewer.refresh();
				monitor.done();
				return Status.OK_STATUS;
			} else {
				return Status.CANCEL_STATUS;
			}
		}

	}

	@Override
	public void dispose() {
		if (decorateJob != null) {
			decorateJob.cancel();
			decorateJob = null;
		}
		super.dispose();
	}

	@Override
	public String getQueryLanguage() {
		JsonDataAdapter da = (JsonDataAdapter) getDataAdapterDescriptor().getDataAdapter();
		return da.getLanguage().getName();
		// return "json"; //$NON-NLS-1$
	}

}
