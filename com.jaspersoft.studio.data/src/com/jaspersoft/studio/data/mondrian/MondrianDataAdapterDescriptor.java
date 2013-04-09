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
package com.jaspersoft.studio.data.mondrian;

import net.sf.jasperreports.data.mondrian.MondrianDataAdapterImpl;

import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * @author gtoffoli
 *
 */
public class MondrianDataAdapterDescriptor extends JDBCDataAdapterDescriptor {

	public MondrianDataAdapterDescriptor() {
		setDataAdapter(new MondrianDataAdapterImpl());
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new MondrianDataAdapterEditor();
	}

	@Override
	protected void getFieldProvider() {
		if (fprovider == null)
			fprovider = new MondrianFieldsProvider();
	}
	
	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return false;
	}

}
