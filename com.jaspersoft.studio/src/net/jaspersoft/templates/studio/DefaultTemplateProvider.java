package net.jaspersoft.templates.studio;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.jaspersoft.templates.TemplateBundle;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

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
						System.out.println(ex.getMessage() + " " + templateURL);
						// An error occurred while loading a template... let's ignore this template and continue.
					}
				}
		}
		
		templates.addAll(cache);
		// Look for other templates inside a specific directory...
		
		File dir = new File("/Applications/iReport.app/Contents/Resources/ireport/ireport/templates");
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".jrxml");
			}
		});
			
		for (File f : files)
		{
			try {
				JrxmlTemplateBundle bundle = new JrxmlTemplateBundle( f.toURI().toURL() );
				if (bundle != null)
				{
					templates.add(bundle);
				}
			} catch (Exception ex)
			{
				System.out.println(ex.getMessage() + " " + f);
				// An error occurred while loading a template... let's ignore this template and continue.
			}
		}
		
	  return templates;
	}
}
