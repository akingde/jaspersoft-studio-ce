package com.jaspersoft.templates;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.templates.DefaultTemplateProvider;
import com.jaspersoft.studio.templates.IconedTemplateBundle;
import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.BuiltInCategories;

/**
 * This is a generic template bundle able to load info from a JRXML file. The Jrxml location is provided via URL, so the
 * location of the jrxml is filesystem independent (it could be a jar, a bundleentry or a regular file inside a
 * directory). When the JRXML is loaded also a properties file is searched in the same location with the name of
 * nameOfTheJRXML_descriptor.properties. This properties file contains some basic information on the template, like
 * categories, name and so on. If the properties file is not found then these properties are read from the JRXML
 * 
 * @author Giulio Toffoli & Orlandin Marco
 * 
 */
public class GenericTemplateBundle implements IconedTemplateBundle, ValidatedTemplateBundle {

	public static final String MAIN_REPORT = "MAIN_REPORT"; //$NON-NLS-1$

	public static final String DEFAULT_ICON = "blank_a4.png"; //$NON-NLS-1$

	private String label;

	/**
	 * The design of the template report, that will be copied and modified to create the new report
	 */
	private JasperDesign jasperDesign = null;

	/**
	 * True if the report is not one of the JSS default report, false otherwise
	 */
	private boolean isExternal;

	/**
	 * The template engine to generate a report from this template
	 */
	protected TemplateEngine templateEngine = null;

	/**
	 * This is the url of the jrxml used to define this type of bundle.
	 * 
	 */
	private URL templateURL = null;

	/**
	 * The list of files (available in the same directory as the jrxml), discovered by looking at the main jasperdesign...
	 * 
	 */
	protected List<String> resourceNames;

	/**
	 * The properties file associated with the report
	 */
	protected Properties propertyFile = null;

	/**
	 * A map to map resource names (file names) with their full location.
	 */
	protected Map<String, URL> resourceUrls;

	/**
	 * The icon for the report inside JSS
	 */
	private ImageDescriptor icon = null;

	/**
	 * The current context
	 */
	private JasperReportsContext jrContext;

	/**
	 * Creates a template bundle from a template file.
	 * 
	 * @param file
	 *          URL of the template file
	 * @param jrContext
	 *          the context of the new report. If it is null then the default one is used
	 * @throws Exception
	 */
	public GenericTemplateBundle(URL url, JasperReportsContext jrContext) throws Exception {
		this(url, false, jrContext);
	}

