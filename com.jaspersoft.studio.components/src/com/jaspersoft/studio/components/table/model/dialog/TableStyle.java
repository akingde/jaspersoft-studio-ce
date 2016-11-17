/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.dialog;

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

import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator.SCHEMAS;
import com.jaspersoft.studio.style.view.TemplateStyleView;
import com.jaspersoft.studio.utils.AlfaRGB;

import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;

/**
 * 
 * This class specialize the TemplateStyle to handle the table. Essentially this is done 
 * to provide standard key to access the data and some conversion method.
 * 
 * @author Orlandin Marco
 *
 */
public class TableStyle extends TemplateStyle implements IExportedResourceHandler{
	
	/**
	 * The type of this template
	 */
	public static final String TEMPLATE_TYPE = "tableStyle";
	
	/**
	 * id for serialization
	 */
	private static final long serialVersionUID = -6611750741729597106L;

	/**
	 * Key for the border style attribute
	 */
	public final static String BORDER_STYLE_KEY = "border_style";
	
	/**
	 * Key for the border color attribute
	 */
	public final static String BORDER_COLOR_KEY = "border_corlor";
	
	/**
	 * Key for the boolean attribute that identify if the color of the detail row
	 * is alternated
	 */
	public final static String ALTERNATE_COLOR_KEY = "alternate_corlor";
	
	/**
	 * Key for the color detail attribute, it will be used only if the attribute identified 
	 * by ALTERNATE_COLOR_KEY is true
	 */
	public final static String COLOR_DETAIL = "color_detail";
	
	/**
	 * Key for the color detail cells, overridden from the COLOR_DETAIL on the odd cell when ALTERNATE_COLOR_KEY
	 * is true
	 */
	public final static String STANDARD_COLOR_DETAIL = "color_detail_standard";
	
	/**
	 * Key for the color of the column header and footer attributes
	 */
	public final static String COLOR_COL_HEADER = "color_column_header";
	
	/**
	 * Key for the color of the table header and footer attributes
	 */
	public final static String COLOR_TABLE_HEADER = "color_table_header";
	
	/**
	 * Enumeration for the available type of borders for the table 
	 * 
	 * FULL: the table have both horizontal and vertical borders
	 * 
	 * PARTIAL_VERTICAL: like FULL but without the vertical border ad the vertical edges of the table
	 * 
	 * ONLY_HORIZONTAL: the table has only horizontal borders
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public static enum BorderStyleEnum {FULL, PARTIAL_VERTICAL, ONLY_HORIZONTAL};
	
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
	
	/**
	 * Create an instance of the class
	 * 
	 * @param baseColor base color that will be used to color the cells
	 * @param variation key of the variation of the color
	 * @param borderStyle style of the border
	 * @param borderColor color of the border
	 * @param altenrateColor true if the color of the detail are alternated, false otherwise
	 */
	public TableStyle(AlfaRGB baseColor, ColorSchemaGenerator.SCHEMAS variation, BorderStyleEnum borderStyle, AlfaRGB borderColor, boolean altenrateColor) {
		super(baseColor, variation);
		setBorderStyle(borderStyle);
		setAlternateRowColor(altenrateColor);
		setBorderColor(borderColor);
		generateAndStoreColor(COLOR_COL_HEADER, 2);
		generateAndStoreColor(COLOR_TABLE_HEADER, 3);
		Color detail = getColorValue(COLOR_TABLE_HEADER);
		detail = ColorSchemaGenerator.overlayWhite(detail);
		detail = ColorSchemaGenerator.overlayWhite(detail);
		storeColor(COLOR_DETAIL, new AlfaRGB(new RGB(detail.getRed(), detail.getGreen(), detail.getBlue()), detail.getAlpha()));
		storeColor(STANDARD_COLOR_DETAIL, AlfaRGB.getFullyOpaque(new RGB(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue())));
	}
	
	/**
	 * Create an instance of the class
	 * 
	 * @param colorTHeader the color used in the table header cells
	 * @param colorCheader the color used in the column header cells
	 * @param colorDetail the color used in the detail cells
	 * @param colorAlternateDetail the color used in the detail cells, when the row is odd and the attribute alternate color is true
	 * @param borderStyle style of the border
	 * @param borderColor color of the border
	 * @param altenrateColor true if the color of the detail are alternated, false otherwise
	 */
	public TableStyle(AlfaRGB colorTHeader, AlfaRGB colorCheader, AlfaRGB colorDetail, AlfaRGB colorAlternateDetail, BorderStyleEnum borderStyle, AlfaRGB borderColor, boolean altenrateColor) {
		super(null, null);
		setBorderStyle(borderStyle);
		setAlternateRowColor(altenrateColor);
		setBorderColor(borderColor);
		storeColor(COLOR_COL_HEADER, colorCheader);
		storeColor(COLOR_TABLE_HEADER, colorTHeader);
		storeColor (COLOR_DETAIL, colorAlternateDetail);
		storeColor(STANDARD_COLOR_DETAIL, colorDetail);
	}
	
