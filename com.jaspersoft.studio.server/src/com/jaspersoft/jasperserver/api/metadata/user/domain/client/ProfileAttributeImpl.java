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

package com.jaspersoft.jasperserver.api.metadata.user.domain.client;

import com.jaspersoft.jasperserver.api.metadata.user.domain.ProfileAttribute;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @author sbirney
 */

@XmlRootElement(name = "profileAttribute")
public class ProfileAttributeImpl implements ProfileAttribute, Serializable {

    private String attrName;
    private String attrValue;

    @XmlTransient
    private Object principal;

    public String getAttrName() {
	return attrName;
    }
    public void setAttrName(String s) {
	this.attrName = s;
    }

    public String getAttrValue() {
	return attrValue;
    }
    public void setAttrValue(String s) {
	this.attrValue = s;
    }

    @XmlTransient
    public Object getPrincipal() {
	return principal;
    }


    public void setPrincipal(Object o) {
	this.principal = o;
    }

    public String toString() {
	return new ToStringBuilder(this)
	    .append("attrName", getAttrName())
	    .append("attrValue", getAttrValue())
	    .append("principal", getPrincipal())
	    .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ProfileAttributeImpl) ) return false;
        ProfileAttributeImpl castOther = (ProfileAttributeImpl) other;
        return new EqualsBuilder()
            .append(this.getAttrName(), castOther.getAttrName())
            .append(this.getPrincipal(), castOther.getPrincipal())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAttrName())
            .append(getPrincipal())
            .toHashCode();
    }
}
