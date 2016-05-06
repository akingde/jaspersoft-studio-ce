/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.util.Map;

import com.jaspersoft.studio.utils.ModelUtils;

/**
 * A generic entry to bound a name to a value, a general pair
 * class that can be used in many case to do association of two 
 * element
 * 
 * @author Orlandin Marco
 *
 */
public class Pair<K, V> implements Map.Entry<K, V> {
    
	private final K key;
    
	private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Pair){
    		Pair<?, ?> def = (Pair<?, ?>)obj;
    		return ModelUtils.safeEquals(key, def.getKey()) &&
    				ModelUtils.safeEquals(value, def.getValue());
    	}
    	return false;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
    	return new Pair<K, V>(key, value);
    }
}