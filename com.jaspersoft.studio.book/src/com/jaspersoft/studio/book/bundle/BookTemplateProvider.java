/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book.bundle;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.templates.TemplateLocationsPreferencePage;
import com.jaspersoft.studio.templates.TemplateProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.BuiltInCategories;
import com.jaspersoft.templates.TemplateBundle;

/**
 * Book template bundle provider, scan the template directories to eventually found
 * a the book template bundles
 * 
 * @author Orlandin Marco
 *
 */
public class BookTemplateProvider implements TemplateProvider {

	/**
	 * List of loaded templates
	 */
	public static List<TemplateBundle> cache = null;
	
	/**
	 * Key used to distinguish the tabular template from the other templates
	 */
	public static final String bookTemplateKey="book_template";
	
	/**
	 * Read all the templates jrxml in the folder templates/book, the subdirectories are excluded
	 */
	@Override
	public List<TemplateBundle> getTemplateBundles() {
		List<TemplateBundle> templates = new ArrayList<TemplateBundle>();	
		if (cache == null){
			cache =  new ArrayList<TemplateBundle>();
			Enumeration<?> en = JaspersoftStudioPlugin.getInstance().getBundle().findEntries("templates/book", "*.jrxml", false); //Doesn't search in the subdirectories
			while (en != null && en.hasMoreElements()) {
				URL templateURL = (URL) en.nextElement();
				try {
					TemplateBundle bundle = new BookTemplateBundle(templateURL, false, JasperReportsConfiguration.getDefaultInstance());
					if (bundle != null)
					{
						cache.add(bundle);
					}	
				} catch (Exception ex) 	{
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
	
	/**
	 * Look for other templates inside the specified directories in the preferences.
	 */
	private void loadAdditionalTemplateBundles(List<TemplateBundle> templates) {
		
		String paths = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
		StringTokenizer st = new StringTokenizer(paths, File.pathSeparator + "\n\r");
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
						TemplateBundle bundle = new BookTemplateBundle(f.toURI().toURL(), true, JasperReportsConfiguration.getDefaultInstance());
						if (bundle != null && bookTemplateKey.equals(bundle.getProperty(BuiltInCategories.ENGINE_KEY))) {
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

	@Override
	public String getProviderKey() {
		return bookTemplateKey;
	}

	@Override
	public String getProviderName() {
		return "Book Template";
	}

	@Override
	public List<String> validateTemplate(JasperDesign design) {
		return new ArrayList<String>();
	}

}
