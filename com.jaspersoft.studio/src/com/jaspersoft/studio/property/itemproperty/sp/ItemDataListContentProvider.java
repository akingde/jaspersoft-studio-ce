/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.sp;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Tree content provider for a list of {@link MapDataElementDTO}.
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class ItemDataListContentProvider implements ITreeContentProvider {
	private boolean showItemData;

	public ItemDataListContentProvider(boolean showItemData) {
		this.showItemData = showItemData;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List<?>) {
			if (showItemData) {
				return ((List<?>) inputElement).toArray();
			} else {
				List<Item> list = new ArrayList<Item>();
				for (Object id : ((List<?>) inputElement)) {
					if (id instanceof ItemData)
						list.addAll(((ItemData) id).getItems());
				}
				return list.toArray();
			}
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ItemData)
			return ((ItemData) parentElement).getItems().toArray();
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
