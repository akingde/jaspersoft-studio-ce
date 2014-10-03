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

public class DynamicCompositeHelper {

	/**
	 * Folder where the velocity templates are placed
	 */
	private static final String TEMPLATES_LOCATION_PREFIX = "com/jaspersoft/studio/custom/adapter/resources/dynamic/";
	
	private static final String TEXT_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "TextValueControl.vm";
	
	private static final String BOOLEAN_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "BooleanValueControl.vm";
	
	private static final String FLOAT_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "FloatValueControl.vm";
	
	private static final String INTEGER_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "IntegerValueControl.vm";
	
	private static final String LIST_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "ListValueControl.vm";
	
	private static final String COMPOSITE_TEMPLATE_LOCATION = TEMPLATES_LOCATION_PREFIX + "DynamicComposite.vm";
	
	private JarFile jarFile;
	
	private AdapterInfo adapterInfo;
	
	/**
	 * The velocity engine
	 */
	private VelocityEngine ve = VelocityUtils.getConfiguredVelocityEngine();
	
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
	 * of the Activator class
	 * 
	 * @param adapterInfo information to put the parameter inside the template
	 * @return a textual representation of a .java file
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
	

	protected String getXmlDefinitionLocation(){		
		 Properties props = new Properties();
		 try {
			ZipEntry propertiesFile = jarFile.getEntry("jasperreports_extension.properties");
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
	
	private Node getBindNode(Node parent){
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeName().equals("bind-xml")) return child;
		}
		return null;
	}
	
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
