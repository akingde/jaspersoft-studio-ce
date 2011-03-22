package com.jaspersoft.studio.data.basic;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class PropertyDialog extends Dialog {
	private Text property;
	private Text value;
	private TableItem tableItem = null;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PropertyDialog(Shell parentShell) {
		super(parentShell);
	}
	
	public PropertyDialog(Shell parentShell, TableItem tableItem) {
		this(parentShell);
	  this.tableItem = tableItem;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Property Name" + " :");
		
		property = new Text(container, SWT.BORDER);
		property.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("Property Value" + " :");
		
		value = new Text(container, SWT.BORDER);
		value.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		if (tableItem != null) {
			property.setText(tableItem.getText(0));
			value.setText(tableItem.getText(1));
		}
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	// GETTERS AND SETTERS
	private String getProperty() {
		return property.getText();
	}

	private String getValue() {
		return value.getText();
	}
	
	@Override
	protected void okPressed() {
		String newProperty = getProperty();
		if (newProperty.length() != 0) {
		  // Add a new row to the tableViewer
		  // a property name is case sensitive and must be unique
			if (tableItem == null && DefaultDataAdapterEditorComposite.isKeyUnique(newProperty)) {
				String newValue = getValue();
				DefaultDataAdapterEditorComposite.addRow(new String[]{newProperty, newValue});
			} 
			// Edit the existing row
			else if (tableItem instanceof TableItem) {
				String[] newKeyValue = new String[]{getProperty(), getValue()};
				DefaultDataAdapterEditorComposite.modifyRow(tableItem, newKeyValue);
			}
			
			// Close the Dialog
			setReturnCode(OK);
			close();
		}
		
	  
	}
}
