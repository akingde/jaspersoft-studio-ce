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
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.defaults.CustomStyleResolver;
import com.jaspersoft.studio.model.MGraphicElement;
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
	 * Create a JasperDesign for the tool starting from the list of the elements
	 * that define it 
	 * 
	 * @param name the name of the tool
	 * @return a not null jasperdesign with the elements that define the tool in 
	 * the frist band of the detail section
	 */
	private JasperDesign createDesign(String name){
		Integer leftOffset = null;
		Integer topOffset = null;
		for(Object element : toolElements){
			MGraphicElement gElement = (MGraphicElement)element;
			int elementX = gElement.getValue().getX();
			int elementY = gElement.getValue().getY();
			if (leftOffset == null || leftOffset > elementX){
				leftOffset = elementX;
			} 
			if (topOffset == null || topOffset > elementY){
				topOffset = elementY;
			} 
		}
		JasperDesign jd = new JasperDesign();
		jd.setTitle(null);
		jd.setName(name);
		jd.setColumnFooter(null);
		jd.setColumnHeader(null);
		jd.setPageFooter(null);
		jd.setPageHeader(null);
		jd.setSummary(null);
		jd.setBackground(null);
		jd.setLeftMargin(0);
		jd.setRightMargin(0);
		jd.setTopMargin(0);
		jd.setBottomMargin(0);
		JRDesignBand band = new JRDesignBand();
		JRDesignSection detailSection = (JRDesignSection)jd.getDetailSection();
		for(Object element : toolElements){
			MGraphicElement mOriginalElement = (MGraphicElement) element;
			JRDesignElement originalElement = mOriginalElement.getValue();
			JRDesignElement newElement = (JRDesignElement)originalElement.clone();
			//Merge the styles attributes
			CustomStyleResolver.copyInheritedAttributes(mOriginalElement, newElement);
			newElement.setX(originalElement.getX()-leftOffset);
			newElement.setY(originalElement.getY()-topOffset);
			band.addElement(newElement);
		}
		detailSection.addBand(band);
		return jd;
	}
	
	/**
	 * Create the required resources from the provided metadata and use them to 
	 * request the add of the tool
	 */
	@Override
	public boolean performFinish() {
		String name = page0.getName();
		String iconPath = page0.getIconPath();
		String description = page0.getDescription();
		FileInputStream stream = null;
		try{
			//Load the images and create the small and big variants
			stream = new FileInputStream(new File(iconPath));
			Image loadedImage = new Image(null, new FileInputStream(new File(iconPath)));
			ImageData resized16 = ImageUtils.resizeKeepingRatio(16, loadedImage);
			ImageData resized32 = ImageUtils.resizeKeepingRatio(32, loadedImage);
			loadedImage.dispose();
			//Create the definition for the tool
			JasperDesign jd = createDesign(name);
			//Add the tool to the set
			ToolManager.INSTANCE.addTool(name, description, resized16, resized32, jd);
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
