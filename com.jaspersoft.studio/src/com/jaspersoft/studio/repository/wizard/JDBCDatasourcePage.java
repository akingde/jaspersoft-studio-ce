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

import java.sql.Connection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
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

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;
import com.jaspersoft.studio.repository.RepositoryManager;
import com.jaspersoft.studio.utils.ErrorUtil;

public class JDBCDatasourcePage extends ADatasourcePage {

	private Text driverClassTxt;
	private Text jdbcURLTxt;
	private Text usernameTxt;
	private Text passwordTxt;
	private Text jarTxt;
	private Text errTxt;

	protected JDBCDatasourcePage() {
		super("jdbcdatasourceeditor"); //$NON-NLS-1$
		setTitle(Messages.JDBCDatasourcePage_jdbc_datasource);
		setDescription(Messages.JDBCDatasourcePage_description);
	}

	@Override
	public void dispose() {
		getAMDatasource();
		super.dispose();
	}

	private MJDBCDataSource getAMDatasource() {
		MJDBCDataSource value = (MJDBCDataSource) getValue();
		value.setPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS, driverClassTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL, jdbcURLTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_USERNAME, usernameTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD, passwordTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_JAR, jarTxt.getText());
		return value;
	}

	@Override
	protected void createMoreControls(Composite parent) {

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.common_driver_class+":"); //$NON-NLS-1$

		driverClassTxt = new Text(parent, SWT.BORDER);
		driverClassTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.common_jdbc_url+":"); //$NON-NLS-1$

		jdbcURLTxt = new Text(parent, SWT.BORDER);
		jdbcURLTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.common_username+":"); //$NON-NLS-1$

		usernameTxt = new Text(parent, SWT.BORDER);
		usernameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.common_password+":"); //$NON-NLS-1$

		passwordTxt = new Text(parent, SWT.PASSWORD | SWT.BORDER);
		passwordTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.JDBCDatasourcePage_jars+":"); //$NON-NLS-1$

		Composite c = new Composite(parent, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);

		jarTxt = new Text(c, SWT.BORDER);
		jarTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button copyButton = new Button(c, SWT.PUSH);
		copyButton.setText(Messages.JDBCDatasourcePage_browse);
		copyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.MULTI);
				fd.setFileName(jarTxt.getText());
				fd.setFilterExtensions(new String[] { "*.jar", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String selection = fd.open();
				if (selection != null)
					jarTxt.setText(selection);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button testButton = new Button(parent, SWT.PUSH);
		testButton.setText(Messages.JDBCDatasourcePage_test_connection);
		testButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				final MJDBCDataSource datasource = getAMDatasource();
				Job job = new Job("TestConnection") { //$NON-NLS-1$

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							Connection c = RepositoryManager.establishConnection(datasource, null, monitor);
							if (c != null)
								c.close();

							final String msg = (c != null ? Messages.JDBCDatasourcePage_connection_established_message : Messages.JDBCDatasourcePage_connection_is_null_message);
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									errTxt.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
									errTxt.setText(msg);
								}
							});
						} catch (final Throwable e) {
							final String msg = ErrorUtil.getStackTrace(e);
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									errTxt.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
									errTxt.setText(msg);
								}
							});
							e.printStackTrace();
							return Status.OK_STATUS;
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		testButton.setLayoutData(new GridData(GridData.FILL_BOTH));

		errTxt = new Text(parent, SWT.MULTI | SWT.V_SCROLL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 100;
		errTxt.setLayoutData(gd);

	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			driverClassTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			jdbcURLTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_USERNAME);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			usernameTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			passwordTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_JAR);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			jarTxt.setText(dsName);
		}
	}

}
