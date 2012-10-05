/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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
