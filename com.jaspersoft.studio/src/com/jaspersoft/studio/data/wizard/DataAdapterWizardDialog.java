/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class DataAdapterWizardDialog extends WizardDialog {

	List<SelectionListener> listeners = new ArrayList<SelectionListener>();
	Button testButton = null;
	
	public DataAdapterWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
	}
	
	public void addTestListener(SelectionListener listener)
	{
		if (!listeners.contains(listener))
		{
			listeners.add(listener);
		}
		
	}
	
	public void removeTestListener(SelectionListener listener)
	{
		listeners.remove(listener);
	}

	private void fireTestPressed(SelectionEvent e) {
		for (SelectionListener listener : listeners)
		{
			listener.widgetSelected(e);
		}
		
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		panel.setFont(parent.getFont());
		((GridLayout)parent.getLayout()).numColumns=1;
		parent.layout();
		GridLayout layout = new GridLayout();
		layout.numColumns=1;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		panel.setLayout(layout);
		testButton = new Button(panel, SWT.NONE);
		testButton.setFont(parent.getFont());
		testButton.setText("Test");
		setButtonLayoutData(testButton);
		testButton.setEnabled(false);
		testButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				fireTestPressed(event);
			}

		});
		super.createButtonsForButtonBar(panel);
		panel.setLayout(layout);	
	}
	
	public void setTestButtonEnabled(boolean b) {
		testButton.setEnabled(b);
	}
}
