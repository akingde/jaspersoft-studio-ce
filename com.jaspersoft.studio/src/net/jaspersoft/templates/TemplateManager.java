package net.jaspersoft.templates;

import java.util.List;

/**
 * 
 * This interface represnt the basic service to list the available templates
 * 
 * @author gtoffoli
 *
 */
public interface TemplateManager {

		 /**
		  * 
		  * The list of available templates.
		  * 
		  * @return
		  */
			public List<TemplateBundle> getTemplateBundles();
	
}
