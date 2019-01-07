/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard;

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

import com.jaspersoft.studio.server.messages.Messages;

public class ServerProfileWizardDialog extends WizardDialog {

	List<SelectionListener> listeners = new ArrayList<>();
	Button testButton = null;

	public ServerProfileWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
	}

	public void addTestListener(SelectionListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}

	}

	public void removeTestListener(SelectionListener listener) {
		listeners.remove(listener);
	}

	private void fireTestPressed(SelectionEvent e) {
		for (SelectionListener listener : listeners) {
			listener.widgetSelected(e);
		}

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns = 1;
		parent.layout();

		testButton = new Button(parent, SWT.NONE);
		testButton.setFont(parent.getFont());
		testButton.setText(Messages.ServerProfileWizardDialog_testButton);
		setButtonLayoutData(testButton);
		testButton.setEnabled(true);
		testButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				fireTestPressed(event);
			}
		});
		super.createButtonsForButtonBar(parent);
	}

	public void setTestButtonEnabled(boolean b) {
		testButton.setEnabled(b);
	}
}