	/**
	 * Creates a template bundle from a template file.
	 * 
	 * @param file
	 *          URL of the template file
	 * @param jrContext
	 *          the context of the new report. If it is null then the default one is used
	 * @param isExternal
	 *          True if the report is not one of the JSS default report, false otherwise
	 * @throws Exception
	 */
	public GenericTemplateBundle(URL url, boolean isExternal, JasperReportsContext jrContext) throws Exception {
		if (jrContext == null)
			jrContext = JasperReportsConfiguration.getDefaultInstance();
		this.jrContext = jrContext;
		this.templateURL = url;
		this.isExternal = isExternal;
		String urlPath = URLDecoder.decode(templateURL.toExternalForm(), "utf-8"); //$NON-NLS-1$
		if (urlPath.endsWith(FileExtension.PointJRXML)) {
			String propertiesPath = urlPath.substring(0, urlPath.length() - 6).concat("_descriptor.properties"); //$NON-NLS-1$

			URL propertiesFile = new URL(propertiesPath);
			if (!isExternal() || (new File(propertiesFile.getFile())).exists()) {
				this.propertyFile = new Properties();
				InputStream ioStream = null;
				try {
					ioStream = propertiesFile.openStream();
					this.propertyFile.load(ioStream);
				} finally {
					FileUtils.closeStream(ioStream);
				}
			}

			// read information from the jasper design object...
			readProperties();
			// locate the template thumbnail by replacing the .jrxml with png....
			String[] imageExtensions = new String[] { ".png", ".gif", ".jpg" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			String baseImageUrl = URLDecoder.decode(templateURL.toExternalForm(), "utf-8"); //$NON-NLS-1$
			// remove the .jrxml...
			baseImageUrl = baseImageUrl.substring(0, baseImageUrl.length() - FileExtension.PointJRXML.length());
			for (String extension : imageExtensions) {
				try {
					URL iconURL = new URL(baseImageUrl + extension);
					setIcon(getIconFromUrl(iconURL));
					if (getIcon() != null)
						break;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			if (getIcon() == null) {
				// Use a default icon
				setIcon(JaspersoftStudioPlugin.getInstance().getImageDescriptor("templates/blank_a4.png")); //$NON-NLS-1$
			}
		}

	}

	/**
	 * Return the template engine to handle the current template
	 */
	@Override
	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	/**
	 * Get a named resource from the bundle
	 * 
	 * This template implementation assumes that all the resources are located in the same directory as the main report.
	 * 
	 * @param name
	 *          The name of the resource to open
	 * 
	 * @return an InputStream or null if the resource has not been found or an error has occurred
	 */
	@Override
	public InputStream getResource(String name) {
		if (!getResourceNames().contains(name))
			return null;
		// We need to replace the last name from the current templateURL..
		String url = templateURL.toString();
		String mainFileName = new File(templateURL.getFile()).getName();
		url = url.substring(0, url.length() - mainFileName.length()) + name;
		try {
			URL resourceURL = new URL(url);
			return resourceURL.openStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *          the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Return the name of all the resources referenced by this template. This can be an expensive operation, since the
	 * jrxml representing the template may be loaded in order locate all the referenced resources.
	 * 
	 */
	@Override
	public List<String> getResourceNames() {

		if (resourceNames == null) {
			resourceNames = new ArrayList<String>();
			List<JRDesignElement> list = ModelUtils.getAllGElements(getJasperDesign());
			System.out.println("Elements found: " + list); //$NON-NLS-1$
			for (JRDesignElement el : list) {
				// Check for images...
				if (el instanceof JRImage) {
					JRImage im = (JRImage) el;
					String res = evalResourceName(im.getExpression());
					if (res != null) {
						resourceNames.add(res);
					}
				}
				// Check for subreports (filename.jasper becomes filename.jrxml)
				if (el instanceof JRSubreport) {
					JRSubreport sr = (JRSubreport) el;
					String res = evalResourceName(sr.getExpression());
					if (res.endsWith(".jasper")) { //$NON-NLS-1$
						res = res.substring(0, res.length() - ".jasper".length()) + ".jrxml"; //$NON-NLS-1$ //$NON-NLS-2$
						resourceNames.add(res);
					}
				}
			}
			// Check for external style references
			List<JRReportTemplate> templates = getJasperDesign().getTemplatesList();
			for (JRReportTemplate t : templates) {
				String res = evalResourceName(t.getSourceExpression());
				if (res != null) {
					resourceNames.add(res);
				}
			}
		}
		return resourceNames;
	}

	/**
	 * Return a property for the TemplateBundle. First the property is read from the properties file of the report. If
	 * this file is not available then the property is read from the JasperDesign
	 */
	public Object getProperty(String properyName) {
		if (propertyFile != null)
			return propertyFile.getProperty(properyName);
		return getJasperDesign().getProperty(properyName);
	}

	/**
	 * This method check that an expression has a text of type:
	 * 
	 * "filename"
	 * 
	 * if the format is different, or if filename does not exist in the current report directory, it returns null.
	 * 
	 * @param exp
	 * @return the correct filename
	 */
	protected String evalResourceName(JRExpression exp) {
		if (exp == null)
			return null;
		if (exp.getText() == null || exp.getText().length() == 0)
			return null;

		String text = exp.getText().trim();

		if (text.charAt(0) != '"')
			return null;

		text = text.substring(1);

		if (text.lastIndexOf('"') != text.length() - 1)
			return null;

		text = text.substring(0, text.length() - 1);

		if (text.indexOf('"') >= 0)
			return null;

		java.io.File f = new java.io.File(text);

		// We don't accept images inside a subdirectory, all must be in the same directory as the main jrxml
		if (f.getParent() != null)
			return null;

		return text;

	}

	/**
	 * Load the jasperdesign from the JRXML file and save it
	 */
	protected void loadJasperDesign() {
		InputStream is = null;
		try {
			is = templateURL.openStream();
			this.jasperDesign = JRXmlLoader.load(jrContext, is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(is);
		}
	}

	/**
	 * Check for an icon provided by the template. If it is available it is returned, otherwise it return null
	 * 
	 * @param iconURL
	 *          the url of the icon
	 * @return the icon or null if it can't be found
	 */
	private ImageDescriptor getIconFromUrl(URL iconURL) {
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(iconURL);
		if (descriptor != null && descriptor.getImageData() != null)
			return descriptor;
		return null;
	}

	/**
	 * @return the templateIcon
	 */
	public ImageDescriptor getIcon() {
		return icon;
	}

	/**
	 * @param templateIcon
	 *          the templateIcon to set
	 */
	public void setIcon(ImageDescriptor templateIcon) {
		this.icon = templateIcon;
	}

	/**
	 * The jasperdesign provided by the template ready to be customized. If the jasperdesign was not previously loaded
	 * then it is read from the JRXML file
	 * 
	 */
	public JasperDesign getJasperDesign() {
		if (jasperDesign == null)
			loadJasperDesign();
		return jasperDesign;
	}

	/**
	 * @return the templateURL
	 */
	public URL getTemplateURL() {
		return templateURL;
	}

	/**
	 * @param templateURL
	 *          the templateURL to set
	 */
	protected void setTemplateURL(URL templateURL) {
		this.templateURL = templateURL;
	}

	/**
	 * Introspect the properties file or jasperdesign to set template label and engine informations
	 */
	protected void readProperties() {
		String name = null;
		String engine = null;
		if (this.propertyFile != null) {
			name = propertyFile.getProperty(BuiltInCategories.NAME_KEY);
			engine = propertyFile.getProperty(BuiltInCategories.ENGINE_KEY);
		}

		if (engine == null || engine.toLowerCase().equals(DefaultTemplateProvider.defaultEngineKey)) {
			templateEngine = new DefaultTemplateEngine();
		}

		if (name == null) {
			name = getJasperDesign().getName();
		}
		setLabel(name);
	}

	@Override
	public boolean isExternal() {
		return isExternal;
	}

	/**
	 * Validate the template using the version property. If a template specify a property with the key
	 * BuiltInCategories.REQUIRED_JR_VERSION and with value the minimum required version then that version
	 * is compared with the current JSS compatibility version and if the current is equal or greater it return
	 * null, otherwise it return an error message. If the bundle doesn't specify the property then the version
	 * is simply not checked and the template will be assumed valid for the current configuration
	 */
	@Override
	public List<String> validateConfiguration() {
		Object requestedVersion = getProperty(BuiltInCategories.REQUIRED_JR_VERSION);
		if (requestedVersion != null){
			String currentVersion = getCurrentVersion();
			boolean validVersion = versionCompare(currentVersion, (String)requestedVersion)>=0;
			if (!validVersion){
				List<String> errors = new ArrayList<String>();
				errors.add(MessageFormat.format(Messages.GenericTemplateBundle_invalidVersionMessage, new Object[]{requestedVersion, currentVersion}));
				return errors;
			}
		}
		return null;
	}

	/**
	 * Return the version of the current JSS configuration. If the version is last
	 * it check for the last version from the JR plugin. 
	 * 
	 * @return a version number like x.y.z
	 */
	private String getCurrentVersion() {
		// assume last version as safe fall-back
		String ver = JaspersoftStudioPlugin.getInstance().getPreferenceStore().getString(StudioPreferencePage.JSS_COMPATIBILITY_VERSION);
		if (JRXmlWriterHelper.LAST_VERSION.equals(ver)) {
			return net.sf.jasperreports.engine.JasperCompileManager.class.getPackage().getImplementationVersion();
		} else {
			return ver;
		}
	}
	
	/**
	 * Compares two version strings. 
	 * 
	 * Use this instead of String.compareTo() for a non-lexicographical 
	 * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
	 * 
	 * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
	 * 
	 * @param str1 a string of ordinal numbers separated by decimal points. 
	 * @param str2 a string of ordinal numbers separated by decimal points.
	 * @return The result is a negative integer if str1 is _numerically_ less than str2. 
	 *         The result is a positive integer if str1 is _numerically_ greater than str2. 
	 *         The result is zero if the strings are _numerically_ equal.
	 */
	private Integer versionCompare(String str1, String str2)
	{
	    String[] vals1 = str1.split("\\."); //$NON-NLS-1$
	    String[] vals2 = str2.split("\\."); //$NON-NLS-1$
	    int i = 0;
	    // set index to first non-equal ordinal or length of shortest version string
	    while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) 
	    {
	      i++;
	    }
	    // compare first non-equal ordinal number
	    if (i < vals1.length && i < vals2.length) 
	    {
	        int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
	        return Integer.signum(diff);
	    }
	    // the strings are equal or one string is a substring of the other
	    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
	    else
	    {
	        return Integer.signum(vals1.length - vals2.length);
	    }
	}
}
