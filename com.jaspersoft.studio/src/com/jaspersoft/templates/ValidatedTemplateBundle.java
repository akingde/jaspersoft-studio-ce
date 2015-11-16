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
package com.jaspersoft.templates;

import java.util.List;

/**
 * A template bundle can implement this interface to provide the validation of
 * the configuration before to be used to generate a report. This is actually
 * used in the new report wizard to avoid the user to generate a report with 
 * an unsupported template bundle
 * 
 * @author Orlandin Marco
 *
 */
public interface ValidatedTemplateBundle {

	/**
	 * Validate the current JSS configuration
	 * 
	 * @return a list of error if the current configuration is not valid, the error can
	 * then be displayed. It return null or an empty list if the configuration is valid
	 */
	public List<String> validateConfiguration();
	
}
