/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.widgets.map.messages.Messages;

/**
 * The activator class controls the plug-in life cycle
 */
public class MapActivator extends AbstractUIPlugin {

	public static final String FIND_BTN_IMG = "FIND_BTN"; //$NON-NLS-1$

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.widgets.map"; //$NON-NLS-1$

	// The shared instance
	private static MapActivator plugin;
	
	/**
	 * The constructor
	 */
	public MapActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		// trick used to unpack the dir content to filesystem
		getFileLocation("mapfiles/gmaps_library/"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static MapActivator getDefault() {
		return plugin;
	}
	
	/**
	 * Logs an error message and an optional exception for the current bundle.
	 * 
	 * @param message
	 *            a human-readable message
	 * @param exception
	 *            a low-level exception, or <code>null</code> if not applicable
	 */
	public static void logError(String message, Throwable exception) {
		Platform.getLog(getDefault().getBundle()).log(
				new Status(IStatus.ERROR, PLUGIN_ID, message, exception));
	}

	/**
	 * Gets the full path name for a resource located inside the current bundle.
	 * 
	 * @param path
	 *            the path of the internal resource
	 * @return the string corresponding to the full path
	 * @throws IOException
	 *             if a problem occurs during conversion
	 */
	public static String getFileLocation(String path) {
		Assert.isNotNull(path);
		Bundle bundle = getDefault().getBundle();
		String fullPath = null;
		try {
			if (bundle != null) {
				fullPath = new Path(FileLocator.toFileURL(bundle.getEntry(path)).getPath()).toOSString();
			}
		}
		catch (IOException ex) {
			logError(Messages.MapActivator_FileError, ex);
		}
		return fullPath;
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		IPath path = new Path("icons/find-20.png"); //$NON-NLS-1$
		URL url = FileLocator.find(bundle, path, null);
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		reg.put(FIND_BTN_IMG, desc);
	}
	
}
