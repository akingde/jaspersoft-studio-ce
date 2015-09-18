/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.List;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

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

import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.label.ItemPropertyLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class TableItemDialog extends AItemDialog {

	public TableItemDialog(Shell parentShell, ADescriptor descriptor,
			JasperReportsConfiguration jrConfig) {
		super(parentShell, descriptor, jrConfig);
	}

	private EditButton<StandardItemProperty> bpropEdit;
	private TableViewer tviewer;

	protected void createValue(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ItemDialog_0);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		Table wtable = new Table(cmp, SWT.V_SCROLL | SWT.MULTI
				| SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		gd.heightHint = 300;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(true);

		tviewer = new TableViewer(wtable);

		TableViewerColumn column = new TableViewerColumn(tviewer, SWT.NONE);
		column.setLabelProvider(new ItemPropertyLabelProvider(descriptor) {

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
		column.setLabelProvider(new ItemPropertyLabelProvider(descriptor) {
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

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton() {
			protected void afterElementAdded(Object selement) {
				validateForm();
			}
		}.createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				StandardItemProperty prop = new StandardItemProperty("newname", //$NON-NLS-1$
						"value", null); //$NON-NLS-1$
				descriptor.setOldItemProperty(null);
				ItemPropertyDialog dialog = new ItemPropertyDialog(getShell(),
						prop, descriptor);
				dialog.setExpressionContext(currentExpContext);
				if (openChildDialog(dialog) == Window.OK)
					return dialog.getValue();
				return null;
			}

		});

		bpropEdit = new EditButton<StandardItemProperty>() {
			@Override
			protected void afterElementModified(Object element,
					List<StandardItemProperty> inlist, int ind) {
				validateForm();
			}
		};
		bpropEdit.createEditButtons(bGroup, tviewer,
				new IEditElement<StandardItemProperty>() {

					@Override
					public void editElement(List<StandardItemProperty> input,
							int pos) {
						StandardItemProperty old = input.get(pos);
						descriptor.setOldItemProperty(old);
						StandardItemProperty prop = (StandardItemProperty) old
								.clone();
						ItemPropertyDialog dialog = new ItemPropertyDialog(
								getShell(), prop, descriptor);
						dialog.setExpressionContext(currentExpContext);
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
				ItemPropertyDescription<?> ipd = descriptor.getDescription(p
						.getName());
				if (ipd.isMandatory())
					if (!UIUtils.showConfirmation(Messages.ItemDialog_3,
							Messages.ItemDialog_4))
						return false;
				return super.confirmDelete(obj);
			}
		};
		delb.createDeleteButton(bGroup, tviewer);

		bptab.setControl(cmp);
	}

	protected void fillData() {
		tviewer.setInput(item.getProperties());
		tviewer.getTable().setSelection(0);

		super.fillData();
	}
}
