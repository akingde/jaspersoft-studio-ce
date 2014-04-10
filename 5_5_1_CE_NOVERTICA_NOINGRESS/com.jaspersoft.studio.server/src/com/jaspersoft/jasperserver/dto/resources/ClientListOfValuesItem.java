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
package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientListOfValuesItem.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = "item")
public class ClientListOfValuesItem {
    private String label;
    private String value;

    public ClientListOfValuesItem(){
    }

    public ClientListOfValuesItem(String label, String value){
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public ClientListOfValuesItem setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ClientListOfValuesItem setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientListOfValuesItem that = (ClientListOfValuesItem) o;

        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientListOfValuesItem{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
