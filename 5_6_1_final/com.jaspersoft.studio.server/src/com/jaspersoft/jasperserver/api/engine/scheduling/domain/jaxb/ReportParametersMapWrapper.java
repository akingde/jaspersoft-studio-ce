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

package com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id$
 */
@XmlRootElement(name = "parameters")
public class ReportParametersMapWrapper {
    private HashMap<String, Object> parameterValues;

    public ReportParametersMapWrapper(){}
    public ReportParametersMapWrapper(HashMap<String, Object> parameterValues){
        this.parameterValues = parameterValues;
    }

    public HashMap<String, Object> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(HashMap<String, Object> parameterValues) {
        this.parameterValues = parameterValues;
    }
}
