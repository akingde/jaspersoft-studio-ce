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
 * This is general approach for display error message without full stackTrace
 * On UI(errorPage.jsp) side user should see friendly message based on this exception.
 *
 * @author Roman Kuziv
 * @version $Id: JSShowOnlyErrorMessage.java 20327 2011-04-08 10:05:27Z roman.kuziv $
 */

@JasperServerAPI
public class JSShowOnlyErrorMessage extends JSException {
    public JSShowOnlyErrorMessage(String message) {
        super(message);
    }

    public JSShowOnlyErrorMessage(String message, Object[] args) {
		super(message);
		this.args = args;
	}
}
