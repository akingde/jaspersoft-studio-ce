/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.xpath;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.xml.XMLNode;

/**
 * Content provider for the Xpath tree viewer.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public final class XPathTreeViewerContentProvider implements
		ITreeContentProvider {
	public Object[] getChildren(Object element) {
		if(element instanceof XMLNode){
			return ((ANode)element).getChildren().toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if(element instanceof XMLNode){
			return ((XMLNode) element).getParent();
		}
		return null;
	}

	public Object[] getElements(Object element) {
		if (element instanceof XMLTreeCustomStatus){
			return new Object[]{element};
		}
		if(element instanceof MRoot){
			return ((MRoot) element).getChildren().toArray();
		}
		return getChildren(element);
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object old_input,
			Object new_input) {
	}
}
