/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.style.view.TemplateViewProvider;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.util.JRXmlUtils;

/**
 * Class to read, load and save the template styles from the file storage.
 * For each type of template it uses a different storage
 * 
 * @author Orlandin Marco
 *
 */
public class PreferencesTemplateStylesStorage {
	
	/**
	 * Key of the property of the template styles stored in the properties file,
	 * used before the file storage. This is keep only for back compatibility and
	 * for convert the old storage configuration to the new one
	 */
	private static final String PREF_KEYS_STYLES = "templateStyles";
	
	/**
	 * Property raised when something in the storage changes
	 */
	public static final String PROPERTY_CHANGE_NAME = "TEMPLATESTYLES";

	/**
	 * Here are saved all the TemplateStyle read from the properties file, splitted by their type
	 */
	protected Map<String, List<TemplateStyle>> styleDescriptors;
	
	/**
	 * A map with all the registered type of styles
	 */
	private static Map<String, TemplateStyle> availableStyles = null;
	
	/**
	 * The notifier of the property changes
	 */
	private PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(JaspersoftStudioPlugin.getInstance());
	
	/**
	 * Name of the id property of the style. It include a random number to be easily an unique
	 * property name
	 */
	private final static String STYLE_ID = "STYLE_ID6358649593550007203L";

	/**
	 * Return and empty TemplateStyle that can be used to build a real instance from the XML of the style. The association 
	 * of an XML style with a TemplateStyle instance is done using the method getTemplateName() on the instance and the attribute
	 * type on the XML
	 */
	private TemplateStyle getBuilder(String className){
		initAvailableStyles();
		return availableStyles.get(className);
	}
	
	/**
	 * Search the extension point to cache the available styles builder, if necessary. 
	 * However after they are read then they are also cached
	 */
	private void initAvailableStyles(){
		if (availableStyles == null){
			availableStyles = new HashMap<String, TemplateStyle>();
			for (TemplateViewProvider e : JaspersoftStudioPlugin.getExtensionManager().getStylesViewProvider()) {
					TemplateStyle builder = e.getBuilder();
					availableStyles.put(builder.getTemplateName(), builder);
			}
		}
	}
	
	/**
	 * Return a list of strings where each string is type
	 * of template style that is supported, because there is builder for it. 
	 * 
	 * @return a not null list of supported type of template styles
	 */
	private String[] getAvailableStylesType(){
		initAvailableStyles();
		Set<String> types = availableStyles.keySet();
		return types.toArray(new String[types.size()]);
	}
	
	
	/**
	 * Add a change listener for the add, delete or edit of a style
	 * 
	 * @param propertyName
	 * @param listener
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * remove a change listener for the add, delete or edit of a style
	 * 
	 * @param propertyName
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.removePropertyChangeListener(listener);
	}


	/**
	 * Add a template style to the storage
	 * 
	 * @param style the Template style to add
	 */
	public void addStyle(TemplateStyle style) {
		File nodeStorage = ConfigurationManager.getStorage(style.getTemplateName());
		TemplateNameConverter converter = TemplateNameConverter.getConverter(style.getTemplateName());
		File xmlTargetFile = new File(nodeStorage, converter.getFileName(null));
		while(xmlTargetFile.exists()){
			xmlTargetFile = new File(nodeStorage, converter.getFileName(null));
		}
		style.storePropertiy(STYLE_ID, xmlTargetFile.getName());
		List<TemplateStyle> styles = styleDescriptors.get(style.getTemplateName());
		if (styles == null){
			styles = new ArrayList<TemplateStyle>();
			styleDescriptors.put(style.getTemplateName(), styles);
		} 
		styles.add(style);
		save(style, xmlTargetFile);
		propChangeSupport.firePropertyChange(PROPERTY_CHANGE_NAME, "ADD", style);
	}
	
	/**
	 * Search an template style inside a list of template styles. Two templates match
	 * when they have the same id
	 * 
	 * @param searchSet the list of template styles
	 * @param searchedStyle the searched template style
	 * @return the index of the searched template style in the list or -1 if it was not found
	 */
	private int getStyleIndex(List<TemplateStyle> searchSet, TemplateStyle searchedStyle){
		String id = (String)searchedStyle.getProperty(STYLE_ID);
		if (id != null){
			int currentIndex = 0;
			for(TemplateStyle style : searchSet){
				String currentId = (String)style.getProperty(STYLE_ID);
				if (id.equals(currentId)) return currentIndex;
				else currentIndex++;
			}
		}
		return -1;
	}
	
	/**
	 * Change the value of a style
	 * 
	 * @param style the Template style to add
	 */
	public void editStyle(TemplateStyle oldStyle, TemplateStyle newStyle) {
		String id = (String)oldStyle.getProperty(STYLE_ID);
		if (id != null){
			List<TemplateStyle> styles = styleDescriptors.get(oldStyle.getTemplateName());
			if (styles == null){
				styles = new ArrayList<TemplateStyle>();
				styleDescriptors.put(oldStyle.getTemplateName(), styles);
			} 
			int oldIndex = getStyleIndex(styles, oldStyle);
			if (oldIndex != -1){
				styles.set(oldIndex, newStyle);
			} else {
				styles.add(newStyle);
			}
			newStyle.storePropertiy(STYLE_ID, id);
			ConfigurationManager.removeStoregeResource(oldStyle.getTemplateName(), id);
			File replacement = new File(ConfigurationManager.getStorage(oldStyle.getTemplateName()), id);
			save(newStyle, replacement);
			propChangeSupport.firePropertyChange(PROPERTY_CHANGE_NAME, "EDIT", newStyle);
		}
	}
	
