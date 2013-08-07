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
package com.jaspersoft.studio.data.xmla;

import net.sf.jasperreports.data.xmla.XmlaDataAdapter;
import net.sf.jasperreports.data.xmla.XmlaDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class XmlaDataAdapterDescriptor extends DataAdapterDescriptor {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public XmlaDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new XmlaDataAdapterImpl();
		return (XmlaDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new XmlaDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/database.png");
		}
		return null;
	}
}
