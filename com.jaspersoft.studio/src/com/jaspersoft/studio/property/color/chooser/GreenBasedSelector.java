package com.jaspersoft.studio.property.color.chooser;

import org.eclipse.swt.graphics.RGB;

public class GreenBasedSelector implements IWidgetGovernor{
	@Override
	public int getSliderMax() {
		return 255;
	}

	@Override
	public int getSliderMin() {
		return 0;
	}


	@Override
	public int getPadMinX() {
		return 0;
	}

	@Override
	public int getPadMinY() {
		return 0;
	}

	@Override
	public int getPadMaxX() {
		return 255;
	}

	@Override
	public int getPadMaxY() {
		return 255;
	}

	@Override
	public RGB getPadColor(int x, int y, int sliderPosition) {
		return new RGB(Math.abs(255-y),Math.abs(255-sliderPosition),x);
	}
	
	@Override
	public RGB getSliderColor(int x, int y, int sliderPosition) {
		return getPadColor(x, y, sliderPosition);
	}

	@Override
	public int getX(RGB color) {
		return color.blue;
	}

	@Override
	public int getY(RGB color) {
		return Math.abs(255-color.red);
	}

	@Override
	public int getSlider(RGB color) {
		return Math.abs(255-color.green);
	}

	@Override
	public int[] getXYSlider(RGB color) {
		return new int[]{getX(color), getY(color), getSlider(color)};
	}
	
}
