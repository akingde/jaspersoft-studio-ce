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

package com.jaspersoft.jasperserver.api.common.domain.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * This adapter can be used to convert any type of internal property to string value using defined in enumeration mapping.
 * Where: enumeration constant name - string value;
 *        enumeration constant value - internal value.
 * Enumeration should implement NamedPropertyHolder interface.
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: AbstractEnumXmlAdapter.java 30161 2013-03-22 19:20:15Z inesterenko $
 */
public abstract class AbstractEnumXmlAdapter<PropertyType> extends XmlAdapter<String, PropertyType>{
     @Override
    public PropertyType unmarshal(String v) throws Exception {
        PropertyType result = null;
        if (v != null && !"".equals(v))
            for(NamedPropertyHolder<PropertyType> currentConstant : getEnumConstantsList())
            if(currentConstant.name().equals(v)){
                result = currentConstant.getProperty();
                break;
            }
        return result;
    }

    @Override
    public String marshal(PropertyType v) throws Exception {
         String result = null;
        for (NamedPropertyHolder<PropertyType> currentConstant : getEnumConstantsList()) {
            if (currentConstant.getProperty().equals(v)) {
                result = currentConstant.name();
                break;
            }
        }
        return result;
    }

    protected List<NamedPropertyHolder<PropertyType>> getEnumConstantsList(){
        return Arrays.asList(getEnumConstantsArray());
    }

    protected abstract NamedPropertyHolder<PropertyType>[] getEnumConstantsArray();

}
