/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.strokes;

import java.util.Collection;

import org.eclipse.jface.viewers.LabelProvider;

/*
 * @author Chicu Veaceslav
 * 
 */
public class StrokesLabelProvider extends LabelProvider {

	public StrokesLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return "";
		if (element instanceof Collection)
			return "[Size: " + ((Collection<?>) element).size() + "]";
		return element.toString();
	}

}
