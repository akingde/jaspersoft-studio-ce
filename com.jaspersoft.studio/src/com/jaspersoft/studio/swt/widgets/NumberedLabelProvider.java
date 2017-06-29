/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import org.eclipse.jface.viewers.LabelProvider;

/**
 * Label provider that should be used when listing generic elements
 * without a specific text. The representing label will be composed by a prefix, 
 * plus the item incremental position.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class NumberedLabelProvider extends LabelProvider {
	private int currentIdx = 1;
	private String prefix;
	
	public NumberedLabelProvider(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public String getText(Object element) {
		return prefix + currentIdx++;
	}
	
	public void resetIndex() {
		currentIdx=1;
	}
}
