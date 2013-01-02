/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
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
