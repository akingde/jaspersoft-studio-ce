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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;

public abstract class ADatasourcePage extends WizardPage {
	private AMDatasource value;
	private Text datasourceNameTxt;

	public AMDatasource getValue() {
		return value;
	}

	@Override
	public void dispose() {
		value.setPropertyValue(AMDatasource.PROPERTY_NAME, datasourceNameTxt.getText());
		super.dispose();
	}

	public void setValue(AMDatasource list) {
		this.value = list;
	}

	protected ADatasourcePage(String pageName) {
		super(pageName);
		setTitle(Messages.ADatasourcePage_datasource_editor);
		setDescription(Messages.ADatasourcePage_datasource_editor_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		Label lbl1 = new Label(composite, SWT.NONE);
		lbl1.setText(Messages.ADatasourcePage_datasource_name+":"); //$NON-NLS-1$

		datasourceNameTxt = new Text(composite, SWT.BORDER);
		datasourceNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createMoreControls(composite);

		setWidgets();
		datasourceNameTxt.setFocus();
	}

	abstract protected void createMoreControls(Composite parent);

	protected void setWidgets() {
		if (value != null) {
			String dsName = (String) value.getPropertyValue(AMDatasource.PROPERTY_NAME);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			datasourceNameTxt.setText(dsName);
		}
	}

}
