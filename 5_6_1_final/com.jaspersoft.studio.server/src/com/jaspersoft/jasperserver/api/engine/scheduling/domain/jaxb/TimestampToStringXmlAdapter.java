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

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: TimestampToStringXmlAdapter.java 38348 2013-09-30 04:57:18Z carbiv $
 */
public class TimestampToStringXmlAdapter extends XmlAdapter<String, Timestamp>{
    @Override
    public Timestamp unmarshal(String v) throws Exception {
        return new Timestamp(DatatypeConverter.parseDateTime(v).getTimeInMillis());
    }

    @Override
    public String marshal(Timestamp v) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(v);
        return DatatypeConverter.printDateTime(calendar);
    }
}
