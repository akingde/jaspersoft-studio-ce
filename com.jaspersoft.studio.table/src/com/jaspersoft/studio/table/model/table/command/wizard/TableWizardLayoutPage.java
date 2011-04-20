/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
package com.jaspersoft.studio.table.model.table.command.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.table.messages.Messages;
import com.jaspersoft.studio.table.model.MTable;

public class TableWizardLayoutPage extends WizardPage {
	private MTable table = new MTable();

	private boolean isTableHeader = true;
	private boolean isTableFooter = true;
	private boolean isColumnHeader = true;
	private boolean isColumnFooter = true;
	private boolean isGroupHeader = true;
	private boolean isGroupFooter = true;

	public MTable getTable() {
		return table;
	}

	protected TableWizardLayoutPage() {
		super("tablepage"); //$NON-NLS-1$
		setTitle(Messages.TableWizardLayoutPage_layout);
		setDescription(Messages.TableWizardLayoutPage_description);
	}

	@Override
	public void dispose() {

		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);

		final Button addTHeader = new Button(composite, SWT.CHECK);
		addTHeader.setText(Messages.TableWizardLayoutPage_add_table_header);
		addTHeader.setSelection(isTableHeader);
		addTHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isTableHeader = addTHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addCHeader = new Button(composite, SWT.CHECK);
		addCHeader.setText(Messages.TableWizardLayoutPage_add_column_header);
		addCHeader.setSelection(isColumnHeader);
		addCHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isColumnHeader = addCHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addGHeader = new Button(composite, SWT.CHECK);
		addGHeader.setText(Messages.TableWizardLayoutPage_add_group_header);
		addGHeader.setSelection(isGroupHeader);
		addGHeader.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isGroupHeader = addGHeader.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addGFooter = new Button(composite, SWT.CHECK);
		addGFooter.setText(Messages.TableWizardLayoutPage_add_group_footer);
		addGFooter.setSelection(isGroupFooter);
		addGFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isGroupFooter = addGFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addCFooter = new Button(composite, SWT.CHECK);
		addCFooter.setText(Messages.TableWizardLayoutPage_add_column_footer);
		addCFooter.setSelection(isColumnFooter);
		addCFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isColumnFooter = addCFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addTFooter = new Button(composite, SWT.CHECK);
		addTFooter.setText(Messages.TableWizardLayoutPage_add_table_footer);
		addTFooter.setSelection(isTableFooter);
		addTFooter.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isTableFooter = addTFooter.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(addTHeader, "Jaspersoft.wizard");

	}

	public boolean isTableHeader() {
		return isTableHeader;
	}

	public boolean isTableFooter() {
		return isTableFooter;
	}

	public boolean isColumnHeader() {
		return isColumnHeader;
	}

	public boolean isColumnFooter() {
		return isColumnFooter;
	}

	public boolean isGroupHeader() {
		return isGroupHeader;
	}

	public boolean isGroupFooter() {
		return isGroupFooter;
	}
}
