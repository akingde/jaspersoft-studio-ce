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
/*
 * Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.jasperserver.dto.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Zakhar.Tomchenco
 * @version $Id$
 */
@XmlRootElement(name = "patch")
public class PatchItem {
    private String field, value, expression;

    @XmlElement(name = "field")
    public String getField() {
        return field;
    }

    public PatchItem setField(String field) {
        this.field = field;
        return this;
    }

    @XmlElement(name = "value")
    public String getValue() {
        return value;
    }

    public PatchItem setValue(String value) {
        this.value = value;
        return this;
    }

    @XmlElement(name = "expression")
    public String getExpression() {
        return expression;
    }

    public PatchItem setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    @Override
    public String toString() {
        String result = expression;
        if (result == null || "".equals(result)){
            StringBuilder resultBuilder = new StringBuilder(field);
            if (value == null){
                resultBuilder.append(" = null");
            }else {
                resultBuilder.append(" = \"").append(value).append("\"");
            }
            result = resultBuilder.toString();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatchItem patchItem = (PatchItem) o;

        if (expression != null ? !expression.equals(patchItem.expression) : patchItem.expression != null) return false;
        if (field != null ? !field.equals(patchItem.field) : patchItem.field != null) return false;
        if (value != null ? !value.equals(patchItem.value) : patchItem.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (expression != null ? expression.hashCode() : 0);
        return result;
    }
}
