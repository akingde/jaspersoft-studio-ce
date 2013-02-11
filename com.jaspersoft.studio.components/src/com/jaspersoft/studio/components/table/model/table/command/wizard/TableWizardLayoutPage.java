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
package com.jaspersoft.studio.components.table.model.table.command.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class TableWizardLayoutPage extends JSSHelpWizardPage {
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
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_LAYOUT_PAGE;
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
