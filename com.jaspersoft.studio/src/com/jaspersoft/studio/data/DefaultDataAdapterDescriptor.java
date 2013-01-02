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
package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.DataAdapter;

public class DefaultDataAdapterDescriptor extends DataAdapterDescriptor {
	private DataAdapter dataAdapter;

	@Override
	public DataAdapter getDataAdapter() {
		return dataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.dataAdapter = dataAdapter;
	}

}
