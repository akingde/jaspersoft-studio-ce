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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2011 Jaspersoft Corporation. All
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
		return JaspersoftStudioPlugin.getImage("icons/database.png");
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
		if (icon != null) {
			return ImageDescriptor.createFromImage(getIcon(16));
		} else {
			return ImageDescriptor.createFromImage(JaspersoftStudioPlugin.getImage("icons/database.png"));
		}
	}

	/**
	 * Gets the icon32.
	 * 
	 * @return the icon32
	 */
	public ImageDescriptor getIcon32() {
		Image icon = getIcon(32);
		if (icon != null) {
			return ImageDescriptor.createFromImage(getIcon(16));
		} else {
			return ImageDescriptor.createFromImage(JaspersoftStudioPlugin.getImage("icons/database.png"));
		}
	}
}
