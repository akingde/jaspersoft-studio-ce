/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

/*
 * The Class NodeIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class NodeIconDescriptor implements IIconDescriptor {

	/** The ICO n_ title. */
	private String ICON_TITLE = "<not-defined>"; //$NON-NLS-1$

	/** The ICO n_ description. */
	private String ICON_DESCRIPTION = "<not-defined>"; //$NON-NLS-1$

	/** The ICO n_ tooltip. */
	private String ICON_TOOLTIP = "<not-defined>"; //$NON-NLS-1$

	/** The ICO n_16. */
	private String ICON_16 = "icons/resources/genericelement-16.png"; //$NON-NLS-1$

	/** The ICO n_32. */
	private String ICON_32 = "icons/resources/genericelement-32.png"; //$NON-NLS-1$
	private AbstractUIPlugin plugin;

	/**
	 * Instantiates a new node icon descriptor.
	 * 
	 * @param name
	 *            the name
	 */
	public NodeIconDescriptor(String name) {
		this(name, JaspersoftStudioPlugin.getInstance());
	}

	public NodeIconDescriptor(String name, AbstractUIPlugin plugin) {
		this.plugin = plugin;
		if (name != null && !name.isEmpty()) {
			this.ICON_TITLE = getFromBundle(name + ".title", ICON_TITLE); //$NON-NLS-1$
			this.ICON_DESCRIPTION = getFromBundle(name + ".description", ICON_DESCRIPTION); //$NON-NLS-1$
			this.ICON_TOOLTIP = getFromBundle(name + ".description", ICON_TOOLTIP); //$NON-NLS-1$
			this.ICON_16 = getFromBundle(name + ".icon16", ICON_16); //$NON-NLS-1$
			this.ICON_32 = getFromBundle(name + ".icon32", ICON_32); //$NON-NLS-1$
		}
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
			ResourceBundle inputStream = null;
			inputStream = ResourceBundle.getBundle("resources/icons", Locale.getDefault(), //$NON-NLS-1$
					plugin.getClass().getClassLoader());
			setResourceBundleIcons(inputStream);
		}
		return getResourceBundleIcons();
	}

	/**
	 * Gets the from bundle.
	 * 
	 * @param key
	 *            the key
	 * @param def
	 *            the def
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
	 *            the title
	 * @param description
	 *            the description
	 * @param toolTip
	 *            the tool tip
	 * @param icon16
	 *            the icon16
	 * @param icon32
	 *            the icon32
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
		if (plugin != null)
			return AbstractUIPlugin.imageDescriptorFromPlugin(plugin.getBundle().getSymbolicName(), ICON_16);
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor(ICON_16);
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
		if (plugin != null)
			return AbstractUIPlugin.imageDescriptorFromPlugin(plugin.getBundle().getSymbolicName(), ICON_32);
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor(ICON_32);
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
