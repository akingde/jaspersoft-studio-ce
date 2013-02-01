/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.templates;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.templates.TemplateLocationsPreferencePage;
import com.jaspersoft.templates.TemplateBundle;

/**
 * The default implementation in JSS of template provider looks for templates inside the plugin's templates
 * directory and load the templates based on the typical characteristics of filesystem based templates
 * (the same used inside the Jaspersoft iReport Designer).
 *  
 * @author gtoffoli
 *
 */
public class DefaultTemplateProvider implements TemplateProvider {

	public static List<TemplateBundle> cache = null;
	@Override
	public List<TemplateBundle> getTemplateBundles() {
		
		List<TemplateBundle> templates = new ArrayList<TemplateBundle>();
		
		if (cache == null)
		{
			  cache =  new ArrayList<TemplateBundle>();
			  
				Enumeration<?> en = JaspersoftStudioPlugin.getInstance().getBundle().findEntries("templates", "*.jrxml", true); //$NON-NLS-1$ //$NON-NLS-2$
				while (en.hasMoreElements()) {
					URL templateURL = (URL) en.nextElement();
					
					try {
						
						
						JrxmlTemplateBundle bundle = new JrxmlTemplateBundle( templateURL );
						
						if (bundle != null)
						{
							cache.add(bundle);
						}
					} catch (Exception ex)
					{
						// Log error but continue...
						JaspersoftStudioPlugin.getInstance().getLog().log(
								new Status(IStatus.ERROR,JaspersoftStudioPlugin.PLUGIN_ID,
										MessageFormat.format(Messages.DefaultTemplateProvider_TemplateLoadingErr,new Object[]{templateURL}), ex));

					}
				}
		}
		
		templates.addAll(cache);

		loadAdditionalTemplateBundles(templates);
		
	  return templates;
	}
	
	/*
	 * Look for other templates inside the specified directories in the preferences.
	 */
	private void loadAdditionalTemplateBundles(List<TemplateBundle> templates) {
		String paths = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
		StringTokenizer st = new StringTokenizer(paths, File.pathSeparator + "\n\r");//$NON-NLS-1$
		ArrayList<String> pathsList = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			pathsList.add(st.nextToken());
		}

		for (String dir : pathsList) {
			File[] files = new File(dir).listFiles(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".jrxml"); //$NON-NLS-1$
				}
			});

			if (files != null) {
				for (File f : files) {
					try {
						JrxmlTemplateBundle bundle = new JrxmlTemplateBundle(f.toURI().toURL());
						if (bundle != null) {
							templates.add(bundle);
						}
					} catch (Exception ex) {
						// Log error but continue...
						JaspersoftStudioPlugin.getInstance().getLog().log(
								new Status(IStatus.ERROR,JaspersoftStudioPlugin.PLUGIN_ID,
										MessageFormat.format(Messages.DefaultTemplateProvider_TemplateLoadingErr,new Object[]{f.getAbsolutePath()}), ex));
					}
				}
			}
		}
	}

}
