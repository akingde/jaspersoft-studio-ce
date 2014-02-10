/*
* Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights  reserved.
* http://www.jaspersoft.com.
*
* Unless you have purchased  a commercial license agreement from Jaspersoft,
* the following license terms  apply:
*
* This program is free software: you can redistribute it and/or  modify
* it under the terms of the GNU Affero General Public License  as
* published by the Free Software Foundation, either version 3 of  the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero  General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public  License
* along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
*/
package com.jaspersoft.jasperserver.remote.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: HashMapBasedBidirectionalMapping.java 23005 2012-04-05 12:47:10Z ykovalchyk $
 */
public class HashMapBasedBidirectionalMapping<K, V> implements BidirectionalMapping<K,V> {

    protected final Map<K, V> keyValueMap = Collections.synchronizedMap(new HashMap<K, V>());
    protected final Map<V, K> valueKeyMap = Collections.synchronizedMap(new HashMap<V, K>());

    @Override
    public K getKey(V value) {
        return valueKeyMap.get(value);
    }

    @Override
    public V getValue(K key) {
        return keyValueMap.get(key);
    }

    @Override
    public void put(K key, V value) {
        synchronized (keyValueMap) {
            V oldValue = keyValueMap.get(key);
            if(oldValue != null)
                valueKeyMap.remove(oldValue);
            K oldKey = valueKeyMap.get(value);
            if(oldKey != null)
                keyValueMap.remove(oldKey);
            keyValueMap.put(key, value);
            valueKeyMap.put(value, key);
        }
    }

    @Override
    public void clear() {
        synchronized (keyValueMap) {
            keyValueMap.clear();
            valueKeyMap.clear();
        }
    }

    @Override
    public int size() {
        return keyValueMap.size();
    }

}
