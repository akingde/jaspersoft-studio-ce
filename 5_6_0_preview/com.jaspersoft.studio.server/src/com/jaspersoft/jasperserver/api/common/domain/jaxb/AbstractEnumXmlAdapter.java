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
package com.jaspersoft.jasperserver.api.common.domain.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * This adapter can be used to convert any type of internal property to string value using defined in enumeration mapping.
 * Where: enumeration constant name - string value;
 *        enumeration constant value - internal value.
 * Enumeration should implement NamedPropertyHolder interface.
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: AbstractEnumXmlAdapter.java 30161 2013-03-22 19:20:15Z inesterenko $
 */
public abstract class AbstractEnumXmlAdapter<PropertyType> extends XmlAdapter<String, PropertyType>{
     @Override
    public PropertyType unmarshal(String v) throws Exception {
        PropertyType result = null;
        if (v != null && !"".equals(v))
            for(NamedPropertyHolder<PropertyType> currentConstant : getEnumConstantsList())
            if(currentConstant.name().equals(v)){
                result = currentConstant.getProperty();
                break;
            }
        return result;
    }

    @Override
    public String marshal(PropertyType v) throws Exception {
         String result = null;
        for (NamedPropertyHolder<PropertyType> currentConstant : getEnumConstantsList()) {
            if (currentConstant.getProperty().equals(v)) {
                result = currentConstant.name();
                break;
            }
        }
        return result;
    }

    protected List<NamedPropertyHolder<PropertyType>> getEnumConstantsList(){
        return Arrays.asList(getEnumConstantsArray());
    }

    protected abstract NamedPropertyHolder<PropertyType>[] getEnumConstantsArray();

}
