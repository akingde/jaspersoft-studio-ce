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
