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

package com.jaspersoft.jasperserver.api.engine.common.domain;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: Request.java 29021 2013-02-28 13:07:08Z ykovalchyk $
 */
public interface Request
{
    public static final String PARAM_NAME_FRESH_DATA = "freshData";
	
	String getId();
	
}
