/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge.properties;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.jaspersoft.jasperreports.components.bridge.BridgeItemProperty;
import com.jaspersoft.studio.components.bridge.BridgeComponentUtil;
import com.jaspersoft.studio.utils.Misc;

/**
 * Label provider for the column value of a table containing a list of
 * {@link BridgeItemProperty}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyValueLabelProvider extends ColumnLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof BridgeItemProperty) {
			String value = BridgeComponentUtil
					.getBridgeItemPropertyValueAsString((BridgeItemProperty) element);
			return Misc.nvl(value);
		}
		return super.getText(element);
	}

}
