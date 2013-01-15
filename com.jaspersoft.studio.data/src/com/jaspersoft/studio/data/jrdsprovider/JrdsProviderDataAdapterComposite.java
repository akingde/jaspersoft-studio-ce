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
package com.jaspersoft.studio.data.jrdsprovider;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.widgets.ClassType;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;

public class JrdsProviderDataAdapterComposite extends ADataAdapterComposite {

	private ClassType factoryText;
	private ClasspathComponent cpath;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JrdsProviderDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText(Messages.JrdsProviderDataAdapterComposite_0);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		lblNewLabel.setLayoutData(gd);

		factoryText = new ClassType(this,
				Messages.JrdsProviderDataAdapterComposite_1);
		factoryText
				.setClassType("com.jaspersoft.ireport.examples.SampleJRDataSourceFactory"); //$NON-NLS-1$

		cpath = new ClasspathComponent(this);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		cpath.getControl().setLayoutData(gd);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		DataSourceProviderDataAdapter da = (DataSourceProviderDataAdapter) dataAdapter;

		bindingContext.bindValue(SWTObservables.observeText(
				factoryText.getControl(), SWT.Modify), PojoObservables
				.observeValue(dataAdapter, "providerClass")); //$NON-NLS-1$

		cpath.setClasspaths(da.getClasspath());
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new JrdsProviderDataAdapterDescriptor();

		DataSourceProviderDataAdapter da = (DataSourceProviderDataAdapter) dataAdapterDesc
				.getDataAdapter();
		da.setProviderClass(factoryText.getClassType().trim());
		da.setClasspath(cpath.getClasspaths());

		return dataAdapterDesc;
	}

}
