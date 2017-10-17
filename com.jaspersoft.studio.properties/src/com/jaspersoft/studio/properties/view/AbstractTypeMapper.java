/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

/**
 * Default implementation of a type mapper.
 * 
 * @author Anthony Hunter
 */
public class AbstractTypeMapper
    implements ITypeMapper {

    public Class<? extends Object> mapType(Object object) {
        return object.getClass();
    }

}
