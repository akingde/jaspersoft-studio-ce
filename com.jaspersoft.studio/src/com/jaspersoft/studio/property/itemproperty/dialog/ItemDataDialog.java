/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.util.JRCloneUtils;

public abstract class ItemDataDialog extends ElementDatasetDialog {
	protected StandardItemData itemData;
	private Button bhasds;
	private boolean hasDS;
	private ADescriptor descriptor;
	protected List<ItemData> itemDatas;
	private ExpressionContext expContext;
	private APropertyNode pnode;
	protected IEditElement<Item> editElement;
	protected TableViewer itemsViewer;
	protected CTabItem dsTabItem;
	protected CTabItem itTabItem;

	public ItemDataDialog(Shell parentShell, String title, String message, List<ItemData> itemDatas,
			StandardItemData itemData, JasperReportsConfiguration jConfig, ADescriptor descriptor,
			ExpressionContext expconContext, APropertyNode pnode) {
		super(parentShell, title, message, itemData.getDataset(), jConfig);
		this.itemData = itemData;
		this.itemDatas = itemDatas;
		this.descriptor = descriptor;
		this.expContext = expconContext;
		this.pnode = pnode;
		hasDS = itemData.getDataset() != null;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(getItemName());
	}

	@Override
	protected void preElementDataset(Composite parent) {
		bhasds = new Button(parent, SWT.CHECK);
		bhasds.setText(Messages.ItemDataDialog_0);
		bhasds.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hasDS = bhasds.getSelection();
				if (hasDS)
					itemData.setDataset(getDataset());
				else
					itemData.setDataset(null);
				UIUtils.setEnabled(compositeDatasetInfo, hasDS);

			}
		});
		bhasds.setSelection(itemData.getDataset() != null);
	}

	@Override
	protected void createElementDatasetArea(Composite area) {
		Composite c = new Composite(area, SWT.NONE);
		c.setLayout(new GridLayout());
		c.setLayoutData(new GridData(GridData.FILL_BOTH));

		CTabFolder tabFolder = new CTabFolder(c, SWT.FLAT | SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createItems(tabFolder);

		dsTabItem = new CTabItem(tabFolder, SWT.NONE);
		dsTabItem.setText(Messages.common_dataset);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout());

		super.createElementDatasetArea(cmp);
		UIUtils.setEnabled(compositeDatasetInfo, hasDS);
		dsTabItem.setControl(cmp);
	}

	protected void createItems(CTabFolder tabFolder) {
		itTabItem = new CTabItem(tabFolder, SWT.NONE);
		itTabItem.setText(getItemName());

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		itemsViewer = new TableViewer(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		itemsViewer.setContentProvider(ArrayContentProvider.getInstance());
		itemsViewer.setLabelProvider(new ItemLabelProvider(descriptor) {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				return getText(element);
			}
		});
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 600;
		itemsViewer.getTable().setLayoutData(gd);

		Composite c = new Composite(cmp, SWT.NONE);
		c.setLayout(new GridLayout());
		c.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		new NewButton().createNewButtons(c, itemsViewer, new INewElement() {

			@Override
			public Object newElement(List<?> input, int pos) {
				int indx = itemDatas.indexOf(itemData);
				List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
				StandardItemData idClone = (StandardItemData) clones.get(indx);
				StandardItem item = new StandardItem();
				for (ItemPropertyDescription<?> ipd : descriptor.getItemPropertyDescriptors()) {
					if (ipd.isMandatory()) {
						StandardItemProperty p = new StandardItemProperty(ipd.getName(), ipd.getDefaultValueString(), null);
						item.addItemProperty(p);
						StructuredSelection s = (StructuredSelection) itemsViewer.getSelection();
						if (s != null) {
							Object obj = s.getFirstElement();
							if (obj != null && obj instanceof Item)
								descriptor.setupDefaultValue((Item) obj, p);
						}
					}
				}
				idClone.addItem(item);
				descriptor.setItemDatas(clones, pnode);
				descriptor.setItemData(idClone);
				descriptor.setItem(item);

				AItemDialog dialog = createItemDialog();
				dialog.setValues(clones, idClone, item);
				dialog.setExpressionContext(expContext);
				try {
					if (dialog.open() == Dialog.OK) {
						itemDatas.clear();
						itemDatas.addAll(clones);
						itemData = dialog.getItemData();
					} else
						item = null;
				} finally {
					descriptor.setItem(item);
					descriptor.setItemData(itemData);
					descriptor.setItemDatas(itemDatas, pnode);
				}
				return item;
			}
		});
		EditButton<Item> eb = new EditButton<Item>();
		editElement = new IEditElement<Item>() {

			@Override
			public void editElement(List<Item> input, int pos) {
				int indx = itemDatas.indexOf(itemData);
				List<ItemData> clones = JRCloneUtils.cloneList(itemDatas);
				StandardItemData idClone = (StandardItemData) clones.get(indx);
				StandardItem itemClone = (StandardItem) idClone.getItems().get(pos);
				descriptor.setItemDatas(clones, pnode);
				descriptor.setItemData(idClone);
				descriptor.setItem(itemClone);
				try {
					AItemDialog dialog = createItemDialog();
					dialog.setValues(clones, idClone, itemClone);
					dialog.setExpressionContext(expContext);
					if (dialog.open() == Dialog.OK) {
						input.set(pos, itemClone);
						// itemData.getItems().set(indx, itemClone);
						itemDatas.clear();
						itemDatas.addAll(clones);
						itemData = dialog.getItemData();
					}
				} finally {
					descriptor.setItem(itemData.getItems().get(pos));
					descriptor.setItemData(itemData);
					descriptor.setItemDatas(itemDatas, pnode);
				}
			}
		};
		eb.createEditButtons(c, itemsViewer, editElement);
		eb.editOnDoubleClick();
		new DeleteButton().createDeleteButton(c, itemsViewer, true);
		new ListOrderButtons().createOrderButtons(c, itemsViewer);

		itTabItem.setControl(cmp);

		itemsViewer.setInput(itemData.getItems());
	}

	protected String getItemName() {
		return "Items";
	}

	protected abstract AItemDialog createItemDialog();

	@Override
	public JRElementDataset getDataset() {
		return hasDS ? super.getDataset() : null;
	}
}
