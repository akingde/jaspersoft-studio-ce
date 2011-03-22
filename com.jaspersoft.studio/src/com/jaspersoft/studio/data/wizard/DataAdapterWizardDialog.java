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

	public void setTestButtonEnabled(boolean b) {
		testButton.setEnabled(b);
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
		
		//super.createButtonsForButtonBar(parent);
	}
	
	

}
