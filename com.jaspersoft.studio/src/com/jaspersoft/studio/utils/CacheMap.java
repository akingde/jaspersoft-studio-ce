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
package com.jaspersoft.studio.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CacheMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 362498820763181265L;
	private int timeout = 1000;
	private Map<K, Date> expmap = new HashMap<K, Date>();

	public CacheMap(int timeout) {
		super();
		this.timeout = timeout;
	}

	@Override
	public V put(K key, V value) {
		V oldval = super.put(key, value);
		expmap.put(key, new Date());
		return oldval;
	};

	@Override
	public V get(Object key) {
		V val = super.get(key);
		if (val != null) {
			Date time = expmap.get(key);

			long newtime = new Date().getTime();
			if (newtime - time.getTime() > timeout) {
				remove(key);
				return null;
			}
		}
		return val;
	}
}
