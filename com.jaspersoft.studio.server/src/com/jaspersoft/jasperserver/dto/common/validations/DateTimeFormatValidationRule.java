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

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: DateTimeFormatValidationRule.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement
public class DateTimeFormatValidationRule extends ValidationRule<DateTimeFormatValidationRule> {
    public static final String INVALID_DATE = "fillParameters.error.invalidDate";
    public static final String INVALID_DATE_TIME = "fillParameters.error.invalidDateTime";
    public static final String INVALID_TIME = "fillParameters.error.invalidTime";
    private String format;

    public String getFormat() {
        return format;
    }

    public DateTimeFormatValidationRule setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateTimeFormatValidationRule)) return false;
        if (!super.equals(o)) return false;

        DateTimeFormatValidationRule that = (DateTimeFormatValidationRule) o;

        if (!format.equals(that.format)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + format.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DateTimeFormatValidationRule{" +
                "format='" + format + '\'' +
                "} " + super.toString();
    }
}
