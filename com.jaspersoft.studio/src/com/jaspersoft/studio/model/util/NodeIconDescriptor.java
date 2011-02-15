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
package com.jaspersoft.studio.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import com.jaspersoft.studio.JaspersoftStudioPlugin;

/**
 * The Class NodeIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class NodeIconDescriptor implements IIconDescriptor {

	/** The ICO n_ title. */
	private String ICON_TITLE = "Unknown element"; //$NON-NLS-1$

	/** The ICO n_ description. */
	private String ICON_DESCRIPTION = "Unknown element"; //$NON-NLS-1$

	/** The ICO n_ tooltip. */
	private String ICON_TOOLTIP = "Unknown element"; //$NON-NLS-1$

	/** The ICO n_16. */
	private String ICON_16 = "icons/resources/genericelement-16.png"; //$NON-NLS-1$

	/** The ICO n_32. */
	private String ICON_32 = "icons/resources/genericelement-32.png"; //$NON-NLS-1$
	private AbstractUIPlugin plugin;

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *          the name
	 */
	public NodeIconDescriptor(String name) {
		this(name, JaspersoftStudioPlugin.getInstance());
	}

	public NodeIconDescriptor(String name, AbstractUIPlugin plugin) {
		this.plugin = plugin;

		this.ICON_TITLE = getFromBundle(name + ".title", ICON_TITLE); //$NON-NLS-1$
		this.ICON_DESCRIPTION = getFromBundle(name + ".description", ICON_DESCRIPTION); //$NON-NLS-1$
		this.ICON_TOOLTIP = getFromBundle(name + ".description", ICON_TOOLTIP); //$NON-NLS-1$
		this.ICON_16 = getFromBundle(name + ".icon16", ICON_16); //$NON-NLS-1$
		this.ICON_32 = getFromBundle(name + ".icon32", ICON_32); //$NON-NLS-1$
	}

	/** The resource bundle icons. */
	private static ResourceBundle resourceBundleIcons;

	public ResourceBundle getResourceBundleIcons() {
		return resourceBundleIcons;
	}

	public void setResourceBundleIcons(ResourceBundle resourceBundleIcons) {
		NodeIconDescriptor.resourceBundleIcons = resourceBundleIcons;
	}

	/**
	 * Gets the resource bundle icons.
	 * 
	 * @return the resource bundle icons
	 */
	public ResourceBundle getResourceBundle(AbstractUIPlugin plugin) {
		if (getResourceBundleIcons() == null) {
			InputStream inputStream = null;
			try {
				inputStream = plugin.getBundle().getResource("resources/icons" + getLocale() + ".properties").openStream(); //$NON-NLS-1$ //$NON-NLS-2$
				setResourceBundleIcons(new PropertyResourceBundle(inputStream));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (inputStream != null)
						inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return getResourceBundleIcons();
	}

	/**
	 * Gets the from bundle.
	 * 
	 * @param key
	 *          the key
	 * @param def
	 *          the def
	 * @return the from bundle
	 */
	private String getFromBundle(String key, String def) {
		String res = def;
		try {
			res = getResourceBundle(plugin).getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param title
	 *          the title
	 * @param description
	 *          the description
	 * @param toolTip
	 *          the tool tip
	 * @param icon16
	 *          the icon16
	 * @param icon32
	 *          the icon32
	 */
	public NodeIconDescriptor(String title, String description, String toolTip, String icon16, String icon32) {
		this.ICON_TITLE = title;
		this.ICON_DESCRIPTION = description;
		this.ICON_TOOLTIP = toolTip;
		this.ICON_16 = icon16;
		this.ICON_32 = icon32;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getDescription()
	 */
	public String getDescription() {
		return this.ICON_DESCRIPTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getIcon16()
	 */
	public ImageDescriptor getIcon16() {
		// return JaspersoftStudioPlugin.getImageDescriptor(ICON_16);
		return AbstractUIPlugin.imageDescriptorFromPlugin(plugin.getBundle().getSymbolicName(), ICON_16);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getIcon16Path()
	 */
	public String getIcon16Path() {
		return this.ICON_16;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getIcon32()
	 */
	public ImageDescriptor getIcon32() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(plugin.getBundle().getSymbolicName(), ICON_32);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getIcon32Path()
	 */
	public String getIcon32Path() {
		return this.ICON_32;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getTitle()
	 */
	public String getTitle() {
		return this.ICON_TITLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IIconDescriptor#getToolTip()
	 */
	public String getToolTip() {
		return this.ICON_TOOLTIP;
	}

  // This method returns the correct locale suffix. It will test from the most specific to
	// the most general: fr_FR > fr > no suffix.
	private String getLocale() {
		String dLocale = Locale.getDefault().toString();
		int charIndex = dLocale.indexOf("_");
		String[] suffixes = {"", "_" + dLocale, "_" + dLocale.substring(0, charIndex)};
		int returnIndex = 0;
		URL url1 = plugin.getBundle().getResource("resources/icons" + suffixes[1] + ".properties");
		URL url2 = plugin.getBundle().getResource("resources/icons" + suffixes[2] + ".properties");
		try {
			url1.getFile();
			returnIndex = 1;
		} catch (NullPointerException e1) {
			try {
				url2.getFile();
				returnIndex = 2;
			} catch (NullPointerException e2) {
				// ignore
			}
		}
		return suffixes[returnIndex];
	}
}
