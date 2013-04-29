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
package com.jaspersoft.studio.data.xlsx;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class XLSXDataAdapterEditor implements DataAdapterEditor {

	XLSXDataAdapterComposite composite = null;
	
	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (dataAdapter instanceof XLSXDataAdapterDescriptor) {
			this.composite.setDataAdapter((XLSXDataAdapterDescriptor)dataAdapter);
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage) {
		if (composite == null) {
			composite = new XLSXDataAdapterComposite(parent, style);
		}
		return composite;
	}

	public String getHelpContextId() {
		return this.composite.getHelpContextId();
	}
}
