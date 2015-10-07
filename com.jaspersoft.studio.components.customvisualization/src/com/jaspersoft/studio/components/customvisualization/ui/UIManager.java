/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

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
import com.jaspersoft.studio.utils.jasper.IDisposeListener;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class UIManager {
	private static Map<String, ComponentDescriptor> cachePlugin = new HashMap<String, ComponentDescriptor>();
	private static Map<JasperReportsConfiguration, Map<String, ComponentDescriptor>> cache = new HashMap<JasperReportsConfiguration, Map<String, ComponentDescriptor>>();

	public static ComponentDescriptor getDescriptor(
			final JasperReportsConfiguration jConfig, String module) {
		// let's look if we cached something
		Map<String, ComponentDescriptor> modules = cache.get(jConfig);
		if (modules == null) {
			modules = new HashMap<String, ComponentDescriptor>();
			cache.put(jConfig, modules);
			jConfig.addDisposeListener(new IDisposeListener() {

				@Override
				public void dispose() {
					cache.remove(jConfig);
				}
			});
		}
		ComponentDescriptor d = modules.get(module);
		if (d != null)
			return d;

		// ok, let's look into plugin,
		d = cachePlugin.get(module);
		if (d != null) {
			modules.put(module, d);
			return d;
		}
		CustomVisualizationActivator activator = CustomVisualizationActivator
				.getDefault();
		Enumeration<?> en = activator.getBundle().findEntries("resources", //$NON-NLS-1$
				"*.json", true); //$NON-NLS-1$
		while (en != null && en.hasMoreElements()) {
			URL url = (URL) en.nextElement();
			try {
				// use jackson to read the file
				ComponentDescriptor cd = readURL(url);
				cachePlugin.put(module, d);
				if (d.getModule().equals(module))
					d = cd;
			} catch (Exception ex) {
				// Log error but continue...
				activator.log(new Status(IStatus.ERROR,
						CustomVisualizationActivator.PLUGIN_ID, MessageFormat
								.format(Messages.UIManager_2,
										new Object[] { url }), ex));
			}
		}
		if (d != null) {
			modules.put(module, d);
			return d;
		}

		// ok, let's look into our preferences

		String paths = jConfig.getPrefStore().getString(
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
					if (cd != null && cd.getModule().equals(module)) {
						modules.put(module, cd);
						return cd;
					}
				} catch (MalformedURLException e) {
					// we are not interested to handle this
				} catch (Exception ex) {
					// Log error but continue...
					activator.log(new Status(IStatus.ERROR,
							CustomVisualizationActivator.PLUGIN_ID,
							MessageFormat.format(Messages.UIManager_2,
									new Object[] { url }), ex));
				}
			}
		}
		return null;
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
