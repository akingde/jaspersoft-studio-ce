/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.dialog;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator.SCHEMAS;
import com.jaspersoft.studio.style.view.TemplateStyleView;
import com.jaspersoft.studio.utils.AlfaRGB;

import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;

/**
 * 
 * This class specialize the TemplateStyle to handle the crosstab element. 
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabStyle extends TemplateStyle implements IExportedResourceHandler{

	/**
	 * The type of this template
	 */
	public static final String TEMPLATE_TYPE = "crosstabStyle";
	
	/**
	 * id for serialization
	 */
	private static final long serialVersionUID = -2866538585051431701L;

	/**
	 * Key for the boolean attribute that specify when the grid is white
	 */
	private final static String WHITE_GRID = "white_grid";
	
	/**
	 * Key for the boolean attribute that specify to show or not the grid
	 */
	private final static String SHOW_GRID = "show_grid";
	
	/**
	 * Key for the total row and column color
	 */
	public final static String COLOR_TOTAL = "color_total";
	
	/**
	 * Key for the Color for the group row and column
	 */
	public final static String COLOR_GROUP = "color_group";
	
	/**
	 * Key for the detail cells color
	 */
	public final static String COLOR_DETAIL = "color_detail";
	
	/**
	 * Key for the color of the measure cells
	 */
	public final static String COLOR_MEASURES = "color_measures";
	
	/**
	 * Filename used to store metadata of the exported resources
	 */
	private static final String INDEX_FILE_NAME = "index.properties";
	
	/**
	 * The name of the file where the styles will be written
	 */
	private static final String STYLE_FILE_NAME= "exportedStyles.xml";
	
	/**
	 * Cache when the list of exportable resource definition is requested, used to avoid multiple calculation
	 */
	private List<IResourceDefinition> cachedExportableResources = null;

	/**
	 * Cache when the list of importable resource definition is requested, used to avoid multiple calculation of the same container
	 */
	private Pair<String, List<IResourceDefinition>> cachedImportableResources = null;
	
	public CrosstabStyle(AlfaRGB baseColor, SCHEMAS variation, boolean whiteGrid) {
		super(baseColor, variation);
		storePropertiy(WHITE_GRID, whiteGrid);
		storePropertiy(SHOW_GRID, true);
		generateAndStoreColor(COLOR_TOTAL, 1);
		generateAndStoreColor(COLOR_GROUP, 2);
		generateAndStoreColor(COLOR_MEASURES, 3);
		storeColor(COLOR_DETAIL, AlfaRGB.getFullyOpaque(ColorConstants.white.getRGB()));
	}
	
	public CrosstabStyle(AlfaRGB colorTotal, AlfaRGB colorGroup, AlfaRGB colorMeasures, AlfaRGB colorDetail, boolean whiteGrid) {
		super(null, null);
		storePropertiy(WHITE_GRID, whiteGrid);
		storePropertiy(SHOW_GRID, true);
		storeColor(COLOR_TOTAL, colorTotal);
		storeColor(COLOR_GROUP, colorGroup);
		storeColor(COLOR_MEASURES, colorMeasures);
		storeColor(COLOR_DETAIL, colorDetail);
	}
	
	
	public CrosstabStyle(){
		super(null,null);
	}
	
	/**
	 * Check if the crosstab has a white grid
	 * 
	 * @return true if the corsstab has a white grid, false otherwise
	 */
	public Boolean getWhiteGrid(){
		return (Boolean)getProperty(WHITE_GRID);
	}
	
	/**
	 * Check if the crosstab has to has show the grid
	 * 
	 * @return true if the corsstab has to show, false otherwise
	 */
	public Boolean isShowGrid(){
		return (Boolean)getProperty(SHOW_GRID);
	}
	
	/**
	 * Choose to show or not the grid
	 * 
	 * @param value true to show the grid, false otherwise
	 */
	public void setShowGrid(boolean value){
		storePropertiy(SHOW_GRID, value);
	}
	
	/**
	 * Set if the crosstab has a white grid
	 *  
	 * @param value true if the corsstab has a white grid, false otherwise
	 */
	public void setWhiteGrid(Boolean value){
		storePropertiy(WHITE_GRID, value);
	}
	
	/**
	 * Read a color properties and return it as an AWT color
	 * 
	 * @param name the name of the color properties
	 * @return the color read, in AWT.Color format
	 */
	public Color getColorValue(String name){
		AlfaRGB alphaColor =  super.getColor(name);
		RGB rgbColor =  alphaColor.getRgb();
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue, alphaColor.getAlfa());
	}
	
	/**
	 * Return a string unique representation for the crosstab style
	 */
	@Override
	public String toString() {
		String color1 = getColor(COLOR_TOTAL).toString();
		String color2 = getColor(COLOR_GROUP).toString();
		String color3 = getColor(COLOR_MEASURES).toString();
		String color4 = getColor(COLOR_DETAIL).toString();
		return color1.concat(color2).concat(color3).concat(color4).concat(getWhiteGrid().toString());
	}
	
	/**
	 * Return an XML representation of the crosstab style
	 * 
	 * @return a string containing the xml representation of the crosstab style
	 */
	@Override
	public String getXMLData() {
		String result = "<"+getTemplateName()+" type=\"" + getTemplateName() +"\" ";
		result += "whiteGrid=\""+getWhiteGrid().toString()+"\">";
		result += "<description>".concat(getDescription()).concat("</description>");
		result += xmlColor("colorTotal",getColor(COLOR_TOTAL));
		result += xmlColor("colorGroup",getColor(COLOR_GROUP));
		result += xmlColor("colorMeasures",getColor(COLOR_MEASURES));
		result += xmlColor("colorDetail",getColor(COLOR_DETAIL));
		result += "</"+getTemplateName()+">";
		return result;
	}


	/**
	 * Rebuild a CrosstabStyle from its XML representation
	 * 
	 * @param xmlNode an XML node with the representation of a CrosstabStyle
	 * @return the CrosstabStyle builded from the xmlNode, or null if something goes wrong during the rebuilding
	 */
	@Override
	public TemplateStyle buildFromXML(Node xmlNode) {
		try{
			NamedNodeMap rootAttributes = xmlNode.getAttributes();
			boolean whiteGrid = rootAttributes.getNamedItem("whiteGrid").getNodeValue().equals("true"); 
			
			Node schemasNode = rootAttributes.getNamedItem("colorSchema");
			SCHEMAS variation =  schemasNode != null ? SCHEMAS.valueOf(schemasNode.getNodeValue()) : null;
			
			Node firstChild = xmlNode.getFirstChild();
			String description = null;
			
			AlfaRGB baseColor = null;
			AlfaRGB colorTotal = null;
			AlfaRGB colorGroup = null;
			AlfaRGB colorMeasures = null;
			AlfaRGB colorDetail = null;
			while(firstChild!=null){
				if (firstChild.getNodeName().equals("baseColor")){
					baseColor = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("description")) {
					Node descriptionNode = firstChild.getChildNodes().item(0);
					description = descriptionNode != null ? descriptionNode.getNodeValue() : "";				
				} else if (firstChild.getNodeName().equals("colorTotal")) {
					colorTotal = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("colorGroup")) {
					colorGroup = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("colorMeasures")) {
					colorMeasures = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("colorDetail")) {
					colorDetail = rgbColor(firstChild);
				}
				firstChild = firstChild.getNextSibling();
			}
			CrosstabStyle result = null;
			if (variation != null && baseColor != null) result = new CrosstabStyle(baseColor, variation, whiteGrid);
			else result = new CrosstabStyle(colorTotal, colorGroup, colorMeasures, colorDetail, whiteGrid);
			result.setDescription(description);
			return result;
		} catch(Exception ex){
			System.out.println("Unable to rebuild the crosstab style");
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Return an unique identifier of the crosstab template type
	 * 
	 * @return a string representing the type of the crosstab template
	 */
	@Override
	public String getTemplateName() {
		return TEMPLATE_TYPE;
	}
	
	/**
	 * Return the name of the exported resource for the crosstab styles
	 * 
	 * @return a not null and fixed string
	 */
	@Override
	public String getResourceNameExport() {
		Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(CrosstabStyle.TEMPLATE_TYPE);
		return "Crosstab Styles (" + styles.size() + ")";
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
		//Load the styles from the exported folder
		List<TemplateStyle> loadedStyles = new ArrayList<TemplateStyle>();
		File exportedFolder = new File(exportedContainer, CrosstabStyle.TEMPLATE_TYPE);
		for(File styleDefinition : exportedFolder.listFiles()){
			try{
				String xml = FileUtils.readFileAsAString(styleDefinition);
				List<TemplateStyle> fileStyles = TemplateStyleView.getTemplateStylesStorage().readTemplateFromFile(xml);
				loadedStyles.addAll(fileStyles);
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return "Crosstab Styles (" + loadedStyles.size() + ")";
	}

	/**
	 * Backup all the styles of a specific type into a folder and return it.
	 * All the files are stored into a single xml file
	 * 
	 * @return a not null folder containing the backup of the styles
	 */
	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {
		//Create the temp folder
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, CrosstabStyle.TEMPLATE_TYPE);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();

		//Create the set of the resources that should be exported
		HashSet<TemplateStyle> resourcesToExportSet = new HashSet<TemplateStyle>();
		for(IResourceDefinition definition : resourcesToExport){
			resourcesToExportSet.add((TemplateStyle)definition.getData());
		}
		
		//Convert the styles handled by this class into a single xml
		Properties props = new Properties();
		Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(CrosstabStyle.TEMPLATE_TYPE);
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n<templateStyles>");  //$NON-NLS-1$
		int index = 0;
		for(TemplateStyle style : styles){
			if (resourcesToExportSet.contains(style)) {
				xmlBuffer.append(style.getXMLData());
				props.put(index, style.getDescription());
				index++;
			}
		}
		xmlBuffer.append("</templateStyles>"); //$NON-NLS-1$
		
		//Write the xml on the exportedfolder
		try {
			OutputStream file = new FileOutputStream(new File(destDir, STYLE_FILE_NAME));  //$NON-NLS-1$
			String xml = xmlBuffer.toString();
			file.write(xml.getBytes("UTF-8")); //$NON-NLS-1$
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Write the index file
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(new File(destDir, INDEX_FILE_NAME));
			props.store(out, "Exported Crosstab Styles Index");
		} catch (Exception ex){
			JaspersoftStudioPlugin.getInstance().logError(ex);
		} finally {
			FileUtils.closeStream(out);
		}
		return destDir;
	}

	/**
	 * Restore the selected styles from the content folder
	 * 
	 * @param exportedContainer a not null file containing all the resources exported 
	 * by the export procedure
	 */
	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
		
		//Create the set of the files to import
		HashSet<Integer> stylesToImport = new HashSet<Integer>();
		for(IResourceDefinition resourceToImport : resourcesToImport){
			stylesToImport.add((Integer)resourceToImport.getData());
		}
		
		//Load the styles from the exported folder
		List<TemplateStyle> loadedStyles = new ArrayList<TemplateStyle>();
		File exportedFolder = new File(exportedContainer, CrosstabStyle.TEMPLATE_TYPE);
		File exportedFile = new File(exportedFolder, STYLE_FILE_NAME);
		try{
			String xml = FileUtils.readFileAsAString(exportedFile);
			List<TemplateStyle> fileStyles = TemplateStyleView.getTemplateStylesStorage().readTemplateFromFile(xml);
			int index = 0;
			for(TemplateStyle style : fileStyles){
				if (stylesToImport.contains(index)){
					loadedStyles.add(style);
				}
				index++;
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		//Search the duplicated styles name
		HashMap<String, TemplateStyle> existingStyles = getExistingStyles(CrosstabStyle.TEMPLATE_TYPE);
		List<String> duplicatedStyles = new ArrayList<String>();
		for(TemplateStyle style : loadedStyles){
			if (existingStyles.containsKey(style.getDescription())){
				duplicatedStyles.add(style.getDescription());
			}
		}
		
		//If there are duplicate ask the user how to handle them
		RESPONSE_TYPE response = RESPONSE_TYPE.KEEP_BOTH;
		if (duplicatedStyles.size() > 0){
			response = askOverwrite(duplicatedStyles);
		}
		
		//Restore the imported style according to the user response for the duplicated
		for(TemplateStyle style : loadedStyles){
			String name = style.getDescription();
			if (existingStyles.containsKey(name)){
				if (response == RESPONSE_TYPE.KEEP_BOTH){
					style.setDescription(getName(existingStyles, name));
					TemplateStyleView.getTemplateStylesStorage().addStyle(style);
				} else if (response == RESPONSE_TYPE.OVERWRITE){
					TemplateStyleView.getTemplateStylesStorage().removeStyle(existingStyles.get(name));
					TemplateStyleView.getTemplateStylesStorage().addStyle(style);
				}
			} else {
				TemplateStyleView.getTemplateStylesStorage().addStyle(style);
			}
		}
	}

	/**
	 * Check if there are resource to import in the imported folder
	 * 
	 * @return true if there is something to import, false otherwise
	 */
	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		String containerPath = exportedContainer.getAbsolutePath();
		if (cachedImportableResources == null || 
				!cachedImportableResources.getKey().equals(containerPath)){
	
			File exportedFolder = new File(exportedContainer, CrosstabStyle.TEMPLATE_TYPE);
			File indexFile = new File(exportedFolder, INDEX_FILE_NAME);
			if (indexFile.exists()){
				FileInputStream is = null;
				try{
					List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
					is = new FileInputStream(indexFile);
					Properties loadedProperties = new Properties();
					loadedProperties.load(is);
					for(Entry<Object, Object> entry : loadedProperties.entrySet()){
						BaseResource resource = new BaseResource(entry.getValue().toString());
						resource.setData(entry.getKey());
						result.add(resource);
					}
					cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, result);
				} catch (Exception ex){ 
					JaspersoftStudioPlugin.getInstance().logError(ex);
					cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, new ArrayList<IResourceDefinition>());
				} finally {
					FileUtils.closeStream(is);
				}
			} else {
				cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, new ArrayList<IResourceDefinition>());
			}
		}
		return cachedImportableResources.getValue();
	}

	/**
	 * Check if there are styles of the current type 
	 * 
	 * @return true if there are at least one crosstab template style, false otherwise
	 */
	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(CrosstabStyle.TEMPLATE_TYPE);
			cachedExportableResources = new ArrayList<IResourceDefinition>();
			for(TemplateStyle style : styles){
				BaseResource resource = new BaseResource(style.getDescription());
				resource.setData(style);
				cachedExportableResources.add(resource);
			}
		}
		return cachedExportableResources;
	}
	
	/**
	 * Show a question dialog with the buttons overwrite, skip and  keep both,
	 * no and cancel. It is executed in the graphic thread so this method dosen't need to be called
	 * inside a the display thread.
	 *
	 * @param stylesName the list of the overlapping names, this will be used to build the message and show
	 * which elements are overlapping, must be not null
	 * @return a not null enum that can be Overwrite, Skip or Keep Both
	 */
	private RESPONSE_TYPE askOverwrite(List<String> stylesName) {
		String baseMessage = Messages.CrosstabStyle_overlappingMessage;
		StringBuilder message = new StringBuilder();
		int index = 1;
		for(String adapter : stylesName){
			message.append(adapter);
			message.append(index == stylesName.size() ? "" : "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			index ++;
		}
		String composedMessage = MessageFormat.format(baseMessage, new Object[]{message.toString()});
		return RunnableOverwriteQuestion.showQuestion(Messages.CrosstabStyle_overlappingTitle, composedMessage);
	}
	
	/**
	 * Return an unique name for the imported resource, not already used by the others
	 * 
	 * @param existingStyles the existing resources, the search of the name will be test against this map, must be
	 * not null
	 * @param baseName the base name used into the search
	 * @return a not null unique name for the new resource
	 */
	private String getName(HashMap<String, TemplateStyle> existingStyles, String baseName){
		int index = 1;
		String newName = baseName + "_" + index; //$NON-NLS-1$
		while(existingStyles.containsKey(newName)){
			index++;
			newName = baseName + "_" + index; //$NON-NLS-1$
		}
		return newName;
	}
}
