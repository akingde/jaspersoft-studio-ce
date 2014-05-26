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
import java.math.BigDecimal;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: RangeValidationRule.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement
public class RangeValidationRule extends InvertibleValidationRule<RangeValidationRule>{
    private BigDecimal maxValue;
    private BigDecimal minValue;
    private Boolean includeMaxValue = false;
    private Boolean includeMinValue = false;

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public RangeValidationRule setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public RangeValidationRule setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
        return this;
    }

    public Boolean isIncludeMaxValue() {
        return includeMaxValue;
    }

    public RangeValidationRule setIncludeMaxValue(Boolean includeMaxValue) {
        this.includeMaxValue = includeMaxValue;
        return this;
    }

    public Boolean isIncludeMinValue() {
        return includeMinValue;
    }

    public RangeValidationRule setIncludeMinValue(Boolean includeMinValue) {
        this.includeMinValue = includeMinValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangeValidationRule)) return false;
        if (!super.equals(o)) return false;

        RangeValidationRule that = (RangeValidationRule) o;

        if (includeMaxValue != null ? !includeMaxValue.equals(that.includeMaxValue) : that.includeMaxValue != null)
            return false;
        if (includeMinValue != null ? !includeMinValue.equals(that.includeMinValue) : that.includeMinValue != null)
            return false;
        if (maxValue != null ? !maxValue.equals(that.maxValue) : that.maxValue != null) return false;
        if (minValue != null ? !minValue.equals(that.minValue) : that.minValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (maxValue != null ? maxValue.hashCode() : 0);
        result = 31 * result + (minValue != null ? minValue.hashCode() : 0);
        result = 31 * result + (includeMaxValue != null ? includeMaxValue.hashCode() : 0);
        result = 31 * result + (includeMinValue != null ? includeMinValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RangeValidationRule{" +
                "maxValue=" + maxValue +
                ", minValue=" + minValue +
                ", includeMaxValue=" + includeMaxValue +
                ", includeMinValue=" + includeMinValue +
                "} " + super.toString();
    }
}
