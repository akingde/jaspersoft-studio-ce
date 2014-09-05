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
 * Created by nthapa on 7/12/13.
 */

import java.lang.RuntimeException;

/**
 * Created by nthapa on 7/11/13.
 */
public class SessionAttribMissingException extends JSShowOnlyErrorMessage {

    public SessionAttribMissingException(String message) {
        super(message);
    }

    public SessionAttribMissingException(String message, Object[] args) {
        super(message, args);
    }

}
