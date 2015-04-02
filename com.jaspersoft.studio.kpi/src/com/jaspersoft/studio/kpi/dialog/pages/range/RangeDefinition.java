package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RangeDefinition{
	
	private static final HashMap<String, Color> colorNameMap = new HashMap<String, Color>();
	
	private static final List<String> availableNames = new ArrayList<String>();
	
	static{
		availableNames.add("good");
		colorNameMap.put(availableNames.get(availableNames.size()-1), Color.GREEN);
		availableNames.add("normal");
		colorNameMap.put(availableNames.get(availableNames.size()-1), Color.BLACK);
		availableNames.add("bad");
		colorNameMap.put(availableNames.get(availableNames.size()-1), Color.RED);
	}
	
	private int min;
	
	private int max;
	
	private String name;
	
	public RangeDefinition(int min, int max, String name){
		this.min = min;
		this.max = max;
		this.name = name;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	public String getName(){
		return name;
	}
	
	public static String  getHexColor(Color color){
		String hexColour = Integer.toHexString(color.getRGB() & 0xffffff);
		if (hexColour.length() < 6) {
			hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
		}
		return "#" + hexColour;
	}
	
	public static List<String> getNames(){
		return availableNames;
	}
}