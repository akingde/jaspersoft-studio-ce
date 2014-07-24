/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

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
