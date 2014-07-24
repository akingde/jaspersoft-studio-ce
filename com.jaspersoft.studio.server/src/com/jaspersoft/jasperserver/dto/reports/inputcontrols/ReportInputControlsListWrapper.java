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

package com.jaspersoft.jasperserver.dto.reports.inputcontrols;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportInputControlsListWrapper.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement(name = "inputControls")
public class ReportInputControlsListWrapper {

    private List<ReportInputControl> inputParameters;

    public ReportInputControlsListWrapper(){}
    public ReportInputControlsListWrapper(List<ReportInputControl> inputParameters){
        this.inputParameters = inputParameters;
    }
    @XmlElement(name = "inputControl")
    public List<ReportInputControl> getInputParameters() {
        return inputParameters;
    }

    public ReportInputControlsListWrapper setInputParameters(List<ReportInputControl> inputParameters) {
        this.inputParameters = inputParameters;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportInputControlsListWrapper)) return false;

        ReportInputControlsListWrapper that = (ReportInputControlsListWrapper) o;

        if (inputParameters != null ? !inputParameters.equals(that.inputParameters) : that.inputParameters != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return inputParameters != null ? inputParameters.hashCode() : 0;
    }
}
