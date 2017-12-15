/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.itemdata;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataDatasetDTO;

/**
 * Label Provider for a list of {@link MapDataDatasetDTO} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapDataDatasetsLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if(element instanceof MapDataDatasetDTO){
			return ((MapDataDatasetDTO) element).getName();
		}
		return super.getText(element);
	}
	
}
