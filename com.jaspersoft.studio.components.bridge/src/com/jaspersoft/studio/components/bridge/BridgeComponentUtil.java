/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge;

import com.jaspersoft.jasperreports.bridge.BridgeItemProperty;

/**
 * Utility class for model-like operations involving the Bridge component.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class BridgeComponentUtil {
	
	public static String getBridgeItemPropertyValueAsString(BridgeItemProperty property) {
		return getBridgeItemPropertyValueAsString(property, false);
	}
	
	public static String getBridgeItemPropertyValueAsString(BridgeItemProperty property, boolean addDoubleQuotes) {
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
