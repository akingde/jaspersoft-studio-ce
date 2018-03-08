/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.imports.svg;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

import net.sf.jasperreports.eclipse.ui.util.ExtendedMessageDialog;

/**
 * Message dialog shown when inkscape is necessary but not found, it inform the user
 * on how to download it and provide a text area where the path can be inserted manually
 * 
 * @author Orlandin Marco
 *
 */
public class InkscapeMessageDialog extends ExtendedMessageDialog {

	private static final String dialogMessage = Messages.InkscapeMessageDialog_dialogMessage;
	
	private Text inkscapeParentFolder;
	
	public InkscapeMessageDialog(Shell parentShell) {
		super(parentShell, Messages.InkscapeMessageDialog_dialogTitle, null, dialogMessage, MessageDialog.WARNING, new String[]{Messages.InkscapeMessageDialog_retryOption, Messages.InkscapeMessageDialog_cancelOption}, 1, null);
	}
	
	@Override
	protected Control createCustomArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(3,false));
		
		new Label(container, SWT.NONE).setText(Messages.InkscapeMessageDialog_partentLabel);
		inkscapeParentFolder = new Text(container, SWT.BORDER);
		inkscapeParentFolder.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		String inkscapeCurrentPath = PreferencesUtils.getJasperReportsProperty(ConsolePdfConverter.INKSCAPE_PATH_PROPERTY);
		if (inkscapeCurrentPath != null) {
			inkscapeParentFolder.setText(inkscapeCurrentPath);
		}
		Button browseButton = new Button(container, SWT.PUSH);
		browseButton.setText(Messages.common_browse);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog selectionDialog = new DirectoryDialog(getShell());
				selectionDialog.setMessage(Messages.InkscapeMessageDialog_fileDialogTitle);
				String result = selectionDialog.open();
				if (result != null) {
					inkscapeParentFolder.setText(result);
				}
			}
		});
		return container;
	}

	/**
	 * Check if the button is pressed is Ok store the property of the path of inkscape
	 * if it was defined
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == 0) {
			if (!inkscapeParentFolder.getText().isEmpty()) {
				PreferencesUtils.storeJasperReportsProperty(ConsolePdfConverter.INKSCAPE_PATH_PROPERTY, inkscapeParentFolder.getText());	
			}
			okPressed();
		} else if (buttonId == 1) {
			cancelPressed();
		}
	}
	
}
