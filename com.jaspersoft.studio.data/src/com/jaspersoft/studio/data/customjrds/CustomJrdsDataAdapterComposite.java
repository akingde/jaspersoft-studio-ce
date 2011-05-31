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
package com.jaspersoft.studio.data.customjrds;

import net.sf.jasperreports.data.ds.DataSourceDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class CustomJrdsDataAdapterComposite extends Composite {
	
	private CustomJrdsDataAdapterDescriptor customJrdsDataAdapterDesc = null;
	private Text textFactoryClass;
	private Text textMethodToCall;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CustomJrdsDataAdapterComposite(Composite parent, int style) {
		
		/*
		 * UI ELEMENTS
		 */
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Factory Class:");
		
		textFactoryClass = new Text(this, SWT.BORDER);
		textFactoryClass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("The called static method to retrieve the JRDataSource:");
		
		textMethodToCall = new Text(this, SWT.BORDER);
		textMethodToCall.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		/*
		 * UI ELEMENTS LISTENERS
		 */
		textFactoryClass.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				((DataSourceDataAdapter)customJrdsDataAdapterDesc.getDataAdapter()).setFactoryClass(textFactoryClass.getText().trim());
			}
		});
		
		textMethodToCall.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				((DataSourceDataAdapter)customJrdsDataAdapterDesc.getDataAdapter()).setMethodToCall(textMethodToCall.getText().trim());
			}
		});
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(CustomJrdsDataAdapterDescriptor dataAdapterDesc) 
	{
		customJrdsDataAdapterDesc = dataAdapterDesc;
		
		DataSourceDataAdapter dsDataAdapter = (DataSourceDataAdapter)customJrdsDataAdapterDesc.getDataAdapter();
		
		textFactoryClass.setText(Misc.nvl(dsDataAdapter.getFactoryClass(), "com.jaspersoft.studio.data.sample.SampleJRDataSourceFactory"));
		textMethodToCall.setText(Misc.nvl(dsDataAdapter.getMethodToCall(), "createDatasource"));
	}

	public DataAdapterDescriptor getDataAdapter() 
	{
		if (customJrdsDataAdapterDesc == null) {
			customJrdsDataAdapterDesc = new CustomJrdsDataAdapterDescriptor();
		}
		
		DataSourceDataAdapter dsDataAdapter = (DataSourceDataAdapter)customJrdsDataAdapterDesc.getDataAdapter();
		
		dsDataAdapter.setFactoryClass(textFactoryClass.getText().trim());
		dsDataAdapter.setMethodToCall(textMethodToCall.getText().trim());
		
		return customJrdsDataAdapterDesc;
	}

	public String getHelpContextId() {
		// TODO Auto-generated method stub
		return null;
	}
}
