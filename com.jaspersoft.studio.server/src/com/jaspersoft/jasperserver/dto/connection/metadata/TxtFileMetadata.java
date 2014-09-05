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

package com.jaspersoft.jasperserver.dto.connection.metadata;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author yaroslav.kovalchyk
 * @version $Id: TxtFileMetadata.java 39224 2013-11-01 11:27:34Z ykovalchyk $
 */
@XmlRootElement
public class TxtFileMetadata {
    private List<String> columns;

    public TxtFileMetadata() {
    }

    public TxtFileMetadata(TxtFileMetadata source) {
        if (source.columns != null) {
            columns = new ArrayList<String>(source.columns);
        }
    }

    public List<String> getColumns() {
        return columns;
    }

    public TxtFileMetadata setColumns(List<String> columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TxtFileMetadata that = (TxtFileMetadata) o;

        if (columns != null ? !columns.equals(that.columns) : that.columns != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return columns != null ? columns.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TxtFileMetadata{" +
                "columns=" + columns +
                "} " + super.toString();
    }
}
