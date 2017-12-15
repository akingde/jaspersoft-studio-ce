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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.preferences.editor.table.TableLabelProvider;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorQuery;
import com.jaspersoft.studio.swt.events.ChangeEvent;
import com.jaspersoft.studio.swt.events.ChangeListener;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

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

	private Table table;
	private TableViewer tableViewer;
	private NewButton bnew;
	private DeleteButton bdel;
	private ListOrderButtons border;

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

		bnew = new NewButton() {
			@Override
			protected void afterElementAdded(Object selement) {
				setValues();
				super.afterElementAdded(selement);
			}
		};
		bnew.createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				StringValueDialog d = new StringValueDialog(UIUtils.getShell(), Messages.QueryVisibleColumnsTable_0);
				if (d.open() == Dialog.OK)
					return d.getValue();
				return null;
			}

		});
		EditButton<String> bedit = new EditButton<String>();
		bedit.createEditButtons(bGroup, tableViewer, new IEditElement<String>() {

			@Override
			public void editElement(List<String> input, int pos) {
				StringValueDialog d = new StringValueDialog(UIUtils.getShell(), input.get(pos));
				if (d.open() == Dialog.OK) {
					input.set(pos, d.getValue());
					setValues();
				}
			}
		});
		bedit.editOnDoubleClick();
		bdel = new DeleteButton() {
			@Override
			protected void afterElementDeleted(Object selement) {
				setValues();
				super.afterElementDeleted(selement);
			}
		};
		bdel.createDeleteButton(bGroup, tableViewer);
		border = new ListOrderButtons();
		border.createOrderButtons(bGroup, tableViewer);
		border.addChangeListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event) {
				setValues();
			}
		});

		tableViewer.setInput(new ArrayList<String>(Arrays.asList(rd.getQueryVisibleColumns())));

	}

	class StringValueDialog extends Dialog {
		private String value;

		protected StringValueDialog(Shell parentShell, String value) {
			super(parentShell);
			this.value = value;
		}

		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText(Messages.QueryVisibleColumnsTable_2);
		}

		public String getValue() {
			return value;
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite cmp = (Composite) super.createDialogArea(parent);
			cmp.setLayout(new GridLayout(2, false));

			new Label(cmp, SWT.NONE).setText(Messages.QueryVisibleColumnsTable_2);

			final Text txt = new Text(cmp, SWT.BORDER);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 150;
			txt.setLayoutData(gd);
			txt.setText(Misc.nvl(value));
			txt.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					value = txt.getText();
					getButton(OK).setEnabled(!Misc.isNullOrEmpty(value));
				}
			});

			return cmp;
		}
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
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
