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
package com.jaspersoft.studio.editor.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.tools.ToolModfiedListener.OPERATION_TYPE;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * The tool manager allow to load and save custom tools. Essentially the custom tools
 * are set of design elements with some properties already set inside. A custom tool
 * is so a report file, but to avoid to mess up with the file association it use a different
 * extension
 * 
 * @author Orlandin Marco
 *
 */
public class ToolManager {

	/**
	 * The Tool Manager is global and it is accessible only trough this class
	 */
	public static final ToolManager INSTANCE = new ToolManager();
	
	/**
	 * Storage name for the defined tools
	 */
	private static final String TOOL_KEY = "customTools"; //$NON-NLS-1$
	
	/**
	 * name of the xml file containing the definitions of all the tool saved, a definition of 
	 * each tool is composed by name, description, path of the report containing the tool itself
	 * and the path to the icons to use the palette
	 */
	private static final String TOOL_PROPERTIES_NAME = "index.xml"; //$NON-NLS-1$
	
	/**
	 * Attribute name for each tool entry in the xml file
	 */
	private static final String PROPERTY_NAME = "name"; //$NON-NLS-1$
	
	/**
	 * Attribute path of the icon (smaller one) for each tool entry in the xml file
	 */
	private static final String PROPERTY_ICON_SMALL = "icon_small"; //$NON-NLS-1$
	
	/**
	 * Attribute path of the icon (bigger one) for each tool entry in the xml file
	 */
	private static final String PROPERTY_ICON_BIG = "icon_big"; //$NON-NLS-1$
	
	/**
	 * Attribute description for each tool entry in the xml file
	 */
	private static final String PROPERTY_DESCRIPTION = "description"; //$NON-NLS-1$
	
	/**
	 * Attribute path of the report for each tool entry in the xml file
	 */
	private static final String PROPERTY_PATH = "path"; //$NON-NLS-1$
	
	/**
	 * Name of the tag for each tool entry in the xml file
	 */
	private static final String XML_TAG_NAME = "tool"; //$NON-NLS-1$
	
	/**
	 * List of the available tools
	 */
	private List<MCustomTool> availableTools = new ArrayList<MCustomTool>();
	
	/**
	 * Map to keep cached the tool definition once its jrxml is loaded. The key is 
	 * the path of the tool definition, the value is a band that contains all the 
	 * element defined by the tool
	 */
	private HashMap<String, JRBand> cachedToolsMap = new HashMap<String, JRBand>();
	
	
	/**
	 * List of listeners used to notify when the tools set changes
	 */
	private List<ToolModfiedListener> listeners = new ArrayList<ToolModfiedListener>();
	
	/**
	 * Constructor, since it's private the class can be only accessed by the INSTANCE method
	 */
	private ToolManager(){
		loadTools();
	};
	
	/**
	 * Return a list of all the available tools
	 * 
	 * @return a not null list of tools
	 */
	public List<MCustomTool> getAvailableTools(){
		return availableTools;
	}

	/**
	 * Check if a name for a tool is already used by another tool
	 * 
	 * @param name a not null name
	 * @return true if the name is already in use, false otherwise
	 */
	public boolean isNameAlreadyUsed(String name){
		for(MCustomTool tool : availableTools){
			if (tool.getName().equals(name)) return true;
		}
		return false;
	}
	
