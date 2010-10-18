package com.jaspersoft.studio.barcode.model.barcode4j;

public class DataMatrixShape {
	public static String[] getItems() {
		return new String[] { "<Default>", "Force None", "Force Square", "Force Rectangle" };
	}

	public static int getPos4Shape(String textPosition) {
		if (textPosition != null) {
			if (textPosition.equals("<Default>"))
				return 0;
			if (textPosition.equals("Force None"))
				return 1;
			if (textPosition.equals("Force Square"))
				return 2;
			if (textPosition.equals("Force Rectangle"))
				return 3;
		}
		return 0;
	}

	public static String getShape4Pos(int pos) {
		switch (pos) {
		case 0:
			return "<Default>";
		case 1:
			return "Force None";
		case 2:
			return "Force Square";
		case 3:
			return "Force Rectangle";
		}
		return "<Default>";
	}
}
