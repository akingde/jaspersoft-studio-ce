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

package com.jaspersoft.jasperserver.dto.reports;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportParameters.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement
public class ReportParameters {
    private List<ReportParameter> reportParameters;

    public ReportParameters() { }

    public ReportParameters(List<ReportParameter> reportParameters) {
        this.reportParameters = reportParameters;
    }

    @XmlElement(name = "reportParameter")
    public List<ReportParameter> getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(List<ReportParameter> reportParameters) {
        this.reportParameters = reportParameters;
    }

    public Map<String, String[]> getRawParameters(){
        Map<String, String[]> rawParameters = new HashMap<String, String[]>();
        if(reportParameters != null){
            for(ReportParameter currentParameter : reportParameters){
                rawParameters.put(
                        currentParameter.getName(),
                        currentParameter.getValues().toArray(new String[currentParameter.getValues().size()])
                );
            }
        }
        return rawParameters;
    }

    @Override
    public String toString() {
        return "ReportParameters{" +
                "reportParameters=" + reportParameters +
                '}';
    }
}
