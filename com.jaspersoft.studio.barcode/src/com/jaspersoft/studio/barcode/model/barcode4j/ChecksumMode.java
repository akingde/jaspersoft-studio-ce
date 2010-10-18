package com.jaspersoft.studio.barcode.model.barcode4j;

public class ChecksumMode {
	public static String[] getItems() {
		return new String[] { "<default>", "Auto", "Ignore", "Add", "Check" };
	}

	public static int getPos4ChecksumMode(String mode) {
		if (mode != null) {
			if (mode.equals("<default>"))
				return 0;
			if (mode.equals("Auto"))
				return 1;
			if (mode.equals("Ignore"))
				return 2;
			if (mode.equals("Add"))
				return 3;
			if (mode.equals("Check"))
				return 4;
		}
		return 0;
	}

	public static String getChecksumMode4Pos(int pos) {
		switch (pos) {
		case 0:
			return "<default>";
		case 1:
			return "Auto";
		case 2:
			return "Ignore";
		case 3:
			return "Add";
		case 4:
			return "Check";
		}
		return null;
	}
}
