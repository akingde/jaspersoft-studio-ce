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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: TxtFileConnection.java 39215 2013-10-31 07:59:03Z ykovalchyk $
 */
@XmlRootElement(name = "txtFile")
public class TxtFileConnection extends AbstractFileConnection<TxtFileConnection> {

    public TxtFileConnection(){
    }

    public TxtFileConnection(TxtFileConnection source){
        super(source);
        if(source.parser instanceof TxtFileDelimiterParser){
            parser = new TxtFileDelimiterParser((TxtFileDelimiterParser) source.parser);
        } else if(source.parser instanceof TxtFileRegularExpressionParser){
            parser = new TxtFileRegularExpressionParser((TxtFileRegularExpressionParser) source.parser);
        }
    }

    private TxtFileParser parser;

    @XmlElements({
            @XmlElement(name = "delimiterParser", type = TxtFileDelimiterParser.class),
            @XmlElement(name = "regexpParser", type = TxtFileRegularExpressionParser.class)
    })
    public TxtFileParser getParser() {
        return parser;
    }

    public TxtFileConnection setParser(TxtFileParser parser) {
        this.parser = parser;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TxtFileConnection that = (TxtFileConnection) o;

        if (parser != null ? !parser.equals(that.parser) : that.parser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (parser != null ? parser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TxtFileConnection{" +
                "parser=" + parser +
                "} " + super.toString();
    }
}
