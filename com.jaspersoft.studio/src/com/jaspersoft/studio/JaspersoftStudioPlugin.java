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
package com.jaspersoft.studio;

import java.io.IOException;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.editor.gef.decorator.DecoratorManager;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.property.PostSetValueManager;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.DriversManager;

/*
 * The main plugin class to be used in the desktop.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * 
 * @version $Id: JasperCompileManager.java 1229 2006-04-19 13:27:35 +0300 (Wed, 19 Apr 2006) teodord $
 */
public class JaspersoftStudioPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "com.jaspersoft.studio"; //$NON-NLS-1$
	public static final String COMPONENTS_ID = "com.jaspersoft.studio.components"; //$NON-NLS-1$

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

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		getExtensionManager();
	}

	/**
	 * The constructor.
	 */
	public JaspersoftStudioPlugin() {
		plugin = this;
	}

	/**
	 * This method is called when the plug-in is stopped.
	 * 
	 * @param context
	 *          the context
	 * @throws Exception
	 *           the exception
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Gets the image.
	 * 
	 * @param descriptor
	 *          the descriptor
	 * @return the image
	 */
	public static Image getImage(ImageDescriptor descriptor) {
		if (descriptor == null)
			descriptor = ModelUtils.getImageDescriptor("icons/report.png"); //$NON-NLS-1$
		ImageRegistry imageRegistry = getInstance().getImageRegistry();
		Image image = imageRegistry.get(descriptor.toString());
		if (image == null) {
			image = descriptor.createImage();
			if (image != null)
				imageRegistry.put(descriptor.toString(), image);
			else
				image = imageRegistry.get("icons/report.png"); //$NON-NLS-1$
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
	 * Returns an image descriptor for the image file at the given plug-in relative path.
	 * 
	 * @param path
	 *          the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(UNIQUE_ID, path);
	}

	/** The Constant UNIQUE_ID. */
	private static final String UNIQUE_ID = "com.jaspersoft.studio"; //$NON-NLS-1$

	/**
	 * Gets the unique identifier.
	 * 
	 * @return the unique identifier
	 */
	public static String getUniqueIdentifier() {
		return UNIQUE_ID;
	}

	private static ExtensionManager extensionManager;

	public static ExtensionManager getExtensionManager() {
		if (extensionManager == null) {
			extensionManager = new ExtensionManager();
			extensionManager.init();
		}
		return extensionManager;
	}

	private static DecoratorManager decoratorManager;

	public static DecoratorManager getDecoratorManager() {
		if (decoratorManager == null) {
			decoratorManager = new DecoratorManager();
			decoratorManager.init();
		}
		return decoratorManager;
	}
	private static DriversManager driversManager;

	public static DriversManager getDriversManager() {
		if (driversManager == null) {
			driversManager = new DriversManager();
			driversManager.init();
		}
		return driversManager;
	}
	private static PostSetValueManager postSetValueManager;

	public static PostSetValueManager getPostSetValueManager() {
		if (postSetValueManager == null) {
			postSetValueManager = new PostSetValueManager();
			postSetValueManager.init();
		}
		return postSetValueManager;
	}

	/**
	 * Get the full path name for a resource located inside the plug-in.
	 * 
	 * @param path
	 *          the path of the internal resource
	 * @return the string corresponding to the full path
	 * @throws IOException
	 *           if a problem occurs during conversion
	 */
	public String getFileLocation(String path) throws IOException {
		Assert.isNotNull(path);
		return FileLocator.toFileURL(getBundle().getEntry(path)).getPath();
	}
	
	public void log(int severity, String message, Throwable t) {
		IStatus status = new Status(severity, PLUGIN_ID, 0, message, t);
		getLog().log(status);
	}

	public void logError(String message, Throwable t) {
		log(IStatus.ERROR, message, t);
	}


}
