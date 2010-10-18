package com.jaspersoft.studio.barcode.model.barcode4j;

import net.sf.jasperreports.components.barcode4j.BarcodeComponent;

public class Orientation {
	public static String[] getItems() {
		return new String[] { "up", "left", "down", "right" };
	}

	public static int getPos4Orientation(int orientation) {
		switch (orientation) {
		case BarcodeComponent.ORIENTATION_UP:
			return 0;
		case BarcodeComponent.ORIENTATION_LEFT:
			return 1;
		case BarcodeComponent.ORIENTATION_DOWN:
			return 2;
		case BarcodeComponent.ORIENTATION_RIGHT:
			return 3;
		}
		return -1;
	}

	public static int getOrientation4Pos(int pos) {
		switch (pos) {
		case 0:
			return BarcodeComponent.ORIENTATION_UP;
		case 1:
			return BarcodeComponent.ORIENTATION_LEFT;
		case 2:
			return BarcodeComponent.ORIENTATION_DOWN;
		case 3:
			return BarcodeComponent.ORIENTATION_RIGHT;
		}
		return 0;
	}
}
