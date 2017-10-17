/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.json;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.json.JsonSupportNode;

/**
 * Content provider for the tree visualizing the json information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public final class JsonTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof MRoot){
			return ((MRoot) inputElement).getChildren().toArray();
		}
		if(inputElement instanceof JsonTreeCustomStatus){
			return new Object[]{inputElement};
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof JsonSupportNode){
			return ((JsonSupportNode) parentElement).getChildren().toArray();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof JsonSupportNode){
			return ((JsonSupportNode) element).getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length>0;
	}
	
}
