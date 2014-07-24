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
 * @version $Id: XlsSheet.java 39224 2013-11-01 11:27:34Z ykovalchyk $
 */
@XmlRootElement(name = "sheet")
public class XlsSheet {
    private String name;
    private List<String> columns;

    public XlsSheet() {
    }

    public XlsSheet(XlsSheet source) {
        name = source.name;
        if (source.columns != null) {
            columns = new ArrayList<String>(source.columns);
        }
    }

    public String getName() {
        return name;
    }

    public XlsSheet setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getColumns() {
        return columns;
    }

    public XlsSheet setColumns(List<String> columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XlsSheet xlsSheet = (XlsSheet) o;

        if (columns != null ? !columns.equals(xlsSheet.columns) : xlsSheet.columns != null) return false;
        if (name != null ? !name.equals(xlsSheet.name) : xlsSheet.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (columns != null ? columns.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "XlsSheet{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                "} " + super.toString();
    }
}
