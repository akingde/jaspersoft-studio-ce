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

import com.jaspersoft.jasperserver.api.engine.scheduling.domain.FTPInfo;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: FtpTypeAdapter.java 38348 2013-09-30 04:57:18Z carbiv $
 */
public class FtpTypeAdapter extends XmlAdapter<String, String> {
    private static final String CLIENT_TYPE_FTP = "ftp";
    private static final String CLIENT_TYPE_FTPS = "ftps";
    @Override
    public String unmarshal(String v) throws Exception {
        return CLIENT_TYPE_FTPS.equals(v) ? FTPInfo.TYPE_FTPS : FTPInfo.TYPE_FTP;
    }

    @Override
    public String marshal(String v) throws Exception {
        return FTPInfo.TYPE_FTPS.equals(v) ? CLIENT_TYPE_FTPS : CLIENT_TYPE_FTP;
    }
}
