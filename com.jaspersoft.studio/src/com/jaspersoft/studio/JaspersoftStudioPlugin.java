/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.utils.ModelUtils;

// TODO: Auto-generated Javadoc
/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperCompileManager.java 1229 2006-04-19 13:27:35 +0300 (Wed,
 *          19 Apr 2006) teodord $
 */
public class JaspersoftStudioPlugin extends AbstractUIPlugin {

	// The shared instance.
	/** The plugin. */
	private static JaspersoftStudioPlugin plugin;

	/**
	 * Gets the single instance of JaspersoftStudioPlugin.
	 * 
	 * @return the plugin instance singleton.
	 */
	public static JaspersoftStudioPlugin getInstance() {
		return plugin; // plugin cannot be null, Eclipse takes care to instance it
		// at startup.
	}

	/**
	 * The constructor.
	 */
	public JaspersoftStudioPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation.
	 * 
	 * @param context
	 *          the context
	 * @throws Exception
	 *           the exception
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped.
	 * 
	 * @param context
	 *          the context
	 * @throws Exception
	 *           the exception
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the default
	 */
	public static JaspersoftStudioPlugin getDefault() {
		return plugin;
	}

	/** The image cache. */
	private static Map<ImageDescriptor, Image> imageCache = new HashMap<ImageDescriptor, Image>(11);

	/**
	 * Gets the image.
	 * 
	 * @param descriptor
	 *          the descriptor
	 * @return the image
	 */
	public static Image getImage(ImageDescriptor descriptor) {
		if (descriptor == null)
			descriptor = ModelUtils.getImageDescriptor("icons/report.png");
		Image image = imageCache.get(descriptor);
		if (image == null) {
			image = descriptor.createImage();
			if (image != null)
				imageCache.put(descriptor, image);
			else
				image = imageCache.get(ModelUtils.getImageDescriptor("icons/report.png"));
		}
		return image;
	}

	/**
	 * Gets the image.
	 * 
	 * @param path
	 *          the path
	 * @return the image
	 */
	public static Image getImage(String path) {
		ImageDescriptor descriptor = null;
		if (path != null)
			descriptor = JaspersoftStudioPlugin.getImageDescriptor(path);
		return getImage(descriptor);
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *          the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(UNIQUE_ID, path);
	}

	/** The resource bundle icons. */
	private static ResourceBundle resourceBundleIcons;

	/**
	 * Gets the resource bundle icons.
	 * 
	 * @return the resource bundle icons
	 */
	public static ResourceBundle getResourceBundleIcons() {
		if (resourceBundleIcons == null) {
			String resourceName = getDefault().getBundle().getEntry("/").toString() + "resources/icons.properties";
			InputStream inputStream = null;
			try {
				inputStream = new URL(resourceName).openStream();
				return new PropertyResourceBundle(new URL(resourceName).openStream());
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
		return resourceBundleIcons;
	}

	/** The Constant UNIQUE_ID. */
	private static final String UNIQUE_ID = "com.jaspersoft.studio";

	/**
	 * Gets the unique identifier.
	 * 
	 * @return the unique identifier
	 */
	public static String getUniqueIdentifier() {
		return UNIQUE_ID;
	}

}
