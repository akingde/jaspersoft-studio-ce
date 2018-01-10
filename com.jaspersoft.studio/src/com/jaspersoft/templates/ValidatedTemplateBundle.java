/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
