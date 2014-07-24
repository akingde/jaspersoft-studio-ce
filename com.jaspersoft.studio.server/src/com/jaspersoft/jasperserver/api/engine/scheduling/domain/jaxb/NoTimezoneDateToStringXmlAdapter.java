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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: NoTimezoneDateToStringXmlAdapter.java 38348 2013-09-30 04:57:18Z carbiv $
 */
public class NoTimezoneDateToStringXmlAdapter extends XmlAdapter<String, Date>{

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    @Override
    public Date unmarshal(String v) throws Exception {
        return new SimpleDateFormat(DATE_TIME_PATTERN).parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(v);
    }
}
