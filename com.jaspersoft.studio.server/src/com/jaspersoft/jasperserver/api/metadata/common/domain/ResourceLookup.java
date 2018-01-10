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

import com.jaspersoft.jasperserver.api.JasperServerAPI;



/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id:ResourceLookup.java 2403 2006-03-16 14:08:12Z lucian $
 */
@JasperServerAPI
public interface ResourceLookup extends Resource
{
	void setURI(String uri);
	
	
	void setResourceType(String resourceType);
}