	/**
	 * Remove a style from the properties file
	 * 
	 * @param style the Template style to remove
	 */
	public void removeStyle(TemplateStyle style) {
		String id = (String)style.getProperty(STYLE_ID);
		if (id != null) {
			List<TemplateStyle> styles = styleDescriptors.get(style.getTemplateName());
			if (styles == null){
				styles = new ArrayList<TemplateStyle>();
				styleDescriptors.put(style.getTemplateName(), styles);
			} 
			int oldIndex = getStyleIndex(styles, style);
			if (oldIndex != -1){
				styles.remove(oldIndex);
			}
			ConfigurationManager.removeStoregeResource(style.getTemplateName(), id);
			propChangeSupport.firePropertyChange(PROPERTY_CHANGE_NAME, "DELETE", style);
		}
	}
	
	/**
	 * Read all the styles from the properties file
	 */
	private void findAll() {
		doSilentConversion();
		for(String templateType : getAvailableStylesType()){
			File[] storageContent = ConfigurationManager.getStorageContent(templateType);
			TemplateStyle factory = getBuilder(templateType); 
			List<TemplateStyle> foundTemplates = styleDescriptors.get(templateType);
			if (foundTemplates == null){
				foundTemplates = new ArrayList<TemplateStyle>();
				styleDescriptors.put(templateType, foundTemplates);
			}
			if (factory != null){
				for(File storageElement : storageContent){
					try{
						InputStream inputStream = new FileInputStream(storageElement);
						Reader reader = new InputStreamReader(inputStream,FileUtils.UTF8_ENCODING);
						InputSource is = new InputSource(reader);
						is.setEncoding(FileUtils.UTF8_ENCODING);
						Document document = JRXmlUtils.parse(is);
						TemplateStyle readStyle = factory.buildFromXML(document.getDocumentElement());
						readStyle.storePropertiy(STYLE_ID, storageElement.getName());
						foundTemplates.add(readStyle);
					} catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * If necessary it convert the template styles in the old properties storage to be used
	 * inside the new file storage, then removes the old ones
	 */
	private void doSilentConversion(){
		try {
			Preferences prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
			String xml = prefs.get(PREF_KEYS_STYLES, null);
			if (xml != null) {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));
				NodeList adapterNodes = document.getDocumentElement().getChildNodes();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);
					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						String type = adapterNode.getAttributes().getNamedItem("type").getNodeValue(); //$NON-NLS-1$
						File nodeStorage = ConfigurationManager.getStorage(type);
						DOMSource source = new DOMSource(adapterNode);
						//Create the converter to get the name
						TemplateNameConverter converter = TemplateNameConverter.getConverter(type);
						File xmlTargetFile = new File(nodeStorage, converter.getFileName(adapterNode));
						while(xmlTargetFile.exists()){
							xmlTargetFile = new File(nodeStorage, converter.getFileName(adapterNode));
						}
						StreamResult result = new StreamResult(xmlTargetFile);
						transformer.transform(source, result);
					}
				}
				prefs.remove(PREF_KEYS_STYLES);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return an unique file name for a template type
	 * 
	 * @param style the style template for which a name is searched
	 * @return a valid name for a file
	 */
	private String getUniqueId(TemplateStyle style){
		String type = style.getTemplateName();
		File nodeStorage = ConfigurationManager.getStorage(type);
		TemplateNameConverter converter = TemplateNameConverter.getConverter(type);
		File xmlTargetFile = new File(nodeStorage, converter.getFileName(null));
		while(xmlTargetFile.exists()){
			xmlTargetFile = new File(nodeStorage, converter.getFileName(null));
		}
		return xmlTargetFile.getName();
	}
	
	/**
	 * Read a list of templates from an xml file
	 * 
	 * @param xml the content of the xml
	 * @return a not null list of the styles read from the template.
	 */
	public List<TemplateStyle> readTemplateFromFile(String xml) {
		List<TemplateStyle> result = new ArrayList<TemplateStyle>();
		if (xml != null) {
			ByteArrayInputStream inputStream = null;
			try {
				inputStream = new ByteArrayInputStream(xml.getBytes());
				DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder builder = documentFactory.newDocumentBuilder();
				Document document = builder.parse(inputStream);
				NodeList adapterNodes = document.getDocumentElement().getChildNodes();
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);
					if (adapterNode.getNodeType() == Node.ELEMENT_NODE && adapterNode.getAttributes().getNamedItem("type")!=null) {
						// 1. Find out the class of this styles...
						String className = adapterNode.getAttributes().getNamedItem("type").getNodeValue(); //$NON-NLS-1$
						TemplateStyle factory = getBuilder(className); 
						if (factory != null){
							TemplateStyle readStyle = factory.buildFromXML(adapterNode);
							readStyle.storePropertiy(STYLE_ID, getUniqueId(readStyle));
							result.add(readStyle);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				FileUtils.closeStream(inputStream);
			}
		} 
		return result;
	}

	/**
	 * Return a list of all the template style read from the properties file
	 * 
	 * @param templateType the requested type
	 * @return a list of all the template styles for a specific type, can be null.
	 */
	public Collection<TemplateStyle> getStylesDescriptors(String templateType) {
		if (styleDescriptors == null) {
			styleDescriptors = new LinkedHashMap<String, List<TemplateStyle>>();
			findAll();
		}
		return styleDescriptors.get(templateType);
	}
	
	/**
	 * Save a template style inside the storage inside a specific file
	 * 
	 * @param template the template to save
	 * @param destination the file where the template must be saved
	 */
	private void save(TemplateStyle template, File destination) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(template.getXMLData())));
			// Write the parsed document to an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			FileOutputStream stream = new FileOutputStream(destination);
			StreamResult result = new StreamResult(stream);
			transformer.transform(source, result);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
