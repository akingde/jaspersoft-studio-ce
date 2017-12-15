/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
	public boolean containsKey(Object key) {
		boolean b = super.containsKey(key);
		if (b) {
			Date time = expmap.get(key);

			long newtime = System.currentTimeMillis();
			if (newtime - time.getTime() > timeout) {
				remove(key);
				return false;
			}
		}
		return b;
	}

	@Override
	public V get(Object key) {
		V val = super.get(key);
		if (val != null) {
			Date time = expmap.get(key);

			long newtime = System.currentTimeMillis();
			if (newtime - time.getTime() > timeout) {
				remove(key);
				return null;
			}
		}
		return val;
	}

	@Override
	public V remove(Object arg0) {
		expmap.remove(arg0);
		return super.remove(arg0);
	}
}
