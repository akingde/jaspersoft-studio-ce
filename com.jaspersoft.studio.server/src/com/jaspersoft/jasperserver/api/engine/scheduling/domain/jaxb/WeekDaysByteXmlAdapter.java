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

package com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: WeekDaysByteXmlAdapter.java 22756 2012-03-23 10:39:15Z sergey.prilukin $
 */
public class WeekDaysByteXmlAdapter extends XmlAdapter<WeekDaysSortedSetWrapper, SortedSet<Byte>>{
    @Override
    public SortedSet<Byte> unmarshal(WeekDaysSortedSetWrapper v) throws Exception {
        SortedSet<Byte> result = null;
        if(v != null && v.getDays() != null && !v.getDays().isEmpty()){
            result = new TreeSet<Byte>();
            for(String value : v.getDays())
                result.add(Byte.valueOf(value));
        }
        return result;
    }

    @Override
    public WeekDaysSortedSetWrapper marshal(SortedSet<Byte> v) throws Exception {
        WeekDaysSortedSetWrapper result = null;
        if(v != null && !v.isEmpty()){
            SortedSet<String> strings = new TreeSet<String>();
            for(Byte value : v)
                strings.add(value.toString());
            result = new WeekDaysSortedSetWrapper(strings);
        }
        return result;
    }
}
