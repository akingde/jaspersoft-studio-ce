package com.jaspersoft.studio.data.querydesigner.json;

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.data.json.JsonDataAdapter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.WorkbenchJob;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider;
import com.jaspersoft.studio.data.designer.tree.TreeBasedQueryDesigner;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.json.JsonSupportNode;

/**
 * Json query designer that provides a basic syntax highlighting support,
 * plus a tree viewer where the json file is visualized.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JsonQueryDesigner extends TreeBasedQueryDesigner {
	
	private static final int JOB_DELAY=300;
	private JsonDataManager jsonDataManager;
	private DecorateTreeViewerJob decorateJob;
	private NodeBoldStyledLabelProvider<JsonSupportNode> treeLabelProvider;

	public JsonQueryDesigner() {
		super();
		jsonDataManager=new JsonDataManager();
		this.decorateJob=new DecorateTreeViewerJob();
		this.treeLabelProvider=new NodeBoldStyledLabelProvider<JsonSupportNode>();
	}

	@Override
	protected IBaseLabelProvider getTreeLabelProvider() {
		return this.treeLabelProvider;
	}

	@Override
	protected IContentProvider getTreeContentProvider() {
		return new JsonTreeContentProvider();
	}

	@Override
	protected void decorateTreeUsingQueryText() {
		if(jsonDataManager.getJsonSupportModel()!=null){
			decorateJob.cancel();
			decorateJob.schedule(JOB_DELAY);
		}
	}

	@Override
	protected void refreshTreeViewerContent(final DataAdapterDescriptor da) {
		if(!isRefreshing){
			this.container.getQueryStatus().showInfo("");
			if(da!=null && da.getDataAdapter() instanceof JsonDataAdapter){
				treeViewer.setInput(JsonTreeCustomStatus.LOADING_JSON);
				
				Job j=new Job("Loading Json resource..."){
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							JsonQueryDesigner.this.run(true, true, new IRunnableWithProgress() {
								
								@Override
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									monitor.beginTask("Loading Json resource...", -1);
									String fileName = ((JsonDataAdapter)da.getDataAdapter()).getFileName();
									try {
										jsonDataManager.loadJsonDataFile(fileName);
										Display.getDefault().asyncExec(new Runnable() {
											@Override
											public void run() {
												treeViewer.setInput(jsonDataManager.getJsonSupportModel());
												treeViewer.expandToLevel(2);
												decorateTreeUsingQueryText();
												isRefreshing=false;
											}
										});
									} catch (Exception e){
										JsonQueryDesigner.this.container.getQueryStatus().showError(e);
										Display.getDefault().asyncExec(new Runnable() {
											@Override
											public void run() {
												treeViewer.getTree().removeAll();
												treeViewer.setInput(JsonTreeCustomStatus.ERROR_LOADING_JSON);
												isRefreshing=false;
											}
										});
									} finally {
										monitor.done();
									}
								}
							});
						} catch (Exception ex) {
							JsonQueryDesigner.this.container.getQueryStatus().showError(ex);
							Display.getDefault().asyncExec(new Runnable() {
								@Override
								public void run() {
									treeViewer.getTree().removeAll();
									treeViewer.setInput(JsonTreeCustomStatus.ERROR_LOADING_JSON);
									isRefreshing=false;
								}
							});
						}
						return Status.OK_STATUS;
					}
				};
				j.schedule();
			}
			else{
				treeViewer.getTree().removeAll();
				treeViewer.setInput(JsonTreeCustomStatus.FILE_NOT_FOUND);
				isRefreshing=false;
			}
		}
	}
	
	/*
	 * Content provider for the tree visualizing the json information.
	 */
	private final class JsonTreeContentProvider implements ITreeContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof MRoot){
				return ((MRoot) inputElement).getChildren().toArray();
			}
			if(inputElement instanceof JsonTreeCustomStatus){
				return new Object[]{inputElement};
			}
			return new Object[0];
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof JsonSupportNode){
				return ((JsonSupportNode) parentElement).getChildren().toArray();
			}
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			if(element instanceof JsonSupportNode){
				return ((JsonSupportNode) element).getParent();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return getChildren(element).length>0;
		}
		
	}
	
	/*
	 * Job that is responsible to update the treeviewer presentation 
	 * depending on the nodes selected by the Json query.
	 */
	private final class DecorateTreeViewerJob extends WorkbenchJob{

		public DecorateTreeViewerJob(){
			super("Refresh panel job");
			setSystem(true);
		}
		
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			monitor.beginTask("Decorating...", IProgressMonitor.UNKNOWN);
			String query=queryTextArea.getText();
			treeLabelProvider.setSelectedNodes(jsonDataManager.getSelectableNodes(query));
			treeViewer.refresh();
			monitor.done();
			return Status.OK_STATUS;
		}
		
	}

	@Override
	public void dispose() {
		if(decorateJob!=null){
			decorateJob.cancel();
			decorateJob=null;
		}
		super.dispose();
	}
	
}
