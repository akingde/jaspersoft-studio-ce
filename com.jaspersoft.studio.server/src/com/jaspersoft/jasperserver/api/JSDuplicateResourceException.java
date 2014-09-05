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
package com.jaspersoft.jasperserver.api;

/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id
 */
public class JSDuplicateResourceException extends JSException
{
	public JSDuplicateResourceException(Throwable cause)
	{
		super(cause);
	}

	public JSDuplicateResourceException(String message)
	{
		super(message);
	}

	public JSDuplicateResourceException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public JSDuplicateResourceException(String message,  Object[] args)
	{
		super(message, args);
	}

}
