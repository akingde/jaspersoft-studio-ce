/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.wizard.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.wizard.validator.EmptyStringValidator;
import com.jaspersoft.studio.server.wizard.validator.URLValidator;

public class ServerProfilePage extends WizardPage {
	private MServerProfile sprofile;

	public ServerProfilePage(MServerProfile sprofile) {
		super("serverprofilepage");
		setTitle("JasperServer connection configuration");
		setDescription("Configure server connection");
		this.sprofile = sprofile;
	}

	public void createControl(Composite parent) {
		DataBindingContext dbc = new DataBindingContext();
		WizardPageSupport.create(this, dbc);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		new Label(composite, SWT.NONE).setText("Name");
		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("URL");
		Text turl = new Text(composite, SWT.BORDER);
		turl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("User");
		Text tuser = new Text(composite, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("Password");
		Text tpass = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		dbc.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(sprofile.getValue(), "name"),
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator()),
				null);
		dbc.bindValue(SWTObservables.observeText(turl, SWT.Modify),
				PojoObservables.observeValue(sprofile.getValue(), "url"),
				new UpdateValueStrategy()
						.setAfterConvertValidator(new URLValidator()), null);
		dbc.bindValue(SWTObservables.observeText(tuser, SWT.Modify),
				PojoObservables.observeValue(sprofile.getValue(), "user"),
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator()),
				null);
		dbc.bindValue(SWTObservables.observeText(tpass, SWT.Modify),
				PojoObservables.observeValue(sprofile.getValue(), "pass"));
	}
}
