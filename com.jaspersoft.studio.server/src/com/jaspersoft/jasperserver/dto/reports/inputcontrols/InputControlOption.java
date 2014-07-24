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
