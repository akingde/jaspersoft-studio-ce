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


package com.jaspersoft.jasperserver.api.common.service;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ServletContextInformation.java 8408 2007-05-29 23:29:12Z melih $
 */
public interface ServletContextInformation {

	boolean jspExists(String path);
	
}
