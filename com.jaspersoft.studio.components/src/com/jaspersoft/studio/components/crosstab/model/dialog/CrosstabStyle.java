/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.dialog;

import java.awt.Color;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator.SCHEMAS;

/**
 * 
 * This class specialize the TemplateStyle to handle the crosstab element. 
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabStyle extends TemplateStyle {

	/**
	 * Key for the boolean attribute that specify when the grid is white
	 */
	private final static String WHITE_GRID = "white_grid";
	
	/**
	 * Key for the boolean attribute that specify to show or not the grid
	 */
	private final static String SHOW_GRID = "show_grid";
	
	/**
	 * Key for the total row and column color
	 */
	public final static String COLOR_TOTAL = "color_total";
	
	/**
	 * Key for the Color for the group row and column
	 */
	public final static String COLOR_GROUP = "color_group";
	
	/**
	 * Key for the detail cells color
	 */
	public final static String COLOR_DETAIL = "color_detail";
	
	/**
	 * Key for the color of the measure cells
	 */
	public final static String COLOR_MEASURES = "color_measures";
	
	public CrosstabStyle(RGB baseColor, SCHEMAS variation, boolean whiteGrid) {
		super(baseColor, variation);
		storePropertiy(WHITE_GRID, whiteGrid);
		storePropertiy(SHOW_GRID, true);
		generateAndStoreColor(COLOR_TOTAL, 1);
		generateAndStoreColor(COLOR_GROUP, 2);
		generateAndStoreColor(COLOR_MEASURES, 3);
		storeColor(COLOR_DETAIL, ColorConstants.white.getRGB());
	}
	
	/**
	 * Check if the crosstab has a white grid
	 * 
	 * @return true if the corsstab has a white grid, false otherwise
	 */
	public boolean getWhiteGrid(){
		return (Boolean)getProperty(WHITE_GRID);
	}
	
	/**
	 * Check if the crosstab has to has show the grid
	 * 
	 * @return true if the corsstab has to show, false otherwise
	 */
	public boolean isShowGrid(){
		return (Boolean)getProperty(SHOW_GRID);
	}
	
	/**
	 * Choose to show or not the grid
	 * 
	 * @param value true to show the grid, false otherwise
	 */
	public void setShowGrid(boolean value){
		storePropertiy(SHOW_GRID, value);
	}
	
	/**
	 * Set if the crosstab has a white grid
	 *  
	 * @param value true if the corsstab has a white grid, false otherwise
	 */
	public void setWhiteGrid(Boolean value){
		storePropertiy(WHITE_GRID, value);
	}
	
	/**
	 * Read a color properties and return it as an AWT color
	 * 
	 * @param name the name of the color properties
	 * @return the color read, in AWT.Color format
	 */
	public Color getColorValue(String name){
		RGB rgbColor =  super.getColor(name);
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue);
	}

}
