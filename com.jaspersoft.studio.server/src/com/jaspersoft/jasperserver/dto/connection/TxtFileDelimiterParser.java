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

package com.jaspersoft.jasperserver.dto.connection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: TxtFileDelimiterParser.java 39215 2013-10-31 07:59:03Z ykovalchyk $
 */
@XmlRootElement(name = "delimiterParser")
public class TxtFileDelimiterParser implements TxtFileParser {

    public TxtFileDelimiterParser(){
    }

    public TxtFileDelimiterParser(TxtFileDelimiterParser source){
        delimiter = source.delimiter;
    }
    private String delimiter;

    public String getDelimiter() {
        return delimiter;
    }

    public TxtFileDelimiterParser setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TxtFileDelimiterParser that = (TxtFileDelimiterParser) o;

        if (delimiter != null ? !delimiter.equals(that.delimiter) : that.delimiter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return delimiter != null ? delimiter.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TxtFileDelimiterParser{" +
                "delimiter='" + delimiter + '\'' +
                "} " + super.toString();
    }
}
