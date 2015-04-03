package com.jaspersoft.studio.kpi.template;

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
import com.jaspersoft.studio.kpi.Activator;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.templates.TemplateLocationsPreferencePage;
import com.jaspersoft.studio.templates.TemplateProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.BuiltInCategories;
import com.jaspersoft.templates.TemplateBundle;

public class KPITemplateProvider  implements TemplateProvider {

	/**
	 * List of loaded templates
	 */
	public static List<TemplateBundle> cache = null;
	
	/**
	 * Key used to distinguish the tabular template from the other templates
	 */
	public static final String kpiTemplateKey="kpi_template";
	
	/**
	 * Read all the templates jrxml in the folder templates/book, the subdirectories are excluded
	 */
	@Override
	public List<TemplateBundle> getTemplateBundles() {
		List<TemplateBundle> templates = new ArrayList<TemplateBundle>();	
		if (cache == null){
			cache =  new ArrayList<TemplateBundle>();
			Enumeration<?> en = Activator.getDefault().getBundle().findEntries("/resources/templates", "*.jrxml", false); //Doesn't search in the subdirectories
			while (en != null && en.hasMoreElements()) {
				URL templateURL = (URL) en.nextElement();
				try {
					TemplateBundle bundle = new KPITemplateBundle(templateURL, false, JasperReportsConfiguration.getDefaultInstance());
					if (bundle != null)
					{
						cache.add(bundle);
					}	
				} catch (Exception ex) 	{
					// Log error but continue...
					Activator.getDefault().getLog().log(
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
		String paths = JaspersoftStudioPlugin.getInstance().getPreferenceStore().getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
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
						TemplateBundle bundle = new KPITemplateBundle(f.toURI().toURL(), true, JasperReportsConfiguration.getDefaultInstance());
						if (bundle != null && kpiTemplateKey.equals(bundle.getProperty(BuiltInCategories.ENGINE_KEY))) {
							templates.add(bundle);
						}
					} catch (Exception ex) {
						// Log error but continue...
						Activator.getDefault().getLog().log(
								new Status(IStatus.ERROR,JaspersoftStudioPlugin.PLUGIN_ID,
										MessageFormat.format(Messages.DefaultTemplateProvider_TemplateLoadingErr,new Object[]{f.getAbsolutePath()}), ex));
					}
				}
			}
		}
	}

	@Override
	public String getProviderKey() {
		return kpiTemplateKey;
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
