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

package com.jaspersoft.jasperserver.api.metadata.xml.domain.impl;

/**
 * @author tkavanagh
 * @version $Id: Request.java 3614 2006-06-09 12:14:38Z giulio $
 */

import java.util.List;
import java.util.ArrayList;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

public class Request {

    public static final String OPERATION_RUN_REPORT = "runReport";
    public static final String OPERATION_LIST = "list";
    public static final String OPERATION_PUT = "put";
    public static final String OPERATION_GET = "get";
    public static final String OPERATION_LOGIN = "login";
    public static final String OPERATION_CHECK_DEPENDS = "checkForDependentResources";
    
    /**
     * List of arguments
     */
    private List arguments = new ArrayList();
    private ResourceDescriptor resourceDescriptor;
    
    private String operationName = null;
    private String locale = null; // a string defining locale...
    
    /**
     * Creates a new instance of Request
     */
    public Request() {
    }

    public List getArguments() {
        return arguments;
    }

    public void setArguments(List arguments) {
        this.arguments = arguments;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public ResourceDescriptor getResourceDescriptor() {
        return resourceDescriptor;
    }

    public void setResourceDescriptor(ResourceDescriptor resourceDescriptor) {
        this.resourceDescriptor = resourceDescriptor;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    public String getArgumentValue(String argumentName) {
    	String value = null;
        for (int i=0; i < arguments.size(); ++i) {
            Argument a = (Argument) arguments.get(i);
            if (a.getName() == null ? a.getName() == argumentName : a.getName().equals(argumentName)) {
            	value = a.getValue();
            	break;
            }
        }
        return value;
    }
    
    /**
     * Determines if a specific argument name is present in this request's
     * list of arguments.
     * 
     * @param argumentName the name of the argument to look for
     * @return <code>true</code> if an argument having the specified name is
     * found in this request
     */
    public boolean hasArgument(String argumentName) {
    	boolean found = false;
        for (int i=0; i < arguments.size(); ++i) {
            Argument arg = (Argument) arguments.get(i);
            if (argumentName.equals(arg.getName())) {
            	found = true;
            	break;
            }
        }
        return found;
    }

    /**
     * Adds an argument with no value to the request.
     * 
     * @param name the argument name
     */
    public void addArgument(String name) {
    	addArgument(name, null);
    }
    
    /**
     * Adds an argument to the request
     * 
     * @param name the argument name
     * @param value the argument value
     */
    public void addArgument(String name, String value) {
    	Argument argument = new Argument(name, value);
    	arguments.add(argument);
    }
	
}
