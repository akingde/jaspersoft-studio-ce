/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools.wizards;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
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

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Wizard used to import one or more composite element
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementImportWizard extends Wizard {

	/**
	 * The composite elements to import
	 */
	private List<MCompositeElement> elementsToImport;
	
	/**
	 * The pages where the informations will be provided and can be edited
	 */
	private List<CompositeElementImportWizardPage> pages = new ArrayList<CompositeElementImportWizardPage>();
	
	/**
	 * Create the wizard
	 * 
	 * @param the composite elements to import, must be not null
	 */
	public CompositeElementImportWizard(List<MCompositeElement> elementsToImport) {
		this.elementsToImport = elementsToImport;
	}
	
	@Override
	public void addPages() {
		int currentElement = 1;
		for(MCompositeElement elementToImport : elementsToImport){	
			CompositeElementImportWizardPage currentPage = 
						new CompositeElementImportWizardPage(elementToImport, currentElement, elementsToImport.size());
			pages.add(currentPage);
			addPage(currentPage);
			currentElement++;
		}
	}
	
	/**
	 * Import every of the selected composite element
	 */
	@Override
	public boolean performFinish() {
		for(CompositeElementImportWizardPage page0 : pages){
			String name = page0.getName();
			String iconPath = page0.getIconPath();
			String groupID = page0.getGroupID();
			String description = page0.getElementDescription();
			if (description == null || description.trim().isEmpty()){
				description = ""; //$NON-NLS-1$
			}
			
			FileInputStream stream = null;
			try{
				//Load the images and create the small and big variants
				ImageData resized16 = null;
				ImageData resized32 = null;
				if (iconPath != null){
					File iconFile = new File(iconPath);
					if (iconFile.exists()){
						//Use the icon only if the file exist, otherwise will use null that will display a default icon
						stream = new FileInputStream(new File(iconPath));
						Image loadedImage = new Image(null, new FileInputStream(new File(iconPath)));
						resized16 = ImageUtils.resizeKeepingRatio(16, loadedImage);
						resized32 = ImageUtils.resizeKeepingRatio(32, loadedImage);
						loadedImage.dispose();	
					}
				}
					
				InputStream in = null;
				JasperDesign result = null;
				try {
					File componentFile = new File(page0.getElementToImport().getPath());
					File resourceFolder = page0.getElementToImport().getResourceFolder();
					in = new ByteArrayInputStream(FileUtils.readFileToByteArray(componentFile));
					JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
					result = new JRXmlLoader(jConfig, JRXmlDigesterFactory.createDigester(jConfig)).loadXML(in);
					checkResources(result.getTitle().getChildren(), resourceFolder, name);
					jConfig.setJasperDesign(result);
					//Add the composite element to the set
					CompositeElementManager.INSTANCE.addCompositeElement(name, description, groupID, resized16, resized32, result, resourceFolder);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					net.sf.jasperreports.eclipse.util.FileUtils.closeStream(in);
				}
			} catch (Exception ex){
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
				UIUtils.showError(ex);
			} finally{
				net.sf.jasperreports.eclipse.util.FileUtils.closeStream(stream);
			}
		}
		return true;
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
	
	
}
