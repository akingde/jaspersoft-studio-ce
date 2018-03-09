/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.wizard.resource.LovLabelProvider;
import com.jaspersoft.studio.server.wizard.resource.KeyValueDialog;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class LovDialog extends ATitledDialog {
	private List<ListItem> value;
	private ObjectMapper mapper = new ObjectMapper();

	protected LovDialog(Shell parentShell, String v) {
		super(parentShell, false);
		setTitle(Messages.LovPageContent_lov);
		try {
			value = mapper.readValue(v, new TypeReference<List<ListItem>>() {
			});
		} catch (IOException e) {
			// do nothing
		}
		if (value == null)
			value = new ArrayList<>();
	}

	private TableViewer tableViewer;

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(2, false));

		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(parent.getBackground());

		NewButton bnew = new NewButton();
		bnew.createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				KeyValueDialog d = new KeyValueDialog(UIUtils.getShell(), getName(), "");
				if (d.open() == Dialog.OK) {
					ListItem li = new ListItem();
					li.setLabel(d.getKey());
					li.setValue(d.getValue());
					return li;
				}
				return null;
			}

			private String getName() {
				String name = "name";
				if (exists(name))
					for (int i = 0; i < 1000; i++) {
						if (!exists(name + "_" + i))
							return name + "_" + i;
					}
				return name;
			}

		});
		EditButton<ListItem> bedit = new EditButton<>();
		bedit.createEditButtons(bGroup, tableViewer, (input, pos) -> {
			ListItem li = input.get(pos);
			KeyValueDialog d = new KeyValueDialog(UIUtils.getShell(), li.getLabel(), (String) li.getValue());
			if (d.open() == Dialog.OK) {
				li.setLabel(d.getKey());
				li.setValue(d.getValue());
			}
		});
		bedit.editOnDoubleClick();

		DeleteButton bdel = new DeleteButton();
		bdel.createDeleteButton(bGroup, tableViewer);

		ListOrderButtons border = new ListOrderButtons();
		border.createOrderButtons(bGroup, tableViewer);

		tableViewer.setInput(value);

		return composite;
	}

	private void buildTable(Composite composite) {
		Table table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 300;
		gd.widthHint = 300;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setLabelProvider(new LovLabelProvider());
		tableViewer.setContentProvider(new ListContentProvider());

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50));
		tlayout.addColumnData(new ColumnWeightData(50));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Value");

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}

	private boolean exists(String v) {
		for (ListItem li : value)
			if (li.getLabel().equals(v))
				return true;
		return false;
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
