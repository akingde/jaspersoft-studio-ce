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

package com.jaspersoft.jasperserver.api.common.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

import java.util.List;

/**
 * Class which implements AttributedObject interface can keep a list of attributes.
 *
 * @author swood
 * @version $Id: AttributedObject.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0.1
 */
@JasperServerAPI
public interface AttributedObject {

	/**
     * Returns a list of attributes for the object.
     *
	 * @return a list of attributes for the object
	 */
	public List getAttributes();

    /**
     * Sets a list of attributes for the object.
     *
     * @param attrs a list of attributes for the object.
     */
    public void setAttributes(List attrs);
}
