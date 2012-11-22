/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;

public class CrosstabWizardLayoutPage extends WizardPage {
	private MCrosstab crosstab;
	private boolean isAddRowTotal = true;
	private boolean isAddColTotal = true;

	public boolean isAddRowTotal() {
		return isAddRowTotal;
	}

	public boolean isAddColTotal() {
		return isAddColTotal;
	}

	public void setCrosstab(MCrosstab crosstab) {
		this.crosstab = crosstab;
	}

	public MCrosstab getCrosstab() {
		return crosstab;
	}

	protected CrosstabWizardLayoutPage() {
		super("crosstablayoutpage"); //$NON-NLS-1$
		setTitle(Messages.CrosstabWizardLayoutPage_layout);
		setImageDescriptor(Activator.getImageDescriptor("icons/wizard_preview.png"));//$NON-NLS-1$
		setDescription(Messages.CrosstabWizardLayoutPage_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		// Label lbl = new Label(composite, SWT.NONE);
		// lbl.setText("Color scheme");
		//
		// CCombo colorScheme = new CCombo(composite, SWT.BORDER);
		// // colorScheme.setItems(ModelUtils.getDataSources(jasperDesign));
		// // colorScheme.select(0);
		//
		// lbl = new Label(composite, SWT.NONE);
		// lbl.setText("Variations");
		//
		// CCombo variations = new CCombo(composite, SWT.BORDER);

		// Button useWhiteGrid = new Button(composite, SWT.CHECK);
		// useWhiteGrid.setText("Use white grid");
		// GridData gd = new GridData();
		// gd.horizontalSpan = 2;
		// useWhiteGrid.setLayoutData(gd);
		//
		// Button showGridLines = new Button(composite, SWT.CHECK);
		// showGridLines.setText("Show grid lines (adding cell border)");
		// gd = new GridData();
		// gd.horizontalSpan = 2;
		// showGridLines.setLayoutData(gd);

		final Button addRowTotals = new Button(composite, SWT.CHECK);
		addRowTotals.setText(Messages.CrosstabWizardLayoutPage_add_row_group_totals);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		addRowTotals.setLayoutData(gd);
		addRowTotals.setSelection(true);
		addRowTotals.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isAddRowTotal = addRowTotals.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		final Button addColumnTotals = new Button(composite, SWT.CHECK);
		addColumnTotals.setText(Messages.CrosstabWizardLayoutPage_add_column_group_totals);
		gd = new GridData();
		gd.horizontalSpan = 2;
		addColumnTotals.setLayoutData(gd);
		addColumnTotals.setSelection(true);
		addColumnTotals.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isAddColTotal = addColumnTotals.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");

	}

}
