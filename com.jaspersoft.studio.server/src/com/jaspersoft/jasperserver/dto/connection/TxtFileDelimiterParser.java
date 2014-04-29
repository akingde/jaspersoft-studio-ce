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
package com.jaspersoft.jasperserver.dto.connection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: TxtFileDelimiterParser.java 39215 2013-10-31 07:59:03Z ykovalchyk $
 */
@XmlRootElement(name = "delimiterParser")
public class TxtFileDelimiterParser implements TxtFileParser {

    public TxtFileDelimiterParser(){
    }

    public TxtFileDelimiterParser(TxtFileDelimiterParser source){
        delimiter = source.delimiter;
    }
    private String delimiter;

    public String getDelimiter() {
        return delimiter;
    }

    public TxtFileDelimiterParser setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TxtFileDelimiterParser that = (TxtFileDelimiterParser) o;

        if (delimiter != null ? !delimiter.equals(that.delimiter) : that.delimiter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return delimiter != null ? delimiter.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TxtFileDelimiterParser{" +
                "delimiter='" + delimiter + '\'' +
                "} " + super.toString();
    }
}
