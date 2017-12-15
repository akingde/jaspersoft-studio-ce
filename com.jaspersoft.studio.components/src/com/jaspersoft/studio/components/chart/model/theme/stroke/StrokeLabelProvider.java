/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.stroke;

import java.awt.BasicStroke;

import org.eclipse.jface.viewers.LabelProvider;

/*
 * @author Chicu Veaceslav
 * 
 */
public class StrokeLabelProvider extends LabelProvider {

	public StrokeLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return "";
		if (element instanceof BasicStroke) {
			BasicStroke bs = (BasicStroke) element;
			float d = 1f;
			float[] da = bs.getDashArray();
			if (da != null && da.length > 0)
				d = da[0];
			return "[" + ((BasicStroke) element).getLineWidth() + "," + d + "," + ((BasicStroke) element).getDashPhase() + "]";
		}
		return element.toString();
	}

}
