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
 * @version $Id$
 */
@XmlRootElement(name = "regexpParser")
public class TxtFileRegularExpressionParser implements TxtFileParser {
    private String regularExpression;

    public TxtFileRegularExpressionParser(){
    }

    public TxtFileRegularExpressionParser(TxtFileRegularExpressionParser source){
        regularExpression = source.regularExpression;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public TxtFileRegularExpressionParser setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TxtFileRegularExpressionParser that = (TxtFileRegularExpressionParser) o;

        if (regularExpression != null ? !regularExpression.equals(that.regularExpression) : that.regularExpression != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return regularExpression != null ? regularExpression.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TxtFileRegularExpressionParser{" +
                "regularExpression='" + regularExpression + '\'' +
                "} " + super.toString();
    }
}
