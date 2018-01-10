/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.List;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.DescriptorPropertyLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class TableItemDialog extends AItemDialog {

	private EditButton<StandardItemProperty> bpropEdit;
	private TableViewer tviewer;
	private Composite vcmp;

	public TableItemDialog(Shell parentShell, ADescriptor descriptor, JasperReportsConfiguration jrConfig,
			boolean showDataset) {
		super(parentShell, descriptor, jrConfig, showDataset);
	}

	protected void createValue(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ItemDialog_0);

		createValue(tabFolder);

		bptab.setControl(vcmp);
	}

	@Override
	protected void createValue(Composite parent) {
		vcmp = new Composite(parent, SWT.NONE);
		vcmp.setLayout(new GridLayout(2, false));
		vcmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		Table wtable = new Table(vcmp, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		gd.heightHint = 300;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(true);

		tviewer = new TableViewer(wtable);

		TableViewerColumn column = new TableViewerColumn(tviewer, SWT.NONE);
		column.setLabelProvider(new DescriptorPropertyLabelProvider(descriptor) {

			@Override
			public String getText(Object element) {
				return super.getColumnText(element, 0);
			}

			@Override
			public String getColumnText(Object element, int columnIndex) {
				return super.getColumnText(element, 0);
			}
		});
		column.getColumn().setText(Messages.ItemDialog_1);
		column.getColumn().setWidth(100);

		column = new TableViewerColumn(tviewer, SWT.NONE);
		column.setLabelProvider(new DescriptorPropertyLabelProvider(descriptor) {
			@Override
			public Image getImage(Object element) {
				return getColumnImage(element, 1);
			}
		});
		column.getColumn().setText(Messages.ItemDialog_2);
		column.getColumn().setWidth(100);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, false));
		tlayout.addColumnData(new ColumnWeightData(50, false));
		wtable.setLayout(tlayout);

		tviewer.setContentProvider(new ListContentProvider());
		// tviewer.setLabelProvider(lp);
		ColumnViewerToolTipSupport.enableFor(tviewer, ToolTip.NO_RECREATE);
		UIUtil.setViewerCellEditingOnDblClick(tviewer);
		tviewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				bpropEdit.push();
			}
		});

		Composite bGroup = new Composite(vcmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton() {
			protected void afterElementAdded(Object selement) {
				validateForm();
			}
		}.createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				StandardItemProperty prop = new StandardItemProperty("newname", "value", null); //$NON-NLS-1$
				descriptor.setOldItemProperty(null);
				ItemPropertyDialog dialog = new ItemPropertyDialog(getShell(), prop, descriptor, currentExpContext);
				if (openChildDialog(dialog) == Window.OK)
					return dialog.getValue();
				return null;
			}

		});

		bpropEdit = new EditButton<StandardItemProperty>() {
			@Override
			protected void afterElementModified(StandardItemProperty element, List<StandardItemProperty> inlist,
					int ind) {
				validateForm();
			}
		};
		bpropEdit.createEditButtons(bGroup, tviewer, new IEditElement<StandardItemProperty>() {

			@Override
			public void editElement(List<StandardItemProperty> input, int pos) {
				StandardItemProperty old = input.get(pos);
				descriptor.setOldItemProperty(old);
				StandardItemProperty prop = (StandardItemProperty) old.clone();
				ItemPropertyDialog dialog = new ItemPropertyDialog(getShell(), prop, descriptor, currentExpContext);
				if (openChildDialog(dialog) == Window.OK)
					input.set(pos, dialog.getValue());
			}
		});
		final DeleteButton delb = new DeleteButton() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.swt.widgets.table.DeleteButton#
			 * afterElementDeleted(java.lang.Object)
			 */
			@Override
			protected void afterElementDeleted(Object element) {
				validateForm();
			}

			@Override
			protected boolean confirmDelete(Object obj) {
				StandardItemProperty p = (StandardItemProperty) obj;
				ItemPropertyDescription<?> ipd = descriptor.getDescription(p.getName());
				if (ipd != null && ipd.isMandatory()) {
					if (!UIUtils.showConfirmation(Messages.ItemDialog_3, Messages.ItemDialog_4))
						return false;
				}
				return super.confirmDelete(obj);
			}
		};
		delb.createDeleteButton(bGroup, tviewer);
	}

	@Override
	protected void fillData() {
		tviewer.setInput(item.getProperties());
		tviewer.getTable().setSelection(0);

		super.fillData();
	}

}
