/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.repository.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.AMFileDataSource;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;

public abstract class AFileDatasourcePage extends ADatasourcePage {
	private Text fileNameTxt;
	private Text dateFormat;
	private Text numberFormat;

	protected AFileDatasourcePage(String pageName) {
		super(pageName);
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(AMFileDataSource.PROPERTY_FILENAME, fileNameTxt.getText());
		value.setPropertyValue(AMFileDataSource.PROPERTY_NUMBERFORMAT, numberFormat.getText());
		value.setPropertyValue(AMFileDataSource.PROPERTY_DATEFORMAT, dateFormat.getText());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.AFileDatasourcePage_file_name+":"); //$NON-NLS-1$

		Composite c = new Composite(parent, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);

		fileNameTxt = new Text(c, SWT.BORDER);
		fileNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button copyButton = new Button(c, SWT.PUSH);
		copyButton.setText(Messages.AFileDatasourcePage_browse);
		copyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell());
				fd.setFileName(fileNameTxt.getText());
				fd.setFilterExtensions(getFilterExt());
				String selection = fd.open();
				if (selection != null)
					fileNameTxt.setText(selection);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.AFileDatasourcePage_number_format+":"); //$NON-NLS-1$

		c = new Composite(parent, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);

		numberFormat = new Text(c, SWT.BORDER);
		numberFormat.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		numberFormat.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getValue().setPropertyValue(AMFileDataSource.PROPERTY_NUMBERFORMAT, numberFormat.getText());
			}
		});

		Button numberFormatButton = new Button(c, SWT.PUSH);
		numberFormatButton.setText("..."); //$NON-NLS-1$
		numberFormatButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setDatePatterns(false);
				wizard.setValue((String) getValue().getPropertyValue(AMFileDataSource.PROPERTY_NUMBERFORMAT));
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					getValue().setPropertyValue(AMFileDataSource.PROPERTY_NUMBERFORMAT, val);
					numberFormat.setText(val);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.AFileDatasourcePage_date_format+":"); //$NON-NLS-1$

		c = new Composite(parent, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);

		dateFormat = new Text(c, SWT.BORDER);
		dateFormat.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dateFormat.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getValue().setPropertyValue(AMFileDataSource.PROPERTY_DATEFORMAT, dateFormat.getText());
			}
		});

		Button dateFormatButton = new Button(c, SWT.PUSH);
		dateFormatButton.setText("..."); //$NON-NLS-1$
		dateFormatButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setValue((String) getValue().getPropertyValue(AMFileDataSource.PROPERTY_DATEFORMAT));
				wizard.setNumberPatterns(false);
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					getValue().setPropertyValue(AMFileDataSource.PROPERTY_DATEFORMAT, val);
					dateFormat.setText(val);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	protected abstract String[] getFilterExt();

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(AMFileDataSource.PROPERTY_FILENAME);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			fileNameTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(AMFileDataSource.PROPERTY_NUMBERFORMAT);
			if (dsName == null)
				dsName = "#,##0.##"; //$NON-NLS-1$
			numberFormat.setText(dsName);

			dsName = (String) value.getPropertyValue(AMFileDataSource.PROPERTY_DATEFORMAT);
			if (dsName == null)
				dsName = "yyyy-MM-dd"; //$NON-NLS-1$
			dateFormat.setText(dsName);

		}
	}
}
