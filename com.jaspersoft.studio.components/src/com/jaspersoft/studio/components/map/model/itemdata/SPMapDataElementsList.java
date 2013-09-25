/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.itemdata;

import java.util.List;

import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.util.JRCloneUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.model.itemdata.ElementsTreeStatus.LAST_OPERATION;
import com.jaspersoft.studio.components.map.model.itemdata.dialog.ElementDatasetDialog;
import com.jaspersoft.studio.components.map.model.itemdata.dialog.ElementItemDialog;
import com.jaspersoft.studio.components.map.model.itemdata.dialog.MapElementDialog;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataDatasetDTO;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementDTO;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementItemDTO;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementsConfiguration;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Abstract widget that should be extended by map properties involving a list of {@link ItemData} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public abstract class SPMapDataElementsList extends ASPropertyWidget {

	private MapDataElementsConfiguration mapElementsConfig;
	private JasperReportsConfiguration jConfig;
	private ExpressionContext defaultExpressionContext;

	// Widget stuff
	private Button btnAddNewElement;
	private Button btnMoveUpItem;
	private Button btnMoveDownItem;
	private TreeViewer elementsTV;
	private Composite datasetsCmp;
	private Composite elementsCmp;
	private TabFolder tabfolder;
	private TableViewer datasetsTV;
	private Button btnAddNewDataset;
	private Button btnModifyDataset;
	private Button btnRemoveDataset;
	private ElementsTreeStatus treeStatus;
	private ElementsListWidgetConfiguration widgetConfig;

	public SPMapDataElementsList(
			Composite parent, AbstractSection section,
			IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	protected void createComponent(Composite parent) {
		widgetConfig = getWidgetConfiguration();
		
		tabfolder = new TabFolder(parent, SWT.NONE);
		GridData tabfolderGD = new GridData(SWT.FILL, SWT.FILL, true, false);
		tabfolderGD.heightHint = 200;
		tabfolder.setLayoutData(tabfolderGD);
		
		createElementsTab(tabfolder);
		createDatasetsTab(tabfolder);
	}
	
	protected abstract ElementsListWidgetConfiguration getWidgetConfiguration();

	private void createElementsTab(TabFolder parentFolder){
		TabItem elementsTab = new TabItem(parentFolder, SWT.NONE);
		elementsCmp = new Composite(parentFolder,SWT.NONE);
		elementsCmp.setLayout(new GridLayout(2,false));
		elementsTab.setControl(elementsCmp);
		elementsTab.setText(widgetConfig.getElementsTabTitle());
		
		elementsTV = new TreeViewer(elementsCmp, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		elementsTV.getTree().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,3));
		elementsTV.setLabelProvider(getElementsViewerLabelProvider());
		elementsTV.setContentProvider(getElementsViewerContentProvider());
		elementsTV.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				enableDefaultTreeButtons();
			}
		});
		elementsTV.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Object selObj = getElementsTVSelectedObj();
				if(selObj instanceof MapDataElementDTO) {
					modifySelectedElement((MapDataElementDTO) selObj);	
				}
				else if(selObj instanceof MapDataElementItemDTO) {
					modifySelectedElementItem((MapDataElementItemDTO) selObj);
				}
			}
		});
		
		btnAddNewElement = new Button(elementsCmp, SWT.NONE);
		btnAddNewElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnAddNewElement.setImage(widgetConfig.getAddNewElementIcon());
		btnAddNewElement.setToolTipText(NLS.bind("Add a new {0}", widgetConfig.getElementTxt()));
		btnAddNewElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewElementBtnPressed();
			}
		});
		
		btnMoveUpItem = new Button(elementsCmp, SWT.NONE);
		btnMoveUpItem.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnMoveUpItem.setImage(Activator.getDefault().getImage("/icons/pathitem-moveup-16.png"));
		btnMoveUpItem.setToolTipText("Move up");
		btnMoveUpItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveUpItemBtnPressed();
			}
		});
		
		btnMoveDownItem = new Button(elementsCmp, SWT.NONE);
		btnMoveDownItem.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnMoveDownItem.setImage(Activator.getDefault().getImage("/icons/pathitem-movedown-16.png"));
		btnMoveDownItem.setToolTipText("Move down");
		btnMoveDownItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveDownItemBtnPressed();
			}
		});
		
		enableDefaultTreeButtons();
		addTreeMenuManager();
	}
	
	protected abstract IBaseLabelProvider getElementsViewerLabelProvider();

	protected abstract IContentProvider getElementsViewerContentProvider();

	private void addTreeMenuManager() {
		MenuManager menuMgr = new MenuManager();
        Menu menu = menuMgr.createContextMenu(elementsTV.getControl());
        menuMgr.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                if (elementsTV.getSelection().isEmpty()) {
                    return;
                }

                if (elementsTV.getSelection() instanceof IStructuredSelection) {
                    IStructuredSelection selection = (IStructuredSelection) elementsTV.getSelection();
                    Object selObj = selection.getFirstElement();
                    
                    if (selObj instanceof MapDataElementItemDTO) {
                        manager.add(new EditItemAction());
                        manager.add(new DeleteItemAction());
                    }
                    else if (selObj instanceof MapDataElementDTO) {
                    	manager.add(new AddItemAction());
                    	manager.add(new EditElementAction());
                    	manager.add(new DeleteElementAction());
                    }
                    else {
                    	return;
                    }
                }
            }
        });
        menuMgr.setRemoveAllWhenShown(true);
        elementsTV.getControl().setMenu(menu);
	}
	
	private class AddItemAction extends Action {
		
		private AddItemAction(){
			super();
			setText("Add new Item");
			setToolTipText(NLS.bind("Add a new item the current {0}",widgetConfig.getElementTxt()));
			setImageDescriptor(Activator.getDefault().getImageDescriptor("/icons/add_element.gif"));
		}
		
		@Override
		public void run() {
			Object selObj = ((IStructuredSelection) elementsTV.getSelection()).getFirstElement();
			if(selObj instanceof MapDataElementDTO) {
				ItemProperty elementName = ((MapDataElementDTO) selObj).getName();
				MapDataElementItemDTO newItemDTO = new MapDataElementItemDTO(elementName);
				StandardItem newItem = createStandardItem();
				newItem.addItemProperty((ItemProperty) elementName.clone());
				newItemDTO.setItem(newItem);				
	        	ElementItemDialog dialog = new ElementItemDialog(
	        			UIUtils.getShell(),newItemDTO,widgetConfig,
	        			mapElementsConfig.getElementDatasetsMap(),jConfig);
	        	dialog.setMandatoryPropertyNames(getMandatoryProperties());
	        	dialog.setDefaultExpressionContext(getDefaultExpressionContext());
	        	if(dialog.open()==Window.OK) {
	        		((MapDataElementDTO)selObj).getDataItems().add(dialog.getItemDTO());
	        		firePropertyChanged(LAST_OPERATION.ADD);
	        	}
			}
		}
	}
	
	private class EditElementAction extends Action {
		private EditElementAction() {
			super();
			setText(NLS.bind("Edit {0}",widgetConfig.getElementTxt()));
			setToolTipText(NLS.bind("Edit the selected {0} information",widgetConfig.getElementTxt()));
			setImageDescriptor(Activator.getDefault().getImageDescriptor("/icons/edit_element.gif"));
		}
		
		@Override
		public void run() {
			Object selObj = getElementsTVSelectedObj();
			if(selObj instanceof MapDataElementDTO) {
				modifySelectedElement((MapDataElementDTO) selObj);
			}
		}
	}
	
	private void modifySelectedElement(MapDataElementDTO selectedElement) {
		ItemProperty clonedElementName = JRCloneUtils.nullSafeClone(selectedElement.getName());
		if(clonedElementName==null){
			clonedElementName = new StandardItemProperty("name","",null);
		}
		MapElementDialog dialog = new MapElementDialog(UIUtils.getShell(), clonedElementName, widgetConfig);
		dialog.setExpressionContext(getDefaultExpressionContext());
		if(dialog.open()==Window.OK) {
			selectedElement.setName(dialog.getElementName());
			firePropertyChanged(LAST_OPERATION.EDIT);
		}
	}
	
	private class DeleteElementAction extends Action {
		private DeleteElementAction(){
			super();
			setText(NLS.bind("Delete {0}",widgetConfig.getElementTxt()));
			setToolTipText(NLS.bind("Delete the selected {0}",widgetConfig.getElementTxt()));
			setImageDescriptor(Activator.getDefault().getImageDescriptor("/icons/delete_element.gif"));
		}
		
		@Override
		public void run() {
			boolean deleteElement = MessageDialog.openQuestion(
					UIUtils.getShell(), NLS.bind("Delete {0}",widgetConfig.getElementTxt()), 
					NLS.bind("Are you sure you want to delete the following {0}? All the associated items will be removed.",widgetConfig));
			if(deleteElement){
				IStructuredSelection selection = (IStructuredSelection) elementsTV.getSelection();
		        Object selObj = selection.getFirstElement();
		        if(selObj instanceof MapDataElementDTO) {
		        	mapElementsConfig.getElements().remove(selObj);
		        	firePropertyChanged(LAST_OPERATION.REMOVE);
		        }
			}
		}
	}
	
	private class EditItemAction extends Action {
		private EditItemAction(){
			super();
			setText("Edit Item");
			setToolTipText(NLS.bind("Modify the information associated to the {0} Item",widgetConfig.getElementTxt()));
			setImageDescriptor(Activator.getDefault().getImageDescriptor("/icons/edit_element.gif"));
		}
		
		@Override
		public void run() {
			Object selObj = getElementsTVSelectedObj();
			if(selObj instanceof MapDataElementItemDTO) {
				modifySelectedElementItem((MapDataElementItemDTO) selObj);
			}
		}
	}

	private void modifySelectedElementItem(MapDataElementItemDTO selObj) {
    	MapDataElementItemDTO itemDTOClone = (MapDataElementItemDTO) selObj.clone();
    	ElementItemDialog dialog = new ElementItemDialog(
    			UIUtils.getShell(),itemDTOClone, widgetConfig,
    			mapElementsConfig.getElementDatasetsMap(), jConfig);
    	dialog.setMandatoryPropertyNames(getMandatoryProperties());
    	if(dialog.open()==Window.OK) {
    		ElementDataHelper.updateElementDataItem(
    				mapElementsConfig,itemDTOClone.getParentName(),selObj,dialog.getItemDTO());
    		firePropertyChanged(LAST_OPERATION.EDIT);
    	}
	}
	
	protected abstract List<String> getMandatoryProperties();
	
	private class DeleteItemAction extends Action {
		private DeleteItemAction(){
			super();
			setText("Delete Item");
			setToolTipText(NLS.bind("Delete the following {0} Item", widgetConfig.getElementTxt()));
			setImageDescriptor(Activator.getDefault().getImageDescriptor("/icons/delete_element.gif"));
		}
		
		@Override
		public void run() {
			boolean delete = MessageDialog.openQuestion(
					UIUtils.getShell(),NLS.bind("Remove {0} Item",widgetConfig.getElementTxt()), 
					NLS.bind("Are you sure you want to delete the {0} Item?", widgetConfig.getElementTxt()));
			if(delete){
				MapDataElementItemDTO item = (MapDataElementItemDTO) ((IStructuredSelection) elementsTV.getSelection()).getFirstElement();
				ItemProperty parentName = item.getParentName();
				ElementDataHelper.removeElementDataItem(mapElementsConfig,parentName,item);
				firePropertyChanged(LAST_OPERATION.REMOVE);
			}
		}
	}

	private void enableDefaultTreeButtons() {
		btnAddNewElement.setEnabled(true);
		Object selElement = ((IStructuredSelection)elementsTV.getSelection()).getFirstElement();
		boolean enableMoveBtns = !elementsTV.getSelection().isEmpty() && selElement instanceof MapDataElementItemDTO;
		btnMoveDownItem.setEnabled(enableMoveBtns);
		btnMoveUpItem.setEnabled(enableMoveBtns);
	}
	
	protected void addNewElementBtnPressed() {
		StandardItemProperty pname = new StandardItemProperty();
		pname.setName("name");
		MapElementDialog dialog = new MapElementDialog(UIUtils.getShell(), pname, widgetConfig);
		dialog.setExpressionContext(getDefaultExpressionContext());
		if(dialog.open()==Window.OK) {
			MapDataElementDTO newElement = new MapDataElementDTO();
			ItemProperty elementName = dialog.getElementName();
			newElement.setName(elementName);
			MapDataElementItemDTO dummyElementItem = new MapDataElementItemDTO(elementName);
			dummyElementItem.setDatasetName(null);
			StandardItem dummyItem = new StandardItem();
			dummyItem.addItemProperty(elementName);
			for(String p : getMandatoryProperties()) {
				dummyItem.addItemProperty(new StandardItemProperty(p, "CHANGE_ME", null));
			}
			dummyElementItem.setItem(dummyItem);
			newElement.getDataItems().add(dummyElementItem);
			mapElementsConfig.getElements().add(newElement);			
			firePropertyChanged(LAST_OPERATION.ADD);
		}		
	}

	protected void moveDownItemBtnPressed() {
		MapDataElementItemDTO item = (MapDataElementItemDTO) ((IStructuredSelection) elementsTV.getSelection()).getFirstElement();
		ItemProperty parentName = item.getParentName();
		ElementDataHelper.moveDownDataItem(mapElementsConfig,parentName,item);
		firePropertyChanged(LAST_OPERATION.MOVEDOWN);
	}

	protected void moveUpItemBtnPressed() {
		MapDataElementItemDTO item = (MapDataElementItemDTO) ((IStructuredSelection) elementsTV.getSelection()).getFirstElement();
		ItemProperty parentName = item.getParentName();
		ElementDataHelper.moveUpDataItem(mapElementsConfig,parentName,item);
		firePropertyChanged(LAST_OPERATION.MOVEUP);
	}
	
	private void createDatasetsTab(TabFolder parentFolder) {
		TabItem datasetsTab = new TabItem(parentFolder, SWT.NONE);
		datasetsCmp = new Composite(parentFolder,SWT.NONE);
		datasetsCmp.setLayout(new GridLayout(2,false));
		datasetsTab.setControl(datasetsCmp);
		datasetsTab.setText("Associated Datasets");
		
		datasetsTV = new TableViewer(datasetsCmp, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
		datasetsTV.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
		datasetsTV.setLabelProvider(new MapDataDatasetsLabelProvider());
		datasetsTV.setContentProvider(new ArrayContentProvider());
		datasetsTV.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				enableDefaultDatasetsButtons();
			}
		});
		datasetsTV.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				editDatasetBtnPressed();
			}
		});
		
		btnAddNewDataset = new Button(datasetsCmp, SWT.NONE);
		btnAddNewDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnAddNewDataset.setImage(Activator.getDefault().getImage("/icons/add_element.gif"));
		btnAddNewDataset.setToolTipText("Add a new Dataset suitable for the elements");
		btnAddNewDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewDatasetBtnPressed();
			}
		});
		
		btnModifyDataset = new Button(datasetsCmp, SWT.NONE);
		btnModifyDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnModifyDataset.setImage(Activator.getDefault().getImage("/icons/edit_element.gif"));
		btnModifyDataset.setToolTipText("Modify an existing Dataset");
		btnModifyDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editDatasetBtnPressed();
			}
		});
		
		btnRemoveDataset = new Button(datasetsCmp, SWT.NONE);
		btnRemoveDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnRemoveDataset.setImage(Activator.getDefault().getImage("/icons/delete_element.gif"));
		btnRemoveDataset.setToolTipText("Delete an existing Dataset");
		btnRemoveDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeDatasetBtnPressed();
			}
		});
		
		enableDefaultDatasetsButtons();
	}

	private void enableDefaultDatasetsButtons() {
		btnAddNewDataset.setEnabled(true);
		btnModifyDataset.setEnabled(!datasetsTV.getSelection().isEmpty());
		btnRemoveDataset.setEnabled(!datasetsTV.getSelection().isEmpty());
	}
	
	private void addNewDatasetBtnPressed() {
		ElementDatasetDialog elementDatasetDialog = new ElementDatasetDialog(
				UIUtils.getShell(),
				"Add Dataset", NLS.bind("Configure here a new dataset suitable for Map {0} generation.",widgetConfig.getElementTxt()),
				null,jConfig);
		elementDatasetDialog.setDefaultExpressionContext(getDefaultExpressionContext());
		if(elementDatasetDialog.open()==Window.OK){
			JRElementDataset newDataset = elementDatasetDialog.getDataset();
			MapDataDatasetDTO dto = new MapDataDatasetDTO();
			dto.setDataset(newDataset);
			mapElementsConfig.getDatasets().add(dto);
			refreshDatasetsTableViewer();
		}
	}
	
	private void editDatasetBtnPressed() {
		Object firstElement = ((IStructuredSelection)datasetsTV.getSelection()).getFirstElement();
		if(firstElement instanceof MapDataDatasetDTO) {
			Object dsClone = ((MapDataDatasetDTO) firstElement).getDataset().clone();
			ElementDatasetDialog elementDatasetDialog = new ElementDatasetDialog(
					UIUtils.getShell(),
					"Edit Dataset", NLS.bind("Edit the information associated to the dataset suitable for Map {0} generation.",widgetConfig.getElementTxt()),
					(JRElementDataset)dsClone,jConfig);
			elementDatasetDialog.setDefaultExpressionContext(getDefaultExpressionContext());
			if(elementDatasetDialog.open()==Window.OK){
				JRElementDataset modifiedDataset = elementDatasetDialog.getDataset();
				((MapDataDatasetDTO)firstElement).setDataset(modifiedDataset);
				refreshDatasetsTableViewer();
			}
		}
	}
	
	private void removeDatasetBtnPressed() {
		Object firstElement = ((IStructuredSelection)datasetsTV.getSelection()).getFirstElement();
		if(firstElement instanceof MapDataDatasetDTO) {
			boolean confirm = MessageDialog.openQuestion(UIUtils.getShell(), "Remove Dataset",
					NLS.bind("If you proceed removing the Dataset all {0} Items related will be automatically be considered as static. " +
					"Are you sure you want to proceed?", widgetConfig.getElementTxt()));
			if(confirm) {
				mapElementsConfig.getDatasets().remove((MapDataDatasetDTO) firstElement);
				refreshDatasetsTableViewer();				
			}
		}
	}
	
	private void refreshDatasetsTableViewer() {
		ElementDataHelper.fixDatasetNames(mapElementsConfig);
		firePropertyChanged(null);
	}
	
	private void firePropertyChanged(LAST_OPERATION lastOperation) {
		if(lastOperation!=null) {
			treeStatus = ElementsTreeStatus.getElementsTreeStatus(elementsTV,lastOperation);
		}
		else {
			treeStatus = null;
		}
		section.changeProperty(widgetConfig.getWidgetPropertyID(), mapElementsConfig);
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		mapElementsConfig = (MapDataElementsConfiguration) value;
		elementsTV.setInput(mapElementsConfig.getElements());
		datasetsTV.setInput(mapElementsConfig.getDatasets());
		jConfig = pnode.getJasperConfiguration();
		defaultExpressionContext=ModelUtils.getElementExpressionContext(null, pnode);
		if(treeStatus!=null) {
			Object[] expandedElements = treeStatus.findExpandedElements(mapElementsConfig.getElements());
			elementsTV.setExpandedElements(expandedElements);
			ISelection selection = ElementsTreeStatus.getSuggestedSelection(elementsTV, treeStatus);
			elementsTV.setSelection(selection);
		}
	}

	@Override
	public Control getControl() {
		return tabfolder;
	}

	private ExpressionContext getDefaultExpressionContext() {		
		return this.defaultExpressionContext;
	}
	
	private StandardItem createStandardItem() {
		StandardItem item = new StandardItem();
		for(String pname : getMandatoryProperties()) {
			item.addItemProperty(new StandardItemProperty(pname,"CHANGE_ME",null));
		}
		return item;
	}

	private Object getElementsTVSelectedObj() {
		IStructuredSelection selection = (IStructuredSelection) elementsTV.getSelection();
        return selection.getFirstElement();
	}
}
