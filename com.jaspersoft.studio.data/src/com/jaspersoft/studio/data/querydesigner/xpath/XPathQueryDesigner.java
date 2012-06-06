package com.jaspersoft.studio.data.querydesigner.xpath;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;
import net.sf.jasperreports.data.xml.XmlDataAdapter;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.PluginTransfer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.xml.XMLAttributeNode;
import com.jaspersoft.studio.model.datasource.xml.XMLNode;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.ResourceManager;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Query designer for the XPath language.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XPathQueryDesigner extends AQueryDesigner {
	
	private Composite control;
	private StyledText queryTextArea;
	private TreeViewer xmlTreeViewer;
	private XMLDocumentManager documentManager;
	private boolean reloadXMLData;

	public XPathQueryDesigner(){
		this.documentManager=new XMLDocumentManager();
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
				if(visible){
					refreshTreeViewerContent(container.getDataAdapter());
				}
				super.setVisible(visible);
			}
		};
		control.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(control, SWT.NONE);
		
		createXMLTreeViewer(sashForm);
		
		queryTextArea = new StyledText(sashForm, SWT.BORDER);
		
		sashForm.setWeights(new int[] {30, 70});
		return control;
	}

	private void createXMLTreeViewer(SashForm parent) {
		Composite container=new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1,true);
		layout.marginWidth=0;
		layout.marginHeight=0;
		container.setLayout(layout);
		
		Label titleLabel=new Label(container,SWT.WRAP);
		titleLabel.setText("Drag a node into the fields table to map a new field");
		titleLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
		
		xmlTreeViewer = new TreeViewer(container, SWT.BORDER);
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
				if (element instanceof BrokenTreeStatus){
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
		xmlTreeViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if(element instanceof XMLNode){
					return ((XMLNode)element).getDisplayText();
				}
				else if(element instanceof BrokenTreeStatus){
					return ((BrokenTreeStatus) element).getMessage();
				}
				return super.getText(element);
			}
			@Override
			public Image getImage(Object element) {
				if(element instanceof XMLNode){
					return ((XMLNode) element).getImage();
				}
				else if(element instanceof BrokenTreeStatus){
					return ResourceManager.getPluginImage(
							Activator.PLUGIN_ID,((BrokenTreeStatus) element).getImagePath());
				}
				return super.getImage(element);
			}
		});
		
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
		setRecordNodeItem.setText("Set record node (generate xPath)");
		setRecordNodeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
				if(sel instanceof XMLNode){
					String xPathExpression = documentManager.getXPathExpression(null,(XMLNode)sel);
					queryTextArea.setText((xPathExpression!=null) ? xPathExpression : "");
				}
			}
		});
		final MenuItem setDocumentRootItem=new MenuItem(contextMenu, SWT.PUSH);
		setDocumentRootItem.setText("Set document root");
		setDocumentRootItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object sel = ((IStructuredSelection)xmlTreeViewer.getSelection()).getFirstElement();
				try {
					Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
					Node originalNode = (Node) ((XMLNode)sel).getValue();
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
		addNodeAsFieldItem1.setText("Add node as field");
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
		addNodeAsFieldItem2.setText("Add node as field (using absolute path)");
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
		expandAllItem.setText("Expand all");
		expandAllItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				xmlTreeViewer.expandAll();
			}
		});
		final MenuItem collapseAllItem=new MenuItem(contextMenu, SWT.PUSH);
		collapseAllItem.setText("Collapse all");
		collapseAllItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				xmlTreeViewer.collapseAll();
			}
		});
		final MenuItem resetRefreshDocItem=new MenuItem(contextMenu, SWT.PUSH);
		resetRefreshDocItem.setText("Reset/Refresh document");
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
			if(da!=null && da.getDataAdapter() instanceof XmlDataAdapter) {
				xmlTreeViewer.setInput(BrokenTreeStatus.LOADING_XML);
				
				Job loadXMLJob=new Job("Load xml resource...") {
					
					@Override
					protected IStatus run(IProgressMonitor monitor) {
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
							Display.getDefault().asyncExec(new Runnable() {
								@Override
								public void run() {
									xmlTreeViewer.getTree().removeAll();
									xmlTreeViewer.setInput(BrokenTreeStatus.ERROR_LOADING_XML);
									reloadXMLData=false;
								}
							});
						}
						return Status.OK_STATUS;
					}
				}; 
				loadXMLJob.schedule();
			}
			else{
				xmlTreeViewer.getTree().removeAll();
				xmlTreeViewer.setInput(BrokenTreeStatus.FILE_NOT_FOUND);
				reloadXMLData=false;
			}
		}
	}
	
	private enum BrokenTreeStatus {
		LOADING_XML("Loading XML data...","icons/waiting.gif"),
		ERROR_LOADING_XML("Error loading the XML file.", "icons/error.gif"),
		FILE_NOT_FOUND("No file found.", "icons/warning.gif");
		
		private String message;
		private String imagePath;
		
		private BrokenTreeStatus(String message, String imagePath) {
			this.message=message;
			this.imagePath=imagePath;
		}				
		
		public String getMessage(){
			return this.message;
		}
		
		public String getImagePath(){
			return this.imagePath;
		}
	}
}
