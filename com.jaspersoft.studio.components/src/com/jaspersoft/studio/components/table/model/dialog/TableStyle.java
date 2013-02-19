package com.jaspersoft.studio.components.table.model.dialog;

import java.awt.Color;

import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.editor.style.TemplateStyle;

public class TableStyle extends TemplateStyle {

	public final static String BORDER_STYLE_KEY = "border_style";
	
	public final static String BORDER_COLOR_KEY = "border_corlor";
	
	public final static String ALTERNATE_COLOR_KEY = "alternate_corlor";
	
	public final static String GROUP_HEADER_KEY = "add_group_header";
	
	public final static String GROUP_FOOTER_KEY = "add_group_footer";
	
	public final static String TABLE_HEADER_KEY = "add_table_header";
	
	public final static String TABLE_FOOTER_KEY = "add_table_footer";
	
	public final static String COLUMN_HEADER_KEY = "add_column_header";
	
	public final static String COLUMN_FOOTER_KEY = "add_column_footer";
	
	public final static String COLOR1 = "color1";
	
	public final static String COLOR2 = "color2";
	
	public final static String COLOR3 = "color3";
	
	public TableStyle(RGB baseColor, String variation, int borderStyle, RGB borderColor, boolean altenrateColor) {
		super(baseColor, variation);
		setBorderStyle(borderStyle);
		setAlternateRowColor(altenrateColor);
		setBorderColor(borderColor);
		generateAndStoreColor(COLOR1, 1);
		generateAndStoreColor(COLOR2, 2);
		generateAndStoreColor(COLOR3, 3);
	}
	
	public void setBorderStyle(int value){
		storePropertiy(BORDER_STYLE_KEY, value);
	}
	
	public int getBorderStyle(){
		return (Integer)getProperty(BORDER_STYLE_KEY);
	}
	
	public void setBorderColor(RGB value){
		storeColor(BORDER_COLOR_KEY, value);
	}
	
	public Color getBorderColor(){
		RGB rgbColor =  super.getColor(BORDER_COLOR_KEY);
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue);
	}
	
	public void setAlternateRowColor(boolean value){
		storePropertiy(ALTERNATE_COLOR_KEY, value);
	}
	
	public boolean hasAlternateColor(){
		return (Boolean)getProperty(ALTERNATE_COLOR_KEY);
	}
	
	public Color getColorValue(String name){
		RGB rgbColor =  super.getColor(name);
		return new Color(rgbColor.red, rgbColor.green, rgbColor.blue);
	}

}
