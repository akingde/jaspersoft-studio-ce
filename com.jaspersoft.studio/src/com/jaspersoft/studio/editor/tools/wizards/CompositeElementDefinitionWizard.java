/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.utils.ImageUtils;

/**
 * Wizard used to create a new composite element and provide the metadata about it
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementDefinitionWizard extends Wizard {

	/**
	 * The page where the informations will be provided
	 */
	private CompositeElementDefinitionWizardPage page0;
	
	/**
	 * The selected elements that will compose the composite element
	 */
	private List<Object> elementContents;
	
	/**
	 * Create the wizard
	 * 
	 * @param elementContents The selected elements that will compose the composite element, must 
	 * be a not null list of MGraphicalElement
	 */
	public CompositeElementDefinitionWizard(List<Object> elementContents) {
		this.elementContents = elementContents;
	}
	
	@Override
	public void addPages() {
		page0 = new CompositeElementDefinitionWizardPage();
		addPage(page0);
	}
	
	/**
	 * Create the required resources from the provided metadata and use them to 
	 * request the add of the composite element
	 */
	@Override
	public boolean performFinish() {
		String name = page0.getName();
		String iconPath = page0.getIconPath();
		String description = page0.getElementDescription();
		String groupID = page0.getGroupID();
		if (description == null || description.trim().isEmpty()){
			description = "";
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
			//Add the composite element to the set
			CompositeElementManager.INSTANCE.addCompositeElement(name, description, groupID, resized16, resized32, elementContents);
		} catch (Exception ex){
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
			UIUtils.showError(ex);
		} finally{
			FileUtils.closeStream(stream);
		}
		return true;
	}
	
	

}
