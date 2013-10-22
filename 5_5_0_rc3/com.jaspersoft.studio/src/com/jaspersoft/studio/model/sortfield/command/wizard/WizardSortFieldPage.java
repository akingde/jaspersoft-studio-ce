/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.sortfield.command.wizard;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.property.dataset.TLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

public class WizardSortFieldPage extends WizardPage {

	private final class SFSelectionListener implements SelectionListener {
		public void widgetSelected(SelectionEvent e) {
			IStructuredSelection sel = (IStructuredSelection) tableView.getSelection();
			Object obj = sel.getFirstElement();
			if (obj instanceof JRField) {
				jrSortfield.setName(((JRField) obj).getName());
				jrSortfield.setType(SortFieldTypeEnum.FIELD);
			} else if (obj instanceof JRVariable) {
				jrSortfield.setName(((JRVariable) obj).getName());
				jrSortfield.setType(SortFieldTypeEnum.VARIABLE);
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}

	private JRDesignDataset jrDataset;
	private JRDesignSortField jrSortfield;
	private TableViewer tableView;
	private Table table;

	public WizardSortFieldPage(JRDesignDataset jrDataset, JRDesignSortField jrSortField) {
		super("sortfieldpage"); //$NON-NLS-1$
		this.jrDataset = jrDataset;
		this.jrSortfield = jrSortField;
		setTitle("New Sort Field");
		setDescription("Create a SortField from Field or Variable");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		setControl(composite);

		table = new Table(composite, SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);

		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(table, SWT.NONE);
		col[0].setText("Dataset Fields and Variables");
		col[0].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		table.setLayout(tlayout);

		tableView = new TableViewer(table);
		tableView.setContentProvider(new ListContentProvider());
		tableView.setLabelProvider(new TLabelProvider());

		fillTable();

		SFSelectionListener listener = new SFSelectionListener();
		table.addSelectionListener(listener);

		if (table.getItemCount() > 0) {
			table.select(0);
			listener.widgetSelected(null);
		}

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");
	}

	private void fillTable() {
		List<Object> objects = new ArrayList<Object>();
		for (JRField f : jrDataset.getFieldsList()) {
			boolean found = false;
			for (JRSortField sf : jrDataset.getSortFieldsList()) {
				if (sf.getType().equals(SortFieldTypeEnum.FIELD) && sf.getName().equals(f.getName())) {
					found = true;
					break;
				}
			}
			if (!found)
				objects.add(f);
		}

		for (JRVariable f : jrDataset.getVariablesList()) {
			boolean found = false;
			for (JRSortField sf : jrDataset.getSortFieldsList()) {
				if (sf.getType().equals(SortFieldTypeEnum.VARIABLE) && sf.getName().equals(f.getName())) {
					found = true;
					break;
				}
			}
			if (!found)
				objects.add(f);
		}

		tableView.setInput(objects);
	}
}
