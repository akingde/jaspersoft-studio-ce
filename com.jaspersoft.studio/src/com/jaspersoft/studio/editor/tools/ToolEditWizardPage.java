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

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

/**
 * Page used to edit the metadata of a tool
 * 
 * @author Orlandin Marco
 *
 */
public class ToolEditWizardPage extends ToolDefinitionWizardPage {

	/**
	 * The current name of the tool
	 */
	private String initialName;
	
	/**
	 * The current description of the tool
	 */
	private String initialDescription;
	
	/**
	 * The current image of the tool
	 */
	private String initialImagePath = "";
	
	public ToolEditWizardPage(String initialName, String initialDescription, String initialImagePath){
		this.initialDescription = initialDescription;
		this.initialName = initialName;
		
		File image = new File(initialImagePath);
		//If exist export the image to the temp directory
		if (image.exists()){
			File tmpDir = new File(System.getProperty("java.io.tmpdir")); 
			File destFile = new File(tmpDir, image.getName());
			try{
				FileUtils.copyFile(image, destFile);
			} catch (Exception ex){
				ex.printStackTrace();
			}
			destFile.deleteOnExit();
			this.initialImagePath = destFile.getAbsolutePath();
		}
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		nameText.setText(initialName);
		descriptionText.setText(initialDescription);
		iconPathText.setText(initialImagePath);
		widgetsModfied.modifyText(null);
	}
	
	/**
	 * Check if the page is complete, the page is completed 
	 * if name, description and icon are not empty and if the name is 
	 * unique
	 */
	@Override
	public boolean isPageComplete() {
		String name = getName().trim();
		if (name.isEmpty()){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameEmpry);	
		} else if (!net.sf.jasperreports.eclipse.util.FileUtils.isFilenameValid(name)){
			setErrorMessage(Messages.ToolDefinitionWizardPage_invalidFileName);
		} else if (!initialName.equals(name) && ToolManager.INSTANCE.isNameAlreadyUsed(name.trim())){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameUsed);
		} else {
			setErrorMessage(null);
			setDescription(Messages.ToolDefinitionWizardPage_description);
		}
		return getErrorMessage() == null;
	}
	
}
