/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.itemdata;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementDTO;

/**
 * Tree content provider for a list of {@link MapDataElementDTO}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapDataElementsContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List<?>){
			return ((List<?>) inputElement).toArray();
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof MapDataElementDTO){
			return ((MapDataElementDTO) parentElement).getDataItems().toArray();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length>0;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	
}
