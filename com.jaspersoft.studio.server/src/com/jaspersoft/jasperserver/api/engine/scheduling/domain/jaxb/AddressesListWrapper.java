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

package com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This class is needed because of bug in JAXB.
 * XmlElementWrapper annotation doesn't support @XmlJavaTypeAdapter
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: AddressesListWrapper.java 22756 2012-03-23 10:39:15Z sergey.prilukin $
 */
@XmlRootElement
public class AddressesListWrapper {

    private List<String> addresses;

    public AddressesListWrapper(){}

    public AddressesListWrapper(List<String> addresses){
        this.addresses = addresses;
    }
    @XmlElement(name = "address")
    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
