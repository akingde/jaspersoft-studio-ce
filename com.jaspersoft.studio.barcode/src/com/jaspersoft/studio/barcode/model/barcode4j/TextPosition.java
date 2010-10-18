package com.jaspersoft.studio.barcode.model.barcode4j;

public class TextPosition {
	public static String[] getItems() {
		return new String[] { "<Default>", "None", "Bottom", "Top" };
	}

	public static int getPos4TextPosition(String textPosition) {
		if (textPosition != null) {
			if (textPosition.equals("<Default>"))
				return 0;
			if (textPosition.equals("None"))
				return 1;
			if (textPosition.equals("Bottom"))
				return 2;
			if (textPosition.equals("Top"))
				return 3;
		}
		return 0;
	}

	public static String getTextPosition4Pos(int pos) {
		switch (pos) {
		case 0:
			return "<Default>";
		case 1:
			return "None";
		case 2:
			return "Bottom";
		case 3:
			return "Top";
		}
		return "<Default>";
	}
}
