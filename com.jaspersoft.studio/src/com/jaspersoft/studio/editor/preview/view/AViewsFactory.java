/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Set;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.preferences.exporter.JRExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AViewsFactory {

	/**
	 * Return the available keys for the preview area, may contains separator
	 */
	public abstract Set<String> getKeys();

	protected abstract LinkedHashMap<String, Class<? extends APreview>> getMap();

	/**
	 * Return true if a key is invalid or a separator
	 */
	public boolean isSeparator(String key) {
		return getMap().get(key) == null;
	}

	public String getLabel(String key) {
		return key;
	}

	public LinkedHashMap<String, APreview> createPreviews(final Composite composite,
			final JasperReportsConfiguration jContext) {
		LinkedHashMap<String, APreview> pmap = new LinkedHashMap<String, APreview>() {
			public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public APreview get(Object key) {
				APreview val = super.get(key);
				if (val == null && getMap().get(key) != null) {
					Class<? extends APreview> clazz = getMap().get(key);
					try {
						val = clazz.getConstructor(Composite.class, JasperReportsConfiguration.class)
								.newInstance(composite, jContext);
					} catch (IllegalArgumentException | SecurityException | InstantiationException
							| IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						e.printStackTrace();
					}
					put((String) key, val);
				}
				return val;
			}
		};
		for (String key : getMap().keySet()) {
			if (key.equals(ViewsFactory.HTML_NO_INTERACTIVITY) && !jContext
					.getPropertyBoolean(JRExporterPreferencePage.COM_JASPERSOFT_STUDIO_EXPORTER_SHOW_HTML, false))
				continue;
			pmap.put(key, null);
		}
		return pmap;
	}
}
