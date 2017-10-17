/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.descriptor.seriescolor;

import java.util.Set;

import org.eclipse.jface.viewers.LabelProvider;
/*
 * @author Chicu Veaceslav
 * 
 */
public class SeriesColorLabelProvider extends LabelProvider {

	public SeriesColorLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return "";
		if (element instanceof Set<?>)
			return "[Colors: " + ((Set<?>) element).size() + "]";
		return element.toString();
	}

}
