/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.components.customvisualization.model.MCustomVisualization;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptorResolver;
import com.jaspersoft.studio.components.customvisualization.ui.preferences.CVCDescriptorsPreferencePage;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.IDisposeListener;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.util.ResourceBundleMessageProvider;

public class UIManager {
	private static Map<String, CVCWidgetsDescriptor> cachePlugin;
	private static Map<JasperReportsConfiguration, Map<String, CVCWidgetsDescriptor>> cache = new HashMap<JasperReportsConfiguration, Map<String, CVCWidgetsDescriptor>>();
	private static Map<CVCWidgetsDescriptor, Image> imageCache = new HashMap<CVCWidgetsDescriptor, Image>();
	private static Map<CVCWidgetsDescriptor, String> parentsPath = new HashMap<CVCWidgetsDescriptor, String>();
	private static IResourceChangeListener listener = new ResourceChangeReporter();
	private static final CVCWidgetsDescriptorResolver RESOLVER = new CVCWidgetsDescriptorResolver();
	
	
	static {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener,
				IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.POST_CHANGE);
	}

	public static class ResourceChangeReporter implements IResourceChangeListener {
		private boolean checkInPaths(IResourceDelta rd) {
			if (rd.getFullPath() != null && rd.getResource() != null && rd.getResource().getRawLocationURI() != null) {
				String rpath = rd.getResource().getRawLocationURI().toASCIIString();
				for (String path : parentsPath.values()) {
					if (path.startsWith("bundle"))
						continue;
					if (!rpath.startsWith(path))
						return false;
				}
				imageCache.clear();
				parentsPath.clear();
				rbMap.clear();
				cache.clear();
				if (cachePlugin != null)
					cachePlugin.clear();
				initCachePlugin();
			}
			return true;
		}

		private void checkResource(IResourceDelta[] children) {
			for (IResourceDelta rd : children) {
				if (!Misc.isNullOrEmpty(rd.getAffectedChildren()))
					checkResource(rd.getAffectedChildren());
				else if (checkInPaths(rd))
					return;
			}
		}

		public void resourceChanged(IResourceChangeEvent event) {
			final IResourceDelta delta = event.getDelta();
			if (delta != null)
				checkResource(delta.getAffectedChildren());
		}
	}

	public static CVCWidgetsDescriptor getComponentDescriptor(MCustomVisualization model) {
		List<ItemProperty> props = model.getComponent().getItemProperties();
		if (props != null) {
			ItemProperty p = ItemPropertyUtil.getProperty(props, "module");
			if (p != null) {
				// let's ignore interpretter
				String module = ItemPropertyUtil.getItemPropertyString((StandardItemProperty) p, null);
				if (!Misc.isNullOrEmpty(module))
					return getDescriptor(model.getJasperConfiguration(), module);
			}
		}
		return null;
	}

	public static void copyFile(CVCWidgetsDescriptor cd, JasperReportsConfiguration jConf, String path) {
		if (Misc.isNullOrEmpty(path))
			return;
		IFile f = (IFile) jConf.get(FileUtils.KEY_FILE);
		if (f == null)
			return;
		IContainer destFolder = f.getParent();
		InputStream is = null;
		try {
			File dest = new File(destFolder.getLocation().toOSString(), path);

			// if (isInPlugin(cd)) {
			// String uri = parentsPath.get(cd);
			// if (uri != null)
			// is = new URL(uri + path).openStream();

			// String uri = parentsPath.get(cd);
			// if (uri != null)
			// uri += path;
			// URL url =
			// CustomVisualizationActivator.getDefault().getBundle()
			// .getEntry(uri);

			// if (url != null)
			// is = url.openStream();
			// } else {
			getDescriptor(jConf, cd.getModule());
			String uri = parentsPath.get(cd);
			if (uri != null) {
				URL url = new URL(uri + path);
				if (url.sameFile(dest.toURI().toURL()))
					return;
				is = url.openStream();
			}
			// }
			if (is != null) {
				org.apache.commons.io.FileUtils.copyInputStreamToFile(is, dest);
				destFolder.refreshLocal(1, new NullProgressMonitor());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(is);
		}
	}

	public static Image getThumbnail(CVCWidgetsDescriptor cd) {
		Image img = imageCache.get(cd);
		if (img == null && !Misc.isNullOrEmpty(cd.getThumbnail())) {
			try {
				String uri = parentsPath.get(cd);
				if (uri != null) {
					InputStream is = new URL(uri + cd.getThumbnail()).openStream();
					if (is != null) {
						try {
							img = new Image(null, is);
							imageCache.put(cd, img);
						} finally {
							FileUtils.closeStream(is);
						}
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	private static Map<CVCWidgetsDescriptor, ResourceBundleMessageProvider> rbMap = new HashMap<CVCWidgetsDescriptor, ResourceBundleMessageProvider>();

	public static String getProperty(CVCWidgetsDescriptor cd, String key) {
		String uri = parentsPath.get(cd);
		if (uri != null) {
			try {
				ResourceBundleMessageProvider rbmp = rbMap.get(cd);
				if (rbmp == null && !rbMap.containsKey(cd)) {
					ClassLoader cl = new URLClassLoader(new URL[] { new URI(uri).toURL() });
					rbmp = new ResourceBundleMessageProvider(cd.getModule(), cl);
					rbMap.put(cd, rbmp);
				}
				try {
					key = rbmp.getMessage(key, Locale.getDefault());
				} catch (MissingResourceException e) {
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return key;

	}

	public static List<CVCWidgetsDescriptor> getModules(JasperReportsConfiguration jConfig) {
		List<CVCWidgetsDescriptor> res = new ArrayList<CVCWidgetsDescriptor>();
		if (cachePlugin == null)
			initCachePlugin();
		res.addAll(cachePlugin.values());
		Map<String, CVCWidgetsDescriptor> modules = initCacheJConfig(jConfig);
		res.addAll(modules.values());
		return res;
	}

	public static CVCWidgetsDescriptor getDescriptor(final JasperReportsConfiguration jConfig, String module) {
		if (cachePlugin == null)
			initCachePlugin();
		CVCWidgetsDescriptor d = cachePlugin.get(module);
		if (d != null)
			return d;
		Map<String, CVCWidgetsDescriptor> modules = cache.get(jConfig);
		if (modules == null)
			modules = initCacheJConfig(jConfig);
		// let's look if we cached something
		return modules.get(module);
	}

	protected static CustomVisualizationActivator initCachePlugin() {
		if (cachePlugin == null)
			cachePlugin = new HashMap<String, CVCWidgetsDescriptor>();
		CustomVisualizationActivator activator = CustomVisualizationActivator.getDefault();
		Enumeration<?> en = activator.getBundle().findEntries("components", "*.json", true); //$NON-NLS-1$
		while (en != null && en.hasMoreElements()) {
			URL url = (URL) en.nextElement();
			try {
				CVCWidgetsDescriptor cd = RESOLVER.resolveURL(url);
				if (cd != null) {
					cachePlugin.put(cd.getModule(), cd);
					String purl = url.toURI().toASCIIString();
					int indx = purl.lastIndexOf("/");
					if (indx != -1) {
						purl = purl.substring(0, indx + 1);
						parentsPath.put(cd, purl);
					}
				}
			} catch (Exception ex) {
				// Log error but continue...
				activator.log(new Status(IStatus.ERROR, CustomVisualizationActivator.PLUGIN_ID,
						MessageFormat.format(Messages.UIManager_2, new Object[] { url }), ex));
			}
		}
		return activator;
	}

	protected static Map<String, CVCWidgetsDescriptor> initCacheJConfig(final JasperReportsConfiguration jConf) {
		Map<String, CVCWidgetsDescriptor> modules = cache.get(jConf);
		if (modules == null) {
			modules = new HashMap<String, CVCWidgetsDescriptor>();
			cache.put(jConf, modules);
			jConf.getPrefStore().addPropertyChangeListener(new IPropertyChangeListener() {

				@Override
				public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
					if (event.getProperty().equals(CVCDescriptorsPreferencePage.RESOURCE_PATHS)) {
						for (CVCWidgetsDescriptor cd : cache.get(jConf).values()) {
							imageCache.remove(cd);
							parentsPath.remove(cd);
						}
						cache.remove(jConf);
						initCacheJConfig(jConf);
					}
				}
			});
			jConf.getPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equals("preferences")
							&& evt.getNewValue() instanceof PropertyChangeEvent) {
						evt = (PropertyChangeEvent) evt.getNewValue();
						if (evt.getPropertyName().equals(CVCDescriptorsPreferencePage.RESOURCE_PATHS)) {
							for (CVCWidgetsDescriptor cd : cache.get(jConf).values()) {
								imageCache.remove(cd);
								parentsPath.remove(cd);
							}
							cache.remove(jConf);
							initCacheJConfig(jConf);
						}
					}
				}
			});
			jConf.addDisposeListener(new IDisposeListener() {

				@Override
				public void dispose() {
					Map<String, CVCWidgetsDescriptor> map = cache.get(jConf);
					if (map != null) {
						for (CVCWidgetsDescriptor cd : map.values()) {
							Image img = imageCache.get(cd);
							if (img != null)
								img.dispose();
							imageCache.remove(cd);
							parentsPath.remove(cd);
						}
					}
					cache.remove(jConf);
				}
			});

			String paths = jConf.getPrefStore().getString(CVCDescriptorsPreferencePage.RESOURCE_PATHS);
			StringTokenizer st = new StringTokenizer(paths, File.pathSeparator + "\n\r"); //$NON-NLS-1$
			Set<String> pathsList = new LinkedHashSet<String>();
			while (st.hasMoreTokens())
				pathsList.add(st.nextToken());
			for (String dir : pathsList) {
				File[] files = new File(dir).listFiles(new FileFilter() {
					@Override
					public boolean accept(File f) {
						return f.getName().endsWith(".json"); //$NON-NLS-1$
					}
				});
				if (files == null)
					continue;
				for (File f : files) {
					URL url = null;
					try {
						url = f.toURI().toURL();
						CVCWidgetsDescriptor cd = RESOLVER.resolveURL(url);
						if (cd != null) {
							modules.put(cd.getModule(), cd);
							parentsPath.put(cd, f.getParentFile().toURI().toASCIIString());
						}
					} catch (MalformedURLException e) {
						// we are not interested to handle this
					} catch (Exception ex) {
						// Log error but continue...
						CustomVisualizationActivator.getDefault()
								.log(new Status(IStatus.ERROR, CustomVisualizationActivator.PLUGIN_ID,
										MessageFormat.format(Messages.UIManager_2, new Object[] { url }), ex));
					}
				}
			}
		}
		return modules;
	}

	public static boolean isInPlugin(ComponentDescriptor cd) {
		return cachePlugin.values().contains(cd);
	}
}
