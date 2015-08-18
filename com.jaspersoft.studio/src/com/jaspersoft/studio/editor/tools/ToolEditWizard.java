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

import java.io.File;
import java.io.FileInputStream;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.ImageUtils;

/**
 * Wizard used to edit the metadata of an existing tool
 * 
 * @author Orlandin Marco
 *
 */
public class ToolEditWizard extends Wizard {

	/**
	 * The tool to edit
	 */
	private MCustomTool toolToEdit;
	
	/**
	 * The page where the informations will be provided
	 */
	private ToolEditWizardPage page0;
	
	/**
	 * Create the wizard
	 * 
	 * @param the tool to edit, must be not null
	 */
	public ToolEditWizard(MCustomTool toolToEdit) {
		this.toolToEdit = toolToEdit;
	}
	
	@Override
	public void addPages() {
		String imagePath = toolToEdit.getIconPathBig();
		if (imagePath == null) {
			imagePath = toolToEdit.getIconPathSmall();
		}
		page0 = new ToolEditWizardPage(toolToEdit.getName(), toolToEdit.getDescription(), imagePath);
		addPage(page0);
	}
	
	/**
	 * Update the metadata of the selected tool
	 */
	@Override
	public boolean performFinish() {
		String name = page0.getName();
		String iconPath = page0.getIconPath();
		String description = page0.getToolDescription();
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
			//Add the tool to the set
			ToolManager.INSTANCE.editTool(toolToEdit, name, description, resized16, resized32);
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
