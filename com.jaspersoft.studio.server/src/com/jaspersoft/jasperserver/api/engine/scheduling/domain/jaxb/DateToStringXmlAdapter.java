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
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: DateToStringXmlAdapter.java 38348 2013-09-30 04:57:18Z carbiv $
 */
public class DateToStringXmlAdapter extends XmlAdapter<String, Date>{
    @Override
    public Date unmarshal(String v) throws Exception {
        return DatatypeConverter.parseDateTime(v).getTime();
    }

    @Override
    public String marshal(Date v) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(v);
        return DatatypeConverter.printDateTime(calendar);
    }
}
