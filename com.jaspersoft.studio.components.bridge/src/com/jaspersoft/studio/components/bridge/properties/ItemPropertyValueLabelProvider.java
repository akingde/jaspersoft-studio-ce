/*******************************************************************************
 * Copyright (C) 2010 - 2014 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *  
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 	Jaspersoft Studio Team - initial API and implementation
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
