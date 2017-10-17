/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.widgets.Tree;

import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.framework.DatasetPropertyDescriptor;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemDataDialog;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.property.itemproperty.sp.ItemDataListContentProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
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
public class SPCVCItemDataList extends ASPropertyWidget<AItemDataListPropertyDescriptor>
		implements IExpressionContextSetter {

	private ExpressionContext expContext;

	protected TreeViewer dsTViewer;
	private Button btnAddNewDataset;
	private Button btnModifyDataset;
	private Button btnRemoveDataset;
	private Composite dsParent;
	protected List<ItemData> itemDatas;

	private Button btnUpDataset;

	private Button btnDownDataset;
	protected APropertyNode pnode;

	private CVCWidgetsDescriptor cd;

	private Composite datasetsCmp;

	public SPCVCItemDataList(Composite parent, AbstractSection section, AItemDataListPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	private ADescriptor getDescriptor() {
		return pDescriptor.getDescriptor();
	}

	@Override
	protected void createComponent(Composite parent) {
		dsParent = section.getWidgetFactory().createComposite(parent);
		dsParent.setLayout(new GridLayout(2, false));
		createDatasetsTab(dsParent);
	}

	protected ViewerComparator getElementViewerComparator() {
		return null;
	}

	private void handleEditElement(TreeViewer tviewer) {
		StructuredSelection sel = (StructuredSelection) tviewer.getSelection();
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
			StandardItemData itemData = (StandardItemData) getStandardItemData(false, tviewer, itemDatas);
			StandardItemData itemDataClone = (StandardItemData) getStandardItemData(false, tviewer, clones);

			ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(),
					itemDataLabelProvider.getText4ItemData(itemData), "", //$NON-NLS-1$
					clones, itemDataClone, (JasperReportsConfiguration) section.getJasperReportsContext(),
					getDescriptor(), expContext, pnode) {

				@Override
				protected AItemDialog createItemDialog() {
					return SPCVCItemDataList.this.createItemDialog();
				}
			};
			if (dialog.open() == Dialog.OK)
				section.changeProperty(pDescriptor.getId(), new ArrayList<ItemData>(clones));
		}
	}

	private void handleDeleteElement(TreeViewer tviewer) {
		List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		StructuredSelection sel = (StructuredSelection) tviewer.getSelection();
		for (Object obj : sel.toList()) {
			if (sel == null || obj == null)
				continue;
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
				continue;
		}
		section.changeProperty(pDescriptor.getId(), new ArrayList<ItemData>(clones));
	}

	protected void handleNewElement(TreeViewer tviewer) {
		List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		StandardItemData itemData = new StandardItemData();
		clones.add(itemData);
		ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(), itemDataLabelProvider.getText4ItemData(itemData),
				"", clones, //$NON-NLS-1$
				itemData, (JasperReportsConfiguration) section.getJasperReportsContext(), getDescriptor(), expContext,
				pnode) {

			@Override
			protected AItemDialog createItemDialog() {
				return SPCVCItemDataList.this.createItemDialog();
			}
		};
		if (dialog.open() == Dialog.OK) {

			section.changeProperty(pDescriptor.getId(), new ArrayList<ItemData>(clones));
		}
	}

	protected void showItemDialog(List<ItemData> citemsData, StandardItemData itemData, StandardItem item) {
		putil.showItemDialog(citemsData, itemData, item, expContext);
	}

	protected AItemDialog createItemDialog() {
		return putil.createItemDialog();
	}

	private ItemData getStandardItemData(boolean createNew, TreeViewer tviewer, List<ItemData> clones) {
		IStructuredSelection sel = (IStructuredSelection) dsTViewer.getSelection();
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
				dsTViewer.expandToLevel(itemData, 1);
				if (item != null)
					dsTViewer.setSelection(new StructuredSelection(item), true);
				else
					dsTViewer.setSelection(new StructuredSelection(itemData), true);
			}
		});
	}

	private void createDatasetsTab(Composite parent) {

		datasetsCmp = section.getWidgetFactory().createComposite(parent);
		datasetsCmp.setLayout(new GridLayout(2, false));
		datasetsCmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		dsTViewer = new TreeViewer(datasetsCmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
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
	}

	ItemLabelProvider itemDataLabelProvider;

	protected void createDsLabelProvider() {
		itemDataLabelProvider = new ItemLabelProvider(getDescriptor()) {
			@Override
			public Image getImage(Object element) {
				return null;
			}

			@Override
			public String getText4ItemData(ItemData element) {
				if (cd != null && !Misc.isNullOrEmpty(cd.getDatasets())) {
					int indx = itemDatas.indexOf(element);
					int c = 0;
					for (DatasetPropertyDescriptor cdd : cd.getDatasets()) {
						int card = cdd.getCardinality();
						if (card > 0)
							c += card;
						else if (card <= 0) {
							if (!cdd.getSections().isEmpty())
								return cd.getLocalizedString(cdd.getSections().get(Math.max(0, Math.min(indx - c, cdd.getSections().size() - 1))).getName());
							return cd.getLocalizedString(cdd.getLabel());
						}
						if (c > indx) {
							if (!cdd.getSections().isEmpty())
								return cd.getLocalizedString(cdd.getSections().get(Math.max(0, Math.min(indx - c, cdd.getSections().size() - 1))).getName());
							return cd.getLocalizedString(cdd.getLabel());
						}
					}
				}
				return "Item Data " + (itemDatas.indexOf(element) + 1);
			}

		};
		dsTViewer.setLabelProvider(itemDataLabelProvider);
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

	@SuppressWarnings("unchecked")
	@Override
	public void setData(APropertyNode pnode, Object value) {
		this.pnode = pnode;
		itemDatas = (List<ItemData>) value;

		putil.setPnode(pnode);
		putil.setItemDatas(itemDatas);

		cd = putil.getComponentDescriptor();
		if (cd != null && Misc.isNullOrEmpty(cd.getDatasets()) && !datasetsCmp.isDisposed()) {
			datasetsCmp.dispose();
			dsParent.layout(true);
		}
		if (cd != null && !Misc.isNullOrEmpty(cd.getDatasets()) && datasetsCmp.isDisposed()) {
			createDatasetsTab(dsParent);
			dsParent.layout(true);
		}
		if (cd == null && datasetsCmp.isDisposed()) {
			createDatasetsTab(dsParent);
			dsParent.layout(true);
		}
		if (datasetsCmp.isDisposed())
			return;
		dsTViewer.setInput(itemDatas);
		getDescriptor().setItemDatas(itemDatas, pnode);
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

		dsTViewer.refresh(true);
		if (dsTViewer.getSelection().isEmpty() && !itemDatas.isEmpty())
			dsTViewer.setSelection(new StructuredSelection(itemDatas.get(0)));

		enableDefaultDatasetsButtons();
	}

	private ItemPropertiesUtil putil = new ItemPropertiesUtil(pDescriptor, section) {
		@Override
		public int getSelectedItemDataIndex() {
			StructuredSelection sel = null;

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

		@Override
		protected void setElementSelection(ItemData itemData, Item item) {
			SPCVCItemDataList.this.setElementSelection(itemData, item);
		}
	};

	private void enableDefaultDatasetsButtons() {
		btnAddNewDataset.setEnabled(true);
		StructuredSelection selection = (StructuredSelection) dsTViewer.getSelection();
		btnModifyDataset.setEnabled(!selection.isEmpty());
		btnRemoveDataset.setEnabled(!selection.isEmpty());
		btnUpDataset.setEnabled(!selection.isEmpty());
		btnDownDataset.setEnabled(!selection.isEmpty());

		if (cd != null) {
			int qte = 0;
			for (DatasetPropertyDescriptor cdd : cd.getDatasets()) {
				if (cdd.getCardinality() < 0) {
					qte = -1;
					break;
				}
				qte += cdd.getCardinality();
			}
			if (qte >= 0)
				btnAddNewDataset.setEnabled(itemDatas.size() < qte);
		}
		for (Object sel : selection.toList()) {
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
			} else if (sel instanceof ItemData) {
				int indx = itemDatas.indexOf((ItemData) sel);
				if (cd != null) {
					List<DatasetPropertyDescriptor> ds = cd.getDatasets();
					if (ds.size() > 0) {
						int c = 0;
						if (btnAddNewDataset.isEnabled())
							for (int i = 0; i < ds.size(); i++) {
								DatasetPropertyDescriptor cdd = ds.get(i);
								int card = cdd.getCardinality();
								if (card > 0)
									c += card;
								if (i < indx)
									continue;
								else if (card <= 0) {
									// btnAddNewDataset.setEnabled(true);
									break;
								}
								if (c <= indx + 1) {
									// btnAddNewDataset.setEnabled(false);
									break;
								}
							}
						if (btnRemoveDataset.isEnabled() && !ds.isEmpty()) {
							DatasetPropertyDescriptor cdd = ds.get(ds.size() - 1);
							btnRemoveDataset.setEnabled(cdd.getCardinality() <= 0 || c < itemDatas.size());
						}
					}
				}
			}
		}
		if (cd != null) {
			btnUpDataset.setEnabled(false);
			btnDownDataset.setEnabled(false);
		}
	}

	@Override
	public Control getControl() {
		return dsParent;
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

}
