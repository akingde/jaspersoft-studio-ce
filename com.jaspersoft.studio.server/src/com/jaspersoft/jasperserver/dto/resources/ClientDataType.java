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
 * @version $Id: ClientDataType.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.DATA_TYPE_CLIENT_TYPE)
public class ClientDataType extends ClientResource<ClientDataType> implements ClientReferenceableDataType {
    private TypeOfDataType type;
    private String pattern;
    private String maxValue;
    private Boolean strictMax = false;
    private String minValue;
    private Boolean strictMin = false;
    private Integer maxLength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientDataType that = (ClientDataType) o;

        if (maxLength != null ? !maxLength.equals(that.maxLength) : that.maxLength != null) return false;
        if (maxValue != null ? !maxValue.equals(that.maxValue) : that.maxValue != null) return false;
        if (minValue != null ? !minValue.equals(that.minValue) : that.minValue != null) return false;
        if (pattern != null ? !pattern.equals(that.pattern) : that.pattern != null) return false;
        if (strictMax != null ? !strictMax.equals(that.strictMax) : that.strictMax != null) return false;
        if (strictMin != null ? !strictMin.equals(that.strictMin) : that.strictMin != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pattern != null ? pattern.hashCode() : 0);
        result = 31 * result + (maxValue != null ? maxValue.hashCode() : 0);
        result = 31 * result + (strictMax != null ? strictMax.hashCode() : 0);
        result = 31 * result + (minValue != null ? minValue.hashCode() : 0);
        result = 31 * result + (strictMin != null ? strictMin.hashCode() : 0);
        result = 31 * result + (maxLength != null ? maxLength.hashCode() : 0);
        return result;
    }

    public Integer getMaxLength() {
        return maxLength;

    }

    public ClientDataType setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public TypeOfDataType getType() {
        return type;
    }

    public ClientDataType setType(TypeOfDataType type) {
        this.type = type;
        return this;
    }

    public String getPattern() {
        return pattern;
    }

    public ClientDataType setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public ClientDataType setMaxValue(String maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public Boolean isStrictMax() {
        return strictMax;
    }

    public ClientDataType setStrictMax(Boolean strictMax) {
        this.strictMax = strictMax;
        return this;
    }

    public String getMinValue() {
        return minValue;
    }

    public ClientDataType setMinValue(String minValue) {
        this.minValue = minValue;
        return this;
    }

    public Boolean isStrictMin() {
        return strictMin;
    }

    public ClientDataType setStrictMin(Boolean strictMin) {
        this.strictMin = strictMin;
        return this;
    }

    public enum TypeOfDataType{
        text, number, date, datetime,time
    }

    @Override
    public String toString() {
        return "ClientDataType{" +
                "type=" + type +
                ", pattern='" + pattern + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", strictMax=" + strictMax +
                ", minValue='" + minValue + '\'' +
                ", strictMin=" + strictMin +
                ", maxLength=" + maxLength +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
