/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

public class MDataAdapters extends ANode{
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if("DATAADAPTERS".equals(evt.getPropertyName())) //$NON-NLS-1$
		{
			updateChildren();
		}
		
		super.propertyChange(evt);
	}

	private void updateChildren() {
		this.removeChildren();
		List<DataAdapterDescriptor> dataAdapters = DataAdapterManager.getDataAdapters();
		for (DataAdapterDescriptor dataAdapter : dataAdapters) {
			MDataAdapter mDataAdapter = new MDataAdapter(dataAdapter);
			this.addChild(mDataAdapter);
		}
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("dataAdapters"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MDataAdapters(ANode parent) {
		super(parent, -1);
		DataAdapterManager.getPropertyChangeSupport().addPropertyChangeListener(this);
		updateChildren();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
