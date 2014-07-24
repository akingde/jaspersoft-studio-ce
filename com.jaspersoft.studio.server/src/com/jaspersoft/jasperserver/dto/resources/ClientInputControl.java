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
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
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

package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author Zakhar.Tomchenco
 * @version $Id: ClientInputControl.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.INPUT_CONTROL_CLIENT_TYPE)
public class ClientInputControl extends ClientResource<ClientInputControl> implements ClientReferenceableInputControl {
    private boolean mandatory;
    private boolean readOnly;
    private boolean visible;
    private byte type;
    private List<String> visibleColumns;
    private String valueColumn;

    private ClientReferenceableDataType dataType;
    private ClientReferenceableQuery query;
    private ClientReferenceableListOfValues listOfValues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientInputControl that = (ClientInputControl) o;

        if (mandatory != that.mandatory) return false;
        if (readOnly != that.readOnly) return false;
        if (type != that.type) return false;
        if (visible != that.visible) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (listOfValues != null ? !listOfValues.equals(that.listOfValues) : that.listOfValues != null) return false;
        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        if (valueColumn != null ? !valueColumn.equals(that.valueColumn) : that.valueColumn != null) return false;
        if (visibleColumns != null ? !visibleColumns.equals(that.visibleColumns) : that.visibleColumns != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mandatory ? 1 : 0);
        result = 31 * result + (readOnly ? 1 : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (int) type;
        result = 31 * result + (visibleColumns != null ? visibleColumns.hashCode() : 0);
        result = 31 * result + (valueColumn != null ? valueColumn.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (query != null ? query.hashCode() : 0);
        result = 31 * result + (listOfValues != null ? listOfValues.hashCode() : 0);
        return result;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public ClientInputControl setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public ClientInputControl setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public ClientInputControl setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public byte getType() {
        return type;
    }

    public ClientInputControl setType(byte type) {
        this.type = type;
        return this;
    }

    @XmlElementWrapper(name = "visibleColumns")
    @XmlElement(name = "visibleColumn")
    public List<String> getVisibleColumns() {
        return visibleColumns;
    }

    public ClientInputControl setVisibleColumns(List<String> visibleColumns) {
        this.visibleColumns = visibleColumns;
        return this;
    }

    public String getValueColumn() {
        return valueColumn;
    }

    public ClientInputControl setValueColumn(String valueColumn) {
        this.valueColumn = valueColumn;
        return this;
    }

    @XmlElements({
            @XmlElement(type = ClientReference.class, name = "dataTypeReference"),
            @XmlElement(type = ClientDataType.class, name = "dataType")
    })
    public ClientReferenceableDataType getDataType() {
        return dataType;
    }

    public ClientInputControl setDataType(ClientReferenceableDataType dataType) {
        this.dataType = dataType;
        return this;
    }

    @XmlElements({
        @XmlElement(type = ClientReference.class, name = "queryReference"),
        @XmlElement(type = ClientQuery.class, name = "query")
    })
    public ClientReferenceableQuery getQuery() {
        return query;
    }

    public ClientInputControl setQuery(ClientReferenceableQuery query) {
        this.query = query;
        return this;
    }

    @XmlElements({
            @XmlElement(type = ClientReference.class, name = "listOfValuesReference"),
            @XmlElement(type = ClientListOfValues.class, name = "listOfValues")
    })
    public ClientReferenceableListOfValues getListOfValues() {
        return listOfValues;
    }

    public ClientInputControl setListOfValues(ClientReferenceableListOfValues listOfValues) {
        this.listOfValues = listOfValues;
        return this;
    }

    @Override
    public String toString() {
        return "ClientInputControl{" +
                "mandatory=" + mandatory +
                ", readOnly=" + readOnly +
                ", visible=" + visible +
                ", type=" + type +
                ", visibleColumns=" + visibleColumns +
                ", valueColumn='" + valueColumn + '\'' +
                ", dataType=" + dataType +
                ", query=" + query +
                ", listOfValues=" + listOfValues +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
