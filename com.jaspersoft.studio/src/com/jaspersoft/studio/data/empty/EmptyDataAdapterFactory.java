/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.empty;

import org.eclipse.swt.graphics.Image;

import net.sf.jasperreports.data.empty.EmptyDataAdapterImpl;

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
		// TODO Auto-generated method stub
		return "Use a set of empty rows";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16)
		{
			return  JaspersoftStudioPlugin.getImage("icons/battery-empty.png");
		}
		return null;
	}
	
	

	
}
