/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.repo.RepositoryUtil;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.components.customvisualization.ui.preferences.CVCDescriptorsPreferencePage;
import com.jaspersoft.studio.property.itemproperty.desc.ColorPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ComboItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.NumberPropertyDescription;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.IDisposeListener;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class UIManager {
	private static Map<String, ComponentDescriptor> cachePlugin = new HashMap<String, ComponentDescriptor>();
	private static Map<JasperReportsConfiguration, Map<String, ComponentDescriptor>> cache = new HashMap<JasperReportsConfiguration, Map<String, ComponentDescriptor>>();
	private static Map<ComponentDescriptor, Image> imageCache = new HashMap<ComponentDescriptor, Image>();

	public static void copyFile(ComponentDescriptor cd,
			JasperReportsConfiguration jConf, String path) {
		if (Misc.isNullOrEmpty(path))
			return;
		IFile f = (IFile) jConf.get(FileUtils.KEY_FILE);
		if (f == null)
			return;
		IContainer destFolder = f.getParent();
		File dest = new File(destFolder.getRawLocation().toFile(), path);

		InputStream is = null;
		try {
			if (isInPlugin(cd)) {
				URL url = CustomVisualizationActivator.getDefault().getBundle()
						.getEntry(path);
				if (url != null)
					is = url.openStream();
			} else {
				is = RepositoryUtil.getInstance(jConf)
						.getInputStreamFromLocation(path);
			}
			if (is != null) {
				org.apache.commons.io.FileUtils.copyInputStreamToFile(is, dest);
				destFolder.refreshLocal(1, new NullProgressMonitor());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(is);
		}
	}

	public static Image getThumbnail(ComponentDescriptor cd,
			JasperReportsConfiguration jConf) {
		Image img = imageCache.get(cd);
		if (img == null && !Misc.isNullOrEmpty(cd.getThumbnail())) {
			try {
				InputStream is = RepositoryUtil.getInstance(jConf)
						.getInputStreamFromLocation(cd.getThumbnail());
				if (is != null) {
					try {
						img = new Image(null, is);
						imageCache.put(cd, img);
					} finally {
						FileUtils.closeStream(is);
					}
				}
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static List<ComponentDescriptor> getModules(
			JasperReportsConfiguration jConfig) {
		List<ComponentDescriptor> res = new ArrayList<ComponentDescriptor>();
		if (cachePlugin == null)
			initCachePlugin();
		res.addAll(cachePlugin.values());
		Map<String, ComponentDescriptor> modules = initCacheJConfig(jConfig);
		res.addAll(modules.values());
		return res;
	}

	public static ComponentDescriptor getDescriptor(
			final JasperReportsConfiguration jConfig, String module) {
		if (cachePlugin == null)
			initCachePlugin();
		ComponentDescriptor d = cachePlugin.get(module);
		if (d != null)
			return d;
		Map<String, ComponentDescriptor> modules = cache.get(jConfig);
		if (modules == null)
			modules = initCacheJConfig(jConfig);
		// let's look if we cached something
		return modules.get(module);
	}

	protected static CustomVisualizationActivator initCachePlugin() {
		CustomVisualizationActivator activator = CustomVisualizationActivator
				.getDefault();
		Enumeration<?> en = activator.getBundle().findEntries("resources", //$NON-NLS-1$
				"*.json", true); //$NON-NLS-1$
		while (en != null && en.hasMoreElements()) {
			URL url = (URL) en.nextElement();
			try {
				// use jackson to read the file
				ComponentDescriptor cd = readURL(url);
				if (cd != null)
					cachePlugin.put(cd.getModule(), cd);
			} catch (Exception ex) {
				// Log error but continue...
				activator.log(new Status(IStatus.ERROR,
						CustomVisualizationActivator.PLUGIN_ID, MessageFormat
								.format(Messages.UIManager_2,
										new Object[] { url }), ex));
			}
		}
		return activator;
	}

	protected static Map<String, ComponentDescriptor> initCacheJConfig(
			final JasperReportsConfiguration jConf) {
		Map<String, ComponentDescriptor> modules = cache.get(jConf);
		if (modules == null) {
			modules = new HashMap<String, ComponentDescriptor>();
			cache.put(jConf, modules);
			jConf.addDisposeListener(new IDisposeListener() {

				@Override
				public void dispose() {
					cache.remove(jConf);
				}
			});

			String paths = jConf.getPrefStore().getString(
					CVCDescriptorsPreferencePage.RESOURCE_PATHS);
			StringTokenizer st = new StringTokenizer(paths, File.pathSeparator
					+ "\n\r"); //$NON-NLS-1$
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
						ComponentDescriptor cd = readURL(url);
						if (cd != null)
							modules.put(cd.getModule(), cd);
					} catch (MalformedURLException e) {
						// we are not interested to handle this
					} catch (Exception ex) {
						// Log error but continue...
						CustomVisualizationActivator.getDefault().log(
								new Status(IStatus.ERROR,
										CustomVisualizationActivator.PLUGIN_ID,
										MessageFormat.format(
												Messages.UIManager_2,
												new Object[] { url }), ex));
					}
				}
			}
		}
		return modules;
	}

	public static boolean isInPlugin(ComponentDescriptor cd) {
		return cachePlugin.values().contains(cd);
	}

	private static ComponentDescriptor readURL(URL url) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(url, ComponentDescriptor.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ItemPropertyDescription<?> createItemPropertyDescriptor(
			ComponentPropertyDescriptor cpd) {
		ItemPropertyDescription<?> desc = null;
		Number min = null;
		Number max = null;
		Number def = null;
		if (cpd.getType().equalsIgnoreCase("text"))
			desc = new ItemPropertyDescription<String>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					cpd.getDefaultValue());
		else if (cpd.getType().equalsIgnoreCase("path"))
			desc = new ItemPropertyDescription<String>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					cpd.getDefaultValue());
		else if (cpd.getType().equalsIgnoreCase("combo"))
			desc = new ComboItemPropertyDescription<String>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					cpd.getDefaultValue(), cpd.getOptions());
		else if (cpd.getType().equalsIgnoreCase("color"))
			desc = new ColorPropertyDescription<String>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					cpd.getDefaultValue());

		else if (cpd.getType().equalsIgnoreCase("float")) {
			if (cpd.getMin() != null)
				min = new Float(cpd.getMin());
			if (cpd.getMax() != null)
				max = new Float(cpd.getMax());
			if (cpd.getDefaultValue() != null)
				def = new Float(cpd.getDefaultValue());
			desc = new NumberPropertyDescription<Float>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					(Float) def, min, max);
		} else if (cpd.getType().equalsIgnoreCase("integer")) {
			if (cpd.getMin() != null)
				min = new Integer(cpd.getMin());
			if (cpd.getMax() != null)
				max = new Integer(cpd.getMax());
			if (cpd.getDefaultValue() != null)
				def = new Integer(cpd.getDefaultValue());
			desc = new NumberPropertyDescription<Integer>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					(Integer) def, min, max);
		} else if (cpd.getType().equalsIgnoreCase("double")) {
			if (cpd.getMin() != null)
				min = new BigDecimal(cpd.getMin());
			if (cpd.getMax() != null)
				max = new BigDecimal(cpd.getMax());
			if (cpd.getDefaultValue() != null)
				def = new BigDecimal(cpd.getDefaultValue());
			desc = new NumberPropertyDescription<BigDecimal>(cpd.getName(),
					cpd.getLabel(), cpd.getDescription(), cpd.isMandatory(),
					(BigDecimal) def, min, max);
		}
		if (desc != null)
			desc.setReadOnly(cpd.isReadOnly());
		return desc;
	}
}
