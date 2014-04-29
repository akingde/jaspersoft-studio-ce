package com.jaspersoft.jasperserver.remote.services.async;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author: Zakhar.Tomchenco
 */
@XmlRootElement(name = "state")
public class StateDto {
    String id;
    String message, phase;

    public StateDto() {
        super();
    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "phase")
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    @XmlElement(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
