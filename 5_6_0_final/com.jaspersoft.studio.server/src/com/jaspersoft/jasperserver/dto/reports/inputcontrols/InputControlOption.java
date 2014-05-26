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
package com.jaspersoft.jasperserver.dto.reports.inputcontrols;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * InputControlOption
 * @author akasych
 * @version $Id: InputControlOption.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 * Simple class to transfer Input Control list option.
 */

@XmlRootElement(name = "inputControlOption")
public class InputControlOption implements Serializable {

    private final static long serialVersionUID = 1l;

    private boolean selected;
    private String label;
    private String value;

    public InputControlOption() {
    }

    public InputControlOption(String value, String label, boolean selected) {
        this.label = label;
        this.value = value;
        this.selected = selected;
    }

    public InputControlOption(String value, String label) {
        this.label = label;
        this.value = value;
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }
    public InputControlOption setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public String getLabel() {
        return label;
    }
    public InputControlOption setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getValue() {
        return value;
    }

    public InputControlOption setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputControlOption)) return false;

        InputControlOption that = (InputControlOption) o;

        if (selected != that.selected) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (selected ? 1 : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