	/**
	 * Delete a tool. This both remove all the physical of the tool resources from 
	 * the disk and its entry on the index file. It also notify the listeners that the
	 * set of tools is changed
	 * 
	 * @param toolToRemove a not null model of the tool to remove
	 */
	public void deleteTool(MCustomTool toolToRemove){
		cachedToolsMap.remove(toolToRemove.getPath());
		availableTools.remove(toolToRemove);

		//Update the index file
		File indexFile = ConfigurationManager.getStorageResource(TOOL_KEY, TOOL_PROPERTIES_NAME);
		if (indexFile.exists()){
			try{
				String xmlContent = readFile(indexFile);
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));
				NodeList adapterNodes = document.getElementsByTagName(XML_TAG_NAME);
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					try{
						Node adapterNode = adapterNodes.item(i);
						if (adapterNode.getNodeType() == Node.ELEMENT_NODE && adapterNode.getAttributes().getNamedItem(PROPERTY_NAME)!=null) {
							String name = adapterNode.getAttributes().getNamedItem(PROPERTY_NAME).getNodeValue(); 
							if (name.equals(toolToRemove.getName())){
								document.getDocumentElement().removeChild(adapterNode);
								break;
							}
						}
					}catch(Exception ex){
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
				indexFile.delete();
				if (adapterNodes.getLength() > 0){
					//Write the file only if there s at least an entry
					indexFile.createNewFile();
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(document);
					StreamResult result = new StreamResult(new FileOutputStream(indexFile));
					indexFile.createNewFile();
					transformer.transform(source, result);
				}
				deleteToolResources(toolToRemove);
			}catch(Exception ex){
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		} else {
			//Index file not available, delete the resources if they are found
			deleteToolResources(toolToRemove);
		}
		//Notify the change to the listeners
		firePropertyChange(toolToRemove, OPERATION_TYPE.DELETE);
	}
	
	/**
	 * Add a new tool to the set and notify the listeners that the set of tools is changed
	 * 
	 * @param name the name of the tool, must be unique
	 * @param description the description of the tool
	 * @param iconSmall the small icon for the tool, typically 16x16
	 * @param iconBig the big icon for the tool, typically 32x32
	 * @param jd the jasperdesign containing the element of the tool. The element must be contained
	 * in the first band of the detail section
	 */
	public void addTool(String name, String description, ImageData iconSmall, ImageData iconBig, JasperDesign jd){
		Assert.isTrue(!isNameAlreadyUsed(name), "The name must be unique");
		File storage = ConfigurationManager.getStorage(TOOL_KEY);	
		try{
			//Write the report on disk
			String reportName = name + ".jrtool"; //$NON-NLS-1$
			File reportFile = new File(storage, reportName);
			String contents = JRXmlWriterHelper.writeReport(JasperReportsConfiguration.getDefaultInstance(), jd, JRXmlWriterHelper.LAST_VERSION);
			BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile));
		  writer.write (contents);
		  writer.close();
			
		  //write the 16x16 icon
		  String imageSmallName = name+"-small.png"; //$NON-NLS-1$
		  if (iconSmall != null){
		  	writeImage(new File(storage, imageSmallName), iconSmall);
		  }
		  
		  //write the 32x32 icon
		  String imageBigName = name+"-big.png"; //$NON-NLS-1$
		  if (iconBig != null){
		  	writeImage(new File(storage, imageBigName), iconBig);
		  }
		  
