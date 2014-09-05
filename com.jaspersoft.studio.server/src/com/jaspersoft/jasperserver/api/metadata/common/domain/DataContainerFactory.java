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


package com.jaspersoft.jasperserver.api.metadata.common.domain;

/**
 * The interface for the factory that creates the DataContainer
 * See {@link com.jaspersoft.jasperserver.api.metadata.common.domain.DataContainer}
 *
 * @author Lucian Chirita
 * @version $Id:
 */
public interface DataContainerFactory {

    /**
     * Creates and returns the new DataContainer
     *
     * @return DataContainer
     */    
	DataContainer createDataContainer();
	
}
