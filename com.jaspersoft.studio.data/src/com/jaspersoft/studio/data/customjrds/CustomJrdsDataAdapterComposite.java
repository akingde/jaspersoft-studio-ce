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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.utils.Misc;

public class CustomJrdsDataAdapterComposite extends Composite {
	
	private CustomJrdsDataAdapter customJrdsDataAdapter = null;
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
			
			@Override
			public void modifyText(ModifyEvent e) {
				customJrdsDataAdapter.setFactoryClass(textFactoryClass.getText().trim());
			}
		});
		
		textMethodToCall.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				customJrdsDataAdapter.setMethodToCall(textMethodToCall.getText().trim());
			}
		});
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(CustomJrdsDataAdapter dataAdapter) {
		
		customJrdsDataAdapter = dataAdapter;
		textFactoryClass.setText(Misc.nvl(customJrdsDataAdapter.getFactoryClass(), ""));
		textMethodToCall.setText(Misc.nvl(customJrdsDataAdapter.getMethodToCall(), ""));
	}

	public DataAdapter getDataAdapter() {
		
		if (customJrdsDataAdapter == null) {
			customJrdsDataAdapter = new CustomJrdsDataAdapter();
		}
		
		customJrdsDataAdapter.setFactoryClass(textFactoryClass.getText().trim());
		customJrdsDataAdapter.setMethodToCall(textMethodToCall.getText().trim());
		
		return customJrdsDataAdapter;
	}

	public String getHelpContextId() {
		// TODO Auto-generated method stub
		return null;
	}
}
