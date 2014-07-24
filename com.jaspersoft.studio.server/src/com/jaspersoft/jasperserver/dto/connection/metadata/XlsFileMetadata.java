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

package com.jaspersoft.jasperserver.dto.connection.metadata;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: XlsFileMetadata.java 39224 2013-11-01 11:27:34Z ykovalchyk $
 */
@XmlRootElement
public class XlsFileMetadata {
    private List<XlsSheet> sheets;
    public XlsFileMetadata(){
    }
    public XlsFileMetadata(XlsFileMetadata source){
        if(source.sheets != null){
            sheets = new ArrayList<XlsSheet>(source.sheets);
        }
    }

    public List<XlsSheet> getSheets() {
        return sheets;
    }

    public XlsFileMetadata setSheets(List<XlsSheet> sheets) {
        this.sheets = sheets;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XlsFileMetadata that = (XlsFileMetadata) o;

        if (sheets != null ? !sheets.equals(that.sheets) : that.sheets != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sheets != null ? sheets.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "XlsFileMetadata{" +
                "sheets=" + sheets +
                "} " + super.toString();
    }
}
