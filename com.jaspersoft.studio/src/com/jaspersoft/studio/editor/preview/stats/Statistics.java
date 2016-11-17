/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Statistics {
	public class Duration {
		long start = 0;
		long end = start;

		public Duration() {
			start = System.currentTimeMillis();
			end = start;
		}

		public void stop() {
			end = System.currentTimeMillis();
		}

		public long getDuration() {
			return end - start;
		}
	}

	private Map<String, Object> durations = new ConcurrentHashMap<String, Object>();

	public Duration startCount(String key) {
		Duration d = new Duration();
		durations.put(key, d);
		return d;
	}

	public void endCount(String key) {
		Object obj = durations.get(key);
		if (obj != null && obj instanceof Duration)
			((Duration) obj).stop();
	}

	public long getDuration(String key) {
		Object obj = durations.get(key);
		if (obj != null && obj instanceof Duration)
			return ((Duration) obj).getDuration();
		return 0;
	}

	public void setValue(String key, Object value) {
		durations.put(key, value);
	}

	public Object getValue(String key) {
		return durations.get(key);
	}
}
