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
 * @version $Id: MandatoryValidationRule.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement
public class MandatoryValidationRule extends ValidationRule<MandatoryValidationRule>{
    public static final String ERROR_KEY = "fillParameters.error.mandatoryField";
    // currently it's enough to have super class's fields only, i.e. errorMessage.

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getClass().getName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof MandatoryValidationRule)) return false;
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "MandatoryValidationRule{} " + super.toString();
    }
}
