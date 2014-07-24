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

import java.util.Set;

/**
 * Helper to convert bytes to strings and back.
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: OutputFormatConversionHelper.java 21927 2012-01-24 09:46:33Z ykovalchyk $
 */
public class OutputFormatConversionHelper {
    public static Set<Byte> toBytes(Set<String> strings) throws Exception {
        ReportJobOutputFormatsWrapper formatsWrapper = new ReportJobOutputFormatsWrapper();
        formatsWrapper.setFormats(strings);
        return new OutputFormatXmlAdapter().unmarshal(formatsWrapper);
    }

    public static Set<String> toStrings(Set<Byte> bytes) throws Exception {
        return new OutputFormatXmlAdapter().marshal(bytes).getFormats();
    }
}
