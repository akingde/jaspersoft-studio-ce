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

public class InkscapeMessageDialog extends ExtendedMessageDialog {

	private static final String dialogMessage = "To import a PDF file it is necessary to convert it into SVG format. This conversion is done with the free and open source software Inkscape, available for all the platforms. It appears that you don't have Inkscape installed, you can download and install from its <a href=\"https://inkscape.org\">official page</a>.\nIf you have Inkscape already installed then please insert the path of your Inkscape folder in the following text area. This value can be always changed from the Properties section of Jaspersoft Studio Preferences.";
	
	private Text inkscapeParentFolder;
	
	public InkscapeMessageDialog(Shell parentShell) {
		super(parentShell, "Inkscape Not Found", null, dialogMessage, MessageDialog.WARNING, new String[]{"Retry", "Cancel"}, 1, null);
	}
	
	@Override
	protected Control createCustomArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(3,false));
		
		new Label(container, SWT.NONE).setText("Inkscape parent folder");
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
				selectionDialog.setMessage("Select the Inkscape folder");
				String result = selectionDialog.open();
				if (result != null) {
					inkscapeParentFolder.setText(result);
				}
			}
		});
		return container;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == 0) {
			PreferencesUtils.storeJasperReportsProperty(ConsolePdfConverter.INKSCAPE_PATH_PROPERTY, inkscapeParentFolder.getText());	
			okPressed();
		} else if (buttonId == 1) {
			cancelPressed();
		}
	}
	
}
