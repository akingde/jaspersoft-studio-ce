package com.jaspersoft.studio.property.color.chooser;

import org.eclipse.swt.graphics.RGB;

public interface IWidgetGovernor {

	public int getSliderMax();
	
	public int getSliderMin();
	
	public RGB getSliderColor(int x, int y, int sliderPosition);
	
	public int getPadMinX();
	
	public int getPadMinY();
	
	public int getPadMaxX();
	
	public int getPadMaxY();
	
	public RGB getPadColor(int x, int y, int sliderPosition);
	
	public int[] getXYSlider(Object color);
	
}
