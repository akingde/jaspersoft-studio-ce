/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.csv;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

/**
 * A simple dialog to ask a string value to the user
 * 
 * @author Orlandin Marco
 * 
 */
public class NameComboDialog extends ATitledDialog {

	private Combo columnName;

	private String choosenName;
	private List<String> names;

	public NameComboDialog(Shell parentShell, List<String> names) {
		super(parentShell);
		setTitle(Messages.NameComboDialog_0);
		this.names = names;
	}

	/**
	 * Return the value in the text field after the button ok is pressed, or the value used to initialize the textfield
	 * before the button ok is pressed
	 */
	public String getName() {
		return choosenName;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, true));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		columnName = new Combo(container, SWT.READ_ONLY);
		columnName.setItems(names.toArray(new String[names.size()]));
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.widthHint = 200;
		columnName.select(0);
		choosenName = names.get(0);
		columnName.setLayoutData(textData);
		columnName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				choosenName = names.get(columnName.getSelectionIndex());
			}
		});
		return area;
	}

}
