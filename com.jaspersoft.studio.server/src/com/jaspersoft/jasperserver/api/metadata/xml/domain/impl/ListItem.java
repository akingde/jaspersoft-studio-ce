/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.jasperserver.api.metadata.xml.domain.impl;

/**
 * It's an implementation of ListOfValuesItem used in Webservices.
 * This class does not implements directly ListOfValuesItem to avoid
 * the need of this interface on client side.
 *
 * @author gtoffoli
 */
public class ListItem {
    
    private Object value;
    private String label;
    private boolean isListItem = false;
    
    /** Creates a new instance of ListItem */
    public ListItem() {
    }
    
    /** Creates a new instance of ListItem */
    public ListItem(String label, Object value) {
        this.value = value;
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isIsListItem() {
        return isListItem;
    }

    public void setIsListItem(boolean isListItem) {
        this.isListItem = isListItem;
    }
    
}
