/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AItemDialog extends ATitledDialog implements IExpressionContextSetter {
	private JasperReportsConfiguration jrConfig;

	private List<ItemData> itemDatas;
	private StandardItemData itemData;
	protected StandardItem item;

	private CTabFolder tabFolder;

	protected ADescriptor descriptor;
	private Button useDatasetB;
	private TableViewer dsviewer;
	private EditButton<StandardItemData> dsEditButton;

	private NewButton dsNewButton;

	public AItemDialog(Shell parentShell, ADescriptor descriptor, JasperReportsConfiguration jrConfig) {
		super(parentShell);
		setTitle(descriptor.getDisplayName());
		this.jrConfig = jrConfig;
		this.descriptor = descriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jasperreports.eclipse.ui.ATitledDialog#close()
	 */
	@Override
	public boolean close() {
		List<ItemProperty> todel = new ArrayList<ItemProperty>();
		for (ItemProperty p : item.getProperties())
			if (Misc.isNullOrEmpty(p.getValue())
					&& (p.getValueExpression() == null || Misc.isNullOrEmpty(p.getValueExpression().getText())))
				todel.add(p);
		for (ItemProperty p : todel)
			item.removeItemProperty(p);
		descriptor.setItem(null);
		return super.close();
	}

	public void setValues(List<ItemData> itemDatas, StandardItemData itemData, StandardItem item) {
		this.item = item;
		descriptor.setItem(item);
		setItemData(itemData);
		this.itemDatas = itemDatas;
	}

	private void setItemData(StandardItemData itemData) {
		this.itemData = itemData;
		descriptor.setItemData(itemData);
	}

	/**
	 * @return the itemData
	 */
	public StandardItemData getItemData() {
		return itemData;
	}

	protected void fillData() {

		useDatasetB.setSelection(itemData.getDataset() != null);
		enableUseDataset();
		dsviewer.setInput(itemDatas);
		dsviewer.getTable().select(itemDatas.indexOf(itemData));

		setupExpressionContext();
		setError(null);
		validateForm();
	}

	protected void validateForm() {
		Button ok = getButton(IDialogConstants.OK_ID);
		if (!ok.isEnabled())
			ok.setEnabled(true);
		String str = null;
		try {
			descriptor.validateItem(null);
		} catch (Exception e) {
			str = e.getMessage();
			ok.setEnabled(false);
		}
		setError(str);
	}

	protected ExpressionContext currentExpContext;
	protected ExpressionContext expContext;

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		currentExpContext = expContext;
	}

	private void enableUseDataset() {
		dsviewer.getTable().setEnabled(useDatasetB.getSelection());
		dsEditButton.setEnabled(useDatasetB.getSelection());
		dsNewButton.setEnabled(useDatasetB.getSelection());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		tabFolder = new CTabFolder(cmp, SWT.FLAT | SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createValue(tabFolder);
		createDataset(tabFolder);
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				fillData();
			}
		});

		return cmp;
	}

	protected abstract void createValue(CTabFolder tabFolder);

	private void createDataset(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ItemDialog_7);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		useDatasetB = new Button(cmp, SWT.CHECK);
		useDatasetB.setText(Messages.ItemDialog_6);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		useDatasetB.setLayoutData(gd);
		useDatasetB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUseDataset();
			}
		});

		Table table = new Table(cmp, SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		gd.heightHint = 300;
		gd.verticalSpan = 3;
		table.setLayoutData(gd);
		table.setLinesVisible(false);

		dsviewer = new TableViewer(table);
		dsviewer.setContentProvider(new ListContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof List<?>) {
					List<ItemData> list = new ArrayList<ItemData>();
					for (ItemData id : (List<ItemData>) inputElement) {
						if (id.getDataset() == null)
							continue;
						list.add(id);
					}
					return list.toArray();
				}
				return new Object[0];
			}
		});
		dsviewer.setLabelProvider(new ItemLabelProvider(descriptor));
		UIUtil.setViewerCellEditingOnDblClick(dsviewer);
		ColumnViewerToolTipSupport.enableFor(dsviewer, ToolTip.NO_RECREATE);

		TableViewerColumn viewerColumn = new TableViewerColumn(dsviewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(600);
		viewerColumn.setLabelProvider(new ItemLabelProvider(descriptor));

		dsNewButton = new NewButton() {
			protected void afterElementAdded(Object selement) {
				Button b = getButton(IDialogConstants.OK_ID);
				b.setEnabled(true);
				itemData.removeItem(item);
				((StandardItemData) selement).addItem(item);
				setItemData((StandardItemData) selement);
				setupExpressionContext();
			}
		};
		dsNewButton.createNewButtons(cmp, dsviewer, new INewElement() {

			@Override
			public Object newElement(List<?> input, int pos) {
				JRDesignElementDataset eds = new JRDesignElementDataset();
				// eds.setDatasetRun(new JRDesignDatasetRun());
				ElementDatasetDialog dialog = new ElementDatasetDialog(UIUtils.getShell(), Messages.ItemDialog_7,
						Messages.ItemDialog_7, eds, jrConfig);
				dialog.setExpressionContext(currentExpContext);
				if (openChildDialog(dialog) == Dialog.OK) {
					StandardItemData id = new StandardItemData();
					id.setDataset(dialog.getDataset());
					return id;
				}
				return null;
			}

		});
		dsEditButton = new EditButton<StandardItemData>();
		dsEditButton.createEditButtons(cmp, dsviewer, new IEditElement<StandardItemData>() {

			@Override
			public void editElement(List<StandardItemData> input, int pos) {
				JRElementDataset ds = itemData.getDataset();
				ElementDatasetDialog dialog = new ElementDatasetDialog(UIUtils.getShell(), Messages.ItemDialog_7,
						Messages.ItemDialog_7, (JRElementDataset) ds.clone(), jrConfig);
				dialog.setExpressionContext(currentExpContext);
				if (openChildDialog(dialog) == Dialog.OK) {
					itemData.setDataset(dialog.getDataset());
					dsviewer.refresh();
					setupExpressionContext();
				}
			}
		});
		dsEditButton.editOnDoubleClick();
		dsviewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				handleDsSelectionChanged();
			}

		});
		bptab.setControl(cmp);
	}

	private void handleDsSelectionChanged() {
		StructuredSelection sel = (StructuredSelection) dsviewer.getSelection();
		if (sel.getFirstElement() != null) {
			itemData.removeItem(item);
			StandardItemData newID = (StandardItemData) sel.getFirstElement();
			newID.addItem(item);
			setItemData(newID);
		}
		setupExpressionContext();
		validateForm();
	}

	protected void setupExpressionContext() {
		if (itemData.getDataset() != null) {
			JRDesignDataset ds = ModelUtils.getDesignDatasetForDatasetRun(jrConfig.getJasperDesign(), itemData.getDataset()
					.getDatasetRun());
			currentExpContext = new ExpressionContext(ds, jrConfig);
		} else
			currentExpContext = expContext;
	}

	private void handleUseDataset() {
		Button b = getButton(IDialogConstants.OK_ID);
		b.setEnabled(true);
		boolean useDs = useDatasetB.getSelection();
		enableUseDataset();
		if (useDs) {
			if (itemDatas.isEmpty())
				b.setEnabled(false);
			else
				dsviewer.getTable().select(0);
		} else {
			dsviewer.setSelection(null);
			itemData.removeItem(item);
			// let's use a ItemData with no dataset
			StandardItemData newid = null;
			for (ItemData id : itemDatas) {
				if (id.getDataset() == null) {
					newid = (StandardItemData) id;
					break;
				}
			}
			if (newid == null)
				newid = new StandardItemData();
			newid.addItem(item);
			setItemData(newid);
		}
		handleDsSelectionChanged();
	}
}
