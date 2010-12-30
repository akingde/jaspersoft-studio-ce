/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.model.table.command;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.table.model.MTable;

public class TableWizardPage extends WizardPage {
	private MTable table = new MTable();

	public MTable getTable() {
		return table;
	}

	protected TableWizardPage() {
		super("barcodepage"); //$NON-NLS-1$
		setTitle(Messages.TableWizardPage_wizard);
		setDescription(Messages.TableWizardPage_wizard_description_a
				+ Messages.TableWizardPage_wizard_description_b);
	}

	@Override
	public void dispose() {

		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		// Label lbl = new Label(composite, SWT.NONE);
		// lbl.setText(Messages.BarcodeWizardPage_barbecue_types);
		//
		// lbl = new Label(composite, SWT.NONE);
		// lbl.setText(Messages.BarcodeWizardPage_barcode4j_types);
		//
		// final Table table = new Table(composite, SWT.NONE);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.heightHint = 500;
		// gd.widthHint = 300;
		// table.setLayoutData(gd);
		// table.setHeaderVisible(false);
		// table.setLinesVisible(true);
		//
		// TableColumn[] column = new TableColumn[1];
		// column[0] = new TableColumn(table, SWT.NONE);
		// column[0].setText(Messages.BarcodeWizardPage_name);
		//
		// column[0].pack();
		//
		// final Table table2 = new Table(composite, SWT.NONE);
		// gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.heightHint = 500;
		// gd.widthHint = 300;
		// table2.setLayoutData(gd);
		// table2.setHeaderVisible(false);
		// table2.setLinesVisible(true);
		//
		// TableColumn[] column2 = new TableColumn[1];
		// column2[0] = new TableColumn(table2, SWT.NONE);
		// column2[0].setText(Messages.BarcodeWizardPage_name);

	}

}
