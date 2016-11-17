/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.swt.widgets.NumericText;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;

/**
 * Dialog used a couple of X and Y points in the {@link PointsWizardPage}
 * 
 * @author Orlandin Marco
 *
 */
public class ProvidePointDialog extends PersistentLocationTitleAreaDialog {

	private Integer X = 0;
	
	private Integer Y = 0;
	
	public ProvidePointDialog(Shell parentShell) {
		super(parentShell);
	}
	
	public ProvidePointDialog(Shell parentShell, int X, int Y) {
		this(parentShell);
		this.X = X;
		this.Y = Y;
	}

	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Label(container, SWT.NONE).setText("X");
		final NumericText xArea = new NumericText(container, SWT.BORDER, 0, 0);
		xArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		xArea.setValues(X, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		xArea.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				X = xArea.getValueAsInteger();
				updateButton();
			}
		});
		
		new Label(container, SWT.NONE).setText("Y");
		final NumericText yArea = new NumericText(container, SWT.BORDER, 0, 0);
		yArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yArea.setValues(Y, Integer.MIN_VALUE, Integer.MAX_VALUE);
		yArea.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Y = yArea.getValueAsInteger();
				updateButton();
			}
		});
		setTitle("Define the coordinates");
		setMessage("Define the X and Y coordinates of the point");
		return container;
	}
	
  @Override
  protected void configureShell(Shell newShell) {
  	super.configureShell(newShell);
  	newShell.setText("Point Definition Dialog");
  }
	
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
	
	/**
	 * Disable the OK button if the coordinates are not correct
	 */
	protected void updateButton(){
		getButton(IDialogConstants.OK_ID).setEnabled(X != null && Y != null);
	}
}
