/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.sql;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterDescriptor;
import com.jaspersoft.studio.data.querydesigner.sql.ui.DBMetadata;
import com.jaspersoft.studio.swt.widgets.CSashForm;

public class SQLQueryDesigner extends SimpleSQLQueryDesigner {
	private SashForm sf;
	private DBMetadata dbMetadata;

	public SQLQueryDesigner() {
		super();
	}

	@Override
	public Control getControl() {
		return sf;
	}

	@Override
	public Control createControl(Composite parent) {
		sf = new CSashForm(parent, SWT.HORIZONTAL);
		sf.setLayoutData(new GridData(GridData.FILL_BOTH));
		sf.setLayout(new GridLayout());

		dbMetadata = new DBMetadata();
		Control c = dbMetadata.createControl(sf);
		c.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		super.createControl(sf);
		control.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		sf.setWeights(new int[] { 250, 750 });
		return sf;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor da) {
		if (da instanceof JDBCDataAdapterDescriptor) {
			try {
				DataAdapterService das = DataAdapterServiceUtil.getInstance(jConfig).getService(da.getDataAdapter());
				Map<String, Object> parameters = new HashMap<String, Object>();
				das.contributeParameters(parameters);

				dbMetadata.updateUI((Connection) parameters.get(JRParameter.REPORT_CONNECTION));
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
}
