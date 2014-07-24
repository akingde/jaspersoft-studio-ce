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

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: InvertibleValidationRule.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
public abstract class InvertibleValidationRule<BuilderType extends InvertibleValidationRule<BuilderType>>
        extends ValidationRule<BuilderType> {
    private Boolean inverted = false;

    public Boolean isInverted() {
        return inverted;
    }

    // definition of subclasses assures cast safety.
    @SuppressWarnings("unchecked")
    public BuilderType setInverted(Boolean inverted) {
        this.inverted = inverted;
        return (BuilderType) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvertibleValidationRule)) return false;
        if (!super.equals(o)) return false;

        InvertibleValidationRule that = (InvertibleValidationRule) o;

        if (inverted != null ? !inverted.equals(that.inverted) : that.inverted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (inverted != null ? inverted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InvertibleValidationRule{" +
                "inverted=" + inverted +
                "} " + super.toString();
    }
}
