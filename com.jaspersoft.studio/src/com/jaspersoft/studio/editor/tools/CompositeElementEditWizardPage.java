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
import com.jaspersoft.studio.plugin.PaletteGroup;

/**
 * Page used to edit the metadata of a composite element
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementEditWizardPage extends CompositeElementDefinitionWizardPage {

	/**
	 * The current name of the composite element
	 */
	private String initialName;
	
	/**
	 * The current description of the composite element
	 */
	private String initialDescription;
	
	/**
	 * The current palette group id of the composite element
	 */
	private String initialGroupId;
	
	/**
	 * The current image of the composite element
	 */
	private String initialImagePath = "";
	
	public CompositeElementEditWizardPage(String initialName, String initialDescription, String initialGroupId, String initialImagePath){
		this.initialDescription = initialDescription;
		this.initialName = initialName;
		this.initialGroupId = initialGroupId;

		if (initialImagePath != null){
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
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		nameText.setText(initialName);
		descriptionText.setText(initialDescription);
		iconPathText.setText(initialImagePath);
		
		int index = 0;
		for(PaletteGroup group : groups){
			if (group.getId().equals(initialGroupId)){
				break;
			}
			index++;
		}
		palettePosition.select(index);
		
		updateWidgets();
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
		} else if (!initialName.equals(name) && CompositeElementManager.INSTANCE.isNameAlreadyUsed(name.trim())){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameUsed);
		} else {
			setErrorMessage(null);
			setDescription(Messages.ToolDefinitionWizardPage_description);
		}
		return getErrorMessage() == null;
	}
	
}
