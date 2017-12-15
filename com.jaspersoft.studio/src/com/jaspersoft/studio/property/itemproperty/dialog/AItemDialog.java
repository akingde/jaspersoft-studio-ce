/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.jface.dialogs.EditableDatasetBaseComposite;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.ComponentElementDatasetAdapter;
import com.jaspersoft.studio.model.dataset.ComponentElementDatasetRunAdapter;
import com.jaspersoft.studio.model.dataset.IEditableDatasetRun;
import com.jaspersoft.studio.property.dataset.DatasetRunSelectionListener;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

public abstract class AItemDialog extends ATitledDialog implements IExpressionContextSetter {
	private JasperReportsConfiguration jrConfig;

	protected List<ItemData> itemDatas;
	protected StandardItemData itemData;
	protected StandardItem item;

	protected ADescriptor descriptor;
	private Button useDatasetB;
	protected Combo dsviewer;
	protected EditableDatasetBaseComposite compositeDatasetInfo;
	protected boolean showDataset = true;
	protected ExpressionContext currentExpContext;
	protected ExpressionContext expContext;

	private Composite dsCmp;

	public AItemDialog(Shell parentShell, ADescriptor descriptor, JasperReportsConfiguration jrConfig,
			boolean showDataset) {
		super(parentShell);
		setTitle(descriptor.getDisplayName());
		setDefaultSize(500, 600);
		this.jrConfig = jrConfig;
		this.descriptor = descriptor;
		this.showDataset = showDataset;
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
			if (p != null && Misc.isNullOrEmpty(p.getValue())
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
		if (showDataset) {
			useDatasetB.setSelection(itemData.getDataset() != null);

			setupItemDataCombo();
			setupExpressionContext();
			if (itemData.getDataset() != null) {
				if (compositeDatasetInfo != null)
					compositeDatasetInfo.dispose();
				createDatasetComposite(dsCmp, true);
			} else {
				if (compositeDatasetInfo != null)
					compositeDatasetInfo.dispose();
				compositeDatasetInfo = null;
			}
		} else
			setupExpressionContext();

		setError(null);
		validateForm();
		tcmp.getParent().update();
		tcmp.getParent().layout(true);
	}

	protected void setupItemDataCombo() {
		String[] items = new String[itemDatas.size()];
		for (int i = 0; i < items.length; i++) {
			ItemData id = itemDatas.get(i);
			JRElementDataset ed = id.getDataset();
			if (ed == null)
				items[i] = Messages.AItemDialog_0;
			else
				items[i] = ed.getDatasetRun() != null ? ed.getDatasetRun().getDatasetName() : Messages.AItemDialog_1;
			if (id.getItems() != null)
				items[i] += " ; " + id.getItems().size() + Messages.AItemDialog_3; //$NON-NLS-1$
		}
		dsviewer.setItems(items);
		dsviewer.select(itemDatas.indexOf(itemData));
	}

	protected boolean refresh = false;

	protected void validateForm() {
		if (refresh)
			return;
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

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		currentExpContext = expContext;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		if (showDataset) {
			CTabFolder tabFolder = new CTabFolder(cmp, SWT.FLAT | SWT.TOP);
			tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

			createValue(tabFolder);
			createDataset(tabFolder);
		} else
			createValue(cmp);
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				fillData();
			}
		});

		return cmp;
	}

	protected abstract void createValue(CTabFolder tabFolder);

	protected abstract void createValue(Composite parent);

	private void createDataset(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ItemDialog_7);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));

		createDataItemSelector(cmp);

		useDatasetB = new Button(cmp, SWT.CHECK);
		useDatasetB.setText(Messages.ItemDialog_6);
		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		useDatasetB.setLayoutData(gd);
		useDatasetB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleUseDataset();
			}
		});

		dsCmp = new Composite(cmp, SWT.NONE);
		dsCmp.setLayout(new FillLayout());
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		gd.widthHint = 750;
		dsCmp.setLayoutData(gd);

		bptab.setControl(cmp);
	}

	protected void createDataItemSelector(Composite cmp) {
		new Label(cmp, SWT.NONE).setText(Messages.AItemDialog_4);

		dsviewer = new Combo(cmp, SWT.READ_ONLY);
		GridData gd = new GridData();
		gd.widthHint = 200;
		dsviewer.setLayoutData(gd);

		createAddItemDataButton(cmp);

		Label sep = new Label(cmp, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		sep.setLayoutData(gd);
		dsviewer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleDsSelectionChanged();
			};
		});
	}

	protected void createAddItemDataButton(Composite cmp) {
		Button dsNewButton = new Button(cmp, SWT.PUSH);
		dsNewButton.setText(Messages.common_add);
		dsNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRDesignElementDataset eds = new JRDesignElementDataset();
				// eds.setDatasetRun(new JRDesignDatasetRun());
				ElementDatasetDialog dialog = new ElementDatasetDialog(UIUtils.getShell(), Messages.ItemDialog_7,
						Messages.ItemDialog_7, eds, jrConfig);
				dialog.setExpressionContext(currentExpContext);
				if (openChildDialog(dialog) == Dialog.OK) {
					StandardItemData id = new StandardItemData();
					id.setDataset(dialog.getDataset());
					int indx = dsviewer.getSelectionIndex();
					if (indx >= 0 && indx < itemDatas.size())
						itemDatas.add(indx, id);
					else
						itemDatas.add(id);

					Button b = getButton(IDialogConstants.OK_ID);
					b.setEnabled(true);
					itemData.removeItem(item);
					id.addItem(item);
					setItemData(id);
					setupExpressionContext();

					fillData();
				}
			}
		});
	}

	protected void createDatasetComposite(Composite container, boolean center) {
		compositeDatasetInfo = new EditableDatasetBaseComposite(
				new ComponentElementDatasetAdapter((JRDesignElementDataset) itemData.getDataset(), jrConfig), container,
				SWT.NONE) {
			@Override
			protected IEditableDatasetRun getEditableDatesetRun() {
				return new ComponentElementDatasetRunAdapter(this.getEditableDataset());
			}
		};
		compositeDatasetInfo.addDatasetRunSelectionListener(new DatasetRunSelectionListener() {
			public void selectionChanged() {
				ExpressionContext contextFromDSRun = getExpressionContextFromDSRun();
				compositeDatasetInfo.setExpressionContext(contextFromDSRun);
				setExpressionContext(contextFromDSRun);
			}
		});
		compositeDatasetInfo.setExpressionContext(getExpressionContextFromDSRun());
		compositeDatasetInfo.setDefaultExpressionContext(expContext);

		dsCmp.layout();
		UIUtils.relayoutDialog(getShell(), 0, -1);
		if (center)
			UIUtils.centerDialog(getShell());
	}

	private ExpressionContext getExpressionContextFromDSRun() {
		if (itemData.getDataset() != null) {
			JRDesignDataset ds = ModelUtils.getDesignDatasetForDatasetRun(jrConfig.getJasperDesign(),
					itemData.getDataset().getDatasetRun());
			return new ExpressionContext(ds, jrConfig);
		}
		return null;
	}

	private void handleDsSelectionChanged() {
		int indx = dsviewer.getSelectionIndex();
		if (indx >= 0) {
			itemData.removeItem(item);
			StandardItemData newID = (StandardItemData) itemDatas.get(indx);
			newID.addItem(item);
			setItemData(newID);
			compositeDatasetInfo.dispose();
			compositeDatasetInfo = null;
			fillData();

			setupExpressionContext();
			validateForm();
		}
	}

	protected void setupExpressionContext() {
		if (itemData.getDataset() != null) {
			JRDesignDataset ds = ModelUtils.getDesignDatasetForDatasetRun(jrConfig.getJasperDesign(),
					itemData.getDataset().getDatasetRun());
			currentExpContext = new ExpressionContext(ds, jrConfig);
		} else
			currentExpContext = new ExpressionContext(jrConfig);
	}

	private void handleUseDataset() {
		boolean useDs = useDatasetB.getSelection();
		if (!useDs) {
			itemData.setDataset(null);
			compositeDatasetInfo.dispose();
			compositeDatasetInfo = null;
		} else {
			itemData.setDataset(new JRDesignElementDataset());
			createDatasetComposite(dsCmp, false);
		}
		setupItemDataCombo();
		setupExpressionContext();
	}
}
