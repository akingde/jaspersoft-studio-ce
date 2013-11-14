package com.jaspersoft.studio.utils;

import java.io.Serializable;

import org.eclipse.swt.graphics.RGB;

public class AlfaRGB implements Serializable{

	private static final long serialVersionUID = 1283053372093648788L;
	
	private RGB rgb;
	
	private int alfa;

	public AlfaRGB(RGB rgb, int alfa) {
		this.rgb = rgb;
		this.alfa = alfa;
	}

	public RGB getRgb() {
		return rgb;
	}

	public int getAlfa() {
		return alfa;
	}
	
	public static AlfaRGB getFullyOpaque(RGB rgb) {
		return (rgb!=null) ? new AlfaRGB(rgb, 255) : null;
	}
	
	public static AlfaRGB getFullyTransparent(RGB rgb) {
		return (rgb!=null) ? new AlfaRGB(rgb, 0) : null;
	}
	
	public static RGB safeGetRGB(AlfaRGB argb) {
		return (argb!=null) ? argb.getRgb() : null;
	}
}
