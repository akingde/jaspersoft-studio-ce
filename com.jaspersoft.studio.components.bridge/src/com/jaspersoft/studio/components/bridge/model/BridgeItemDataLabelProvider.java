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
package com.jaspersoft.studio.components.bridge.model;

import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;

import com.jaspersoft.jasperreports.components.bridge.design.BridgeDesignComponent;
import com.jaspersoft.studio.components.bridge.messages.Messages;

/**
 * Label provider for the {@link BridgeDesignComponent#PROPERTY_ITEM_DATA}
 * property in the Advanced property tab.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class BridgeItemDataLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if(element instanceof List<?>) {
			return NLS.bind(Messages.BridgeItemDataLabelProvider_TextStr,((List) element).size());
		}
		return super.getText(element);
	}
	
}
