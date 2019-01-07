/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;
import net.sf.jasperreports.engine.util.CompositeClassloader;

public class DriversManager {
	public void init() {
		if (nodeFactory == null) {
			nodeFactory = new ArrayList<>();
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "drivers"); //$NON-NLS-1$
			for (IConfigurationElement e : config) {
				try {
					Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
					if (o instanceof IDriverProvider)
						nodeFactory.add((IDriverProvider) o);
				} catch (CoreException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	private static List<IDriverProvider> nodeFactory;

	private URL[] getDriversURL() {
		List<URL> urls = new ArrayList<>();
		for (IDriverProvider f : nodeFactory)
			urls.addAll(Arrays.asList(f.getDriversURL()));

		return urls.toArray(new URL[urls.size()]);
	}

	private ClassLoader parent;
	private URLClassLoader classloader;

	public ClassLoader getClassLoader(ClassLoader cl) {
		if (parent != cl) {
			JavaProjectClassLoader.clean(classloader);
			classloader = null;
		}
		if (classloader == null) {
			URL[] urls = getDriversURL();
			if (urls != null && urls.length > 0)
				classloader = new URLClassLoader(urls);
		}
		if (classloader == null)
			return cl;
		return new CompositeClassloader(cl, classloader);
	}
}