			//Load the index file
			File indexFile = new File(storage, TOOL_PROPERTIES_NAME);
			Document document = null;
			Element root = null;
			if (indexFile.exists()){
				String xmlContent = readFile(indexFile);
				document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));
				root = document.getDocumentElement();
			} else {
				 //No index file defined, must create it
				 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				 DocumentBuilder builder = factory.newDocumentBuilder();
				 document = builder.newDocument();
				 root = document.createElement("tools"); //$NON-NLS-1$
				 document.appendChild(root);
			}
			
			//Add the new entry on the index
			FileOutputStream oStream = null;
			try{
				Element newNode = document.createElement(XML_TAG_NAME);
				newNode.setAttribute(PROPERTY_NAME, name);
				newNode.setAttribute(PROPERTY_PATH, reportName);
				newNode.setAttribute(PROPERTY_DESCRIPTION, description);
				if (iconSmall != null) {
					newNode.setAttribute(PROPERTY_ICON_SMALL, imageSmallName);
				}
				if (iconBig != null) {
					newNode.setAttribute(PROPERTY_ICON_BIG, imageBigName);
				}
				root.appendChild(newNode);
				indexFile.delete();
				//Write the file only if there s at least an entry
				indexFile.createNewFile();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				oStream = new FileOutputStream(indexFile); 
				StreamResult result = new StreamResult(oStream);
				indexFile.createNewFile();
				transformer.transform(source, result);
				MCustomTool newElement = createToolFromNode(newNode);
				availableTools.add(newElement);			
				firePropertyChange(newElement, OPERATION_TYPE.ADD);
			}catch(Exception ex){
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			} finally {
				net.sf.jasperreports.eclipse.util.FileUtils.closeStream(oStream);
			}
		} catch (Exception ex){
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
		}
	}
	
	/**
	 * Get the command used to create the requested tool inside the report
	 * 
	 * @param parent the parent of the tool
	 * @param tool the tool to create
	 * @param location the location where the tool should be created
	 * @param newIndex the index of the tool
	 * @return the command to create the tool inside the parent in the requested location
	 */
	public Command getCommand(ANode parent, MCustomTool tool, Rectangle location, int newIndex){
		JSSCompoundCommand cmd = new JSSCompoundCommand(parent){
			
			@Override
			public void execute() {
				//Before to execute the commands disable the use of defaults, since
				//the custom tools are not affected by defaults
				boolean defaultValue = DefaultManager.INSTANCE.isDisabled();
				//DefaultManager.INSTANCE.setDisabled(true);
				super.execute();
				DefaultManager.INSTANCE.setDisabled(defaultValue);
			}
			
		};
		JRBand toolContainer = getElementContainer(tool.getPath());
		for(JRChild child : toolContainer.getChildren()){
			if (child instanceof JRDesignElement){
				JRDesignElement designElement = (JRDesignElement)child;
				MGraphicElement model = new MGraphicElement();
				model.setValue(designElement.clone());
				Rectangle relativeLocation = new Rectangle(location);
				relativeLocation.x += designElement.getX();
				relativeLocation.y += designElement.getY();
				relativeLocation.width = designElement.getWidth();
				relativeLocation.height = designElement.getHeight();
				Command createCommand = OutlineTreeEditPartFactory.getCreateCommand(parent, model, relativeLocation, newIndex);
				if (createCommand != null){
					cmd.add(createCommand);
				}
			}
		}
		return cmd;
	}
	
	/**
	 * Add a listener that is notified when the toolset changes
	 * 
	 * @param listener a not null listener
	 */
	public void addModifyListener(ToolModfiedListener listener){
		if (!listeners.contains(listener)) listeners.add(listener);
	}
	
	/**
	 * Load the list of the tools the preferences storage. Called when the class
	 * is loaded
	 */
	private void loadTools(){
		availableTools.clear();
		File indexFile = ConfigurationManager.getStorageResource(TOOL_KEY, TOOL_PROPERTIES_NAME);
		if (indexFile != null && indexFile.exists()){
			try{
				String xmlContent = readFile(indexFile);
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xmlContent)));
			
				NodeList adapterNodes = document.getElementsByTagName(XML_TAG_NAME);
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					try{
						Node adapterNode = adapterNodes.item(i);
						if (adapterNode.getNodeType() == Node.ELEMENT_NODE && adapterNode.getAttributes().getNamedItem(PROPERTY_NAME)!=null) {					
							availableTools.add(createToolFromNode(adapterNode));			
						}
					}catch(Exception ex){
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		}
	}
	
	/**
	 * From a node of the xml index file it build an MCustomTool
	 * 
	 * @param adapterNode a not null node
	 * @return an MCustomTool build with the informations inside the node
	 */
	private MCustomTool createToolFromNode(Node adapterNode){
		String name = adapterNode.getAttributes().getNamedItem(PROPERTY_NAME).getNodeValue(); 
		String path = adapterNode.getAttributes().getNamedItem(PROPERTY_PATH).getNodeValue();
		File toolFile = ConfigurationManager.getStorageResource(TOOL_KEY, path);
		
		//Get the icons
		String absoluteIconPathSmall = null;
		String absoluteIconPathBig = null;
		Node iconNode =  adapterNode.getAttributes().getNamedItem(PROPERTY_ICON_SMALL);
		String iconPath = iconNode != null ? iconNode.getNodeValue() : null;
		if (iconPath != null){
			File resource = new File(ConfigurationManager.getStorage(TOOL_KEY), iconPath);
			absoluteIconPathSmall = resource.getAbsolutePath();
		}
		iconNode =  adapterNode.getAttributes().getNamedItem(PROPERTY_ICON_BIG);
		iconPath = iconNode != null ? iconNode.getNodeValue() : null;
		if (iconPath != null){
			File resource = new File(ConfigurationManager.getStorage(TOOL_KEY), iconPath);
			absoluteIconPathBig = resource.getAbsolutePath();
		}
		
		//get the description
		Node descriptionNode =  adapterNode.getAttributes().getNamedItem(PROPERTY_DESCRIPTION);
		String description = descriptionNode != null && descriptionNode.getNodeValue() != null ? descriptionNode.getNodeValue() : "A user defined custom tool";  //$NON-NLS-1$
		
		return new MCustomTool(name, description, toolFile.getAbsolutePath(), absoluteIconPathSmall, absoluteIconPathBig);			
	}
	
	/**
	 * Write an image data on the disk as a PNG image
	 * 
	 * @param destination the destination, must be not null
	 * @param image the image to write, must be not null
	 */
	private void writeImage(File destination, ImageData image){
		 ImageLoader loader = new ImageLoader();
	   loader.data = new ImageData[] {image};
	   loader.save(destination.getAbsolutePath(), SWT.IMAGE_PNG);
	}
	
	/**
	 * Delete all the physical resources of a tool, so it's images and 
	 * its report file
	 * 
	 * @param toolToRemove a not null tool to remove
	 */
	private void deleteToolResources(MCustomTool toolToRemove){
		//Delete the physical resources	
		File toolData = new File(toolToRemove.getPath());
		toolData.delete();
		
		if (toolToRemove.getIconPathSmall() != null){
			File iconSmall = new File(toolToRemove.getIconPathSmall());
			iconSmall.delete();
		}
		
		if (toolToRemove.getIconPathBig() != null){
			File iconBig = new File(toolToRemove.getIconPathBig());
			iconBig.delete();
		}
	}
	
	/**
	 * Read the xml source file and convert it into a string
	 * 
	 * @param path the path of the file
	 * @return the XML file
	 */
	private String readFile(File file){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
	
			while((line=br.readLine())!= null){
			    sb.append(line.trim());
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Load the report of a tool file and return the first band of the detail section,
	 * containing the component of the tool
	 * 
	 * @param defaultFile a not null report file containing the tool definition
	 */
	private JRBand loadToolModel(File defaultFile) {
		InputStream in = null;
		JRBand result = null;
		try {
			in = new ByteArrayInputStream(FileUtils.readFileToByteArray(defaultFile));
			JasperReportsConfiguration jConfig = getDefaultJRConfig();
			JasperDesign jd = new JRXmlLoader(jConfig, JRXmlDigesterFactory.createDigester(jConfig)).loadXML(in);
			jConfig.setJasperDesign(jd);
			result = jd.getDetailSection().getBands()[0];
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			net.sf.jasperreports.eclipse.util.FileUtils.closeStream(in);
		}
		return result;
	}
	
	/**
	 * Return the first band of the detail section of the tool definition
	 * identified by the provided path. If the band was already requested
	 * before and it is cached then it is returned from cache, otherwise it 
	 * is loaded, returned and cached
	 * 
	 * @param path the path of the tool definition file, must be not null
	 * @return the band containing the controls of the tool or null if there is 
	 * an error loading them
	 */
	private JRBand getElementContainer(String path){
		if (cachedToolsMap.containsKey(path)){
			return cachedToolsMap.get(path);
		} else {
			File toolFile = new File(path);
			if (toolFile.exists()){
				JRBand band = loadToolModel(toolFile);
				if (band != null){
					cachedToolsMap.put(path, band);
					return band;
				}
			}
		}
		return null;
	}
	
	/**
	 * Return the default JasperReports configuration 
	 * @return a not null jasperreports configuration
	 */
	private JasperReportsConfiguration getDefaultJRConfig() {
		return new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
	}
	
	/**
	 * Notify all the listeners that the set of tools is changed
	 * 
	 * @param newElement the element that has changed inside the toolset
	 * @param operation the operation on that element
	 */
	protected void firePropertyChange(MCustomTool newElement, OPERATION_TYPE operation){
		for(ToolModfiedListener listener : listeners){
			listener.toolChanged(newElement, operation);
		}
	}
}
