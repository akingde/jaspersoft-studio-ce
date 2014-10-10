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
package com.jaspersoft.studio.data.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
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

import net.sf.jasperreports.engine.util.JRXmlUtils;

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

/**
 * Class to read, load and save the template styles from the proeprties file
 * 
 * @author Orlandin Marco
 *
 */
public class PreferencesTemplateStylesStorage {
	
	private static final String PREF_KEYS_STYLES = "templateStyles";
	
	public static final String PROPERTY_CHANGE_NAME = "TEMPLATESTYLES";
	
	/**
	 * The properties file
	 */
	private Preferences prefs;

	/**
	 * Here are saved all the TemplateStyle read from the properties file
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
	 * Name of the id property of the style. It include a random number to be easly an unique
	 * property name
	 */
	private final static String STYLE_ID = "STYLE_ID6358649593550007203L";

	/**
	 * Build the class and initialize the properties file
	 */
	public PreferencesTemplateStylesStorage() {
		prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
	}
	
	/**
	 * Return and empty TemplateStyle that can be used to build a real instance from the XML of the style. The association 
	 * of an XML style with a TemplateStyle instance is done using the method getTemplateName() on the instance and the attribute
	 * type on the XML
	 */
	private TemplateStyle getBuilder(String className){
		initAvailableStyles();
		return availableStyles.get(className);
	}
	
	private void initAvailableStyles(){
		if (availableStyles == null){
			availableStyles = new HashMap<String, TemplateStyle>();
			for (TemplateViewProvider e : JaspersoftStudioPlugin.getExtensionManager().getStylesViewProvider()) {
					TemplateStyle builder = e.getBuilder();
					availableStyles.put(builder.getTemplateName(), builder);
			}
		}
	}
	
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
	 * Add a template style to the properties file
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
	
	private void doSilentConversion(){
		try {
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
						Reader reader = new InputStreamReader(inputStream,"UTF-8");
						InputSource is = new InputSource(reader);
						is.setEncoding("UTF-8");
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
	
	public List<TemplateStyle> readTemplateFromFile(String xml) {
		List<TemplateStyle> result = new ArrayList<TemplateStyle>();
		try {
			if (xml != null) {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));
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

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Return a list of all the template style read from the properties file
	 * 
	 * @return
	 */
	public Collection<TemplateStyle> getStylesDescriptors(String templateType) {
		if (styleDescriptors == null) {
			styleDescriptors = new LinkedHashMap<String, List<TemplateStyle>>();
			findAll();
		}
		return styleDescriptors.get(templateType);
	}
	
	/**
	 * Save all the styles in the map into the properties file
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
			StreamResult result = new StreamResult(destination);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
