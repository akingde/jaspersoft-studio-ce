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
package com.jaspersoft.studio.wizards.group;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;

public class WizardBandGroupLayoutPage extends WizardPage {
	private boolean addHeader = true;
	private boolean addFooter = true;

	public boolean isAddHeader() {
		return addHeader;
	}

	public boolean isAddFooter() {
		return addFooter;
	}

	public WizardBandGroupLayoutPage() {
		super("grouplayout"); //$NON-NLS-1$
		setTitle(Messages.WizardBandGroupLayoutPage_group_layout);
		setDescription(Messages.WizardBandGroupLayoutPage_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);

		final Button addHeaderButon = new Button(composite, SWT.CHECK);
		addHeaderButon.setText(Messages.WizardBandGroupLayoutPage_add_group_header);
		addHeaderButon.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				addHeader = addHeaderButon.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		addHeaderButon.setSelection(true);

		final Button addFooterButon = new Button(composite, SWT.CHECK);
		addFooterButon.setText(Messages.WizardBandGroupLayoutPage_add_group_footer);
		addFooterButon.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				addFooter = addFooterButon.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		addFooterButon.setSelection(true);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");
	}

}
