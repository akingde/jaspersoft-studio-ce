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
 * @version $Id$
 */
@XmlRootElement(name = "regexpParser")
public class TxtFileRegularExpressionParser implements TxtFileParser {
    private String regularExpression;

    public TxtFileRegularExpressionParser(){
    }

    public TxtFileRegularExpressionParser(TxtFileRegularExpressionParser source){
        regularExpression = source.regularExpression;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public TxtFileRegularExpressionParser setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TxtFileRegularExpressionParser that = (TxtFileRegularExpressionParser) o;

        if (regularExpression != null ? !regularExpression.equals(that.regularExpression) : that.regularExpression != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return regularExpression != null ? regularExpression.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TxtFileRegularExpressionParser{" +
                "regularExpression='" + regularExpression + '\'' +
                "} " + super.toString();
    }
}
