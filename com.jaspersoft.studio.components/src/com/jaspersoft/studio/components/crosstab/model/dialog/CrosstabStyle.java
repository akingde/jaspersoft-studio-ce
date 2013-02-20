package com.jaspersoft.studio.components.crosstab.model.dialog;

import java.awt.Color;

import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator.SCHEMAS;

public class CrosstabStyle extends TemplateStyle {

	private final static String WHITE_GRID = "white_grid";
	
	/**
	 * Key for the color detail attribute, it will be used only if the attribute identified 
	 * by ALTERNATE_COLOR_KEY is true
	 */
	public final static String COLOR_TOTAL = "color_total";
	
	/**
	 * Key for the color of the column header and footer attributes
	 */
	public final static String COLOR_COL_HEADER = "color_column_header";
	
	/**
	 * Key for the color of the table header and footer attributes
	 */
	public final static String COLOR_TABLE_HEADER = "color_table_header";
	
	public CrosstabStyle(RGB baseColor, SCHEMAS variation, boolean whiteGrid) {
		super(baseColor, variation);
		storePropertiy(WHITE_GRID, whiteGrid);
		generateAndStoreColor(COLOR_TOTAL, 1);
		generateAndStoreColor(COLOR_COL_HEADER, 2);
		generateAndStoreColor(COLOR_TABLE_HEADER, 3);
	}
	
	public boolean getWhiteGrid(){
		return (Boolean)getProperty(WHITE_GRID);
	}
	
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
