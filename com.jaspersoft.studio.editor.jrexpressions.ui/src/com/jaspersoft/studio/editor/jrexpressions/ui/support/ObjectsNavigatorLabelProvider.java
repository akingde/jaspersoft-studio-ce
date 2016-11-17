/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for the navigator tree containing the categories of object items.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ObjectsNavigatorLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if(element!=null && 
				element instanceof ObjectCategoryItem){
			return ((ObjectCategoryItem) element).getIcon();
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if(element!=null &&
				element instanceof ObjectCategoryItem){
			return ((ObjectCategoryItem) element).getDisplayName();
		}
		return super.getText(element);
	}

}
