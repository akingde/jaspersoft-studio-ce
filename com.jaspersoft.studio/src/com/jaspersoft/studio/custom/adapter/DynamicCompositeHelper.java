/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.utils.VelocityUtils;

/**
 * Utility class with the method to create a panel for the configuration of a data adapter
 * using the castor serialization file of the data adapter itself
 * 
 * @author Orlandin Marco
 *
 */
public class DynamicCompositeHelper {

	/**
	 * Folder where the velocity templates are placed
	 */
	private static final String TEMPLATES_LOCATION_PREFIX = "com/jaspersoft/studio/custom/adapter/resources/dynamic/";
	
	/**
	 * Template for a text value control
	 */
	private static final String TEXT_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "TextValueControl.vm";
	
	/**
	 * Template for a boolean value control
	 */
	private static final String BOOLEAN_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "BooleanValueControl.vm";
	
	/**
	 * Template for a float value control
	 */
	private static final String FLOAT_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "FloatValueControl.vm";
	
	/**
	 * Template for a integer value control
	 */
	private static final String INTEGER_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "IntegerValueControl.vm";
	
	/**
	 * Template for a list value control
	 */
	private static final String LIST_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "ListValueControl.vm";
	
	/**
	 * Template the structure of the composite
	 */
	private static final String COMPOSITE_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "DynamicComposite.vm";
	
	/**
	 * Jar file of the data adapter
	 */
	private JarFile jarFile;
	
	/**
	 * Information container of the data adapter
	 */
	private AdapterInfo adapterInfo;
	
	/**
	 * The velocity engine
	 */
	private VelocityEngine ve = VelocityUtils.getConfiguredVelocityEngine();
	
