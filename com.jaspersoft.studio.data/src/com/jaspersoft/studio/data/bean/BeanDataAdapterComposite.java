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
package com.jaspersoft.studio.data.bean;

import net.sf.jasperreports.data.bean.BeanDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.swt.widgets.ClassType;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.utils.Misc;

public class BeanDataAdapterComposite extends Composite {

	private BeanDataAdapterDescriptor beanDataAdapter = null;
	private ClassType factoryText;
	private Text methodText;
	private Button useFDcheck;
	private ClasspathComponent cpath;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public BeanDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblFactory = new Label(this, SWT.NONE);
		lblFactory.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 2, 1));
		lblFactory
				.setText("Factory class (the class that will produce JavaBeans");

		factoryText = new ClassType(this);
		factoryText
				.setClassType("com.jaspersoft.ireport.examples.SampleJRDataSourceFactory");

		Label lblMethodName = new Label(this, SWT.NONE);
		lblMethodName
				.setText("The static method in the Factory that returns a Collection<?> or an array of objects ");
		new Label(this, SWT.NONE);

		methodText = new Text(this, SWT.BORDER);
		methodText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		methodText.setText("createBeanCollection");
		new Label(this, SWT.NONE);

		useFDcheck = new Button(this, SWT.CHECK);
		useFDcheck.setText("Use field description");
		useFDcheck.setSelection(true);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		useFDcheck.setLayoutData(gd);

		cpath = new ClasspathComponent(this);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cpath.getControl().setLayoutData(gd);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Set the MyDataAdapter to edit. The UI will be updated with the content of
	 * this adapter
	 * 
	 * @param dataAdapter
	 */
	public void setDataAdapter(BeanDataAdapterDescriptor beanDataAdapter) {
		this.beanDataAdapter = beanDataAdapter;
		BeanDataAdapter bda = (BeanDataAdapter) beanDataAdapter
				.getDataAdapter();
		factoryText.setClassType(Misc.nvl(bda.getFactoryClass(),
				"com.jaspersoft.studio.data.sample.SampleJRDataSourceFactory"));
		methodText
				.setText(Misc.nvl(bda.getMethodName(), "createBeanCollection"));
		useFDcheck.setSelection(bda.isUseFieldDescription());
		cpath.setClasspaths(bda.getClasspath());
	}

	public BeanDataAdapterDescriptor getDataAdapter() {

		if (beanDataAdapter == null)
			beanDataAdapter = new BeanDataAdapterDescriptor();

		BeanDataAdapter bda = (BeanDataAdapter) beanDataAdapter
				.getDataAdapter();
		bda.setFactoryClass(factoryText.getClassType());
		bda.setMethodName(methodText.getText().trim());

		bda.setUseFieldDescription(useFDcheck.getSelection());
		bda.setClasspath(cpath.getClasspaths());

		return beanDataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}
}
