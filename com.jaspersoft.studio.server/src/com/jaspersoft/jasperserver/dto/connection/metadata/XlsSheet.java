/*
* Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights  reserved.
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
