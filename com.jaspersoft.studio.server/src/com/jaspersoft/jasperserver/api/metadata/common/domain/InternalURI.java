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
 * A URI reference to an object in our metadata
 * 
 * @author swood
 * 
 */
public interface InternalURI {

	public String getURI();

	public String getPath();

	public String getProtocol();

	public String getParentURI();

	public String getParentPath();
}
