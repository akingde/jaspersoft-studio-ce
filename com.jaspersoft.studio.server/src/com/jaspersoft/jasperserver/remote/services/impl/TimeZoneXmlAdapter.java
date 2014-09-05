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

package com.jaspersoft.jasperserver.remote.services.impl;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.TimeZone;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id$
 */
public class TimeZoneXmlAdapter extends XmlAdapter<String, TimeZone>{
    @Override
    public TimeZone unmarshal(String v) throws Exception {
        return TimeZone.getTimeZone(v);
    }

    @Override
    public String marshal(TimeZone v) throws Exception {
        return v.getID();
    }
}
