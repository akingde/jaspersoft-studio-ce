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
		V oldval = put(key, value);
		expmap.put(key, new Date());
		return oldval;
	};

	@Override
	public V get(Object key) {
		V val = super.get(key);
		Date time = expmap.get(key);

		long newtime = new Date().getTime();
		if (newtime - time.getTime() > timeout) {
			remove(key);
			return null;
		}
		return val;
	}
}
