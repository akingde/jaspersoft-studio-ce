/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization;

import com.jaspersoft.jasperreports.customvisualization.CVItemProperty;


/**
 * Utility class for model-like operations involving the Custom Visualization component.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CVComponentUtil {
	
	public static final String PHANTOMJS_INSTALLATION_PATH_PROPERTY = 
			"com.jaspersoft.jasperreports.components.customvisualization.phantomjs.executable.path"; //$NON-NLS-1$
	public static final String PHANTOJS_WARNING_PROPERTY = "phantomJS.installation.check"; //$NON-NLS-1$
	public static final String PHANTOMJS_VERSIONCHECK_CMD = "phantomjs -v";
	public static final String PHANTOMJS_URL_DOWNLOAD = "http://phantomjs.org/download.html";
	
	public static String getCVItemPropertyValueAsString(CVItemProperty property) {
		return getCVItemPropertyValueAsString(property, false);
	}
	
	public static String getCVItemPropertyValueAsString(CVItemProperty property, boolean addDoubleQuotes) {
		if(property!=null) {
			String propertyValue=property.getValue();
			if(propertyValue == null && property.getValueExpression()!=null) {
				propertyValue = property.getValueExpression().getText();
			}
			else {
				if(addDoubleQuotes){
					propertyValue = "\""+propertyValue+"\""; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			return propertyValue;
		}
		return null;
	}
}
