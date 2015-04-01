package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.awt.Color;
import java.util.HashMap;


public class RangeDefinition{
	
	private static final HashMap<Color, String> colorNameMap = new HashMap<Color, String>();
	
	static{
		colorNameMap.put(Color.GREEN, "Good");
		colorNameMap.put(Color.YELLOW, "Normal");
		colorNameMap.put(Color.RED, "Bad");
	}
	
	private int min;
	
	private int max;
	
	private String color;
	
	private String name;
	
	public RangeDefinition(int min, int max, String color, String name){
		this.min = min;
		this.max = max;
		this.color = color;
		this.name = name;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public String getColor() {
		return color;
	}
	
	public String getName(){
		return name;
	}
	
	public static String getNameFromColor(Color color){
		String name = colorNameMap.get(color);
		if (name != null) return name;
		else return "Undefinded";
	}
	
	public static String  getHexColor(Color color){
		String hexColour = Integer.toHexString(color.getRGB() & 0xffffff);
		if (hexColour.length() < 6) {
			hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
		}
		return "#" + hexColour;
	}
}