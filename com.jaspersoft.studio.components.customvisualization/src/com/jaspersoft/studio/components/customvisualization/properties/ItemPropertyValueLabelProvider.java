/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;
 

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.jaspersoft.studio.components.customvisualization.CVComponentUtil;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.eclipse.util.Misc;

/**
 * Label provider for the column value of a table containing a list of
 * {@link CVItemProperty}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyValueLabelProvider extends ColumnLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof ItemProperty) {
			String value = CVComponentUtil
					.getCVItemPropertyValueAsString((ItemProperty) element);
			return Misc.nvl(value);
		}
		return super.getText(element);
	}

}
