/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.bean;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.bean.BeanDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.widgets.ClassType;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;

public class BeanDataAdapterComposite extends ADataAdapterComposite {
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
		lblFactory.setText(Messages.BeanDataAdapterComposite_0);

		factoryText = new ClassType(this, Messages.BeanDataAdapterComposite_1);
		factoryText.setClassType(Messages.BeanDataAdapterComposite_2);

		Label lblMethodName = new Label(this, SWT.NONE);
		lblMethodName.setText(Messages.BeanDataAdapterComposite_3);
		new Label(this, SWT.NONE);

		methodText = new Text(this, SWT.BORDER);
		methodText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		methodText.setText(Messages.BeanDataAdapterComposite_4);
		new Label(this, SWT.NONE);

		useFDcheck = new Button(this, SWT.CHECK);
		useFDcheck.setText(Messages.BeanDataAdapterComposite_5);
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
	protected void bindWidgets(DataAdapter dataAdapter) {
		BeanDataAdapter bda = (BeanDataAdapter) dataAdapter;

		bindingContext.bindValue(SWTObservables.observeText(
				factoryText.getControl(), SWT.Modify), PojoObservables
				.observeValue(dataAdapter, "factoryClass")); //$NON-NLS-1$

		bindingContext.bindValue(
				SWTObservables.observeText(methodText, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "methodName")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(useFDcheck),
				PojoObservables
						.observeValue(dataAdapter, "useFieldDescription")); //$NON-NLS-1$

		cpath.setClasspaths(bda.getClasspath());
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new BeanDataAdapterDescriptor();

		BeanDataAdapter bda = (BeanDataAdapter) dataAdapterDesc
				.getDataAdapter();
		bda.setFactoryClass(factoryText.getClassType());
		bda.setMethodName(methodText.getText().trim());

		bda.setUseFieldDescription(useFDcheck.getSelection());
		bda.setClasspath(cpath.getClasspaths());

		return dataAdapterDesc;
	}

}
