package com.jaspersoft.studio.editor.preview.jive;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private static final Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();

	public static Map<String, Object> getContext(String key) {
		return map.get(key);
	}

	public static void putContext(String key, Map<String, Object> value) {
		map.put(key, value);
	}
}
