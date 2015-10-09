/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.List;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.util.JRCloneUtils;

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
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ItemDataDialog extends ElementDatasetDialog {
	private StandardItemData itemData;
	private Button bhasds;
	private boolean hasDS;
	private ADescriptor descriptor;
	private List<ItemData> itemDatas;
	private ExpressionContext expContext;
	private APropertyNode pnode;

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
		newShell.setText("The name of the dataset");
	}

	@Override
	protected void preElementDataset(Composite parent) {
		bhasds = new Button(parent, SWT.CHECK);
		bhasds.setText(Messages.ItemDataDialog_0);
		bhasds.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hasDS = bhasds.getSelection();
				UIUtils.setEnabled(compositeDatasetInfo, hasDS);
			}
		});
		bhasds.setSelection(itemData.getDataset() != null);
	}

	@Override
	protected void createElementDatasetArea(Composite area) {
		Composite c = new Composite(area, SWT.NONE);
		c.setLayout(new GridLayout());

		CTabFolder tabFolder = new CTabFolder(c, SWT.FLAT | SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createItems(tabFolder);

		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Dataset");

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout());

		super.createElementDatasetArea(cmp);
		UIUtils.setEnabled(compositeDatasetInfo, hasDS);
		tabItem.setControl(cmp);
	}

	private void createItems(CTabFolder tabFolder) {
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Items");

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		final TableViewer viewer = new TableViewer(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
				| SWT.BORDER);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new ItemLabelProvider(descriptor) {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				return getText(element);
			}
		});
		viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite c = new Composite(cmp, SWT.NONE);
		c.setLayout(new GridLayout());
		c.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		new NewButton().createNewButtons(c, viewer, new INewElement() {

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
						StructuredSelection s = (StructuredSelection) viewer.getSelection();
						if (s != null) {
							Object obj = s.getFirstElement();
							if (obj != null && obj instanceof Item)
								descriptor.setupDefaultValue((Item) obj, p);
						}
					}
				}
				descriptor.setItemDatas(clones, pnode);
				descriptor.setItemData(idClone);
				descriptor.setItem(item);

				AItemDialog dialog = createItemDialog();
				dialog.setValues(clones, itemData, item);
				dialog.setExpressionContext(expContext);
				try {
					if (dialog.open() != Dialog.OK)
						item = null;
				} finally {
					descriptor.setItem(item);
					descriptor.setItemData(itemData);
					descriptor.setItemDatas(itemDatas, pnode);
				}
				return item;
			}
		});
		EditButton<StandardItem> eb = new EditButton<StandardItem>();
		eb.createEditButtons(c, viewer, new IEditElement<StandardItem>() {

			@Override
			public void editElement(List<StandardItem> input, int pos) {
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
						itemData = dialog.getItemData();
						input.set(pos, itemClone);
						itemData.getItems().set(indx, itemClone);
						itemDatas.set(indx, itemData);
					}
				} finally {
					descriptor.setItem(itemData.getItems().get(pos));
					descriptor.setItemData(itemData);
					descriptor.setItemDatas(itemDatas, pnode);
				}
			}
		});
		eb.editOnDoubleClick();
		new DeleteButton().createDeleteButton(c, viewer);
		new ListOrderButtons().createOrderButtons(c, viewer);

		tabItem.setControl(cmp);

		viewer.setInput(itemData.getItems());
	}

	protected abstract AItemDialog createItemDialog();

	@Override
	public JRElementDataset getDataset() {
		return hasDS ? super.getDataset() : null;
	}
}
