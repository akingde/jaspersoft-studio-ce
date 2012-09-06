package com.jaspersoft.studio.templates;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.jaspersoft.templates.TemplateEngine;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.ResourceManager;

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
	
	private TemplateEngine templateEngine = null;
	
	
	/**
	 * This is the url of the jrxml used to define this type of bundle.
	 * 
	 */
	private URL templateURL = null;
	

	/**
	 * The list of files (available in the same directory as the jrxml),
	 * discovered by looking at the main jasperdesign...
	 * 
	 */
	protected List<String> resourceNames;
	
	
	
	/**
	 * A map to map resource names (file names) with their full location.
	 * 
	 */
	protected Map<String, URL> resourceUrls;

	private Image icon = null;;
		
	@Override
	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
	
	/**
	 * Get a named resource from the bundle
	 * 
	 * This template implementation assumes that all the resources
	 * are located in the same directory as the main report.
	 * 
	 * @param name
	 * 					The name of the resource to open
	 * 
	 * @return an InputStream or null if the resource has not been found or an error has occurred
	 */
	@Override
	public InputStream getResource(String name) {
		
		if (!getResourceNames().contains(name)) return null;
		
		// We need to replace the last name from the current templateURL..
		String url = templateURL.toString();
		
		String mainFileName = new File(templateURL.getFile()).getName();
	
		url = url.substring(0, url.length() - mainFileName.length()) + name;
		try {
			URL resourceURL = new URL(url);
			
			return resourceURL.openStream();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		
		if (resourceNames == null)
		{
			
			resourceNames = new ArrayList<String>();
			
			List<JRDesignElement> list = ModelUtils.getAllGElements(this.jasperDesign);
			
			System.out.println("Elements found: " + list);
			
			for (JRDesignElement el : list) {
				
			// Check for images...
				if (el instanceof JRImage) {
					JRImage im = (JRImage) el;
					
					String res = evalResourceName(im.getExpression());
					System.out.println("Evaluation " + im.getExpression().getText() + " " + res);
					
					if (res != null)
					{
						resourceNames.add(res);
					}
				}
				
			// Check for subreports (filename.jasper becomes filename.jrxml)				
				if (el instanceof JRSubreport) {
					JRSubreport sr = (JRSubreport) el;
					
					String res = evalResourceName(sr.getExpression());
					
					if (res.endsWith(".jasper"))
					{
						res = res.substring(0, res.length() - ".jasper".length()) + ".jrxml";
						resourceNames.add(res);
					}
				}
				
			}
			
			// Check for external style references
			List<JRReportTemplate> templates = this.jasperDesign.getTemplatesList();
			for (JRReportTemplate t : templates)
			{
				 	String res = evalResourceName( t.getSourceExpression());
					if (res != null)
					{
						resourceNames.add(res);
					}
			}
		
		}
						
//						Enumeration<?> en = JaspersoftStudioPlugin.getInstance().getBundle().findEntries(Messages.ReportNewWizard_7, str, true);
//						while (en.hasMoreElements()) {
//							URL uimage = (URL) en.nextElement();
//							IFile f = repFile.getParent().getFile(new Path(str));
//							try {
//								if (!f.exists())
//									f.create(uimage.openStream(), true, monitor);
//							} catch (CoreException e) {
//								e.printStackTrace();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						}

		return resourceNames;
	}

	
	/**
	 * This method check that an expression has a text of type:
	 * 
	 * "filename"
	 * 
	 * if the format is different, or if filename does not exist in
	 * the current report directory, it returns null.
	 * 
	 * @param exp
	 * @return the correct filename
	 */
	private String evalResourceName(JRExpression exp)
	{
		if (exp == null) return null;
		if (exp.getText() == null || exp.getText().length() == 0) return null;
		
		String text = exp.getText().trim();
		
		if (text.charAt(0)  != '"') return null;
		
		text = text.substring(1);
		
		if (text.lastIndexOf('"') != text.length() -1) return null;
		
		text = text.substring(0, text.length() - 1);
		
		if (text.indexOf('"') >=0) return null;
		
		java.io.File f = new java.io.File(text);
		
		// We don't accept images inside a subdirectory, all must be in the same directory as the main jrxml
		if (f.getParent() != null) return null;
		
		return text;
		
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
		} finally {
			if (is != null) is.close();
		}
			
	  if (this.jasperDesign != null)
	  {
	  		// read information from the jasper design object...
	  		readProperties();
	  		
	  		if (templateURL.getFile().endsWith(".jrxml"))
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
	  
	}

	
	/**
	 * Check for an icon provided by the template. If an icon is not available,
	 * it defaults to an internal report png.
	 * 
	 * @param iconURL
	 * @return
	 */
	private Image getIconFromUrl(URL iconURL) {
		
		
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(iconURL);
		
		if (descriptor == null)
		{		
			// fall back to the icons/report.png...
			descriptor = ResourceManager.getImageDescriptor("icons/report.png"); //$NON-NLS-1$
		}
		
		return ResourceManager.getImage(descriptor);
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
	 * The jasperdesign provided by the template
	 * ready to be customized.
	 * 
	 * Attention! A new JasperDesign is created on each call.
	 * 
	 * 
	 */
	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}

	/**
	 * @return the templateURL
	 */
	protected URL getTemplateURL() {
		return templateURL;
	}

	/**
	 * @param templateURL the templateURL to set
	 */
	protected void setTemplateURL(URL templateURL) {
		this.templateURL = templateURL;
	}
	
	
	/**
	 * Introspect the jasperdesign to set template label and other possible information
	 * 
	 */
	protected void readProperties()
	{
		if (this.jasperDesign != null)
		{
			setLabel(  this.jasperDesign.getName() );
			
			if (this.jasperDesign.getProperty("template.engine") != null)
			{
				// TODO: handle the selection of a special template engine.
				//String templateEngineClassName = this.jasperdesign.getProperty("template.engine");
				// Check if this templateEngine exists...
				templateEngine = new DefaultTemplateEngine();
			}
			else
			{
				templateEngine = new DefaultTemplateEngine();
			}
		}
	}
	
}
