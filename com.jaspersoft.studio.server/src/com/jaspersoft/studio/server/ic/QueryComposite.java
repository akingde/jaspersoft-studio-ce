/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.data.designer.ICQuery;
import com.jaspersoft.studio.data.designer.IFilterQuery;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.wizard.resource.StringValueDialog;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class QueryComposite {
	private ICQuery value;
	private IFilterQuery fq;
	private ObjectMapper mapper = new ObjectMapper();
	private Text tQuery;
	private Text tValue;
	private TableViewer tableViewer;

	public QueryComposite(String v, IFilterQuery fq) {
		this.fq = fq;
		try {
			value = mapper.readValue(v, ICQuery.class);
		} catch (IOException e) {
			// do nothing
		}
		if (value == null)
			value = new ICQuery();
	}

	public void createComposite(Composite composite) {
		CTabFolder tabFolder = new CTabFolder(composite, SWT.TOP | SWT.FLAT);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 250;
		gd.widthHint = 400;
		tabFolder.setLayoutData(gd);

		createQuery(tabFolder);
		createFields(tabFolder);

		if (fq != null) {
			Button btn = new Button(composite, SWT.PUSH);
			btn.setText(Messages.QueryDialog_1);
			btn.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
			btn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					try {
						value = mapper.readValue(fq.getFilterQuery(), ICQuery.class);
						refresh();
					} catch (IOException e1) {
						UIUtils.showError(e1);
					}
					// here we could read also domain uri?
				}
			});
		}
		refresh();
	}

	private void createFields(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.QueryDialog_2);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		cmp.setBackgroundMode(SWT.INHERIT_FORCE);

		Composite scmp = new Composite(cmp, SWT.NONE);
		scmp.setLayout(new GridLayout(2, false));
		scmp.setBackgroundMode(SWT.INHERIT_FORCE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		scmp.setLayoutData(gd);

		new Label(scmp, SWT.NONE).setText(Messages.QueryDialog_3);

		tValue = new Text(scmp, SWT.BORDER);
		tValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tValue.addModifyListener(e -> value.valueField = tValue.getText());

		buildTable(cmp);

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(cmp.getBackground());

		NewButton bnew = new NewButton() {
			@Override
			protected void afterElementAdded(Object selement) {
				handleValueChanged();
			}
		};
		bnew.createNewButtons(bGroup, tableViewer, (input, pos) -> {
			StringValueDialog d = new StringValueDialog(cmp.getShell(), Messages.QueryDialog_4);
			if (d.open() == Dialog.OK)
				return d.getValue();
			return null;
		});
		EditButton<String> bedit = new EditButton<String>() {
			@Override
			protected void afterElementModified(String element, List<String> inlist, int ind) {
				handleValueChanged();
			}
		};
		bedit.createEditButtons(bGroup, tableViewer, (input, pos) -> {
			String li = input.get(pos);
			StringValueDialog d = new StringValueDialog(UIUtils.getShell(), li);
			if (d.open() == Dialog.OK)
				input.set(pos, d.getValue());
		});
		bedit.editOnDoubleClick();

		DeleteButton bdel = new DeleteButton() {
			@Override
			protected void afterElementDeleted(Object element) {
				handleValueChanged();
			}
		};
		bdel.createDeleteButton(bGroup, tableViewer);

		ListOrderButtons border = new ListOrderButtons();
		border.createOrderButtons(bGroup, tableViewer);
		border.addChangeListener(event -> handleValueChanged());

		tableViewer.setInput(value.columns);

		UIUtils.getDisplay().asyncExec(() -> tabFolder.setSelection(0));
		bptab.setControl(cmp);
	}

	private void createQuery(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.DataQueryAdapters_querytab);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setBackgroundMode(SWT.INHERIT_FORCE);

		tQuery = new Text(cmp, SWT.BORDER | SWT.MULTI);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gd.heightHint = 100;
		gd.widthHint = 250;
		tQuery.setLayoutData(gd);
		tQuery.addModifyListener(e -> {
			value.query = tQuery.getText();
			handleValueChanged();
		});

		bptab.setControl(cmp);
	}

	private void buildTable(Composite composite) {
		Table table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 300;
		gd.widthHint = 250;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100));
		table.setLayout(tlayout);

		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setText(Messages.QueryDialog_5);
		tc.pack();
	}

	private void refresh() {
		if (value == null)
			value = new ICQuery();
		tQuery.setText(Misc.nvl(value.query));
		tValue.setText(Misc.nvl(value.valueField));
		tableViewer.setInput(value.columns);
	}

	protected void handleValueChanged() {
		// do nothing
	}

	public String getValue() {
		try {
			return mapper.writeValueAsString(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
