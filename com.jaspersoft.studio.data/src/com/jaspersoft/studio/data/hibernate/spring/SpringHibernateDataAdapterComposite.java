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
package com.jaspersoft.studio.data.hibernate.spring;

import net.sf.jasperreports.data.hibernate.spring.SpringHibernateDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class SpringHibernateDataAdapterComposite extends Composite {

	private SpringHibernateDataAdapterDescriptor hbmDADesc = null;
	private Text springConfig;
	private Text beanIDtxt;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public SpringHibernateDataAdapterComposite(Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Spring Configuration:");

		springConfig = new Text(composite, SWT.BORDER);
		springConfig.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button btnBrowse = new Button(composite, SWT.NONE);
		btnBrowse.setText("...");

		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell());
				fd.setFileName(springConfig.getText());
				fd.setFilterExtensions(new String[] { "*.cfg.xml", "*.*" });
				String selection = fd.open();
				if (selection != null)
					springConfig.setText(selection);
			}
		});

		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Session Factory Bean ID:");

		beanIDtxt = new Text(composite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		beanIDtxt.setLayoutData(gd);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(SpringHibernateDataAdapterDescriptor dataAdapter) {
		this.hbmDADesc = dataAdapter;

		SpringHibernateDataAdapter xmlDataAdapter = hbmDADesc.getDataAdapter();

		springConfig.setText(Misc.nvl(xmlDataAdapter.getSpringConfig(), ""));
		beanIDtxt.setText(Misc.nvl(xmlDataAdapter.getBeanId(), ""));

	}

	public DataAdapterDescriptor getDataAdapter() {
		if (hbmDADesc == null)
			hbmDADesc = new SpringHibernateDataAdapterDescriptor();

		SpringHibernateDataAdapter dataAdapter = hbmDADesc.getDataAdapter();

		dataAdapter.setSpringConfig(springConfig.getText());
		dataAdapter.setBeanId(beanIDtxt.getText());

		return hbmDADesc;
	}

	public String getHelpContextId() {
		return "";
	}
}
