/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.preferences.editor.table.TableLabelProvider;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.StringValueDialog;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorQuery;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class QueryVisibleColumnsTable {
	private ResourceDescriptor rd;
	private APageContent page;
	private SelectorQuery sQuery;

	public QueryVisibleColumnsTable(Composite composite, ResourceDescriptor rd, APageContent page,
			SelectorQuery sQuery) {
		this.rd = rd;
		this.page = page;
		this.sQuery = sQuery;
		createControls(composite);
	}

	private TableViewer tableViewer;

	public void setValues() {
		List<String> lst = (List<String>) tableViewer.getInput();
		rd.setQueryVisibleColumns(lst.toArray(new String[lst.size()]));
		page.setPageComplete(sQuery.isPageComplete());
	}

	private void createControls(Composite composite) {
		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		NewButton bnew = new NewButton() {
			@Override
			protected void afterElementAdded(Object selement) {
				setValues();
				super.afterElementAdded(selement);
			}
		};
		bnew.createNewButtons(bGroup, tableViewer, (input, pos) -> {
			StringValueDialog d = new StringValueDialog(composite.getShell(), Messages.QueryVisibleColumnsTable_0);
			if (d.open() == Dialog.OK)
				return d.getValue();
			return null;
		});
		EditButton<String> bedit = new EditButton<>();
		bedit.createEditButtons(bGroup, tableViewer, (input, pos) -> {
			StringValueDialog d = new StringValueDialog(composite.getShell(), input.get(pos));
			if (d.open() == Dialog.OK) {
				input.set(pos, d.getValue());
				setValues();
			}
		});
		bedit.editOnDoubleClick();
		DeleteButton bdel = new DeleteButton() {
			@Override
			protected void afterElementDeleted(Object selement) {
				setValues();
				super.afterElementDeleted(selement);
			}
		};
		bdel.createDeleteButton(bGroup, tableViewer);
		ListOrderButtons border = new ListOrderButtons();
		border.createOrderButtons(bGroup, tableViewer);
		border.addChangeListener(event -> setValues());

		tableViewer.setInput(new ArrayList<String>(Arrays.asList(rd.getQueryVisibleColumns())));

	}

	private void buildTable(Composite composite) {
		Table table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 200;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.QueryVisibleColumnsTable_1);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TableLabelProvider());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new ListContentProvider());
	}

}
