/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.engine.util.JRCloneUtils;

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
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

import com.jaspersoft.jasperreports.customvisualization.design.CVDesignComponent;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDatasetDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentPropertyDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentSectionDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemDataDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.TableItemDialog;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.property.itemproperty.sp.ItemDataListContentProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Widget that allows to manage the <code>ItemDataList</code> property.
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class SPCVCItemDataList extends
		ASPropertyWidget<AItemDataListPropertyDescriptor> implements
		IExpressionContextSetter {

	private ExpressionContext expContext;

	protected TreeViewer dsTViewer;
	private Button btnAddNewDataset;
	private Button btnModifyDataset;
	private Button btnRemoveDataset;

	public SPCVCItemDataList(Composite parent, AbstractSection section,
			AItemDataListPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	private ADescriptor getDescriptor() {
		return pDescriptor.getDescriptor();
	}

	@Override
	protected void createComponent(Composite parent) {
		createDatasetsTab(parent);
	}

	protected ViewerComparator getElementViewerComparator() {
		return null;
	}

	private void handleEditElement(TreeViewer tviewer) {
		StructuredSelection sel = (StructuredSelection) tviewer.getSelection();
		sel.getFirstElement();
		Object obj = sel.getFirstElement();
		if (sel == null || obj == null)
			return;
		if (obj instanceof StandardItem) {
			List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
			StandardItemData itemData = (StandardItemData) getStandardItemData(
					false, tviewer, itemDatas);
			StandardItemData itemDataClone = (StandardItemData) getStandardItemData(
					false, tviewer, clones);
			int ind = itemData.getItems().indexOf((StandardItem) obj);
			if (ind >= 0) {
				StandardItem item = (StandardItem) itemDataClone.getItems()
						.get(ind);
				showItemDialog(clones, itemDataClone, item);
			}
		} else if (obj instanceof StandardItemData) {
			StandardItemData itemData = (StandardItemData) obj;
			JRElementDataset ds = itemData.getDataset();
			if (ds == null)
				ds = new JRDesignElementDataset();
			else
				ds = (JRElementDataset) ds.clone();
			ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(),
					Messages.SPItemDataList_3, Messages.SPItemDataList_3,
					itemData,
					(JasperReportsConfiguration) section
							.getJasperReportsContext());
			if (dialog.open() == Dialog.OK) {
				itemData.setDataset(dialog.getDataset());
				dsTViewer.refresh();
			}
		}
	}

	private void handleDeleteElement(TreeViewer tviewer) {
		List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		StructuredSelection sel = (StructuredSelection) tviewer.getSelection();
		Object obj = sel.getFirstElement();
		if (sel == null || obj == null)
			return;

		if (obj instanceof StandardItem) {
			StandardItem item = (StandardItem) obj;
			StandardItemData itemData = (StandardItemData) getStandardItemData(
					false, tviewer, itemDatas);
			if (itemData != null) {
				StandardItemData itemDataClone = (StandardItemData) getStandardItemData(
						false, tviewer, clones);
				int ind = itemData.getItems().indexOf(item);
				if (ind >= 0 && !itemData.getItems().isEmpty())
					itemDataClone.removeItem(itemDataClone.getItems().get(ind));
			}
		} else if (obj instanceof StandardItemData) {
			StandardItemData itemData = (StandardItemData) obj;
			StandardItemData itemDataClone = (StandardItemData) getStandardItemData(
					false, tviewer, clones);
			int ind = itemDatas.indexOf(itemData);
			if (ind >= 0)
				clones.remove(itemDataClone);
		} else
			return;
		section.changeProperty(pDescriptor.getId(), new ArrayList<ItemData>(
				clones));
	}

	protected void handleNewElement(TreeViewer tviewer) {
		List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
		StandardItemData itemData = (StandardItemData) getStandardItemData(
				true, tviewer, clones);
		if (!clones.contains(itemData))
			clones.add(itemData);

		StandardItem item = new StandardItem();
		for (ItemPropertyDescription<?> ipd : getDescriptor()
				.getItemPropertyDescriptors()) {
			if (ipd.isMandatory()) {
				StandardItemProperty p = new StandardItemProperty(
						ipd.getName(), ipd.getDefaultValueString(), null);
				item.addItemProperty(p);
				StructuredSelection s = (StructuredSelection) tviewer
						.getSelection();
				if (s != null) {
					Object obj = s.getFirstElement();
					if (obj != null && obj instanceof Item)
						getDescriptor().setupDefaultValue((Item) obj, p);
				}
			}
		}
		itemData.addItem(item);
		showItemDialog(clones, itemData, item);
	}

	protected void showItemDialog(List<ItemData> citemsData,
			StandardItemData itemData, StandardItem item) {
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
		if (cd != null && !Misc.isNullOrEmpty(cd.getDatasets())) {
			JasperReportsConfiguration jConf = pnode.getJasperConfiguration();
			int indx = getSelectedItemDataIndex();
			int c = 0;
			for (ComponentDatasetDescriptor cdd : cd.getDatasets()) {
				int card = cdd.getCardinality();
				if (card > 0)
					c += card;
				else if (card <= 0)
					return createForm(jConf, cdd);
				if (c >= indx)
					return createForm(jConf, cdd);
			}
		}
		return new TableItemDialog(UIUtils.getShell(), getDescriptor(),
				(JasperReportsConfiguration) section.getJasperReportsContext());
	}

	private AItemDialog createForm(JasperReportsConfiguration jConf,
			final ComponentDatasetDescriptor cdd) {
		return new FormItemDialog(UIUtils.getShell(), getDescriptor(), jConf) {

			@Override
			protected void createValue(CTabFolder tabFolder) {
				CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
				bptab.setText(com.jaspersoft.studio.messages.Messages.ItemDialog_0);

				Composite cmp = createScrolledComposite(tabFolder, bptab);

				for (ComponentSectionDescriptor s : cdd.getSections()) {
					Composite c = null;
					if (s.isExpandable())
						c = createSection(cmp, s.getName());
					else {
						c = cmp;
						createSeparator(cmp);
					}
					for (ComponentPropertyDescriptor pd : s.getProperties())
						createItemProperty(c, pd.getName());
				}
				configScrolledComposite(cmp);
			}
		};
	}

	private ItemData getStandardItemData(boolean createNew, TreeViewer tviewer,
			List<ItemData> clones) {
		IStructuredSelection sel = (IStructuredSelection) tviewer
				.getSelection();
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
					dsTViewer.setSelection(new StructuredSelection(itemData),
							true);
			}
		});
	}

	private void createDatasetsTab(Composite parent) {
		datasetsCmp = section.getWidgetFactory().createComposite(parent);
		datasetsCmp.setLayout(new GridLayout(2, false));

		dsTViewer = new TreeViewer(datasetsCmp, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		Tree tree = dsTViewer.getTree();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 5;
		tree.setLayoutData(gd);
		createDsLabelProvider();
		dsTViewer.setContentProvider(new ItemDataListContentProvider(true));
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

		btnAddNewDataset = section.getWidgetFactory().createButton(datasetsCmp,
				Messages.SPItemdDataList_0, SWT.NONE);
		btnAddNewDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false, 1, 1));
		btnAddNewDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleNewElement(dsTViewer);
			}
		});

		btnModifyDataset = section.getWidgetFactory().createButton(datasetsCmp,
				Messages.SPItemDataList_1, SWT.NONE);
		btnModifyDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false, 1, 1));
		btnModifyDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleEditElement(dsTViewer);
			}
		});

		btnRemoveDataset = section.getWidgetFactory().createButton(datasetsCmp,
				Messages.SPItemDataList_2, SWT.NONE);
		btnRemoveDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false, 1, 1));
		btnRemoveDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDeleteElement(dsTViewer);
			}
		});

		btnUpDataset = section.getWidgetFactory().createButton(datasetsCmp,
				Messages.SPItemDataList_0, SWT.NONE);
		btnUpDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false, 1, 1));
		btnUpDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUpElement(dsTViewer);
			}
		});

		btnDownDataset = section.getWidgetFactory().createButton(datasetsCmp,
				Messages.SPItemDataList_4, SWT.NONE);
		btnDownDataset.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false, 1, 1));
		btnDownDataset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDownElement(dsTViewer);
			}
		});

		enableDefaultDatasetsButtons();
	}

	protected void createDsLabelProvider() {
		dsTViewer.setLabelProvider(new ItemLabelProvider(getDescriptor()) {
			@Override
			protected String getText4ItemData(Object element) {
				if (cd != null && !Misc.isNullOrEmpty(cd.getDatasets())) {
					int indx = itemDatas.indexOf(element);
					int c = 0;
					for (ComponentDatasetDescriptor cdd : cd.getDatasets()) {
						int card = cdd.getCardinality();
						if (card > 0)
							c += card;
						else if (card <= 0)
							return cdd.getLabel();
						if (c > indx)
							return cdd.getLabel();
					}
				}
				return super.getText4ItemData(element);
			}

			@Override
			protected String getToolTipText4ItemData(Object element) {
				// TODO Auto-generated method stub
				return super.getToolTipText4ItemData(element);
			}
		});
	}

	private void handleUpElement(TreeViewer tviewer) {
		moveItem(true);
	}

	private void handleDownElement(TreeViewer tviewer) {
		moveItem(false);
	}

	protected JRPropertyChangeSupport getComponentEventSupport(
			JRDesignComponentElement jrElement) {
		Component c = jrElement.getComponent();
		if (c instanceof net.sf.jasperreports.engine.design.events.JRChangeEventsSupport)
			return ((net.sf.jasperreports.engine.design.events.JRChangeEventsSupport) c)
					.getEventSupport();
		return null;
	}

	private void moveItem(boolean up) {
		StructuredSelection selection = (StructuredSelection) dsTViewer
				.getSelection();
		if (selection == null)
			return;
		Object sel = selection.getFirstElement();
		if (sel instanceof Item) {
			Item item = (Item) sel;
			JRPropertyChangeSupport es = getComponentEventSupport((JRDesignComponentElement) pnode
					.getValue());
			for (ItemData id : itemDatas) {
				List<Item> items = id.getItems();
				if (Misc.isNullOrEmpty(items))
					continue;
				int size = items.size();
				if (size > 1)
					for (int i = 0; i < size; i++) {
						if (items.get(i) == item) {
							int ind = items.indexOf(item);
							int newind = up ? Math.max(0, ind - 1) : Math.min(
									items.size() - 1, ind + 1);
							Item tmp = items.get(newind);
							items.set(newind, item);
							items.set(ind, tmp);
							if (es != null)
								es.fireIndexedPropertyChange(
										(String) pDescriptor.getId(), newind,
										tmp, item);
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

	private ComponentDescriptor cd;

	private Composite datasetsCmp;

	@SuppressWarnings("unchecked")
	@Override
	public void setData(APropertyNode pnode, Object value) {
		this.pnode = pnode;
		itemDatas = (List<ItemData>) value;

		dsTViewer.setInput(itemDatas);
		getDescriptor().setItemDatas(itemDatas, pnode);
		enableDefaultDatasetsButtons();
		JRDesignElement designEl = null;
		if (pnode.getValue() instanceof JRDesignElement)
			designEl = (JRDesignElement) pnode.getValue();
		// Try to get an expression context for the node if any
		Object expContextAdapter = pnode.getAdapter(ExpressionContext.class);
		if (expContextAdapter != null)
			setExpressionContext((ExpressionContext) expContextAdapter);
		else
			setExpressionContext(ModelUtils.getElementExpressionContext(
					designEl, pnode));

		dsTViewer.expandAll();

		cd = getComponentDescriptor();
		dsTViewer.refresh(true);
	}

	private ComponentDescriptor getComponentDescriptor() {
		if (pnode == null)
			return null;
		// let's look if we have some files with our properties
		@SuppressWarnings("unchecked")
		List<ItemProperty> p = (List<ItemProperty>) pnode
				.getPropertyValue(CVDesignComponent.PROPERTY_ITEM_PROPERTIES);
		if (Misc.isNullOrEmpty(p))
			return null;
		// let's get our description
		JasperDesign jd = pnode.getJasperDesign();
		JasperReportsConfiguration jConf = pnode.getJasperConfiguration();
		JRDesignDataset dataset = null;
		if (dataset == null)
			dataset = ModelUtils.getDataset(pnode);
		if (dataset == null)
			dataset = (JRDesignDataset) jd.getMainDataset();

		ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(
				dataset, jd, jConf);
		for (ItemProperty ip : p)
			if (ip.getName().equals("module")) {
				String module = ItemPropertyUtil.getItemPropertyString(
						(StandardItemProperty) ip, expIntr);
				if (Misc.isNullOrEmpty(module))
					break;
				return UIManager.getDescriptor(jConf, module);
			}
		return null;
	}

	private void enableDefaultDatasetsButtons() {
		btnAddNewDataset.setEnabled(true);
		StructuredSelection selection = (StructuredSelection) dsTViewer
				.getSelection();
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
		return datasetsCmp;
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

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

}
