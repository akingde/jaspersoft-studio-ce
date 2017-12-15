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

package com.jaspersoft.jasperserver.api.metadata.user.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * ProfileAttribute interface manages attaching of extra information to users and roles. For example, you may assign
 * �State� attribute and �CA� value for it to users, and then use it as a default state for logged user in reports or
 * views.
 *
 * @author sbirney
 * @version $Id: ProfileAttribute.java 21806 2011-12-27 09:24:01Z ykovalchyk $
 * @see com.jaspersoft.jasperserver.api.metadata.user.domain.client.ProfileAttributeImpl
 * @since 2.0.0
 */
@JasperServerAPI
public interface ProfileAttribute {

    /**
     * Returns the name of the attribute.
     *
     * @return the name of the attribute.
     */
    public String getAttrName();

    /**
     * Sets the name of the attribute.
     *
     * @param s the name of the attribute.
     */
    public void setAttrName(String s);

    /**
     * Returns the value of the attribute.
     *
     * @return the value of the attribute.
     */
    public String getAttrValue();

    /**
     * Sets the value of the attribute.
     *
     * @param s the value of the attribute.
     */
    public void setAttrValue(String s);

    /**
     * Returns the principal object (see {@link #setPrincipal(Object)}).
     *
     * @return the principal object.
     */
    public Object getPrincipal();

    /**
     * Sets the principal object ({@link User} or {@link Role}) to which this profile attribute is attached.
     *
     * @param o the principal object
     */
    public void setPrincipal(Object o);
}
