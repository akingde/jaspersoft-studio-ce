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
package com.jaspersoft.studio.data.storage;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
	
	/**
	 * The properties file
	 */
	private Preferences prefs;

	/**
	 * Here are saved all the TemplateStyle read from the properties file
	 */
	protected Map<String, TemplateStyle> styleDescriptors;
	
	/**
	 * A map with all the registered type of styles
	 */
	private static Map<String, TemplateStyle> availableStyles = null;
	
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
		if (availableStyles == null){
			availableStyles = new HashMap<String, TemplateStyle>();
			for (TemplateViewProvider e : JaspersoftStudioPlugin.getExtensionManager().getStylesViewProvider()) {
					TemplateStyle builder = e.getBuilder();
					availableStyles.put(builder.getTemplateName(), builder);
			}
		}
		return availableStyles.get(className);
	}

	/**
	 * Add a template style to the properties file
	 * 
	 * @param style the Template style to add
	 */
	public void addStyle(TemplateStyle style) {
		String name = style.toString();
		if (styleDescriptors.containsKey(name)) {
			styleDescriptors.remove(name);
		}
		styleDescriptors.put(name, style);
		save();
	}
	
	/**
	 * Remove a style from the properties file
	 * 
	 * @param style the Template style to remove
	 */
	public void removeStyle(TemplateStyle style) {
		String name = style.toString();
		if (styleDescriptors.containsKey(name)) {
			styleDescriptors.remove(name);
		}
		save();
	}
	
	/**
	 * Read all the styles from the properties file
	 */
	private void findAll() {
		try {
			String xml = prefs.get(PREF_KEYS_STYLES, null);
			if (xml != null) {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));

				NodeList adapterNodes = document.getDocumentElement().getChildNodes();
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);
					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						// 1. Find out the class of this styles...
						String className = adapterNode.getAttributes().getNamedItem("type").getNodeValue(); //$NON-NLS-1$
						TemplateStyle factory = getBuilder(className); 
						if (factory != null){
							TemplateStyle readStyle = factory.buildFromXML(adapterNode);
							styleDescriptors.put(readStyle.toString(), readStyle);
						}
					}
				}

			} else {
				prefs.put(PREF_KEYS_STYLES, "<templateStyles></templateStyles>");
				prefs.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return a list of all the template style read from the properties file
	 * 
	 * @return
	 */
	public Collection<TemplateStyle> getStylesDescriptors() {
		if (styleDescriptors == null) {
			styleDescriptors = new LinkedHashMap<String, TemplateStyle>();
			findAll();
		}
		return styleDescriptors.values();
	}
	
	/**
	 * Save all the styles in the map into the properties file
	 */
	private void save() {
		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<templateStyles>"); //$NON-NLS-1$
			for (TemplateStyle desc : getStylesDescriptors()) {
				try {
					xml.append(desc.getXMLData());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			xml.append("</templateStyles>"); 
			prefs.put("templateStyles", xml.toString());
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

}
