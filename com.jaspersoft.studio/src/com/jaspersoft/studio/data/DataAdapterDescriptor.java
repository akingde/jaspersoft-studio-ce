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
import net.sf.jasperreports.util.CastorUtil;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ui.DefaultDataAdapterEditor;
import com.jaspersoft.studio.model.util.IIconDescriptor;

/*
 * 
 * @author gtoffoli
 */
public abstract class DataAdapterDescriptor implements IIconDescriptor {
	/**
	 *
	 */
	public abstract DataAdapter getDataAdapter();

	/**
	 * FIXME consider remove
	 */
	public abstract void setDataAdapter(DataAdapter dataAdapter);

	/**
	 * FIXME consider remove
	 */
	public String getName() {
		return getDataAdapter() == null ? null : getDataAdapter().getName();
	}

	/**
	 * Coming from the IIconDescriptor interface. Just returns the name of this data adapter
	 */
	public String getTitle() {
		if (getDataAdapter() == null || getDataAdapter().getName() == null || getDataAdapter().getName().length() == 0) {
			return "<unnamed>";
		}
		return getDataAdapter().getName();
	}

	/**
	 * Coming from the IIconDescriptor interface. Return null;
	 */
	public String getDescription() {
		return null;
	}

	/**
	 * Coming from the IIconDescriptor interface. Return null;
	 */
	public String getToolTip() {
		return null;
	}

	/**
	 * Return an Image. By default this method returns a simple database icon
	 */
	public Image getIcon(int size) {
		return JaspersoftStudioPlugin.getInstance().getImage("icons/database.png");
	}

	/**
	 * It just write a portion of XML to persist the data adapter it is used by the DataAdapterManager and can be used to
	 * export this data adapter too. FIXME consider remove
	 */
	public final String toXml() {

		return CastorUtil.write(getDataAdapter());
	}

	@Override
	public String toString() {
		return getTitle();
	}

	/**
	 * This method is used to provide to the datasources window the GUI to configure this kind of component.
	 * 
	 */
	public DataAdapterEditor getEditor() {
		// return new BasicDataAdapterEditor();
		return new DefaultDataAdapterEditor();
	}

	/**
	 * Gets the icon16.
	 * 
	 * @return the icon16
	 */
	public ImageDescriptor getIcon16() {
		Image icon = getIcon(16);
		if (icon != null)
			return ImageDescriptor.createFromImage(icon);
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/database.png");
	}

	/**
	 * Gets the icon32.
	 * 
	 * @return the icon32
	 */
	public ImageDescriptor getIcon32() {
		Image icon = getIcon(32);
		if (icon != null)
			return ImageDescriptor.createFromImage(icon);
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/database.png");
	}
}
