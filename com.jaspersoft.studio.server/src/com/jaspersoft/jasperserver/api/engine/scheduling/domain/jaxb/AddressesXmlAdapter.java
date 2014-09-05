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

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: AddressesXmlAdapter.java 22756 2012-03-23 10:39:15Z sergey.prilukin $
 */
public class AddressesXmlAdapter extends XmlAdapter<AddressesListWrapper, List<String>> {
    @Override
    public List<String> unmarshal(AddressesListWrapper v) throws Exception {
        return v != null ? v.getAddresses() : null;
    }

    @Override
    public AddressesListWrapper marshal(List<String> v) throws Exception {
        return v != null ? new AddressesListWrapper(v) : null;
    }
}
