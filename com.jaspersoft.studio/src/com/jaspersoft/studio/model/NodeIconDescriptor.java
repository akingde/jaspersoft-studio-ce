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
package com.jaspersoft.studio.model;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class NodeIconDescriptor implements IIconDescriptor {

	/** The ICO n_ title. */
	private String ICON_TITLE = "Unknown element";

	/** The ICO n_ description. */
	private String ICON_DESCRIPTION = "Unknown element";

	/** The ICO n_ tooltip. */
	private String ICON_TOOLTIP = "Unknown element";

	/** The ICO n_16. */
	private String ICON_16 = "icons/resources/genericelement-16.png";

	/** The ICO n_32. */
	private String ICON_32 = "icons/resources/genericelement-32.png";

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *          the name
	 */
	public NodeIconDescriptor(String name) {
		this.ICON_TITLE = getFromBundle(name + ".title", ICON_TITLE);
		this.ICON_DESCRIPTION = getFromBundle(name + ".description", ICON_DESCRIPTION);
		this.ICON_TOOLTIP = getFromBundle(name + ".tooltip", ICON_TOOLTIP);
		this.ICON_16 = getFromBundle(name + ".icon16", ICON_16);
		this.ICON_32 = getFromBundle(name + ".icon32", ICON_32);
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
			res = JaspersoftStudioPlugin.getResourceBundleIcons().getString(key);
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
		return JaspersoftStudioPlugin.getImageDescriptor(ICON_16);
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
		return JaspersoftStudioPlugin.getImageDescriptor(ICON_32);
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

}
