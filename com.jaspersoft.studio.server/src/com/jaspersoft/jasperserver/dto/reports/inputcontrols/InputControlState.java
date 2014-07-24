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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * InputControlState
 * @author akasych
 * @version $Id: InputControlState.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 * Simple class to transfer Input Control value.
 */

@XmlRootElement
public class InputControlState implements Serializable {

    private final static long serialVersionUID = 1l;

    private String uri;
    private String id;
    private String value;
    private String error;
    private List<InputControlOption> options;

    public InputControlState(){
    }

    public String getId() {
        return id;
    }

    public InputControlState setId(String id) {
        this.id = id;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public InputControlState setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getValue() {
        return value;
    }

    public InputControlState setValue(String value) {
        this.value = value;
        return this;
    }

    public String getError() {
        return error;
    }

    public InputControlState setError(String error) {
        this.error = error;
        return this;
    }

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    public List<InputControlOption> getOptions() {
        return options;
    }

    public InputControlState setOptions(List<InputControlOption> options) {
        this.options = options;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputControlState)) return false;

        InputControlState that = (InputControlState) o;

        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (!id.equals(that.id)) return false;
        if (options != null ? !options.equals(that.options) : that.options != null) return false;
        if (!uri.equals(that.uri)) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uri.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        return result;
    }
}




