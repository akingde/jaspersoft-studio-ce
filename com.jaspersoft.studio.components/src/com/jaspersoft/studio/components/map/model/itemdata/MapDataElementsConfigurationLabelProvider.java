/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.itemdata;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementsConfiguration;

/**
 * Label Provider for the {@link MapDataElementsConfiguration} element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapDataElementsConfigurationLabelProvider extends LabelProvider {

	private String itemsKind;
	
	public MapDataElementsConfigurationLabelProvider(String itemsKind) {
		super();
		this.itemsKind = itemsKind;
	}

	@Override
	public String getText(Object element) {
		if(element == null) {
			return NLS.bind(Messages.MapDataElementsConfigurationLabelProvider_NoElementsDefined,itemsKind); 
		}
		else if (element instanceof MapDataElementsConfiguration){
			int size = ((MapDataElementsConfiguration) element).getElements().size();
			return NLS.bind(Messages.MapDataElementsConfigurationLabelProvider_ElementsNum, itemsKind, size);
		}
		else {
			return super.getText(element);			
		}
	}
}
