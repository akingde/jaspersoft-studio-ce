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

package com.jaspersoft.jasperserver.api;


/**
 * Runtime Exception for security validation errors
 *
 * @author Anton Fomin
 * @version $Id: JSSecurityException.java 25479 2012-10-25 19:15:37Z dlitvak $
 */
public class JSSecurityException extends JSException {

    public JSSecurityException(String message) {
        super(message);
    }

	public JSSecurityException(String message, Throwable cause) {
		super(message, cause);
	}
}
