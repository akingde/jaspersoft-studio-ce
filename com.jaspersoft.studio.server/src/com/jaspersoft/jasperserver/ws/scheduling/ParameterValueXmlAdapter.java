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

package com.jaspersoft.jasperserver.ws.scheduling;

import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.ValuesCollection;
import net.sf.jasperreports.engine.JRParameter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.TimeZone;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ParameterValueXmlAdapter.java 26539 2012-12-07 16:31:32Z sergey.prilukin $
 */
public class ParameterValueXmlAdapter extends XmlAdapter<JobParameter, JobParameter> {
    @Override
    public JobParameter unmarshal(JobParameter v) throws Exception {
        JobParameter result = new JobParameter();
        result.setName(v.getName());
        result.setValue(v.getValue());
        if(v.getName().equals(JRParameter.REPORT_TIME_ZONE) && v.getValue() instanceof String){
            result.setValue(TimeZone.getTimeZone((String) v.getValue()));
        }else if(v.getValue() instanceof ValuesCollection){
            result.setValue(((ValuesCollection)v.getValue()).getCollection());
        }
        return result;
    }

    @Override
    public JobParameter marshal(JobParameter v) throws Exception {
        JobParameter result = new JobParameter();
        result.setName(v.getName());
        result.setValue(v.getValue());
        if(v.getValue() instanceof Collection){
            final ValuesCollection valuesCollection = new ValuesCollection();
            valuesCollection.setCollection((Collection<Object>) v.getValue());
            result.setValue(valuesCollection);
        }else if(v.getValue() instanceof TimeZone){
            result.setValue(((TimeZone)v.getValue()).getID());
        }
        return result;
    }
}
