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
 * This exception should be thrown to indicate that some
 * feature is not implemented yet.
 * On UI side user should see friendly message based on this exception.
 *
 * @author Sergey Prilukin
 * @version $Id: JSNotImplementedException.java 20440 2011-05-03 22:54:35Z asokolnikov $
 */
@JasperServerAPI
public class JSNotImplementedException extends RuntimeException {

    public JSNotImplementedException() {
        super("Feature is not implemented.");
    }

    public JSNotImplementedException(String message) {
        super(message);
    }
}
