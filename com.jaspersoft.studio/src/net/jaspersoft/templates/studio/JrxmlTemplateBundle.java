package net.jaspersoft.templates.studio;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.jaspersoft.templates.TemplateBundle;
import net.jaspersoft.templates.TemplateEngine;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * This is a generic template bundle able to laod info from a JRXML file.
 * The Jrxml location is provided via URL, so the location of the jrxml is filesystem independent (it could be a jar,
 * a bundleentry or a regural file inside a directory).
 * 
 * @author gtoffoli
 *
 */
public class JrxmlTemplateBundle implements IconedTemplateBundle	 {

	public static final String MAIN_REPORT = "MAIN_REPORT";
	public static final String DEFAULT_ICON = "blank_a4.png";
	
	private String label;
	private String category = null;
	private JasperDesign jasperDesign = null;

	
	/**
	 * This is the url of the jrxml used to define this type of bundle.
	 * 
	 */
	private URL templateURL = null;
	
	
	protected List<String> resourceNames;
	
	
	
	/**
	 * A map to map resource names (file names) with their full location.
	 * 
	 */
	protected Map<String, URL> resourceUrls;

	private Image icon = null;;
		
	@Override
	public TemplateEngine getTemplateEngine() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Get a named resource from the bundle
	 */
	@Override
	public InputStream getResource(String name) {
		
		return null;
	}
	
/**
	 * @return the category (not used for now, always returns null)
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 *  
	 * @return the label 
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	
	/**
	 * Return the name of all the resources referenced by this template.
	 * This can be an expensive operation, since the jrxml representing the template may be loaded in order
	 * locate all the referenced resources.
	 * 
	 */
	@Override
	public List<String> getResourceNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Creates a template bundle from a file.
	 * 
	 * @param file
	 * @throws Exception
	 */
	public JrxmlTemplateBundle(URL url) throws Exception
	{
		
		this.templateURL = url;
		InputStream is = templateURL.openStream();
		try {
				
				this.jasperDesign = JRXmlLoader.load(is);
				is.close();
		} finally {
			if (is != null) is.close();
		}
			
	  if (this.jasperDesign != null && templateURL.getFile().endsWith(".jrxml"))
	  {
	  	 // locate the template thumbnail by replacing the .jrxml with png....
	  	 String[] imageExtensions = new String[] { ".png",".gif", ".jpg" };
	  	 
	  	 String baseImageUrl = templateURL.toExternalForm();
	  	 // remove the .jrxml...
	  	 baseImageUrl = baseImageUrl.substring(0, baseImageUrl.length() - ".jrxml".length()  );
	  	 
	  	 for (String extension : imageExtensions)
	  	 {
	  		 URL iconURL = new URL(baseImageUrl + extension);
	  		 setIcon(getIconFromUrl(iconURL));
	  		 if (getIcon() != null) break;
	  	 }
	  }
	}

	private Image getIconFromUrl(URL iconURL) {
		
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(iconURL);
		
		if (descriptor == null)
		{
			// fall back to the icons/report.png...
			descriptor = ModelUtils.getImageDescriptor("icons/report.png"); //$NON-NLS-1$
		}
		
		ImageRegistry imageRegistry = JaspersoftStudioPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(descriptor.toString());
		
		if (image == null) {
			
			// Let's load the image form the URL...
			try {
				image = descriptor.createImage();
			} catch (Exception ex) {
				return null;
			}
			if (image != null)
				imageRegistry.put(descriptor.toString(), image);
			else
				image = imageRegistry.get("icons/report.png"); //$NON-NLS-1$
		}
		return image;
	}
	

	/**
	 * @return the templateIcon
	 */
	public Image getIcon() {
		
		return icon;
	}

	/**
	 * @param templateIcon the templateIcon to set
	 */
	public void setIcon(Image templateIcon) {
		this.icon = templateIcon;
	}

	/**
	 * This property is not part of the TemplateBundle and should not be used by external
	 * APIs. The method is public just for convenience if the report must be loaded,
	 * but it should be considered READ ONLY!
	 * 
	 * @return the jasperDesign
	 */
	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}

	/**
	 * @return the templateURL
	 */
	public URL getTemplateURL() {
		return templateURL;
	}

	/**
	 * @param templateURL the templateURL to set
	 */
	public void setTemplateURL(URL templateURL) {
		this.templateURL = templateURL;
	}
	
}
