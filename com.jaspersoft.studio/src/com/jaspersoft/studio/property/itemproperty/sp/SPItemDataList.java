/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.sp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemDataDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.TableItemDialog;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * Widget that allows to manage the <code>ItemDataList</code> property.
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class SPItemDataList extends ASPropertyWidget<AItemDataListPropertyDescriptor>
		implements IExpressionContextSetter {

	protected ExpressionContext expContext;

	// Widget stuff
	private TreeViewer elTViewer;
	private Button btnAddElement;
	private Button btnEditElement;
	private Button btnDelElement;

	protected TreeViewer dsTViewer;
	private Button btnAddNewDataset;
	private Button btnModifyDataset;
	private Button btnRemoveDataset;

	public SPItemDataList(Composite parent, AbstractSection section, AItemDataListPropertyDescriptor pDescriptor) {
		this(parent, section, pDescriptor, true);
	}

	public SPItemDataList(Composite parent, AbstractSection section, AItemDataListPropertyDescriptor pDescriptor,
			boolean showElements) {
		super(parent, section, pDescriptor);
		if (showElements)
			control = createComponentTab(parent);
		else {
			control = createDatasetsTab(parent);
			GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
			gd.heightHint = 300;
			// gd.widthHint = 300;
			control.setLayoutData(gd);
		}

		if (getControl() != null)
			setupFocusControl(pDescriptor, getControl());
	}

	private Control control;

	private ADescriptor getDescriptor() {
		return pDescriptor.getDescriptor();
	}

	@Override
	protected void createComponent(Composite parent) {

	}

	protected Control createComponentTab(Composite parent) {
		tabfolder = new TabFolder(parent, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
		gd.heightHint = 300;
		// gd.widthHint = 300;
		tabfolder.setLayoutData(gd);

		TabItem elementsTab = new TabItem(tabfolder, SWT.NONE);
		elementsTab.setText(pDescriptor.getDisplayName());
		elementsTab.setControl(createElementsTab(tabfolder));

		TabItem datasetsTab = new TabItem(tabfolder, SWT.NONE);
		datasetsTab.setText(Messages.SPItemDataList_5);
		datasetsTab.setControl(createDatasetsTab(tabfolder));
		return tabfolder;
	}

	protected ViewerComparator getElementViewerComparator() {
		return null;
	}

	private Control createElementsTab(Composite parentFolder) {
		Composite elementsCmp = section.getWidgetFactory().createComposite(parentFolder);
		elementsCmp.setLayout(new GridLayout(2, false));

		elTViewer = new TreeViewer(elementsCmp, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		Tree elementsTree = elTViewer.getTree();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		elementsTree.setLayoutData(gd);
		elTViewer.setLabelProvider(new ItemLabelProvider(getDescriptor()));
		elTViewer.setContentProvider(new ItemDataListContentProvider(true));
		elTViewer.setComparator(getElementViewerComparator());
		ColumnViewerToolTipSupport.enableFor(elTViewer, ToolTip.NO_RECREATE);

		elTViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				enableDefaultTreeButtons();
			}
		});
		elTViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				handleEditElement(elTViewer);
			}
		});

		btnAddElement = section.getWidgetFactory().createButton(elementsCmp, Messages.SPItemdDataList_0, SWT.PUSH);
		btnAddElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnAddElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleNewElement(elTViewer);
			}
		});

		btnEditElement = section.getWidgetFactory().createButton(elementsCmp, Messages.SPItemDataList_1, SWT.NONE);
		btnEditElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnEditElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleEditElement(elTViewer);
			}
		});

		btnDelElement = section.getWidgetFactory().createButton(elementsCmp, Messages.SPItemDataList_2, SWT.NONE);
		btnDelElement.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnDelElement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDeleteElement(elTViewer);
			}
		});

		enableDefaultTreeButtons();
		return elementsCmp;
	}

	private void handleEditElement(TreeViewer tviewer) {
		StructuredSelection sel = (StructuredSelection) tviewer.getSelection();
		sel.getFirstElement();
		Object obj = sel.getFirstElement();
		if (sel == null || obj == null)
			return;
		if (obj instanceof StandardItem) {
			List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
			StandardItemData itemData = (StandardItemData) getStandardItemData(false, tviewer, itemDatas);
			StandardItemData itemDataClone = (StandardItemData) getStandardItemData(false, tviewer, clones);
			int ind = itemData.getItems().indexOf((StandardItem) obj);
			if (ind >= 0) {
				StandardItem item = (StandardItem) itemDataClone.getItems().get(ind);
				showItemDialog(clones, itemDataClone, item);
			}
		} else if (obj instanceof StandardItemData) {
			List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
			StandardItemData itemDataClone = (StandardItemData) getStandardItemData(false, tviewer, clones);

			ItemDataDialog dialog = createItemDataDialog(clones, itemDataClone);
			if (dialog.open() == Dialog.OK)
				postCreateItemDialog(new ArrayList<ItemData>(clones));
		}
	}

	protected Map<String, Object> postCreateMap = new HashMap<String, Object>();

	protected void addPostCreateCommand(String id, Object value) {
		Object obj = pnode.getPropertyValue(id);
		if (obj instanceof ItemProperty) {
			if (value != null && ((ItemProperty) obj).getValueExpression() != null)
				return;
		}
		postCreateMap.put(id, value);
	}

	protected void postCreateItemDialog(List<ItemData> clones) {
		List<Command> cmds = new ArrayList<Command>();
		for (String key : postCreateMap.keySet())
			cmds.add(section.getChangePropertyCommand(key, postCreateMap.get(key), pnode));
		postCreateMap.clear();
		section.changeProperty(pDescriptor.getId(), new ArrayList<ItemData>(clones), cmds);
	}

	private void handleDeleteElement(TreeViewer tviewer) {
		List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		StructuredSelection sel = (StructuredSelection) tviewer.getSelection();
		Object obj = sel.getFirstElement();
		if (sel == null || obj == null)
			return;

		if (obj instanceof StandardItem) {
			StandardItem item = (StandardItem) obj;
			StandardItemData itemData = (StandardItemData) getStandardItemData(false, tviewer, itemDatas);
			if (itemData != null) {
				StandardItemData itemDataClone = (StandardItemData) getStandardItemData(false, tviewer, clones);
				int ind = itemData.getItems().indexOf(item);
				if (ind >= 0 && !itemData.getItems().isEmpty())
					itemDataClone.removeItem(itemDataClone.getItems().get(ind));
			}
		} else if (obj instanceof StandardItemData) {
			StandardItemData itemData = (StandardItemData) obj;
			StandardItemData itemDataClone = (StandardItemData) getStandardItemData(false, tviewer, clones);
			int ind = itemDatas.indexOf(itemData);
			if (ind >= 0)
				clones.remove(itemDataClone);
		} else
			return;
		section.changeProperty(pDescriptor.getId(), new ArrayList<ItemData>(clones));
	}

	protected void handleNewElement(TreeViewer tviewer) {
		List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		StandardItemData itemData = new StandardItemData();
		clones.add(itemData);
		if (createItemDataDialog(clones, itemData).open() == Dialog.OK)
			postCreateItemDialog(new ArrayList<ItemData>(clones));

		// List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		// StandardItemData itemData = (StandardItemData)
		// getStandardItemData(true, tviewer, clones);
		// if (!clones.contains(itemData))
		// clones.add(itemData);
		//
		// StandardItem item = new StandardItem();
		// for (ItemPropertyDescription<?> ipd :
		// getDescriptor().getItemPropertyDescriptors()) {
		// if (ipd.isMandatory() ||
		// !Misc.isNullOrEmpty(ipd.getDefaultValueString())) {
		// StandardItemProperty p = new StandardItemProperty(ipd.getName(),
		// ipd.getDefaultValueString(), null);
		// item.addItemProperty(p);
		// StructuredSelection s = (StructuredSelection) tviewer.getSelection();
		// if (s != null) {
		// Object obj = s.getFirstElement();
		// if (obj != null && obj instanceof Item)
		// getDescriptor().setupDefaultValue((Item) obj, p);
		// }
		// }
		// }
		// itemData.addItem(item);
		// showItemDialog(clones, itemData, item);
	}

	protected ItemDataDialog createItemDataDialog(List<ItemData> clones, StandardItemData itemData) {
		ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(), Messages.SPItemDataList_6,
				Messages.SPItemDataList_7, clones, itemData,
				(JasperReportsConfiguration) section.getJasperReportsContext(), getDescriptor(), expContext, pnode) {

			@Override
			protected AItemDialog createItemDialog() {
				return SPItemDataList.this.createItemDialog();
			}
		};
		return dialog;
	}

	protected void showItemDialog(List<ItemData> citemsData, StandardItemData itemData, StandardItem item) {
		getDescriptor().setItemDatas(citemsData, pnode);
		getDescriptor().setItemData(itemData);
		AItemDialog dialog = createItemDialog();
		dialog.setValues(citemsData, itemData, item);
		dialog.setExpressionContext(expContext);
		if (dialog.open() == Dialog.OK) {
			section.changeProperty(pDescriptor.getId(), citemsData);
			setElementSelection(dialog.getItemData(), item);
			getDescriptor().setItem(null);
		}
	}

	protected AItemDialog createItemDialog() {
		return new TableItemDialog(UIUtils.getShell(), getDescriptor(),
				(JasperReportsConfiguration) section.getJasperReportsContext(), false);
	}

	private ItemData getStandardItemData(boolean createNew, TreeViewer tviewer, List<ItemData> clones) {
		IStructuredSelection sel = (IStructuredSelection) tviewer.getSelection();
		Object fe = sel.getFirstElement();
		if (fe != null) {
			if (fe instanceof StandardItemData)
				return clones.get(itemDatas.indexOf((StandardItemData) fe));
			else if (fe instanceof StandardItem) {
				for (ItemData sid : itemDatas) {
					for (Item it : sid.getItems())
						if (it == fe)
							return clones.get(itemDatas.indexOf(sid));
				}
			}
		} else if (!Misc.isNullOrEmpty(itemDatas))
			return (StandardItemData) clones.get(0);
		return createNew ? new StandardItemData() : null;
	}

	private void setElementSelection(final ItemData itemData, final Item item) {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (elTViewer != null) {
					elTViewer.expandToLevel(itemData, 1);
					if (item != null)
						elTViewer.setSelection(new StructuredSelection(item), true);
					else
						elTViewer.setSelection(new StructuredSelection(itemData), true);
				}
				dsTViewer.expandToLevel(itemData, 1);
				if (item != null)
					dsTViewer.setSelection(new StructuredSelection(item), true);
				else
					dsTViewer.setSelection(new StructuredSelection(itemData), true);
			}
		});
	}

	private Composite createDatasetsTab(Composite parentFolder) {
		Composite datasetsCmp = section.getWidgetFactory().createComposite(parentFolder);
		datasetsCmp.setLayout(new GridLayout(2, false));

		dsTViewer = new TreeViewer(datasetsCmp, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		Tree tree = dsTViewer.getTree();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 5;
		tree.setLayoutData(gd);
		createDsLabelProvider();
		dsTViewer.setContentProvider(new ItemDataListContentProvider(true) {
			@Override
			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof ItemData)
					return new Object[0];
				return super.getChildren(parentElement);
			}
		});
		ColumnViewerToolTipSupport.enableFor(dsTViewer, ToolTip.NO_RECREATE);

		dsTViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				enableDefaultDatasetsButtons();
			}
		});
		dsTViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				handleEditElement(dsTViewer);
			}
		});

		btnAddNewDataset = section.getWidgetFactory().createButton(datasetsCmp, Messages.SPItemdDataList_0, SWT.NONE);
		btnAddNewDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnAddNewDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleNewElement(dsTViewer);
			}
		});

		btnModifyDataset = section.getWidgetFactory().createButton(datasetsCmp, Messages.SPItemDataList_1, SWT.NONE);
		btnModifyDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnModifyDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleEditElement(dsTViewer);
			}
		});

		btnRemoveDataset = section.getWidgetFactory().createButton(datasetsCmp, Messages.SPItemDataList_2, SWT.NONE);
		btnRemoveDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnRemoveDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDeleteElement(dsTViewer);
			}
		});

		btnUpDataset = section.getWidgetFactory().createButton(datasetsCmp, Messages.SPItemDataList_0, SWT.NONE);
		btnUpDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnUpDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUpElement(dsTViewer);
			}
		});

		btnDownDataset = section.getWidgetFactory().createButton(datasetsCmp, Messages.SPItemDataList_4, SWT.NONE);
		btnDownDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnDownDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDownElement(dsTViewer);
			}
		});

		enableDefaultDatasetsButtons();
		return datasetsCmp;
	}

	protected void createDsLabelProvider() {
		dsTViewer.setLabelProvider(new ItemLabelProvider(getDescriptor()) {
			@Override
			public Image getImage(Object element) {
				return null;
			}

			@Override
			public String getText4ItemData(ItemData element) {
				return "Item Data " + (itemDatas.indexOf(element) + 1);
			}
		});
	}

	private void handleUpElement(TreeViewer tviewer) {
		moveItem(true);
	}

	private void handleDownElement(TreeViewer tviewer) {
		moveItem(false);
	}

	protected JRPropertyChangeSupport getComponentEventSupport(JRDesignComponentElement jrElement) {
		Component c = jrElement.getComponent();
		if (c instanceof net.sf.jasperreports.engine.design.events.JRChangeEventsSupport)
			return ((net.sf.jasperreports.engine.design.events.JRChangeEventsSupport) c).getEventSupport();
		return null;
	}

	private void moveItem(boolean up) {
		StructuredSelection selection = (StructuredSelection) dsTViewer.getSelection();
		if (selection == null)
			return;
		Object sel = selection.getFirstElement();
		if (sel instanceof Item) {
			Item item = (Item) sel;
			JRPropertyChangeSupport es = getComponentEventSupport((JRDesignComponentElement) pnode.getValue());
			for (ItemData id : itemDatas) {
				List<Item> items = id.getItems();
				if (Misc.isNullOrEmpty(items))
					continue;
				int size = items.size();
				if (size > 1)
					for (int i = 0; i < size; i++) {
						if (items.get(i) == item) {
							int ind = items.indexOf(item);
							int newind = up ? Math.max(0, ind - 1) : Math.min(items.size() - 1, ind + 1);
							Item tmp = items.get(newind);
							items.set(newind, item);
							items.set(ind, tmp);
							if (es != null)
								es.fireIndexedPropertyChange((String) pDescriptor.getId(), newind, tmp, item);
							if (elTViewer != null)
								elTViewer.refresh(true);
							dsTViewer.refresh(true);
							btnUpDataset.setEnabled(newind > 0);
							btnDownDataset.setEnabled(newind < size - 1);
							setElementSelection(id, item);
							return;
						}
					}
			}
		}
	}

	protected List<ItemData> itemDatas;

	private Button btnUpDataset;

	private Button btnDownDataset;
	protected APropertyNode pnode;

	private TabFolder tabfolder;

	@SuppressWarnings("unchecked")
	@Override
	public void setData(APropertyNode pnode, Object value) {
		this.pnode = pnode;
		itemDatas = (List<ItemData>) value;

		if (elTViewer != null)
			elTViewer.setInput(itemDatas);
		dsTViewer.setInput(itemDatas);
		getDescriptor().setItemDatas(itemDatas, pnode);
		enableDefaultDatasetsButtons();
		enableDefaultTreeButtons();
		JRDesignElement designEl = null;
		if (pnode.getValue() instanceof JRDesignElement)
			designEl = (JRDesignElement) pnode.getValue();
		// Try to get an expression context for the node if any
		Object expContextAdapter = pnode.getAdapter(ExpressionContext.class);
		if (expContextAdapter != null)
			setExpressionContext((ExpressionContext) expContextAdapter);
		else
			setExpressionContext(ModelUtils.getElementExpressionContext(designEl, pnode));

		dsTViewer.expandAll();
	}

	private void enableDefaultTreeButtons() {
		if (elTViewer == null)
			return;
		btnAddElement.setEnabled(true);
		boolean enableMoveBtns = !elTViewer.getSelection().isEmpty();
		btnEditElement.setEnabled(enableMoveBtns);
		btnDelElement.setEnabled(enableMoveBtns);
	}

	private void enableDefaultDatasetsButtons() {
		btnAddNewDataset.setEnabled(true);
		StructuredSelection selection = (StructuredSelection) dsTViewer.getSelection();
		btnModifyDataset.setEnabled(!selection.isEmpty());
		btnRemoveDataset.setEnabled(!selection.isEmpty());

		Object sel = selection.getFirstElement();
		if (sel instanceof Item) {
			Item item = (Item) sel;
			for (ItemData id : itemDatas) {
				List<Item> items = id.getItems();
				if (Misc.isNullOrEmpty(items))
					continue;
				int size = items.size();
				if (size > 1)
					for (int i = 0; i < size; i++) {
						if (items.get(i) == item) {
							btnUpDataset.setEnabled(i > 0);
							btnDownDataset.setEnabled(i < size - 1);
							return;
						}
					}
			}
		}
		btnUpDataset.setEnabled(false);
		btnDownDataset.setEnabled(false);
	}

	@Override
	public Control getControl() {
		return control;
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	public int getSelectedItemDataIndex() {
		StructuredSelection sel = null;
		if (elTViewer != null && tabfolder.getSelectionIndex() == 0)
			sel = (StructuredSelection) elTViewer.getSelection();
		else
			sel = (StructuredSelection) dsTViewer.getSelection();
		Object obj = sel.getFirstElement();
		if (obj instanceof ItemData)
			return itemDatas.indexOf(obj);
		if (obj instanceof Item) {
			Item item = (Item) obj;
			for (ItemData id : itemDatas) {
				List<Item> items = id.getItems();
				if (Misc.isNullOrEmpty(items))
					continue;
				for (Item it : items)
					if (it == item)
						return itemDatas.indexOf(id);
			}
		}
		if (!Misc.isNullOrEmpty(itemDatas))
			return 0;
		return -1;
	}

}
