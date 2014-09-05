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
