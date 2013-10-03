package com.jaspersoft.studio.translation;

import java.util.Locale;

import org.eclipse.swt.graphics.Image;

public class ImageLocale {
	
	private Locale locale;
	
	private Image image;
	
	public ImageLocale(Locale locale, Image image){
		this.locale = locale;
		this.image = image;
	}
	
	public Image getImage(){
		return image;
	}
	
	public Locale getLocale(){
		return locale;
	}
	
}
