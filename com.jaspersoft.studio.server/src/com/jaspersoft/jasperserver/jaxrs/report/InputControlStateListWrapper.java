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

package com.jaspersoft.jasperserver.jaxrs.report;

import com.jaspersoft.jasperserver.dto.reports.inputcontrols.InputControlState;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author akasych
 * @version $Id$
 */
@XmlRootElement(name = "inputControlStateList")
public class InputControlStateListWrapper {

    private List<InputControlState> inputControlStateList;

    public InputControlStateListWrapper(){}
    public InputControlStateListWrapper(List<InputControlState> inputControlStateList){
        this.inputControlStateList = inputControlStateList;
    }
    @XmlElement(name = "inputControlState")
    public List<InputControlState> getInputControlStateList() {
        return inputControlStateList;
    }

    public void setInputControlStateList(List<InputControlState> inputControlStateList) {
        this.inputControlStateList = inputControlStateList;
    }

}
