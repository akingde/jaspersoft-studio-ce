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
package com.jaspersoft.studio.data.jrdsprovider;

import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.utils.Misc;

public class JrdsProviderDataAdapterComposite extends Composite {

	private JrdsProviderDataAdapterDescriptor jrdsDADesc = null;
	private Text textJRDSProviderClassName;
	private ClasspathComponent cpath;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JrdsProviderDataAdapterComposite(Composite parent, int style) {

		/*
		 * UI ELEMENTS
		 */
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("JasperReports DataSource Provider Class Name:");

		textJRDSProviderClassName = new Text(this, SWT.BORDER);
		textJRDSProviderClassName.setLayoutData(new GridData(SWT.FILL,
				SWT.CENTER, true, false, 1, 1));

		/*
		 * UI ELEMENTS LISTENERS
		 */
		textJRDSProviderClassName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				((DataSourceProviderDataAdapter) jrdsDADesc.getDataAdapter())
						.setProviderClass(textJRDSProviderClassName.getText()
								.trim());
			}
		});

		cpath = new ClasspathComponent(this);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		cpath.getControl().setLayoutData(gd);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(JrdsProviderDataAdapterDescriptor dataAdapter) {
		jrdsDADesc = dataAdapter;
		DataSourceProviderDataAdapter da = (DataSourceProviderDataAdapter) jrdsDADesc
				.getDataAdapter();
		textJRDSProviderClassName.setText(Misc.nvl(da.getProviderClass(),
				"com.jaspersoft.studio.data.sample.PersonBeansDataSource"));

		cpath.setClasspaths(da.getClasspath());
	}

	public DataAdapterDescriptor getDataAdapter() {

		if (jrdsDADesc == null)
			jrdsDADesc = new JrdsProviderDataAdapterDescriptor();

		DataSourceProviderDataAdapter da = (DataSourceProviderDataAdapter) jrdsDADesc
				.getDataAdapter();
		da.setProviderClass(textJRDSProviderClassName.getText().trim());
		da.setClasspath(cpath.getClasspaths());

		return jrdsDADesc;
	}

	public String getHelpContextId() {
		// TODO Auto-generated method stub
		return null;
	}
}
