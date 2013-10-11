package com.jaspersoft.studio.utils;

import org.eclipse.swt.graphics.RGB;

public class AlfaRGB {
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
}
