/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRExpressionUtil;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Exporter used to import/export the composite elements definitions
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedCompositeElementsHandler implements IExportedResourceHandler {

	/**
	 * Filename used to store metadata of the exported resources
	 */
	private static final String INDEX_FILE_NAME = "index.properties";
	
	/**
	 * Cache when the list of exportable resource definition is requested, used to avoid multiple calculation
	 */
	private List<IResourceDefinition> cachedExportableResources = null;

	/**
	 * Cache when the list of importable resource definition is requested, used to avoid multiple calculation of the same container
	 */
	private Pair<String, List<IResourceDefinition>> cachedImportableResources = null;
	
	/**
	 * Name of the folder where the definition of the composite elements will be stored 
	 * in the exported container
	 */
	private static final String EXPORTED_FOLDER_NAME = "compositeElements"; //$NON-NLS-1$
	
	@Override
	public String getResourceNameExport() {
		int elementsNumber = CompositeElementManager.INSTANCE.getAvailableElements().size();
		return "Composite Elements (" + elementsNumber + ")"; //$NON-NLS-1$
	}

	@Override
	public String getResourceNameImport(File exportedContainer) {
		return "Composite Elements (" + getRestorableResources(exportedContainer).size() + ")"; //$NON-NLS-1$
	}
	
	
	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			cachedExportableResources = new ArrayList<IResourceDefinition>();
			for(MCompositeElement element : CompositeElementManager.INSTANCE.getAvailableElements()){
				BaseResource resource = new BaseResource(element.getName());
				resource.setData(element);
				cachedExportableResources.add(resource);
			}
		}
		return cachedExportableResources;
	}
	
	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		String containerPath = exportedContainer.getAbsolutePath();
		if (cachedImportableResources == null || 
				!cachedImportableResources.getKey().equals(containerPath)){
			
			List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
			File exportedFolder = new File(exportedContainer, EXPORTED_FOLDER_NAME);
			File indexFile = new File(exportedFolder, INDEX_FILE_NAME);
			if (indexFile.exists()){
				FileInputStream is = null;
				try{
					is = new FileInputStream(indexFile);
					Properties loadedProperties = new Properties();
					loadedProperties.load(is);
					for(Entry<Object, Object> entry : loadedProperties.entrySet()){
						BaseResource resource = new BaseResource(entry.getKey().toString());
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

	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {
		//Create the set of the resources that should be exported
		HashSet<MCompositeElement> resourcesToExportSet = new HashSet<MCompositeElement>();
		for(IResourceDefinition definition : resourcesToExport){
			resourcesToExportSet.add((MCompositeElement)definition.getData());
		}
		
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, EXPORTED_FOLDER_NAME);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		List<MCompositeElement> elementsToExport = new ArrayList<MCompositeElement>();
		Properties props = new Properties();	
		for(MCompositeElement element : CompositeElementManager.INSTANCE.getAvailableElements()){
			if (resourcesToExportSet.contains(element)) {
				elementsToExport.add(element);
				props.put(element.getName(), element.getName());
			}
		}
		CompositeElementManager.INSTANCE.exportCompositeElement(elementsToExport, destDir);
		
		//Write the index file
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(new File(destDir, INDEX_FILE_NAME));
			props.store(out, "Exported Elements Index");
		} catch (Exception ex){
			JaspersoftStudioPlugin.getInstance().logError(ex);
		} finally {
			FileUtils.closeStream(out);
		}
		return destDir;
	}
	
	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
		File elementsFolder = new File(exportedContainer, EXPORTED_FOLDER_NAME);
		if (elementsFolder.exists()){
			
			//Create the set of the files to import
			HashSet<String> filesToImport = new HashSet<String>();
			for(IResourceDefinition resourceToImport : resourcesToImport){
				filesToImport.add(resourceToImport.getData().toString());
			}
			
			List<MCompositeElement> elements = CompositeElementManager.INSTANCE.loadCompositeElements(elementsFolder);
			HashMap<String, MCompositeElement> existingElements = new HashMap<String, MCompositeElement>();
			for(MCompositeElement storedElement : CompositeElementManager.INSTANCE.getAvailableElements()){
				existingElements.put(storedElement.getName(), storedElement);
			}
			List<String> duplicatedElementsName = new ArrayList<String>();
			for(MCompositeElement element :  elements){
				if (existingElements.containsKey(element.getName())){
					duplicatedElementsName.add(element.getName());
				}
			}
			
			RESPONSE_TYPE response = RESPONSE_TYPE.KEEP_BOTH;
			if (duplicatedElementsName.size() > 0){
				response = askOverwrite(duplicatedElementsName);
			}
			
			for(MCompositeElement element :  elements){
				String name = element.getName();
				if (filesToImport.contains(element.getName())){
					if (existingElements.containsKey(name)){
						if (response == RESPONSE_TYPE.KEEP_BOTH){
							String newName = getName(existingElements, element.getName());
							MCompositeElement renamedElement = new MCompositeElement(newName, element.getDescription(), element.getGroupId(), 
																																					element.getPath(), element.getIconPathSmall(), element.getIconPathBig());
							addCompositeElement(renamedElement);
						} else if (response == RESPONSE_TYPE.OVERWRITE){
							MCompositeElement oldElement = existingElements.get(element.getName());
							CompositeElementManager.INSTANCE.deleteCompositeElement(oldElement);
							addCompositeElement(element);
						}
					} else {
						addCompositeElement(element);
					}
				}
			}
		}
	}
	
	/**
	 * Add a composite element to the current configuration
	 * 
	 * @param element the element to add, must be not null
	 */
	private void addCompositeElement(MCompositeElement element){
		ImageData imageSmall = null;
		if (element.getIconSmall() != null){
			imageSmall = element.getIconSmall().getImageData();
		}
		ImageData imageBig = null;
		if (element.getIconBig() != null){
			imageBig = element.getIconBig().getImageData();
		}
		
		InputStream in = null;
		JasperDesign result = null;
		try {
			File componentFile = new File(element.getPath());
			File resourceFolder = element.getResourceFolder();
			in = new ByteArrayInputStream(org.apache.commons.io.FileUtils.readFileToByteArray(componentFile));
			JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
			result = new JRXmlLoader(jConfig, JRXmlDigesterFactory.createDigester(jConfig)).loadXML(in);
			checkResources(result.getTitle().getChildren(), resourceFolder, element.getName());
			jConfig.setJasperDesign(result);
			//Add the composite element to the set
			CompositeElementManager.INSTANCE.addCompositeElement(element.getName(), element.getDescription(), element.getGroupId(), 
																														imageSmall, imageBig, result, resourceFolder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			net.sf.jasperreports.eclipse.util.FileUtils.closeStream(in);
		}
	}

	
	/**
	 * Check recursively all the resources need by the elements inside the imported composite element. At the moment
	 * the only searched resources are the images. When a resource is found then its expression is changed
	 * to point to the resource folder that will be inside the composite elements storage. This is done because
	 * when an element is exported it will have absolute path that could be not valid once imported, so they
	 * need to be updated
	 * 
	 * @param children the elements to check recursively
	 * @param currentResourcesLocation the folder where the resource of the exported element could be located, typically this is
	 * a temp folder
	 * @param the new element name once it is imported, this will be also the name of its resource folder inside the storage
	 */
	private void checkResources(List<JRChild> children, File currentResourcesLocation, String elementNewName){
		for(JRChild newElement : children){
			if (newElement instanceof JRDesignImage){
				JRExpression exp = ((JRDesignImage)newElement).getExpression();
				if (exp != null){
					String textualExp = JRExpressionUtil.getSimpleExpressionText(exp);
					File oldResource = new File(textualExp);
					//Check if the resource is in the resource folder and in case update the expression
					if (new File(currentResourcesLocation, oldResource.getName()).exists()){
						File newResourceFolder = CompositeElementManager.INSTANCE.getResourceDir(elementNewName);
						File newResource = new File(newResourceFolder, oldResource.getName());
						((JRDesignImage)newElement).setExpression(new JRDesignExpression("\"" + newResource.getAbsolutePath() + "\"")); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
			if (newElement instanceof JRElementGroup){
				checkResources(((JRElementGroup)newElement).getChildren(), currentResourcesLocation, elementNewName);
			}
		}
	}
	
	/**
	 * Show a question dialog with the buttons overwrite, skip and  keep both,
	 * no and cancel. It is executed in the graphic thread so this method dosen't need to be called
	 * inside a the display thread.
	 *
	 * @param elementsName the list of the overlapping names, this will be used to build the message and show
	 * which elements are overlapping, must be not null
	 * @return a not null enum that can be Overwrite, Skip or Keep Both
	 */
	private RESPONSE_TYPE askOverwrite(List<String> elementsName) {
		String baseMessage = Messages.ExportedCompositeElementsHandler_overlappingMessage;
		StringBuilder message = new StringBuilder("\n");
		int index = 1;
		for(String elementName : elementsName){
			message.append(elementName);
			message.append(index == elementsName.size() ? ".\n" : ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
			index ++;
		}
		String composedMessage = MessageFormat.format(baseMessage, new Object[]{message.toString()});
		return RunnableOverwriteQuestion.showQuestion(Messages.ExportedCompositeElementsHandler_overlappingTitle, composedMessage);
	}	
	
	/**
	 * Return an unique name for the imported resource, not already used by the others
	 * 
	 * @param existingElements the existing resources, the search of the name will be test against this map, must be
	 * not null
	 * @param baseName the base name used into the search
	 * @return a not null unique name for the new resource
	 */
	private String getName(HashMap<String, MCompositeElement> existingElements, String baseName){
		int index = 1;
		String newName = baseName + "_" + index; //$NON-NLS-1$
		while(existingElements.containsKey(newName)){
			index++;
			newName = baseName + "_" + index; //$NON-NLS-1$
		}
		return newName;
	}
}