	/**
	 * Create the helper for a data adapter
	 * 
	 * @param jarFile jar file of the data adapter
	 * @param adapterInfo information provided during the wizard by the user
	 */
	public DynamicCompositeHelper(File jarFile, AdapterInfo adapterInfo){
		try {
			this.jarFile = new JarFile(jarFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.adapterInfo = adapterInfo;
	}
	
	/**
	 * Uses the velocity engine and the template to generate the content
	 * of the Composite class. 
	 */
	public String getCompositeClass(){
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("packageName", adapterInfo.getPackageName());
		functionContext.put("adapterComposite", adapterInfo.getCompositeClassName());
		functionContext.put("descriptorName", adapterInfo.getDescriptorClassName());

		Template functionTemplate = ve.getTemplate(COMPOSITE_TEMPLATE_LOCATION);
		StringWriter fsw = new StringWriter();
		functionContext.put("controlCreationCode", createDynamicControls());
		functionTemplate.merge( functionContext, fsw );
		
		return fsw.toString();
	}
	
	/**
	 * Search inside the the jar of the data adapter for a jasper report extension
	 * properties file. Then load this file and search the property for the path
	 * of the castor mapping file of the data adapter. When the property is found
	 * it return the path of the castor file inside the jar
	 * 
	 * @return path of the castor mapping file inside the jar or null if it 
	 * can't be found
	 */
	protected String getXmlDefinitionLocation(){		
		 Properties props = new Properties();
		 try {
			ZipEntry propertiesFile = jarFile.getEntry("jasperreports_extension.properties");
			if (propertiesFile == null) throw new RuntimeException("The file jasperreports_extension.properties can't be found inside the selected JAR");
			props.load(jarFile.getInputStream(propertiesFile));
			for(Object property : props.keySet()){
				if (property.toString().startsWith("net.sf.jasperreports.extension.castor.mapping")){
					return props.getProperty(property.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Create the text to generate a control inside the composite template.
	 * The type of control change to reflect the type
	 * 
	 * @param type the type of control to create. Supported control are int, boolean, float and string
	 * @param bindName the name of the field binded to the control
	 * @param label the label of the control
	 * @return the text to place in the template to generate the control
	 */
	protected String createDynamicControl(String type, String bindName, String label){
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("controlLabel", label);
		functionContext.put("controlName", label+"Control");
		functionContext.put("bindName", bindName);
		StringWriter fsw = new StringWriter();
		if (type.equals("int")){
			Template functionTemplate = ve.getTemplate(INTEGER_TEMPLATE_LOCATION);
			functionTemplate.merge( functionContext, fsw );
		} else if (type.equals("boolean")){
			Template functionTemplate = ve.getTemplate(BOOLEAN_TEMPLATE_LOCATION);
			functionTemplate.merge( functionContext, fsw );
		} else if (type.equals("float")){
			Template functionTemplate = ve.getTemplate(FLOAT_TEMPLATE_LOCATION);
			functionTemplate.merge( functionContext, fsw );
		} else if (type.equals("string")){
			Template functionTemplate = ve.getTemplate(TEXT_TEMPLATE_LOCATION);
			functionTemplate.merge( functionContext, fsw );
		}
		
		return fsw.toString();
	}
	
	/**
	 * Generate the control to handle a list of vales
	 * 
	 * @param type the type of the values
	 * @param bindName the name of the collection field binded to this list
	 * @param label label of the list control
	 * @return the text to place in the template to generate the list
	 */
	protected String createDynamicArray(String type, String bindName, String label){
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("controlLabel", label);
		functionContext.put("controlName", label+"Control");
		functionContext.put("bindName", bindName);
		functionContext.put("elementType", type);
		StringWriter fsw = new StringWriter();
		Template functionTemplate = ve.getTemplate(LIST_TEMPLATE_LOCATION);
		functionTemplate.merge( functionContext, fsw );
		return fsw.toString();
	}
	
	/**
	 * Given a field node of the castor mapping file it return
	 * the first bind-xml node
	 * 
	 * @param parent the field node of the castor mapping 
	 * @return the first children bind-xml node
	 */
	private Node getBindNode(Node parent){
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeName().equals("bind-xml")) return child;
		}
		return null;
	}
	
	/**
	 * Read the content of a castor mapping file and create an appropriate
	 * control for every field inside the node list
	 * 
	 * @param fieldNodes list of the children of the root node of the castor mapping file
	 * @return string to place inside the composite to generate all the controls to edit the data adapter
	 */
	protected String createDynamicControls(NodeList fieldNodes){
		String result = "";
		for (int i = 0; i < fieldNodes.getLength(); ++i) {
			Node fieldNode = fieldNodes.item(i);
			if (fieldNode.getNodeName().equals("field")){
				String nameAttribute =  fieldNode.getAttributes().getNamedItem("name").getNodeValue();
				//name and class are handled by default
				if (!nameAttribute.equals("class") && !nameAttribute.equals("name")){
					String type = fieldNode.getAttributes().getNamedItem("type").getNodeValue();
					Node collectionNode = fieldNode.getAttributes().getNamedItem("collection");
					boolean isArray = collectionNode != null && collectionNode.getNodeValue().toLowerCase().equals("arraylist");
					Node bindNode = getBindNode(fieldNode);
					if (bindNode != null){
						String name = fieldNode.getAttributes().getNamedItem("name").getNodeValue();
						String bindName = bindNode.getAttributes().getNamedItem("name").getNodeValue();
						if (isArray){
							result += createDynamicArray(type, bindName, name);
						} else {
							result += createDynamicControl(type, bindName, name) + "\n";
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Search a castor mapping file inside the data adapter jar and if it is found create the controls
	 * to edit it
	 * 
	 * @return string to place inside the composite to generate all the controls to edit the data adapter
	 */
	protected String createDynamicControls() {
		String xmlDefinition = getXmlDefinitionLocation();
		if (xmlDefinition != null) {
			ZipEntry xmlFile = jarFile.getEntry(xmlDefinition);
			InputStream is = null;
			try {
				is = jarFile.getInputStream(xmlFile);
				if (null != is) {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					dbf.setValidating(false);
					dbf.setIgnoringComments(true);
					dbf.setNamespaceAware(false);
					DocumentBuilder builder = dbf.newDocumentBuilder();
					// Set the builder to pare the file without using a schema
					builder.setEntityResolver(new EntityResolver() {
						@Override
						public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
							if (systemId.contains("http://castor.org/mapping.dtd")) {
								return new InputSource(new StringReader(""));
							} else {
								return null;
							}
						}
					});

					Document document = builder.parse(is);
					Node mapNode = document.getDocumentElement();
					if (mapNode.getNodeName().equals("mapping")) {
						NodeList adapterNodes = mapNode.getChildNodes();
						for (int j = 0; j < adapterNodes.getLength(); ++j) {
							Node adapterNode = adapterNodes.item(j);
							if (adapterNode.getNodeName().equals("class")) {
								String result = createDynamicControls(adapterNode.getChildNodes());
								is.close();
								return result;
							}
						}
					}
				}
			} catch (Exception ex) {
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {
				}
				ex.printStackTrace();
			}
		}
		return "";
	}

}
