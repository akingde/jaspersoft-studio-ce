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

package com.jaspersoft.jasperserver.dto.connection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: XlsFileConnection.java 39215 2013-10-31 07:59:03Z ykovalchyk $
 */
@XmlRootElement(name = "xlsFile")
public class XlsFileConnection extends AbstractFileConnection<XlsFileConnection> {
    public XlsFileConnection(){
    }

    public XlsFileConnection(XlsFileConnection source){
        super(source);
    }
}
