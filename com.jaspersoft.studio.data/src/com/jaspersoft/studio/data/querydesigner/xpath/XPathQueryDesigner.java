package com.jaspersoft.studio.data.querydesigner.xpath;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;
import net.sf.jasperreports.data.xml.XmlDataAdapter;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.progress.WorkbenchJob;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.xml.XMLAttributeNode;
import com.jaspersoft.studio.model.datasource.xml.XMLNode;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Query designer for the XPath language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XPathQueryDesigner extends AQueryDesigner {
	
	private static final int JOB_DELAY=300;
	private Composite control;
	private StyledText queryTextArea;
	private TreeViewer xmlTreeViewer;
	private XMLDocumentManager documentManager;
	private boolean reloadXMLData;
	private DecorateTreeViewerJob decorateJob;
	private BoldStyledLabelProvider treeLabelProvider;

	public XPathQueryDesigner(){
		this.documentManager=new XMLDocumentManager();
		this.decorateJob=new DecorateTreeViewerJob();
		this.reloadXMLData=true;
	}
	
	public Control getControl() {
		return control;
	}

	@Override
	public Control createControl(Composite parent) {
		control=new Composite(parent, SWT.NONE){
			@Override
			public void setVisible(boolean visible) {
				super.setVisible(visible);
				if(visible){
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							refreshTreeViewerContent(container.getDataAdapter());							
						}
					});
				}
			}
		};
		GridLayout controlGl=new GridLayout(1,true);
		controlGl.marginWidth=0;
		controlGl.marginHeight=0;
		control.setLayout(controlGl);
		
		Label titleLabel=new Label(control,SWT.WRAP);
		titleLabel.setText(Messages.XPathQueryDesigner_InfoTitle);
		titleLabel.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false));
		
		SashForm sashForm = new SashForm(control, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
		createXMLTreeViewer(sashForm);
		
		queryTextArea = new StyledText(sashForm, SWT.BORDER);
		queryTextArea.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if(documentManager.isDocumentSet()){
					decorateJob.cancel();
					decorateJob.schedule(JOB_DELAY);
				}
				// keep the query info updated
				((JRDesignQuery) jDataset.getQuery()).setText(queryTextArea.getText());
			}
		});
		
		sashForm.setWeights(new int[] {30, 70});
		return control;
	}

	private void createXMLTreeViewer(SashForm parent) {
		xmlTreeViewer = new TreeViewer(parent, SWT.BORDER);
		xmlTreeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		xmlTreeViewer.setContentProvider(new ITreeContentProvider() {
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
		});
		treeLabelProvider = new BoldStyledLabelProvider();
		xmlTreeViewer.setLabelProvider(treeLabelProvider);
		
		addDragSupport();
		
		createContextualMenu();
	}

	/*
	 * Adds drag support to the xml tree viewer. 
	 */
	private void addDragSupport() {
		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { NodeTransfer.getInstance(),
				PluginTransfer.getInstance() };
		xmlTreeViewer.addDragSupport(ops, transfers, new NodeDragListener(
				xmlTreeViewer) {
			@Override
			public void dragStart(DragSourceEvent event) {
				TreeSelection s = (TreeSelection) xmlTreeViewer.getSelection();
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
		Menu contextMenu=new Menu(xmlTreeViewer.getTree());
		final MenuItem setRecordNodeItem=new MenuItem(contextMenu, SWT.PUSH);
		setRecordNodeItem.setText(Messages.XPathQueryDesigner_SetRecordItem);
		setRecordNodeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
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
				Object sel = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
				try {
					Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
					Node originalNode = documentManager.getDocumentNodesMap().get(sel);
					Node importedNode = newDocument.importNode(originalNode, true);
					newDocument.appendChild(importedNode);
					documentManager.setDocument(newDocument);
					xmlTreeViewer.setInput(documentManager.getXMLDocumentModel());
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
				Object sel = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
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
				Object sel = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
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
				xmlTreeViewer.expandAll();
			}
		});
		final MenuItem collapseAllItem=new MenuItem(contextMenu, SWT.PUSH);
		collapseAllItem.setText(Messages.XPathQueryDesigner_CollapseAllItem);
		collapseAllItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				xmlTreeViewer.collapseAll();
			}
		});
		final MenuItem resetRefreshDocItem=new MenuItem(contextMenu, SWT.PUSH);
		resetRefreshDocItem.setText(Messages.XPathQueryDesigner_RefreshItem);
		resetRefreshDocItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadXMLData=true;
				refreshTreeViewerContent(container.getDataAdapter());
			}
		});
		xmlTreeViewer.getTree().setMenu(contextMenu);
		
		contextMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuShown(MenuEvent e) {
				Object selEl = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
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
	
	@Override
	public void setQuery(JasperDesign jDesign, JRDataset jDataset) {
		super.setQuery(jDesign, jDataset);
		queryTextArea.setText(jDataset.getQuery().getText());
	}

	@Override
	public Control getToolbarControl() {
		return null;
	}

	@Override
	public Control createToolbar(Composite parent) {
		return null;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor da) {
		reloadXMLData=true;
		refreshTreeViewerContent(da);
	}

	/*
	 * Creates a new JRField and adds it the fields table.
	 */
	private void createField(XMLNode node) {
		List<JRDesignField> currentFields = this.container.getCurrentFields();
		JRDesignField field = (JRDesignField)node.getAdapter(JRDesignField.class);
		field.setName(ModelUtils.getNameForField(currentFields, field.getName()));
		currentFields.add(field);
		this.container.setFields(currentFields);
	}
	
	/*
	 * Refresh the tree data using the dataadapter file as
	 * input source.
	 */
	private void refreshTreeViewerContent(final DataAdapterDescriptor da){
		if(reloadXMLData){
			this.container.getQueryStatus().showInfo(""); //$NON-NLS-1$
			if(da!=null && da.getDataAdapter() instanceof XmlDataAdapter) {
				xmlTreeViewer.setInput(XMLTreeCustomStatus.LOADING_XML);
			
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
												xmlTreeViewer.setInput(documentManager.getXMLDocumentModel());
												xmlTreeViewer.expandToLevel(2);
												reloadXMLData=false;
											}
										});
									} catch (Exception e) {
										XPathQueryDesigner.this.container.getQueryStatus().showError(e);
										Display.getDefault().asyncExec(new Runnable() {
											@Override
											public void run() {
												xmlTreeViewer.getTree().removeAll();
												xmlTreeViewer.setInput(XMLTreeCustomStatus.ERROR_LOADING_XML);
												reloadXMLData=false;
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
									xmlTreeViewer.getTree().removeAll();
									xmlTreeViewer.setInput(XMLTreeCustomStatus.ERROR_LOADING_XML);
									reloadXMLData=false;
								}
							});
						}					
						return Status.OK_STATUS;
					}
					
				};
				j.schedule();
				
			}
			else{
				xmlTreeViewer.getTree().removeAll();
				xmlTreeViewer.setInput(XMLTreeCustomStatus.FILE_NOT_FOUND);
				reloadXMLData=false;
			}
		}
	}
	
	/*
	 * Job that is responsible to update the treeviewer presentation 
	 * depending on the nodes selected by the XPath query.
	 */
	private class DecorateTreeViewerJob extends WorkbenchJob{

		public DecorateTreeViewerJob(){
			super(Messages.XPathQueryDesigner_RefreshJobTitle);
			setSystem(true);
		}
		
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			monitor.beginTask(Messages.XPathQueryDesigner_RefreshTaskTitle, IProgressMonitor.UNKNOWN);
			String query=queryTextArea.getText();
			List<Node> nodes=documentManager.selectNodeList(query);
			// Find the XMLNode items that should be selected on the tree
			List<XMLNode> selected=new ArrayList<XMLNode>();
			for(XMLNode n : documentManager.getDocumentNodesMap().keySet()){
				if(nodes.contains(documentManager.getDocumentNodesMap().get(n))){
					selected.add(n);
				}
			}
			
			treeLabelProvider.setSelectedNodes(selected);
			xmlTreeViewer.refresh();
			monitor.done();
			return Status.OK_STATUS;
		}
		
	}

}
