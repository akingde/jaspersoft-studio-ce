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
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.ImageUtils;

/**
 * Wizard used to create a new tool and provide the metadata about it
 * 
 * @author Orlandin Marco
 *
 */
public class ToolDefinitionWizard extends Wizard {

	/**
	 * The page where the informations will be provided
	 */
	private ToolDefinitionWizardPage page0;
	
	/**
	 * The selected elements that will compose the tool
	 */
	private List<Object> toolElements;
	
	/**
	 * Create the wizard
	 * 
	 * @param toolElements The selected elements that will compose the tool, must 
	 * be a not null list of MGraphicalElement
	 */
	public ToolDefinitionWizard(List<Object> toolElements) {
		this.toolElements = toolElements;
	}
	
	@Override
	public void addPages() {
		page0 = new ToolDefinitionWizardPage();
		addPage(page0);
	}
	
	/**
	 * Create the required resources from the provided metadata and use them to 
	 * request the add of the tool
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
			ToolManager.INSTANCE.addTool(name, description, resized16, resized32, toolElements);
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
