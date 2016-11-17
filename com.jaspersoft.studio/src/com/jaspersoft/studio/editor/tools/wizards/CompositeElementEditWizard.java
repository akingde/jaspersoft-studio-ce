/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools.wizards;

import java.io.File;
import java.io.FileInputStream;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.utils.ImageUtils;

/**
 * Wizard used to edit the metadata of an existing composite element
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementEditWizard extends Wizard {

	/**
	 * The composite element to edit
	 */
	private MCompositeElement elementToEdit;
	
	/**
	 * The page where the informations will be provided
	 */
	private CompositeElementEditWizardPage page0;
	
	/**
	 * Create the wizard
	 * 
	 * @param the composite element to edit, must be not null
	 */
	public CompositeElementEditWizard(MCompositeElement elementToEdit) {
		this.elementToEdit = elementToEdit;
	}
	
	@Override
	public void addPages() {
		String imagePath = elementToEdit.getIconPathBig();
		if (imagePath == null) {
			imagePath = elementToEdit.getIconPathSmall();
		}
		page0 = new CompositeElementEditWizardPage(elementToEdit.getName(), elementToEdit.getDescription(), elementToEdit.getGroupId(), imagePath);
		addPage(page0);
	}
	
	/**
	 * Update the metadata of the selected composite element
	 */
	@Override
	public boolean performFinish() {
		String name = page0.getName();
		String iconPath = page0.getIconPath();
		String groupID = page0.getGroupID();
		String description = page0.getElementDescription();
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
			CompositeElementManager.INSTANCE.editCompositeElement(elementToEdit, name, description, groupID, resized16, resized32);
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
