/*
* Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights  reserved.
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
package com.jaspersoft.jasperserver.dto.common.validations;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: RegexpValidationRule.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement
public class RegexpValidationRule extends InvertibleValidationRule<RegexpValidationRule>{

    private String regexp;

    public String getRegexp() {
        return regexp;
    }

    public RegexpValidationRule setRegexp(String regexp) {
        this.regexp = regexp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegexpValidationRule)) return false;
        if (!super.equals(o)) return false;

        RegexpValidationRule that = (RegexpValidationRule) o;

        if (regexp != null ? !regexp.equals(that.regexp) : that.regexp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (regexp != null ? regexp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegexpValidationRule{" +
                "regexp='" + regexp + '\'' +
                "} " + super.toString();
    }
}