	/**
	 * Create an empty table style
	 */
	public TableStyle(){
		super(null,null);
	}
	
	/**
	 * Set the border style
	 * 
	 * @param value the style of the borders of the table
	 */
	public void setBorderStyle(BorderStyleEnum value){
		storePropertiy(BORDER_STYLE_KEY, value);
	}
	
	/**
	 * get the border of the table
	 * 
	 * @return return the enumeration value that represent the style of the borders
	 */
	public BorderStyleEnum getBorderStyle(){
		return (BorderStyleEnum)getProperty(BORDER_STYLE_KEY);
	}
	
	/**
	 * Set the borders color
	 * 
	 * @param value an SWT RGB color
	 */
	public void setBorderColor(AlfaRGB value){
		storeColor(BORDER_COLOR_KEY, value);
	}
	
	/**
	 * Return the borders color
	 * 
	 * @return an AWT color
	 */
	public Color getBorderColor(){
		AlfaRGB alfaColor =  super.getColor(BORDER_COLOR_KEY);
		RGB rgbColor = alfaColor.getRgb();
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue, alfaColor.getAlfa());
	}
	
	/**
	 * Return the borders color
	 * 
	 * @return an AWT color
	 */
	public AlfaRGB getRGBBorderColor(){
		AlfaRGB rgbColor =  super.getColor(BORDER_COLOR_KEY);
		return rgbColor;
	}
	
	/**
	 * Set if the color of the detail rows are alternated
	 * 
	 * @param value true if the color of the detail rows are alternated, 
	 * false otherwise
	 */
	public void setAlternateRowColor(boolean value){
		storePropertiy(ALTERNATE_COLOR_KEY, value);
	}
	
	/**
	 * Check if the color of the rows are alternated
	 * 
	 * @return true if the color of the detail rows are alternated, 
	 * false otherwise
	 */
	public Boolean hasAlternateColor(){
		return (Boolean)getProperty(ALTERNATE_COLOR_KEY);
	}
	
	/**
	 * Read a color properties and return it as an AWT color
	 * 
	 * @param name the name of the color properties
	 * @return the color read, in AWT.Color format
	 */
	public Color getColorValue(String name){
		AlfaRGB alfaColor =  super.getColor(name);
		RGB rgbColor = alfaColor.getRgb();
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue, alfaColor.getAlfa());
	}
	
	
	/**
	 * Return a string unique representation for the style
	 */
	@Override
	public String toString() {
		String color1 = getColor(COLOR_TABLE_HEADER).toString();
		String color2 = getColor(COLOR_COL_HEADER).toString();
		String color3 = getColor(STANDARD_COLOR_DETAIL).toString();
		String color4 = getColor(COLOR_DETAIL).toString();
		Boolean alternate = hasAlternateColor();
		String borderStyle = getBorderStyle().toString();
		return color1.concat(color2).concat(color3).concat(color4).concat(borderStyle)
				.concat(getRGBBorderColor().toString()).concat(alternate.toString());
	}
	
	/**
	 * Return an XML representation of the template style
	 * 
	 * @return a string containing the xml representation of the style
	 */
	@Override
	public String getXMLData() {
		String result = "<"+getTemplateName()+" type=\"" + getTemplateName() +"\" ";
		result += "alternateColor=\""+hasAlternateColor().toString(); // colorSchema=\"" + variation.name();
		result += "\" borderStyle=\""+getBorderStyle().name();
		result += "\"><description>".concat(getDescription()).concat("</description>");
		//result += xmlColor("baseColor", baseColor);
		result += xmlColor("borderColor", getRGBBorderColor());
		result += xmlColor("tHeaderColor",getColor(COLOR_TABLE_HEADER));
		result += xmlColor("cHeaderColor",getColor(COLOR_COL_HEADER));
		result += xmlColor("detailColor",getColor(STANDARD_COLOR_DETAIL));
		result += xmlColor("altDetailColor",getColor(COLOR_DETAIL));
		result += "</"+getTemplateName()+">";
		return result;
	}
	
	/**
	 * Rebuild a TableStyle from its XML representation
	 * 
	 * @param xmlNode an XML node with the representation of a TableStyle
	 * @return the TemplateStyle builded from the xmlNode, or null if something goes wrong during the rebuilding
	 */
	@Override
	public TemplateStyle buildFromXML(Node xmlNode) {
		try{
			NamedNodeMap rootAttributes = xmlNode.getAttributes();
			boolean alternateColor = rootAttributes.getNamedItem("alternateColor").getNodeValue().equals("true"); 
			
			Node schemasNode = rootAttributes.getNamedItem("colorSchema");
			SCHEMAS variation =  schemasNode != null ? SCHEMAS.valueOf(schemasNode.getNodeValue()) : null;
			BorderStyleEnum borderStyle = BorderStyleEnum.valueOf(rootAttributes.getNamedItem("borderStyle").getNodeValue());
			Node firstChild = xmlNode.getFirstChild();
			String description = null;
			AlfaRGB baseColor = null;
			AlfaRGB borderColor = null;
			AlfaRGB colorTHeader = null;
			AlfaRGB colorCHeader = null;
			AlfaRGB colorDetail = null;
			AlfaRGB colorAlternateDetail = null;
			while(firstChild!=null){
				if (firstChild.getNodeName().equals("baseColor")){
					baseColor = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("borderColor")){
					borderColor = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("description")) {
					Node descriptionNode = firstChild.getChildNodes().item(0);
					description = descriptionNode != null ? descriptionNode.getNodeValue() : "";		
				} else if (firstChild.getNodeName().equals("tHeaderColor")) {
					colorTHeader = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("cHeaderColor")) {
					colorCHeader = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("detailColor")) {
					colorDetail = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("altDetailColor")) {
					colorAlternateDetail = rgbColor(firstChild);
				}
				firstChild = firstChild.getNextSibling();
			}
			TableStyle result = null;
			if (variation != null && baseColor != null) result = new TableStyle(baseColor, variation, borderStyle, borderColor, alternateColor);
			else result =  new TableStyle(colorTHeader, colorCHeader, colorDetail, colorAlternateDetail, borderStyle, borderColor, alternateColor);
			result.setDescription(description);
			return result;
		} catch(Exception ex){
			System.out.println("Unable to rebuild the table style");
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Return an unique identifier of the table template type
	 * 
	 * @return a string representing the type of the table template
	 */
	@Override
	public String getTemplateName() {
		return TEMPLATE_TYPE;
	}

	/**
	 * Return the name of the exported resource for the table styles
	 * 
	 * @return a not null and fixed string
	 */
	@Override
	public String getResourceNameExport() {
		Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(TableStyle.TEMPLATE_TYPE);
		return "Table Styles (" + styles.size() + ")";
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
		//Load the styles from the exported folder
		List<TemplateStyle> loadedStyles = new ArrayList<TemplateStyle>();
		File exportedFolder = new File(exportedContainer, TableStyle.TEMPLATE_TYPE);
		for(File styleDefinition : exportedFolder.listFiles()){
			try{
				String xml = FileUtils.readFileAsAString(styleDefinition);
				List<TemplateStyle> fileStyles = TemplateStyleView.getTemplateStylesStorage().readTemplateFromFile(xml);
				loadedStyles.addAll(fileStyles);
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return "Table Styles (" + loadedStyles.size() + ")";
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
		File destDir = new File (tempDir, TableStyle.TEMPLATE_TYPE);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		
		//Create the set of the resources that should be exported
		HashSet<TemplateStyle> resourcesToExportSet = new HashSet<TemplateStyle>();
		for(IResourceDefinition definition : resourcesToExport){
			resourcesToExportSet.add((TemplateStyle)definition.getData());
		}
		
		//Convert the styles handled by this class into a single xml
		Properties props = new Properties();
		Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(TableStyle.TEMPLATE_TYPE);
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
			OutputStream file = new FileOutputStream(new File(destDir, "exportedStyles.xml"));  //$NON-NLS-1$
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
			props.store(out, "Exported Table Styles Index");
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
		File exportedFolder = new File(exportedContainer, TableStyle.TEMPLATE_TYPE);
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
		HashMap<String, TemplateStyle> existingStyles = getExistingStyles(TableStyle.TEMPLATE_TYPE);
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
	
			File exportedFolder = new File(exportedContainer, TableStyle.TEMPLATE_TYPE);
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
	 * @return true if there are at least one table template style, false otherwise
	 */
	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(TableStyle.TEMPLATE_TYPE);
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
		String baseMessage = Messages.TableStyle_overlappingMessage;
		StringBuilder message = new StringBuilder("\n");
		int index = 1;
		for(String adapter : stylesName){
			message.append(adapter);
			message.append(index == stylesName.size() ? ".\n" : ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
			index ++;
		}
		String composedMessage = MessageFormat.format(baseMessage, new Object[]{message.toString()});
		return RunnableOverwriteQuestion.showQuestion(Messages.TableStyle_overlappingTitle, composedMessage);
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
