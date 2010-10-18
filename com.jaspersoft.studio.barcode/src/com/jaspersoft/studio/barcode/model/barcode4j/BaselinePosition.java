package com.jaspersoft.studio.barcode.model.barcode4j;

public class BaselinePosition {
	public static String[] getItems() {
		return new String[] { "<Default>", "Top", "Bottom" };
	}

	public static int getPos4BaselinePosition(String mode) {
		if (mode != null) {
			if (mode.equals("<Default>"))
				return 0;
			if (mode.equals("Top"))
				return 1;
			if (mode.equals("Bottom"))
				return 2;
		}
		return 0;
	}

	public static String getBaselinePosition4Pos(int pos) {
		switch (pos) {
		case 0:
			return "<Default>";
		case 1:
			return "Top";
		case 2:
			return "Bottom";
		}
		return null;
	}
}
