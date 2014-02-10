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
package com.jaspersoft.jasperserver.dto.reports;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportParameter.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement
public class ReportParameter {
    private String name;
    private List<String> values = new LinkedList<String>();

    @XmlAttribute(required = true)
    public String getName() {
        return name;
    }

    public ReportParameter setName(String name) {
        this.name = name;
        return this;
    }

    @XmlElement(name = "value")
    public List<String> getValues() {
        return values;
    }

    public ReportParameter setValues(List<String> values) {
        this.values = values;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportParameter that = (ReportParameter) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (values != null ? !new HashSet(values).equals(new HashSet(that.values)) : that.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (values != null ? new HashSet(values).hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReportParameter{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
