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
package com.jaspersoft.studio.data.empty;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.empty.EmptyDataAdapterImpl;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class EmptyDataAdapterFactory implements DataAdapterFactory {

	public EmptyDataAdapterDescriptor createDataAdapter() {
		return new EmptyDataAdapterDescriptor();
	}

	public String getDataAdapterClassName() {
		return EmptyDataAdapterImpl.class.getName();
	}

	public String getLabel() {
		return "Empty rows";
	}
	
	public String getDescription() { 
		return "Use a set of empty rows";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	public Image getIcon(int size) { 
		if (size == 16)
		{
			return  JaspersoftStudioPlugin.getInstance().getImage("icons/battery-empty.png");
		}
		return null;
	}

	public DataAdapterService createDataAdapterService(DataAdapter dataAdapter) {
		return null;
	}
	
	

	
}
