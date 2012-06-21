package com.jaspersoft.studio.data.querydesigner.xpath;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;
import net.sf.jasperreports.data.xml.XmlDataAdapter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.progress.WorkbenchJob;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider;
import com.jaspersoft.studio.data.designer.tree.TreeBasedQueryDesigner;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.xml.XMLAttributeNode;
import com.jaspersoft.studio.model.datasource.xml.XMLNode;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Query designer for the XPath language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XPathQueryDesigner extends TreeBasedQueryDesigner {
	
	private static final int JOB_DELAY=300;
	private XMLDocumentManager documentManager;
	private DecorateTreeViewerJob decorateJob;
	private NodeBoldStyledLabelProvider<XMLNode> treeLabelProvider;

	public XPathQueryDesigner(){
		super();
		this.documentManager=new XMLDocumentManager();
		this.decorateJob=new DecorateTreeViewerJob();
		this.treeLabelProvider=new NodeBoldStyledLabelProvider<XMLNode>();
	}
	
	@Override
	protected void createTitleBar(Composite parent) {
		Label titleLabel=new Label(parent,SWT.WRAP);
		titleLabel.setText(Messages.XPathQueryDesigner_InfoTitle);
		titleLabel.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false));
	}

	@Override
	protected void decorateTreeUsingQueryText() {
		if(documentManager.isDocumentSet()){
			decorateJob.cancel();
			decorateJob.schedule(JOB_DELAY);
		}
	}

	@Override
	protected void createTreeViewer(Composite parent) {
		super.createTreeViewer(parent);
		addDragSupport();
		createContextualMenu();
	}

	@Override
	protected IBaseLabelProvider getTreeLabelProvider() {
		return this.treeLabelProvider;
	}

	@Override
	protected IContentProvider getTreeContentProvider() {
		return new XPathTreeViewerContentProvider();
	}
	
	/*
	 * Adds drag support to the xml tree viewer. 
	 */
	private void addDragSupport() {
		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { NodeTransfer.getInstance(),
				PluginTransfer.getInstance() };
		treeViewer.addDragSupport(ops, transfers, new NodeDragListener(
				treeViewer) {
			@Override
			public void dragStart(DragSourceEvent event) {
				TreeSelection s = (TreeSelection) treeViewer.getSelection();
				if(s.getFirstElement() instanceof XMLNode){
					XMLNode xmlNode=(XMLNode) s.getFirstElement();
					xmlNode.setXPathExpression(
							documentManager.getXPathExpression(queryTextArea.getText(), xmlNode));
					event.doit = !s.isEmpty();
				}
				else{
					event.doit=false;
				}
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				if (!event.doit)
					return;
			}
		});
	}

	/*
	 * Creates the contextual menu for the tree representing the XML document. 
	 */
	private void createContextualMenu() {
		Menu contextMenu=new Menu(treeViewer.getTree());
		final MenuItem setRecordNodeItem=new MenuItem(contextMenu, SWT.PUSH);
		setRecordNodeItem.setText(Messages.XPathQueryDesigner_SetRecordItem);
		setRecordNodeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
				if(sel instanceof XMLNode){
					String xPathExpression = documentManager.getXPathExpression(null,(XMLNode)sel);
					queryTextArea.setText((xPathExpression!=null) ? xPathExpression : ""); //$NON-NLS-1$
				}
			}
		});
		final MenuItem setDocumentRootItem=new MenuItem(contextMenu, SWT.PUSH);
		setDocumentRootItem.setText(Messages.XPathQueryDesigner_SetDocRootItem);
		setDocumentRootItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
				try {
					Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
					Node originalNode = documentManager.getDocumentNodesMap().get(sel);
					Node importedNode = newDocument.importNode(originalNode, true);
					newDocument.appendChild(importedNode);
					documentManager.setDocument(newDocument);
					treeViewer.setInput(documentManager.getXMLDocumentModel());
				} catch (Exception e1) {
					UIUtils.showError(e1);
				}
			}
		});
		new MenuItem(contextMenu, SWT.SEPARATOR);
		final MenuItem addNodeAsFieldItem1=new MenuItem(contextMenu, SWT.PUSH);
		addNodeAsFieldItem1.setText(Messages.XPathQueryDesigner_AddAsFieldItem);
		addNodeAsFieldItem1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
				if(sel instanceof XMLNode){
					String xPathExpression = documentManager.getXPathExpression(queryTextArea.getText(),(XMLNode)sel);
					((XMLNode)sel).setXPathExpression(xPathExpression);
					createField((XMLNode)sel);
				}
			}
		});
		final MenuItem addNodeAsFieldItem2=new MenuItem(contextMenu, SWT.PUSH);
		addNodeAsFieldItem2.setText(Messages.XPathQueryDesigner_AddAsFieldAbsoluteItem);
		addNodeAsFieldItem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
				if(sel instanceof XMLNode){
					String xPathExpression = documentManager.getXPathExpression(null,(XMLNode)sel);
					((XMLNode)sel).setXPathExpression(xPathExpression);
					createField((XMLNode)sel);
				}
			}
		});
		new MenuItem(contextMenu, SWT.SEPARATOR);
		final MenuItem expandAllItem=new MenuItem(contextMenu, SWT.PUSH);
		expandAllItem.setText(Messages.XPathQueryDesigner_ExpandAllItem);
		expandAllItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				treeViewer.expandAll();
			}
		});
		final MenuItem collapseAllItem=new MenuItem(contextMenu, SWT.PUSH);
		collapseAllItem.setText(Messages.XPathQueryDesigner_CollapseAllItem);
		collapseAllItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				treeViewer.collapseAll();
			}
		});
		final MenuItem resetRefreshDocItem=new MenuItem(contextMenu, SWT.PUSH);
		resetRefreshDocItem.setText(Messages.XPathQueryDesigner_RefreshItem);
		resetRefreshDocItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshTreeViewerContent(container.getDataAdapter());
			}
		});
		treeViewer.getTree().setMenu(contextMenu);
		
		contextMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuShown(MenuEvent e) {
				Object selEl = ((IStructuredSelection)treeViewer.getSelection()).getFirstElement();
				if(selEl instanceof XMLNode){
					addNodeAsFieldItem1.setEnabled(true);
					addNodeAsFieldItem2.setEnabled(true);
					if(selEl instanceof XMLAttributeNode){
						setRecordNodeItem.setEnabled(false);
						setDocumentRootItem.setEnabled(false);
					}
					else{
						setRecordNodeItem.setEnabled(true);
						setDocumentRootItem.setEnabled(true);
					}
				}
				else{
					setRecordNodeItem.setEnabled(false);
					setDocumentRootItem.setEnabled(false);
					addNodeAsFieldItem1.setEnabled(false);
					addNodeAsFieldItem2.setEnabled(false);
				}
			}
			
			@Override
			public void menuHidden(MenuEvent e) {
				
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see com.jaspersoft.studio.data.designer.TreeBasedQueryDesigner#refreshTreeViewerContent(com.jaspersoft.studio.data.DataAdapterDescriptor)
	 */
	protected void refreshTreeViewerContent(final DataAdapterDescriptor da){
		if(!isRefreshing){
			isRefreshing=true;
			this.container.getQueryStatus().showInfo(""); //$NON-NLS-1$
			if(da!=null && da.getDataAdapter() instanceof XmlDataAdapter) {
				treeViewer.setInput(XMLTreeCustomStatus.LOADING_XML);
			
				Job j=new Job(Messages.XPathQueryDesigner_JobTitle){
	
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							XPathQueryDesigner.this.run(true, true, new IRunnableWithProgress() {
								
								@Override
								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException {
									
									monitor.beginTask(Messages.XPathQueryDesigner_TaskTitle, -1);
									
									String fileName = ((XmlDataAdapter)da.getDataAdapter()).getFileName();
									try {
										Document doc=null;
										if(da.getDataAdapter() instanceof RemoteXmlDataAdapter){
											doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
										}
										else {
											File in = new File(fileName);
											doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
										}
										documentManager.setDocument(doc);
										Display.getDefault().asyncExec(new Runnable() {
											@Override
											public void run() {
												treeViewer.setInput(documentManager.getXMLDocumentModel());
												treeViewer.expandToLevel(2);
												decorateTreeUsingQueryText();
												isRefreshing=false;
											}
										});
									} catch (Exception e) {
										XPathQueryDesigner.this.container.getQueryStatus().showError(e);
										Display.getDefault().asyncExec(new Runnable() {
											@Override
											public void run() {
												treeViewer.getTree().removeAll();
												treeViewer.setInput(XMLTreeCustomStatus.ERROR_LOADING_XML);
												isRefreshing=false;
											}
										});
									} finally {
										monitor.done();
									}
								}
							});
						} catch (Exception ex) {
							XPathQueryDesigner.this.container.getQueryStatus().showError(ex);
							Display.getDefault().asyncExec(new Runnable() {
								@Override
								public void run() {
									treeViewer.getTree().removeAll();
									treeViewer.setInput(XMLTreeCustomStatus.ERROR_LOADING_XML);
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
				treeViewer.setInput(XMLTreeCustomStatus.FILE_NOT_FOUND);
				isRefreshing=false;
			}
		}
	}
	
	/*
	 * Content provider for the Xpath tree viewer.
	 */
	private final class XPathTreeViewerContentProvider implements
			ITreeContentProvider {
		public Object[] getChildren(Object element) {
			if(element instanceof XMLNode){
				return ((ANode)element).getChildren().toArray();
			}
			return new Object[0];
		}

		public Object getParent(Object element) {
			if(element instanceof XMLNode){
				return ((XMLNode) element).getParent();
			}
			return null;
		}

		public Object[] getElements(Object element) {
			if (element instanceof XMLTreeCustomStatus){
				return new Object[]{element};
			}
			if(element instanceof MRoot){
				return ((MRoot) element).getChildren().toArray();
			}
			return getChildren(element);
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object old_input,
				Object new_input) {
		}
	}

	/*
	 * Job that is responsible to update the treeviewer presentation 
	 * depending on the nodes selected by the XPath query.
	 */
	private final class DecorateTreeViewerJob extends WorkbenchJob{

		public DecorateTreeViewerJob(){
			super(Messages.XPathQueryDesigner_RefreshJobTitle);
			setSystem(true);
		}
		
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			monitor.beginTask(Messages.XPathQueryDesigner_RefreshTaskTitle, IProgressMonitor.UNKNOWN);
			String query=queryTextArea.getText();
			treeLabelProvider.setSelectedNodes(documentManager.getSelectableNodes(query));
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
