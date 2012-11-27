package com.jaspersoft.studio.preferences.fonts.utils;

import java.util.ArrayList;
import java.util.List;

public class FontUtils {
	public static String separator = "__________________";

	/**
	 * Convert a list of array of string into a single array of string, ready to be inserted into a combo
	 * 
	 * @param fontsList
	 *          List of array of fonts, between every array will be inserted a separator
	 * @return List of combo item
	 */
	public static String[] stringToItems(List<String[]> fontsList) {
		List<String> itemsList = new ArrayList<String>();
		for (int index = 0; index < fontsList.size(); index++) {
			String[] fonts = fontsList.get(index);
			for (String element : fonts) {
				itemsList.add(element);
			}
			if (index + 1 != fontsList.size() && fonts.length > 0) {
				itemsList.add(separator);
				;
			}
		}
		String[] result = new String[itemsList.size()];
		return itemsList.toArray(result);
	}
}
