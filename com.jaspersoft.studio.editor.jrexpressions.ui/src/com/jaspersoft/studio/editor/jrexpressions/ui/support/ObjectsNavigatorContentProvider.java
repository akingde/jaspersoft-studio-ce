/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.jaspersoft.studio.editor.jrexpressions.ui.support.java.JavaExpressionEditorComposite;


/**
 * Content provider for the navigator tree containing the categories of object
 * items.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see JavaExpressionEditorComposite Example of usage of the content
 *      provider
 * 
 */
public class ObjectsNavigatorContentProvider implements ITreeContentProvider {

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	public Object getParent(Object element) {
		return null;
	}

	public Object[] getElements(Object inputElement) {
		// The passed element will be NameIconItem representing the objects root
		// categories
		// Usually these are the root categories (most of the times a subset of
		// them)
		// - Parameters
		// - Field
		// - Variables
		// - CrossTabs
		// - Built-in functions
		// - User Defined Expressions
		// - Recent Expressions
		if (inputElement != null
				&& inputElement instanceof ObjectCategoryItem[]) {
			return (Object[]) inputElement;
		}
		return new Object[0];
	}

	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof ObjectCategoryItem) {
			Object data = ((ObjectCategoryItem) parentElement).getData();
			if (data != null && data instanceof ObjectCategoryItem[]) {
				return (ObjectCategoryItem[]) data;
			}
		}

		return new Object[0];
	}

}
