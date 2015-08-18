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
import java.io.FileNotFoundException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Wizard page to provide the metadata of a custom tool
 * 
 * @author Orlandin Marco
 *
 */
public class ToolDefinitionWizardPage extends JSSHelpWizardPage {

	/**
	 * The unique name of the new tool
	 */
	private String name = ""; //$NON-NLS-1$
	
	/**
	 * The description of the new tool
	 */
	private String toolDescription = ""; //$NON-NLS-1$
	
	/**
	 * The path to the icon of the new tool
	 */
	private String iconPath = ""; //$NON-NLS-1$
	
	/**
	 * Text area where the name of the tool is provided
	 */
	protected Text nameText;
	
	/**
	 * Text area where the description of the tool is provided
	 */
	protected Text descriptionText;
	
	/**
	 * Text area where the icon path of the tool is provided
	 */
	protected Text iconPathText;
	
	/**
	 * Text area where the currently loaded icon is shown
	 */
	private Label iconPreview;
	
	/**
	 * The last loaded image as icon for the tool. It is keep a reference
	 * to dispose it when it is no more necessary
	 */
	private Image lastLoadedImage = null;
	
	/**
	 * Modify listener called when one of the textual control changes, update
	 * the field and eventually if the icon path is changed then it reload the image
	 */
	protected ModifyListener widgetsModfied = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			name = nameText.getText();
			toolDescription = descriptionText.getText();
			if (!iconPath.equals(iconPathText.getText())){
				iconPath = iconPathText.getText();
				try{ 
					loadIconSample();
				} catch (Exception ex){
					//if something goes wrong hide the preview area
					iconPreview.setImage(null);
					GridData iconLabelData = new GridData();
					iconLabelData.exclude = true;
					iconPreview.setLayoutData(iconLabelData);
					iconPreview.getParent().layout(true, true);
				}
			}
			getWizard().getContainer().updateButtons();
			
		}
	};
	
	/**
	 * Create the wizard page
	 */
	protected ToolDefinitionWizardPage() {
		super("tooldefinitionwizard"); //$NON-NLS-1$
		setTitle(Messages.ToolDefinitionWizardPage_title);
		setDescription(Messages.ToolDefinitionWizardPage_description);
	}

	protected void createIconArea(Composite container){
			//Create the controls for the image
			Composite imageContainer = new Composite(container, SWT.NONE);
			imageContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout imageContainerLayout = new GridLayout(3, false);
			imageContainerLayout.marginHeight = 0;
			imageContainerLayout.marginWidth = 0;
			imageContainer.setLayout(imageContainerLayout);
			iconPathText = new Text(imageContainer, SWT.BORDER);
			iconPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iconPreview = new Label(imageContainer, SWT.NONE);
			GridData previewData = new GridData();
			previewData.exclude = true;
			iconPreview.setLayoutData(previewData);
			
			Button selectImageButton = new Button(imageContainer, SWT.PUSH);
			selectImageButton.setText(Messages.common_browse);
			selectImageButton.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e) {
				   	FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN);
		        fd.setText(Messages.common_open);
		        String[] filterExt = { "*.jpg", "*.png", ".gif" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if (selected != null){
		        	iconPathText.setText(selected);
		        }
				}
			});
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		//Create the name and description text area
		new Label(container, SWT.NONE).setText(Messages.ToolDefinitionWizardPage_titleLabel);
		nameText = new Text(container, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		nameText.setText(getFirstAvailableName());
		
		new Label(container, SWT.NONE).setText(Messages.ToolDefinitionWizardPage_descriptionLabel);
		descriptionText = new Text(container, SWT.BORDER);
		descriptionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		descriptionText.setText(Messages.ToolDefinitionWizardPage_defaultDescription);
		new Label(container, SWT.NONE).setText(Messages.ToolDefinitionWizardPage_iconLabel);
		
		createIconArea(container);
		
		//Add the listeners
		nameText.addModifyListener(widgetsModfied);
		descriptionText.addModifyListener(widgetsModfied);
		iconPathText.addModifyListener(widgetsModfied);
		
		//force the update of the text field
		widgetsModfied.modifyText(null);
		
		setControl(container);
	}
	
	/**
	 * Check if the page is complete, the page is completed 
	 * if name, description and icon are not empty and if the name is 
	 * unique
	 */
	@Override
	public boolean isPageComplete() {
		if (name.trim().isEmpty()){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameEmpry);	
		} else if (!FileUtils.isFilenameValid(name)){
			setErrorMessage(Messages.ToolDefinitionWizardPage_invalidFileName);
		} else if (ToolManager.INSTANCE.isNameAlreadyUsed(name.trim())){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameUsed);
		} else {
			setErrorMessage(null);
			setDescription(Messages.ToolDefinitionWizardPage_description);
		}
		return getErrorMessage() == null;
	}
	
	/**
	 * Search the first available name for a tool
	 * 
	 * @return a not null and unique name for a tool
	 */
	private String getFirstAvailableName(){
		String baseName= Messages.ToolDefinitionWizardPage_defaultName;
		String name = baseName;
		int counter = 1;
		while(ToolManager.INSTANCE.isNameAlreadyUsed(name)){
			name = baseName + " " + counter;  //$NON-NLS-1$
			counter ++;
		}
		return name;
	}
	
	/**
	 * Load the selected file as icon and show it the appropriate area
	 * 
	 * @throws FileNotFoundException throw if the icon file can not be found
	 */
	private void loadIconSample() throws FileNotFoundException{
		FileInputStream stream = null;
		//dispose first the last loaded image if available
		if (lastLoadedImage != null) lastLoadedImage.dispose();
		try{
			stream = new FileInputStream(new File(iconPath));
			Image loadedImage = new Image(null, stream);
			ImageData resized = ImageUtils.resizeKeepingRatio(16, loadedImage);
			loadedImage.dispose();
			FileUtils.closeStream(stream);
			lastLoadedImage = new Image(null, resized);
			iconPreview.setImage(lastLoadedImage);
			
			GridData iconLabelData = new GridData();
			iconLabelData.widthHint = resized.width;
			iconPreview.setLayoutData(iconLabelData);
	
			iconPreview.getParent().layout(true, true);
		} catch(FileNotFoundException ex){
			FileUtils.closeStream(stream);
			throw ex;
		}
	}
	
	/**
	 * When disposed it dispose also the last loaded image
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (lastLoadedImage != null){
			lastLoadedImage.dispose();
			lastLoadedImage = null;
		}
	}

	@Override
	protected String getContextName() {
		return null;
	}

	/**
	 * Return the name of the tool
	 * 
	 * @return a not null and not empty string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the description of the tool
	 * 
	 * @return a not null and not empty string
	 */
	public String getToolDescription() {
		return toolDescription;
	}

	/**
	 * Return the absolute icon path of the tool
	 * 
	 * @return a not null and valid absolute path
	 */
	public String getIconPath() {
		return iconPath;
	}

}
