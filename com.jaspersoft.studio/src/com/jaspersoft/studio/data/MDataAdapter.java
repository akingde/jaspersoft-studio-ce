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
package com.jaspersoft.studio.data;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;

/*
 * This class wraps a DataAdapter to display a dataAdapter inside the repository view. The dataAdapters node in the view
 * takes care to update its content
 * 
 * @author czhu, gtoffoli
 */
public class MDataAdapter extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The icon descriptor. */
	private DataAdapterDescriptor dataAdapter = null;

	public DataAdapterDescriptor getDataAdapter() {
		return dataAdapter;
	}

	@Override
	public DataAdapterDescriptor getValue() {
		return (DataAdapterDescriptor) super.getValue();
	}

	public MDataAdapter(MDataAdapters parent, DataAdapterDescriptor dataAdapter) {
		super(parent, dataAdapter, -1);
		this.dataAdapter = dataAdapter;
	}

	public String getDisplayText() {
		return dataAdapter.getTitle();
	}

	public ImageDescriptor getImagePath() {
		return dataAdapter.getIcon16();
	}
}
