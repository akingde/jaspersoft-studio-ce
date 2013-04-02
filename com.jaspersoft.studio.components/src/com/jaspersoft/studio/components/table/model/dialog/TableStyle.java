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

package com.jaspersoft.studio.components.table.model.dialog;

import java.awt.Color;

import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;

/**
 * 
 * This class specialize the TemplateStyle to handle the table. Essentially this is done 
 * to provide standard key to access the data and some conversion method.
 * 
 * @author Orlandin Marco
 *
 */
public class TableStyle extends TemplateStyle {

	/**
	 * Key for the border style attribute
	 */
	public final static String BORDER_STYLE_KEY = "border_style";
	
	/**
	 * Key for the border color attribute
	 */
	public final static String BORDER_COLOR_KEY = "border_corlor";
	
	/**
	 * Key for the boolean attribute that identify if the color of the detail row
	 * is alternated
	 */
	public final static String ALTERNATE_COLOR_KEY = "alternate_corlor";
	
	/**
	 * Key for the color detail attribute, it will be used only if the attribute identified 
	 * by ALTERNATE_COLOR_KEY is true
	 */
	public final static String COLOR_DETAIL = "color_detail";
	
	/**
	 * Key for the color of the column header and footer attributes
	 */
	public final static String COLOR_COL_HEADER = "color_column_header";
	
	/**
	 * Key for the color of the table header and footer attributes
	 */
	public final static String COLOR_TABLE_HEADER = "color_table_header";
	
	/**
	 * Enumeration for the available type of borders for the table 
	 * 
	 * FULL: the table have both horizontal and vertical borders
	 * 
	 * PARTIAL_VERTICAL: like FULL but without the vertical border ad the vertical edges of the table
	 * 
	 * ONLY_HORIZONTAL: the table has only horizontal borders
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public static enum BorderStyleEnum {FULL, PARTIAL_VERTICAL, ONLY_HORIZONTAL};
	
	/**
	 * Create an instance of the class
	 * 
	 * @param baseColor base color that will be used to color the cells
	 * @param variation key of the variation of the color
	 * @param borderStyle style of the border
	 * @param borderColor color of the border
	 * @param altenrateColor true if the color of the detail are alternated, false otherwise
	 */
	public TableStyle(RGB baseColor, ColorSchemaGenerator.SCHEMAS variation, BorderStyleEnum borderStyle, RGB borderColor, boolean altenrateColor) {
		super(baseColor, variation);
		setBorderStyle(borderStyle);
		setAlternateRowColor(altenrateColor);
		setBorderColor(borderColor);
		generateAndStoreColor(COLOR_COL_HEADER, 2);
		generateAndStoreColor(COLOR_TABLE_HEADER, 3);
		Color detail = getColorValue(COLOR_TABLE_HEADER);
		detail = ColorSchemaGenerator.overlayWhite(detail);
		detail = ColorSchemaGenerator.overlayWhite(detail);
		storeColor (COLOR_DETAIL, new RGB(detail.getRed(), detail.getGreen(), detail.getBlue()));
	}
	
	/**
	 * Set the border style
	 * 
	 * @param value the style of the borders of the table
	 */
	public void setBorderStyle(BorderStyleEnum value){
		storePropertiy(BORDER_STYLE_KEY, value);
	}
	
	/**
	 * get the border of the table
	 * 
	 * @return return the enumeration value that rapresent the style of the borders
	 */
	public BorderStyleEnum getBorderStyle(){
		return (BorderStyleEnum)getProperty(BORDER_STYLE_KEY);
	}
	
	/**
	 * Set the borders color
	 * 
	 * @param value an SWT RGB color
	 */
	public void setBorderColor(RGB value){
		storeColor(BORDER_COLOR_KEY, value);
	}
	
	/**
	 * Return the borders color
	 * 
	 * @return an AWT color
	 */
	public Color getBorderColor(){
		RGB rgbColor =  super.getColor(BORDER_COLOR_KEY);
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue);
	}
	
	/**
	 * Set if the color of the detail rows are alternated
	 * 
	 * @param value true if the color of the detail rows are alternated, 
	 * false otherwise
	 */
	public void setAlternateRowColor(boolean value){
		storePropertiy(ALTERNATE_COLOR_KEY, value);
	}
	
	/**
	 * Check if the color of the rows are alternated
	 * 
	 * @return true if the color of the detail rows are alternated, 
	 * false otherwise
	 */
	public boolean hasAlternateColor(){
		return (Boolean)getProperty(ALTERNATE_COLOR_KEY);
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
